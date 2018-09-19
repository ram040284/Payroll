<!DOCTYPE html>
<html>
<head>
<title>Department Details</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
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

.banksListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.banksListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.banksListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.banksListTableClass th {
	background-color: #8B9DC3;
	color: #fff;
	cursor: pointer;
}
table.dataTable thead:first-child .sorting_asc { 
	background: url('../Payroll/resources/images/uparrow.png') no-repeat right bottom 8px; 
	background-size: 25px; 
	background-color: #8B9DC3;
	color: white;
}
table.dataTable thead:first-child .sorting_desc { 
	background: url('../Payroll/resources/images/downarrow.png') no-repeat right bottom 8px; 
	background-size: 25px; 
	background-color: #8B9DC3;
	color: white;
}
.dataTables_paginate {
	text-align: right;
}
</style>
<script type="text/javascript">
   /*    function getBanksList() {
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
      } */
      
      $(document).ready(function(){
		  $.ajax({
          url : '../Payroll/listBanks',
          type:"GET",
          contentType: "application/json;charset=utf-8",
          success : function(banksData) {
         $('#banksListTable').DataTable({
        	 "scrollY": "300px",
                data: banksData,
                  columns: [
  					{ data: 'bankName', title: 'Name',"autoWidth": false},
                  	{ data: 'ifscCode', title: 'IFSC Code',"autoWidth": false},
                    {
					 'data': null,title:'<a href="#" onclick="inputBanks()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
					 'render': function (banksData, type, row) {
        			   return '<a id="' + row.Id +'" href="#" onclick="viewBank('+banksData.bankId+')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteBank('+banksData.bankId+')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
		            	}
					}
                 ] 
              });
  		   }
  	   });
  });   	      
      
      
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
    	  if(confirm("Are you sure to delete bank details?")){
    		  var f = document.forms['editForm'];
    		  f.bankId.value=id;
    		  f.action="../Payroll/deleteBankDetails";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	 		<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/mastersMenu')">Back</button>
					</div>
				</div>
			</div>
		</div>
		<div class="container">	
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Bank Details</h4>
					<div id="banksListDiv" class="banksListTableClass" style ="width:100%; margin-top: 25px">
							<table id="banksListTable" class="table table-striped table-bordered table-responsive"></table>
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