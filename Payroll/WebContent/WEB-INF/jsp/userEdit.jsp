<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Update User</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
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
	rolesList = ${sessionScope.roles};
	$.each(rolesList, function( index, value ) {
		$('<option>').val(value.roleId).text(value.roleName).appendTo('#roleId');
	});
	
	var roleId = '${user.roleId}';
	roleId = roleId == ''? 0 : roleId;
	$('#roleId').val(roleId);
	
	if (roleId != 0) roleSelection();
	
	    $("#resetBtn").click(function() {       // apply to reset button's click event
	        this.form.reset();   
	        return false;                         // prevent reset button from resetting again
	    });
	    
	    $('#usersListBtn').click(function(event) {
	    	$("#listForm").attr("action", "../Payroll/usersListFilter");
			$("#listForm").submit();
	    });
	    
	    $('#editUserBtn').click(function(event) {
	    	
	    	if($('#roleId').val() == 0){
	    		alert("Role must be selected!");
	    		$('#roleId').focus();
	    		return false;
	    	}
	    	
	    	if($('#password').val() == ''){
	    		alert("Password must be entered");
	    		$('#Password').focus();
	    		return false;
	    	}
	    	if($('#confirmPassword').val() == ''){
	    		alert("Confirm Password must be entered");
	    		$('#confirmPassword').focus();
	    		return false;
	    	}
	    	if($('#password').val() != $('#confirmPassword').val()){
	    		alert("Password & Confirm Password are not same");
	    		$('#Password').focus();
	    		return false;
	    	}
	    	
	    	if($('#password').val().length < 6 || $('#confirmPassword').val() < 6){
	    		alert("Password or Confirm Password lenght must have atleast 6 characters");
	    		$('#Password').focus();
	    		return false;
	    	}
	    	
	    	var passReg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/; ///^(?=.*\d)(?=.*[a-zA-Z]).{6,20}$/;
	    	if (!passReg.test($('#password').val())) {
	    		alert("Password must have atleast one letter, one number ");
	    		$('#Password').focus();
	    		return false;
	    		
	    	}
	    	
	    	$("#userForm").attr("action", "../Payroll/editUserConfirm");
			$("#userForm").submit();
	    });
	    
});
  
function roleSelection() {
	var roleId = $('#roleId').val();
	$('#roleDesc').html('');
	$.each(rolesList, function( index, value ) {
		if (value.roleId == roleId) {
			$('#roleDesc').html(value.roleDesc);
		}
	});
}

</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;font-size:12pt;">
				Update User
			</h4>
			
			<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
			<div class="row">
			<form:form method = "POST" action = "" id="userForm" autocomplete="off">
				<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Role</label>
								<select id="roleId" name="roleId" class="form-control" onchange="roleSelection()"> 
									<option value="0">-- Select Role --</option></select>
							</div>
							
							<div class="col-sm-6 form-group" style="vertical-align: bottom">
								<span style="color: blue;font-weight:bold;" id="roleDesc"></span>
							</div>
						</div>
						<div class="row">	
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<div class="ui-widget">
								${user.employee.department}
								<form:input type="hidden" path="deptId"/>
								</div>
							</div>
							<div class="col-sm-6 form-group">
								<label>Employee</label>
								<div class="ui-widget">
								${user.employee.fullName}
								<form:input type="hidden" path="empId"/>
								</div>
							</div>
							
						</div>	
						
						<div class="row">
							<div class="col-sm-6 form-group">
							<label>User Name</label>
							<div class="ui-widget">
							${user.userId}
							<form:input type="hidden" id="userId" path="userId"/>
							</div>
							</div>
							<div class="col-sm-6 form-group">
							<label></label>
							</div>
						</div>	
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Password</label>
								<form:input type="password" id="password" path="password" placeholder="Enter Password" class="form-control"/>
							</div>	
							<div class="col-sm-6 form-group">
								<label>Confirm Password</label>
								<form:input type="password" id="confirmPassword" path="confirmPassword" placeholder="Enter Confirm Password" class="form-control"/>
							</div>		
						</div>			
						
						<div class="row">
							<div class="col-sm-12 form-group text-right">
							<button type="button" id="editUserBtn" class="btn">Update</button>
							<button type="reset" class="btn" id="resetBtn">Reset</button>	
							<button type="button" id="usersListBtn"  class="btn">Users List</button>	
						</div>	
						</div>			
					</div>	
					<form:input type="hidden" path="userIdPk"/> 
			</form:form> 
		</div>
	</div>
	</div>
	</div>
	</div>
	<form:form  action="" id="listForm" method="post">
		<form:input type="hidden" path="listDeptId"/>
		<form:input type="hidden" path="listRoleId"/>
	</form:form >
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>