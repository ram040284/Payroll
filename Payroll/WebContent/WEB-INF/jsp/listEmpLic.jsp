<!DOCTYPE html>
<html>
<head>
<title>LIC Details</title>
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

.licTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.licTableClass th, td {
	text-align: left;
	padding: 5px;
}

.licTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.licTableClass th {
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
              url : '../Payroll/listEmpLic',
              type:"GET",
              contentType: "application/json;charset=utf-8",
             
              success : function(employeeLicData){
              $('#licTable').DataTable({
           		  data:employeeLicData,
           		  columns:[
                      {data:'fullName',title:'Employee'},
           			  {data:'policyNo',title:'Policy No'},
           			  {data:'paymentDate',"render": function (data) 
           			  {
           		        var date = new Date(data);
           		        var month = date.getMonth() + 1;
           		        return (month.length > 1 ? month :  + month) + "/" + date.getDate() + "/" + date.getFullYear();
           		      }, title:'Payment Date'},
           			  {data:'paymentAmount',title:'Payment Amount'},
                      {
            		   'data': null, title:'<a href="#" onclick="addLic()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
                       'render': function (employeeLicData, type, row)
           				{
                         return '<a id= "' +row.Id +'" href="#" onclick="updateLic('+employeeLicData.employeeId+', '+employeeLicData.policyNo+')"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/> <a id="' +row.Id+'"  href="#" onclick="deleteLic('+employeeLicData.employeeId+', '+employeeLicData.policyNo+')"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>'}
     			        }
            			  
                      ]	  
                  });
              }
     	  });
     });
       function updateLic(id,policyNo){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.policyNo.value=policyNo;
		  f.action="../Payroll/inputEmpLic";
		  f.submit();
	  }
      function addLic(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpLic";
		  f.submit();
	  }
      function deleteLic(id,policyNo){
    	  if(confirm("Are you sure want to delete Employee LIC?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.policyNo.value=policyNo;
    		  alert("delete");
    		  f.action="../Payroll/deleteLic";
    		  f.submit();
    	  }
      }
      </script>
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
      		
      	 <div style="margin-top: 12px; float: left; width: 98%;">
      				<h4 style="color: #0101DF;">LIC Details</h4>
      		<form method="post" name="editForm" action="">
      		<input type="hidden" name="employeeId" value="0">
      		<input type="hidden" name="policyNo" value="0">      		      					
      		</form>
      	<div id="licListDiv" class="licTableClass" style ="width:100%;">
      			<table id="licTable" class="table table-striped table-bordered table-responsive"></table>
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