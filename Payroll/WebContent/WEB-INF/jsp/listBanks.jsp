<!DOCTYPE html>
<html>
<head>
<title>Department Details</title>
<style type="text/css">
	</style>
<script type="text/javascript">
      function getBanksList() {
          $.ajax({
              url : '../Payroll/listBanks',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                  var banksTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#banksListDiv'));
                  $(data).each(function(i, bankDetails){
                	  $('<tr/>').appendTo(banksTab)
                	  		.append($('<td/>').text(bankDetails.bankName))
                			  .append($('<td/>').text(bankDetails.ifscCode))
                			  .append($('<td/>').append('<a href="#" onclick=viewBank('+bankDetails.bankId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteBank('+bankDetails.bankId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewBank(id){
    	  var f = document.forms['editForm'];
    	  f.bankId.value=id;
		  f.action="../Payroll/inputBankDetails";
		  f.submit();
	  }
      
      function inputBanks(){
    	  var f = document.forms['editForm'];
    	  f.action="../Payroll/inputBankDetails";
		  f.submit();
	  }
      
      function deleteBank(id){
    	  if(confirm("Are you sure to delete Department?")){
    		  var f = document.forms['editForm'];
    		  f.departmentId.value=id;
    		  f.action="../Payroll/deleteBankDetails";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getBanksList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	 
	<%--<div class="wrapper">--%>
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Bank Details</h4>
			<div>
				<div class="tblClass" id="banksListDiv">
				<table>
				<tr>
					<th>Name</th>
					<th>IFSC Code</th>
					<th><a href="#" onclick="inputBanks()" title="Add">
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
		<input type="hidden" name="bankId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>