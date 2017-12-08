<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/font-awesome.min.css">
<%--  <jsp:include page="bootstrap.min.css.jsp" />
<jsp:include page="bootstarp.min.css2.jsp" />
<jsp:include page="font-awesome.min.css.jsp" />--%>
<%--
<style type="text/css">
#headerNav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	border: 1px solid #e7e7e7;
	background-color: #f3f3f3;
}

#headerNav li {
	float: left;
}

#headerNav li a {
	display: block;
	color: #666;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

#headerNav li a:hover:not (.active ) {
	background-color: #ddd;
}

#headerNav li a.active {
	color: white;
	background-color: #4CAF50;
}
/*header {
    height: 120px;
}*/
div.container {
	width: 100%;
}

header {
	color: white;
	clear: left;
	text-align: center;
	min-height: 110px;
	background: #00bdf2;
	background: -webkit-linear-gradient(top, #00bdf2 0%, #00b3f0 20%, #0066b3 75%,
		#004785 100%);
	background: -moz-linear-gradient(top, #00bdf2 0%, #00b3f0 20%, #0066b3 75%, #004785
		100%);
	background: linear-gradient(to bottom, #00bdf2 0%, #00b3f0 20%, #0066b3 75%, #004785
		100%);
}

footer {
	padding: 1em;
	color: white;
	background-color: black;
	clear: left;
	text-align: center;
}

.titleCSS {
	color: #fff;
	float: left;
	margin-top: 16px;
	margin-bottom: 24px;
	margin-left: 38px;
	font-size: 1.6em;
}

.topnav {
	overflow: hidden;
	padding: 0;
	min-width: 80%;
}

.topnav a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 10px 16px;
	text-decoration: none;
	font-size: 12px;
	font-weight: bold;
	border-right: 1px solid #004785;
}

.topnav a:hover {
	background-color: #004785;
	color: #fff;
}

.topnav a.active {
	background-color: #004785;
	color: white;
}

.logoImage {
	float: right;
	margin-top: 3px;
	margin-right: 38px;
	border-radius: 50%;
}
</style> --%>
<jsp:include page="jqueryMin2.0.3.jsp" />
<jsp:include page="bootstrap.min.jsp" />

</head>
<body>
	<%--<header>
		<h1 style="text-align: center">Contonment Payroll</h1>
		<div class="text-center" id="headerNav">
  			
  			<ul>
 			 <li><a class="active" href="../Payroll/employee">Home</a></li>
 			 <li><a href="../Payroll/employee">Employee</a></li>
  			<li><a href="../Payroll/viewDept">Department</a></li>
 			<li><a href="../Payroll/viewDesg">Designation</a></li>
			</ul>
  			
  		</div>
  </header>	
  <div class="container">--%>

	<header>
		<img src="../Payroll/resources/images/logo_new.jpg" alt="Cantonment Board"
			class="logoImage" style="width: 105px; height: 105px;">
		<h1 class="titleCSS">Khadaki Cantonment Board</h1>


		<div class="topnav">
			<a class="active" href="#home">Home</a> <a href="../Payroll/employee">Employee</a>
			<a href="../Payroll/viewDept">Department</a> <a
				href="../Payroll/viewDesg">Designation</a>
		</div>

	</header>

	<%----<footer>Copyright &copy; W3Schools.com</footer>-->

</div>	 --%>
</body>
</html>