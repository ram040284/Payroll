<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Designation</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
<%--<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/> --%>

<script type="text/javascript">
$(document).ready(function() {
	<%--var designationList = ${designations};
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});
	$('#paymentDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#inlineDatepicker').datepick({onSelect: showDate});	--%>
	
	$('#addAdvBtn').click(function(event) {
		var advanceId = "${advance.advanceId}";
		if(advanceId != 0){
			var advanceAmount = "${advance.advanceAmount}";
			var advanceName = "${advance.advanceName}";
			<%--var designationId = "${advance.designationId}";
			var departmentId = "${advance.departmentId}";--%>
			<%--var paymentDate = "${advance.paymentDate}";--%>
			if(advanceAmount == $('#advanceAmount').val() && advanceName == $('#advanceName').val()){
				alert('Nothing was changed');
				$('#advanceName').focus();
				return false;
			}
		}
		if($('#advanceName').val() == 0){
			alert("Advance Name must be provided!");
			$('#advanceName').focus();
			return false;
		}
		<%--if($('#paymentDate').val() == 0){
			alert("Payment Date must be selected!");
			$('#paymentDate').focus();
			return false;
		}--%>
		var advAmountVal = $('#advanceAmount').val().trim();
		if(advAmountVal){
			if(!checkAmount(advAmountVal)){
				alert("Invalid Advance Amount!");
				$('#advanceAmount').focus();
				return false;
			}
		}else {
			alert("Advance Amount must be provided!");
			$('#advanceAmount').focus();
			return false;
		}
		
		var inputJson = { "advanceAmount" : advAmountVal, "advanceId" : $('#advanceId').val(), 
				"advanceName" : $('#advanceName').val()};
	    $.ajax({
	        url: '../Payroll/addAdvance',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewAdvance";
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
	var decimal=  /^\d+(\.\d{2,2})?$/;   
	if(value.match(decimal)) {   
		return true;  
	}
	return false;
}
function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${advance.advanceId != '0'}" >Update</c:if><c:if test="${advance.advanceId == '0'}">Add</c:if> Advance Amount
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
							<div class="form-group">
								<label>Advance Name:</label>
									<form:input path="advanceName" id="advanceName" placeholder="Enter Advance Name" class="form-control"/>
							</div>
							<%--<div class="form-group">
								<label>Payment Date:</label>
								<form:input path="paymentDate" id="paymentDate" placeholder="Enter Date (DD/MM/YYYY)" class="form-control"/>
							</div> --%>
							
							<div class="form-group">
								<label>Advance Amount:</label>
								<form:input path="advanceAmount"  id="advanceAmount" placeholder="Enter Advance Amount" class="form-control"/>
								<form:input type="hidden" path="advanceId" id="advanceId" />
							</div>
						<div class="row">	
							<div class="text-right form-group">
								<button type="button" id="addAdvBtn" class="btn">Submit</button>
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