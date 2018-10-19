<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Pension</title>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	var empId = "${pension.employeeId}";
	var pensionFlag = "${pension.familyPensionFlag}";
	if (pensionFlag == 2) {
		$("input[type=radio]").attr('disabled', true);
		$('#familyDiv').css('visibility', 'visible');
		$("#family").prop('checked', true);
	}else {
		$("input[type=radio]").attr('disabled', false);
		$('#familyDiv').css('visibility', 'hidden');
		$("#self").prop('checked', true);
		
	}
	$.ajax({
	        url : '../Payroll/loadAllEmployees',
	        type:"GET",
	        async: false,
	        contentType: "application/json;charset=utf-8",
	        success : function(data) {
	        $('#employeeId').empty();
	 	    $('<option>').val(0).text("-- Select Employee --").appendTo('#employeeId');
	 	    $(data).each(function(i, employee){
	 	    	$('<option>').val(employee.employeeId).text(employee.fullName).appendTo('#employeeId');
	 	    });
	 	    if(empId !=0) {
	 	  	$('#employeeId').val(empId);
	        }
	     }
	});
	
	$('#addEmpPension').click(function(event) {
		
			var basicPension = "${pension.basicPension}";
			var medicalAllowance = "${pension.medicalAllowance}";
			var familyPensionName = "${pension.familyPensionName}";
			var pensionRemark = "${pension.pensionRemark}";
			var commutationAmount = "${pension.commutationAmount}";
			var residualPension = "${pension.residualPension}";
			var dearnessReliefArrears = "${pension.dearnessReliefArrears}";
			if(empId !=0){
				if(basicPension == $('#basicPension').val() && medicalAllowance == $('#medicalAllowance').val() && 
						familyPensionName == $('#familyPensionName').val() && pensionRemark == $('#pensionRemark').val()
						&& commutationAmount == $('#commutationAmount').val() && residualPension == $('#residualPension').val()
						&& dearnessReliefArrears == $('#dearnessReliefArrears').val()){
					alert('Nothing was changed');
					$('#employeeId').focus();
					return false;
				}
			}
			if(empId ==0){
				if($('#employeeId').val() == 0){
					alert("Employee must be selected!");
					$('#employeeId').focus();
					return false;
				}
			}
			var basicPensionVal = $('#basicPension').val().trim();
			if(basicPensionVal != ""){
				if(isNaN(basicPensionVal)){
					alert("Invalid Basic Pension!");
					$('#basic').focus();
					return false;
				}
			}else {
				alert("Basic Pension must be provided!");
				$('#basic').focus();
				return false;
			}
			var medicalAllowanceVal = $('#medicalAllowance').val().trim();
			if(medicalAllowanceVal){
				if(isNaN(medicalAllowanceVal)){
					alert("Invalid Medical Allowance!");
					$('#medicalAllowance').focus();
					return false;
				}
			}else {
				alert("Medical Allowance must be provided!");
				$('#medicalAllowance').focus();
				return false;
			}
			var commutationAmountVal = $('#commutationAmount').val().trim();
			if(commutationAmountVal){
				if(isNaN(commutationAmountVal)){
					alert("Invalid Commutation Amount!");
					$('#commutationAmount').focus();
					return false;
				}
			}else {
				alert("Medical Commutation Amount must be provided!");
				$('#commutationAmount').focus();
				return false;
			}
			var residualPensionVal = $('#residualPension').val().trim();
			if(residualPensionVal){
				if(isNaN(residualPensionVal)){
					alert("Invallid Residual Pension!");
					$('#residualPension').focus();
					return false;
				}
			}else {
				alert("Medical Residual Pension must be provided!");
				$('#residualPension').focus();
				return false;
			}
			/* if($('#familyPensionName').val().trim() == ""){
				alert("Fami;ly Pensioner must be provided!");
				$('#familyPensionName').focus();
				return false;
			} */
			if($('#pensionRemark').val().trim() == ""){
				alert("Pension remark must be provided!");
				$('#pensionRemark').focus();
				return false;
			}
			var dearnessReliefArrearsVal = $('#dearnessReliefArrears').val().trim();
			if(dearnessReliefArrearsVal){
				if(isNaN(dearnessReliefArrearsVal)){
					alert("Invalid Dearness relief arrears!");
					$('#dearnessReliefArrears').focus();
					return false;
				}
			}else {
				alert("Dearness relief arrears must be provided!");
				$('#dearnessReliefArrears').focus();
				return false;
			}
			var empIdInput = 0;
			if(empId !=0)
				empIdInput = empId;
			else
				empIdInput = $('#employeeId').val();
			
			var inputJson = { "employeeId" : empIdInput, "basicPension" : $('#basicPension').val(),  
					"medicalAllowance" : $('#medicalAllowance').val(), "familyPensionName" : $('#familyPensionName').val(), 
					"pensionRemark": $('#pensionRemark').val(), "commutationAmount": $('#commutationAmount').val(), "residualPension": $('#residualPension').val(),
					"addUpdate": $('#addUpdate').val(),"familyPensionFlag": $("input[name='pensionerType']:checked").val(), 
					"dearnessReliefArrears": $('#dearnessReliefArrears').val()};
			$.ajax({
		        url: '../Payroll/addPension',
		        data: JSON.stringify(inputJson),
		        type: "POST",           
		        contentType: "application/json;charset=utf-8",
		        success: function(data){ 
		            if(data == "Yes"){
		            	window.location = "../Payroll/viewPension";
		            }else{
		            	$("#errMsgDiv").text(data);
			        	$("#errMsgDiv").show();
		            }
		        }
		    });
		    event.preventDefault();
	});
	
});
function btnType(){
	var pensionerType = $("input[name='pensionerType']:checked").val();
	if (pensionerType == 2) {
		$('#familyDiv').css('visibility', 'visible');
	}else {
		$('#familyDiv').css('visibility', 'hidden');
	}
	
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
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewPension#')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${pension.employeeId != '0'}" >	Update</c:if><c:if test="${pension.employeeId == '0'}">Add</c:if> Employee Pension
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-10 form-group">
									<label style="margin-top: 10px;">Pensioner Type: </label> 
									<input type="radio" id="self" name= "pensionerType" style="margin-left: 8px;" checked="checked" value="1" onclick="btnType()"/> Self
									<input type="radio" id="family" name= "pensionerType" style="margin-left: 8px;" value="2" onclick="btnType()"/> Family
								</div>
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${pension.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-4 form-group"  style="visibility:hidden;" id = "familyDiv">
									<label>Family pensioner name:</label>
									<form:input path="familyPensionName"  id="familyPensionName" placeholder="Enter Family Pensioner Name" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
							</div>
							<div class="row">
								
								<div class="col-sm-4 form-group">
									<label>Medical allowances:</label>
									<form:input path="medicalAllowance"  id="medicalAllowance" placeholder="Enter Medical Allowance" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Basic Pension:</label>
									<form:input path="basicPension"  id="basicPension" placeholder="Enter Basic Pension" class="form-control"/>
									<input type="hidden" name="addUpdate" id=addUpdate <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Dearness Relief Arrears:</label>
									<form:input path="dearnessReliefArrears"  id="dearnessReliefArrears" placeholder="Enter Dearness Relief Arrears" class="form-control"/>
									<input type="hidden" name="addUpdate" id=addUpdate <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
							</div>
							<div class="row">
								
								<div class="col-sm-4 form-group">
									<label>Commutation Amount</label>
									<form:input path="commutationAmount"  id="commutationAmount" placeholder="Enter Commutation Amount" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Residual Pension:</label>
									<form:input path="residualPension"  id="residualPension" placeholder="Enter Residual Pension" class="form-control"/>
									<input type="hidden" name="addUpdate" id=addUpdate <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Pension remark:</label>
									<form:textarea path="pensionRemark"  id="pensionRemark" placeholder="Enter Remark" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${pension.employeeId != '0'}" > value="1" </c:if>/>
								</div>
							</div>
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addEmpPension" class="btn">Submit</button>
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