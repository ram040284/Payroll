<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Generic Exception</title>
        <c:if test="${not empty sessionScope.user}">
        <jsp:include page="../jsp/public/postHeader.jsp" />
        </c:if>
        <c:if test="${empty sessionScope.user}">
        	<jsp:include page="../jsp/public/preHeader.jsp" />
        </c:if>
        <script type="text/javascript">
        	<%--function nextPage(path){
        		document.location.href="";
        	}--%>
        </script>
    </head>
    <body>
    <div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="row">
		<div class="col-md-12 text-center">
        <h4 style="color: red;">Failed to process your request, try again!</h4>
        </div>
     </div>
     <div class="row">
		<div class="col-md-12 text-center">
        <span style="font-size: 1.2em; color:blue;">
	       <c:if test="${not empty sessionScope.user}">
	       	<a href="../Payroll/login">Login</a>
	       </c:if>
	       <c:if test="${empty sessionScope.user}">
	       	<a href="../Payroll/login">Home</a>
	       </c:if>  
        </span>
        </div>
     </div>
   </div>
   </div>
   <jsp:include page="../jsp/public/postFooter.jsp" />
    </body>
</html>