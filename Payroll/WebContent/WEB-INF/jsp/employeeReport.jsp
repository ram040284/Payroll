<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Reports</title>

<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("#downloadLink").click(function(event) {
		window.location = "../Payroll/employeeRptDownload";
	});
	
	$('#empRptTable').DataTable({
		"info" : false,
		"paging" : false,
	  	"filter" : false,
	  	"lengthChange" : false,
	  	"autoWidth" : true,
	  	"pagingType" : "full"
 	});
	
	$('#searchBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/employeeReport");
		$("#formSearch").submit();
	});
});
      </script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	
	<jsp:include page="../jsp/employeeSearch.jsp" />
	
	<c:if test="${sessionScope.employees.size() gt 0}">
	<div  class="container" class="row" style ="position: relative;">
	<div id="empListDiv" class="rptTblClass" style ="width:100%;overflow-x: auto;overflow-y: auto;min-height:10px;max-height:380px;" >
		<table id="empRptTable" class="table table-striped table-bordered table-hover table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>Department</th>
			<th>Head</th>
			<th>Designation</th>
			<th>Address</th>
			<th>Date of Birth</th>
			<th>Gender</th>
			<th>Joining Date</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Aadhar Number</th>
			<th>PAN</th>
			</tr></thead>
			<c:forEach var="employee" items="${sessionScope.employees}">
			<tr>
			<td> ${employee.fullName} </td>
			<td> ${employee.department}</td>
			<td> ${employee.headName}</td>
			<td> ${employee.designation}</td>
			<td> ${employee.address}</td>
			<td> ${employee.dob} </td>
			<td> ${employee.gender}</td>
			<td> ${employee.joiningDate}</td>
			<td> ${employee.phone}</td>
			<td> ${employee.email}</td>
			<td> ${employee.adharNo} </td>
			<td> ${employee.pan} </td>
			</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</c:if>
	</div>			
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>