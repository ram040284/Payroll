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
<link rel="stylesheet" type="text/css" href="../Payroll/resources/css/bootstrap.min.css" media="print">
<link href="/Payroll/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" media="print">
<link href="/Payroll/resources/css/jssor.css" rel="stylesheet" media="print">
<link href="/Payroll/resources/css/style_new.css" rel="stylesheet" media="print">
<link id="skin" href="/Payroll/resources/css/default.css" rel="stylesheet" media="print">
<link href="/Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet" media="print"/>
<script src="/Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="/Payroll/resources/js/dataTables.bootstrap.min.js"></script>
   
<style type="text/css">
@page {
	size: auto;   /* auto is the initial value */
    margin-top: 3mm;  /* this affects the margin in the printer settings */
    margin-left: 3mm;
    margin-right: 3mm;
    margin-bottom: 10mm;
}

@media print {
body {
	1background:#fff;
	1height: 100%;
	font-family:'raleway';
	font-size:13px;
	line-height:24px;
	color:#616161;
	letter-spacing:1px;
	background-image: url("../Payroll/resources/images/CBK_Logo.png") !important;
	background-position: center 130px !important;;	
    background-repeat: no-repeat !important;;
    background-size: 40% !important;;
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

.rptTblClass tr:nth-child(odd) {
	background-color: #f2f2f2;
}

.rptTblClass th {
	background-color: #8B9DC3;
	color: white;
}

table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
display: none;
}
}

</style>
</head>
<body>

<div style="text-align:center; width:100%">
	<img src="../Payroll/resources/images/logo-02.png" class="img-responsive" alt="theme logo" style="float:center" width="600" height="150"/>
</div>
		
<div style="width:100%;"><br>
	<h5 style="color: #0101DF;margin-bottom:0px;" id="reportName"></h5>
	<div id="printReport" style="width:100%">

	</div>
</div>
</body>