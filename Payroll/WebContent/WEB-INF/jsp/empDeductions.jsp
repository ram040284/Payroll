<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Deductions</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
<link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	
	var deptId = "${empDeductions.departmentId}";
	var headId = "${empDeductions.headId}";
	var desgId = "${empDeductions.designationId}";
	
	
	
	//$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${empDeductions.employeeId}";
	if(empId != 0) {
		//getEmployeesByIds(deptId, desgId, empId);
		// need to fix this
		getAllEmployees();
	}else {
		getAllEmployees();
	}
	$('#employeeId').val(empId);
	
	$('#addQtrBtn').click(function(event) {
		var section80CVal = $('#section80C').val().trim();
		var cessVal = $('#cess').val().trim();
		var homeLoanIntrst88EEVal = $('#homeLoanIntrst88EE').val().trim();
		var selfDisable80UVal = $('#selfDisable80U').val().trim();
		var loanPrincipalVal = $('#loanPrincipal').val().trim();
		var schoolFeesVal = $('#schoolFees').val().trim();
		var licVal = $('#lic').val().trim();
		var mutualFundVal = $('#mutualFund').val().trim();
		var section80DVal = $('#section80D').val().trim();
		var section80EVal = $('#section80E').val().trim();
		var nscVal = $('#nsc').val().trim();
		var ppfVal = $('#ppf').val().trim();
		var donationVal = $('#donation').val().trim();
		var section80DDVal = $('#section80DD').val().trim();
		var arrearsVal = $('#arrears').val().trim();
		var bonusVal = $('#bonus').val().trim();
		var otAmountVal = $('#otAmount').val().trim();
		var otWagesVal = $('#otWages').val().trim();
		
		var hra_section10_13A_val = $('#hra_section10_13A').val().trim();
		var income_tax_rebate_section_87C_val = $('#income_tax_rebate_section_87C').val().trim();
		var child_trans_allw_10_14_val = $('#child_trans_allw_10_14').val().trim();
		var home_loan_section_24B_val = $('#home_loan_section_24B').val().trim();
		var hlp_pf_lic_80C_val = $('#hlp_pf_lic_80C').val().trim();
		var nps_80CCD_1B_val = $('#nps_80CCD_1B').val().trim();
		var health_insu_80D_val = $('#health_insu_80D').val().trim();
		var des_dep_80DD_val = $('#des_dep_80DD').val().trim();
		var medical_80DDB_val = $('#medical_80DDB').val().trim();
		var edu_load_80D_val = $('#edu_load_80D').val().trim();
		var donation_80G_val = $('#donation_80G').val().trim();
		var rent_80GG_val = $('#rent_80GG').val().trim();
		var int_bank_section_80TTA_val = $('#int_bank_section_80TTA').val().trim();
		var phys_dis_per_section_80U_val = $('#phys_dis_per_section_80U').val().trim();
		
		if(empId !=0){
			var section80C = "${empDeductions.section80C}";
			var cess = "${empDeductions.cess}";
			var homeLoanIntrst88EE = "${empDeductions.homeLoanIntrst88EE}";
			var selfDisable80U = "${empDeductions.selfDisable80U}";
			var loanPrincipal = "${empDeductions.loanPrincipal}";
			var schoolFees = "${empDeductions.schoolFees}";
			var lic = "${empDeductions.lic}";
			var mutualFund = "${empDeductions.mutualFund}";
			var section80D = "${empDeductions.section80D}";
			var section80E = "${empDeductions.section80E}";
			var nsc = "${empDeductions.nsc}";
			var ppf = "${empDeductions.ppf}";
			var donation = "${empDeductions.donation}";
			var section80DD = "${empDeductions.section80DD}";
			var bonus = "${empDeductions.bonus}";
			var arrears = "${empDeductions.arrears}";
			var otAmount = "${empDeductions.otAmount}";
			var otWages = "${empDeductions.otWages}";
			
			var hra_section10_13A = "${empDeductions.hra_section10_13A}";
			var income_tax_rebate_section_87C = "${empDeductions.income_tax_rebate_section_87C}";
			var child_trans_allw_10_14 = "${empDeductions.child_trans_allw_10_14}";
			var home_loan_section_24B = "${empDeductions.home_loan_section_24B}";
			var hlp_pf_lic_80C = "${empDeductions.hlp_pf_lic_80C}";
			var nps_80CCD_1B = "${empDeductions.nps_80CCD_1B}";
			var health_insu_80D = "${empDeductions.health_insu_80D}";
			var des_dep_80DD = "${empDeductions.des_dep_80DD}";
			var medical_80DDB = "${empDeductions.medical_80DDB}";
			var edu_load_80D = "${empDeductions.edu_load_80D}";
			var donation_80G = "${empDeductions.donation_80G}";
			var rent_80GG = "${empDeductions.rent_80GG}";
			var int_bank_section_80TTA = "${empDeductions.int_bank_section_80TTA}";
			var phys_dis_per_section_80U = "${empDeductions.phys_dis_per_section_80U}";
			
			if(section80C == section80CVal && cess == cessVal && homeLoanIntrst88EE == homeLoanIntrst88EEVal &&  
					selfDisable80U == selfDisable80UVal && loanPrincipal == loanPrincipalVal && schoolFees == schoolFeesVal &&
					lic == licVal && mutualFund == mutualFundVal && section80D == section80DVal && section80E == section80EVal && 
					nsc == nscVal && ppf == ppfVal && donation == donationVal && section80DD == section80DDVal && 
					bonus == bonusVal && arrears == arrearsVal && otAmount == otAmountVal && otWages == otWagesVal 
					&& hra_section10_13A == hra_section10_13A_val  && income_tax_rebate_section_87C == income_tax_rebate_section_87C_val &&
					child_trans_allw_10_14 == child_trans_allw_10_14_val && home_loan_section_24B == home_loan_section_24B_val &&
					hlp_pf_lic_80C == hlp_pf_lic_80C_val && nps_80CCD_1B == nps_80CCD_1B_val && health_insu_80D == health_insu_80D_val &&
					health_insu_80D == health_insu_80D_val && des_dep_80DD == des_dep_80DD_val && medical_80DDB == medical_80DDB_val && 
					edu_load_80D == edu_load_80D_val && donation_80G == donation_80G_val && rent_80GG == rent_80GG_val && 
					int_bank_section_80TTA == int_bank_section_80TTA_val && phys_dis_per_section_80U == phys_dis_per_section_80U_val){
				
				alert('Nothing was changed');
				$('#section80C').focus();
				return false;
			}
		}
		/* if($('#departmentId').val() == 0){
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
		} */
		if(empId == 0){
			empId = $('#employeeId').val();
		}
		if(empId == 0){
			alert("Employee must be selected!");
			$('#employeeId').focus();
			return false;
		}
		<%--if(section80CVal && section80CVal == 0 && cessVal && cessVal == 0 && homeLoanIntrst88EEVal && 
				homeLoanIntrst88EEVal == 0 && selfDisable80UVal && selfDisable80UVal == 0){
			alert("Section 80C or Home Loan Interest or Cess or Self Disable must be provided!");
			$('#section80C').focus();
			return false;
		}--%>
		if(section80CVal && isNaN(section80CVal)){
			if(!checkAmount(section80CVal)){
				alert("Invalid Section 80C Value!");
				$('#section80C').focus();
				return false;
			}
		}
		if(homeLoanIntrst88EEVal && isNaN(homeLoanIntrst88EEVal)){
			if(!checkAmount(homeLoanIntrst88EEVal)){
				alert("Invalid Home Loan Interest Value!");
				$('#homeLoanIntrst88EE').focus();
				return false;
			}
		}
		if(cessVal && isNaN(cessVal)){
			if(!checkAmount(cessVal)){
				alert("Invalid Cess Value!");
				$('#cess').focus();
				return false;
			}
		}
		if(selfDisable80UVal && isNaN(selfDisable80UVal)){
			if(!checkAmount(selfDisable80UVal)){
				alert("Invalid Self Disable 80U Value!");
				$('#selfDisable80U').focus();
				return false;
			}
		}
		if(loanPrincipalVal && isNaN(loanPrincipalVal)){
			alert("Please enter valid Loan Principal!");
			$('#loanPrincipal').focus();
			return false;
		}
		
		if(schoolFeesVal && isNaN(schoolFeesVal)){
			alert("Please enter valid School Fees Value!");
			$('#schoolFees').focus();
			return false;
		}
		
		if(licVal && isNaN(licVal)){
			alert("Please enter valid LIC Value!");
			$('#lic').focus();
			return false;
		}
		
		if(mutualFundVal && isNaN(mutualFundVal)){
			alert("Please enter valid Mutual Fund Value!");
			$('#mutualFund').focus();
			return false;
		}
		
		if(section80DVal && isNaN(section80DVal)){
			alert("Please enter valid Section80D Value!");
			$('#section80D').focus();
			return false;
		}
		
		if(section80EVal && isNaN(section80EVal)){
			alert("Please enter valid Section80E Value!");
			$('#section80E').focus();
			return false;
		}
		if(nscVal && isNaN(nscVal)){
			alert("Please enter valid NSC Value!");
			$('#nsc').focus();
			return false;
		}
		
		if(ppfVal && isNaN(ppfVal)){
			alert("Please enter valid PPF Value!");
			$('#ppf').focus();
			return false;
		}
		
		if(donationVal && isNaN(donationVal)){
			alert("Please enter valid Donation Value!");
			$('#donation').focus();
			return false;
		}
		if(section80DDVal && isNaN(section80DDVal)){
			alert("Please enter valid Section80DD Value!");
			$('#section80DD').focus();
			return false;
		}
		
		if(bonusVal && isNaN(bonusVal)){
			alert("Please enter valid Bonus Value!");
			$('#bonus').focus();
			return false;
		}
		
		if(arrearsVal && isNaN(arrearsVal)){
			alert("Please enter valid Arears Value!");
			$('#arrears').focus();
			return false;
		}
		
		if(otAmountVal && isNaN(otAmountVal)){
			alert("Please enter valid OT Amount!");
			$('#otAmount').focus();
			return false;
		}
		if(otWagesVal && isNaN(otWagesVal)){
			alert("Please enter valid OT Wages!");
			$('#otWages').focus();
			return false;
		}
		var inputJson = { "employeeId" : empId, "section80C" : section80CVal, "addUpdate": $('#addUpdate').val(), 
				"cess" : cessVal, "homeLoanIntrst88EE" : homeLoanIntrst88EEVal, "selfDisable80U" : selfDisable80UVal, 
				"loanPrincipal" : loanPrincipalVal, "schoolFees" : schoolFeesVal, "lic" : licVal, "mutualFund" : mutualFundVal,
				"section80D" : section80DVal, "section80E" : section80EVal, "nsc" : nscVal, "ppf" : ppfVal, 
				"donation" : donationVal, "section80DD" : section80DDVal, "bonus" : bonusVal, "arrears" : arrearsVal, 
				"otAmount" : otAmountVal, "otWages" : otWagesVal, "hra_section10_13A" : hra_section10_13A_val, 
				"income_tax_rebate_section_87C" : income_tax_rebate_section_87C_val, "child_trans_allw_10_14" : child_trans_allw_10_14_val, 
				"home_loan_section_24B" : home_loan_section_24B_val, "hlp_pf_lic_80C" : hlp_pf_lic_80C_val, "nps_80CCD_1B" : nps_80CCD_1B_val,
				"health_insu_80D" : health_insu_80D_val, "des_dep_80DD" : des_dep_80DD_val, "medical_80DDB" : medical_80DDB_val, "edu_load_80D" : edu_load_80D_val,
				"donation_80G" : donation_80G_val, "rent_80GG" : rent_80GG_val, "int_bank_section_80TTA" : int_bank_section_80TTA_val, 
				"phys_dis_per_section_80U" : phys_dis_per_section_80U_val};
		$.ajax({
	        url: '../Payroll/addEmpDeductions',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	        	if(data == "Yes"){
	            	//window.location = "../Payroll/viewEmpDeductions";
	            	var f = document.forms['empDedForm'];
	            	f.action="../Payroll/listEmpDeductions";
	            	f.submit();
	            }else{
	            	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
	            }
	        }
	    });
	    event.preventDefault();
	});
});
function checkAmount(value){
	var decimal=  /^\d+(\.\d{2,2})?$/;   
	if(value.match(decimal)) {   
		return true;  
	}
	return false;
}

