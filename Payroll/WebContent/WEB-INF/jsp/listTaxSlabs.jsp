<!DOCTYPE html>
<html>
<head>
<title>Incometax Slabs</title>
<style type="text/css">
</style>
<script type="text/javascript">
      function getTaxSlabsList() {
          $.ajax({
              url : '../Payroll/listTaxSlabs',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                var taxSlabsTab = $('<table/>').appendTo($('#taxSlabsListDiv'));
                  $(data).each(function(i, taxSlab){
                	  $('<tr/>').appendTo(taxSlabsTab)
                	  		.append($('<td/>').text(taxSlab.financialYear))
                	  		.append($('<td/>').text(taxSlab.lowerSlab))
                			.append($('<td/>').text(taxSlab.higherSlab))
                			.append($('<td/>').text(taxSlab.incomtaxPercent))
                	  		.append($('<td/>').text(taxSlab.surcharge))
                			.append($('<td/>').text(taxSlab.educationCess))
                			.append($('<td/>').text(taxSlab.otherCess))
                			.append($('<td/>').append('<a href="#" onclick=viewTaxSlab('+taxSlab.incomtaxId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDesg('+taxSlab.incomtaxId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewTaxSlab(id){
    	  var f = document.forms['editForm'];
    	  f.incomtaxId.value=id;
		  f.action="../Payroll/inputTaxSlab";
		  f.submit();
	  }
      function inputTaxSlab(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputTaxSlab";
		  f.submit();
	  }
      function deleteTaxSlab(id){
    	  if(confirm("Are you sure want to delete Incometax Slab?")){
    		  var f = document.forms['editForm'];
    		  f.incomtaxId.value=id;
    		  f.action="../Payroll/deleteTaxSlab";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getTaxSlabsList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	 <div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Incometax Slabs</h4>
		<div>
			
				<div class="tblClass" id="taxSlabsListDiv">
				<table>
				<tr>
					<th>Financial Year</th>
					<th>Lower Slab</th>
					<th>Higher Slab</th>
					<th>Incometax Percentage</th>
					<th>Surcharges</th>
					<th>Education Cess</th>
					<th>Other Cess</th>
					<th><a href="#" onclick="inputTaxSlab()" title="Add">
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
		<input type="hidden" name="incomtaxId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>