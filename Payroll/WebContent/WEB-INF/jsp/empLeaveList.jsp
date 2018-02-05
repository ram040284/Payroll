<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Details</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>

<style type="text/css">
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
	background-color: #f2f2f2;
}

.rptTblClass th {
	background-color: #8B9DC3;
	color: white;
}
</style>
<script type="text/javascript">

$(document).ready(function() {
	
	$("#addBtn").hide();
	$("#collapse").hide();
	$("#expand").show();
	var departmentList = ${sessionScope.departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${employee.departmentId}";
	$('#departmentId').val(deptId);
	var headId = "${employee.headId}";
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	var firstName = "${employee.firstName}";
	$('#firstName').val(firstName);
	$('#closeBtn').click(function(event) {
		   $("#searchDiv").toggle();
		   $("#collapse").toggle();
		   $("#expand").toggle();
	});
	
	$('#empLeaveTable').DataTable({
		"info" : false,
		"sort":$('#empLeaveTable tbody tr').length>1,
		"paging" : $('#empLeaveTable tbody tr').length>10,
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
});

function viewLeave(id){
	var f = document.forms['editForm'];
	f.employeeId.value=id;
	f.departmentId.value = $('#departmentId').val();
	f.headId.value = $('#headId').val();
	f.firstName.value = $('#firstName').val();
	/*f.listDeptId.value = $('#departmentId').val();
	f.listHeadId.value = $('#headId').val();
	f.listName.value = $('#firstName').val();*/
	f.action="../Payroll/viewLeave";
	f.submit();
}
function searchEmps(){
	if($('#departmentId').val() == 0 && $('#firstName').val().trim() == ""){
		alert('Either Department or Name must be provided to get List!');
		$('#departmentId').focus();
		return false;
	}
	var f = document.forms['empSearch'];
	f.action="../Payroll/empLeaveSearch";
	f.submit();
}

function inputPage(){
	
}

<c:if test="${not empty message}">
alert("${message}");
</c:if>
</script>
</head>
<body><%-- onload="getList()">--%>
	
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<h5 style="color: #0101DF;">Employee Leave Request Details</h5>
		<jsp:include page="../jsp/public/searchCriteria.jsp" />
		<c:if test="${sessionScope.empLeaveList.size() gt 0}">
		<div style="margin-top: 6px; float: left; width: 100%;">
			
			<div id="empListDiv" class="rptTblClass" style="width: 100%;">
			<table id="empLeaveTable" class="table table-striped table-bordered table-hover table-responsive">
			<thead>
				<tr>
					<th>Employee</th>
					<th>Medical </th>
					<th>Casual</th>
					<th>Half Paid</th>
					<th>Earned </th>
					<th>Maternity</th>
					<th>Paternity</th>
					<th>Extraordinary</th>
					<th>Apply Leave</th>
				</tr></thead>
				<c:forEach var="leave" items="${sessionScope.empLeaveList}">
				<tr>
					<td> ${leave.fullName} </td>
					<td> ${leave.sickLeaves}</td>
					<td> ${leave.casualLeaves}</td>
					<td> ${leave.paidLeaves}</td>
					<td> ${leave.earnLeave} </td>
					<td> ${leave.maternityLeave}</td>
					<td> ${leave.paternityLeave}</td>
					<td> ${leave.extraLeave}</td>
					<td><a href="#" onclick="viewLeave('${leave.employeeId}')" >
							<img src="../Payroll/resources/images/applyleave.png" alt="Apply Leave" class="listImg"/>
						</a>
					</td>
				</tr>
				</c:forEach>
				</table>
		</div>
	</div>
	</c:if>
	</div>
	</div>
	<form  action="" id="editForm" method="post">
		<input type="hidden" name="employeeId" value="0">
		<input type="hidden" name="departmentId" value="${employee.departmentId}"/>
		<input type="hidden" name="headId" value="${employee.headId}"/>
		<input type="hidden" name="firstName" value="${employee.firstName}"/>
		<input type="hidden" name="listDeptId" value="${employee.listDeptId}"/>
		<input type="hidden" name="listHeadId" value="${employee.listHeadId}"/>
		<input type="hidden" name="listName" value="${employee.listName}"/>
	</form >
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>