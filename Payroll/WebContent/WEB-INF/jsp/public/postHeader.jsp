<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
   <link rel="icon" href="../Payroll/resources/images/favicon.png">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/bootstrap.min.css">
<%--<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/style.css">--%>
 <link href="../Payroll/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
 <link href="../Payroll/resources/css/jssor.css" rel="stylesheet">
 <link href="../Payroll/resources/css/style_new.css" rel="stylesheet">
 <link id="skin" href="../Payroll/resources/css/default.css" rel="stylesheet">
 <%--<jsp:include page="jqueryMin2.0.3.jsp" />
 <jsp:include page="simpleExpand.jsp" />
<jsp:include page="bootstrap.min.jsp" /> --%>
 <style type="text/css">
.addImg {
	width: 25px;
	height: 25px;
}
.listImg {
	width: 25px;
	height: 25px;
	margin-right: 10px;
}

.tblClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	border: 1px solid #aaa;
}

.tblClass th, td {
	text-align: left;
	padding: 8px;
	min-width: 126px;
}

.tblClass tr:nth-child(even) {
	background-color: #f2f2f2;
}

.tblClass th {
	background-color: #8B9DC3;
	color: white;
}
.tblClass tr > th:last-of-type {
	background-color: #fff;
    text-align: center;
}
.tblClass tr > td:last-of-type {
    text-align: center;
}

@media only screen and (min-width: 1200px) and (max-width: 1600px){
.formDiv{
	margin-top: 0px; float: left; width: 70%; border: 1px solid #8B9DC3; margin-bottom: 10px;
}
.imageMenuItem {
	height: 80px;
	width: 80px;
}
h3 {font-size:16pt;}
}
@media only screen and (min-width: 768px) and (max-width: 1199px)  {
  .formDiv{
	margin-top: 0px; float: left; width: 100%; border: 1px solid #8B9DC3; margin-bottom: 10px;
  }
  .imageMenuItem {
	height: 60px;
	width: 60px;
  }
  h3 {font-size:13pt; line-height:20px;margin-top:10px;}
}
@media only screen and (min-width: 320px)and (max-width: 767px) {
 .formDiv{
	margin-top: 0px; float: left; border: 1px solid #8B9DC3; margin-bottom: 10px;width:100%;
 } 
 .imageMenuItem {
	height: 50px;
	width: 50px;
 }
 h3 {font-size:11pt;line-height:20px;margin-top:6px;}
}
.formDiv button {
	color: #fff; 
	background-color: #8B9DC3;
	border-radius:5px;
	margin-right: 5px;
	
}
.formDiv button:HOVER {
	color: #fff;
	border-radius:5px;
	background-color: #3b589a;;
}

.bodyDivCss {
	position:relative;
	padding-bottom: 50px;
	margin-top: 250px;	
}
.errMsg {
	display: none;
	font-size: 1.2em;
	color: red;
}
ul a { 
    cursor: pointer;
}
.active { 
    background-color:#E0E2EE !important;
    color:#3b589a !important;
}

 </style>

</head>
<body>
<div id="loading" class="loading-invisible">
		<div class="loading-center">
			<div class="loading-center-absolute">
				<div class="object" id="object_one"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_three"></div>
				<div class="object" id="object_four"></div>
				<div class="object" id="object_five"></div>
				<div class="object" id="object_six"></div>
				<div class="object" id="object_seven"></div>
				<div class="object" id="object_eight"></div>
				<div class="object" id="object_big"></div>
			</div>
			<p>Please wait...</p>
		</div>
	</div>
	<script type="text/javascript">
		  document.getElementById("loading").className = "loading-visible";
		  var hideDiv = function(){document.getElementById("loading").className = "loading-invisible";};
		  var oldLoad = window.onload;
		  var newLoad = oldLoad ? function(){hideDiv.call(this);oldLoad.call(this);} : hideDiv;
		  window.onload = newLoad;
	</script>

