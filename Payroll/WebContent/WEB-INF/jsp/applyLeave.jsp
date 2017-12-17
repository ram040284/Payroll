<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Apply Leave</title>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	<%--$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});--%>
	var deptId = "${leave.departmentId}";
	var desgId = "${leave.designationId}";
	var headId = "${leave.headId}";
	if(deptId !=0) {
		getHeadsByDept(deptId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId);
	}
	var empId = "${leave.empId}";
	$('#departmentId').val(deptId);
	if(empId != 0){
		getEmployeesByIds(deptId, desgId);
	}
	
	$('#applyBtn').click(function(event) {
			
			
		if($('#leaveType').val() == 0){
			alert("Leave Type must be selected!");
			$('#leaveType').focus();
			return false;
		}
		if($('#noOfLeaves').val() < 1){
			alert("No. Of Leaves must be provided!");
			$('#noOfLeaves').focus();
			return false;
		}
		<%--if($('#leaveBalance').val() == ''){
			alert("Leave Balance must be provided!");
			$('#leaveBalance').focus();
			return false;
		}--%>
		var empIdInput = 0;
		if(empId !=0)
			empIdInput = empId;
		else
			empIdInput = $('#employeeId').val();
		
		var inputJson = { "empId" : empIdInput, "leaveType" : $('#leaveType').val(),  
				"noOfLeaves" : $('#noOfLeaves').val(), "leaveId" : $('#leaveId').val()};
		$.ajax({
	        url: '../Payroll/addLeave',
	        data: JSON.stringify(inputJson),
	        type: "POST",
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewLeave";
	            }else {
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
      	var empId = "${leave.empId}";
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
	if($('#designationId').val() == 0 || $('#designationId').val() == 0){
		alert("Department and Designation must be selected to get employees!");
		$('#departmentId').focus();
		return false;
	}
	var deptId = $('#departmentId').val();
	var desgId = $('#designationId').val();
	getEmployeesByIds(deptId, desgId);
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
	    	var headId = "${leave.headId}";
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
	    	var designationId = "${leave.designationId}";
	    	if(designationId !=0) {
	  			$('#designationId').val(designationId);
	  		}
	    },
	    failure: function (){
	    	alert('Unable to load Heads');
	    }
	});
}

</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h6 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				Search Employee
			</h6>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-8 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-8 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${leave.empId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							<div class="col-sm-8 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							<div class="col-sm-8 form-group">
								<label>Employee:</label>
									<select id="employeeId" class="form-control" <c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
							</div>
							<div class="row">
							
							</div>
							<div class="row">
									<div class="col-sm-6 form-group">
									<label>Leave Type:</label>
									<select id="leaveType" class="form-control" <c:if test="${leave.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="">-- Select Employee --</option>
										<option value="Sick Leave">Sick Leave</option>
										<option value="Casual Leave">Casual Leave</option>
										<option value="Paid Vacation">Paid Vacation</option>
									</select>
								</div>
							
								<div class="col-sm-6 form-group">
									<label>No.of Leaves:</label>
									<form:input path="noOfLeaves"  id="noOfLeaves" placeholder="Enter No. Of Leaves" class="form-control"/>
									
								</div>
								<%--<div class="col-sm-6 form-group">
									<label>Leave Balance:</label>
									<form:input path="leaveBalance"  id="leaveBalance" placeholder="Enter Grade Pay" class="form-control"/>
								</div> --%>
							</div>
							
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addLeaveBtn" class="btn">Submit</button>
									<button type="reset" class="btn">Reset</button>	
								</div>	
							</div>
					</div>
					<input type="hidden" name = "leaveId" id="leaveId" />				
				</form:form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>