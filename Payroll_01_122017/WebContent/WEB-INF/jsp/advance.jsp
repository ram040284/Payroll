<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Designation</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	var designationList = ${designations};
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});
	$('#paymentDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	
	$('#addAdvBtn').click(function(event) {
		alert('add advance');
		if($('#advanceId').val()!= "0"){
			var advanceAmount = "${advance.advanceAmount}";
			var designationId = "${advance.designationId}";
			var departmentId = "${advance.departmentId}";
			var paymentDate = "${advance.paymentDate}";
			if(advanceAmount == $('#conveyanceAmount').val() && designationId == $('#designationId').val() && 
					departmentId == $('#departmentId').val() && paymentDate == $('#paymentDate').val()){
				alert('Nothing was changed');
				$('#departmentId').focus();
				return false;
			}
		}
		alert('add advance1');
		if($('#departmentId').val() == 0){
			alert("Department must be selected!");
			$('#departmentId').focus();
			return false;
		}
		if($('#designationId').val() == 0){
			alert("Designation must be selected!");
			$('#designationId').focus();
			return false;
		}
		if($('#employeeId').val() == 0){
			alert("Employee must be selected!");
			$('#employeeId').focus();
			return false;
		}
		if($('#paymentDate').val() == 0){
			alert("Payment Date must be selected!");
			$('#paymentDate').focus();
			return false;
		}
		if($('#advanceAmount').val() == 0){
			alert("Advance Amount must be provided!");
			$('#advanceAmount').focus();
			return false;
		}
		var inputJson = { "advanceAmount" : $('#advanceAmount').val(), "departmentId" : $('#departmentId').val(), 
				"designationId" : $('#designationId').val(), "empId" : $('#employeeId').val(), "paymentDate": $('#paymentDate').val()};
	    $.ajax({
	        url: '../Payroll/addAdvance',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewAdvance";
	            }
	        }
	    });
	    event.preventDefault();
	});
});

function getEmployees() {
	if($('#designationId').val() == 0 || $('#designationId').val() == 0){
		alert("Department and Designation must be selected!");
		$('#departmentId').focus();
		return false;
	}
	var inputJson = { "departmentId" : $('#departmentId').val(),"designationId" : $('#designationId').val()};
		  $.ajax({
	        url: '../Payroll/loadEmployees',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	        	$('#employeeId').empty();
	        	$('<option>').val(0).text("-- Select Employee --").appendTo('#employeeId');
	        	$(data).each(function(i, employee){
	        		$('<option>').val(employee.employeeId).text(employee.fullName).appendTo('#employeeId');
            	});
	        },
	        failure: function (){
	        	alert('Unable to load Employees');
	        }
	    });
	    event.preventDefault();
	
}
function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${advance.advanceId != '0'}" >Update</c:if><c:if test="${advance.advanceId == '0'}">Add</c:if> Advance Amount
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getEmployees()">
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							
							<div class="col-sm-6 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()">
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Employee:</label>
								<select id="employeeId" class="form-control">
									<option value="0">-- Select Employee --</option>
								</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Payment Date:</label>
								<form:input path="paymentDate" id="paymentDate" placeholder="Date (DD/MM/YYYY)" class="form-control"/>
							</div>
							
						</div>
							
						<div class="row">	
							<div class="col-sm-6 form-group">
								<label>Advance Amount:</label>
								<form:input path="advanceAmount"  id="advanceAmount" class="form-control"/>
								<form:input type="hidden" path="advanceId" id="advanceId" />
							</div>
						</div>
						<div class="row">	
							<div class="text-right form-group">
								<button type="button" id="addAdvBtn" class="btn">Submit</button>
								<button type="reset" class="btn">Reset</button>	
							</div>	
						</div>
					</div>
						
				</form:form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>