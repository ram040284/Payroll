<!DOCTYPE html>
<html>
<head>
<title>Advance Details</title>
<script type="text/javascript">
      function getAdvanceList() {
          $.ajax({
              url : '../Payroll/listAdvance',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  <%--$(data).each(function(i, advance){
                	  var rowClass = (i % 2 ==0) ? "rTableRow" : "rTableRowEven";
                	  $("<div class='"+rowClass+"'></div>").appendTo($('#advListDiv')).append($("<div class='rTableCell'></div>").text(advance.departmentName))
                	  .append($("<div class='rTableCell'></div>").text(advance.designationName))
                	  .append($("<div class='rTableCell'></div>").text(advance.paymentDate))
                	  .append($("<div class='rTableCell'></div>").text(advance.advanceAmount))
        			  .append($("<div class='rTableCellLast'></div>").append('<a href="#" onclick=viewAdvance('+advance.advanceId+','+advance.designationId+','+advance.departmentId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteConveyance('+conveyance.conveyanceId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });--%>
                  var advTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#advListDiv'));
                  $(data).each(function(i, advance){
                	  $('<tr/>').appendTo(advTab)
                	  		.append($('<td/>').text(advance.departmentName))
                			.append($('<td/>').text(advance.designationName))
                			.append($('<td/>').text(advance.paymentDate))
                			.append($('<td/>').text(advance.advanceAmount))
                			.append($('<td/>').append('<a href="#" onclick=viewAdvance('+advance.empId+',\''+advance.paymentDate+'\')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteAdvance('+advance.empId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewAdvance(id, paymentDate){
    	  var f = document.forms['editForm'];
		  <%--f.designationId.value=designationId;
		  f.departmentId.value=departmentId;--%>
		  f.empId.value=id;
		  f.paymentDate.value = paymentDate;
		  f.action="../Payroll/inputAdvance";
		  f.submit();
	  }
      function inputAdvance(){
    	  var f = document.forms[0];
		  f.action="../Payroll/inputAdvance";
		  f.submit();
	  }
      function deleteAdvance(id){
    	  if(confirm("Are you sure want to delete Advance Amount?")){
    		  var f = document.forms[0];
    		  f.advanceId.value=id;
    		  f.action="../Payroll/deleteAdvance";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getAdvanceList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Advance Details</h4>
		<div>
			
				<div class="tblClass" id="advListDiv">
				<table>
				<tr>
					<th>Department</th>
					<th>Designation</th>
					<%--<th>Employee</th> --%>
					<th>Payment Date</th>
					<th>Advance Amount</th>
					<th><a href="#" onclick="inputAdvance()" title="Add">
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
		<input type="hidden" name="empId" value="0">
		<input type="hidden" name="paymentDate" value="0">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>