<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Arrears</title>
<script type="text/javascript">
	function getEmployeeArrersDetails() {
		  $.ajax({
	        url : '../Payroll/listArrears',
	        type:"GET",
	        contentType: "application/json;charset=utf-8",
	        success : function(data) {
	            var arrearsTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#arrearsListDiv'));
	            $(data).each(function(i, arrears){
	          	  $('<tr/>').appendTo(arrearsTab)
	          	  		.append($('<td/>').text(arrears.fullName))
	          	  		.append($('<td/>').text(arrears.arrearsType))
	          	  		.append($('<td/>').text(arrears.arrearsPay))
	          	  		.append($('<td/>').text(arrears.arrearsDeductions))
	          	  		.append($('<td/>').text(arrears.miscPay))
	          	  		.append($('<td/>').text(arrears.miscDeductions))
	          	  		.append($('<td/>').text(arrears.arrearsPayNote))
	          	  		.append($('<td/>').text(arrears.arrearsDeductionNote))
	          			.append($('<td/>').append('<a href="#" onclick="viewArrear('+arrears.arrearId+')"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteArrear('+arrears.arrearId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
	            });
	        }
	    });
	}
	function viewArrear(id){
		  var f = document.forms['editForm'];
		  f.arrearId.value=id;
		  f.action="../Payroll/inputArrear";
		  f.submit();
	}
	function createArrears(){
		  var f = document.forms['editForm'];
		  f.action="../Payroll/inputArrear";
		  f.submit();
	}
	function deleteArrear(id){
		  if(confirm("Are you sure want to delete Employee Arrears Pay?")){
			  var f = document.forms['editForm'];
			  f.arrearId.value=id;
			  f.action="../Payroll/deleteArrears";
			  f.submit();
		  }
}
</script>
</head>
<body onload="getEmployeeArrersDetails()">
	<jsp:include page="../jsp/public/postHeader.jsp" />
	<div class="contain-wrapp bodyDivCss">	
			<div class="container" style="margin-top: 85px;">  
				<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 33px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>	
			<div style="margin-top: 12px; float: left; width: 98%; margin-left: 15px;">
				<h4 style="color: #0101DF;">Arrears Pay Details</h4>
				<div>
					
				<div class="tblClass" id="arrearsListDiv">
				<table>
				<tr>
					<th>Employee Name</th>
					<th>Arrears Type </th>
					<th>Arrears Pay</th>
					<th>Arrears Deductions</th>
					<th>Misc Pay</th>
					<th>Misc Deductions</th>
					<th>Arrears Note</th>
					<th>Arrears Deductions</th>
					<th><a href="#" onclick="createArrears()" title="Add">
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
		<input type="hidden" name="arrearId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>