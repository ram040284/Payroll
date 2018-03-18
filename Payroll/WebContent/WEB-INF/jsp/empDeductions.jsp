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
			if(section80C == section80CVal && cess == cessVal && homeLoanIntrst88EE == homeLoanIntrst88EEVal &&  
					selfDisable80U == selfDisable80UVal && loanPrincipal == loanPrincipalVal && schoolFees == schoolFeesVal &&
					lic == licVal && mutualFund == mutualFundVal && section80D == section80DVal && section80E == section80EVal && 
					nsc == nscVal && ppf == ppfVal && donation == donationVal && section80DD == section80DDVal && 
					bonus == bonusVal && arrears == arrearsVal && otAmount == otAmountVal && otWages == otWagesVal){
				alert('Nothing was changed');
				$('#section80C').focus();
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
		
		var inputJson = { "employeeId" : $('#employeeId').val(), "section80C" : section80CVal, "addUpdate": $('#addUpdate').val(), 
				"cess" : cessVal, "homeLoanIntrst88EE" : homeLoanIntrst88EEVal, "selfDisable80U" : selfDisable80UVal, 
				"loanPrincipal" : loanPrincipalVal, "schoolFees" : schoolFeesVal, "lic" : licVal, "mutualFund" : mutualFundVal,
				"section80D" : section80DVal, "section80E" : section80EVal, "nsc" : nscVal, "ppf" : ppfVal, 
				"donation" : donationVal, "section80DD" : section80DDVal, "bonus" : bonusVal, "arrears" : arrearsVal, 
				"otAmount" : otAmountVal, "otWages" : otWagesVal};
		$.ajax({
	        url: '../Payroll/addEmpDeductions',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewEmpDeductions";
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

     
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${empDeductions.employeeId != '0'}" >	Update</c:if><c:if test="${empDeductions.employeeId == '0'}">Add</c:if> Employee Deductions</h4>

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