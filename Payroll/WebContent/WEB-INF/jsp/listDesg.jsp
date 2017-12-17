<!DOCTYPE html>
<html>
<head>
<title>Designation Details</title>
<style type="text/css">
</style>
<script type="text/javascript">
      function getDeptList() {
          $.ajax({
              url : '../Payroll/listDesg',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                <%--  $(data).each(function(i, designation){
                	  var rowClass = (i % 2 ==0) ? "rTableRow" : "rTableRowEven";
                	  $("<div class='"+rowClass+"'></div>").appendTo($('#desgListDiv')).append($("<div class='rTableCell'></div>").text(designation.designationId))
                	  .append($("<div class='rTableCell'></div>").text(designation.designationName))
        			  .append($("<div class='rTableCellLast'></div>").append('<a href="#" onclick="viewDesgMethod('+designation.designationId+',&#34;'+designation.designationName+'&#34;)"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDesg('+designation.designationId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });--%>
                  var deptTab = $('<table/>').appendTo($('#desgListDiv'));
                  $(data).each(function(i, designation){
                	  $('<tr/>').appendTo(deptTab)
                	  		.append($('<td/>').text(designation.departmentName))
                	  		.append($('<td/>').text(designation.headName))
                			.append($('<td/>').text(designation.designationName))
                			.append($('<td/>').append('<a href="#" onclick=viewDesg('+designation.designationId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDesg('+designation.designationId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewDesg(id){
    	  var f = document.forms['editForm'];
    	  f.designationId.value=id;
		  f.designationName.value=name;
		  f.action="../Payroll/inputDesg";
		  f.submit();
	  }
      function inputDesg(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputDesg";
		  f.submit();
	  }
      function deleteDesg(id){
    	  if(confirm("Are you sure want to delete Designation?")){
    		  var f = document.forms['editForm'];
    		  f.designationId.value=id;
    		  f.action="../Payroll/deleteDesg";
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
			<h4 style="color: #0101DF;">Designation Details</h4>
		<div>
			
				<div class="tblClass" id="desgListDiv">
				<table>
				<tr>
					<th>Department</th>
					<th>Head</th>
					<th>Name</th>
					<th><a href="#" onclick="inputDesg()" title="Add">
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
		<input type="hidden" name="designationName">
		<input type="hidden" name="designationId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>