<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Reports</title>


<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>

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

.rptTblClass tr:nth-child(even) {
	background-color: #f2f2f2;
}

.rptTblClass th {
	background-color: #8B9DC3;
	color: white;
}

</style>
<jsp:include page="../jsp/public/postHeader.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$('#printLink').click(function(event) {
		var newWin = window.frames["empRptFrame"];
		
		var frameDoc = newWin.document;
        if (newWin.contentWindow)
            frameDoc = newWin.contentWindow.document;

        frameDoc.open();
        frameDoc.writeln($('#empListDiv').html());
        frameDoc.close();
        if (newWin.contentWindow) {
		newWin.contentWindow.focus();
		newWin.contentWindow.print();
        } else {
        	newWin.focus();
    		newWin.print();
        }
	});
	
	$("#downloadLink").click(function(event) {
		alert("download ...");
		window.location = "../Payroll/employeeRptDownload";
	});
});
      </script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	<div  class="container" class="row" style ="position: relative;">
	<jsp:include page="../jsp/employeeSearch.jsp" />
	
	<c:if test="${employees.size() gt 0}">
	<div class="col-sm-12" style ="width:100%;padding-left:0px;margin-top:10px;margin-left:0px;">
	<div class="col-sm-4" style ="padding-left:0px;margin-left:0px;"><h6 style="color: #0101DF;margin-bottom:0px;">Employee Information</h6></div> 
	<div class="col-sm-4" ></div><div class="col-sm-2" ></div>
	<div class="col-sm-2" style ="text-align:right;">
	<a id="downloadLink" href="javascript:void(0)" style="color: #0101DF;text-decoration: underline;margin-right:15px;"><b>Download</b></a>
	<a id="printLink" href="javascript:void(0)" style="color: #0101DF;text-decoration: underline;"><b>Print</b></a></div>
	</div>
	<div id="empListDiv" style ="width:100%;margin-top:10px;overflow:auto;max-height: 500px;overflow-y: auto;border-collapse: collapse;border: 1px solid #aaa;">
	<%-- <c:if test="${empty employees}"> 
		<h4 style="color: #0101DF;" align="center">No Records Found</h4>
	</c:if> --%>
		<table class="rptTblClass table-responsive">
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
			<th>Year</th>
			<th>Basic Pay</th>
			<th>Grade Pay</th>
			<th>Scale Pay</th>
			<th>Scale Increment</th>
			
			</tr>
			<c:forEach var="employee" items="${employees}">
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
			<td> ${employee.salaryVo.year}</td>
			<td> ${employee.salaryVo.basic}</td>
			<td> ${employee.salaryVo.gradePay}</td>
			<td> ${employee.salaryVo.scalePay}</td>
			<td> ${employee.salaryVo.scaleInc}</td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</c:if>
	</div>			
	</div>	
	<iframe id="empRptFrame" name="empRptFrame" src="javascript:false" style="display:none;">
	</iframe>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>