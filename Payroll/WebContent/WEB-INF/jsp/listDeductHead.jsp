<!DOCTYPE html>
<html>
<head>
<title>Deduct Head Details</title>
<script type="text/javascript">
      function getDeductHeadList() {
          $.ajax({
              url : '../Payroll/listDeductHead',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                  $(data).each(function(i, headInfo){
                	  var rowClass = (i % 2 ==0) ? "rTableRow" : "rTableRowEven";
                	  $("<div class='"+rowClass+"'></div>").appendTo($('#deductListDiv')).append($("<div class='rTableCell'></div>").text(headInfo.headName))
                	  .append($("<div class='rTableCell'></div>").text(headInfo.headValue))
                	  .append($("<div class='rTableCellLast'></div>").append('<a href="#" onclick=viewDeductHead('+headInfo.headId+','+headInfo.headName+','+headInfo.headValue+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDeductHead('+headInfo.headId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      }
      function viewDeductHead(id, name, value){
    	  var f = document.forms[0];
		  f.headId.value=id;
		  f.headName.value=name;
		  f.headValue.value=value;
		  f.action="../Payroll/inputDeductHead";
		  f.submit();
	  }
      function inputDeductHead(){
    	  var f = document.forms[0];
		  f.action="../Payroll/inputDeductHead";
		  f.submit();
	  }
      function deleteDeductHead(id){
    	  if(confirm("Are you sure want to delete Deduction-Head?")){
    		  var f = document.forms[0];
    		  f.headId.value=id;
    		  f.action="../Payroll/deleteDeductHead";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body onload="getDeductHeadList()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
	<div style="margin-top: 12px; float: left; width: 98%;">
			<h4 style="color: #0101DF;">Deduction Details</h4>
		<div>
			
			<div class="rTable" id="deductListDiv">
					<div class="rTableRowHead">
					<div class="rTableHead">Deduction-Head</div>
					<div class="rTableHead">Default Value</div>
					<div class="rHeadCellLast"><a href="#" onclick="inputDeductHead()" title="Add">
					<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a></div>
					</div>
				</div>
		</div>
	</div>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="headId" value="0">
		<input type="hidden" name="headName">
		<input type="hidden" name="headValue">
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>