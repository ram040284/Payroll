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
	var taxId = "${incomtaxSlab.incomtaxId}";
	$('#addTaxSlab').click(function(event) { 
		if(taxId!= 0){
			var financialYear = "${incomtaxSlab.financialYear}";
			var lowerSlab = "${incomtaxSlab.lowerSlab}";
			var higherSlab = "${incomtaxSlab.higherSlab}";
			var incomtaxPercent = "${incomtaxSlab.incomtaxPercent}";
			var surcharge = "${incomtaxSlab.surcharge}";
			var educationCess = "${incomtaxSlab.educationCess}";
			var otherCess = "${incomtaxSlab.otherCess}";
			
			
			if(financialYear == $('#financialYear').val() && lowerSlab == $('#lowerSlab').val() && 
					higherSlab == $('#higherSlab').val() && incomtaxPercent == $('#incomtaxPercent').val() &&
					surcharge == $('#surcharge').val() && educationCess == $('#educationCess').val() &&
					otherCess == $('#otherCess').val()){
				alert('Nothing was changed');
				$('#financialYear').focus();
				return false;
			}
		}
		if($('#financialYear').val().trim() == ""){
			alert("Fianancial year must be provided!");
			$('#financialYear').focus();
			return false;
		}
		var lowerSlabVal = $('#lowerSlab').val().trim();
		if(lowerSlabVal == ""){
		    alert("Lower Slab must be provided!");
			$('#lowerSlab').focus();
			return false;
		}
		if(isNaN(lowerSlabVal)){
			alert("Lower Slab must be Number!");
			$('#lowerSlab').focus();
			return false;
		}
		if(lowerSlabVal > 0){
			if(!checkAmount(lowerSlabVal)){
				alert("Invalid Lower Slab!");
				$('#lowerSlab').focus();
				return false;
			}
		}
		var higherSlabVal = $('#higherSlab').val().trim();
		if(lowerSlabVal == ""){
		    alert("Higher Slab must be provided!");
			$('#higherSlab').focus();
			return false;
		}
		if(isNaN(lowerSlabVal)){
			alert("Higher Slab must be Number!");
			$('#higherSlab').focus();
			return false;
		}
		if(higherSlabVal > 0){
			if(!checkAmount(higherSlabVal)){
				alert("Invalid Higer Slab!");
				$('#higherSlab').focus();
				return false;
			}
		}
		var incomtaxPercentVal = $('#incomtaxPercent').val().trim();
		
		if(incomtaxPercentVal && incomtaxPercentVal > 0){
			alert('incomtaxPercentVal:'+incomtaxPercentVal);
			if(!checkAmount(incomtaxPercentVal)){
				alert("Invalid Incometax Percentage!");
				$('#incomtaxPercent').focus();
				return false;
			}
		}
		var surchargeVal = $('#surcharge').val().trim();
		if(surchargeVal && surchargeVal > 0){
			if(!checkAmount(surchargeVal)){
				alert("Invalid Surchanges!");
				$('#surcharge').focus();
				return false;
			}
		}
		var educationCessVal = $('#educationCess').val().trim();
		if(educationCessVal && educationCessVal > 0){
			if(!checkAmount(educationCessVal)){
				alert("Invalid Education Cess!");
				$('#educationCess').focus();
				return false;
			}
		}
		var otherCessVal = $('#otherCess').val().trim();
		if(otherCessVal && otherCessVal > 0){
			if(!checkAmount(otherCessVal)){
				alert("Invalid Other Cess!");
				$('#otherCess').focus();
				return false;
			}
		}
		var inputJson = { "financialYear" : $('#financialYear').val(), "lowerSlab" : $('#lowerSlab').val(), "higherSlab" : $('#higherSlab').val(),
				"incomtaxPercent" : $('#incomtaxPercent').val(), "surcharge" : $('#surcharge').val(), "educationCess" : $('#educationCess').val(),
				"otherCess" : $('#otherCess').val(), "incomtaxId" : $('#incomtaxId').val()};
	   $.ajax({
	        url: '../Payroll/addTaxSlab',
	        data: JSON.stringify(inputJson),
	        type: "POST", 
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
		        if(data == "Yes"){
		           	window.location = "../Payroll/viewTaxSlab";
		        }else {
		        	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
		        }
	        }
	    });
	    event.preventDefault();
	});
});
function checkAmount(value){
	if(value.indexOf(".")!= -1){
		if(value.split(".")[1].length == 1)
			value = value + "0";
	}
	var decimal=  /^\d+(\.\d{2,2})?$/;   
	if(value.match(decimal)) {   
		return true;  
	}
	return false;
}    
      </script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${incomtaxSlab.incomtaxId != '0'}" >Update</c:if><c:if test="${incomtaxSlab.incomtaxId == '0'}">Add</c:if> Incometax Slab
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
		<div class="row">
		<form:form method = "POST" action = "">
		<div class="col-sm-12">
			<div class="row">
			<div class="col-sm-4 form-group">
				<label>Financial Year</label>
				<form:input path="financialYear" id="financialYear" class="form-control"/>
				<form:input type="hidden" path="incomtaxId" id="incomtaxId" />
			</div>
			<div class="col-sm-4 form-group">
				<label>Lower Slab</label>
				<form:input path="lowerSlab" id="lowerSlab" class="form-control"/>
			</div>
			<div class="col-sm-4 form-group">
				<label>Higher Slab</label>
				<form:input path="higherSlab" id="higherSlab" class="form-control"/>
			</div>
			</div>
			<div class="row">
			<div class="col-sm-6 form-group">
				<label>Incometax Percentage</label>
				<form:input path="incomtaxPercent" id="incomtaxPercent" class="form-control"/>
			</div>
			<div class="col-sm-6 form-group">
				<label>Surcharges</label>
				<form:input path="surcharge" id="surcharge" class="form-control"/>
			</div>
			</div>
			<div class="row">
			<div class="col-sm-6 form-group">
				<label>Education Cess</label>
				<form:input path="educationCess" id="educationCess" class="form-control"/>
			</div>
			<div class="col-sm-6 form-group">
				<label>Other Cess</label>
				<form:input path="otherCess" id="otherCess" class="form-control"/>
			</div>
			</div>
			<div class="row">	
				<div class="text-right">
					<button type="button" id="addTaxSlab" class="btn">Submit</button>
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