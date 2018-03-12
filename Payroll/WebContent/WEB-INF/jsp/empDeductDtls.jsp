<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${empDeductions.departmentId}";
	var headId = "${empDeductions.headId}";
	var desgId = "${empDeductions.designationId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${empDeductions.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#addDeductBtn').click(function(event) {
		var afkRentVal = $('#afkRent').val().trim();
		var societyVal = $('#society').val().trim();
		var electRecoveryVal = $('#electRecovery').val().trim();
		var courtRecoveryVal = $('#courtRecovery').val().trim();
		var unionFeeVal = $('#unionFee').val().trim();
		var otherDeductionsVal = $('#otherDeductions').val().trim();
		var miscRecoveryVal = $('#miscRecovery').val().trim();
		var kssUnionRecoveryVal = $('#kssUnionRecovery').val().trim();
		
		if(empId !=0){
			var afkRent = "${empDeductions.afkRent}";
			var society = "${empDeductions.society}";
			var electRecovery = "${empDeductions.electRecovery}";
			var courtRecovery = "${empDeductions.courtRecovery}";
			var unionFee = "${empDeductions.unionFee}";
			var otherDeductions = "${empDeductions.otherDeductions}";
			var miscRecovery = "${empDeductions.miscRecovery}";
			var kssUnionRecovery = "${empDeductions.kssUnionRecovery}";
			
			if(afkRent == afkRentVal && society == societyVal && electRecovery == electRecoveryVal &&  
					courtRecovery == courtRecoveryVal && unionFee == unionFeeVal && otherDeductions == otherDeductionsVal
					 && miscRecovery == miscRecoveryVal && kssUnionRecovery == kssUnionRecoveryVal){
				alert('Nothing was changed');
				$('#afkRent').focus();
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
		if(afkRentVal && isNaN(afkRentVal)){
			alert("Please enter valid AFK Rent!");
			$('#afkRent').focus();
			return false;
		}
		if(societyVal && isNaN(societyVal)){
			alert("Please enter valid Society Value!");
			$('#society').focus();
			return false;
		}
		if(electRecoveryVal && isNaN(electRecoveryVal)){
			alert("Please enter valid Electricity Recovery Value!");
			$('#electRecovery').focus();
			return false;
		}
		if(courtRecoveryVal && isNaN(courtRecoveryVal)){
			alert("Please enter valid Court Recovery value!");
			$('#courtRecovery').focus();
			return false;
		}
		if(unionFeeVal && isNaN(unionFeeVal)){
			alert("Please enter valid Union Fee Value!");
			$('#unionFee').focus();
			return false;
		}
		if(otherDeductionsVal && isNaN(otherDeductionsVal)){
			alert("Please enter valid Other Deductions Value!");
			$('#otherDeductions').focus();
			return false;
		}
		if(miscRecoveryVal && isNaN(miscRecoveryVal)){
			alert("Please enter valid Misc. Recovery Value!");
			$('#miscRecovery').focus();
			return false;
		}
		if(kssUnionRecoveryVal && isNaN(kssUnionRecoveryVal)){
			alert("Please enter valid KSS Union Reovery Value!");
			$('#kssUnionRecovery').focus();
			return false;
		}
		
		
		var inputJson = { "employeeId" : $('#employeeId').val(), "afkRent" : afkRentVal, "addUpdate": $('#addUpdate').val(), 
				"society" : societyVal, "unionFee" : unionFeeVal, "miscRecovery" : miscRecoveryVal, "kssUnionRecovery" : kssUnionRecoveryVal,
				"otherDeductions" : otherDeductionsVal, "courtRecovery" : courtRecoveryVal, "electRecovery" : electRecoveryVal};
		$.ajax({
	        url: '../Payroll/addEmpDeductDtls',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewEmpDeductDtls";
	            }else{
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
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${empDeductions.employeeId != '0'}" >	Update</c:if><c:if test="${empDeductions.employeeId == '0'}">Add</c:if> Employee Deduction Details</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${empDeductions.employeeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							<div class="col-sm-4 form-group">
									<label>Designation:</label>
									<select id="designationId" class="form-control" onchange="getEmployees()"
									<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Designation --</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-4 form-group">
									<label>AFK Rent:</label>
									<form:input path="afkRent"  id="afkRent" placeholder="Enter AFK Rent" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empDeductions.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Society:</label>
									<form:input path="society"  id="society" placeholder="Enter Society" class="form-control"/>
								</div>
							
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>Electricity Recovery:</label>
									<form:input path="electRecovery"  id="electRecovery" placeholder="Enter Electricity Recovery" class="form-control"/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Court Recovery:</label>
									<form:input path="courtRecovery"  id="courtRecovery" placeholder="Enter Court Recovery" class="form-control"/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Union Fee:</label>
									<form:input path="unionFee"  id="unionFee" placeholder="Enter Union Fee" class="form-control"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>Other Deductions:</label>
									<form:input path="otherDeductions"  id="otherDeductions" placeholder="Enter Other Deductions" class="form-control"/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Misc. Recovery:</label>
									<form:input path="miscRecovery"  id="miscRecovery" placeholder="Enter Misc. Recovery" class="form-control"/>
								</div>
								<div class="col-sm-4 form-group">
									<label>KSS Union Recovery:</label>
									<form:input path="kssUnionRecovery"  id="kssUnionRecovery" placeholder="Enter KSS Union Recovery" class="form-control"/>
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