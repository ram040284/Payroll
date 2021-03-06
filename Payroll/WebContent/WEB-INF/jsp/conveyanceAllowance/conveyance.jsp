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
	$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});
	$('#addConvBtn').click(function(event) {
		if($('#conveyanceId').val()!= "0"){
			var conveyanceAmount = "${conveyance.conveyanceAmount}";
			var designationId = "${conveyance.designationId}";
			if(conveyanceAmount == $('#conveyanceAmount').val() && designationId == $('#designationId').val()){
				alert('Nothing was changed');
				$('#designationId').focus();
				return false;
			}
		}
		if($('#designationId').val() == 0){
			alert("Designation must be selected!");
			$('#designationId').focus();
			return false;
		}
		if($('#conveyanceAmount').val() == 0){
			alert("Conveyance Amount must be provided!");
			$('#conveyanceAmount').focus();
			return false;
		}
		var inputJson = { "conveyanceAmount" : $('#conveyanceAmount').val(), "designationId" : $('#designationId').val(), "conveyanceId" : $('#conveyanceId').val()};
	    $.ajax({
	        url: '../Payroll/addConveyance',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("Accept", "application/json");
	            xhr.setRequestHeader("Content-Type", "application/json");
	        },
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewConveyance";
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
					
					<c:if test="${conveyance.conveyanceId != '0'}" >
					Update
					</c:if><c:if test="${conveyance.conveyanceId == '0'}">
						Add
					</c:if>
					 Conveyance Allowances
			</h4>

		<div class="col-lg-12 card card-block bg-faded">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control"><option value="0">-- Select Designation --</option></select>
							</div>
							<div class="form-group">
								<label>Conveyance Amount:</label>
								<form:input path="conveyanceAmount"  id="conveyanceAmount" />
								<form:input type="hidden" path="conveyanceId" id="conveyanceId" />
							</div>
						</div>
						<div class="row">	
							<div class="text-right">
								<button type="button" id="addConvBtn" class="btn btn-lg btn-info">Submit</button>
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