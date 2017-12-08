<!DOCTYPE html>
<html>
<head>
<title>Conveyance  Details</title>
<script type="text/javascript">
      function getDeptList() {
          $.ajax({
              url : '../Payroll/listConveyance',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                 <%-- $(data).each(function(i, conveyance){
                	  var rowClass = (i % 2 ==0) ? "rTableRow" : "rTableRowEven";
                	  $("<div class='"+rowClass+"'></div>").appendTo($('#desgListDiv')).append($("<div class='rTableCell'></div>").text(conveyance.designationName))
                	  .append($("<div class='rTableCell'></div>").text(conveyance.conveyanceAmount))
        			  .append($("<div class='rTableCellLast'></div>").append('<a href="#" onclick=viewConveyance('+conveyance.conveyanceId+',"'+conveyance.designationId+'")><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteConveyance('+conveyance.conveyanceId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });--%>
                  var convyTab = $('<table/>').appendTo($('#convyListDiv'));
                  $(data).each(function(i, designation){
                	  $('<tr/>').appendTo(convyTab)
                	  			.append($('<td/>').text(conveyance.designationName))
                			  .append($('<td/>').text(conveyance.conveyanceAmount))
                			  .append($('<td/>').append('<a href="#" onclick=viewConveyance('+conveyance.conveyanceId+',"'+conveyance.designationId+'")><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteConveyance('+conveyance.conveyanceId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewConveyance(id, designationId){
    	  var f = document.forms[0];
		  f.designationId.value=designationId;
		  f.conveyanceId.value=id;
		  f.action="../Payroll/inputConveyance";
		  f.submit();
	  }
      function inputConveyance(){
    	  var f = document.forms[0];
		  f.action="../Payroll/inputConveyance";
		  f.submit();
	  }
      function deleteConveyance(id){
    	  if(confirm("Are you sure want to delete Conveyance Allowance?")){
    		  var f = document.forms[0];
    		  f.conveyanceId.value=id;
    		  f.action="../Payroll/deleteConveyance";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getDeptList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Conveyance Allowance Details</h4>
		<div>
			
				<%--<div class="rTable" id="desgListDiv">
					<div class="rTableRowHead">
					<div class="rTableHead">Designation</div>
					<div class="rTableHead">Conveyance Amount</div>
					<div class="rHeadCellLast"><a href="#" onclick="inputConveyance()" title="Add">
					<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a></div>
					</div>
				</div> --%>
				<div class="tblClass" id="convyListDiv">
				<table>
				<tr>
					<th>Designation</th>
					<th>Conveyance Amount</th>
					<th><a href="#" onclick="inputConveyance()" title="Add">
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
		<input type="hidden" name="conveyanceId" value="0">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>