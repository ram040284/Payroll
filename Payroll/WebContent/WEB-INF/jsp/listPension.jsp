<!DOCTYPE html>
<html>
<head>
<title>Pension Details</title>
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

.salaryListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.salaryListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.salaryListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.pensionListTableClass th {
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
    	 $(document).ready(function(){
    		  $.ajax({
              url : '../Payroll/listPension',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(pensionData) {
             $('#pensionListTable').DataTable({
            	 columnDefs: [
            		    { className: 'text-right', targets: [1,2,3,4,5,6] },
            		  ],
            	 "scrollY": "300px",
                  data: pensionData,
                      columns: [
      					{ data: 'fullName', title: 'Pensioner',"autoWidth": false},
                        { data: 'basicPension', title: 'Basic Pension',"autoWidth": false},
                        { data: 'residualPension', title: 'Residual Pension',"autoWidth": false},
                        { data: 'medicalAllowance', title: 'Medical Allowance',"autoWidth": false},
                        { data: 'dearnessRelief', title: 'Dearness Relief',"autoWidth": false},
                        { data: 'arrears', title: 'Arrears',"autoWidth": false},
                        { data: 'commutationAmount', title: 'Commutation Amount',"autoWidth": false},
                        {
						 'data': null,title:'<a href="#" onclick="addPension()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
						 'render': function (pensionData, type, row) {
            			   return '<a id="' + row.Id +'" href="#" onclick="editPension(\'' + pensionData.employeeId + '\')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deletePension(\'' + pensionData.employeeId + '\')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
			            	}
						}
                     ] 
                  });
      		   }
      	   });
      });   	                 
	
      function addPension(){
       	  var f = document.forms['editForm'];
   		  f.action="../Payroll/inputPension";
   		  f.submit();
   	  }

      function editPension(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputPension";
		  f.submit();
	  }
      
      function deletePension(id){
    	  if(confirm("Are you sure want to delete Pensioner Pension?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deletePension";
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
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/pensionMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Pension Details</h4>
				<div id="pensionListDiv" class="pensionListTableClass" style ="width:100%; margin-top: 25px">
					<table id="pensionListTable" class="table table-striped table-bordered table-responsive"></table>
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