<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Leave Details</title>
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

      <%--function getLeavesList() {
    	  $.ajax({
              url : '../Payroll/listLeaves',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                 var leaveTab = $('<table style="margin-bottom: 10px;"/>').appendTo($('#leaveListDiv'));
                  $(data).each(function(i, leave){
                	  $('<tr/>').appendTo(leaveTab)
                	  		.append($('<td/>').text(leave.fullName))
                			.append($('<td/>').text(leave.sickLeaves))
                			.append($('<td/>').text(leave.casualLeaves))
                			.append($('<td/>').text(leave.paidLeaves))
                			.append($('<td/>').text(leave.leaveBalance))
                			.append($('<td/>').append('<a href="#" onclick=viewLeave('+leave.empId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteLeave('+leave.empId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                	  
                  });
                  
              }
          });
      }--%>
      function viewLeave(empId){
    	  var f = document.forms['empSearch'];
		  f.empId.value=empId;
		  f.action="../Payroll/inputLeave";
		  f.submit();
	  }
      function inputPage(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputLeave";
		  f.submit();
	  }
      function deleteLeave(id){
    	  if(confirm("Are you sure want to delete Employee Leave?")){
    		  var f = document.forms['empSearch'];
    		  f.empId.value=id;
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
    		f.action="../Payroll/viewLeave";
    		f.submit();
    	}

      </script>
</head>
<body><%--  onload="getLeavesList()">--%>
	<%--<jsp:include page="../jsp/public/postHeader.jsp" /> --%>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	<h5 style="color: #0101DF;">Leave Details</h5>
		<jsp:include page="../jsp/public/searchCriteria.jsp" />
	<c:if test="${leaveVOList.size() gt 0}">	
	<div style="margin-top: 6px; float: left; width: 100%;">
			<%--<h4 style="color: #0101DF;">Leave Details</h4> --%>
		<div>
			
				<div class="tblClass" id="leaveListDiv">
				<table>
				<tr>
					<th>Employee</th>
					<th>Sick Leaves</th>
					<th>Casual Leaves</th>
					<th>Paid Vacation</th>
					<th>Available Balance</th>
					<th><a href="#" onclick="inputPage()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>
					</th>
				</tr>
				<c:forEach var="leave" items="${leaveVOList}">
				<tr>
					<td> ${leave.fullName} </td>
					<td> ${leave.sickLeaves}</td>
					<td> ${leave.casualLeaves}</td>
					<td> ${leave.paidLeaves}</td>
					<td> ${leave.leaveBalance} </td>
					<td><a href="#" onclick="viewLeave('${leave.empId}')" title="Edit">
							<img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/>
						</a>
						<a href="#" onclick="deleteLeave('${leave.empId}')">
							<img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>
						</a>
					</td>
				</tr>
				</c:forEach>
				</table>
				</div>
		</div>
	</div>
	</c:if>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<%--<input type="hidden" name="designationId" value="0">
		<input type="hidden" name="departmentId" value="0"> --%>
		<input type="hidden" name="empId" value="0">
		<input type="hidden" name="leaveId" value="0">
		
		
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>