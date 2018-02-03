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
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var desgId = "${overtime.designationId}";
	var deptId = "${overtime.departmentId}";
	var headId = "${overtime.headId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${overtime.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#overtimeDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	
	$('#addOtimeBtn').click(function(event) {
		
			var overtimeAmount = "${overtime.overtimeAmount}";
			var overtimeDate = "${overtime.overtimeDate}";
			var overtimeOrder = "${overtime.overtimeOrder}";
			var overtimeHours = "${overtime.overtimeHours}";
			var overtimeId = "${overtime.overtimeId}";
			if(overtimeId !=0){
				if(overtimeAmount == $('#overtimeAmount').val() && overtimeOrder == $('#overtimeOrder').val() && 
						overtimeHours == $('#overtimeHours').val() && overtimeDate == $('#overtimeDate').val()){
					alert('Nothing was changed');
					$('#overtimeOrder').focus();
					return false;
				}
			}
		if($('#departmentId').val() == 0){
			alert("Department must be selected!");
			$('#departmentId').focus();
			return false;
		}
		if($('#headId').val() == 0){
			alert("Cost Head must be selected!");
			$('#headId').focus();
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
		if($('#overtimeOrder').val() == 0){
			alert("Overtime Order must be provided!");
			$('#overtimeOrder').focus();
			return false;
		}
		if($('#overtimeDate').val() == 0){
			alert("Overtime Date must be provided!");
			$('#overtimeDate').focus();
			return false;
		}
		var oHoursVal = $('#overtimeHours').val();
		if(oHoursVal){
			if(!checkAmount(oHoursVal)){
				alert("Invalid Overtime Hours!");
				$('#overtimeHours').focus();
				return false;
			}
		}else {
			alert("Overtime Hours must be provided!");
			$('#overtimeHours').focus();
			return false;
		}
		var oAmountVal = $('#overtimeAmount').val();
		if(oAmountVal){
			if(!checkAmount(oAmountVal)){
				alert("Invalid Overtime Amount!");
				$('#overtimeAmount').focus();
				return false;
			}
		}else {
			alert("Overtime Amount must be provided!");
			$('#overtimeAmount').focus();
			return false;
		}
		
		var inputJson = { "overtimeAmount" : $('#overtimeAmount').val(), "overtimeHours" : $('#overtimeHours').val(),  
				"overtimeOrder" : $('#overtimeOrder').val(), "employeeId" : $('#employeeId').val(), 
				"overtimeDate": $('#overtimeDate').val(), "overtimeId" : $('#overtimeId').val()};
		$.ajax({
	        url: '../Payroll/addOvertime',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewOvertime";
	            }else {
	            	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
	            }
	        }
	    });
	    event.preventDefault();
	});
});

function showDate(date) {
	alert('The date chosen is ' + date);
}
function checkAmount(value){
	var decimal=  /^\d+(\.\d{2,2})?$/;   
	if(value.match(decimal)) {   
		return true;  
	}
	return false;
}
     
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${overtime.overtimeId != '0'}" >	Update</c:if><c:if test="${overtime.overtimeId == '0'}">Add</c:if> Overtime Amount
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${overtime.overtimeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${overtime.overtimeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							
							
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Designation:</label>
									<select id="designationId" class="form-control" onchange="getEmployees()"
									<c:if test="${overtime.overtimeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Designation --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${overtime.overtimeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Overtime Order:</label>
									<form:input path="overtimeOrder" placeholder="Enter Overtime Order"  id="overtimeOrder" class="form-control" value=""/>
								</div>
								<div class="col-sm-6 form-group">
									<label>Overtime Date:</label>
									<form:input path="overtimeDate" placeholder="Enter Ovetime Date (DD/MM/YYYY)"  id="overtimeDate" class="form-control" value=""/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Overtime hours:</label>
									<form:input path="overtimeHours" id="overtimeHours" placeholder="Enter Ovetime Hours (HH.MM)" class="form-control"/>
								</div>
								<div class="col-sm-6 form-group">
									<label>Overtime Amount:</label>
									<form:input path="overtimeAmount"  id="overtimeAmount" placeholder="Enter Ovetime Amount" class="form-control"/>
									<form:input type="hidden" path="overtimeId" id="overtimeId" />
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