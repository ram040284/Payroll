<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Designation</title>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
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
	$('#overtimeDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	
	$('#addOtimeBtn').click(function(event) {
		
			var overtimeAmount = "${overtime.overtimeAmount}";
			var designationId = "${overtime.designationId}";
			var departmentId = "${overtime.departmentId}";
			var overtimeDate = "${overtime.overtimeDate}";
			var employeeId = "${overtime.empId}";
			if(overtimeAmount == $('#overtimeAmount').val() && designationId == $('#designationId').val() && 
					departmentId == $('#departmentId').val() && overtimeDate == $('#overtimeDate').val() && 
					employeeId == $('#employeeId').val()){
				alert('Nothing was changed');
				$('#departmentId').focus();
				return false;
			}
		
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
		
		if($('#overtimeDate').val() == 0){
			alert("Overtime Date must be provided!");
			$('#overtimeDate').focus();
			return false;
		}
		if($('#overtimeAmount').val() == 0){
			alert("Overtime Amount must be provided!");
			$('#overtimeAmount').focus();
			return false;
		}
		var inputJson = { "overtimeAmount" : $('#overtimeAmount').val(), "departmentId" : $('#departmentId').val(),  
				"designationId" : $('#designationId').val(), "empId" : $('#employeeId').val(), "overtimeDate": $('#overtimeDate').val()};
		alert('inputJson:'+inputJson);
	    $.ajax({
	        url: '../Payroll/addOvertime',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewOvertime";
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
				<c:if test="${overtime.empId != '0'}" >	Update</c:if><c:if test="${overtime.empId == '0'}">Add</c:if> Overtime Amount
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
									<label>Overtime Date:</label>
									<form:input path="overtimeDate" placeholder="Date (DD/MM/YYYY)"  id="overtimeDate" class="form-control" value=""/>
								</div>
							
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Overtime hours:</label>
									<form:input path="overtimeAmount"  id="overtimeAmount" class="form-control"/>
									<%-- <form:input type="hidden" path="empId" id="empId" />--%>
								</div>
								<div class="col-sm-6 form-group">
									<label>Overtime Amount:</label>
									<form:input path="overtimeAmount"  id="overtimeAmount" class="form-control"/>
									<%-- <form:input type="hidden" path="empId" id="empId" />--%>
								</div>
						</div>
						<div class="row">	
							<div class="text-right">
								<button type="button" id="addOtimeBtn" class="btn">Submit</button>
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