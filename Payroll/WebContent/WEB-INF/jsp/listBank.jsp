<!DOCTYPE html>
<html>
<head>
<title>Bank Details</title>
<script type="text/javascript">
      function getBankList() {
    	  $.ajax({
              url : '../Payroll/listBank',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                  var bankTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#bankListDiv'));
                  $(data).each(function(i, bank){
                	  $('<tr/>').appendTo(bankTab)
                	  		.append($('<td/>').text(bank.fullName))
                			.append($('<td/>').text(bank.bankName))
                			.append($('<td/>').text(bank.ifscCode))
                			.append($('<td/>').text(bank.accountNo))
                			.append($('<td/>').append('<a href="#" onclick="viewBank('+bank.employeeId+')"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteBank('+bank.employeeId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewBank(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputBank";
		  f.submit();
	  }
      function inputBank(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputBank";
		  f.submit();
	  }
      function deleteBank(id){
    	  if(confirm("Are you sure want to delete Employee Bank?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteBank";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getBankList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Bank Details</h4>
				<div>
					
				<div class="tblClass" id="bankListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>Bank </th>
					<th>IFSC Code</th>
					<th>Account#</th>
					<th><a href="#" onclick="inputBank()" title="Add">
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
		<input type="hidden" name="employeeId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>