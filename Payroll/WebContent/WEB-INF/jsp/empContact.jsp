<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Contact</title>
<%-- <link href="../Payroll/resources/css/jquery.datepick.css" rel="stylesheet">--%>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" /> 
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>
<style type="text/css">
select {
	min-width: 200px;
	min-height: 30px;
}

td, th {
	padding: 3px;
}

.buttonPadding {
	padding: 5px;
}
.btn-color{
	background-color: #0101DF;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#state').val('${empContact.state}');
	$('#secState').val('${empContact.secState}');
	
	$("#resetBtn").click(function() {   
		this.form.reset(); 
		$('#state').val('${empContact.state}');
		$('#secState').val('${empContact.secState}');
        return false;                        
    });
	$('#addEmpContactBtn').click(function(event) {
		var address1 = "${empContact.addressLine1}";
		var address2 = "${empContact.addressLine2}";
		var address3 = "${empContact.addressLine3}";
		var phone = "${empContact.phone}";
		var email = "${empContact.email}";
		var city = "${empContact.city}";
		var state = "${empContact.state}";
		var pin = "${empContact.pin}";
					
		var secAddress1 = "${empContact.secAddressLine1}";
		var secAddress2 = "${empContact.secAddressLine2}";
		var secAddress3 = "${empContact.secAddressLine3}";
		var secPhone = "${empContact.secPhone}";
		var secEmail = "${empContact.secEmail}";
		var secCity = "${empContact.secCity}";
		var secState = "${empContact.secState}";
		var secPin = "${empContact.secPin}";
		
	if(
		address1 == $('#addressLine1').val() && address2 == $('#addressLine2').val() && address3 ==$('#addressLine3').val() && 
		phone == $('#phone').val() && email == $('#email').val() && city == $('#city').val() && state == $('#state').val() &&
		pin == $('#pin').val() &&
		secAddress1 == $('#secAddressLine1').val() && secAddress2 == $('#secAddressLine2').val() && secAddress3 ==$('#secAddressLine3').val() && 
		secPhone == $('#secPhone').val() && secEmail == $('#secEmail').val() && secCity == $('#secCity').val() && secState == $('#secState').val() &&
		secPin == $('#secPin').val()
	) {
			alert('Nothing was changed!');
			$('#addressLine1').focus();
			return false;
		}
	
	
		var phoneValue = $('#phone').val();
		if(phoneValue){
			if(phoneValue.length!= 10){
				alert("Phone# must be 10 characters!")  
				$('#phone').focus();
			    return false;
			}
			if(!allNumeric(phoneValue)){
				alert("Phone# must be only Numbers!");
				$('#phone').focus();
				return false;
			}	
		}else {
			alert("Phone# must be provided!");
			$('#phone').focus();
			return false;
		}
		
		if($('#email').val() != ""){
			if(!emailValid($('#email').val())){
				alert("You have entered an invalid email address!")  
				$('#email').focus();
			    return false;  
			}		
		}
		
		if($('#addressLine1').val() == ""){
			alert("Address1 must be provided!");
			$('#addressLine1').focus();
			return false;
		}
		
		if($('#city').val() == ""){
			alert("City must be provided!");
			$('#city').focus();
			return false;
		}
		
		if($('#state').val() == ""){
			alert("State must be provided!");
			$('#state').focus();
			return false;
		}
		
		var pinValue = $('#pin').val();
		if(pinValue){
			if(pinValue.length!= 6){
				alert("PIN code must be 6 characters!")  
				$('#pin').focus();
			    return false;
			}
			if(!allNumeric(pinValue)){
				alert("PIN code must be only Numbers!");
				$('#pin').focus();
				return false;
			}	
		}else {
			alert("PIN code must be provided!");
			$('#pin').focus();
			return false;
		}
		
		var phone2Value = $('#secPhone').val();
		if(phone2Value){
			if(phone2Value.length!= 10){
				alert("Phone# must be 10 characters!")  
				$('#secPhone').focus();
			    return false;
			}
			if(!allNumeric(phone2Value)){
				alert("Phone# must be only Numbers!");
				$('#secPhone').focus();
				return false;
			}	
		}
		
		if($('#secEmail').val() != ""){
			if(!emailValid($('#secEmail').val())){
				alert("You have entered an invalid email address!")  
				$('#secEmail').focus();
			    return false;  
			}		
		}
		
		var secPinValue = $('#secPin').val();
		if(secPinValue){
			if(secPinValue.length!= 6){
				alert("PIN code must be 6 characters!")  
				$('#secPin').focus();
			    return false;
			}
			if(!allNumeric(secPinValue)){
				alert("PIN code must be only Numbers!");
				$('#secPin').focus();
				return false;
			}	
		}
		
		
		if($('#secAddressLine1').val()!="" || $('#secCity').val()!="" || $('#secState').val()!="" || $('#secPin').val()!="") {
			if($('#secAddressLine1').val() == ""){
				alert("Address1 must be provided!");
				$('#secAddressLine1').focus();
				return false;
			}
			
			if($('#secCity').val() == ""){
				alert("City must be provided!");
				$('#secCity').focus();
				return false;
			}
			
			if($('#secState').val() == ""){
				alert("State must be provided!");
				$('#secState').focus();
				return false;
			}
			
			if($('#secPin').val() == ""){
				alert("PIN code must be provided!");
				$('#secPin').focus();
				return false;
			}
		}
		$("#empForm").attr("action", "../Payroll/addUpdateEmpContact");
		$("#empForm").submit();
	});
	
	$('#empListBtn').click(function(event) {
		$("#empForm").attr("action", "../Payroll/empContactListBack");
		$("#empForm").submit();
	});
	
	
	
});

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
</script>
</head>
<body >
<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/empContactList')">Back</button>
					</div>
				</div>
			</div>	
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				Employee Contact Details
			</h4>
			<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
			<div class="row">
			<form:form method = "POST" action = "" name="empForm" id="empForm">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Name</label>
								<form:input type="hidden" path="employeeId"/>
								<form:input type="hidden" path="empContactId"/>
								<input type="hidden" name="departmentId" value="0"/>
								<input type="hidden" name="headId" value="0"/>
								<div>
								${empContact.employee.fullName}
								</div>
							</div>
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<div>
								${empContact.employee.department}
								</div>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head</label>
								<div>
								${empContact.employee.headName}
								</div>
							</div>
						</div>	
						
						<div class="row">
						<div class="col-sm-12 form-group">
								<label style="color:blue;">	Primary Contact </label>
						</div></div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Phone Number</label>
								<form:input type="text" id="phone" path="phone" placeholder="Enter Phone Number" class="form-control"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Email</label>
								<form:input type="text" id="email" path="email" placeholder="Enter Email Address" class="form-control"/>
							</div>		
							<div class="col-sm-4 form-group">
							</div>
						</div>			
						<div class="row">
							
							<div class="col-sm-4 form-group">
								<label>Address1</label>
								<form:input type="text" id="addressLine1" path="addressLine1" placeholder="Enter Addrees1" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>Address2</label>
								<form:input type="text" id="addressLine2" path="addressLine2" placeholder="Enter Addrees2" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>Address3</label>
								<form:input type="text" id="addressLine3" path="addressLine3" placeholder="Enter Addrees3" class="form-control"/>
							</div>		
						
						</div>
						
						<div class="row">
							
							<div class="col-sm-4 form-group">
								<label>City</label>
								<form:input type="text" id="city" path="city" placeholder="Enter City" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>State</label>
								<select id="state" name="state" class="form-control"> 
									<option value="">-- Select State --</option>
									<c:forEach var="state" items="${sessionScope.states}">
									<option value="${state.key}">${state.value}</option>
									</c:forEach>
								</select>
							</div>	
							<div class="col-sm-4 form-group">
								<label>PIN</label>
								<form:input type="text" id="pin" path="pin" placeholder="Enter PIN" class="form-control"/>
							</div>		
						
						</div>
						
						<div class="row form-group">
						<div class="col-sm-12 ">
								<label style="color:blue;">	Secondary Contact </label>
						</div></div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Phone Number</label>
								<form:input type="text" id="secPhone" path="secPhone" placeholder="Enter Phone Number" class="form-control"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Email</label>
								<form:input type="text" id="secEmail" path="secEmail" placeholder="Enter Email Address" class="form-control"/>
							</div>		
							<div class="col-sm-4 form-group">
							</div>
						</div>			
						<div class="row">
							
							<div class="col-sm-4 form-group">
								<label>Address1</label>
								<form:input type="text" id="secAddressLine1" path="secAddressLine1" placeholder="Enter Addrees1" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>Address2</label>
								<form:input type="text" id="secAddressLine2" path="secAddressLine2" placeholder="Enter Addrees2" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>Address3</label>
								<form:input type="text" id="secAddressLine3" path="secAddressLine3" placeholder="Enter Addrees3" class="form-control"/>
							</div>		
						
						</div>
						
						<div class="row">
							
							<div class="col-sm-4 form-group">
								<label>City</label>
								<form:input type="text" id="secCity" path="secCity" placeholder="Enter City" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>State</label>
								<select id="secState" name="secState" class="form-control"> 
									<option value="">-- Select State --</option>
									<c:forEach var="state" items="${sessionScope.states}">
									<option value="${state.key}">${state.value}</option>
									</c:forEach>
								</select>
							</div>	
							<div class="col-sm-4 form-group">
								<label>PIN</label>
								<form:input type="text" id="secPin" path="secPin" placeholder="Enter PIN" class="form-control"/>
							</div>		
						
						</div>
						
						<div class="row">	
							<div class="text-right">
							<button type="button" id="addEmpContactBtn" class="btn">Submit</button>
							<button type="reset" class="btn" id="resetBtn">Reset</button>	
							<button type="button" id="empListBtn"  class="btn">Employee List</button>
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