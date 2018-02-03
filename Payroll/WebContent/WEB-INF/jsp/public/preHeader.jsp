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
		
	</nav>
	<!-- End Navbar -->
	
	<div class="clearfix"></div>
	
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