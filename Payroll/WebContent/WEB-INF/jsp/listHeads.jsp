<!DOCTYPE html>
<html>
<head>
<title>Head Details</title>
<script type="text/javascript">
      function getHeadsList() {
          $.ajax({
              url : '../Payroll/listHeads',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                  var payHeadTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#payHeadListDiv'));
                  $(data).each(function(i, headInfo){
                	  $('<tr/>').appendTo(payHeadTab)
                	  		.append($('<td/>').text(headInfo.departmentName))
                	  		.append($('<td/>').text(headInfo.headName))
                			.append($('<td/>').text(headInfo.description))
                			.append($('<td/>').append('<a href="#" onclick=viewHead('+headInfo.headId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteHead('+headInfo.headId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewHead(id){
    	  var f = document.forms['editForm'];
    	  f.headId.value=id;
		  f.action="../Payroll/inputHead";
		  f.submit();
	  }
      function inputHead(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputHead";
		  f.submit();
	  }
      function deleteHead(id){
    	  if(confirm("Are you sure want to delete Head details?")){
    		  var f = document.forms[0];
    		  f.headId.value=id;
    		  f.action="../Payroll/deleteHeadInfo";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getHeadsList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div style="margin-top: 12px; float: left; width: 98%;">
		<h4 style="color: #0101DF;">Head Details</h4>
		<div>
			<div class="tblClass" id="payHeadListDiv">
				<table>
				<tr>
					<th>Department</th>
					<th>Head Name</th>
					<th>Description</th>
					<th><a href="#" onclick="inputHead()" title="Add">
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
		<input type="hidden" name="headId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>