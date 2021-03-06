<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Leave Details</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<style type="text/css">
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
	
</style>
<script type="text/javascript">
$(document).ready(function() {
	$("#collapse").hide();
	$("#expand").show();
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${leave.departmentId}";
	$('#departmentId').val(deptId);
	var headId = "${leave.headId}";
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	var firstName = "${leave.firstName}";
	$('#firstName').val(firstName);
	$('#closeBtn').click(function(event) {
		   $("#searchDiv").toggle();
		   $("#collapse").toggle();
		   $("#expand").toggle();
	});
	
	$('#empLeaveTable').DataTable({
		"info" : false,
		"sort":$('#empLeaveTable tbody tr').length>1,
		"paging" : $('#empLeaveTable tbody tr').length>10,
		"displayLength": 10,
	  	"filter" : false,
	  	"lengthChange" : false,
	  	"autoWidth" : false,
	  	"pagingType" : "full_numbers",
	  	"scrollX": true,
	  	"scrollY": "300px",
	  	"scrollCollapse": true,
	  	"language": {
	        "emptyTable": "No matching records found"
	    }
 	});
});
      function viewLeave(empId){
    	  var f = document.forms['empSearch'];
		  f.employeeId.value=empId;
		  f.action="../Payroll/inputLeave";
		  f.submit();
	  }
      function inputPage(){
    	  var f = document.forms['empSearch'];
		  f.action="../Payroll/inputLeave";
		  f.submit();
	  }
      function deleteLeave(id){
    	  if(confirm("Are you sure want to delete Employee Leave?")){
    		  var f = document.forms['empSearch'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteLeave";
    		  f.submit();
    	  }
      }
      function searchEmps(){
    	  if($('#departmentId').val() == 0 && $('#firstName').val().trim() == ""){
    			alert('Either Department or Name must be provided to get List!');
    			$('#departmentId').focus();
    			return false;
    	  }
    		var f = document.forms['empSearch'];
    		f.action="../Payroll/viewLeave_1";
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
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
	<h5 style="color: #0101DF;">Leave Details</h5>
		<jsp:include page="../jsp/public/searchCriteria.jsp" />
	<c:if test="${leaveVOList.size() gt 0}">	
		<div style="margin-top: 6px; float: left; width: 100%;">
			<div id="leaveListDiv" class="rptTblClass" style="width: 100%;">
				<table id="empLeaveTable" class="table table-striped table-bordered table-responsive">
				<thead>
				<tr>
					<th>Employee</th>
					<th>Casual Bal</th>
					<th>Medical Bal</th>
					<th>Half Paid Bal</th>
					<th>Earned Bal</th>
					<th>Maternity Bal</th>
					<th>Paternity Bal</th>
					<th>Child Care leave</th>
					<th>Extraordinary With Medical Bal</th>
					<th>Extraordinary Without Medical Bal</th>
					<th><a href="#" onclick="inputPage()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>
					</th>
				</tr>
				</thead>
				<c:forEach var="leave" items="${leaveVOList}">
				<tr>
					<td> ${leave.fullName} </td>
					<td> ${leave.casualLeaveBal}</td>
					<td> ${leave.sickLeaveBal}</td>
					<td> ${leave.halfPaidLeaveBal}</td>
					<td> ${leave.earnLeaveBal} </td>
					<td> ${leave.maternityLeaveBal}</td>
					<td> ${leave.paternitiLeaveBal}</td>
					<td> ${leave.childCareLeaveBal}</td>
					<td> ${leave.extraOrdLeaveBal}</td>
					<td> ${leave.extraOrdLeaveWithoutMediBal} </td>
					<td><a href="#" onclick="viewLeave('${leave.employeeId}')" title="Edit">
							<img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/>
						</a>
						<a href="#" onclick="deleteLeave('${leave.employeeId}')">
							<img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>
						</a>
					</td>
				</tr>
				</c:forEach>
				</table>
				</div>
		</div>
	
	</c:if>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="employeeId" value="0">
		<input type="hidden" name="leaveId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>