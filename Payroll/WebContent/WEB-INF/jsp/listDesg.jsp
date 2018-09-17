<!DOCTYPE html>
<html>
<head>
<title>Designation Details</title>
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

.desgListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.desgListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.desgListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.desgListTableClass th {
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
          url : '../Payroll/listDesg',
          type:"GET",
          contentType: "application/json;charset=utf-8",
          success : function(desgData) {
         $('#desgListTable').DataTable({
        	 "scrollY": "300px",
                data: desgData,
                  columns: [
                  	{ data: 'departmentName', title: 'Department',"autoWidth": false},
                    { data: 'headName', title: 'Head',"autoWidth": false},
                    { data: 'designationName', title: '	Name',"autoWidth": false},
                    {
					 'data': null,title:'<a href="#" onclick="inputDesg()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
					 'render': function (desgData, type, row) {
        			   return '<a id="' + row.Id +'" href="#" onclick="viewDesg('+desgData.designationId+')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteDesg('+desgData.designationId+')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
		            	}
					}
                 ] 
              });
  		   }
  	   });
  });   	            
      function viewDesg(id){
    	  var f = document.forms['editForm'];
    	  f.designationId.value=id;
		  f.designationName.value=name;
		  f.action="../Payroll/inputDesg";
		  f.submit();
	  }
      function inputDesg(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputDesg";
		  f.submit();
	  }
      function deleteDesg(id){
    	  if(confirm("Are you sure want to delete Designation?")){
    		  var f = document.forms['editForm'];
    		  f.designationId.value=id;
    		  f.action="../Payroll/deleteDesg";
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
			<h4 style="color: #0101DF;">Designation Details</h4>
			<div id="desgListDiv" class="desgListTable" style ="width:100%; margin-top: 25px">
							<table id="desgListTable" class="table table-striped table-bordered table-responsive"></table>
						</div>
				</div>
			</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="designationName">
		<input type="hidden" name="designationId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>