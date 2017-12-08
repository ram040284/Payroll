<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
select {
	min-width: 200px;
	min-height: 30px;
}

.buttonPadding {
	padding: 5px;
}
.btn-color{
	background-color: #0101DF;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#collapse").hide();
	$("#expand").show();
	var departmentList = ${sessionScope.departments};
	<%--var designationList = ${sessionScope.designations};--%>
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentBox');
	});
	<%--$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationBox');
	});--%>
	
	var deptId = "${employee.departmentId}";
	$('#departmentBox').val(deptId);
	<%--var desgId = "${employee.designationId}";--%>
	$('#designationBox').val(desgId);
	
	$('#closeBtn').click(function(event) {
		   $("#searchDiv").toggle();
		   $("#collapse").toggle();
		   $("#expand").toggle();
	});
	

});
</script>

<div style="width:100%;"> 
<h6 style="color: #0101DF;margin-bottom:0px;"><a id="closeBtn" href="#" style="color: blue;"><label id="collapse" style="color: blue;" class="glyphicon-plus"></label> <label id="expand" class="glyphicon-minus" style="color: blue;"></label> Show / Hide Search</a></h6>
</div>  
<div id="searchDiv" style="width:100%;margin-top:0px;" class="formDiv">
	<form:form method = "POST" action = "../Payroll/employeeReport" >
	<div  class="col-sm-12" style="margin-top:0px; margin-bottom:10px; padding-top:5px; padding-bottom:10px;float: left;">
	<div class="row">
	<div class="col-sm-3">
		<label>Department </label> 
		<select id="departmentBox" class="form-control" name="departmentId" onchange="getHeads()">
		<option value="0">-- Select Department --</option></select>
	</div>
	<div class="col-sm-3">
		<label>Head </label> 
		<select id="headId" class="form-control" name="headId"><option value="0">-- Select Head --</option></select>
	</div>
	<div class="col-sm-3">
		<label>Name</label>
		<form:input type="text" id="fname" path="firstName" placeholder="Enter First Name" class="form-control"/>
		<%-- <label>Designation:</label>
		<select id="designationBox" class="form-control" name="designationId"><option value="0">-- Select Designation --</option></select> --%>
	</div>
	<div class="col-sm-3">
		
	</div><div class="col-sm-3">
		<br>
		<button type="submit" id="searchBtn"  class="btn" >Search</button>
		<button type="reset"  class="btn">Reset</button>	
	</div>
	</div>
	</div>
	</form:form> 
</div>
	