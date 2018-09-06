<!DOCTYPE html>
<html>
<head>
<title>EMP Fixed Deductions</title>
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

.deductionListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.deductionListTableClass th, td {
	text-align: left;
	padding: 5px;
}

.deductionListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.deductionListTableClass th {
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
              url : '../Payroll/listFixedEmpDeductions',
              type:"GET",
              contentType: "application/json;charset=utf-8",
			   success : function(empDeductionData){
            	  $('#deductionListTable').DataTable({
            		  data:empDeductionData,
				  columns:[
            			  {data:'fullName',title:'Employee'},
					      {data:'kssUnionFee',title:'KSS Union Fee'},
            			  {data:'rent',title:'Rent'},
            			  {data:'courtRecovery',title:'Court Recovery'},
						  {data:'unionFee',title:'Union Fee'},
						  {data:'gis',title:'GIS'},
						  {data:'additionalPF',title:'Additional PF'},
                          { 
						   'data': null, 
						   title:'<a href="#" onclick="addDeductDtls()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
                                 ' width' : '150px',
                                 'render': function (empDeductionData, type, row)
            				     {
                               return '<a id= "' +row.Id +'" href="#" onclick="updateDeductions('+empDeductionData.employeeId+')"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/> <a id="' +row.Id+'"  href="#" onclick="deleteDeductions('+empDeductionData.employeeId+')"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>'}
       
                                }
						  
           
						  ]	  
            	  });
              }
    	  });
});

                 
      function updateDeductions(id) 
	 {
		var f = document.forms['editForm'];
		f.employeeId.value = id;
        f.action = "../Payroll/inputEmpFixedDeductions";
        f.submit();
	}
	function addDeductDtls()
	{
		var f = document.forms['editForm'];
		f.action = "../Payroll/inputEmpFixedDeductions";
		f.submit();
	}
	function deleteDeductions(id) {
		if (confirm("Are you sure want to delete Employee Deduction Details?"))
		{
			var f = document.forms['editForm'];
			f.employeeId.value = id;
			f.action = "../Payroll/deleteEmpFixedDeductions";
			f.submit();
		}
	}</script>
</head>
 <body>
	<div class="contain-wrapp bodyDivCss">	
		<div  class="container" class="row" style="position: relative;">
        <div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn"
							onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
		
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">EMP Fixed Deductions</h4>
				
				
				<form method="post" name="editForm" action="">
					 <input type="hidden" name="employeeId" value="0">
					<a href="#" onclick="inputDeductDtls()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg" />
					</a>
				</form>
				<div id="deductionListDiv" class="deductionListTableClass" style ="width:100%;">
					<table id="deductionListTable" class="table table-striped table-bordered table-responsive"></table>
				</div>
				
					
		</div>
	</div>
</div><form action="" name="editForm" method="post">
		<input type="hidden" name="employeeId" value="0">
	</form>

<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>