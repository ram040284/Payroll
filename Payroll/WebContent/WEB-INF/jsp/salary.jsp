<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Emp Salary</title>
<style type="text/css">
td, th {
	padding: 3px;
}
</style>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var deptId = "${salary.departmentId}";
	var headId = "${salary.headId}";
	var desgId = "${salary.designationId}";
	var empId = "${salary.employeeId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	 // Get saved data from sessionStorage
	 var viewFlag = 0;
	 viewFlag = sessionStorage.getItem('view');
	// alert("ViewFlag is "+viewFlag);
	 
	 if (viewFlag ==1){
		/* <c:if test="${salary.employeeId != '0'}" >	View</c:if> Employee Salary */ 
		 $("#year").prop("disabled", true);
		 $("#basic").prop("disabled", true);
		 $("#gradePay").prop("disabled", true);
		 $("#scalePay").prop("disabled", true);
		 $("#scaleCode").prop("disabled", true);
	 }
	 else{
		/*  <c:if test="${salary.employeeId != '0'}" >	Update</c:if> Employee Salary */ 
		 $("#year").prop("disabled", false);
		 $("#basic").prop("disabled", false);
		 $("#gradePay").prop("disabled", false);
		 $("#scalePay").prop("disabled", false);
		 $("#scaleCode").prop("disabled", false);
	 }
	
	$('#addSalaryBtn').click(function(event) {
		
			var year = "${salary.year}";
			var basic = "${salary.basic}";
			var gradePay = "${salary.gradePay}";
			var scalePay = "${salary.scalePay}";
			var scaleCode = "${salary.scaleCode}";
			if(empId !=0){
				if(year == $('#year').val() && 
						basic == $('#basic').val() && gradePay == $('#gradePay').val() && 
						scalePay == $('#scalePay').val() && scaleCode == $('#scaleCode').val()
						&& scaleInc == $('#scaleInc').val()){
					alert('Nothing was changed');
					$('#employeeId').focus();
					return false;
				}
			}
			if(empId ==0){
				if($('#departmentId').val() == 0){
					alert("Department must be selected!");
					$('#departmentId').focus();
					return false;
				}
				if($('#headId').val() == 0){
					alert("Head must be selected!");
					$('#headId').focus();
					return false;
				}
				if($('#designationId').val() == 0){
					alert("Designation must be selected!");
					$('#designationId').focus();
					return false;
				}
				if($('#employeeId').val() == 0){
					alert("Employee must be selected!");
					$('#employeeId').focus();
					return false;
				}
			}
		if($('#year').val().trim() < 1 ){
			alert("Year must be Valid!");
			$('#year').focus();
			return false;
		}
		var basicVal = $('#basic').val().trim();
		if(basicVal != ""){
			if(isNaN(basicVal)){
				alert("Invalid Basic Pay!");
				$('#basic').focus();
				return false;
			}
		}else {
			alert("Basic Pay must be provided!");
			$('#basic').focus();
			return false;
		}
		var gradePayVal = $('#gradePay').val().trim();
		if(gradePayVal){
			if(isNaN(gradePayVal)){
				alert("Invalid Grade Pay!");
				$('#gradePay').focus();
				return false;
			}
		}else {
			alert("Grade Pay must be provided!");
			$('#gradePay').focus();
			return false;
		}
		<%--if($('#basic').val() < 1){
			alert("Basic Pay must be valid!");
			$('#basic').focus();
			return false;
		}
		if($('#gradePay').val() < 1){
			alert("Grade Pay must be valid!");
			$('#gradePay').focus();
			return false;
		}--%>
		if($('#scalePay').val().trim() == ""){
			alert("Scale Pay must be provided!");
			$('#scalePay').focus();
			return false;
		}
		if($('#scaleCode').val().trim() == ""){
			alert("Scale Code must be provided!");
			$('#scaleCode').focus();
			return false;
		}
		var empIdInput = 0;
		if(empId !=0)
			empIdInput = empId;
		else
			empIdInput = $('#employeeId').val();
		
		var inputJson = { "employeeId" : empIdInput, "basic" : $('#basic').val(),  
				"year" : $('#year').val(), "gradePay" : $('#gradePay').val(), 
				"scalePay": $('#scalePay').val(), "scaleCode": $('#scaleCode').val(),  "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addSalary',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewSalary";
	            }else{
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
    
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewSalary#')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${salary.employeeId != '0'}" >	Update</c:if><c:if test="${salary.employeeId == '0'}">Add</c:if> Employee Salary
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${salary.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${salary.employeeId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${salary.employeeId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${salary.employeeId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Year:</label>
									<form:input path="year" placeholder="(YYYY)"  id="year" class="form-control" value=""/>
								</div>
							
							</div>
							<div class="row">
								<div class="col-sm-4 form-group">
									<label>Basic Pay:</label>
									<form:input path="basic"  id="basic" placeholder="Enter Basic Pay" class="form-control"/>
									<input type="hidden" name="addUpdate" id=addUpdate <c:if test="${salary.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Grade Pay:</label>
									<form:input path="gradePay"  id="gradePay" placeholder="Enter Grade Pay" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${salary.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								<div class="col-sm-4 form-group">
									<label>Scale Pay:</label>
									<form:input path="scalePay"  id="scalePay" placeholder="Enter Scale Pay" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${salary.employeeId != '0'}" > value="1" </c:if>/>
								</div>
							</div>
							
							<div class="row">
							<div class="col-sm-4 form-group">
									<label>Scale Code:</label>
									<form:input path="scaleCode"  id="scaleCode" placeholder="Enter Scale Code" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${salary.employeeId != '0'}" > value="1" </c:if>/>
								</div>
								</div>
							
							<%--<div class="row">
								<div class="col-sm-6 form-group">
									<label>Scale Increment:</label>
									<form:input path="scaleInc"  id="scaleInc" placeholder="Enter Scale Inc" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${salary.employeeId != '0'}" > value="1" </c:if>/>  
									
								</div>
							</div>
							 --%>
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addSalaryBtn" class="btn">Submit</button>
									<button type="reset" class="btn">Reset</button>	
								</div>	
							</div>
					</div>
<%-- 					<input type="hidden" id="scaleCode" name="scaleCode" value="${salary.scaleCode}">
 --%>				</form:form>
			</div>
		</div>
	</div>
	</div>
	</div>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>