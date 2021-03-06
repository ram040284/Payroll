<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Advance</title>
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
	var salaryList = ${salaries};
	
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var desgId = "${advance.designationId}";
	var deptId = "${advance.departmentId}";
	var headId = "${advance.headId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${advance.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	var advId = "${advance.advanceId}";
	
	if(advId == 0)
		{
			$('#advanceDate').datepick({dateFormat: 'dd/mm/yyyy'});
			$('#inlineDatepicker').datepick({onSelect: showDate});	
			
			var d = new Date();
			$('#advanceDate').datepick('setDate', d);
		    var currMonth = d.getMonth();
		    var currYear = d.getFullYear();
		    var currDay  = 25 ;// d.getDay(); //25;
		    var installStartDate = new Date(currYear, currMonth + 1, currDay);
			    $('#installStartDate').datepick({dateFormat: 'dd/mm/yyyy'});
			    $('#installStartDate').datepick('setDate', installStartDate);
			    
			var installEndDate = new Date(currYear, currMonth + 11, currDay);
			    $('#installEndDate').datepick({dateFormat: 'dd/mm/yyyy'});
			    $('#installEndDate').datepick('setDate', installEndDate);
		}
	
	$('#addAdvanceButtotn').click(function(event) {
		
		var advanceId = "${advance.advanceId}";
		var advanceName = "${advance.advanceName}";
		var advanceDate = "${advance.advanceDate}";
		var advanceAmount = "${advance.advanceAmount}";
		var installAmount = "${advance.installAmount}";
		var installStartDate = "${advance.installStartDate}";
		var installEndDate = "${advance.installEndDate}";
			
			if(advanceId !=0){
				if(advanceAmount == $('#advanceAmount').val() && advanceName == $('#advanceName').val() && 
						installAmount == $('#installAmount').val() && advanceDate == $('#advanceDate').val() && installStartDate == $('#installStartDate').val() && installEndDate == $('#installEndDate').val()){
					alert('Nothing was changed');
					$('#advanceName').focus();
					return false;
				}
			}
		if($('#departmentId').val() == 0){
			alert("Department must be selected!");
			$('#departmentId').focus();
			return false;
		}
		if($('#headId').val() == 0){
			alert("Budget Head must be selected!");
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
		if($('#advanceName').val() == 0){
			alert("advance Order must be provided!");
			$('#advanceName').focus();
			return false;
		}
		if($('#advanceDate').val() == 0){
			alert("advance Date must be provided!");
			$('#advanceDate').focus();
			return false;
		}
		if($('#installStartDate').val() == 0){
			alert("installStartDate Date must be provided!");
			$('#installStartDate').focus();
			return false;
		}
		
		if($('#installEndDate').val() == 0){
			alert("installEndDate Date must be provided!");
			$('#installEndDate').focus();
			return false;
		}
		
		var installAmount = $('#installAmount').val();
		if(installAmount){
			if(!checkAmount(installAmount)){
				alert("Invalid installAmount!");
				$('#installAmount').focus();
				return false;
			}
		}else {
			alert("advance installAmount must be provided!");
			$('#installAmount').focus();
			return false;
		}
		var advanceAmount = $('#advanceAmount').val();
		if(advanceAmount){
			if(!checkAmount(advanceAmount)){
				alert("Invalid advance Amount!");
				$('#advanceAmount').focus();
				return false;
			}
		}else {
			alert("advance Amount must be provided!");
			$('#advanceAmount').focus();
			return false;
		}
			
		$.each(salaryList, function( index, value ) {
	
			if($('#employeeId').val() == value.employeeId ){
			
				if(value.gradePay > 4800){
					$("#errMsgDiv").text("Not Eligible for receiving Festival Advance since grade pay is not equal to OR less than 4800 !!");
		        	$("#errMsgDiv").show();
				}
				else{
					var inputJson = { "advanceId" : $('#advanceId').val(),"employeeId" : $('#employeeId').val(),"advanceName" : $('#advanceName').val(),"advanceAmount" : $('#advanceAmount').val(),
							"installAmount" : $('#installAmount').val(),"advanceDate": $('#advanceDate').val(),"installStartDate": $('#installStartDate').val(), "installEndDate": $('#installEndDate').val()};
					
					$.ajax({
				        url: '../Payroll/addEmployeeAdvance',
				        data: JSON.stringify(inputJson),
				        type: "POST",           
				        contentType: "application/json;charset=utf-8",
				        success: function(data){ 
				            if(data == "Yes"){
				            	window.location = "../Payroll/viewAdvance";
				            }else {
				            	$("#errMsgDiv").text(data);
					        	$("#errMsgDiv").show();
				            }
				        }
				    });
				    event.preventDefault();
				}
			}   // if empid
			
			else{
				$("#errMsgDiv").text("EmployeeId not match !!");
	        	$("#errMsgDiv").show();
			}
			
			
		});   // salary
	});
});	

