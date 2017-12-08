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
	<%--var designationList = ${designations};--%>
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	<%--$.each(designationList, function( index, value ) {
		$('<option>').val(value.designationId).text(value.designationName).appendTo('#designationId');
	});--%>
	
	var deptId = "${salary.departmentId}";
	var desgId = "${salary.designationId}";
	var headId = "${salary.headId}";
	var empId = "${salary.empId}";
	$('#departmentId').val(deptId);
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	<%--$('#designationId').val(desgId);--%>
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#addSalaryBtn').click(function(event) {
		
			var year = "${salary.year}";
			var basic = "${salary.basic}";
			var gradePay = "${salary.gradePay}";
			var scalePay = "${salary.scalePay}";
			var scaleInc = "${salary.scaleInc}";
			if(empId !=0){
				if(year == $('#year').val() && 
						basic == $('#basic').val() && gradePay == $('#gradePay').val() && 
						scalePay == $('#scalePay').val() && scaleInc == $('#scaleInc').val()){
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
		if($('#basic').val() < 1){
			alert("Basic Pay must be valid!");
			$('#basic').focus();
			return false;
		}
		if($('#gradePay').val() < 1){
			alert("Grade Pay must be valid!");
			$('#gradePay').focus();
			return false;
		}
		if($('#scalePay').val() < 1){
			alert("Scale Pay must be valid!");
			$('#scalePay').focus();
			return false;
		}
		<%--if($('#scaleInc').val() == ''){
			alert("Scale Inc must be provided!");
			$('#scalePay').focus();
			return false;
		}--%>
		var empIdInput = 0;
		if(empId !=0)
			empIdInput = empId;
		else
			empIdInput = $('#employeeId').val();
		var inputJson = { "empId" : empIdInput, "basic" : $('#basic').val(),  
				"year" : $('#year').val(), "gradePay" : $('#gradePay').val(), 
				"scalePay": $('#scalePay').val(), "scaleInc": $('#scaleInc').val(), "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addSalary',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewSalary";
	            }else{
	            	alert(data);
	            }
	        }
	    });
	    event.preventDefault();
	});
	
});
    
</script>
<jsp:include page="../jsp/public/master.jsp" />
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:14px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${salary.empId != '0'}" >	Update</c:if><c:if test="${salary.empId == '0'}">Add</c:if> Employee Salary
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${salary.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${salary.empId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
							
							<div class="col-sm-4 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${salary.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${salary.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Year:</label>
									<form:input path="year" placeholder="(YYYY)"  id="year" class="form-control" value=""/>
								</div>
							
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Basic Pay:</label>
									<form:input path="basic"  id="basic" placeholder="Enter Basic Pay" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>Grade Pay:</label>
									<form:input path="gradePay"  id="gradePay" placeholder="Enter Grade Pay" class="form-control"/>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Scale Pay:</label>
									<form:input path="scalePay"  id="scalePay" placeholder="Enter Scale Pay" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>Scale Increment:</label>
									<form:input path="scaleInc"  id="scaleInc" placeholder="Enter Scale Inc" class="form-control"/>
									<input type="hidden" name="addUpdate" <c:if test="${salary.empId != '0'}" > value="1" </c:if>/>  
									
								</div>
							</div>
							
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addSalaryBtn" class="btn">Submit</button>
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