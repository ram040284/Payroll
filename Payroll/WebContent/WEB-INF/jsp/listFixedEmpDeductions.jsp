<!DOCTYPE html>
<html>
<head>
<title>EMP Fixed Deductions</title>
<script type="text/javascript">
	function getDeductionsList() {

		$
				.ajax({
					url : '../Payroll/listFixedEmpDeductions',
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
													.append($('<td/>').text(empDeductions.kssUnionFee.toFixed(2)))
													.append($('<td/>').text(empDeductions.rent.toFixed(2)))
													.append($('<td/>').text(empDeductions.courtRecovery.toFixed(2)))
													.append($('<td/>').text(empDeductions.unionFee.toFixed(2)))
													.append($('<td/>').text(empDeductions.gis.toFixed(2)))
													.append($('<td/>').text(empDeductions.additionalPF.toFixed(2)))
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
		f.action = "../Payroll/inputEmpFixedDeductions";
		f.employeeId.value = id;
		f.submit();
	}
	function inputDeductDtls() {
		var f = document.forms['editForm'];
		f.action = "../Payroll/inputEmpFixedDeductions";
		f.submit();
	}
	function deleteDeductions(id) {
		if (confirm("Are you sure want to delete Employee Deduction Details?")) {
			var f = document.forms['editForm'];
			f.employeeId.value = id;
			f.action = "../Payroll/addEmpFxDeductions";
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
				<h4 style="color: #0101DF;">Employee Fixed Deductions</h4>
				<div>
					<div class="tblClass" id="deductDtlsListDiv">
						<table>
							<tr>
								<th>Employee</th>
								<th>KSS Union Fee</th>
								<th>Rent</th>
								<th>Court Recovery</th>
								<th>Union Fee</th>
								<th>GIS</th>
								<th>Additional PF</th>
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