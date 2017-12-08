<!DOCTYPE html>
<html>
<head>
<title>Overtime Details</title>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	border: 1px solid #aaa;
}

th, td {
	text-align: left;
	padding: 8px;
	min-width: 220px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

th {
	background-color: #8B9DC3;
	color: white;
}
tr > th:last-of-type {
	background-color: #fff;
    text-align: center;
}
tr > td:last-of-type {
    text-align: center;
}
</style>
<script type="text/javascript">
      function getAdvanceList() {
          $.ajax({
              url : '../Payroll/listOvertime',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  $(data).each(function(i, overtime){
                	  var rowClass = (i % 2 ==0) ? "rTableRow" : "rTableRowEven";
                	  $("<div class='"+rowClass+"'></div>").appendTo($('#advListDiv')).append($("<div class='rTableCell'></div>").text(overtime.departmentName))
                	  .append($("<div class='rTableCell'></div>").text(overtime.designationName))
                	  .append($("<div class='rTableCell'></div>").text(overtime.employeeName))
                	  .append($("<div class='rTableCell'></div>").text(overtime.overtimeDate))
                	  .append($("<div class='rTableCell'></div>").text(overtime.overtimeAmount))
        			  .append($("<div class='rTableCellLast'></div>").append('<a href="#" onclick=viewAdvance('+overtime.empId+','+overtime.designationId+','+overtime.departmentId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteConveyance('+conveyance.conveyanceId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewOvertime(id, designationId, departmentId){
    	  var f = document.forms[0];
		  f.designationId.value=designationId;
		  f.departmentId.value=departmentId;
		  f.empId.value=id;
		  f.action="../Payroll/inputOvertime";
		  f.submit();
	  }
      function inputOvertime(){
    	  var f = document.forms[0];
		  f.action="../Payroll/inputOvertime";
		  f.submit();
	  }
      function deleteOvertime(id, overtimeDate){
    	  if(confirm("Are you sure want to delete Overtime Amount?")){
    		  var f = document.forms[0];
    		  f.advanceId.value=id;
    		  f.action="../Payroll/deleteOvertime";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getDeptList()">
	<jsp:include page="../public/postHeader.jsp" />
	<div class="wrapper">
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Overtime Details</h4>
		<div>
			
			<div class="rTable" id="otimeListDiv">
					<div class="rTableRowHead">
					<div class="rTableHead">Department</div>
					<div class="rTableHead">Designation</div>
					<div class="rTableHead">Employee</div>
					<div class="rTableHead">Overtime Date</div>
					<div class="rTableHead">Overtime Amount</div>
					<div class="rHeadCellLast"><a href="#" onclick="inputOvertime()" title="Add">
					<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a></div>
					</div>
				</div>
		</div>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="designationId" value="0">
		<input type="hidden" name="departmentId" value="0">
		<input type="hidden" name="empId" value="0">
		<input type="hidden" name="overtimeDate" value="0">
		<input type="hidden" name="overtimeAmount" value="0">
		
	</form>
	<jsp:include page="../public/postFooter.jsp" />
</body>
</html>