<!DOCTYPE html>
<html>
<head>
<title>Process Attendance</title>
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

.processAttendaceTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.processAttendaceTableClass th, td {
	text-align: left;
	padding: 5px;
}

.processAttendaceTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.processAttendaceTableClass th {
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
.dataTables_paginate {
	text-align: right;
}
</style>
<script type="text/javascript">

$(document).ready(function() {
	
	$.ajax({
		url : '../Payroll/listProcessAttendance',
		type : "GET",
		contentType : "application/json;charset=utf-8",
		success : function(attendanceData) {
			$('#processAttendaceTable').DataTable({
                data: attendanceData,
                columns: [
//                 	{ data: null, title: 'Select to process' },
					{ data: 'srNo', title: 'Select to process' },
                	{ data: 'rowUpdateTime', title: 'Date' },
                    { data: 'attendanceId', title: 'Attendance Id' },
                    { data: 'employeeName', title: 'Employee Name' },
                    { data: 'divisionUnit', title: 'Division Unit' },
                    { data: 'officeLocation', title: 'Office Location' }, //or { data: 'MONTH', title: 'Month' }`
                    { data: 'designation', title: 'Designation' },
                    { data: 'status', title: 'Status' },
                    { data: 'absenceReason', title: 'Absence Reason' }
                ],
                "columnDefs": [ {
                    "targets": 0,
                    "orderable": false,
                    "searchable": false,
                    "data": "srNo",
                    "render": function ( data, type, full, meta ) {
                        return '<input type="checkbox" name= "attendanceSrNo" id="'+data+'" />';
               		}, 
                }]
            });
		}
	});
	
});

function processSelectedAttendance() {
	
	var attendanceTable = $("#processAttendaceTable").DataTable();
	
	var selected = [];
	$("#processAttendaceDiv input:checked").each(function() {
	    selected.push($(this).attr("id"));
	});
	
	var absenceReason = $("#absenceReasonOption :selected").val();
	
	$("#attendanceToProcess").val(selected);
	$("#absenceReason").val(absenceReason);
	
	var formData = {
    		firstname : $("#firstName").val(),
    		lastname :  $("#lastName").val(),
    		absenceReason : absenceReason
    	}

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : '../Payroll/processEmployeeAttendance',
		data : JSON.stringify(formData),
		dataType : 'json',
		success : function(result) {
			console.log("In Success: " + result)
			alert("Success!");
		},
		error : function(e) {
			//alert(e);
		}
	});
	
// 	var f = document.forms['processAttendance'];
// 	f.absenceReason=absenceReason;
	
// 	console.log("f.absenceReason.value: " + f.absenceReason.value + " :: " + absenceReason)
	
// 	console.log($("#absenceReason").val());
	
	
	
// 	var f = document.forms['processAttendance'];
// 	  f.attendanceToProcess.value=selected;
// 	  f.absenceReason.value=absenceReason;
// 	  f.action="../Payroll/processEmployeeAttendance";
// 	  f.submit();
	
	
	
// 	console.log(attendanceTable)
	
// 	var count = attendanceTable.columns();
	
// 	console.log(count);

//console.log(attendanceTable.rows('.selected').data());

//console.log(attendanceTable.rows('.selected').data());
//console.log(attendanceTable.rows( { selected: true } ).data());
// 	var rows = $(attendanceTable.$('input[type="checkbox"]').map(function () {
// 		  var currentRow = $(this).prop("checked") ? $(this).closest('tr') : null;
// 		  if (currentRow != null) {
// 			  //
// 			  //console.log(currentRow["Attendance Id"]);
// 			  console.log(currentRow.data());
// 		  }
// 		} ) );
	
}

function processSelectedAttendance2() {
	
	var attendanceTable = $("#processAttendaceTable").DataTable();
	var selected = [];
	$("#processAttendaceDiv input:checked").each(function() {
	    selected.push($(this).attr("id"));
	});
	
	var absenceReason = $("#absenceReasonOption :selected").val();
	
	$("#attendanceToProcess").val(selected);
	$("#absenceReason").val(absenceReason);
	
	var f = document.forms['processAttendance'];
	  f.srNoArray.value=selected;
	  f.absenceReason.value=absenceReason;
	  f.action="../Payroll/processEmployeeAttendance";
	  f.submit();
}

// $('#processAttendaceTable tbody').on( 'click', 'tr', function () {
//     console.log( table.row( this ).data() );
// } );

// function addEmployeeAttendance() {
// 	var f = document.forms['uploadForm'];
// 	f.action = "./addEmployeeAttendance";
// 	f.submit();
// }

</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">
		<div  class="container" class="row" style="position: relative;">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Process Attendance</h4>
<!-- 				<form method="post" name="processAttendance" action="" onSubmit="processSelectedAttendance()"> -->
				<form method="post" name="processAttendance" action="">
					Select absence reason to process attendance:
					<select id="absenceReasonOption">
					  <option value="Sick">Sick leave</option>
					  <option value="Working From Other Location">Working from other location</option>
					  <option value="Workign From Home">Working From Home</option>
					  <option value="Other">Other</option>
					</select>
<!-- 					<input type="hidden" name="attendanceToProcess" id="attendanceToProcess"> -->
					<input type="hidden" name="srNoArray" id="srNoArray">
					<input type="hidden" name="absenceReason" id="absenceReason">
					<input type="submit" value="Submit" onClick="processSelectedAttendance2()">
				</form>
				<div id="processAttendaceDiv" class="processAttendaceTableClass" style ="width:100%; margin-top: 25px">
					<table id="processAttendaceTable" class="table table-striped table-bordered table-responsive"></table>
				</div>
			</div>
	</div>
</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>