function showDate(date) {
	alert('The date chosen is ' + date);
}

function validateEmail(emailText){   
	<%--if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailText)){--%>
	if(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(emailText)){
    	return true;  
  	}  
    return false;  
}

function allNumeric(numberTxt) {
	var numbers = /^[0-9]+$/;
	if(numberTxt.match(numbers)) {
		return true;
	}
	return false;
}

function alphaNumeric(value){
	var letters = /^[0-9a-zA-Z]+$/;
	if(value.match(letters)){
	   return true;
	}
	return false;
}
function emailValid(emailTxt){
    var re = /\S+@\S+/;
    return re.test(emailTxt);
}

function getHeads(){
	if($('#departmentId').val() == 0){
		alert("Department must be selected to get Heads!");
		$('#departmentId').focus();
		return false;
	}
	var deptId = $('#departmentId').val();
	getHeadsByDept(deptId);
}


function getHeadsByDept(deptId) {
	var inputJson = { "departmentId" : deptId};
	  $.ajax({
	    url: '../Payroll/loadHeads',
	    data: JSON.stringify(inputJson),
	    type: "POST",           
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    success: function(data){ 
	    	$('#headId').empty();
	    	$('<option>').val(0).text("-- Select Head --").appendTo('#headId');
	    	$('#designationId').empty();
	    	$('<option>').val(0).text("-- Select Designation --").appendTo('#designationId');
	    	$(data).each(function(i, headInfo){
	    		$('<option>').val(headInfo.headId).text(headInfo.headName).appendTo('#headId');
	    	});
	    	var headId = "${employee.headId}";
	    	if(headId !=0) {
	  		$('#headId').val(headId);
	  		
	  	}
	    },
	    failure: function (){
	    	alert('Unable to load Heads');
	    }
	});

}

