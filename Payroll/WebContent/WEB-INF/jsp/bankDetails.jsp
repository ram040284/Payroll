<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Bank Details</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
<style type="text/css">
td, th {
	padding: 3px;
}

.buttonPadding {
	padding: 5px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {

	$('#addBanksBtn').click(function(event) { 
		if($('#bankId').val()!= "0"){
			var bankName = "${bankDetails.bankName}";
			var ifscCode = "${bankDetails.ifscCode}";
			if(bankName == $('#bankName').val() && ifscCode == $('#ifscCode').val()){
				alert('Nothing was changed');
				$('#bankName').focus();
				return false;
			}
		}
		if($('#bankName').val().trim() == ""){
			alert("Bank Name must be provided!");
			$('#bankName').focus();
			return false;
		}
		if($('#ifscCode').val().trim() == ""){
			alert("IFSC Code must be provided!");
			$('#bankName').focus();
			return false;
		}
		var inputJson = { "bankName" : $('#bankName').val(), "ifscCode" : $('#ifscCode').val(), "bankId" : $('#bankId').val()};
	   $.ajax({
	        url: '../Payroll/addBankDetails',
	        data: JSON.stringify(inputJson),
	        type: "POST", 
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
		        if(data == "Yes"){
		           	window.location = "../Payroll/viewBankDetails";
		        }else {
		        	alert(data);
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
				<c:if test="${bankDetails.bankId != '0'}" >Update</c:if><c:if test="${bankDetails.bankId == '0'}">Add</c:if> Bank Details
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
		<div class="row">
		<form:form method = "POST" action = "">
		<div class="col-sm-12">
			<div class="form-group">
				<label>Bank Name</label>
				<form:input path="bankName" id="bankName" class="form-control"/>
				<form:input type="hidden" path="bankId" id="bankId" />
			</div>
			<div class="form-group">
				<label>IFSC Code</label>
				<form:input path="ifscCode" id="ifscCode" class="form-control"/>
			</div>
			<div class="row">	
				<div class="text-right">
					<button type="button" id="addBanksBtn" class="btn">Submit</button>
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