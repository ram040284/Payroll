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
		window.location = "../Payroll/empSalaryRptDownload";
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
		$("#formSearch").attr("action", "../Payroll/empSalaryReport");
		$("#formSearch").submit();
	});
	
});
      </script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	
	<jsp:include page="../jsp/employeeSearch.jsp" />
	
	<c:if test="${sessionScope.empSalaryReport.size() gt 0}">
	<div  class="container" class="row" style ="position: relative;">
	<div id="empListDiv" style ="width:100%;overflow-x: auto;overflow-y: auto;min-height:10px;max-height:380px;">
		<table id="empRptTable" class="rptTblClass table table-striped table-bordered table-hover table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>Department</th>
			<th>Head</th>
			<th>Designation</th>
			<th>Basic</th>
			<th>Grade Pay</th>
			<th>CA</th>
			<th>UFA</th>
			<th>FPA</th>
			<th>TA</th>
			</tr></thead>
			<c:forEach var="employee" items="${sessionScope.empSalaryReport}">
			<tr>
			<td> ${employee.fullName} </td>
			<td> ${employee.department}</td>
			<td> ${employee.headName}</td>
			<td> ${employee.designation}</td>
			<td> ${employee.basic}</td>
			<td> ${employee.gradePay}</td>
			<td> ${employee.ca}</td>
			<td> ${employee.ufa}</td>
			<td> ${employee.fpa} </td>
			<td> ${employee.ta}</td>
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