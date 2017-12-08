<!DOCTYPE html>
<html>
<head>
<title>Provident Fund Details</title>
<script type="text/javascript">
      function getPfList() {
    	  $.ajax({
              url : '../Payroll/listEmpPf',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                 var pfTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#pfListDiv'));
                  $(data).each(function(i, empPf){
                	  $('<tr/>').appendTo(pfTab)
                	  		.append($('<td/>').text(empPf.fullName))
                			.append($('<td/>').text(empPf.pfDate))
                			.append($('<td/>').text(empPf.pfsCpfCntrbn))
                			.append($('<td/>').text(empPf.pfLoneRecAmt))
                			.append($('<td/>').text(empPf.cfLoneRecAmt))
                			.append($('<td/>').text(empPf.apfAcpfCntrbn))
                			.append($('<td/>').append('<a href="#" onclick=viewPf('+empPf.empId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deletePf('+empPf.empId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
                  
              }
          });
      }
      function viewPf(id){
    	  var f = document.forms['editForm'];
		  f.empId.value=id;
		  f.action="../Payroll/inputEmpPf";
		  f.submit();
	  }
      function inputPf(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpPf";
		  f.submit();
	  }
      function deletePf(id){
    	  if(confirm("Are you sure want to delete Employee Provident Fund?")){
    		  var f = document.forms[0];
    		  f.empId.value=id;
    		  f.action="../Payroll/deletePf";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getPfList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Provident Fund Details</h4>
		<div>
			<div class="tblClass" id="pfListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>PF Date</th>
					<th>PF - CPF Contribution</th>
					<th>APF - APCF Contribution</th>
					<th>PF Loan Amount</th>
					<th>CF Loan Amount</th>
					<th><a href="#" onclick="inputPf()" title="Add">
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
		<input type="hidden" name="empId" value="0">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>