<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Apply Leave</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
select {
	min-width: 200px;
	min-height: 30px;
}

td, th {
	padding: 3px;
}

.buttonPadding {
	padding: 5px;
}
.btn-color{
	background-color: #0101DF;
}

.custom-combobox {
	font-family:inherit;
    position: relative;
    display: inline-block;
    width:100%;
  }
   .custom-combobox-toggle {
    position: absolute;
    top: 0;
    bottom: 0;
    margin-left: -1px;
    padding: 0;
    border-radius:2px;
	border:1px solid #aeaeae;
	border-left:0px;
    background:rgba(255, 255, 255, 0.6);
  }
  .custom-combobox-input {
  	font-family:inherit;
    padding:10px 15px;
	height:48px;
	border-radius:2px;
	border:1px solid #aeaeae;
	border-right:0px;
	font-size:13px;
	font-style:italic;
	color:#616161;
	background:rgba(255, 255, 255, 0.6);
	width:93%;
  }
 .custom-combobox-input:focus{
    outline: 0px;
 	border-radius:2px;
 	border:1px solid #00913e;
 }
 
</style>
<script type="text/javascript">
var rolesList ;
$(document).ready(function() {
		$('#fromDate').datepick({dateFormat: 'dd/mm/yyyy'});
		$('#toDate').datepick({dateFormat: 'dd/mm/yyyy'});
	    $("#resetBtn").click(function() {    
	    	$("#leaveBalance").html(leaveBalance);
	        this.form.reset();   
	        return false;                        
	    });
	    
	    $('#usersListBtn').click(function(event) {
	    	$("#listForm").attr("action", "../Payroll/usersListFilter");
			$("#listForm").submit();
	    });
	    
	    $('#leaveListBtn').click(function(event) {
	    	$("#listForm").attr("action", "../Payroll/empLeaveListApply");
			$("#listForm").submit();
	    });
	    
	    $('#applyLeaveBtn').click(function(event) {
	    	
	    	if($('#leaveType').val() == ''){
	    		alert("Leave Type must be selected!");
	    		$('#leaveType').focus();
	    		return false;
	    	}
	    	
	    	if($('#fromDate').val() == ''){
	    		alert("From Date must be entered");
	    		$('#fromDate').focus();
	    		return false;
	    	}
	    	if($('#toDate').val() == ''){
	    		alert("To Date must be entered");
	    		$('#toDate').focus();
	    		return false;
	    	}
	    	
	    	var fromDate = dateConversion($('#fromDate').val());
	    	var toDate = dateConversion($('#toDate').val());
	    	
	    	if (fromDate > toDate) {
	    		alert("From Date must be less than To Date");
	    		$('#fromDate').focus();
	    		return false;
	    	}
	    	
	    	if($('#noOfLeaves').val() == '' || $('#noOfLeaves').val() == '0'){
	    		alert("Number of leaves must be entered");
	    		$('#noOfLeaves').focus();
	    		return false;
	    	}
	    	
	    	var leaveBal = parseInt($("#leaveBalance").html());
	    	if(leaveBal < $('#noOfLeaves').val()) {
	    		alert("Number of Days leave should not more than leave balance");
	    		$('#noOfLeaves').focus();
	    		return false;
	    	}
	    	
	    	if($('#reason').val() == ''){
	    		alert("Reason must be entered");
	    		$('#reason').focus();
	    		return false;
	    	}
	    	
	    	$("#userForm").attr("action", "../Payroll/applyLeave");
			$("#userForm").submit();
	    });
	    
});

function dateConversion(dateString) {
	var dateArray =  dateString.split("/"); 
	var dateObj = new Date("'" + dateArray[2] + "-" + dateArray[1] + "-" +dateArray[0] + "'");
	return dateObj;
}
function getLeaveBalance() {
	var leaveType = $("#leaveType").val();
	var leaveBalance = "";
	switch(leaveType) {
	<c:forEach var="leaveBal" items="${leaveBalance}">
    case "${leaveBal.key}":
    	leaveBalance = "${leaveBal.value}";
        break;
        </c:forEach>
    default:
    	leaveBalance = "";
	}
	
	$("#leaveBalance").html(leaveBalance);
}

