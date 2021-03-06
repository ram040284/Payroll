<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Fixed Allowances</title>
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
	var deptId = "${empAllowance.departmentId}";
	var headId = "${empAllowance.headId}";
	var desgId = "${empAllowance.designationId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${empAllowance.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	var nonPracAwance = "${empAllowance.nonPracAwance}";
	
	var hraFlag = "${empAllowance.hraFlag}";
	var qtrFlag = "${empAllowance.qtrFlag}";
	var afkFlag = "${empAllowance.afkFlag}";
	var taFlag = "${empAllowance.taFlag}";
	var pfFlag = "${empAllowance.pfFlag}";
	var npaFlag = "${empAllowance.npaFlag}";

	$('#hraFlag').val(hraFlag);
	$('#qtrFlag').val(qtrFlag);
	$('#afkFlag').val(afkFlag);
	$('#taFlag').val(taFlag);
	$('#pfFlag').val(pfFlag);
	$('#npaFlag').val(npaFlag);
	
	if(empId != 0)
		$('#hraFlag').val(hraFlag);
	
	$('#addAllowanceBtn').click(function(event) {
		var cca = "${empAllowance.cca}";
		var washingAlwance = "${empAllowance.washingAlwance}";
		var nonPracAwance = "${empAllowance.nonPracAwance}";
		var uniformAlwance = "${empAllowance.uniformAlwance}";
		var familyPlanAlwance ="${empAllowance.familyPlanAlwance}";
		var cycleAlwance = "${empAllowance.cycleAlwance}";
		var npaFlag = "${empAllowance.npaFlag}";
		
		if(empId != 0){
			if(cca == $('#cca').val() && familyPlanAlwance == $('#familyPlanAlwance').val() && cycleAlwance == $('#cycleAlwance').val() &&
					washingAlwance == $('#washingAlwance').val()  && nonPracAwance == $('#nonPracAwance').val() && uniformAlwance == $('#uniformAlwance').val() && 
					hraFlag == $('#hraFlag').val() && qtrFlag == $('#qtrFlag').val() && afkFlag == $('#afkFlag').val() && taFlag == $('#taFlag').val() && pfFlag == $('#pfFlag').val() && npaFlag == $('#npaFlag').val()){
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
		
		if($('#cca').val() < 1 && $('#washingAlwance').val() < 1  &&
				$('#familyPlanAlwance').val() < 1 && $('#cycleAlwance').val() < 1 &&
				 $('#nonPracAwance').val() < 1 && $('#uniformAlwance').val() < 1 && $('#hraFlag').val()=='0'  && $('#qtrFlag').val()=='0'  && $('#afkFlag').val()=='0' && $('#taFlag').val()=='0' && $('#pfFlag').val()=='0'){
			alert("Please provide atleast one allowance amount or select HRA!");
			$('#cca').focus();
			return false;
		}
		var inputJson = { "employeeId" : $('#employeeId').val(), "cca" : $('#cca').val(),  
				"washingAlwance" : $('#washingAlwance').val(), "familyPlanAlwance" : $('#familyPlanAlwance').val(), "cycleAlwance" : $('#cycleAlwance').val(),
				"nonPracAwance" : $('#nonPracAwance').val(), "uniformAlwance" : $('#uniformAlwance').val(), 
				"hraFlag": $('#hraFlag').val(),"qtrFlag": $('#qtrFlag').val(),"afkFlag": $('#afkFlag').val(),"taFlag": $('#taFlag').val(), "pfFlag": $('#pfFlag').val(), "npaFlag": $('#npaFlag').val(), "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addEmpAllowance',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewEmpAlwnce";
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
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewEmpAlwnce')">Back</button>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div style="display: none; color: red; font-weight: bold; height: 15px;"id="errMsgDiv"></div> 
			<div class="formDiv">
				<h4 style="color: #fff; padding: 5px; background-color: #8B9DC3; text-transform: none;">
					<c:if test="${empAllowance.employeeId != '0'}">	Update</c:if>
					<c:if test="${empAllowance.employeeId == '0'}">Add</c:if>
					Employee Fixed Allowances
				</h4>

				<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
					<div class="row">
						<form:form method="POST" action="">
							<div class="col-sm-12">
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Department</label> <select id="departmentId"
											class="form-control" onchange="getHeads()"
											<c:if test="${empAllowance.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Department --</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>Head:</label> <select id="headId" class="form-control"
											onchange="loadDesignations()"
											<c:if test="${empAllowance.employeeId != '0'}" > disabled= "disabled" </c:if>>
											<option value="0">-- Select Head --</option>
										</select>
									</div>

									<div class="col-sm-4 form-group">
										<label>Designation:</label> <select id="designationId"
											class="form-control" onchange="getEmployees()"
											<c:if test="${empAllowance.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Designation --</option>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Employee:</label> 
										<select id="employeeId"
											class="form-control"
											<c:if test="${empAllowance.employeeId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Employee --</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>CCA:</label>
										<form:input type="text" id="cca" path="cca"
											placeholder="Enter CCA" class="form-control" />
									</div>
									<div class="col-sm-4 form-group">
										<label>Washing Allowance:</label>
										<form:input path="washingAlwance" id="washingAlwance"
											placeholder="Enter Washing Allowance" class="form-control" />
									</div>

								</div>
								<div class="row">
								
									<div class="col-sm-4 form-group">
										<label>Is NPA applicable?</label><br />
										<form:select path = "npaFlag" id="npaFlag" class="form-control" onchange="getNPA()">
											<form:option value="0" label="No" />
											<form:option value="1" label="Yes" />
										</form:select>  
									</div> 
									
									
									<div class="col-sm-4 form-group">
										<label>Non-Practical Allowance:</label>
										<form:input path="nonPracAwance" id="nonPracAwance"	
											placeholder="Enter Non-Prac Allowance" class="form-control" />
									</div>
									
									<div class="col-sm-4 form-group">
										<label>Family Planning Allowance:</label>
										<form:input path="familyPlanAlwance" id="familyPlanAlwance"
											placeholder="Enter Family Planning Allowance"
											class="form-control" />

									</div>
									</div>
								<div class="row">
									<div class="col-sm-4 form-group">
										<label>Cycle Allowance:</label>
										<form:input path="cycleAlwance" id="cycleAlwance"
											placeholder="Enter Cycle Allowance" class="form-control" />
									</div>
									<div class="col-sm-4 form-group">
										<label>HRA:</label>
										<select id="hraFlag" class="form-control">
											<option value="0">-- Select HRA --</option>
											<option value="true">1</option>
											<option value="false">0</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>QTR FLag:</label>
										<select id="qtrFlag" class="form-control">
											<option value="0">-- Select QTR Flag --</option>
											<option value="true">1</option>
											<option value="false">0</option>
										</select>
									</div>
                                    </div>
                                  
                                  <div class="row">
                                    <div class="col-sm-4 form-group">
										<label>AFK Flag:</label>
										<select id="afkFlag" class="form-control">
											<option value="0">-- Select AFK Flag --</option>
											<option value="true">1</option>
											<option value="false">0</option>
										</select>
									</div>
									<div class="col-sm-4 form-group">
										<label>TA Flag</label>
										<form:input path="taFlag" id="taFlag"
										placeholder="Enter TA" class="form-control"/>
                                    </div>
									<div class="col-sm-4 form-group">
										<label>PF Flag</label>
 										<form:input path="pfFlag" id="pfFlag"
										placeholder="Enter PF" class="form-control"/>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-4 form-group">
											<label>Uniform Allowance:</label>
											<form:input path="uniformAlwance" id="uniformAlwance"
									placeholder="Enter Uniform Allowance" class="form-control" />
									<input type="hidden" name="addUpdate" id="addUpdate"
										<c:if test="${empAllowance.employeeId != '0'}" > value="1" </c:if> />
									</div>
								</div>
								
								<div class="row">
									<div class="text-right">
										<button type="button" id="addAllowanceBtn" class="btn">Submit</button>
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