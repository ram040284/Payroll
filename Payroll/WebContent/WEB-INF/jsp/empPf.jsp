<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Provident Fund</title>
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
	<%--var designationList = ${designations};--%>
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	<%--$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});--%>
	
	var deptId = "${empPf.departmentId}";
	var headId = "${empPf.headId}";
	var desgId = "${empPf.designationId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${empPf.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#pfDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	
	
	$('#addPfBtn').click(function(event) {
				var pfDate = "${empPf.pfDate}";
				var pfsCpfCntrbn = "${empPf.pfsCpfCntrbn}";
				var pfLoneRecAmt = "${empPf.pfLoneRecAmt}";
				var cfLoneRecAmt = "${empPf.cfLoneRecAmt}";
				var apfAcpfCntrbn = "${empPf.apfAcpfCntrbn}";
				if(empId != 0){
					if(pfDate == $('#pfDate').val() && 
							pfsCpfCntrbn == $('#pfsCpfCntrbn').val() && pfLoneRecAmt == $('#pfLoneRecAmt').val() &&
							cfLoneRecAmt == $('#cfLoneRecAmt').val() && apfAcpfCntrbn == $('#apfAcpfCntrbn').val()){
						alert('Nothing was changed');
						$('#employeeId').focus();
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
		
		if($('#pfDate').val() == ''){
			alert("PF Date must be provided!");
			$('#pfDate').focus();
			return false;
		}
		<%--if($('#pfsCpfCntrbn').val() < 1){
			alert("PF - CPF Contribution must be provided!");
			$('#pfsCpfCntrbn').focus();
			return false;
		}
		if($('#apfAcpfCntrbn').val() < 1){
			alert("APF - ACPF Contribution must be provided!");
			$('#apfAcpfCntrbn').focus();
			return false;
		}--%>
		var pfsCpfCntrbnVal = $('#pfsCpfCntrbn').val().trim();
		if(pfsCpfCntrbnVal){
			if(!checkAmount(pfsCpfCntrbnVal)){
				alert("Invalid PF - CPF Contribution amount!");
				$('#pfsCpfCntrbn').focus();
				return false;
			}
		}else {
			alert("PF - CPF Contribution must be provided!");
			$('#pfsCpfCntrbn').focus();
			return false;
		}
		var apfAcpfCntrbnVal = $('#apfAcpfCntrbn').val().trim();
		if(apfAcpfCntrbnVal){
			if(!checkAmount(apfAcpfCntrbnVal)){
				alert("Invalid APF - ACPF Contribution amount!");
				$('#apfAcpfCntrbn').focus();
				return false;
			}
		}else {
			alert("APF - ACPF Contribution must be provided!");
			$('#apfAcpfCntrbn').focus();
			return false;
		}
		var pfLoneRecAmtVal = $('#pfLoneRecAmt').val().trim();
		if(pfLoneRecAmtVal){
			if(!checkAmount(pfLoneRecAmtVal)){
				alert("Invalid PF LOAN amount!");
				$('#pfLoneRecAmt').focus();
				return false;
			}
		}
		var cfLoneRecAmtVal = $('#cfLoneRecAmt').val().trim();
		if(cfLoneRecAmtVal){
			if(!checkAmount(cfLoneRecAmtVal)){
				alert("Invalid APF - ACPF Contribution amount!");
				$('#cfLoneRecAmt').focus();
				return false;
			}
		}
		<%--if($('#pfLoneRecAmt').val() == ''){
			alert("PF Loan Amount must be provided!");
			$('#pfLoneRecAmt').focus();
			return false;
		}
		if($('#cfLoneRecAmt').val() == ''){
			alert("CF Loan Amount must be provided!");
			$('#cfLoneRecAmt').focus();
			return false;
		}--%>
		var inputJson = { "employeeId" : $('#employeeId').val(), "pfDate" : $('#pfDate').val(),  
				"pfsCpfCntrbn" : $('#pfsCpfCntrbn').val(), "apfAcpfCntrbn" : $('#apfAcpfCntrbn').val(), 
				"pfLoneRecAmt" : $('#pfLoneRecAmt').val(), "cfLoneRecAmt" : $('#cfLoneRecAmt').val(), "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addEmpPf',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewEmpPf";
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
				<c:if test="${empPf.employeeId != '0'}" >	Update</c:if><c:if test="${empPf.employeeId == '0'}">Add</c:if> Employee Provident Fund
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${empPf.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${empPf.employeeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${empPf.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${empPf.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>PF Date:</label>
									<form:input type="text" id="pfDate" path="pfDate" placeholder="Enter PF Date (DD/MM/YYYY)" class="form-control"/>
								</div>
							
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>PF-CPF Contribution:</label>
									<form:input path="pfsCpfCntrbn"  id="pfsCpfCntrbn" placeholder="Enter PF - CPF Contribution" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>APF-ACPF Contribution:</label>
									<form:input path="apfAcpfCntrbn"  id="apfAcpfCntrbn" placeholder="Enter APF - ACPF Contribution" class="form-control"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>PF Loan Amount:</label>
									<form:input path="pfLoneRecAmt"  id="pfLoneRecAmt" placeholder="Enter PF Loan Amount" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>CF Loan Amount:</label>
									<form:input path="cfLoneRecAmt"  id="cfLoneRecAmt" placeholder="Enter CF Loan Amount" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empPf.employeeId != '0'}" > value="1" </c:if>/>
								</div>
							</div>
							
							
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addPfBtn" class="btn">Submit</button>
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