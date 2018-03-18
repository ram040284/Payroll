<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Employee Deductions</title>
<link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>

<style>
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
.tblClass_1 table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	border: 1px solid #aaa;
}

.tblClass_1 th, td {
	text-align: left;
	padding: 5px;
	min-width: 100px;
}

.tblClass_1 tr:nth-child(even) {
	background-color: #f2f2f2;
}

.tblClass_1 th {
	background-color: #8B9DC3;
	color: white;
}
.tblClass_1 tr > th:last-of-type {
	background-color: #fff;
    text-align: center;
}
.tblClass_1 tr > td:last-of-type {
    text-align: center;
}

</style>
<script type="text/javascript">
      function getDeductionsList() {
    	  $.ajax({
              url : '../Payroll/listEmpDeductDtls',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  var qtrTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#deductDtlsListDiv'));
                  $(data).each(function(i, empDeductions){
                	  $('<tr/>').appendTo(qtrTab)
                	  		.append($('<td/>').text(empDeductions.fullName))
                			.append($('<td/>').text(empDeductions.afkRent))
                			.append($('<td/>').text(empDeductions.society))
                			.append($('<td/>').text(empDeductions.electRecovery))
                			.append($('<td/>').text(empDeductions.courtRecovery))
                			.append($('<td/>').text(empDeductions.unionFee))
                			.append($('<td/>').text(empDeductions.otherDeductions))
                			.append($('<td/>').text(empDeductions.miscRecovery))
                			.append($('<td/>').text(empDeductions.kssUnionRecovery))
                			<%--.append($('<td/>').text(empDeductions.profTax))--%>
                			.append($('<td/>').append('<a href="#" onclick=viewDeductions('+empDeductions.employeeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDeductions('+empDeductions.employeeId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                	  
                  });
                  
              }
          });
      }
      function viewDeductions(id){
    	  var f = document.forms['editForm'];
    	  alert('id:'+id);
		  f.employeeId.value=id;
		  f.action="../Payroll/inputEmpDedDtls";
		  f.submit();
	  }
      function inputDeductDtls(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpDedDtls";
		  f.submit();
	  }
      function deleteDeductions(id){
    	  if(confirm("Are you sure want to delete Employee Deduction Details?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteEmpDeductDtls";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body <%--onload="getDeductionsList()" --%>>
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
		<%--<div style="margin-top: 12px; float: left; width: 98%;">--%>
		<h4 style="color: #0101DF;">Employee Deduction Details</h4>
			<div style="width: 100%;">
			<div style="margin-top: 6px; float: left; max-width: 100%;">
			
			<div id="deductDtlsListDiv" class="tblClass_1" style="max-width: 100%;">
	
		<%--<div>
			<div class="tblClass" id="deductDtlsListDiv"> --%>
				<table>
				<tr>
					<th>Employee</th>
					<th>AFK Rent</th>
					<th>Society</th>
					<th>Electricity Rcvry</th>
					<th>Court Rcvry</th>
					<th>Union Fee</th>
					<th>Other Deductions</th>
					<th>Misc. Rcvry</th>
					<th>KSS Union Fee</th>
					<%--<th>Prof. Tax</th> --%>
					<th><a href="#" onclick="inputDeductDtls()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>
					</th>
				</tr>
				<c:forEach var="empDeductions" items="${empDeductions}">
					<tr>
					<td> ${empDeductions.fullName} </td>
					<td> ${empDeductions.afkRent} </td>
					<td> ${empDeductions.society} </td>
					<td> ${empDeductions.electRecovery} </td>
					<td> ${empDeductions.courtRecovery} </td>
					<td> ${empDeductions.unionFee} </td>
					<td> ${empDeductions.otherDeductions} </td>
					<td> ${empDeductions.miscRecovery} </td>
					<td> ${empDeductions.kssUnionRecovery} </td>
					<td><a href="#" onclick="viewDeductions('${empDeductions.employeeId}')" title="Edit">
							<img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/>
						</a>
						<a href="#" onclick="deleteDeductions('${empDeductions.employeeId}')">
							<img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>
						</a>
					</td>
					</tr>
				</c:forEach>
				</table>
				</div>
		</div>
	</div>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="employeeId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>