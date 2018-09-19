<!DOCTYPE html>
<html>
<head>
<title>Employee Advance List</title>
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

.advanceListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.advanceListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.advanceListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.advanceListTableClass th {
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

<!--      /*  function getadvanceList() {
    	  $.ajax({
              url : '../Payroll/listAdvances',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
               var advanceTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#advanceListDiv'));
                  $(data).each(function(i, advance){
                	  $('<tr/>').appendTo(advanceTab)
                			.append($('<td/>').text(advance.fullName))
                			.append($('<td/>').text(advance.advanceId))
     			            .append($('<td/>').text(advance.advanceName))
                	  		.append($('<td/>').text(advance.advanceDate))
                	  		.append($('<td/>').text(advance.advanceAmount))
                			.append($('<td/>').text(advance.installAmount))
                			.append($('<td/>').text(advance.installStartDate))
                			.append($('<td/>').append('<a href="#" onclick=viewadvance('+advance.advanceId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteadvance('+advance.advanceId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });   
              }
          });
      } */ -->

      <script type="text/javascript">
      $(document).ready(function(){
	  $.ajax({
      url : '../Payroll/listAdvances',
      type:"GET",
      contentType: "application/json;charset=utf-8",
      success : function(advanceData) {
     $('#advanceListTable').DataTable({
    	 "scrollY": "300px",
            data: advanceData,
              columns: [
				{ data: 'fullName', title: 'Employee',"autoWidth": false},
              	{ data: 'advanceId', title: 'Advance Id',"autoWidth": false},
                { data: 'advanceName', title: 'Advance Name',"autoWidth": false},
                { data: 'advanceDate', title: 'Advance Date',"autoWidth": false},
                { data: 'advanceAmount', title: 'Advance Amount',"autoWidth": false},
              	{ data: 'installAmount', title: 'Installment Amount',"autoWidth": false},
                { data: 'installStartDate', title: 'Installment Start Date',"autoWidth": false},
                {
				 'data': null,title:'<a href="#" onclick="inputOrtime()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
				 'render': function (advanceData, type, row) {
    			   return '<a id="' + row.Id +'" href="#" onclick="viewadvance('+advanceData.advanceId+')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteadvance('+advanceData.advanceId+')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
	            	}
				}
             ] 
          });
		   }
	   });
});
      
      function viewadvance(id){
    	  var f = document.forms['editForm'];
		  f.advanceId.value=id;
		  f.action="../Payroll/inputAdvance";
		  f.submit();
	  }
      function inputOrtime(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputAdvance";
		  f.submit();
	  }
      function deleteadvance(id, advanceDate){
    	  if(confirm("Are you sure want to delete advance Amount?")){
    		  var f = document.forms['editForm'];
    		  f.advanceId.value=id;
    		  f.action="../Payroll/deleteAdvance";
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
				<h4 style="color: #0101DF;">Employee Advance List</h4>
					<div id="advanceListDiv" class="advanceListTableClass" style ="width:100%; margin-top: 25px">
							<table id="advanceListTable" class="table table-striped table-bordered table-responsive"></table>
					</div>
			</div>
		</div>
	</div>
			
	<form action="" name="editForm" method="post">
		<input type="hidden" name="advanceId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>