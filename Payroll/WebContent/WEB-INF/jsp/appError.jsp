<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Application Exception</title>
    </head>
     
    <body>
    	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="row">
		<div class="col-md-12 text-center">
    	<h2>Exception occurred at: </h2><fmt:formatDate value="${exception.date}" pattern="yyyy-MM-dd" />
        <h2>Exception Message   : </h2>${exception.message}
        </div>
        </div>
        </div>
        </div>
    </body>
</html>