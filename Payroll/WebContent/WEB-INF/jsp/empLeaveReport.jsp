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

function searchEmps(){
	if($('#departmentId').val() == 0 && $('#firstName').val().trim() == ""){
		alert('Either Department or Name must be provided to get List!');
		$('#departmentId').focus();
		return false;
	}
	var f = document.forms['empSearch'];
	f.action="../Payroll/empLeaveReportSearch";
	f.submit();
}

function inputPage(){
	
}

</script>
</head>
<body><%-- onload="getList()">--%>
	
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<h5 style="color: #0101DF;">Employee Leave Request Report</h5>
		<jsp:include page="../jsp/public/searchCriteria.jsp" />
		<c:if test="${sessionScope.empLeaveReport.size() gt 0}">
		<div style="margin-top: 6px; float: left; width: 100%;">
			
			<div id="empListDiv" class="rptTblClass" style="width: 100%;">
			<table id="empLeaveTable" class="table table-striped table-bordered table-hover table-responsive">
			<thead>
				<tr>
					<th>Employee</th>
					<th>Leave Type </th>
					<th>Leave Balance </th>
					<th>From Date</th>
					<th>To Date</th>
					<th>Number of Days </th>
					<th>Reason</th>
				</tr></thead>
				<c:forEach var="leave" items="${sessionScope.empLeaveReport}">
				<tr>
					<td> ${leave.employee.firstName} ${leave.employee.middleName} ${leave.employee.lastName}</td>
					<td> ${leave.leave.leaveType}</td>
					<td> ${leave.leave.leaveBalance}</td>
					<td> ${leave.fromDate}</td>
					<td> ${leave.toDate}</td>
					<td> ${leave.noOfLeaves} </td>
					<td> ${leave.reason}</td>
				</tr>
				</c:forEach>
				</table>
		</div>
	</div>
	</c:if>
	</div>
	</div>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>