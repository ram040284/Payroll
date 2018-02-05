<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<title>Generate Paybills</title>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">

</script>

</head>
<body>
<div class="contain-wrapp bodyDivCss">	
<div class="container">
<%--<h5 style="color: #0101DF;">Paybill Report</h5> --%>
<div class="row">
		<div class="col-md-12 text-center">
		
<h4 style="color:blue;"> <c:if test="${result == '1'}" > Paybills are exist for selected Department and Month!</c:if>
<c:if test="${result == '2'}" > Paybills have been generated for selected Department and Month!</c:if>
</h4>
<c:if test="${result == '3'}" > 
<h4 style="color:Red;"> Unable to generate Paybills for selected Department and Month!</h4></c:if>
</div></div>
<div class="row">
<div class="col-md-12 text-center">

<span style="margin-top: 20px; font-size: 1.4em;"><a href="#" onclick="getList('../Payroll/inputPaybill')">See Report</a></span>
</div> 
</div></div></div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>