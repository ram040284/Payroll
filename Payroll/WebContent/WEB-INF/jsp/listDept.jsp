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

.deptListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.deptListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.deptListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.deptListTableClass th {
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
          url : '../Payroll/listDept',
          type:"GET",
          contentType: "application/json;charset=utf-8",
          success : function(deptData) {
         $('#deptListTable').DataTable({
        	 "scrollY": "300px",
                data: deptData,
                  columns: [
  					{ data: 'departmentId', title: 'ID',"autoWidth": false},
                  	{ data: 'departmantName', title: 'Name',"autoWidth": false},
                    { data: 'section', title: 'Section',"autoWidth": false},
                    { data: 'description', title: 'Description',"autoWidth": false},
                    {
					 'data': null,title:'<a href="#" onclick="inputDept()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
					 'render': function (deptData, type, row) {
        			   return '<a id="' + row.Id +'" href="#" onclick="viewDept('+deptData.departmentId+')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteDept('+deptData.departmentId+')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
		            	}
					}
                 ] 
              });
  		   }
  	   });
  });   	            
      function viewDept(id){
    	  var f = document.forms['editForm'];
    	  f.departmentId.value=id;
		  f.action="../Payroll/inputDept";
		  f.submit();
	  }
      
      function inputDept(){
    	  var f = document.forms['editForm'];
    	  f.action="../Payroll/inputDept";
		  f.submit();
	  }
      
      function deleteDept(id){
    	  if(confirm("Are you sure to delete Department?")){
    		  var f = document.forms['editForm'];
    		  f.departmentId.value=id;
    		  f.action="../Payroll/deleteDept";
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
					<h4 style="color: #0101DF;">Department Details</h4>
						<div id="deptListDiv" class="deptListTableClass" style ="width:100%; margin-top: 25px">
							<table id="deptListTable" class="table table-striped table-bordered table-responsive"></table>
						</div>
				</div>
			</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="departmantName">
		<input type="hidden" name="departmentId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>