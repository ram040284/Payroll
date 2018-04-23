<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Department</title>

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

	$('#addDeptBtn').click(function(event) { 
		var deptNameInp = $('#departmantName').val().trim();
		var sectionInp = $('#section').val().trim();
		var descriptionInp = $('#description').val().trim();
		if($('#departmentId').val()!= "0"){
			var deptName = "${department.departmantName}";
			var section = "${department.section}";
			var description = "${department.description}";
			if(deptName == deptNameInp && section == sectionInp && description == descriptionInp){
				alert('Nothing was changed');
				$('#departmantName').focus();
				return false;
			}
		}
		
		if(deptNameInp == ""){
			alert("Department name must be provided!");
			$('#departmantName').focus();
			return false;
		}
		if(sectionInp == ""){
			alert("Section name must be provided!");
			$('#section').focus();
			return false;
		}
		
		<%--if(!alphaOnly(deptName)){
			alert("Department name must allow only letters and space!");
			$('#departmantName').focus();
			return false;
		}--%>
		var inputJson = { "departmantName" : $('#departmantName').val(), "departmentId" : $('#departmentId').val(), "section" : sectionInp, "description" : descriptionInp};
	   	$.ajax({
	        url: '../Payroll/addDept',
	        data: JSON.stringify(inputJson),
	        type: "POST", 
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
		        if(data == "Yes"){
		           	window.location = "../Payroll/viewDept";
		        }else {
		        	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
		       }
	        }
	    });
	    event.preventDefault();
	});
	$('#backBtn').click(function(event) { 
		window.location = "../Payroll/viewDept";
	});
});
function alphaOnly(value){
	if (!/^[a-zA-Z\s]+$/.test(value)) {
        return false;
    }
}     
      </script>
</head>
<body>

	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div style="display: none;color: red; font-weight:bold; height: 15px; margin: 5px;" id="errMsgDiv"></div>	
			
		<div class="formDiv" style="border: none;">
			<div class="row">
				<div class="text-left" style="margin-left: 15px;">
					<button type="button" id="backBtn" class="btn">Back</button>
				</div>
			</div>
		</div>	
		<div class="formDiv">
					
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${department.departmentId != '0'}" >Update</c:if><c:if test="${department.departmentId == '0'}">Add</c:if> Department
			</h4>
			
		<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
		<div class="row">
		<form:form method = "POST" action = "">
		<div class="col-sm-12">
			<div class="col-sm-6 form-group">
				<label>Department Name</label>
				<form:input path="departmantName" id="departmantName" class="form-control"/>
				<form:input type="hidden" path="departmentId" id="departmentId" />
			</div>
			<div class="col-sm-6 form-group">
				<label>Section</label>
				<form:input path="section" id="section" class="form-control"/>
			</div>
			<div class="form-group">
				<label>Description</label>
				<form:input path="description" id="description" class="form-control"/>
			</div>
			<div class="row">	
				<div class="text-right">
					<button type="button" id="addDeptBtn" class="btn">Submit</button>
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