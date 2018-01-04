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
                 
                  var qtrTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#deductionListDiv'));
                  $(data).each(function(i, empDeductions){
                	  $('<tr/>').appendTo(qtrTab)
                	  		.append($('<td/>').text(empDeductions.fullName))
                			.append($('<td/>').text(empDeductions.section80C))
                			.append($('<td/>').text(empDeductions.homeLoanIntrst88EE))
                			.append($('<td/>').text(empDeductions.cess))
                			.append($('<td/>').text(empDeductions.selfDisable80U))
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
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Employee Deduction Details</h4>
		<div>
			<div class="tblClass" id="deductionListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>Section 80C</th>
					<th>Home Loan Interest 88EE</th>
					<th>Cess</th>
					<th>Self Disable 80U</th>
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