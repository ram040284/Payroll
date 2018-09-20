<!DOCTYPE html>
<html>
<head>
<title>Overtime Details</title>
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

.overtimeListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.overtimeListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.overtimeListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.overtimeListTableClass th {
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
              url : '../Payroll/listOvertimes',
              type:"GET",
              contentType: "application/json;charset=utf-8",
                              
                      success : function(overtimeData) {
	                  $('#overtimeListTable').DataTable({
	                	      data: overtimeData,
	                       columns: [
	       				      { data: 'fullName', title: 'Employee',"autoWidth": true},
	                       	  { data: 'overtimeOrder', title: 'Overtime Order',"autoWidth": true},
	                           { data: 'overtimeDate', title: 'Overtime Date',"autoWidth": true},
	                           { data: 'overtimeHours', title: 'Overtime Hours',"autoWidth": true},
	                           { data: 'overtimeAmount', title: 'Overtime Amount',"autoWidth": true},
	                           { data: 'overtimeDayHours', title: 'Office Day Hours',"autoWidth": true},
	                           {
	 								 'data': null,title:'<a href="#" onclick="addOvertime()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
	 								 'render': function (overtimeData, type, row) {
	 				    
	 				               return '<a href="#" onclick=UpdateOvertime('+overtimeData.overtimeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteOvertime('+overtimeData.overtimeId+ ')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'
	 								 }
	 					       }
	                       ]
                       });
                                }
          });
      });
      function UpdateOvertime(id){
    	  var f = document.forms['editForm'];
    	  f.overtimeId.value=id;
		  f.action="../Payroll/inputOvertime";
		  f.submit();
	  }
      function addOvertime(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputOvertime";
		  f.submit();
	  }
      function deleteOvertime(id, overtimeDate){
    	  if(confirm("Are you sure want to delete Overtime Amount?")){
    		  var f = document.forms['editForm'];
    		  f.overtimeId.value=id;
    		  f.action="../Payroll/deleteOvertime";
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

				<h4 style="color: #0101DF;">Overtime Details</h4>	
					<div id="otimeListDiv" class="overtimeListTableClass" style ="width:100%; margin-top: 25px">
						<table id="overtimeListTable" class="table table-striped table-bordered table-responsive"></table>
 			</div>
 			</div>
			</div>
	</div>
	
	<form action="" name="editForm" method="post">
		<input type="hidden" name="overtimeId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>