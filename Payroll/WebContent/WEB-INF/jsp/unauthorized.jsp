<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../jsp/public/postHeader.jsp" />
<html>
<body>
<div class="contain-wrapp bodyDivCss">	
	<div class="container">
		<c:if test="${unauthorizedMessage == true}">
			<div class="row">
			<div class="col-md-12 text-center">
			<h3 style="color:blue;">${message}</h3>
			</div></div>
		</c:if>
	</div>
</div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>