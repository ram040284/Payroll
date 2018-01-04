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
		"paging" : $('#empRptTable tbody tr').length>10,
		"displayLength": 10,
	  	"filter" : false,
	  	"lengthChange" : false,
	  	"autoWidth" : false,
	  	"pagingType" : "full_numbers",
	  	"scrollX": true,
	  	"scrollY": "300px",
	  	"scrollCollapse": true
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
	
	<c:if test="${sessionScope.employees.size() gt 0}">
	<div  class="container" class="row" style ="position: relative;">
	<div id="empListDiv" class="rptTblClass" style ="width:100%;" >
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
	
	<c:if test="${sessionScope.employees.size() gt 0}">
	<div id="empListPrintDiv" class="rptTblClass" style ="width:100%;overflow-x: auto;overflow-y: auto;min-height:10px;max-height:380px;display:none;" >
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
	</c:if>
	</div>			
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>