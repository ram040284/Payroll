<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Advance</title>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${advance.departmentId}";
	var headId = "${advance.headId}";
	var desgId = "${advance.designationId}";
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

	$('#addAdvanceBtn').click(function(event) {
		alert("Save Advance");
				var advanceId = "${advance.advanceId}";
				var advanceName = "${advance.advanceName}";
				var advanceAmount = "${advance.advanceAmount}";
				var installAmount = "${advance.installAmount}";
				var advanceDate = "${advance.advanceDate}";
				if(empId != 0){
					if(advanceId == $('#advanceId').val() && advanceName == $('#advanceName').val() && advanceAmount == $('#advanceAmount').val() &&
							installAmount == $('#installAmount').val()  && advanceDate == $('#advanceDate').val() ){
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
		
		var inputJson = { "employeeId" : $('#employeeId').val(), "advanceId" : $('#advanceId').val(),
				"installAmount" : $('#installAmount').val(),"advanceName" : $('#advanceName').val(), "advanceAmount" : $('#advanceAmount').val(),
				"advanceDate" : $('#advanceDate').val(),  "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addAdvance',
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
					<c:if test="${advance.employeeId != '0'}">	Update</c:if>
					<c:if test="${advance.employeeId == '0'}">Add</c:if>
					Employee Advance
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
											<c:if test="${advance.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Department --</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>Head:</label> <select id="headId" class="form-control"
											onchange="loadDesignations()"
											<c:if test="${advance.employeeId != '0'}" > disabled= "disabled" </c:if>>
											<option value="0">-- Select Head --</option>
										</select>
									</div>

									<div class="col-sm-4 form-group">
										<label>Designation:</label> <select id="designationId"
											class="form-control" onchange="getEmployees()"
											<c:if test="${advance.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Designation --</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Employee:</label> <select id="employeeId"
											class="form-control"
											<c:if test="${advance.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Employee --</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>advanceId:</label>
										<form:input type="text" id="advanceId" path="advanceId"
											placeholder="Enter advanceId" class="form-control" />
									</div>
																		<div class="col-sm-4 form-group">
										<label>Advance Amount:</label>
										<form:input path="advanceAmount" id="advanceAmount"
											placeholder="Enter Advance Amount" class="form-control" />
									</div>
									

								</div>
								<div class="row">

									<div class="col-sm-4 form-group">
										<label>Advance Date:</label>
										<form:input path="advanceDate" id="advanceDate"
											placeholder="Selct Advance Date:" class="form-control" />

									</div>
									
								</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Advance Name:</label>
										<form:input path="advanceName" id="advanceName"
											placeholder="Select Advance Name:"
											class="form-control" />

									</div>
									<div class="col-sm-4 form-group">
										<label>Installment Amount</label>
										<form:input path="installAmount" id="installAmount"
											placeholder="Enter Installment Amount" class="form-control" />
									</div>	
								</div>
								<div class="row">
									<div class="text-right">
										<button type="button" id="addAdvanceBtn" class="btn">Submit</button>
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