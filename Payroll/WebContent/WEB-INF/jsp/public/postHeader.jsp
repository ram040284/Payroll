<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Dash Board</title>
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
	
}
.errMsg {
	display: none;
	font-size: 1.2em;
	color: red;
}

 </style>
 <script type="text/javascript">
 	function getList(actionPath) {
 		var f = document.forms['inputForm'];
 		<%--f.action="../Payroll/employee";--%>
 		f.action=actionPath;
 		f.submit();
 	}
 </script>
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
	<nav class="navbar yamm navbar-dark navbar-fixed-top">
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
								<i class="fa fa-sign-out fa-4" style="color:#ffff; float:right;"></i>
							</button>
						</div>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="../Payroll/dashboard">Home</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Masters <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<%--<li><a href="portfolio-alt1.html">Option-01</a></li>
							<li><a href="portfolio-alt2.html">Option-02</a></li>
							<li><a href="portfolio-alt3.html">Option-03</a></li> --%>
							<li><a href="../Payroll/viewDept"> Department</a></li>
				            <li><a href="../Payroll/viewHeads">Cost Heads</a></li>
				            <li><a href="../Payroll/viewDesg">Designation</a></li>
				            <li><a href="../Payroll/viewBankDetails">Bank Details</a></li>
				           <li><a href="../Payroll/viewAdvance">Advances </a></li>
				            <li><a href="../Payroll/viewTaxSlab">Incometax Slabs </a></li>
				            <li><a href="../Payroll/usersList">User </a></li>	
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Employee <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<%--<li><a href="#" onclick="inputAction('../Payroll/viewEmp')">New Employee</a></li> --%>
							<li><a href="#" onclick="getList('../Payroll/employee')">Employee Master</a></li>
							<li><a href="../Payroll/viewBank">Employee Bank</a></li>
							<li><a href="../Payroll/viewSalary">Employee Salary</a></li>
							<li><a href="#" onclick="getList('../Payroll/viewLeave_1')">Employee Leave</a></li>
							<li><a href="../Payroll/viewEmpPf">Employee PF</a></li>
							<li><a href="../Payroll/viewEmpQtr">Employee Quarters</a></li>
							<li><a href="../Payroll/viewEmpLic">Employee LIC</a></li>
							<li><a href="../Payroll/viewEmpAlwnce">Employee Allowances</a></li>
							<li><a href="../Payroll/viewOvertime">Employee Overtime</a></li>
							<li><a href="../Payroll/viewEmpDeductions">Employee Deductions</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Leave <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/empLeaveReport">Apply Leave</a></li>
							<%--<li><a href="../Payroll/viewLeave">Leave Report</a></li>
							<li><a href="portfolio-alt1.html">Option-01</a></li>
							<li><a href="portfolio-alt2.html">Option-02</a></li>
							<li><a href="portfolio-alt3.html">Option-03</a></li> --%>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Payroll <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#" onclick="getList('../Payroll/generateBills')">Generate Bills</a></li>
							<%---<li><a href="portfolio-alt1.html">Employee Salary</a></li>
							<li><a href="portfolio-alt2.html">Option-02</a></li>
							<li><a href="portfolio-alt3.html">Option-03</a></li> --%>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Reports <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../Payroll/employeeSearch">Employee Details</a></li>
							<li><a href="../Payroll/empAllowanceSearch">Employee Allowances</a></li>
							<li><a href="#" onclick="getList('../Payroll/inputPaybill')">Paybill Report</a></li>
							<li><a href="#" onclick="getList('../Payroll/monthlyRep')">Monthly Report</a></li>
							<li><a href="#" onclick="getList('../Payroll/headwiseRep')">Headwise Report</a></li>
							<li><a href="#" onclick="getList('../Payroll/bankwiseRep')">Bankwise Report</a></li>
							<li><a href="#" onclick="getList('../Payroll/paycheckRep')">Employee Payslip</a></li>
							<%--<li><a href="portfolio-alt2.html">Option-02</a></li>
							<li><a href="portfolio-alt3.html">Option-03</a></li> --%>
						</ul>
					</li>
				</ul>
				<!--<div class="navlink-right">
						<div class="dropdown shopping-cart">
							<button class="btn-navlink" type="button" data-toggle="dropdown">
								<i class="fa fa-sign-out fa-4" style="color:#ffff"></i>
							</button>
						</div>
				</div>-->
			</div><!--/.nav-collapse -->
			
		</div>
		</div>
		<%--</c:if> --%>
	</nav>
	<!-- End Navbar -->
	
	<div class="clearfix"></div>
	
	<%--<header style="background-color: #8B9DC3; width: 96.5%; margin-left: 10px; margin-right: 10px;">
    <div class="wrapper">
		<img src="../Payroll/resources/images/logo_new.jpg" alt="Mountain View" class= "logoImage" style="width:105px;height:105px; float:left; margin-right:135px;">
        <h1 style="color: #fff;">KHADAKI CANTONMENT BOARD, PUNE</h1>
		<img src="../Payroll/resources/images/Emblem.png" alt="Mountain View" class= "logoImage" style="width:105px;height:105px; float: right; ">
		
	</div>
	
</header>
<div class="wrapper">
    <div class="navMenu" style="width: 98%; margin-top:2px;">
      <ul>
        <li class="home"><a href="../Payroll/dashboard">Home</a></li>
        <li class="tutorials"><a href="#"> Masters<i class="fa fa-angle-down" aria-hidden="true"></i></a>
          <ul>
            <li><a href="../Payroll/viewDept"> Department</a></li>
            <li><a href="../Payroll/viewDesg">Designation</a></li>
           
          </ul>
        </li>
        <li class="about"><a href="#">Employee <i class="fa fa-angle-down" aria-hidden="true"></i></a>
			  <ul>
				<li><a href="#" onclick="inputAction('../Payroll/viewEmp')">New Employee</a></li>
				<li><a href="../Payroll/employee">List Employee</a></li>
				
			  </ul>
		</li>
        <li class="news"><a href="#">Leave <i class="fa fa-angle-down" ></i></a>
          <ul>
            <li><a href="#" onclick="inputAction('../Payroll/inputDept')">News Department</a></li>
            <li><a href="../Payroll/viewDept">List Departments</a></li>
           
          </ul>
        </li>
        <li class="news"><a href="#">Payroll <i class="fa fa-angle-down" ></i></a>
          <ul>
            <li><a href="#" onclick="inputAction('../Payroll/inputDept')">News Department</a></li>
            <li><a href="../Payroll/viewDept">List Departments</a></li>
           
          </ul>
        </li>
        <li class="news"><a href="#">Reports <i class="fa fa-angle-down" ></i></a>
          <ul>
            <li><a href="#" onclick="inputAction('../Payroll/inputDept')">News Department</a></li>
            <li><a href="../Payroll/viewDept">List Departments</a></li>
           
          </ul>
        </li>
        <li class="logoutCSS"><a href="#">Logout</a></li>
      </ul>
    </div>
	</div>
	<form action="" name="inputForm" method="post">
	
	</form>

	<%----<footer>Copyright &copy; W3Schools.com</footer>-->

</div>	 --%>
<form action="" name="inputForm" method="post">
	
</form>
<script type="text/javascript">
function logoutEvent() {
	var alertMsg = "Are you sure, you want to logout?";
	if (confirm(alertMsg)) {
		window.location = "../Payroll/login";
	}
}
</script>
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
			$(this).closest('a').addClass('active');
		}
	});
}
</script>