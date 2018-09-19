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
	color: #fff;
}
.rptTblClass tr > th:last-of-type {
	background-color: #fff;
    text-align: right;
}
.rptTblClass tr > td:last-of-type {
    text-align: right;
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
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#listDeptId');
	});
	
	var deptId = '${user.listDeptId}';
	$('#listDeptId').val(deptId == ''? 0 : deptId);
	
	var rolesList = ${sessionScope.roles};
	$.each(rolesList, function( index, value ) {
		$('<option>').val(value.roleId).text(value.roleName).appendTo('#listRoleId');
	});
	
	var roleId = '${user.listRoleId}';
	$('#listRoleId').val(roleId == ''? 0 : roleId);
	
	$('#searchBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/usersListFilter");
		$("#formSearch").submit();
	});
	
	$('#addBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/addUser");
		$("#formSearch").submit();
	});
	
});

function deleteUser(id){
	if(confirm("Are you sure want to delete User?")){
    	var f = document.forms['editForm'];
    	f.userId.value=id;
    	f.action="../Payroll/deleteUser";
    	f.submit();
    }
}

function inputPage() {
	$("#formSearch").attr("action", "../Payroll/addUser");
	$("#formSearch").submit();
}

function editUser(id){
	var f = document.forms['editForm'];
	f.userId.value=id;
	f.action="../Payroll/editUser";
	f.submit();
}

<c:if test="${not empty message}">
alert("${message}");
</c:if>
      </script>
</head>

<body >
	<div class="contain-wrapp bodyDivCss">	
	<div class="container">
		<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/mastersMenu')">Back</button>
					</div>
				</div>
			</div>
			</div>
	<form:form id="formSearch" method = "POST" action = "" >
	<div class="container panel panel-primary">
      <div class="panel-heading" style="margin:0px;padding:0px;background-color: #8B9DC3;">
      <h5 style="color: #fff; padding:5px;margin:0px; background-color: #8B9DC3; text-transform: none;">
      User Information </h5></div>
<div  class="panel-body" style="padding:10px;margin:0px;border: 1px solid #8B9DC3;">
	<div class="col-sm-4">
		<label>Department </label> 
		<select id="listDeptId" class="form-control" name="listDeptId" >
		<option value="0">-- Select Department --</option></select>
	</div>
	<div class="col-sm-4">
		<label>Role </label> 
		<select id="listRoleId" class="form-control" name="listRoleId" >
		<option value="0">-- Select Role --</option></select>
	</div>
		
	<div class="col-sm-4">
		<div class="formDiv" style="width:100%;margin-top:0px;border: 0px;">
		<br>
		<button type="button" id="searchBtn"  class="btn" style="float:left;">Search</button>
		<button type="button" id="addBtn"  class="btn" style="float:right;">Add User</button>	
		</div></div>
	</div></div>
	</form:form> 
	
	<div  class="container" style ="position: relative;">
	<div id="userListDiv" class="rptTblClass" >
		<table id="userListTable" class="table table-striped table-bordered table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>User Name</th>
			<th>Roles</th>
			<th>Depts Access</th>
			<th>Department</th>
			<th>Head</th>
			<th>Designation</th>
			<th><a href="#" onclick="inputPage()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/>
					</a></th>
			</tr></thead>
		<c:forEach var="user" items="${users}">
			<tr>
			<td> ${user.employee.fullName} </td>
			<td> ${user.userName}</td>
			<td> ${user.roleNames} </td>
			<td> ${user.deptNames} </td>
			<td> ${user.employee.department}</td>
			<td> ${user.employee.headName}</td>
			<td> ${user.employee.designation}</td>
			<td><a href="#" onclick="editUser('${user.userId}')" title="Edit">
					<img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a>
				<a href="#" onclick="deleteUser('${user.userId}')">
					<img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>
			</td>
			</tr>
			</c:forEach>	
		</table>
		</div>
		</div>
	</div>			
	
	<form action="" name="editForm" method="post">
		<input type="hidden" name="userId" value="0">
		<input type="hidden" name="listDeptId" value="${user.listDeptId}" />
		<input type="hidden" name="listRoleId" value="${user.listRoleId}"/>
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>