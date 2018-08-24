<!DOCTYPE html>
<html>
<head>
<title>LIC Master</title>
<script type="text/javascript">
      function getLicList() {
    	  $.ajax({
              url : '../Payroll/listEmpLicMaster',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  var licTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#licListDiv'));
                  $(data).each(function(i, empLic){
                	  $('<tr/>').appendTo(licTab)
                	  		.append($('<td/>').text(empLic.fullName))
                			.append($('<td/>').text(empLic.policyNo))
                			.append($('<td/>').text(empLic.instlmtAmt.toFixed(2)))
                			.append($('<td/>').append('<a href="#" onclick=viewLic('+empLic.employeeId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteLic('+empLic.employeeId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
                  
              }
          });
      }
      function viewLic(id){
    	  var f = document.forms['editForm'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputEmpLicMaster";
		  f.submit();
	  }
      function inputLic(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpLicMaster";
		  f.submit();
	  }
      function deleteLic(id){
    	  if(confirm("Are you sure want to delete Employee LIC?")){
    		  var f = document.forms['editForm'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteLicMaster";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getLicList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
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
			<h4 style="color: #0101DF;">LIC Master</h4>
		<div>
				<div class="tblClass" id="licListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>Policy No</th>
					<th>Installment Amount</th>
					<th><a href="#" onclick="inputLic()" title="Add">
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
		<input type="hidden" name="employeeId" value="0">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>