function showDate(date) {
	alert('The date chosen is ' + date);
}
function checkAmount(value){
	var decimal=  /^\d+(\.\d{1,2})?$/;   
	if(value.match(decimal)) {   
		return true;  
	}
	return false;
}

/* function updateInstallStartDate(){
	var d = $('#advanceDate').val();
	alert('Inside : Advance Date = ' + d);
	var currMonth = d.getMonth();
    var currYear = d.getFullYear();
    var currDay  = d.getDay();
    alert('Inside : CurrMonth = ' + currMonth);
    var installStartDate = new Date(currYear, currMonth + 2, currDay);
    $('#installStartDate').datepick({dateFormat: 'dd/mm/yyyy'});
    $('#installStartDate').datepick('setDate', installStartDate);
}
     */ 
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewAdvance')">Back</button>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${advance.advanceId != '0'}" >	Update</c:if><c:if test="${advance.advanceId == '0'}">Add</c:if> advance Amount
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${advance.advanceId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${advance.advanceId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							
							
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Designation:</label>
									<select id="designationId" class="form-control" onchange="getEmployees()"
									<c:if test="${advance.advanceId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Designation --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${advance.advanceId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Advance Name:</label>
									<form:input path="advanceName" placeholder="Enter Advance Name"  id="advanceName" class="form-control" value=""/>
								</div>
								<div class="col-sm-6 form-group">
									<label>Advance Date:</label>
									<c:if test="${advance.advanceId != '0'}" > 
										<form:input path="advanceDate" id="advanceDate"  placeholder="Enter Advance Date" class="form-control" value="" disabled="true"/>
										<%-- 	<form:input path="advanceDate" id="advanceDate" class="form-control" value="" onchange ="updateInstallStartDate()" disabled="true"/> --%>
									 </c:if>
									 <c:if test="${advance.advanceId == '0'}" > 
										<form:input path="advanceDate" id="advanceDate" placeholder="Enter Advance Date" class="form-control" value="" disabled="true"/>
									 </c:if>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Advance Amount:</label>
									<form:input path="advanceAmount"  id="advanceAmount" placeholder="Enter Advance Amount" class="form-control"/>
									<form:input type="hidden" path="advanceId" id="advanceId" />
								</div>							
								<div class="col-sm-6 form-group">
									<label>Installment Amount:</label>
									<form:input path="installAmount" id="installAmount" placeholder="Enter Installment Amount" class="form-control" />
								</div>
								<div class="col-sm-6 form-group">
									<label>Installment Start Date:</label>
									<c:if test="${advance.advanceId != '0'}" >
										<form:input path="installStartDate" id="installStartDate"  placeholder="Enter Installment Start Date" class="form-control" value="" disabled="true"/>
									</c:if>
									<c:if test="${advance.advanceId == '0'}" >
										<form:input path="installStartDate" id="installStartDate" placeholder="Enter Installment Start Date" class="form-control" value="" disabled="true"/>
									</c:if>
								</div>
								<div class="col-sm-6 form-group">
									<label>Installment End Date:</label>
									<c:if test="${advance.advanceId != '0'}" >
										<form:input path="installEndDate"  id="installEndDate" placeholder="Enter Installment End Date" class="form-control" value=""  disabled="true"/>
									</c:if>
									<c:if test="${advance.advanceId == '0'}" >
										<form:input path="installEndDate"  id="installEndDate" placeholder="Enter Installment End Date" class="form-control" value="" disabled="true"/>
									</c:if>
								</div>
							</div>
						
						<div class="row">	
							<div class="text-right">
								<button type="button" id="addAdvanceButtotn" class="btn">Submit</button>
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