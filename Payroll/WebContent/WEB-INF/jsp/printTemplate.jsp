<!DOCTYPE html>
<html>
<head>
<title></title>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../Payroll/resources/images/favicon.png" media="print">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/bootstrap.min.css">
<%--<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/style.css">--%>
 <link href="../Payroll/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" media="print">
 <link href="../Payroll/resources/css/jssor.css" rel="stylesheet" media="print">
 <link href="../Payroll/resources/css/style_new.css" rel="stylesheet" media="print">
 <link id="skin" href="../Payroll/resources/css/default.css" rel="stylesheet" media="print">
<link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"  type="text/css" media="print"/>
<style type="text/css">

@media print {
body
{
background-image: url(../Payroll/resources/images/logo_new.jpg);
background-repeat: no-repeat;
background-attachment: fixed;
background-position: center center;
}

.rptTblClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.rptTblClass th, td {
	text-align: left;
	padding: 5px;
}

.rptTblClass tr:nth-child(even) {
	background-color: #f2f2f2;
}

.rptTblClass th {
	background-color: #8B9DC3;
	color: white;
}

table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
display: none;
}

.dataTables_wrapper .dataTables_paginate .paginate_button{
color: white;
}

.dataTables_wrapper .dataTables_paginate .paginate_button a:{
  	color: #fff;
	border-radius:5px;
	background-color: #3b589a;
}

.dataTables_wrapper .dataTables_paginate .paginate_button a:hover{
  	color: #fff;
	border-radius:5px;
	background-color: #3b589a;
}
 
.dataTables_wrapper .dataTables_paginate .paginate_button a:active {
  	color: #fff; 
	background-color: #8B9DC3;
	border-radius:5px;
	margin-right: 5px;
}


}
</style>
</head>
<body>
<div class="wrapper" style="background:#e0e2ee;">
			<div class="container">
				<div class="navbar-header">
					<img src="../Payroll/resources/images/logo-02.png" class="img-responsive"
					alt="theme logo" />
				</div>
				<!-- Start Form Search -->
			</div>
		</div>
		
		<div class="wrapper" style="background:#3b589a;">
		<div class="container"><br>
		<h5 style="color: #0101DF;margin-bottom:0px;" id="reportName"></h5>
<div id="printReport" style="width:100%">

</div>
</div></div>
</body>