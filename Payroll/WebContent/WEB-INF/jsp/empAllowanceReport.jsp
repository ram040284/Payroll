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
		window.location = "../Payroll/empAllowanceRptDownload";
	});
	
	$('#empRptTable').DataTable({
		"info" : false,
		"sort":$('#empRptTable tbody tr').length>1,
		"paging" : $('#empRptTable tbody tr').length>10,
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
	
	$('#empRptPrintTable').DataTable({
		"info" : false,
	  	"filter" : false,
	  	"lengthChange" : false,
	  	"autoWidth" : false,
	  	"paging":false
 	});
	
	$('#printLink').click(function(event) {
		var newWin = window.frames["rptPrintFrame"];
		var frameDoc = newWin.document;
        if (newWin.contentWindow)
            frameDoc = newWin.contentWindow.document;

	    frameDoc.getElementById("printReport").innerHTML = document.getElementById("empListPrintDiv").innerHTML;
	    frameDoc.getElementById("reportName").innerHTML = document.getElementById("reportName").innerHTML;
	
	    if (newWin.contentWindow) {
	    	newWin.contentWindow.focus();
			newWin.contentWindow.print();
	    } else {
	    	var browserName = navigator.userAgent.toLowerCase();
    		var printVar = newWin.document.execCommand('print', false, null);
	    	if (!printVar) {
	    		newWin.focus();
				newWin.print();
	    	}
	    }
	});
	
	$('#searchBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/empAllowanceReport");
		$("#formSearch").submit();
	});
	
});
      </script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	
	<jsp:include page="../jsp/employeeSearch.jsp" />
	
	<c:if test="${sessionScope.empAllowanceReport.size() ge 0}">
	<div  class="container" class="row" style ="position: relative;">
	<div id="empListDiv" class="rptTblClass" style ="width:100%;">
		<table id="empRptTable" class="table table-striped table-bordered table-hover table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>Department</th>
			<th>Head</th>
			<th>CCA</th>
			<th>Washing Allowance</th>
			<th>Conveyance Allowance</th>
			<th>Non Practical Allowance</th>
			<th>Uniform Allowance</th>
			<th>Family Plan Allowance</th>
			<th>Cycle Allowance</th>
			<th>HRA</th>
			</tr></thead>
			<c:forEach var="employee" items="${sessionScope.empAllowanceReport}">
			<tr>
			<td> ${employee.fullName} </td>
			<td> ${employee.department}</td>
			<td> ${employee.headName}</td>
			<td> ${employee.cca}</td>
			<td> ${employee.washingAllowance}</td>
			<td> ${employee.convAllowance}</td>
			<td> ${employee.nonPractAllowance}</td>
			<td> ${employee.uniformAllowance} </td>
			<td> ${employee.familyPlanAllowance}</td>
			<td> ${employee.cycleAllowance}</td>
			<td> ${employee.hraFlag? "Yes":"No"}</td>
			</tr>
			</c:forEach>
			
		</table>
		</div>
		</div>
	</c:if>

<c:if test="${sessionScope.empAllowanceReport.size() gt 0}">
	<div id="empListPrintDiv" class="rptTblClass" style ="width:100%;overflow-x: auto;overflow-y: auto;min-height:10px;max-height:380px;display:none;" >
		<table id="empRptPrintTable" class="table table-striped table-bordered table-hover table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>Department</th>
			<th>Head</th>
			<th>CCA</th>
			<th>Washing Allowance</th>
			<th>Conveyance Allowance</th>
			<th>Non Practical Allowance</th>
			<th>Uniform Allowance</th>
			<th>Family Plan Allowance</th>
			<th>Cycle Allowance</th>
			<th>HRA</th>
			</tr></thead>
			<c:forEach var="employee" items="${sessionScope.empAllowanceReport}">
			<tr>
			<td> ${employee.fullName} </td>
			<td> ${employee.department}</td>
			<td> ${employee.headName}</td>
			<td> ${employee.cca}</td>
			<td> ${employee.washingAllowance}</td>
			<td> ${employee.convAllowance}</td>
			<td> ${employee.nonPractAllowance}</td>
			<td> ${employee.uniformAllowance} </td>
			<td> ${employee.familyPlanAllowance}</td>
			<td> ${employee.cycleAllowance}</td>
			<td> ${employee.hraFlag? "Yes":"No"}</td>
			</tr>
			</c:forEach>
		</table>
		</div></div>
	</c:if>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>