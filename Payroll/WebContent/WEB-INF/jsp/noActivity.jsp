<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>No Activity</title>

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
	function closeWin(){
		window.close();
	}
</script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
	<div class="container">
	<div class="row">
		<div class="col-md-12 text-center">
		
			<h4 style="color:red;">Report is not exist for selected Department and Date!</h4>
</div>		
	</div>	
	<div class="row">
<div class="col-md-12 text-center">

<span style="margin-top: 20px; font-size: 1.4em;"><a href="#" onclick="closeWin()">Close Window</a></span>
</div> 
</div>			
</div>
</div>
	
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>