<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
<!-- <link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/> -->
<!-- <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet"/> -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

function getMonthName(month){
	var monthsName = ["January","February","March","April","May","June","July","August","September","October","November","December"];
	return monthsName[parseInt(month)-1];
}

$(document).ready(function() {
	
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawVisualization);
	
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	
	$.ajax({
        url: '../Payroll/monthlysummary',
        type: "GET", 
        contentType: "application/json;charset=utf-8",
        success: function(monthlySummaryDate){
        	for (i=0; i< monthlySummaryDate.length; i++) {
        		monthlySummaryDate[i]['month'] = getMonthName(monthlySummaryDate[i]['month'].substring(5,7)) + " " + monthlySummaryDate[i]['month'].substring(0,4);

        	}
        	$('#monthlyDashboardTable').DataTable( {
        		/* "bLengthChange": false,
        		 "filter" : false, 
        		 */
        		 columnDefs: [
         		    { className: 'text-right', targets: [2,3,4,5] }, // 2- No. of employees, 3- Total Gross Pay, 4- Total Deductions, 5- Total Net Pay
         		  ],   
        		 "data": monthlySummaryDate,
        	        "columns": [
        	            { "data": "month", title : 'Month'},
        	            { "data": "department", title : 'Department' },
        	            { "data": "noOfEmployees", title : 'No. of employees'},
        	            { "data": "totalGrossPay", title : 'Total Gross Pay' },
        	            { "data": "totalDeductions", title : 'Total Deductions' },
        	            { "data": "netPay", title : 'Total Net Pay' }
        	        ]
        	    } );
        }
    });
	
});

function drawVisualization() {
	
	$.ajax({
        url: '../Payroll/paybillchart',
        type: "GET", 
        contentType: "application/json;charset=utf-8",
        success: function(data){
        	

        	for (i=0; i< data.length; i++) {
        		data[i]['monthDate'] = getMonthName(data[i]['monthDate'].substring(5,7)) + " " + data[i]['monthDate'].substring(0,4);
        	}
        	
        	var paybillChartData = google.visualization.arrayToDataTable([
        		['Month', 'Gross Pay', 'Deductions', 'Net Pay'],
                [data[0]["monthDate"],  data[0]["grossPay"],      data[0]["deduction"],         data[0]["netPay"]],
                [data[1]["monthDate"],  data[1]["grossPay"],      data[1]["deduction"],         data[1]["netPay"]],
          	]);
        	
          	var options = {
          	title : 'Monthly Gross, Deductions and Net Pay',
          	vAxis: {title: 'Amount'},
          	hAxis: {title: 'Month'},
          	seriesType: 'bars',
          	bar: {groupWidth: "25%"},
          	series: {5: {type: 'line'}}
          	};

          	var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
          	chart.draw(paybillChartData, options);
        }
    });
	
}



function drawChart() {
	
	$.ajax({
        url: '../Payroll/paybillpiechart',
        type: "GET", 
        contentType: "application/json;charset=utf-8",
        success: function(data){
        	
			var secSchoolNeyPay = data[2]['netPay'] + data[5]['netPay'] + data[6]['netPay'];
			var priSchoolNeyPay = data[4]['netPay'] + data[7]['netPay'] + data[1]['netPay'] + data[10]['netPay'];
          	
          	var paybillPieChartData = google.visualization.arrayToDataTable([
          	    ['Department', 'Net Pay per Department'],
          	    [data[0]['deptName'],     data[0]['netPay']],
	          	[data[3]['deptName'],     data[3]['netPay']],
	          	[data[8]['deptName'],     data[8]['netPay']],
	          	[data[9]['deptName'],     data[9]['netPay']],
	          	[data[11]['deptName'],     data[11]['netPay']],
	          	['Secondary School', secSchoolNeyPay],
	          	['Primary School', priSchoolNeyPay],
          	  ]);

          	  var options = {
          	    title: 'Department wise Net Pay'
          	  };

          	  var chart = new google.visualization.PieChart(document.getElementById('piechart'));

          	  chart.draw(paybillPieChartData, options);
        }
    });

  
}



</script>
<style type="text/css">
select {
	min-width: 200px;
	min-height: 30px;
}

.buttonPadding {
	padding: 5px;
}
.btn-color{
	background-color: #0101DF;
}

.monthlyDashboardTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.monthlyDashboardTableClass th, td {
	text-align: left;
	padding: 1px;
}

.monthlyDashboardTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.monthlyDashboardTableClass th {
	background-color: #8B9DC3;
	color: #fff;
	cursor: pointer;
	
}
table.dataTable thead:first-child .sorting_asc { 
	background: url('../Payroll/resources/images/uparrow.png') no-repeat right bottom 8px; 
	background-size: 25px; 
	background-color: #8B9DC3;
	color: white;
}
table.dataTable thead:first-child .sorting_desc { 
	background: url('../Payroll/resources/images/downarrow.png') no-repeat right bottom 8px; 
	background-size: 25px; 
	background-color: #8B9DC3;
	color: white;
}
input[type=file] {
	display: inline-block;
}
.dataTables_paginate {
	text-align: right;
	margin-bottom: 15px;
}

</style>
</head>
<body>
<div class="contain-wrapp bodyDivCss">	
	<div class="container">
		<c:if test="${sessionScope.user != null}">
				<div class="row">
					<div class="col-md-12 text-center">
						<h3 style="color:blue;">Welcome CEO!</h3>
					</div>
				</div>
		</c:if>
		<div class="row">
		    <div class="col-sm-6 col-md-6 col-lg-6">
				<div id="chart_div" style="width: 550px; height: 250px;"></div>
		    </div>
		    <div class="col-sm-6 col-md-6 col-lg-6">
		      	 <div id="piechart" style="width: 550px; height: 250px;"></div>
		    </div>
	    </div>
	    
	    <div class="container">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Monthly Summary</h4>
				<div id="monthlyDashboardDiv" class="monthlyDashboardTableClass" style ="width:100%; margin-top: 25px">
					<table id=monthlyDashboardTable class="table table-striped table-bordered table-responsive"></table>
				</div>
			</div>
		</div>
	    
	    <!-- <div class="row"><h4 style="color: #0101DF;">Monthly Summary</h4></div>
	    <div class="row monthlyDashboardDiv">
	    	<table id="monthlyDashboard" class="display" style="width:100%"> -->
		    	<!-- <thead>
			    	<tr>
		                <th>Month</th>
		                <th>Department</th>
		                <th>No. of employees</th>
		                <th>Total Gross Pay</th>
		                <th>Total Deductions</th>
		                <th>Total Net Pay</th>
		            </tr>
	            </thead> -->
	            <!-- <tfoot>
			    	<tr>
			    		<th>Month</th>
			    		<th>Department</th>
		                <th>No. of employees</th>
		                <th>Total Gross Pay</th>
		                <th>Total Deductions</th>
		                <th>Total Net Pay</th>
		            </tr>
	            <tfoot> -->
	    	<!-- </table> -->
	    <!-- </div> -->
<!-- 	    <div class="row">Headwise Summary</div> -->
<!-- 	    <div class="row"> -->
<!-- 	    	<table id="headwiseDashboard" class="display" style="width:100%"></table> -->
<!-- 	    </div> -->
<!-- 	    <div class="row">Bankwise Summary</div> -->
<!-- 	    <div class="row"> -->
<!-- 	    	<table id="bankwiseDashboard" class="display" style="width:100%"></table> -->
<!-- 	    </div> -->
    </div>
</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>