<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Employee</title>
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

	var departmentList = ${departments};
	<%--var designationList = ${designations};--%>
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	<%--$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});--%>
	var deptId = "${employee.departmentId}";
	var desgId = "${employee.designationId}";
	var headId = "${employee.headId}";
	if(deptId !=0) {
		getHeadsByDept(deptId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId);
	}
	var genderId = "${employee.gender}";
	var employeeTypeId = "${employee.employeeType}";
	$('#departmentId').val(deptId);
	<%--$('#designationId').val(desgId);--%>
	$('#gender').val(genderId);
	$('#employeeType').val(employeeTypeId);
	
	$('#dob').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#joiningDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#retirementDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	
	$('#addEmpBtn').click(function(event) {
		if($('#employeeId').val() != 0){
			var fname = "${employee.firstName}";
			var lname = "${employee.lastName}";
			var mname = "${employee.middleName}";
			var address1 = "${employee.addressLine1}";
			var address2 = "${employee.addressLine2}";
			var address3 = "${employee.addressLine3}";
			var dob = "${employee.dob}";
			var joiningDate = "${employee.joiningDate}";
			var phone = "${employee.phone}";
			var email = "${employee.email}";
			var adharNo = "${employee.adharNo}";
			var pan = "${employee.pan}";
			var retirementDate = "${employee.retirementDate}";
			var employeeType = "${employee.employeeType}";
			var handicapFlag = "${employee.handicapFlag}";
			
			if(desgId == $('#designationId').val() && deptId == $('#departmentId').val() && 
			fname == $('#fname').val().trim() && lname == $('#lname').val().trim() && mname==$('#mname').val().trim() &&
			dob == $('#dob').val().trim() && joiningDate == $('#joiningDate').val().trim() && 
			adharNo == $('#aadhar').val().trim() && pan == $('#pan').val().trim() && 
			retirementDate == $('#retirementDate').val().trim() && handicapFlag == $('#handicapFlag').val() && employeeType == $('#employeeType').val() && genderId == $('#gender').val().trim()) {
			//address1 == $('#addressLine1').val().trim() && address2 == $('#addressLine2').val().trim() && address3 ==$('#addressLine3').val().trim() &&
			// phone == $('#phone').val().trim() && email == $('#email').val().trim() &&
				alert('Nothing was changed!');
				$('#designationId').focus();
				return false;
			}
		}
		
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
		if($('#fname').val().trim() == ""){
			alert("First Name must be provided!");
			$('#fname').focus();
			return false;
		}
		if($('#lname').val().trim() == ""){
			alert("Last Name must be provided!");
			$('#lname').focus();
			return false;
		}
		
		/*if($('#addressLine1').val().trim() == ""){
			alert("Address1 must be provided!");
			$('#addressLine1').focus();
			return false;
		}*/
		if($('#dob').val().trim() == ""){
			alert("Date of Birth must be provided!");
			$('#dob').focus();
			return false;
		}
		if($('#gender').val().trim() == ""){
			alert("Gender must be selected!");
			$('#gender').focus();
			return false;
		}
		if($('#joiningDate').val().trim() == ""){
			alert("Joining Date must be provided!");
			$('#joiningDate').focus();
			return false;
		}
		if($('#employeeType').val() == 0){
			alert("Employee type must be selected!");
			$('#employeeType').focus();
			return false;
		}
		/*var phoneValue = $('#phone').val().trim();
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
		}*/
		
		<%--if($('#phone').val().trim() == ""){
			alert("Phone# must be provided!");
			$('#phone').focus();
			return false;
		}else{
			if(!allNumeric($('#phone').val().trim())){
				alert("Phone# must be only Numbers!");
				$('#phone').focus();
				return false;
			}			
		}--%>
		/* if($('#email').val().trim() != ""){
			if(!emailValid($('#email').val())){
				alert("You have entered an invalid email address!")  
				$('#email').focus();
			    return false;  
			}		
		} */
		var adharValue = $('#aadhar').val().trim();
		if(adharValue){
			if(adharValue.length!= 12){
				alert("Aadhar# must be 12 characters!")  
				$('#aadhar').focus();
			    return false;
			}
			if(!allNumeric(adharValue)){
				alert("Aadhar# must be only Numbers!")  
				$('#aadhar').focus();
			    return false;
			}
		}
		
		if($('#employeeId').val() == 0){
						var	kcb_Id = $('#kcbId').val().trim();
						if(kcb_Id.length!= 3){
							alert("KCB ID# must be 3 characters!")  
							$('#kcbId').focus();
						    return false;
						}
						if(!allNumeric(kcb_Id)){
							alert("KCB ID# must be only Numbers!")  
							$('#kcbId').focus();
						    return false;
						}
					}
	
		var panValue = $('#pan').val().trim();
		if(panValue){
			if(panValue.length!= 10){
				alert("PAN# must be 10 characters!")  
				$('#pan').focus();
			    return false;
			}
			if(!alphaNumeric(panValue)){
				alert("PAN# must be only Alpha Numeric characters!")  
				$('#pan').focus();
			    return false;
			}
		}
		
		if($('#employeeId').val() != 0){
						var inputJson = { "firstName" : $('#fname').val(), "middleName" : $('#mname').val(), "lastName" : $('#lname').val(),"designationId" : $('#designationId').val(), 
				"departmentId": $('#departmentId').val(), "pan":panValue,				
				"adharNo":$('#aadhar').val(),"dob":$('#dob').val(), "employeeId":$('#employeeId').val(), "gender":$('#gender').val(),
				"joiningDate":$('#joiningDate').val(), "headId":$('#headId').val(), "retirementDate":$('#retirementDate').val(), "handicapFlag":$('#handicapFlag').val(), "employeeType" : $('#employeeType').val()};
						
				}
				else {
					//var combinedId = $('#joiningDate').val().substring(6, 10) + $('#joiningDate').val().substring(3, 5) + $('#kcbID').val();
					var inputJson = { "firstName" : $('#fname').val(), "middleName" : $('#mname').val(), "lastName" : $('#lname').val(),"designationId" : $('#designationId').val(), 
							"departmentId": $('#departmentId').val(), "pan":panValue,				
							"adharNo":$('#aadhar').val(),"dob":$('#dob').val(), "employeeId":$('#kcbId').val(), "gender":$('#gender').val(),
							"joiningDate":$('#joiningDate').val(), "headId":$('#headId').val(), "retirementDate":$('#retirementDate').val(), "handicapFlag":$('#handicapFlag').val(), "employeeType" : $('#employeeType').val()};
				}		
						
		// "addressLine1":$('#addressLine1').val(),"addressLine2":$('#addressLine2').val(),"addressLine3":$('#addressLine3').val(),
		//"email": $('#email').val(), "phone":phoneValue,
	    $.ajax({
	        url: '../Payroll/addEmp',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	<%--window.location = "../Payroll/employee";--%>
	            	var f = document.forms['empForm'];
	            	f.action="../Payroll/employee";
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
</head>
<body >
<div class="contain-wrapp bodyDivCss">
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employee')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${employee.employeeId != '0'}" > Update </c:if><c:if test="${employee.employeeId == '0'}">	Add	</c:if> Employee
			</h4>
			<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
			<div class="row">
			<form:form method = "GET" action = "" name="empForm">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" name="departmentId" class="form-control" onchange="getHeads()" 
								<c:if test="${employee.employeeId != '0'}" > disabled= "disabled" </c:if>>
									<option value="0">-- Select Department --</option></select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" name="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${employee.employeeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" <c:if test="${employee.employeeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Designation --</option></select>
							</div>
							
						</div>	
						
						<div class="row">
								
							<div class="col-sm-4 form-group">
								<label>First Name</label>
								<form:input type="text" id="fname" name="firstName" path="firstName" placeholder="Enter First Name" class="form-control"/>
								<form:input type="hidden" id="employeeId" path="employeeId" placeholder="Enter First Name" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>Middle Name</label>
								<form:input type="text" id="mname" path="middleName" placeholder="Enter Middle Name" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>Last Name</label>
								<form:input type="text" id="lname" path="lastName" placeholder="Enter Last Name" class="form-control"/>
							</div>		
						
						</div>			
						<%-- <div class="row">
							
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
						
						</div>	--%>	
						<%--<div class="form-group">
							<label>Address</label>
							<form:textarea placeholder="Enter Address" id="address" path="address" rows="3" class="form-control"/>
						</div>	 --%>
						<div class="row">
						<div class="col-sm-4 form-group">
								<label>Gender</label>
								
								<select id="gender" class="form-control">
								<option value="">-- Select Gender --</option>
								<option value="MALE">Male</option>
								<option value="FEMALE">Female</option>
								</select>
						</div>
							<div class="col-sm-4 form-group">
								<label>Date of Birth</label>
								<form:input type="text" id="dob" path="dob" placeholder="Enter DOB (DD/MM/YYYY)" class="form-control"/>
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Joining Date</label> 
									<form:input type="text" id="joiningDate" path="joiningDate" placeholder="Enter Joing Date (DD/MM/YYYY)" class="form-control"/>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Retirement Date</label>
								<form:input type="text" id="retirementDate" path="retirementDate" placeholder="Enter Retmt Date (DD/MM/YYYY)" class="form-control"/>
							</div>	
							<%-- <div class="col-sm-4 form-group">
								<label>Phone Number</label>
								<form:input type="text" id="phone" path="phone" placeholder="Enter Phone Number" class="form-control"/>
							</div>
							<div class="col-sm-4 form-group">
								<label>Email</label>
								<form:input type="text" id="email" path="email" placeholder="Enter Email Address" class="form-control"/>
							</div>	--%>
							<div class="col-sm-4 form-group">
								<label>Aadhar Number</label>
								<form:input type="text" id="aadhar" path="adharNo" placeholder="Enter Aadhar Number" class="form-control"/>
							</div>	
							<div class="col-sm-4 form-group">
								<label>PAN</label>
								<form:input type="text" id="pan" path="pan" placeholder="Enter PAN" class="form-control"/>
							</div>	
						</div>
						<div class="row">
								
							<div class="col-sm-4 form-group">
								<label>Employee Type</label>
								<select id="employeeType" class="form-control" <c:if test="${employee.employeeId != '0'}" > disabled= "disabled" </c:if>>
									<option value="0">-- Select Employee Type --</option>
									<option value="1">Permanent</option>
									<option value="2">Contract</option>
									<option value="3">Honorary</option>
								</select>
							</div>	
						
						<div class="col-sm-4 form-group">
								<c:if test='${employee.employeeId eq "0"}'>
									<label>KCB ID</label> 
									<form:input type="text" id="kcbId" path="employeeId" placeholder="Enter KCB ID" class="form-control" />
								</c:if>
								
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Is Handicapped?</label><br />
								<form:select path = "handicapFlag" id="handicapFlag" class="form-control">
								<form:option value="0" label="No"/>
								<form:option value="1" label="Self"/>
								<form:option value="2" label="Dependent"/>
								<form:option value="3" label="Both"/>
								</form:select>  
							</div> 
						</div>		
						
						<div class="row">	
							<div class="text-right">
							<button type="button" id="addEmpBtn" class="btn">Submit</button>
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