</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;font-size:12pt;">
				Apply Leave Request
			</h4>
			
			<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
			<div class="row">
			<form:form method = "POST" action = "" id="userForm" autocomplete="off">
				<div class="col-sm-12">
						<div class="row">	
							
							<div class="col-sm-6 form-group">
								<label>Employee</label>
								<div class="ui-widget">
								${leave.employee.fullName}
								<form:input type="hidden" path="employeeId"/>
								</div>
							</div>
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<div class="ui-widget">
								${leave.employee.department}
								</div>
							</div>
						</div>	
						
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Head</label>
								<div class="ui-widget">
								${leave.employee.headName}
								</div>
							</div>
							<div class="col-sm-6 form-group">
								<label>Designation</label>
								<div class="ui-widget">
								${leave.employee.designation}
								</div>
							</div>
						</div>	
						
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Leave Type</label>
								<div class="ui-widget">
								<select id="leaveType" class="form-control" name="leaveType" onchange="getLeaveBalance()">
								<option value="">-- Select Leave Type --</option>
								<c:forEach var="leaveType" items="${leaveBalance}">
								<option value="${leaveType.key}">${leaveType.key}</option>
								</c:forEach>
								</select>
								</div>
							</div>
							<div class="col-sm-6 form-group">
								<label>Available Balance</label>
								<div class="ui-widget" id="leaveBalance">
								
								</div>
							</div>
						</div>	
						<div class="row">
						<div class="col-sm-6 form-group">
							<label>from Date</label>
							<form:input type="text" id="fromDate" path="fromDate" placeholder="Enter From (DD/MM/YYYY)" class="form-control"/>
						</div>
							
						<div class="col-sm-6 form-group">
							<label>To Date</label>
							<form:input type="text" id="toDate" path="toDate" placeholder="Enter To Date (DD/MM/YYYY)" class="form-control"/>
						</div>
						</div>
						
						<div class="row">
						<div class="col-sm-6 form-group">
							<label>Number of Days</label>
							<form:input type="text" id="noOfLeaves" path="noOfLeaves" placeholder="Enter Number of Leaves" class="form-control"/>
						</div>
							
						<div class="col-sm-6 form-group">
							<label>Reason</label>
							<form:textarea id="reason" path="reason" placeholder="Enter Reason" class="form-control"/>
						</div>
						</div>
						
						<div class="row">
							<div class="col-sm-12 form-group text-right">
							<button type="button" id="applyLeaveBtn" class="btn">Apply Leave</button>
							<button type="reset" class="btn" id="resetBtn">Reset</button>	
							<button type="button" id="leaveListBtn"  class="btn">Employee Leave List</button>	
						</div>	
						</div>			
					</div>	
					
					<input type="hidden" name="departmentId" value="${employee.departmentId}"/>
					<input type="hidden" name="headId" value="${employee.headId}"/>
					<input type="hidden" name="firstName" value="${employee.firstName}"/>
					<input type="hidden" name="listDeptId" value="${employee.listDeptId}"/>
					<input type="hidden" name="listHeadId" value="${employee.listHeadId}"/>
					<input type="hidden" name="listName" value="${employee.listName}"/>
			</form:form> 
		</div>
	</div>
	</div>
	</div>
	</div>
	
	<form:form  action="" id="listForm" method="post">
		<form:input type="hidden" path="departmentId"/>
		<form:input type="hidden" path="headId"/>
		<form:input type="hidden" path="firstName"/>
		<form:input type="hidden" path="listDeptId"/>
		<form:input type="hidden" path="listHeadId"/>
		<form:input type="hidden" path="listName"/>
	</form:form >
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>