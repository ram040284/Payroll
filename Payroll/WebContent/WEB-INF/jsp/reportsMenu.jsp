
<jsp:include page="../jsp/public/postHeader.jsp" />
<html>
<head>
<style type="text/css">
.imageMenuItem {
	height: 120px;
	width: 120px;
}
</style>
</head>
<body>
<div class="contain-wrapp bodyDivCss">	
		<div class="container">

			<div class="row">
				<div class="col-md-3 text-center">
					<div class="precess-column">
					<a href="../Payroll/employeeSearch"><img src="../Payroll/resources/images/emp1.png" class="img-responsive imageMenuItem" 
					alt="theme logo" /></a>
					<h4><a href="../Payroll/employeeSearch">Employee Details</a></h4>
					</div>
				</div>
				
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="../Payroll/empSalarySearch"><img src="../Payroll/resources/images/costhead1.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
					<h4><a href="../Payroll/empAllowanceSearch">Employee Allowances</a></h4>
					</div>
				</div>
				
			</div>
			
		</div>
	</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>