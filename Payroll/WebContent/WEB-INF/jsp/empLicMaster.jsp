<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee LIC</title>
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
	
	var deptId = "${empLic.departmentId}";
	var headId = "${empLic.headId}";
	var desgId = "${empLic.designationId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${empLic.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#policyStartDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#policyMaturityDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	
	$('#addLicBtn').click(function(event) {
				var policyNo = "${empLic.policyNo}";
				var instlmtAmt = "${empLic.instlmtAmt}";
				var policyStartDate = "${empLic.policyStartDate}";
				var policyMaturityDate = "${empLic.policyMaturityDate}";
				if(empId != 0){
					if(policyNo == $('#policyNo').val() && 
							instlmtAmt == $('#instlmtAmt').val() && policyStartDate == $('#policyStartDate').val() && policyMaturityDate == $('#policyMaturityDate').val() ){
						alert('Nothing was changed');
						$('#policyNo').focus();
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
		
		if($('#policyNo').val() == ''){
			alert("Policy No must be selected!");
			$('#policyNo').focus();
			return false;
		}
		if($('#instlmtAmt').val() < 1){
			alert("Installment Amount Amount must be provided!");
			$('#instlmtAmt').focus();
			return false;
		}
		if($('#policyStartDate').val() == ''){
			alert("Policy StartDate must be provided!");
			$('#policyStartDate').focus();
			return false;
		}
		if($('#policyMaturityDate').val() == ''){
			alert("Policy MaturityDate must be provided!");
			$('#policyMaturityDate').focus();
			return false;
		}
		var inputJson = { "employeeId" : $('#employeeId').val(), "policyNo" : $('#policyNo').val()  , "instlmtAmt" : $('#instlmtAmt').val(),
				 "addUpdate": $('#addUpdate').val(), "policyStartDate": $('#policyStartDate').val(),"policyMaturityDate": $('#policyMaturityDate').val()};
		$.ajax({
	        url: '../Payroll/addEmpLicMaster',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewEmpLicMaster";
	            }else{
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
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewEmpLicMaster#')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test='${empLic.employeeId eq "0"}' >	Add</c:if><c:if test='${!(empLic.employeeId eq "0")}' >Update</c:if> Employee LIC
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "GET" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test='${!(empLic.employeeId eq "0")}' >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test='${!(empLic.employeeId eq "0")}' > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test='${!(empLic.employeeId eq "0")}' >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test='${!(empLic.employeeId eq "0")}'> disabled = "disabled"  </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Policy No:</label>
									 <c:if test='${empLic.employeeId eq "0"}'> 
										<form:input path="policyNo"  id="policyNo" placeholder="Enter Policy No" class="form-control"/>
									</c:if>
									
									<c:if test='${!(empLic.employeeId eq "0")}'> 
										 <input path="policyNo"  id="policyNo" disabled="disabled" value="${empLic.policyNo}"
										  class="form-control"/>
									</c:if>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Installment Amount:</label>
								   <form:input path="instlmtAmt"  id="instlmtAmt" placeholder="Enter Installment Amount" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test='${!(empLic.employeeId eq "0")}' > value="1" </c:if>>
								</div>
								<div class="col-sm-6 form-group">
									<label>Policy StartDate:</label>
									<form:input path="policyStartDate"  id="policyStartDate" placeholder="Enter Policy StartDate" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>Policy MaturityDate:</label>
									<form:input path="policyMaturityDate"  id="policyMaturityDate" placeholder="Enter Policy MaturityDate" class="form-control"/>
									
								</div>
								</div>
							
					         <div class="row">	
							<div class="text-right">
								<button type="button" id="addLicBtn" class="btn">Submit</button>
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