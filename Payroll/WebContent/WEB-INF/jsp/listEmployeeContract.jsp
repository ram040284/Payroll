<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Contract Details</title>
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

.employeeContractListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.employeeContractListTableClass th, td {
	text-align: left;
	padding: 5px;
}

.employeeContractListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.employeeContractListTableClass th {
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
        url : '../Payroll/employeeContractList',
        type:"GET",
        contentType: "application/json;charset=utf-8",
        success : function(empContractData) {
      	  $('#empListTable').DataTable({
      		  columnDefs: [
        		    { className: 'text-right', targets: [] },
        		  ],
      		  data:empContractData,
      		  columns:[
      			  {data:'fullName',title:'Employee'},
      			  {data:'appointmentDate',title:'Appointment Date'},
      			  {data:'endDate',title:'End Date'},
      			  {data:'engagementLetterId',title:'Engagement Letter Id'},            			 
      			  {
      				     'data': null, title:'<a href="#" onclick="addContractualEmployee()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
                           ' width' : '150px',
                           'render': function (addContractualEmployee, type, row)
      				     {
                         return '<a id= "' +row.Id +'" href="#" onclick="updateEmpContract(\'' + addContractualEmployee.employeeId + '\')"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/> <a id="' +row.Id+'"  href="#" onclick="deleteEmpContract(\'' + addContractualEmployee.employeeId + '\')"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>'}
      			}
      			  
      		]	  
      	  });
        }
	});
});

function updateEmpContract(id){
	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/viewEmpContract";
		  f.submit();
	  }
  function addContractualEmployee(){
	  var f = document.forms['editForm'];
		  f.action="../Payroll/viewEmpContract";
		  f.submit();
	  }
  function deleteEmpContract(id){
	  if(confirm("Are you sure want to delete Contractual Empllyee?")){
		  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/deleteContractEmp";
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
			<div style="margin-top: 12px; float: left; width: 98%; margin-left: 15px;">
				<h4 style="color: #0101DF;">Employee Contract Details</h4>
				<form method="post" name="editForm" action="">
					 <input type="hidden" name="employeeId" value="0">
				</form>
				<div id="employeeContractListDiv" class="employeeContractListTableClass" style ="width:100%;">
					<table id="empListTable" class="table table-striped table-bordered table-responsive"></table>
				</div>
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