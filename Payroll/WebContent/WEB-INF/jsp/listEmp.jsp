<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Details</title>
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

.empListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.empListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.empListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.empListTableClass th {
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
	
	$('#searchBtn').on('click',function(e){
		
		if($('#departmentId').val() == 0 && $('#firstName').val().trim() == "" && $('#employeeType').val() == 0){
			alert('Either Department or Name or Employee type must be provided to get List!');
			$('#departmentId').focus();
			return false;
		}
		
		if($('#departmentId').val() == 0){
			alert('Department must be provided to get List!');
			$('#departmentId').focus();
			return false;
		}
		
		e.preventDefault();
		var inputJson = { "firstName" : $('#firstName').val(),"departmentId": $('#departmentId').val(), "headId":$('#headId').val(), "employeeType":$('#employeeType').val()};
		
		$.ajax({
			//async:false,
			url : '../Payroll/getEmployeeList',
		    type : "GET",
		    data : inputJson,
		    //data : JSON.stringify(inputJson),
            //contentType: "application/json;charset=utf-8",
		    success : function(empData) {
			 var table = $('#empListTable').DataTable({
				 destroy: true,
				 "searching": false,
				 "scrollY": "300px",
			        data: empData,
			          columns: [
			         		{ data: 'fullName', title: 'Name',"autoWidth": false},
			          		{ data: 'department', title: 'Department',"autoWidth": false},
			            	{ data: 'headName', title: 'Head',"autoWidth": false},
			            	{ data: 'designation', title: 'Designation',"autoWidth": false},
			          		{ data: 'dob', title: 'DOB',"autoWidth": false},
			            	{ data: 'gender', title: 'Gender',"autoWidth": false},
			            	{ data: 'joiningDate', title: 'Joining Date',"autoWidth": false},
			          		{ data: 'retirementDate', title: 'Retirement Date',"autoWidth": false},
			            	/* {
							  'data': null, title:'<a href="#" onclick="inputPage()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
							  'render': function (empData, type, row) {
							     return '<a id="' + row.Id +'" href="#" onclick="viewEmp(\'' + empData.employeeId + '\')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'" href="#" onclick="empServiceBook(\'' + empData.employeeId + '\')" title:"Edit"><img src="../Payroll/resources/images/empProcessAttendance.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteEmp(\'' + empData.employeeId + '\')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
				              }
							} */
			          		{
								 //'data': null,title:'<a href="#" onclick="inputPage()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
								 'data': null, title:'<a href="#" onclick="inputPage()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
								 ' width' : '150px',
								 'render': function (empData, type, row) {
								 return '<a id="' + row.Id +'" href="#" onclick="viewEmp(\'' + empData.employeeId +'\')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'" href="#" onclick="empServiceBook(\'' + empData.employeeId + '\')" title:"Edit"><img src="../Payroll/resources/images/empProcessAttendance.png" alt="Edit" class="listImg"/></a><a id="' + row.Id +'"  href="#" onclick="deleteEmp(\'' + empData.employeeId + '\')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
								}
								},
								 {
									 'data': null,title:'Upload File',
									 ' width' : '150px',
									 'render': function (empData, type, row) {
									 return	'<a id="' + row.Id +'" href="#"><form method="POST" name=\'' + empData.employeeId +'\' action="" enctype="multipart/form-data" onSubmit="return uploadFile(this);"><div class="input-group"><input type="file"class="file" name="fileUpload"/><input type="hidden" name="id" value="'+empData.employeeId +'"><input type="submit" name="Upload" value="Upload" title="Upload file"></div></form></a>' 
									 }
								 } 
								
			         ]
			      }); 
			   }  
		 });	
	});
	
	
	$("#collapse").hide();
	$("#expand").show();
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${employee.departmentId}";
	$('#departmentId').val(deptId);
	var headId = "${employee.headId}";
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	var firstName = "${employee.firstName}";
	$('#firstName').val(firstName);
	$('#closeBtn').click(function(event) {
		   $("#searchDiv").toggle();
		   $("#collapse").toggle();
		   $("#expand").toggle();
	});
	
	$.ajax({
		  url : '../Payroll/view',
		  type : "GET",
		  contentType: "application/json;charset=utf-8",
		  success : function(empData) {
		 var table = $('#empListTable').DataTable({
			 "scrollY": "400px",
			 fixedHeader: true,
			 "searching": false,
		        data: empData,
		          columns: [
		         		{ data: 'fullName', title: 'Name',"autoWidth": false},
		          		{ data: 'department', title: 'Department',"autoWidth": false},
		            	{ data: 'headName', title: 'Head',"autoWidth": false},
		            	{ data: 'designation', title: 'Designation',"autoWidth": false},
		          		{ data: 'dob', title: 'DOB',"autoWidth": false},
		            	{ data: 'gender', title: 'Gender',"autoWidth": false},
		            	{ data: 'joiningDate', title: 'Joining Date',"autoWidth": false},
		          		{ data: 'retirementDate', title: 'Retirement Date',"autoWidth": false},
		            	{
						 //'data': null,title:'<a href="#" onclick="inputPage()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
						 'data': null, title:'<a href="#" onclick="inputPage()"><img style="vertical-align: middle;" src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
						 ' width' : '150px',
						 'render': function (empData, type, row) {
						 return '<a id="' + row.Id +'" href="#" onclick="viewEmp(\'' + empData.employeeId +'\')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'" href="#" onclick="empServiceBook(\'' + empData.employeeId + '\')" title:"Edit"><img src="../Payroll/resources/images/empProcessAttendance.png" alt="Edit" class="listImg"/></a><a id="' + row.Id +'"  href="#" onclick="deleteEmp(\'' + empData.employeeId + '\')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
						}
						},
						 {
							 'data': null,title:'Upload File',
							 ' width' : '150px',
							 'render': function (empData, type, row) {
							 return	'<a id="' + row.Id +'" href="#"><form method="POST" name=\'' + empData.employeeId +'\' action="" enctype="multipart/form-data" onSubmit="return uploadFile(this);"><div class="input-group"><input type="file"class="file" name="fileUpload"/><input type="hidden" name="id" value="'+empData.employeeId +'"><input type="submit" name="Upload" value="Upload" title="Upload file"></div></form></a>' 
							 }
						 } 
						 
						 //'    <a id="' + row.Id +'" href="#" onclick="uploadFile(\'' + empData.employeeId +'\')" title="Upload file"><img src="../Payroll/resources/images/add.jpg" alt="Upload file" class="addImg" /></a>'
				        
		         ]
		      }); 
		   }
	 });
	 
	 
	
});

