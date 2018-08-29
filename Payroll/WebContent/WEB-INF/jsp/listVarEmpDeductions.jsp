<!DOCTYPE html>
<html>
<head>
<title>EMP Fixed Deductions</title>
<script type="text/javascript">
	function getDeductionsList() {

		$.ajax({
					url : '../Payroll/listVarEmpDeductions',
					type : "GET",
					contentType : "application/json;charset=utf-8",
					success : function(data) {

						var fixedDeductionsTab = $('<table style="margin-bottom: 10px;"/>')
								.appendTo($('#deductDtlsListDiv'));
						$(data)
								.each(
										function(i, empDeductions) {
											$('<tr/>')
													.appendTo(fixedDeductionsTab)
													.append($('<td/>').text( empDeductions.fullName))
													.append($('<td/>').text(empDeductions.afkRent))
													.append($('<td/>').text(empDeductions.society.toFixed(2)))
													.append($('<td/>').text(empDeductions.pfLoanRecovery.toFixed(2)))
													.append($('<td/>').text(empDeductions.otherDeductions.toFixed(2)))
													.append($('<td/>').text(empDeductions.miscRecovery.toFixed(2)))
													.append($('<td/>').text(empDeductions.monthDate))
													.append($('<td/>').text(empDeductions.note))
													.append($('<td/>').append('<a href="#" onclick=addUpdateDeductions('+ empDeductions.employeeId + 
													')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteDeductions('
													+ empDeductions.employeeId
													+ ')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
										});
					}
				});
	}
	
	 /* function inputAllowance(){
   	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputEmpAlwnce";
		  f.submit();
	  } */
	 
	function addUpdateDeductions(id) {
		//alert("viewDeductions" + id);
		var f = document.forms['editForm'];
		f.action = "../Payroll/inputEmpVarDeductions";
		f.employeeId.value = id;
		f.submit();
	}
	function inputDeductDtls() {
		var f = document.forms['editForm'];
		f.action = "../Payroll/inputEmpVarDeductions";
		f.submit();
	}
	function deleteDeductions(id) {
		if (confirm("Are you sure want to delete Employee Deduction Details?")) {
			var f = document.forms['editForm'];
			f.employeeId.value = id;
			f.action = "../Payroll/addEmpVarDeductions";
			f.submit();
		}
	}
</script>
</head>
<body onload="getDeductionsList()">
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
				<h4 style="color: #0101DF;">Employee Variable Deductions</h4>
				<div>
					<div class="tblClass" id="deductDtlsListDiv">
						<table>
							<tr>
								<th>Employee</th>
								<th>AFK Rent</th>
								<th>Society</th>
								<th>PF Loan Recovery </th>
								<th>Other Deductions</th>
								<th>Misc Recovery</th>
								<th>Month Date</th>
								<th>Note</th>
								<th><a href="#" onclick="inputDeductDtls()" title="Add">
										<img src="../Payroll/resources/images/add.jpg" alt="Add"
										class="addImg" />
								</a></th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="employeeId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>