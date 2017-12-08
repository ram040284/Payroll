<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/style.css">
<style>
.logoImage{
	margin-top: 5px;
	border-radius: 50%;
}
.navMenu ul {
  list-style: none;
  /*background-color: #aaa;*/
  background-color: #374F89;
  text-align: left;
  padding: 0;
  margin: 0;
  
 }

.navMenu li {
  font-family: 'Oswald', sans-serif;
  font-size: 0.8em;
  line-height: 40px;
  text-align: left;
  min-width: 160px;
  background-color: #374F89;
}

.navMenu a {
  text-decoration: none;
  color: #fff;
  display: block;
  border-bottom: 1px solid #888;
  transition: .3s background-color;
  overflow: inherit;
}

.navMenu a:hover {
  /*background-color: #005f5f;*/
  background-color: #8B9DC3;
   
}

.navMenu a.active {
  background-color: #8B9DC3;
  color: #fff;
  cursor: default;
}

/* Sub Menus */
.navMenu li li {
  font-size: .8em;
  z-index:100;
  border-right: 1px solid #f2f2f2;
  border-bottom: 1px solid #f2f2f2;
  border-left: 1px solid #f2f2f2;
}

.navMenu li li a {
	text-align: center;
	padding-right: 8px;
	white-space: nowrap;
}
.navMenu li li a:hover {
	border-bottom: 1px solid #f2f2f2;	
}

.navMenu li li:last-of-type{
	border-bottom: 1px solid #fff;
}


/*******************************************
   Style menu for larger screens

   Using 650px (130px each * 5 items), but ems
   or other values could be used depending on other factors
********************************************/

@media screen and (min-width: 650px) {
  .navMenu li {
    width: 130px;
    border-bottom: none;
    height: 50px;
    line-height: 50px;
    font-size: 1.1em;
    display: inline-block;
    margin-right: -4px;
  }

  .navMenu a {
    border-bottom: none;
  }

  .navMenu > ul > li {
    text-align: center;
  }

  .navMenu > ul > li > a {
    padding-left: 0;
  }

  /* Sub Menus */
  .navMenu li ul {
    position: absolute;
    display: none;
    width: inherit;
    z-index:100;
   
  }

  .navMenu li:hover ul {
    display: block;
  }

  .navMenu li ul li {
    display: block;
  }
}
.logoutCSS{
	float: right;
}
.watermark {
  width: 100%;
  height: 500px;
  display: block;
  position: relative;
}

.watermark::after {
  content: "";
 background:url(images/logo_new.jpg) no-repeat;
 background-attachment: fixed;
background-size: cover;
background-position: center;
  opacity: 0.2;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  position: absolute;
  z-index: -1;
}
.addImg {
	width: 40px;
	height: 40px;
}
.listImg {
	width: 30px;
	height: 30px;
	margin-right: 10px;
}

#main-footer-bottom ul{
	list-style: none;
	margin-left: -5px;
	padding-left:0;
	color: #374F89;
}

#main-footer-bottom ul li{
	display: inline-block;
	margin-right: 8px;
	color: #374F89;
}

#main-footer-bottom ul li a {
	color: #374F89;
}

#main-footer-bottom ul li a:hover {
	color: #aaa;
	text-decoration: none;
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
	min-width: 154px;
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

</style>
<jsp:include page="jqueryMin2.0.3.jsp" />
<jsp:include page="bootstrap.min.jsp" />
<script type="text/javascript">
function inputAction(actionPath){
	  var f = document.forms['inputForm'];
	  f.action=actionPath;
	  f.submit();
}
var selector = '.navMenu a';

$(document).ready(function () {
    $('.navMenu li a').click(function (e) {
		$('.navMenu li a').removeClass('active');

        var $parent = $(this);
        if (!$parent.hasClass('active')) {
            $parent.addClass('active');
        }
        
    });
});
</script>
</head>
<body>
<header style="background-color: #8B9DC3; width: 96.5%; margin-left: 10px; margin-right: 10px;">
    <div class="wrapper">
		<img src="../Payroll/resources/images/logo_new.jpg" alt="Mountain View" class= "logoImage" style="width:105px;height:105px; float:left; margin-right:135px;">
        <h1 style="color: #fff;">KHADAKI CANTONMENT BOARD, PUNE</h1>
		<img src="../Payroll/resources/images/Emblem.png" alt="Mountain View" class= "logoImage" style="width:105px;height:105px; float: right; ">
		
	</div>
	
</header>
<div class="wrapper">
    <div class="navMenu" style="width: 98%; margin-top:2px;">
      <ul>
        <%--<li class="home"><a href="../Payroll/employee">Home</a></li> --%>
        <li class="home"><a href="../Payroll/dashboard">Home</a></li>
        <li class="tutorials"><a href="#"> Masters<%--Employee  --%> <i class="fa fa-angle-down" aria-hidden="true"></i></a>
          <ul>
            <li><a href="../Payroll/viewDept"> Department</a></li>
            <li><a href="../Payroll/viewDesg">Designation</a></li>
            <li><a href="../Payroll/viewConveyance">Conveyance Allowance</a></li>
           	<li><a href="../Payroll/viewOvertime">Overtime Payment</a></li>
           	<li><a href="../Payroll/viewAdvance">Advance Payment</a></li>
           	<li><a href="../Payroll/viewPayHead">Pay-Head Details</a></li>
           	<li><a href="../Payroll/viewDeductHead">Deduction Details</a></li>
          </ul>
        </li>
        <li class="about"><a href="#">Employee<%--Designation --%> <i class="fa fa-angle-down" aria-hidden="true"></i></a>
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
</body>
</html>