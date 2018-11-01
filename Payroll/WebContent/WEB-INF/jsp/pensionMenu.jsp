
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
						<a href="../Payroll/viewPension"><img
							src="../Payroll/resources/images/costhead1.png"
							class="img-responsive imageMenuItem" alt="theme logo" /></a>
						<h3>
							<a href="../Payroll/viewPension">Employee Pension</a>
						</h3>
					</div>
				</div>
				<div class="col-xs-4 text-center">
					<div class="precess-column">
					<a href="#" onclick="backNav('../Payroll/generatePensionPaybills')"><img src="../Payroll/resources/images/costhead1.png" class="img-responsive imageMenuItem" 
					alt="theme logo" /></a>
					<h3><a href="#"onclick="backNav('../Payroll/generatePensionPaybills')">Pension Paybills</a></h3>
					</div>
				</div>
			</div>	
		</div>
	</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>