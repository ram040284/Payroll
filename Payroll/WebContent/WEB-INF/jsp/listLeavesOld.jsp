<!DOCTYPE html>
<html>
<head>
<title>Leave Details</title>
<script type="text/javascript">
      function getLeavesList() {
    	  $.ajax({
              url : '../Payroll/listLeaves',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                 var leaveTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#leaveListDiv'));
                  $(data).each(function(i, leave){
                	  $('<tr/>').appendTo(leaveTab)
                	  		.append($('<td/>').text(leave.fullName))
                			.append($('<td/>').text(leave.leaveType))
                			<%--.append($('<td/>').text(leave.noOfLeaves))--%>
                			.append($('<td/>').text(leave.leaveBalance))
                			.append($('<td/>').append('<a href="#" onclick=viewLeave('+leave.leaveId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteLeave('+leave.leaveId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                	  
                  });
                  
              }
          });
      }
      function viewLeave(leaveId){
    	  var f = document.forms[0];
		  f.leaveId.value=leaveId;
		  f.action="../Payroll/inputLeave";
		  f.submit();
	  }
      function inputLeave(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputLeave";
		  f.submit();
	  }
      function deleteLeave(id){
    	  if(confirm("Are you sure want to delete Employee Leave?")){
    		  var f = document.forms[0];
    		  f.leaveId.value=id;
    		  f.action="../Payroll/deleteLeave";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getLeavesList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Leave Details</h4>
		<div>
			
				<div class="tblClass" id="leaveListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>Leave Type</th>
				<%--	<th>No. Of Leave</th> --%>
					<th>Leave Balance</th>
					<th><a href="#" onclick="inputLeave()" title="Add">
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
		<%--<input type="hidden" name="designationId" value="0">
		<input type="hidden" name="departmentId" value="0"> --%>
		<input type="hidden" name="empId" value="0">
		<input type="hidden" name="leaveId" value="0">
		
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>