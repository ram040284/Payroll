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
	
	$('#searchBtn').click(function(event) {
		$("#formSearch").attr("action", "../Payroll/employeeReport");
		$("#formSearch").submit();
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
});
      </script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	
	<jsp:include page="../jsp/employeeSearch.jsp" />
	
	<c:if test="${sessionScope.employees.size() ge 0}">
	<div  class="container" class="row" style ="position: relative;">
	<div id="empListDiv" class="rptTblClass" style ="width:100%;" >
		<table id="empRptTable" class="table table-striped table-bordered table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>Department</th>
			<th>Head</th>
			<th>Designation</th>
			<th>Date of Birth</th>
			<th>Gender</th>
			<th>Joining Date</th>
			<th>Aadhar Number</th>
			<th>PAN</th>
			<th>Primary Phone</th>
			<th>Primary Email</th>
			<th>Primary Address</th>
			<th>Secondary Phone</th>
			<th>Secondary Email</th>
			<th>Secondary Address</th>
			</tr></thead>
			<c:forEach var="employee" items="${sessionScope.employees}">
			<tr>
			<td> ${employee.fullName} </td>
			<td> ${employee.department}</td>
			<td> ${employee.headName}</td>
			<td> ${employee.designation}</td>
			<td> ${employee.dob} </td>
			<td> ${employee.gender}</td>
			<td> ${employee.joiningDate}</td>
			<td> ${employee.adharNo} </td>
			<td> ${employee.pan} </td>
			<td> ${employee.empContact.phone}</td>
			<td> ${employee.empContact.email}</td>
			<td> ${employee.address}</td>
			<td> ${employee.empContact.secPhone}</td>
			<td> ${employee.empContact.secEmail}</td>
			<td> ${employee.secAddress}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</c:if>
	
	<c:if test="${sessionScope.employees.size() gt 0}">
	<div id="empListPrintDiv" class="rptTblClass" style ="width:100%;display:none;" >
		<table id="empRptPrintTable" class="table table-striped table-bordered table-responsive">
		<thead>
			<tr>
			<th>Name</th>
			<th>Department</th>
			<th>Head</th>
			<th>Designation</th>
			<th>Date of Birth</th>
			<th>Gender</th>
			<th>Joining Date</th>
			<th>Aadhar Number</th>
			<th>PAN</th>
			<th>Primary Phone</th>
			<th>Primary Email</th>
			<th>Primary Address</th>
			<th>Secondary Phone</th>
			<th>Secondary Email</th>
			<th>Secondary Address</th>
			</tr></thead>
			<c:forEach var="employee" items="${sessionScope.employees}">
			<tr>
			<td> ${employee.fullName} </td>
			<td> ${employee.department}</td>
			<td> ${employee.headName}</td>
			<td> ${employee.designation}</td>
			<td> ${employee.dob} </td>
			<td> ${employee.gender}</td>
			<td> ${employee.joiningDate}</td>
			<td> ${employee.adharNo} </td>
			<td> ${employee.pan} </td>
			<td> ${employee.empContact.phone}</td>
			<td> ${employee.empContact.email}</td>
			<td> ${employee.address}</td>
			<td> ${employee.empContact.secPhone}</td>
			<td> ${employee.empContact.secEmail}</td>
			<td> ${employee.secAddress}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	</div>			
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>