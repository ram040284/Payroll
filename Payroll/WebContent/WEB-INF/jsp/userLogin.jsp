<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>

<jsp:include page="../jsp/public/preHeader.jsp" />
<style type="text/css">
	@media only screen and (min-width: 1200px) 
  		and (max-width: 1600px) {
        #login {
           width:400px;
           margin:auto;
        }
    } 
    
    @media only screen and (min-width: 768px) 
  		and (max-width: 1024px)  {
        #login {
        	width:400px;
        	margin:auto;
        }
    }
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#password').val("payroll123");
	$('#userName').val("payroll");
	$('#loginBtn').click(function(event) {
		if($('#userName').val().trim() == ""){
			alert("User Name must be provided!");
			$('#userName').focus();
			return false;
		}
		if($('#password').val().trim() == ""){
			alert("Password must be provided!");
			$('#password').focus();
			return false;
		}
		$("#formLogin").attr("action", "../Payroll/home");
		$("#formLogin").submit();
	});
	
	$(document).keypress(function(e) {
	    if(e.which == 13) {
	        $("#formLogin").attr("action", "../Payroll/home");
	        $("#formLogin").submit();
	    }
	});
});
</script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	<form:form id="formLogin" method = "POST" action = "../Payroll/home" autocomplete="off">
	<div id="searchDiv" class="container" style ="position: relative;">
	<div class="panel panel-primary" id="login">
    	<div class="panel-heading" style="margin:0px;padding:0px;background-color: #8B9DC3;">
    	<h5 style="color: #fff; padding:5px;margin:0px; background-color: #8B9DC3; text-transform: none;">
    	Payroll Login</h5></div>
		<div  class="panel-body formDiv" style="padding:10px;margin:0px;width:100%;">
			<div class="col-sm-12">
				<label style="color:red"><c:out value="${errorMsg}"></c:out></label>
			</div>
			<div class="col-sm-4">
				<label>User Name </label> 
			</div>
			<div class="col-sm-8  form-group">
				<form:input type="text" id="userName" path="userName" placeholder="User Name" class="form-control"/>
			</div>
			<div class="col-sm-4 form-group">
				<label>Password </label> 
			</div>
			<div class="col-sm-8 form-group">
				<form:input type="password" id="password" path="password" placeholder="Password" class="form-control"/>
			</div>
			<div class="col-sm-12" style="text-align:right">
				<button type="button" id="loginBtn"  class="btn" value="Submit">Log in</button>
			</div>
		</div>
	</div>
	</div>
	</form:form> 
	</div>			
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>