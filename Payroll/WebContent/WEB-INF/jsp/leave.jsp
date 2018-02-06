<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Emp Leave</title>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	<%--var designationList = ${designations};--%>
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${leave.departmentId}";
	var desgId = "${leave.designationId}";
	var headId = "${leave.headId}";
	var leaveIds = "${leave.leaveIds}";
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	var empId = "${leave.employeeId}";
	$('#departmentId').val(deptId);
	$('#leaveIds').val(leaveIds);
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	$('#addLeaveBtn').click(function(event) {
			if(empId != 0){
				var sickLeaves = "${leave.sickLeaves}";
				var casualLeaves = "${leave.casualLeaves}";
				var paidLeaves = "${leave.paidLeaves}";
				var earnLeave = "${leave.earnLeave}";
				var maternityLeave = "${leave.maternityLeave}";
				var paternityLeave = "${leave.paternityLeave}";
				var extraLeave = "${leave.extraLeave}";
				if(sickLeaves == $('#sickLeaveInp').val() && casualLeaves == $('#casualLeaveInp').val() && paidLeaves == $('#paidLeaveInp').val() &&
						earnLeave == $('#earnLeaveInp').val() && maternityLeave == $('#maternityLeaveInp').val() && paternityLeave == $('#paternityLeaveInp').val()&& 
						extraLeave == $('#extraLeave').val()){
					alert('Nothing was changed');
					$('#sickLeaves').focus();
					return false;
				}
			}
			if(empId == 0){
				if($('#departmentId').val() == 0){
					alert("Department must be selected!");
					$('#departmentId').focus();
					return false;
				}
				if($('#headId').val() == 0){
					alert("Head must be selected!");
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
			}
			if($('#sickLeaveInp').val() == 0 && $('#casualLeaveInp').val() == 0 && $('#paidLeaveInp').val() == 0 && 
					$('#earnLeaveInp').val() == 0 && $('#maternityLeaveInp').val() == 0 && $('#paternityLeaveInp').val() == 0 && 
					$('#extraLeave').val() ==0){
				alert("At least one (Sick/Casual/Paid/Earned/Maternity/Paternity/Extraordinary) must be provided!");
				$('#sickLeaveInp').focus();
				return false;
			}
		var empIdInput = 0;
		if(empId !=0)
			empIdInput = empId;
		else
			empIdInput = $('#employeeId').val();
		
		var inputJson = { "employeeId" : empIdInput, "sickLeaveInp" : $('#sickLeaveInp').val(),  
				"casualLeaveInp" : $('#casualLeaveInp').val(), "paidLeaveInp" : $('#paidLeaveInp').val(), "leaveIds": $('#leaveIds').val(),
				"earnLeaveInp" : $('#earnLeaveInp').val(), "maternityLeaveInp" : $('#maternityLeaveInp').val(), "paternityLeaveInp" : $('#paternityLeaveInp').val(),  
				"extraLeaveInp" : $('#extraLeaveInp').val()};
		$.ajax({
	        url: '../Payroll/addLeave',
	        data: JSON.stringify(inputJson),
	        type: "POST",
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	var f = document.forms['leaveInputForm'];
	            	var submittedForm = document.forms['leaveForm'];
	            	f.departmentId.value = submittedForm.departmentId.value;
	            	f.headId.value = submittedForm.headId.value;
	            	f.action="../Payroll/viewLeave_1";
	            	f.submit();
	            }else {
	            	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
	            }
	        }
	    });
	    event.preventDefault();
	});
});
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${leave.employeeId != '0'}" >	Update</c:if><c:if test="${leave.employeeId == '0'}">Add</c:if> Employee Leave
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action ="" name="leaveForm">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentId" name="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${leave.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Head:</label>
								<select id="headId" name="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${leave.employeeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${leave.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control" <c:if test="${leave.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
							
							</div>
							<div class="row">
									<div class="col-sm-4 form-group">
										<label>Medical Leaves: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.sickLeaves}"/></span></c:if> </label>
										<form:input path="sickLeaveInp"  id="sickLeaveInp" class="form-control"/>
									</div>
									<div class="col-sm-4 form-group">
										<label>Casual Leaves: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.casualLeaves}"/></span></c:if></label>
										<form:input path="casualLeaveInp"  id="casualLeaveInp" class="form-control"/>
									</div>
									<div class="col-sm-4 form-group">
										<label>Half Pay Leave: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.paidLeaves}"/></span></c:if></label>
										<form:input path="paidLeaveInp"  id="paidLeaveInp" class="form-control"/>
									</div>
									
							</div>
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>Earned Lvs: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.earnLeave}"/></span></c:if> </label>
									<form:input path="earnLeaveInp"  id="earnLeaveInp" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Ptrnty Lvs: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.paternityLeave}"/></span></c:if></label>
									<form:input path="paternityLeaveInp"  id="paternityLeaveInp" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Mtrnity Lvs: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.maternityLeave}"/></span></c:if></label>
									<form:input path="maternityLeaveInp"  id="maternityLeaveInp" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Xtrdry Lvs: <c:if test="${leave.employeeId != '0'}" ><span style="color: #0101DF; margin-left: 5px;">Avl. Bal:<c:out value="${leave.extraLeave}"/></span></c:if></label>
									<form:input path="extraLeaveInp"  id="extraLeaveInp" class="form-control"/>
								</div>
	
							</div>	
							<div class="row">
								<div class="text-right">
									<button type="button" id="backBtn" class="btn text-left" style="margin-left: 10px; float: left;" onclick="getList('../Payroll/viewLeave_1')">Back</button>
									<button type="button" id="addLeaveBtn" class="btn">Submit</button>
									<button type="reset" class="btn">Reset</button>	
								</div>	
							</div>
							
					</div>
					<input type="hidden" name = "leaveId" id="leaveId" />
					<input type="hidden" name = "leaveIds" id="leaveIds" />				
				</form:form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<form action="" name="leaveInputForm" method="post">
		<input type="hidden" name="headId" value="0">
		<input type="hidden" name="departmentId" value="0">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>