<!-- Start Navbar -->
	<nav class="navbar yamm navbar-dark navbar-fixed-top" style="position: fixed;">
		<div class="wrapper" style="background:#e0e2ee;">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="../Payroll/dashboard"><img src="../Payroll/resources/images/logo-02.png" class="img-responsive"
					alt="theme logo" /></a>
				</div>
				<!-- Start Form Search -->
			</div>
		</div>
		<%--<c:if test="${not empty sessionScope.user}"> --%>
		<div class="wrapper" style="background:#3b589a;">
		<div class="container">
			<!-- End Form Search -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" style="float: left;">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="navlink-right">
						<div class="dropdown shopping-cart">
							<button class="btn-navlink" type="button" data-toggle="dropdown" id="logoutBtn" onclick="logoutEvent();">
								<i class="fa fa-sign-out fa-4" style="color:#ffff; float:right;font-size:30px;padding:5px;"></i>
							</button>
						</div>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
				<c:if test="${isCEO eq false}">
					<li><a href="../Payroll/dashboard">Home</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Masters <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/viewDept"> Department</a></li>
				            <li><a href="../Payroll/viewHeads">Budget Heads</a></li>
				            <li><a href="../Payroll/viewDesg">Designation</a></li>
				            <li><a href="../Payroll/viewBankDetails">Bank Details</a></li>
				            <li><a href="../Payroll/viewTaxSlab">Incometax Slabs </a></li>
				            <li><a href="../Payroll/usersList">User </a></li>	
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Employee <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/employee">Employee Master</a></li>
							<li><a href="../Payroll/viewBank">Employee Bank</a></li>
							<li><a href="../Payroll/viewSalary">Employee Salary</a></li>
							<li><a href="../Payroll/viewLeave_1">Employee Leave</a></li>
							<li><a href="../Payroll/viewAdvance">Employee Festival Advance</a></li>
							<li><a href="../Payroll/viewEmpLicMaster">Employee LIC Master</a></li>
							<li><a href="../Payroll/viewEmpLic">Employee LIC Details</a></li>
							<li><a href="../Payroll/viewEmpAlwnce">Employee Fixed Allowances</a></li>
							<li><a href="../Payroll/viewOvertime">Employee Overtime</a></li>
							<li><a href="../Payroll/viewEmpDeductions">Employee ITax Exemptions</a></li>
							<li><a href="../Payroll/viewEmpFixedDeductions">Employee Fixed Deductions</a></li>
							<li><a href="../Payroll/viewEmpVarDeductions">Employee Variable Deductions</a></li>
							<li><a href="../Payroll/viewArrears">Employee Arrears</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Leave <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/empLeaveList">Apply Leave</a></li>
							<li><a href="../Payroll/empLeaveReport">Leave Report</a></li>
							<li><a href="../Payroll/viewEmployeeAttendance">Employee Attendance</a></li>
							<li><a href="../Payroll/processEmployeeAttendance">Process Attendance</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Payroll <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/generateBills">Generate Bills</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Reports <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/employeeSearch">Employee Details</a></li>
							<li><a href="../Payroll/empAllowanceSearch">Employee Allowances</a></li>
							<li><a href="../Payroll/inputPaybill">Paybill Report</a></li>
							<li><a href="../Payroll/monthlyRep">Monthly Report</a></li>
							<li><a href="../Payroll/headwiseRep">Headwise Report</a></li>
							<li><a href="../Payroll/bankwiseRep">Bankwise Report</a></li>
							<li><a href="../Payroll/paycheckRep">Employee Payslip</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<h6	style="color:white; margin-left: 135px;">${sessionScope.user.employee.fullName} (${sessionScope.user.roleNames})</h6>
					</li>
				</c:if>
				</ul>
			</div>
		</div>
		</div>
		<%--</c:if> --%>
	</nav>
	<!-- End Navbar -->
	
	<div class="clearfix"></div>
	
<script src="../Payroll/resources/js/jquery-2.0.3.min.js"></script>
    <script src="../Payroll/resources/js/bootstrap.min.js"></script>
	<script src="../Payroll/resources/js/jquery.easing-1.3.min.js"></script>
	
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../Payroll/resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../Payroll/resources/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../Payroll/resources/js/html5shiv.min.js"></script>
      <script src="../Payroll/resources/js/respond.min.js"></script>
    <![endif]-->
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../Payroll/resources/js/ie10-viewport-bug-workaround.js"></script>
	
    <!-- PrettyPhoto -->
	<script src="../Payroll/resources/js/prettyPhoto/jquery.prettyPhoto.js"></script>  
	<script src="../Payroll/resources/js/prettyPhoto/setting.js"></script>
	
	<!-- Parallax -->
	<script src="../Payroll/resources/js/parallax/jquery.parallax-1.1.3.js"></script>
	<script src="../Payroll/resources/js/parallax/setting.js"></script>
	
    <!-- masonry -->
	<script src="../Payroll/resources/js/masonry/masonry.min.js"></script>	
	<script src="../Payroll/resources/js/masonry/masonry.filter.js"></script>
	<script src="../Payroll/resources/js/masonry/setting.js"></script>  
	
    <!-- Progres circle -->
    <script src="../Payroll/resources/js/classyloader/jquery.classyloader.min.js"></script> 
		
    <!-- owl carousel -->
	<script src="../Payroll/resources/js/owlcarousel/owl.carousel.min.js"></script>  
	<script src="../Payroll/resources/js/owlcarousel/setting.js"></script>
	
    <!-- JavaScript animatedBg -->	
	<script src="../Payroll/resources/js/animatedBg/animatedBg-setting.js"></script>

    <!-- ticker -->
	<script src="../Payroll/resources/js/ticker/ticker.js"></script>
	
    <!-- Twitter -->
    <!--[if lte IE 9]>
    	<script src="../Payroll/resources/js/tweecool/jquery.xdomainrequest.min.js"></script>      
	<![endif]-->
	<script src="../Payroll/resources/js/tweecool/tweecool.js"></script>
	<script src="../Payroll/resources/js/tweecool/setting.js"></script>
	
	<!-- JavaScript totop -->
	<script src="../Payroll/resources/js/totop/jquery.ui.totop.js"></script>	
	<script src="../Payroll/resources/js/totop/setting.js"></script>
	
    <!-- Custom javaScript for this theme -->
    <script src="../Payroll/resources/js/custom.js"></script>

	<!-- Theme option-->
	<script src="../Payroll/resources/js/theme-option/demosetting.js"></script>	
</body>
</html>

<script type="text/javascript">
$(function() {
	setNavigation();
});
function setNavigation() {
	var path = window.location.pathname;
	
	$(".nav a").each(function () {
		var href = $(this).attr('href');
		if (path == href.substring(2,href.length)) {
			$(this).addClass('active');
			$(this).closest('.dropdown-menu').prev('a.dropdown-toggle').addClass('active');
		}
	});
}

function logoutEvent() {
	var alertMsg = "Are you sure, you want to logout?";
	if (confirm(alertMsg)) {
		window.location = "../Payroll/login";
	}
}

function backNav(actionPath){
	window.location = actionPath;
}
 </script>