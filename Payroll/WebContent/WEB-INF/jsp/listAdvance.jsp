<!DOCTYPE html>
<html>
<head>
<title>Employee Advance List</title>

<script type="text/javascript">
      function getadvanceList() {
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
      }
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
<body onload="getadvanceList()">
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
			</div>
	<div class="container">	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Employee Advance List</h4>
		<div>
			
				<div class="tblClass" id="advanceListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>Advance Id</th>
					<th>Advance Name</th>
					<th>Advance Date</th>
					<th>Advance Amount</th>
					<th>Installment Amount</th>
					<th>Installment Start Date</th>
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
		<input type="hidden" name="advanceId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>