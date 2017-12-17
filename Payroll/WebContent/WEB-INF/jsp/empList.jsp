<!DOCTYPE html>
<html>
<head>
<title>Hello World</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>
<jsp:include page="../jsp/public/jqueryMin2.0.3.jsp" />
<script type="text/javascript">
      function getList() {
          $.ajax({
              url : '../Payroll/view',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                  var empTab = $('<table/>').appendTo($('#empListDiv'));
                  $(data).each(function(i, employee){
                	  $('<tr/>').appendTo(empTab).append($('<td/>').text(employee.firstName +' '+employee.lastName))
                			  .append($('<td/>').text(employee.designation))
                			  .append($('<td/>').text(employee.email))
                			  .append($('<td/>').text(employee.phone));
                  });
              }
          });
      }
      </script>
</head>

<body>
	<div style="float: left;">
		<h2>Employee Details</h2>
		<a onclick="getList()" href="#">Get List</a>
	</div>
	<div id="empListDiv">
		<table>
			<tr>
				<th>Name</th>
				<th>Designation</th>
				<th>Email</th>
				<th>Phone</th>
			</tr>
		</table>
	</div>
</body>
</html>