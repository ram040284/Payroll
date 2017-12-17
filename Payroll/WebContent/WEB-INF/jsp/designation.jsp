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
	var deptId = "${designation.departmentId}";
	$('#departmentId').val(deptId);
	var headId = "${designation.headId}";
	if(deptId !=0) {
		getHeadsByDept(deptId);		
	}
	$('#addDsgBtn').click(function(event) {
		if($('#designationId').val()!= "0"){
			var deptName = "${designation.designationName}";
			var description = "${designation.description}";
			if(deptName == $('#designationName').val() && description == $('#description').val()){
				alert('Nothing was changed');
				$('#designationName').focus();
				return false;
			}
		}
		if($('#designationName').val().trim() == ""){
			alert("Designation name must be provided!");
			$('#designationName').focus();
			return false;
		}
		var inputJson = {"departmentId":$('#departmentId').val(), "headId":$('#headId').val(), "designationName" : $('#designationName').val(), "designationId" : $('#designationId').val(), "description" : $('#description').val()};
	    $.ajax({
	        url: '../Payroll/addDesg',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewDesg";
	            }else{
	            	alert(data);
	            }
	        }
	    });
	    event.preventDefault();
	});
});

function getHeads(){
	if($('#departmentId').val() == 0){
		alert("Department must be selected to get Employees!");
		$('#departmentId').focus();
		return false;
	}
	var deptId = $('#departmentId').val();
	getHeadsByDept(deptId);
}

function getHeadsByDept(deptId) {
	var inputJson = { "departmentId" : deptId};
	  $.ajax({
	    url: '../Payroll/loadHeads',
	    data: JSON.stringify(inputJson),
	    type: "POST",           
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    success: function(data){ 
	    	$('#headId').empty();
	    	$('<option>').val(0).text("-- Select Head --").appendTo('#headId');
	    	$(data).each(function(i, headInfo){
	    		$('<option>').val(headInfo.headId).text(headInfo.headName).appendTo('#headId');
	    	});
	    	var headId = "${designation.headId}";
	    	if(headId !=0) {
	  		$('#headId').val(headId);
	  		
	  	}
	    },
	    failure: function (){
	    	alert('Unable to load Heads');
	    }
	});

}
     
      </script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
	
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${designation.designationId != '0'}" >Update</c:if><c:if test="${designation.designationId == '0'}">Add</c:if> Designation
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Department:</label>
								<select id="departmentId" class="form-control" onchange="getHeads()" 
								<c:if test="${designation.designationId != '0'}" > disabled="disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" <c:if test="${designation.designationId != '0'}" > disabled="disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Designation Name:</label>
								<form:input path="designationName"  id="designationName" class="form-control"/>
								<form:input type="hidden" path="designationId" id="designationId" />
							</div>
							<div class="col-sm-6 form-group">
								<label>Description:</label>
								<form:input path="description"  id="description" class="form-control"/>
							</div>
						</div>
						<div class="row">	
							<div class="text-right">
								<button type="button" id="addDsgBtn" class="btn">Submit</button>
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