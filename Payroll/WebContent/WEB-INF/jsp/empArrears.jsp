<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Emp Arrears</title>
<jsp:include page="../jsp/public/postHeader.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	var arrearId = "${empArrears.arrearId}";
	var arrearsType = "${empArrears.arrearsType}";
	var deptId = "${empArrears.departmentId}";
	var desgId = "${empArrears.designationId}";
	var headId = "${empArrears.headId}";
	$('#departmentId').val(deptId);
	if (arrearsType !=0) {
		$('#arrearsType').val(arrearsType);	
	}
	
	if(deptId !=0) {
		getHeadsByDept(deptId, headId);		
	}
	if(headId != 0) {
		loadDesgByHead(headId, desgId);
	}
	
	var empId = "${empArrears.employeeId}";
	if(empId != 0){
		getEmployeesByIds(deptId, desgId, empId);
	}
	
	$('#addArrearskBtn').click(function(event) {
		var arrearsType = "${empArrears.arrearsType}";
		var arrearsPay = "${empArrears.arrearsPay}";
		var arrearsDeductions = "${empArrears.arrearsDeductions}";
		//var miscPay = "${empArrears.miscPay}";
		//var miscDeductions = "${empArrears.miscDeductions}";
		var arrearsPayNote = "${empArrears.arrearsPayNote}";
		var arrearsDeductionNote = "${empArrears.arrearsDeductionNote}";
		if(empId !=0){
			if(arrearsType == $('#arrearsType').val() 
					&& arrearsPay == $('#arrearsPay').val()
					&& arrearsDeductions == $('#arrearsDeductions').val()
					//&& miscPay == $('#miscPay').val()
					//&& miscDeductions == $('#miscDeductions').val()
					&& arrearsPayNote == $('#arrearsPayNote').val()
					&& arrearsDeductionNote == $('#arrearsDeductionNote').val()){
				alert('Nothing was changed');
				$('#bankId').focus();
				return false;
			}
		}
		if(arrearId ==0){	
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
		if($('#arrearsType').val() == ''){
			alert("Arrear Type must be provided!");
			$('#arrearsType').focus();
			return false;
		}
		if($('#arrearsPay').val() == ''){
			alert("Arrear Pay must be provided!");
			$('#arrearsPay').focus();
			return false;
		}
		if($('#arrearsDeductions').val() == ''){
			alert("Arrear Dedu must be provided!");
			$('#arrearsDeductions').focus();
			return false;
		}
		/* if($('#miscPay').val() == ''){
			alert("Misc Pay must be provided!");
			$('#miscPay').focus();
			return false;
		} */
		/* if($('#miscDeductions').val() == ''){
			alert("Misc Dedu must be provided!");
			$('#miscDeductions').focus();
			return false;
		} */
		if($('#arrearsPayNote').val() == ''){
			alert("Arrear Pay Note must be provided!");
			$('#arrearsPayNote').focus();
			return false;
		}
		if($('#arrearsDeductionNote').val() == ''){
			alert("Arrear Dedu Note must be provided!");
			$('#arrearsDeductionNote').focus();
			return false;
		}
		var inputJson = { 
				"arrearId" : arrearId, 
				"employeeId" : $('#employeeId').val(), 
				"arrearsType" : $('#arrearsType').val(),  
				"arrearsPay" : $('#arrearsPay').val(), 
				"arrearsDeductions" : $('#arrearsDeductions').val(), 
				"miscPay" : 0, 
				"miscDeductions" : 0, 
				"arrearsPayNote" : $('#arrearsPayNote').val(), 
				"arrearsDeductionNote" : $('#arrearsDeductionNote').val(), 
				"addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addArrears',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewArrears";
	            }else{
	            	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
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
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewArrears')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test="${arrears.employeeId != '0'}" >	Add/Update</c:if><c:if test="${arrears.employeeId == '0'}">Add</c:if> Employee Arrears Pay
			</h4>

		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
							<div class="col-sm-12">
								<div class="row">
								<div class="col-sm-6 form-group">
									<label>Department</label>
									<select id="departmentId" class="form-control" onchange="getHeads()"
									<c:if test="${empArrears.arrearId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Department --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Head:</label>
									<select id="headId" class="form-control" onchange="loadDesignations()"
									<c:if test="${empArrears.arrearId != '0'}" > disabled= "disabled" </c:if>>
									<option value="0">-- Select Head --</option></select>
								</div>
								</div>
								<div class="row">
									<div class="col-sm-6 form-group">
									<label>Designation:</label>
									<select id="designationId" class="form-control" onchange="getEmployees()"
									<c:if test="${empArrears.arrearId != '0'}" >disabled = "disabled" </c:if>>
										<option value="0">-- Select Designation --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
										<label>Employee:</label>
										<select id="employeeId" class="form-control"
										<c:if test="${empArrears.arrearId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Employee --</option>
										</select>
								</div>
								
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 form-group">
									<div class="col-sm-6 form-group">
										<label>Arrears Type :</label>
										<select id="arrearsType" class="form-control"
										<c:if test="${empArrears.arrearId != '0'}" >disabled = "disabled" </c:if>>
											<option value="0">-- Select Arrears Type --</option>
											<option value="Rent">Rent</option>
											<option value="AfkRent">AfkRent</option>
											<option value="Misc">Misc</option>
											<option value="Other">Other</option>
										</select>
									</div>
								</div>
								<div class="col-sm-12 form-group">
									<div class="col-sm-6 form-group">
										<label>Arrears Pay:</label>
										<form:input path="arrearsPay"  id="arrearsPay" placeholder="Enter Account No" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empArrears.arrearId != '0'}" > value="1" </c:if>/>
									</div>
									<%-- <div class="col-sm-4 form-group">
										<label>Misc Pay:</label>
										<form:input path="miscPay"  id="miscPay" placeholder="Enter Arrears Type" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empArrears.employeeId != '0'}" > value="1" </c:if>/>
									</div> --%>
									<div class="col-sm-6 form-group">
										<label>Arrears Pay Note:</label>
										<form:textarea path="arrearsPayNote"  id="arrearsPayNote" placeholder="Enter Arrears Type" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empArrears.arrearId != '0'}" > value="1" </c:if>/>
									</div>
								</div>
								<div class="col-sm-12 form-group">
									<div class="col-sm-6 form-group">
										<label>Arrears Deductions:</label>
										<form:input path="arrearsDeductions"  id="arrearsDeductions" placeholder="Enter Arrears Type" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empArrears.arrearId != '0'}" > value="1" </c:if>/>
									</div>
									<%-- <div class="col-sm-4 form-group">
										<label>Misc Diductions:</label>
										<form:input path="miscDeductions"  id="miscDeductions" placeholder="Enter Account No" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empArrears.employeeId != '0'}" > value="1" </c:if>/>
									</div> --%>
									<div class="col-sm-6 form-group">
										<label>Arrears Diduction Note:</label>
										<form:textarea path="arrearsDeductionNote"  id="arrearsDeductionNote" placeholder="Enter Arrears Type" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate" <c:if test="${empArrears.arrearId != '0'}" > value="1" </c:if>/>
									</div>
									
									<div class="row">
										<div class="col-sm-12">	
											<div class="text-right">
												<button type="button" id="addArrearskBtn" class="btn">Submit</button>
												<button type="reset" class="btn">Reset</button>	
											</div>	
										</div>
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