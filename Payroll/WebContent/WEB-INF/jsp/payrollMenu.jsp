
<jsp:include page="../jsp/public/postHeader.jsp" />
<html>
<head>
<style type="text/css">

</style>
</head>
<body>
<div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div class="row">
				<div class="col-xs-4 text-center">
					<div class="precess-column">
					<a href="#" onclick="getList('../Payroll/generateBills')"><img src="../Payroll/resources/images/costhead1.png" class="img-responsive imageMenuItem" 
					alt="theme logo" /></a>
					<h3><a href="#" onclick="getList('../Payroll/generateBills')">Generate Paybills</a></h3>
					</div>
				</div>
				
				<%--<div class="col-xs-4 text-center">
					<div class="precess-column">
						<a href="../Payroll/viewLeave"><img src="../Payroll/resources/images/costhead.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
					<h3><a href="../Payroll/viewLeave">Employee Salary</a></h3>
					</div>
				</div> --%>
			</div>	
		</div>
	</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>