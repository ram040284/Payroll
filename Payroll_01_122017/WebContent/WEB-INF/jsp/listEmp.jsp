<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Details</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<script type="text/javascript">

$(document).ready(function() {
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
}
function searchEmps(){
	if($('#departmentId').val() == 0 && $('#firstName').val().trim() == ""){
		alert('Either Department or Name must be provided to get List!');
		$('#departmentId').focus();
		return false;
	}
	var f = document.forms['empSearch'];
	f.action="../Payroll/employee";
	f.submit();
}


</script>
</head>
<body><%-- onload="getList()">--%>
	
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<h5 style="color: #0101DF;">Employee Details</h5>
		<jsp:include page="../jsp/public/searchCriteria.jsp" />
		<c:if test="${employees.size() gt 0}">
		<div style="margin-top: 6px; float: left; max-width: 100%;">
			
			<div id="empListDiv" class="tblClass" style="max-width: 100%;">
				<table>
				<tr>
					<th>Name</th>
					<th>Department</th>
					<th>Head</th>
					<th>Designation</th>
					<th>DOB</th>
					<th>Gender</th>
					<th>Joining Date</th>
					<th>Phone#</th>
					<th><a href="#" onclick="inputPage()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/>
					</a></th>
				</tr>
				<c:forEach var="employee" items="${employees}">
				<tr>
					<td> ${employee.fullName} </td>
					<td> ${employee.department}</td>
					<td> ${employee.headName}</td>
					<td> ${employee.designation}</td>
					<td> ${employee.dob} </td>
					<td> ${employee.gender}</td>
					<td> ${employee.joiningDate}</td>
					<td> ${employee.phone}</td>
					<td><a href="#" onclick="viewEmp('${employee.employeeId}')" title="Edit">
							<img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/>
						</a>
						<a href="#" onclick="deleteEmp('${employee.employeeId}')">
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
		<input type="hidden" name="departmentId" />
		<input type="hidden" name="headId" />
		<input type="hidden" name="firstName"/>
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>