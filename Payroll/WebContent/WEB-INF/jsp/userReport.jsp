<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>User Information</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>

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

.rptTblClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.rptTblClass th, td {
	text-align: left;
	padding: 5px;
}

.rptTblClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.rptTblClass th {
	background-color: #8B9DC3;
	color: white;
}

table.dataTable thead .sorting_asc { 
background: url('../Payroll/resources/images/uparrow.png') no-repeat right bottom 8px; 
background-size: 25px; 
background-color: #8B9DC3;
color: white;
}
table.dataTable thead .sorting_desc { 
background: url('../Payroll/resources/images/downarrow.png') no-repeat right bottom 8px; 
background-size: 25px; 
background-color: #8B9DC3;
color: white;
}

table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#userListTable').DataTable({
		"info" : false,
		"sort":$('#userListTable tbody tr').length>1,
		"paging" : $('#userListTable tbody tr').length>10,
		"displayLength": 10,
	  	"filter" : false,
	  	"lengthChange" : false,
	  	"autoWidth" : false,
	  	"pagingType" : "full_numbers",
	  	"scrollX": true,
	  	"scrollY": "300px",
	  	"scrollCollapse": true,
	  	"language": {
	        "emptyTable": "No matching records found"
	    }
 	});
	
	var departmentList = ${sessionScope.departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#deptId');
	});
	
	var deptId = '${user.deptId}';
	$('#deptId').val(deptId == ''? 0 : deptId);
	
	$('#searchBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/usersListFilter");
		$("#formSearch").submit();
	});
	
	$('#addBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/addUser");
		$("#formSearch").submit();
	});
	
});
      </script>
</head>

<body >
	<div class="contain-wrapp bodyDivCss">	
	<form:form id="formSearch" method = "POST" action = "" >
	<div id="searchDiv" class="container" style ="position: relative;">
	<div class="col-sm-4">
		<label>Department </label> 
		<select id="deptId" class="form-control" name="deptId" >
		<option value="0">-- Select Department --</option></select>
	</div>
	<div class="col-sm-4">
	<br>
		<div class="formDiv" style="width:100%;margin-top:0px;border: 0px;">
		<button type="button" id="searchBtn"  class="btn" >Search</button></div>
	</div>
	<div class="col-sm-4">
		<br>
		<div class="formDiv" style="width:100%;margin-top:0px;border: 0px;text-align: right">
		<button type="button" id="addBtn"  class="btn">Add User</button>	
		</div>
	</div></div>
	</form:form> 
	
	<div  class="container" style ="position: relative;">
	<div id="userListDiv" class="rptTblClass" >
		<table id="userListTable" class="table table-striped table-bordered table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>User ID</th>
			<th>Role</th>
			<th>Department</th>
			<th>Head</th>
			<th>Designation</th>
			<th>Phone</th>
			<th>Email</th>
			</tr></thead>
		<c:forEach var="user" items="${users}">
			<tr>
			<td> ${user.employee.fullName} </td>
			<td> ${user.userId}</td>
			<td> ${user.role.roleName} </td>
			<td> ${user.employee.department}</td>
			<td> ${user.employee.headName}</td>
			<td> ${user.employee.designation}</td>
			<td> ${user.employee.phone}</td>
			<td> ${user.employee.email}</td>
			</tr>
			</c:forEach>	
		</table>
		</div>
		</div>
	</div>			
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>