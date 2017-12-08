<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Employee</title>

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
	var designationList = ${designations};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentBox');
	});
	$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationBox');
	});
	var deptId = "${employee.departmentId}";
	var desgId = "${employee.designationId}";
	$('#departmentBox').val(deptId);
	$('#designationBox').val(desgId);
	
	$('#dob').datepick({dateFormat: 'dd-mm-yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	
	$('#addEmpBtn').click(function(event) {       
		var inputJson = { "firstName" : $('#fname').val(), "middleName" : $('#mname').val(), "lastName" : $('#lname').val(),"designationId" : $('#designationBox').val(), 
				"departmentId": $('#departmentBox').val(), "email": $('#email').val(), "phone":$('#phone').val(), "pan":$('#pan').val(),
				"address":$('#address').val(),"adharNo":$('#aadhar').val(),"dob":$('#dob').val(), "employeeId":$('#employeeId').val()};
	    $.ajax({
	        url: '../Payroll/addEmp',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/employee";
	            }
	        }
	    });
	    event.preventDefault();
	});
});
function showDate(date) {
	alert('The date chosen is ' + date);
}
     
      </script>
</head>
<body ><%--style="background-color:#A4A4A4"> --%>
	<%--<jsp:include page="../jsp/public/postHeader.jsp"/> --%>
	<div class="wrapper">
	<div class="container" style="margin-top: 10px; width: 60%; float: left;">
	<h4 class="card card-block bg-faded" style="color: #0101DF;">
	<c:if test="${employee.employeeId != '0'}" >
			Update
			</c:if><c:if test="${employee.employeeId == '0'}">
				Add
			</c:if>
			
	Employee</h4>
	<div class="col-lg-12 card card-block bg-faded">
	<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							
							<div class="col-sm-4 form-group">
								<label>First Name</label>
								<form:input type="text" id="fname" path="firstName" placeholder="Enter First Name" class="form-control"/>
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
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Designation:</label>
								<select id="designationBox" class="form-control"><option value="0">-- Select Designation --</option></select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentBox" class="form-control"><option value="0">-- Select Department --</option></select>
							</div>
						</div>		
						<div class="form-group">
							<label>Address</label>
							<form:textarea placeholder="Enter Address" id="address" path="address" rows="3" class="form-control"/>
						</div>	
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Phone Number</label>
								<form:input type="text" id="phone" path="phone" placeholder="Enter Phone Number" class="form-control"/>
							</div>
							<div class="col-sm-6 form-group">
								<label>Email</label>
								<form:input type="text" id="email" path="email" placeholder="Enter Email Address" class="form-control"/>
							</div>	
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Date of Birth</label>
								<form:input type="text" id="dob" path="dob" placeholder="Enter DOB (DD/MM/YYYY)" class="form-control"/>
							</div>	
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
							<div class="text-right">
							<button type="button" id="addEmpBtn" class="btn btn-lg btn-info">Submit</button>
							<button type="reset" class="btn btn-lg btn-info">Reset</button>	
							</div>	
						</div>			
					</div>
				</form:form> 
				</div>
	</div>
	</div>
	</div>
	<%-- <div class="panel panel-primary" style="margin:20px;">
	<div class="panel-heading">
        	<h3 class="panel-title">Registration Form</h3>
	</div>
	</div>
	<div class="container" style="float: left; margin: 5px;">
		<div class="row">
			<h2>Add Employee</h2>

		</div>

		<div id="empInput" class="row">
			<table>
				<tr>
					<th>*First Name:</th>
					<td><input name="fname" id="fname" /></td>
				</tr>
				<tr>
					<th>*Last Name:</th>
					<td><input name="lname" id="lname" /></td>
				</tr>
				<tr>
					<th>*Designation:</th>
					<td><select id="designationBox"><option>--Select--</option>
					</select></td>
				</tr>
				<tr>
					<th>*Department:</th>
					<td><select id="departmentBox"><option>--Select--</option>
					</select></td>
				</tr>
				<tr>
					<th>*Phone:</th>
					<td><input name="phone" id="phone" /></td>
				</tr>
				<tr>
					<th>*Email:</th>
					<td><input name="email" id="email" /></td>
				</tr>
				<tr>
					<th>*Address:</th>
					<td><textarea name="address" id="address" cols="22" rows="3"></textarea></td>
				</tr>
				<tr>
					<th>*Date of Birth:</th>
					<td><input name="dob" id="dob" /></td>
				</tr>
				<tr>
					<th>PAN:</th>
					<td><input name="pan" id="pan" /></td>
				</tr>
				<tr>
					<th>Aadhar:</th>
					<td><input name="aadhar" id="aadhar" /></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<button type="reset" class="buttonPadding">Reset</button> <input
						type="submit" id="addEmpBtn" name="Add" value="Add"
						class="buttonPadding" />

					</td>
				</tr>

			</table>
		</div>

	</div>--%>
</body>
</html>