<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add/Update Pay Head</title>
<style type="text/css">
td, th {
	padding: 3px;
}

.buttonPadding {
	padding: 5px;
}
</style>
<jsp:include page="../jsp/public/postHeader.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	$('#addPayHeadBtn').click(function(event) {
		if($('#headId').val()!= "0"){
			var headValue = "${payHead.headValue}";
			var headName = "${payHead.headName}";
			if(headName == $('#headName').val() && headValue == $('#headValue').val()){
				alert('Nothing was changed');
				$('#headName').focus();
				return false;
			}
		}
		if($('#headName').val() == ''){
			alert("Pay-Head Name must be provided!");
			$('#headName').focus();
			return false;
		}
		if($('#headValue').val() == ''){
			alert("Pay-Head Value must be provided!");
			$('#headValue').focus();
			return false;
		}
		var inputJson = { "headName" : $('#headName').val(), "headValue" : $('#headValue').val(), "headId" : $('#headId').val()};
	    $.ajax({
	        url: '../Payroll/addPayHead',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewPayHead";
	            }
	        }
	    });
	    event.preventDefault();
	});
});
     
      </script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${payHead.headId != '0'}" >Update</c:if><c:if test="${payHead.headId == '0'}">Add</c:if> Pay-Head Amount
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						
							<div class="form-group">
								<label>Pay-Head Name:</label>
								<form:input path="headName"  id="headName" class="form-control"/>
							</div>
							
							<div class="form-group">
								<label>Default Value:</label>
								<form:input path="headValue"  id="headValue" class="form-control"/>
							</div>
						
						<div class="row">	
							<div class="text-right form-group">
								<button type="button" id="addPayHeadBtn" class="btn">Submit</button>
								<button type="reset" class="btn">Reset</button>	
							</div>	
						</div>
					</div>
						
				</form:form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>