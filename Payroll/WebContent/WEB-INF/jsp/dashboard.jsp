 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../jsp/public/postHeader.jsp" />
<html>
<body>
<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<c:if test="${welcomeMsg == true}">
		<div class="row">
		<div class="col-md-12 text-center">
		<h3 style="color:blue;">Welcome ${sessionScope.user.employee.firstName} ${sessionScope.user.employee.lastName}!</h3>
		</div></div>
		</c:if>
		
			<div class="row">
				<div class="col-xs-4 text-center">
					<a href="../Payroll/mastersMenu"><img src="../Payroll/resources/images/Masters.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
					<h3><a href="../Payroll/mastersMenu">Masters</a></h3>
				</div>
				
				<div class="col-xs-4 text-center">
					<div class="precess-column">
						<a href="../Payroll/employeeMenu"><img src="../Payroll/resources/images/Employee.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
					<h3><a href="../Payroll/employeeMenu">Employee</a></h3>
					</div>
				</div>
				
				<div class="col-xs-4 text-center">
					<div class="precess-column">
						<a href="../Payroll/leaveMenu"><img src="../Payroll/resources/images/Leave.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h3><a href="../Payroll/leaveMenu">Leave</a></h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 text-center">
					<div class="precess-column">
						<a href="../Payroll/payrollMenu"><img src="../Payroll/resources/images/payroll-2.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h3><a href="../Payroll/payrollMenu">Payroll</a></h3>
					</div>
				</div>
				<div class="col-xs-4 text-center">
					<div class="precess-column">
						<a href="#"><img src="../Payroll/resources/images/Pension.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h3><a href="#">Pension</a></h3>
					</div>
				</div>
				<div class="col-xs-4 text-center">
					<div class="precess-column">
						<a href="../Payroll/reportsMenu"><img src="../Payroll/resources/images/Reports.png" class="img-responsive imageMenuItem"
					alt="theme logo" /></a>
						<h3><a href="../Payroll/reportsMenu">Reports</a></h3>
					</div>
				</div>
			</div>
			</div>
	</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>