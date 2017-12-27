
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
<div class="contain-wrapp" style="padding-top:180px;">	
		<div class="container">

			<div class="row">
				<div class="col-md-3 text-center">
					<div class="precess-column">
					<a href="#" onclick="getList('../Payroll/employee')"><img src="../Payroll/resources/images/emp1.png" class="img-responsive imageMenuItem" 
					alt="theme logo" /></a>
					<h4><a href="#" onclick="getList('../Payroll/employee')">Employee Details</a></h4>
					</div>
				</div>
				
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="../Payroll/viewBank"><img src="../Payroll/resources/images/empBank.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
					<h4><a href="../Payroll/viewBank">Employee Bank</a></h4>
					</div>
				</div>
				
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="../Payroll/viewSalary"><img src="../Payroll/resources/images/costhead1.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h4><a href="../Payroll/viewSalary">Employee Salary</a></h4>
					</div>
				</div>
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="#" onclick="getList('../Payroll/viewLeave')"><img src="../Payroll/resources/images/empLeave.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h4><a href="#" onclick="getList('../Payroll/viewLeave')">Employee Leave</a></h4>
					</div>
				</div>
			</div>
			
			<div class="row" style="padding-top:40px;">
				
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="../Payroll/viewEmpPf"><img src="../Payroll/resources/images/epfo.jpg" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h4><a href="../Payroll/viewEmpPf">Provident Fund</a></h4>
					</div>
				</div>
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="../Payroll/viewEmpQtr"><img src="../Payroll/resources/images/empQuarters.jpeg" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h4><a href="../Payroll/viewEmpQtr">Quarter Details</a></h4>
					</div>
				</div>
				<div class="col-md-3 text-center">
					<div class="precess-column">
						<a href="../Payroll/viewEmpLic"><img src="../Payroll/resources/images/lic.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h4><a href="../Payroll/viewEmpLic">LIC Details</a></h4>
					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>