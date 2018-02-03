<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<title>Paycheck</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	$('#monthDate').datepick({dateFormat: 'dd/mm/yyyy'});
});

<%--
function getHeads(){
	if($('#departmentId').val() == 0){
		alert("Department must be selected to get Employees!");
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
	    	$(data).each(function(i, headInfo){
	    		$('<option>').val(headInfo.headId).text(headInfo.headName).appendTo('#headId');
	    	});
	    	var headId = "${designation.headId}";
	    	if(headId !=0) {
	  		$('#headId').val(headId);
	  		
	  	}
	    },
	    failure: function (){
	    	alert('Unable to load Heads');
	    }
	});
}--%>
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
<div class="contain-wrapp bodyDivCss">	
<div class="container">
<%--<h5 style="color: #0101DF;">Paybill Report</h5> --%>
<div style="margin-top:0px;">
	<form method = "POST" action = "../Payroll/employee" name="paybillForm">
	<%--<div  class="col-sm-12" style="margin-top:0px; margin-bottom:10px; padding-top:5px; padding-bottom:10px;float: left;"> --%>
	<div class="panel panel-primary" style="width: 40%; margin-left: 25%;">
    	<div class="panel-heading" style="margin:0px;padding:10px;background-color: #8B9DC3; font-size: 1.2em;"><b>Paybill Report</b></div>
		<div  class="panel-body formDiv" style="padding:5px;margin:0px; width: 100%;">
		<div style="margin-left: 10px; margin-right: 10px;">
	<div class="row">
		<div class="col-sm-6 form-group">
			<label>Department </label> 
			<select id="departmentId" class="form-control" name="departmentId" onchange="getHeads()"> 
			<option value="0">-- Select Department --</option></select>
		</div>
	<%--</div>
	<div class="row"> 
		<div class="col-sm-6 form-group">
			<label>Date:</label>
			<input type="text" id="monthDate" name="monthDate" placeholder="Select 1st Date of Month" class="form-control"/>
		</div>--%>
		<div class="col-sm-6 form-group">
			<label>Head:</label>
			<select id="headId" class="form-control" onchange="loadDesignations()">
			<option value="0">-- Select Head --</option></select>
		</div>
	</div>			
	<div class="row">
	<%--	<div class="col-sm-6 form-group">
			<label>Head:</label>
			<select id="headId" class="form-control">
			<option value="0">-- Select Head --</option></select>
		</div>
	 </div>
	<div class="row">--%>
	<div class="col-sm-6 form-group">
		<label>Designation:</label>
		<select id="designationId" class="form-control" onchange="getEmployees()">
			<option value="0">-- Select Designation --</option>
		</select>
	</div>
	
	<div class="col-sm-6 form-group">
			<label>Employee:</label>
			<select id="employeeId" class="form-control">
				<option value="0">-- Select Employee --</option>
			</select>
		</div>
		
	</div>
	<div class="row">
		<div class="col-sm-10 form-group">
			<label>Date:</label>
			<input type="text" id="monthDate" name="monthDate" placeholder="Select 1st Date of Month" class="form-control"/>
		</div>
	</div>
	<div class="row">
		<div class="col-sm" style="float: right; margin-right: 10px; margin-bottom: 5px;">
			<button type="button" id="searchBtn"  class="btn" onclick="generatePayCheck()">Get Report</button>
			
		</div>
	</div>
	</div>
	</div>
	</div>
	
	</form> 
</div></div></div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>