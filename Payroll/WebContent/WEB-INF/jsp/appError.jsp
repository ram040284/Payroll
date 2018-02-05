<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Error!</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
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
        
    </head>
     
    <body>
    	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="row">
		<div class="col-md-12 text-center">
    	<span style="color: red; font-size: 1.2em">Error occurred at: <fmt:formatDate value="${exception.date}" pattern="yyyy-MM-dd" /></span>
        </div>
        </div>
        <div class="row">
        <div class="col-md-12 text-center">
    	<span style="color: red; font-size: 1em">
        	<b>Error Message:</b> ${exception.message}
        </span>
        </div>
        </div>
        <div class="row">
        <div class="col-md-12 text-center">
        <span style="color: blue; font-size: 1em; text-decoration: underline;">
        	<a href="../Payroll/home">Home</a>
        </span>
    	</div>
    	</div>
        </div>
        </div>
        <jsp:include page="../jsp/public/postFooter.jsp" />
    </body>
</html>