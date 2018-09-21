<!DOCTYPE html>
<html>
<head>
<title>EMP Allowances</title>
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

.allowanceListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.allowanceListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.allowanceListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.allowanceListTableClass th {
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
    	 $(document).ready(function() {
    		  $.ajax({
              url : '../Payroll/listEmpAlwnce',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(allowanceData) {
                 $('#allowanceListTable').DataTable({
               	 columnDefs: [
            		    { className: 'text-right', targets: [1, 2, 3, 4, 5, 6] }, // 1- CCA, 2- Washing Allowance, 3- NPA, 4- Uniform Allowance, 5-  Family Planning Allowance, 6- Cycle Allowance
            		  ],
               	 "scrollX": true,
                 "scrollY": "350px",	 
                      data: allowanceData,
                      columns: [
      				      { data: 'fullName', title: 'Employee',"autoWidth": true},
                      	  { data: 'cca', title: 'CCA',"autoWidth": true},
                          { data: 'washingAlwance', title: 'Washing Allowance',"autoWidth": true},
                          { data: 'nonPracAwance', title: 'Non-Practical Allowance',"autoWidth": true},
                          { data: 'uniformAlwance', title: 'Uniform Allowance',"autoWidth": true},
                          { data: 'familyPlanAlwance', title: 'Family Planning Allowance',"autoWidth": true},
                          { data: 'cycleAlwance', title: 'Cycle Allowance',"autoWidth": true},
                          { data: 'hraFlag', title: 'HRA',"autoWidth": true},
                          { data: 'qtrFlag', title: 'QTR Flag',"autoWidth": true},
                          { data: 'afkFlag', title: 'AFK Flag',"autoWidth": true},
                          { data: 'taFlag', title: 'TA Flag',"autoWidth": true},
                          { data: 'pfFlag', title: 'PF Flag',"autoWidth": true},
                          {
								 'data': null,title:'<a href="#" onclick="addAllowance()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
								 'render': function (allowanceData, type, row) {
											   return '<a id="' + row.Id +'" href="#" onclick="UpdateAllowance(\'' + allowanceData.employeeId + '\')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteAllowance(\'' + allowanceData.employeeId + '\')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
										   }
					       }
                      ]
                  });
      		}
      	});
      });   	      
                 
      function UpdateAllowance(id){
       	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
   		  f.action="../Payroll/inputEmpAlwnce";
   		  f.submit();
   	  }
      function addAllowance(){
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
			<div class="container">
				<div style="margin-top: 12px; float: left; width: 98%;">
					<h4 style="color: #0101DF;">Employee Fixed Allowances</h4>	
					<div id="allowanceListDiv" class="allowanceListTableClass" style ="width:100%; margin-top: 25px">
						<table id="allowanceListTable" class="table table-striped table-bordered table-responsive"></table>
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