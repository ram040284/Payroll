<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Emp Bank</title>
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
	var deptId = "${bank.departmentId}";
	var desgId = "${bank.designationId}";
	var headId = "${bank.headId}";
	$('#departmentId').val(deptId);
	<%--$('#designationId').val(desgId);--%>
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${bank.empId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#addBankBtn').click(function(event) {
		var bankName = "${bank.bankName}";
		var ifscCode = "${bank.ifscCode}";
		var accountNo = "${bank.accountNo}";
		if(empId !=0){
			if(bankName == $('#bankName').val() && 
				ifscCode == $('#ifscCode').val() && accountNo == $('#accountNo').val()){
				alert('Nothing was changed');
				$('#bankName').focus();
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
		if($('#bankName').val() == ''){
			alert("Bank Name must be selected!");
			$('#bankName').focus();
			return false;
		}
		if($('#ifscCode').val() == ''){
			alert("IFSC Code must be provided!");
			$('#ifscCode').focus();
			return false;
		}
		if($('#accountNo').val() == ''){
			alert("Account No must be provided!");
			$('#accountNo').focus();
			return false;
		}
		var inputJson = { "empId" : $('#employeeId').val(), "accountNo" : $('#accountNo').val(),  
				"bankName" : $('#bankName').val(), "ifscCode" : $('#ifscCode').val(), "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addBank',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){ 
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewBank";
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
				<c:if test="${bank.empId != '0'}" >	Update</c:if><c:if test="${bank.empId == '0'}">Add</c:if> Employee Bank
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Department</label>
								<select id="departmentId" class="form-control" onchange="getHeads()"
								<c:if test="${bank.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Department --</option>
								</select>
							</div>
							<div class="col-sm-4 form-group">
								<label>Head:</label>
								<select id="headId" class="form-control" onchange="loadDesignations()"
								<c:if test="${bank.empId != '0'}" > disabled= "disabled" </c:if>>
								<option value="0">-- Select Head --</option></select>
							</div>
		
							<div class="col-sm-4 form-group">
								<label>Designation:</label>
								<select id="designationId" class="form-control" onchange="getEmployees()"
								<c:if test="${bank.empId != '0'}" >disabled = "disabled" </c:if>>
									<option value="0">-- Select Designation --</option>
								</select>
							</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test="${bank.empId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Bank Name:</label>
									<form:input path="bankName"  id="bankName" placeholder="Enter Bank Name" class="form-control"/>
								</div>
							
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>IFSC Code:</label>
									<form:input path="ifscCode"  id="ifscCode" placeholder="Enter IFSC Code" class="form-control"/>
									
								</div>
								<div class="col-sm-6 form-group">
									<label>Account No:</label>
									<form:input path="accountNo"  id="accountNo" placeholder="Enter Account No" class="form-control"/>
									<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${bank.empId != '0'}" > value="1" </c:if>/>
								</div>
							</div>
							
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addBankBtn" class="btn">Submit</button>
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