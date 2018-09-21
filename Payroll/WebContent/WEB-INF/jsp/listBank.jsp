<!DOCTYPE html>
<html>
<head>
<title>Bank Details</title>
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

.bankListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.bankListTableClass th, td {
	text-align: left;
	padding: 5px;
}

.bankListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.bankListTableClass th {
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
input[type=file] {
	display: inline-block;
}
.dataTables_paginate {
	text-align: right;
}
</style>
<script type="text/javascript">

$(document).ready(function() {

	$.ajax({
              url : '../Payroll/listBank',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(employeeBankData) {
            	  $('#bankListTable').DataTable({
            		  columnDefs: [
              		    { className: 'text-right', targets: [3] }, // 3- Account #
              		  ],
            		  data:employeeBankData,
            		  columns:[
            			  {data:'fullName',title:'Employee'},
            			  {data:'bankName',title:'Bank Name'},
            			  {data:'ifscCode',title:'Ifsc Code'},
            			  {data:'accountNo',title:'Account#'},            			 
            			  {
            				     'data': null, title:'<a href="#" onclick="addBank()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
                                 ' width' : '150px',
                                 'render': function (employeeBankData, type, row)
            				     {
                               return '<a id= "' +row.Id +'" href="#" onclick="updateBank(\'' + employeeBankData.employeeId + '\')"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/> <a id="' +row.Id+'"  href="#" onclick="deleteBank(\'' + employeeBankData.employeeId + '\')"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>'}
            			}
            			  
            		]	  
            	  });
              }
    	  });
});
      function updateBank(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputBank";
		  f.submit();
	  }
      function addBank(){
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
<body>
	<div class="contain-wrapp bodyDivCss">	
			<div class="container" style="margin-top: 85px;">  
				<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 33px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>	
			<div style="margin-top: 12px; float: left; width: 98%; margin-left: 15px;">
				<h4 style="color: #0101DF;">Bank Details</h4>
				<form method="post" name="editForm" action="">
					 <input type="hidden" name="employeeId" value="0">
				</form>
				<div id="bankListDiv" class="bankListTableClass" style ="width:100%;">
					<table id="bankListTable" class="table table-striped table-bordered table-responsive"></table>
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