function loadDesignations(){
	if($('#headId').val() == 0){
		alert("Head must be selected to get Designations!");
		$('#headId').focus();
		return false;
	}
	var headId = $('#headId').val();
	loadDesgByHead(headId);
}

function loadDesgByHead(headId) {
	var inputJson = { "headId" : headId};
	  $.ajax({
	    url: '../Payroll/loadDesignations',
	    data: JSON.stringify(inputJson),
	    type: "POST",           
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    success: function(data){ 
	    	$('#designationId').empty();
	    	$('<option>').val(0).text("-- Select Designation --").appendTo('#designationId');
	    	$(data).each(function(i, designation){
	    		$('<option>').val(designation.designationId).text(designation.designationName).appendTo('#designationId');
	    	});
	    	var designationId = "${employee.designationId}";
	    	if(designationId !=0) {
	  			$('#designationId').val(designationId);
	  		}
	    },
	    failure: function (){
	    	alert('Unable to load Heads');
	    }
	});
}

function getAllEmployees(){

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
 	    	<%--var empId = "${salary.empId}";--%>
 	    	if(empId !=0) {
 	  		$('#employeeId').val(empId);
         }
       }
     });
}

function getEmployeesByIds(deptId, desgId, empId){
	var inputJson = { "departmentId" : deptId,"designationId" : desgId};
	  $.ajax({
      url: '../Payroll/loadEmployees',
      data: JSON.stringify(inputJson),
      type: "POST",           
      beforeSend: function(xhr) {
          xhr.setRequestHeader("Accept", "application/json");
          xhr.setRequestHeader("Content-Type", "application/json");
      },
      success: function(data){ 
      	$('#employeeId').empty();
      	$('<option>').val(0).text("-- Select Employee --").appendTo('#employeeId');
      	$(data).each(function(i, employee){
      		$('<option>').val(employee.employeeId).text(employee.fullName).appendTo('#employeeId');
      	});
      	<%--var empId = "${salary.empId}";--%>
      	if(empId !=0) {
    		$('#employeeId').val(empId);
    		
    	}
      },
      failure: function (){
      	alert('Unable to load Employees');
      }
  });
  
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
						<button type="button" id="backBtn" class="btn" onclick="getList('../Payroll/listEmpDeductions')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${empDeductions.employeeId != '0'}" >	Update</c:if><c:if test="${empDeductions.employeeId == '0'}">Add</c:if> Employee Deductions</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action="" name="empDedForm">
					<div class="col-sm-12">
						<%-- <div class="row">
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
							</div>--%>
							<div class="row"> 
								
								<div class="col-sm-4 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${empDeductions.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-4 form-group">
									<label>Section 80C:</label>
									<form:input path="section80C"  id="section80C" placeholder="Enter Section 80C" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empDeductions.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Section 80D:</label>
									<form:input path="section80D"  id="section80D" placeholder="Enter Section 80D" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empDeductions.employeeId != '0'}" > value="1" </c:if>/>
								</div>
							
							</div>
							<div class="row">
							<div class="col-sm-3 form-group">
									<label>Section 80E:</label>
									<form:input path="section80E"  id="section80E" placeholder="Enter Section 80E" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empDeductions.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Home Loan Interest 88EE:</label>
									<form:input path="homeLoanIntrst88EE"  id="homeLoanIntrst88EE" placeholder="Enter Home Loan Interest" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Cess:</label>
									<form:input path="cess"  id="cess" placeholder="Enter Cess" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Section 80DD:</label>
									<form:input path="section80DD"  id="section80DD" placeholder="Enter Section 80DD" class="form-control"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>Self Disable 80U:</label>
									<form:input path="selfDisable80U"  id="selfDisable80U" placeholder="Enter Section 80C" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Loan Principal:</label>
									<form:input path="loanPrincipal"  id="loanPrincipal" placeholder="Enter Loan Principal" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>School Fee:</label>
									<form:input path="schoolFees"  id="schoolFees" placeholder="Enter School Fee" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>LIC:</label>
									<form:input path="lic"  id="lic" placeholder="Enter LIC" class="form-control"/>
								</div>
								</div>
								<div class="row">
								<div class="col-sm-3 form-group">
									<label>Mutual Fund:</label>
									<form:input path="mutualFund"  id="mutualFund" placeholder="Enter Mutual Fund" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>NSC:</label>
									<form:input path="nsc"  id="nsc" placeholder="Enter NSC" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>PPF:</label>
									<form:input path="ppf"  id="ppf" placeholder="Enter PPF" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Donation:</label>
									<form:input path="donation"  id="donation" placeholder="Enter donation" class="form-control"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>Bonus:</label>
									<form:input path="bonus"  id="bonus" placeholder="Enter Bonus" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Arrears:</label>
									<form:input path="arrears"  id="arrears" placeholder="Enter Arrears" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>OT Amount:</label>
									<form:input path="otAmount"  id="otAmount" placeholder="Enter OT Amount" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>OT Wages:</label>
									<form:input path="otWages"  id="otWages" placeholder="Enter OT Wages" class="form-control"/>
								</div>
							</div>
							
							<!-- New emp tax exemption data -->
							
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>HAR Section 10(3A):</label>
									<form:input path="hra_section10_13A"  id="hra_section10_13A" placeholder="Enter Bonus" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Income tax rebate 87C:</label>
									<form:input path="income_tax_rebate_section_87C"  id="income_tax_rebate_section_87C" placeholder="Enter Arrears" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Child Tran Allw 10(14):</label>
									<form:input path="child_trans_allw_10_14"  id="child_trans_allw_10_14" placeholder="Enter OT Amount" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Home load 24B:</label>
									<form:input path="home_loan_section_24B"  id="home_loan_section_24B" placeholder="Enter OT Wages" class="form-control"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>HLP PF LIC 80C:</label>
									<form:input path="hlp_pf_lic_80C"  id="hlp_pf_lic_80C" placeholder="Enter Bonus" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>NSP 80CCD(1B):</label>
									<form:input path="nps_80CCD_1B"  id="nps_80CCD_1B" placeholder="Enter Arrears" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Health Insu 80D:</label>
									<form:input path="health_insu_80D"  id="health_insu_80D" placeholder="Enter OT Amount" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Des Dep 80DD:</label>
									<form:input path="des_dep_80DD"  id="des_dep_80DD" placeholder="Enter OT Wages" class="form-control"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>Medical 80DDB:</label>
									<form:input path="medical_80DDB"  id="medical_80DDB" placeholder="Enter Bonus" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>EDU Load 80D:</label>
									<form:input path="edu_load_80D"  id="edu_load_80D" placeholder="Enter Arrears" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Donation 80G:</label>
									<form:input path="donation_80G"  id="donation_80G" placeholder="Enter OT Amount" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>RENT 80GG:</label>
									<form:input path="rent_80GG"  id="rent_80GG" placeholder="Enter OT Wages" class="form-control"/>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-3 form-group">
									<label>Int Bank 80TTA:</label>
									<form:input path="int_bank_section_80TTA"  id="int_bank_section_80TTA" placeholder="Enter Bonus" class="form-control"/>
								</div>
								<div class="col-sm-3 form-group">
									<label>Phys Dis Per 80U:</label>
									<form:input path="phys_dis_per_section_80U"  id="phys_dis_per_section_80U" placeholder="Enter Arrears" class="form-control"/>
								</div>
							</div>
							
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addQtrBtn" class="btn">Submit</button>
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