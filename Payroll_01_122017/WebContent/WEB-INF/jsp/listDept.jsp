<!DOCTYPE html>
<html>
<head>
<title>Department Details</title>
<style type="text/css">
	</style>
<script type="text/javascript">
      function getDeptList() {
          $.ajax({
              url : '../Payroll/listDept',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                  var deptTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#deptListDiv'));
                  $(data).each(function(i, department){
                	  $('<tr/>').appendTo(deptTab)
                	  		.append($('<td/>').text(department.departmentId))
                			  .append($('<td/>').text(department.departmantName))
                			  .append($('<td/>').append('<a href="#" onclick=viewDept('+department.departmentId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDept('+department.departmentId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
                  
                  <%-- var deptTab = $("<div class='rTableRow'></div>").appendTo($('#rTable'));
                  $(data).each(function(i, department){
                	  var rowClass = (i % 2 ==0) ? "rTableRow" : "rTableRowEven";
                	  $("<div class='"+rowClass+"'></div>").appendTo($('#deptListDiv')).append($("<div class='rTableCell'></div>").text(department.departmentId))
                	  .append($("<div class='rTableCell'></div>").text(department.departmantName))
        			  .append($("<div class='rTableCellLast'></div>").append('<a href="#" onclick=viewDept('+department.departmentId+',"'+department.departmantName+'")><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDept('+department.departmentId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });--%>
              }
          });
      }
      function viewDept(id){
    	  var f = document.forms['editForm'];
    	  f.departmentId.value=id;
		  f.action="../Payroll/inputDept";
		  f.submit();
	  }
      
      function inputDept(){
    	  var f = document.forms['editForm'];
    	  f.action="../Payroll/inputDept";
		  f.submit();
	  }
      
      function deleteDept(id){
    	  if(confirm("Are you sure to delete Department?")){
    		  var f = document.forms['editForm'];
    		  f.departmentId.value=id;
    		  f.action="../Payroll/deleteDept";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getDeptList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	 
	<%--<div class="wrapper">--%>
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Department Details</h4>
			<div>
				<%--<table class="tblClass">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th><a href="#" onclick="inputDept()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a></th>
					</tr>
				</table> --%>
				<div class="tblClass" id="deptListDiv">
				<table>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th><a href="#" onclick="inputDept()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>
					</th>
				</tr>
				</table>
				<%--<div class="rTable" id="deptListDiv">
					<div class="rTableRowHead">
					<div class="rTableHead">ID</div>
					<div class="rTableHead">Name</div>
					<div class="rHeadCellLast"><a href="#" onclick="inputDept()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a></div>
					</div> --%>
				</div>
			</div>
	</div>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="departmantName">
		<input type="hidden" name="departmentId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>