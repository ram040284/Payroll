<!DOCTYPE html>
<html>
<head>
<title>Employee Attendance</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
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

.employeeAttendaceTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.employeeAttendaceTableClass th, td {
	text-align: left;
	padding: 5px;
}

.employeeAttendaceTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.employeeAttendaceTableClass th {
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
}
</style>
<script type="text/javascript">

$(document).ready(function() {
	
	$.ajax({
		url : '../Payroll/listEmpAttendance',
		type : "GET",
		contentType : "application/json;charset=utf-8",
		success : function(employeeAttendanceData) {
			$('#employeeAttendaceTable').DataTable({
                data: employeeAttendanceData,
                columns: [
                    { data: 'srNo', title: 'Sr No' },
                    { data: 'officeLocation', title: 'Office Location' }, //or { data: 'MONTH', title: 'Month' }`
                    { data: 'divisionUnit', title: 'Division Unit' },
                    { data: 'attendanceId', title: 'Attendance Id' },
                    { data: 'employeeName', title: 'Employee Name' },
                    { data: 'designation', title: 'Designation' },
                    { data: 'inTime', title: 'In Time' },
                    { data: 'outTime', title: 'Out Time' },
                    { data: 'status', title: 'Status' },
                    { data: 'rowUpdateTime', title: 'Date' }
                ]
            });
		}
	});
	
});

function addEmployeeAttendance() {
	var f = document.forms['uploadForm'];
	f.action = "./addEmployeeAttendance";
	f.submit();
}

</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/leaveMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div  class="container" class="row" style="position: relative;">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Employee Attendance</h4>
				<form method="post" name="uploadForm" action="" enctype="multipart/form-data">
					File to upload: <input type="file" name="file">
					<a href="#" onclick="addEmployeeAttendance()" title="Upload file">
						<img src="../Payroll/resources/images/add.jpg" alt="Upload file" class="addImg" />
					</a>
				</form>
				<div id="employeeAttendaceDiv" class="employeeAttendaceTableClass" style ="width:100%;">
					<table id="employeeAttendaceTable" class="table table-striped table-bordered table-responsive"></table>
				</div>
			</div>
	</div>
</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>