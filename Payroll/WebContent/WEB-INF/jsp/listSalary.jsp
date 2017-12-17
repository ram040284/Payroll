<!DOCTYPE html>
<html>
<head>
<title>Salary Details</title>
<script type="text/javascript">
      function getLeavesList() {
    	  $.ajax({
              url : '../Payroll/listSalary',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                var salaryTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#salaryListDiv'));
                  $(data).each(function(i, salary){
                	  $('<tr/>').appendTo(salaryTab)
                	  		.append($('<td/>').text(salary.fullName))
                			.append($('<td/>').text(salary.year))
                			.append($('<td/>').text(salary.basic))
                			.append($('<td/>').text(salary.gradePay))
                			.append($('<td/>').text(salary.scalePay))
                			.append($('<td/>').text(salary.scaleInc))
                			.append($('<td/>').append('<a href="#" onclick=viewSalary('+salary.employeeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteSalary('+salary.employeeId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                	  
                  });
                  
              }
          });
      }
      function viewSalary(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputSalary";
		  f.submit();
	  }
      function inputSalary(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputSalary";
		  f.submit();
	  }
      function deleteSalary(id){
    	  if(confirm("Are you sure want to delete Employee Salary?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteSalary";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getLeavesList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Salary Details</h4>
		<div>
			
				<div class="tblClass" id="salaryListDiv">
				<table>
				<tr>
					<%-- <th>Department</th>
					<th>Designation</th>--%>
					<th>Employee</th>
					<th>Year</th>
					<th>Basic Pay</th>
					<th>Grade Pay</th>
					<th>Scale Pay</th>
					<th>Scale Increment</th>
					<th><a href="#" onclick="inputSalary()" title="Add">
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