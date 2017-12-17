<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Emp Quarters</title>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	var designationList = ${designations};
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});
	var deptId = "${empQtr.departmentId}";
	var desgId = "${empQtr.designationId}";
	$('#departmentId').val(deptId);
	$('#designationId').val(desgId);
	var afkQtr = ""; 
	var afkQtrValue = "${empQtr.afkQtr}";
	if(afkQtrValue == "Yes")
		afkQtr = "true";
	if(afkQtrValue == "No")
		afkQtr = "false";

	$('#afkQtr').val(afkQtr);
	
	var empId = "${empQtr.empId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId);
	}
	
	$('#addQtrBtn').click(function(event) {
		if(empId !=0 && afkQtr == $('#afkQtr').val()){
			alert('Nothing was changed');
			$('#afkQtr').focus();
			return false;
		}
		if($('#departmentId').val() == 0){
			alert("Department must be selected!");
			$('#departmentId').focus();
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
		
		if($('#afkQtr').val() == ''){
			alert("Quarters Alloted must be provided!");
			$('#afkQtr').focus();
			return false;
		}
		var inputJson = { "empId" : $('#employeeId').val(), "afkQtr" : $('#afkQtr').val(), "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addEmpQtr',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewEmpQtr";
	            }else{
	            	alert(data);
	            }
	        }
	    });
	    event.preventDefault();
	});
});

function getEmployeesByIds(deptId, desgId){
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
      	var empId = "${empQtr.empId}";
      	if(empId !=0) {
    		$('#employeeId').val(empId);
    		
    	}
      },
      failure: function (){
      	alert('Unable to load Employees');
      }
  });
}

function getEmployees() {
	if($('#departmentId').val() == 0 || $('#designationId').val() == 0){
		alert("Department and Designation must be selected to get Employees!");
		$('#departmentId').focus();
		return false;
	}
	var deptId = $('#departmentId').val();
	var desgId = $('#designationId').val();
	getEmployeesByIds(deptId, desgId);
}

     
</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${empQtr.empId != '0'}" >	Update</c:if><c:if test="${empQtr.empId == '0'}">Add</c:if> Employee Quarters</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getEmployees()"
								<c:if test="${empQtr.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							
							<div class="col-sm-6 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${empQtr.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${empQtr.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-4 form-group">
									<label>Quarters Alloted:</label>
									<select id="afkQtr" class="form-control">
										<option value="">-- Allotment --</option>
										<option value="true">Yes</option>
										<option value="false">No</option>
									</select>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empQtr.empId != '0'}" > value="1" </c:if>/>
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