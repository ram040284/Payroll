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
<jsp:include page="../jsp/public/postHeader.jsp" />
<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${headDetails.departmentId}";
	$('#departmentId').val(deptId);
	
	$('#addHeadBtn').click(function(event) {
		if($('#headId').val()!= "0"){
			var headName = "${headDetails.headName}";
			var description = "${headDetails.description}";
			if(headName == $('#headName').val() && description == $('#description').val()){
				alert('Nothing was changed');
				$('#headName').focus();
				return false;
			}
		}
		if($('#departmentId').val() == 0){
			alert("Department must be selected!");
			$('#departmentId').focus();
			return false;
		}
		if($('#headName').val() == ''){
			alert("Head Name must be provided!");
			$('#headName').focus();
			return false;
		}
		var inputJson = { "headName" : $('#headName').val(), "departmentId" : $('#departmentId').val(), "headId" : $('#headId').val(), "description": $('#description').val()};
	    $.ajax({
	        url: '../Payroll/addHead',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewHeads";
	            }else {
	            	alert(data);
	            	return;
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
				<c:if test="${headDetails.headId != '0'}" > Update	</c:if><c:if test="${headDetails.headId == '0'}">	Add	</c:if>Head Details
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
							<div class="form-group">
								<label>Department:</label>
								<select id="departmentId" class="form-control"><option value="0">-- Select Department --</option></select>
							</div>
							<div class="form-group">
								<label>Head Name:</label>
								<form:input path="headName"  id="headName" class="form-control"/>
								<form:input type="hidden" path="headId" id="headId" />
							</div>
							<div class="form-group">
								<label>Description:</label>
								<form:input path="description"  id="description" class="form-control"/>
							</div>
						<div class="row">	
							<div class="text-right">
								<button type="button" id="addHeadBtn" class="btn">Submit</button>
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