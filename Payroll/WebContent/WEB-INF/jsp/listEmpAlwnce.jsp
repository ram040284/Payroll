<!DOCTYPE html>
<html>
<head>
<title>EMP Allowances</title>
<script type="text/javascript">
      function getAllowanceList() {
    	  $.ajax({
              url : '../Payroll/listEmpAlwnce',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  var allowanceTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#allowanceListDiv'));
                  $(data).each(function(i, empAllowance){
                	  $('<tr/>').appendTo(allowanceTab)
                	  		.append($('<td/>').text(empAllowance.fullName))
                			.append($('<td/>').text(empAllowance.cca.toFixed(2)))
                			.append($('<td/>').text(empAllowance.washingAlwance.toFixed(2)))
                			.append($('<td/>').text(empAllowance.nonPracAwance.toFixed(2)))
                			.append($('<td/>').text(empAllowance.uniformAlwance.toFixed(2)))
                			.append($('<td/>').text(empAllowance.familyPlanAlwance.toFixed(2)))
                			.append($('<td/>').text(empAllowance.cycleAlwance.toFixed(2)))
							.append($('<td/>').text(empAllowance.hraFlag))
							.append($('<td/>').text(empAllowance.qtrFlag))
							.append($('<td/>').text(empAllowance.afkFlag))
							.append($('<td/>').text(empAllowance.taFlag))
							.append($('<td/>').text(empAllowance.pfFlag))
                			.append($('<td/>').append('<a href="#" onclick=viewAllowance('+empAllowance.employeeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteAllowance('+empAllowance.employeeId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
                  
              }
          });
      }
      
      function viewAllowance(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputEmpAlwnce";
		  f.submit();
	  }
      function inputAllowance(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpAlwnce";
		  f.submit();
	  }
      function deleteAllowance(id){
    	  if(confirm("Are you sure want to delete Employee Fixed Allowances?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteEmpAllowance";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getAllowanceList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Employee Fixed Allowances</h4>
		<div>
				<div class="tblClass" id="allowanceListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>CCA</th>
					<th>Washing Alwnce</th>
					<th>Non-Prac Alwnce</th>
					<th>Uniform Alwnce</th>
					<th>Fmly Plning Alwnce</th>
					<th>Cycle Alwnce</th>
					<th>HRA</th>
					<th>QTR  Flag</th>
					<th>AFK Flag</th>
					<th>TA Flag</th>
					<th>PF Flag</th>
					<th><a href="#" onclick="inputAllowance()" title="Add">
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