function viewEmp(id){
	var f = document.forms['empSearch'];
	f.employeeId.value=id;
	f.action="../Payroll/viewEmp";
	f.submit();
}
function inputPage(){
	var f = document.forms['empSearch'];
	f.action="../Payroll/viewEmp";
	f.submit();
}
function deleteEmp(id){
	if(confirm("Are you sure want to delete Employee?")){
    	var f = document.forms['empSearch'];
    	f.employeeId.value=id;
    	f.action="../Payroll/deleteEmp";
    	f.submit();
    }
	/*  function addEmployee(id) {
 		var f = document.forms['uploadForm'];
 		f.action = "../Payroll/addEmployee";
 		f.submit();
  	} */
 
}

 function uploadFile(element) 
 {
	var id = element.id.value
	element.action = "../Payroll/uploadFile/"+id;
	return true;
	
} 
	
function empServiceBook(id){
	var employeeIds = ['197810030', '198005008', '198505019', '198712210', 
		          '198811034', '198901028', '198901211', '198905457',
		          '199105024', '199106011', '199106025', '199508018',
		          '199709853', '199712856', '201207284', '201207290',
		          '201212312', '201212314', '201212319', '201212324',
		          '201307348', '201406042', '201408013'];
	
	if(employeeIds.includes(id)){
		window.open('./resources/images/'+id+'.pdf', '');
	}else {
		alert("No E-Book Found for this employee!!!");
	}
	
}


</script>
</head>
<body><%-- onload="getList()">--%>
	
	<div class="contain-wrapp bodyDivCss">
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<!--new code  -->
				<div class="container">
		<div style="margin-top: 12px; float: left; width: 98%;">
					<h4 style="color: #0101DF;">Employee Details</h4>	
					<%--<form method="POST" name="uploadForm" action="uploadFile" enctype="multipart/form-data">
	                                   <td><input type="file" name="fileUpload" size="50" /></td>
	               
						<a href="#" onclick="uploadFile()" title="Upload file">
		                <img src="../Payroll/resources/images/add.jpg" alt="Upload file" class="addImg"></a>
					      
					     </form>--%>
			
				<%-- 	<form method="POST" name="uploadForm" action="" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Pick file #1:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                	<td><input type="hidden" name="id"></td>
                </tr>
            </table>
        </form> --%>
        
			 <%-- <html>
			 <body>
    <center>
<!--         <h1>Spring MVC File Upload Demo</h1>
 -->       
  <form method="post" action="uploadFile" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Description:</td>
                    <td><input type="text" name="description" size="50"/></td>
                </tr>
                <tr>
                    <td>Pick file #1:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                </tr>
                <tr>
                    <td>Pick file #2:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html> --%>
	<!-- <div class="container">
			<h5 style="color: #0101DF;">Employee Details</h5>
		 -->	<jsp:include page="../jsp/public/searchCriteria.jsp" />
			<div id="empListDiv" class="empListTableClass" style ="width:100%; margin-top: 25px">
					<table id="empListTable" class="table table-striped table-bordered table-responsive"></table>
			</div>
 		</div>
	</div>
	<form action="" name="editForm" method="POST">
		<input type="hidden" name="employeeId" value="0">
		<input type="hidden" name="departmentId" />
		<input type="hidden" name="headId" />
		<input type="hidden" name="firstName"/>
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>