<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Designation</title>
<style type="text/css">
td, th {
	padding: 3px;
}

.buttonPadding {
	padding: 5px;
}
</style>
<jsp:include page="../public/postHeader.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	var designationList = ${designations};
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});
	$('#addAdvBtn').click(function(event) {
		if($('#advanceId').val()!= "0"){
			var advanceAmount = "${advance.advanceAmount}";
			var designationId = "${advance.designationId}";
			var departmentId = "${advance.departmentId}";
			var paymentDate = "${advance.paymentDate}";
			if(advanceAmount == $('#conveyanceAmount').val() && designationId == $('#designationId').val() && 
					departmentId == $('#departmentId').val() && paymentDate == $('#paymentDate').val()){
				alert('Nothing was changed');
				$('#departmentId').focus();
				return false;
			}
		}
		if($('#departmentId').val() == 0){
			alert("Department must be selected!");
			$('#departmentId').focus();
			return false;
		}
		if($('#designationId').val() == 0){
			alert("Designation must be selected!");
			$('#designationId').focus();
			return false;
		}
		if($('#paymentDate').val() == 0){
			alert("Payment Date must be selected!");
			$('#paymentDate').focus();
			return false;
		}
		if($('#advanceAmount').val() == 0){
			alert("Advance Amount must be provided!");
			$('#advanceAmount').focus();
			return false;
		}
		var inputJson = { "advanceAmount" : $('#advanceAmount').val(), "departmentId" : $('#departmentId').val(), 
				"designationId" : $('#designationId').val(), "advanceId" : $('#advanceId').val(), "paymentDate": $('#paymentDate').val()};
	    $.ajax({
	        url: '../Payroll/addAdvance',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewAdvance";
	            }
	        }
	    });
	    event.preventDefault();
	});
});
     
      </script>
</head>
<body>
	<div class="wrapper">
		<div style="margin-top: 10px; float: left; width: 100%;">
			<h4 class="card card-block bg-faded" style="color: #0101DF;">
					
					<c:if test="${advance.advanceId != '0'}" >
					Update
					</c:if><c:if test="${advance.advanceId == '0'}">
						Add
					</c:if>
					 Advance Amount
			</h4>

		<div class="col-lg-12 card card-block bg-faded">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control"><option value="0">-- Select Department --</option></select>
							</div>
							
							<div class="form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control"><option value="0">-- Select Designation --</option></select>
							</div>
							<div class="form-group">
								<label>Payment Date:</label>
								<form:input path="paymentDate"  id="paymentDate" />
							</div>
							<div class="form-group">
								<label>Advance Amount:</label>
								<form:input path="advanceAmount"  id="advanceAmount" />
								<form:input type="hidden" path="advanceId" id="advanceId" />
							</div>
						</div>
						<div class="row">	
							<div class="text-right">
								<button type="button" id="addAdvBtn" class="btn btn-lg btn-info">Submit</button>
								<button type="reset" class="btn btn-lg btn-info">Reset</button>	
							</div>	
						</div>
					</div>
						
				</form:form>
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="../public/postFooter.jsp" />
</body>
</html>