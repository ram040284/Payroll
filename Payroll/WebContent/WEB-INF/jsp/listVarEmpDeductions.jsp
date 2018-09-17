<!DOCTYPE html>
<html>
<head>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
<title>Employee Fixed Deduction</title>
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
.EmpDeductionTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.EmpDeductionTableClass th, td {
	text-align: left;
	padding: 5px;
}

.EmpDeductionTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.EmpDeductionTableClass th {
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
		url : '../Payroll/listVarEmpDeductions',
		type : "GET",
		contentType : "application/json;charset=utf-8", 
		success : function(employeeDeductionData) {  
			$('#EmpDeductionTable').DataTable({
				columnDefs: [
        		    { className: 'text-right', targets: [1, 2, 3, 4, 5] }, // 1-  AFK Rent, 2- Society, 3- PF Loan Recovery, 4- Other Deduction, 5- Misc Recover
        		  ],
				"sScrollX": "100%",
		        "sScrollXInner": "110%",
		        "bScrollCollapse": true, 
                data: employeeDeductionData,
                columns: [
                  {data: 'fullName', title: 'Employee',"autowidth":true},
      			  {data: 'afkRent', title: 'AFK Rent',"autowidth":true},
      			  {data: 'society',title: 'Society',"autowidth":true},
      			  {data: 'pfLoanRecovery',title: 'PF Loan Recovery',"autowidth":true},
      			  {data: 'otherDeductions',title: 'Other Deduction',"autowidth":true},
      			  {data: 'miscRecovery',title: 'Misc Recovery',"autowidth":true},
      			  {data: 'monthDate',title: 'Month Date',"autowidth":true},
      			  {data: 'note',title: 'Note',"autowidth":true},
      			{
 				     'data': null, title:
 				    	 
 	'<a href="#" onclick="inputDeductDtls()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg" /></a>',
    ' width' : '150px',
 	'render': function (employeeDeductionData, type, row) {
         return '<a href="#" onclick=addUpdateDeductions('+employeeDeductionData.employeeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDeductions('+employeeDeductionData.employeeId+ ')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'
 				               }
 				}
      			  
                ]
			        
			   });
		}
	});
	
});
  
function addUpdateDeductions(id) {
	var f = document.forms['editForm'];
	f.employeeId.value = id;
    f.action = "../Payroll/inputEmpVarDeductions";
	f.submit();
}
function inputDeductDtls() {
	var f = document.forms['editForm'];
	f.action = "../Payroll/inputEmpVarDeductions";
	f.submit();
}
function deleteDeductions(id) {
	if (confirm("Are you sure want to delete Employee Deduction Details?")) {
		var f = document.forms['editForm'];
		f.employeeId.value = id;
		f.action = "../Payroll/deleteEmpVarDeductions";
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
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		
		
		<div  class="container" class="row" style="position: relative;">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Employee Variable Deduction</h4>
				
				
				<div id="EmpDeductionDiv" class="EmpDeductionTableClass" style ="width:100%;">
					<table id="EmpDeductionTable" class="table table-striped table-bordered table-responsive"></table>
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







