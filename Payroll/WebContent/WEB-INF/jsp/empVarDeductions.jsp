<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Deductions</title>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var departmentList = ${departments};
						$.each(departmentList, function(index, value) {
							$('<option>').val(value.departmentId).text(
									value.departmantName).appendTo(
									'#departmentId');
						});
						var deptId = "${empDeductions.departmentId}";
						var headId = "${empDeductions.headId}";
						var desgId = "${empDeductions.designationId}";
						var employeeName = "{$empDeductions.fullName}";
						$('#departmentId').val(deptId);
						if (deptId != 0) {
							getHeadsByDept(deptId, headId);
						}
						if (headId != 0) {
							loadDesgByHead(headId, desgId);
						}
						$('#departmentId').val(deptId);
						$('#headId').val(headId);
						$('#designationId').val(desgId);
						var empId = "${empDeductions.employeeId}";
						if (empId != 0) {
							getEmployeesByIds(deptId, desgId, empId);
						}

						$('#addDeductBtn')
								.click(
										function(event) {
											var afkRentVal = $(
													'#afkRent').val();
											var societyVal = $('#society').val()
													.trim();
											var otherDeductionsVal = $(
													'#otherDeductions').val();
											var miscRecoveryVal = $('#miscRecovery').val();
											var monthDateVal = $('#monthDate').val();
											if (empId != 0) {
												var afkRent = "${empDeductions.afkRent}";
												var society = "${empDeductions.society}";
												var otherDeductions = "${empDeductions.otherDeductions}";
												var miscRecovery = "${empDeductions.miscRecovery}";
												var monthDate = "${empDeductions.monthDate}";
												if (afkRent == afkRentVal
														&& society == societyVal
														&& otherDeductions == otherDeductionsVal
														&& miscRecovery == miscRecoveryVal
														&& monthDate == monthDateVal) {
													alert('Nothing was changed');
													$('#afkRent').focus();
													return false;
												}
											}
											if ($('#departmentId').val() == 0) {
												alert("Department must be selected!");
												$('#departmentId').focus();
												return false;
											}
											if ($('#headId').val() == 0) {
												alert("Budget Head must be selected!");
												$('#headId').focus();
												return false;
											}

											if ($('#designationId').val() == 0) {
												alert("Designation must be selected!");
												$('#designationId').focus();
												return false;
											}
											if ($('#employeeId').val() == 0) {
												alert("Employee must be selected!");
												$('#employeeId').focus();
												return false;
											}
											if (societyVal && isNaN(societyVal)) {
												alert("Please enter valid society amount!");
												$('#society').focus();
												return false;
											}
											if (afkRentVal
													&& isNaN(afkRentVal)) {
												alert("Please enter valid AFK Rent Value!");
												$('#afkRent').focus();
												return false;
											}
											if (otherDeductionsVal
													&& isNaN(otherDeductionsVal)) {
												alert("Please enter valid other deductions value!");
												$('#otherDeductions').focus();
												return false;
											}
											if (miscRecoveryVal && isNaN(miscRecoveryVal)) {
												alert("Please enter valid Misc Recovery Value!"+ otherDeductionsVal);
												$('#miscRecovery').focus();
												return false;
											}
											if (monthDateVal && isNaN(monthDateVal)) {
												alert("Please enter valid month and date Value!");
												$('#monthDate').focus();
												return false;
											}
											var inputJson = {
												"employeeId" : $('#employeeId').val(),
												"afkRent" : afkRentVal,
												"addUpdate" : $('#addUpdate').val(),
												"society" : societyVal,
												"miscRecovery" : miscRecoveryVal,
												"otherDeductions" : otherDeductionsVal,
												"monthDate" : monthDateVal
											};
											$
													.ajax({
														url : '../Payroll/addEmpFixedDeductions',
														data : JSON
																.stringify(inputJson),
														type : "POST",
														contentType : "application/json;charset=utf-8",
														success : function(data) {
															if (data == "Yes") {
																window.location = "../Payroll/viewEmpFixedDeductions";
															} else {
																$("#errMsgDiv")
																		.text(
																				data);
																$("#errMsgDiv")
																		.show();
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
			<div
				style="display: none; color: red; font-weight: bold; height: 15px;"
				id="errMsgDiv"></div>
			<div class="formDiv">
				<h4
					style="color: #fff; padding: 5px; background-color: #8B9DC3; text-transform: none;">
					<c:if test="${empDeductions.employeeId != '0'}">	Update</c:if>
					<c:if test="${empDeductions.employeeId == '0'}">Add</c:if>
					Employee Deduction Details
				</h4>

				<div class="col-lg-12 card-block bg-faded"
					style="margin-bottom: 10px;">
					<div class="row">
						<form:form method="POST" action="">
							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Department</label> <select id="departmentId"
											class="form-control" onchange="getHeads()"
											<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Department --</option>
										</select>
									</div>

									<div class="col-sm-4 form-group">
										<label>Head:</label> <select id="headId" class="form-control"
											onchange="loadDesignations()"
											<c:if test="${empDeductions.employeeId != '0'}" > disabled= "disabled" </c:if>>
											<option value="0">-- Select Head --</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>Designation:</label> <select id="designationId"
											class="form-control" onchange="getEmployees()"
											<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Designation --</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Employee:</label> <select id="employeeId"
											class="form-control"
											<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Employee --</option>
										</select>
									</div>
																		<div class="col-sm-4 form-group">
										<label>AFK Rent:</label>
										<form:input path="afkRent" id="afkRent"
											placeholder="Enter Union Fee" class="form-control" />
									</div>
									
									<div class="col-sm-4 form-group">
										<label>Society:</label>
										<form:input path="society" id="society" placeholder="Enter QTR society"
											class="form-control" />
									</div>
								</div>
								<div class="row">

									<div class="col-sm-4 form-group">
										<label>Other Dedutions:</label>
										<form:input path="otherDeductions" id="otherDeductions"
											placeholder="Enter Court Recovery" class="form-control" />
									</div>
									<div class="col-sm-4 form-group">
										<label>Misc Recovery:</label>
										<form:input path="miscRecovery" id="miscRecovery"
											placeholder="Enter Union Fee" class="form-control" />
									</div>

									<div class="col-sm-4 form-group">
										<label>GIS:</label>
										<form:input path="monthDate" id="monthDate"
											placeholder="Enter GIS value" class="form-control" />
										<input type="hidden" name="addUpdate" id="addUpdate"
											<c:if test="${empAllowance.employeeId != '0'}" > value="1" </c:if> />
											
									</div>
								</div>
								<div class="row">
									<div class="text-right">
										<button type="button" id="addDeductBtn" class="btn">Submit</button>
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