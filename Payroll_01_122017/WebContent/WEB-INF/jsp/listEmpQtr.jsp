<!DOCTYPE html>
<html>
<head>
<title>Quarters Details</title>
<script type="text/javascript">
      function getQtrList() {
    	  $.ajax({
              url : '../Payroll/listEmpQtr',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  var qtrTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#qtrListDiv'));
                  $(data).each(function(i, empQtr){
                	  $('<tr/>').appendTo(qtrTab)
                	  		.append($('<td/>').text(empQtr.fullName))
                			.append($('<td/>').text(empQtr.afkQtr))
                			.append($('<td/>').append('<a href="#" onclick=viewQtr('+empQtr.empId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteQtr('+empQtr.empId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                	  
                  });
                  
              }
          });
      }
      function viewQtr(id){
    	  var f = document.forms[0];
		  f.empId.value=id;
		  f.action="../Payroll/inputEmpQtr";
		  f.submit();
	  }
      function inputQtr(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpQtr";
		  f.submit();
	  }
      function deleteQtr(id){
    	  if(confirm("Are you sure want to delete Employee Quarters?")){
    		  var f = document.forms[0];
    		  f.empId.value=id;
    		  f.action="../Payroll/deleteQtr";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getQtrList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Quarters Details</h4>
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
				<div class="tblClass" id="qtrListDiv">
				<table>
				<tr>
					<%-- <th>Department</th>
					<th>Designation</th>--%>
					<th>Employee</th>
					<th>QTR Alloted</th>
					<th><a href="#" onclick="inputQtr()" title="Add">
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
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>