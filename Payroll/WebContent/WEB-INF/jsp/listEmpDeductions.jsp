<!DOCTYPE html>
<html>
<head>
<title>Employee Deductions</title>
<script type="text/javascript">
      function getDeductionsList() {
    	  $.ajax({
              url : '../Payroll/listEmpDeductions',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                 <%-- var qtrTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#deductionListDiv'));--%>
                  $(data).each(function(i, empDeductions){
                	  $('<tr/>').appendTo($('#myTable').find('tbody:last'))
                	  		.append($('<td/>').text(empDeductions.fullName))
                			.append($('<td/>').text(empDeductions.section80C))
                			.append($('<td/>').text(empDeductions.homeLoanIntrst88EE))
                			.append($('<td/>').text(empDeductions.cess))
                			.append($('<td/>').text(empDeductions.selfDisable80U))
                			.append($('<td/>').text(empDeductions.loanPrincipal))
                			.append($('<td/>').text(empDeductions.schoolFees))
                			.append($('<td/>').text(empDeductions.lic))
                			.append($('<td/>').text(empDeductions.mutualFund))
                			.append($('<td/>').text(empDeductions.section80D))
                			.append($('<td/>').text(empDeductions.section80E))
                			.append($('<td/>').text(empDeductions.nsc))
                			.append($('<td/>').text(empDeductions.ppf))
                			.append($('<td/>').text(empDeductions.donation))
                			.append($('<td/>').text(empDeductions.section80DD))
                			.append($('<td/>').text(empDeductions.bonus))
                			.append($('<td/>').text(empDeductions.arrears))
                			.append($('<td/>').text(empDeductions.otAmount))
                			.append($('<td/>').text(empDeductions.otWages))
                			.append($('<td/>').append('<a href="#" onclick=viewDeductions('+empDeductions.employeeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDeductions('+empDeductions.employeeId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                	  
                  });
                  
              }
          });
      }
      function viewDeductions(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputEmpDeductions";
		  f.submit();
	  }
      function inputDeductions(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpDeductions";
		  f.submit();
	  }
      function deleteDeductions(id){
    	  if(confirm("Are you sure want to delete Employee Deductions?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteEmpDeductions";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getDeductionsList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<%--<div style="margin-top: 12px; float: left;"> --%>
			<h4 style="color: #0101DF;">Employee Deduction Details</h4>
		<%--<div>
			<div class="tblClass" id="deductionListDiv" style="overflow-x:auto;"> --%>
			<div style="width: 100%;">
			<div style="margin-top: 6px; float: left; max-width: 100%;">
			
			<div id="deductionListDiv" class="tblClass" style="overflow-x:auto;">
				<table id="myTable">
				<tr>
					<th>Employee</th>
					<th>Section 80C</th>
					<th>Home Loan Interest 88EE</th>
					<th>Cess</th>
					<th>Self Disable 80U</th>
					<th>Loan Principal</th>
					<th>School Fees</th>
					<th>LIC</th>
					<th>MutualFund</th>
					<th>Section 80D</th>
					<th>Section 80E</th>
					<th>NSC</th>
					<th>PPF</th>
					<th>Donation</th>
					<th>Section 80DD</th>
					<th>Bonus</th>
					<th>Arrears</th>
					<th>OT Amount</th>
					<th>OT Wages</th>
					
					<th><a href="#" onclick="inputDeductions()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>
					</th>
				</tr>
				</table>
				</div>
		</div>
	</div>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<%--<input type="hidden" name="designationId" value="0">
		<input type="hidden" name="departmentId" value="0"> --%>
		<input type="hidden" name="employeeId" value="0">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>