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
	<%--$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});--%>
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
	var empId = "${leave.empId}";
	$('#departmentId').val(deptId);
	$('#leaveIds').val(leaveIds);
	<%--$('#designationId').val(desgId);
	var leaveType = "${leave.leaveType}";
	$('#leaveType').val(leaveType);--%>
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	$('#addLeaveBtn').click(function(event) {
			<%--var leaveId = "${leave.leaveId}";
			if(leaveId != 0){
				$('#leaveId').val(leaveId);
				var noOfLeaves = "${leave.noOfLeaves}";
			}--%>
			if(empId != 0){
				var sickLeaves = "${leave.sickLeaves}";
				var casualLeaves = "${leave.casualLeaves}";
				var paidLeaves = "${leave.paidLeaves}";
				if(sickLeaves == $('#sickLeaveInp').val() && casualLeaves == $('#casualLeaveInp').val() && paidLeaves == $('#paidLeaveInp').val()){
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
			if($('#sickLeaveInp').val() == 0 && $('#casualLeaveInp').val() == 0 && $('#paidLeaveInp').val() == 0){
				alert("At least one (Sick/Casual/Paid) must be provided!");
				$('#sickLeaveInp').focus();
				return false;
			}
			<%--if($('#leaveType').val() == 0){
			alert("Leave Type must be selected!");
			$('#leaveType').focus();
			return false;
		}
		if($('#noOfLeaves').val() < 1){
			alert("No. Of Leaves must be provided!");
			$('#noOfLeaves').focus();
			return false;
		}
		if($('#leaveBalance').val() == ''){
			alert("Leave Balance must be provided!");
			$('#leaveBalance').focus();
			return false;
		}--%>
		var empIdInput = 0;
		if(empId !=0)
			empIdInput = empId;
		else
			empIdInput = $('#employeeId').val();
		
		var inputJson = { "empId" : empIdInput, "sickLeaveInp" : $('#sickLeaveInp').val(),  
				"casualLeaveInp" : $('#casualLeaveInp').val(), "paidLeaveInp" : $('#paidLeaveInp').val(), "leaveIds": $('#leaveIds').val()};
		$.ajax({
	        url: '../Payroll/addLeave',
	        data: JSON.stringify(inputJson),
	        type: "POST",
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	<%--window.location = "../Payroll/viewLeave";--%>
	            	var f = document.forms['leaveForm'];
	            	f.action="../Payroll/viewLeave";
	            	f.submit();
	            }else {
	            	alert(data);
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
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${leave.empId != '0'}" >	Update</c:if><c:if test="${leave.empId == '0'}">Add</c:if> Employee Leave
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "" name="leaveForm">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${leave.empId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control" <c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
							
							</div>
							<%--<div class="row">
									<div class="col-sm-6 form-group">
									<label>Leave Type:</label>
									<select id="leaveType" class="form-control" <c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="">-- Select Employee --</option>
										<option value="Sick Leave">Sick Leave</option>
										<option value="Casual Leave">Casual Leave</option>
										<option value="Paid Vacation">Paid Vacation</option>
									</select>
								</div>
							
								<div class="col-sm-6 form-group">
									<label>No.of Leaves:</label>
									<form:input path="noOfLeaves"  id="noOfLeaves" placeholder="Enter No. Of Leaves" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>Leave Balance:</label>
									<form:input path="leaveBalance"  id="leaveBalance" placeholder="Enter Grade Pay" class="form-control"/>
								</div> 
							</div>--%>
							<div class="row">
									<div class="col-sm-4 form-group">
										<label>Sick Leaves: <c:if test="${leave.empId != '0'}" ><span style="color: #0101DF; margin-left: 10px;">Avl. Bal:<c:out value="${leave.sickLeaves}"/></span></c:if> </label>
										<form:input path="sickLeaveInp"  id="sickLeaveInp" class="form-control"/>
									</div>
									<div class="col-sm-4 form-group">
										<label>Casual Leaves: <c:if test="${leave.empId != '0'}" ><span style="color: #0101DF; margin-left: 10px;">Avl. Bal:<c:out value="${leave.casualLeaves}"/></span></c:if></label>
										<form:input path="casualLeaveInp"  id="casualLeaveInp" class="form-control"/>
									</div>
									<div class="col-sm-4 form-group">
										<label>Paid Leave: <c:if test="${leave.empId != '0'}" ><span style="color: #0101DF; margin-left: 10px;">Avl. Bal:<c:out value="${leave.paidLeaves}"/></span></c:if></label>
										<form:input path="paidLeaveInp"  id="paidLeaveInp" class="form-control"/>
									</div>
									
								</div>	
							
							<div class="row">	
								<div class="text-right">
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
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>