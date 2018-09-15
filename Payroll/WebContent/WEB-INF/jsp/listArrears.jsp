<%@page language="java" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
<title>Employee Arrears</title>
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
.EmpArrearTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.EmpArrearTableClass th, td {
	text-align: left;
	padding: 5px;
}

.EmpArrearTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.EmpArrearTableClass th {
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
	        url : '../Payroll/listArrears',
	        type:"GET",
	        contentType: "application/json;charset=utf-8",
	        success : function(employeeArrearData) {
	          	$('#EmpArrearTable').DataTable({
					data: employeeArrearData,
	                columns: [
	                  {data: 'fullName', title: 'Employee Name'},
	      			  {data: 'arrearsType', title: 'Arrears Type'},
	      			  {data: 'arrearsPay',title: 'Arrears Pay'},
	      			  {data: 'arrearsDeductions',title: 'Arrears Deduction'},
	      			  {data: 'miscPay',title: 'Misc Pay'},
	      			  {data: 'miscDeductions',title: 'Misc Deduction'},
	      			  {data: 'arrearsPayNote',title: 'Arrears Note'},
	      			  {data: 'arrearsDeductionNote',title: 'Arrears Deduction'},
	      			{
	 				     'data': null, title:
	 			  	 
	 	'<a href="#" onclick="createArrears()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg" /></a>',
	    ' width' : '150px',
	 	'render': function (employeeArrearData, type, row) {
	         return '<a href="#" onclick=viewArrear('+employeeArrearData.arrearId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteArrear('+employeeArrearData.arrearId+ ')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'
	 				               }
	 				}
	      			  
	      			  ]
				});
	          }
		  });
	});
	 function viewArrear(id){
		  var f = document.forms['editForm'];
		  f.arrearId.value=id;
		  f.action="../Payroll/inputArrear";
		  f.submit();
	}
	function createArrears(){
		  var f = document.forms['editForm'];
		  f.action="../Payroll/inputArrear";
		  f.submit();
	}
	function deleteArrear(id){
		  if(confirm("Are you sure want to delete Employee Arrears Pay?")){
			  var f = document.forms['editForm'];
			  f.arrearId.value=id;
			  f.action="../Payroll/deleteArrears";
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
			
			<div  class="container" class="row" style="position: relative;">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Employee Arrears</h4>
				
				<div id="EmpArrearDiv" class="EmpArrearTableClass" style ="width:100%;">
					<table id="EmpArrearTable" class="table table-striped table-bordered table-responsive"></table>
				</div>
				</div>
	            </div>
	   </div>
	
	<form action="" name="editForm" method="post">
	<input type="hidden" name="arrearId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>