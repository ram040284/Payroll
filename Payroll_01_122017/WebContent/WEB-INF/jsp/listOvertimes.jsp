<!DOCTYPE html>
<html>
<head>
<title>Overtime Details</title>

<script type="text/javascript">
      function getOvertimeList() {
    	  $.ajax({
              url : '../Payroll/listOvertimes',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
               var otimeTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#otimeListDiv'));
                  $(data).each(function(i, overtime){
                	  $('<tr/>').appendTo(otimeTab)
                	  		.append($('<td/>').text(overtime.departmentName))
                	  		.append($('<td/>').text(overtime.designationName))
                			.append($('<td/>').text(overtime.fullName))
                			.append($('<td/>').text(overtime.overtimeDate))
                			.append($('<td/>').text(overtime.overtimeAmount))
                			.append($('<td/>').append('<a href="#" onclick=viewOvertime('+overtime.empId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteOvertime('+overtime.empId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
                  
              }
          });
      }
      function viewOvertime(id){
    	  var f = document.forms['editForm'];
		  <%--f.designationId.value=designationId;
		  f.departmentId.value=departmentId;--%>
		  f.empId.value=id;
		  f.action="../Payroll/inputOvertime";
		  f.submit();
	  }
      function inputOrtime(){
    	  var f = document.forms['editForm'];
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
<body onload="getOvertimeList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Overtime Details</h4>
		<div>
			
				<%--<div class="rTable" id="otimeListDiv">
					<div class="rTableRowHead">
					<div class="rTableHead">Department</div>
					<div class="rTableHead">Designation</div>
					<div class="rTableHead">Employee</div>
					<div class="rTableHead">Overtime Date</div>
					<div class="rTableHead">Overtime Amount</div>
					<div class="rHeadCellLast"><a href="#" onclick="inputOrtime()" title="Add">
					<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a></div>
					</div>
				</div>--%>
				<div class="tblClass" id="otimeListDiv">
				<table>
				<tr>
					<th>Department</th>
					<th>Designation</th>
					<th>Employee</th>
					<th>Overtime Date</th>
					<th>Overtime Amount</th>
					<th><a href="#" onclick="inputOrtime()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>
					</th>
				</tr>
				</table>
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
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>