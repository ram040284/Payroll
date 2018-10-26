<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Contract Employee</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" /> 
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>
<script type="text/javascript">

$(document).ready(function() {
	var empId = "${employeeContract.employeeId}";
	$.ajax({
        url : '../Payroll/loadContractualEmp',
        type:"GET",
        async: false,
        contentType: "application/json;charset=utf-8",
        success : function(data) {
       	$('#employeeId').empty();
	    	$('<option>').val(0).text("-- Select Employee --").appendTo('#employeeId');
	    	$(data).each(function(i, employee){
	    		$('<option>').val(employee.employeeId).text(employee.fullName).appendTo('#employeeId');
	    	});
	    	if(empId !=0) {
	  			$('#employeeId').val(empId);
        	}
     	 }
   	 });
	
	
	$('#addContrEmpBtn').click(function(event) {
		if(empId !=0){
			if(appointmentDate == $('#appointmentDate').val() && endDate == $('#endDate').val()){
				alert('Nothing was changed');
				$('#appointmentDate').focus();
				return false;
			}
		}
		if(empId ==0){
			if($('#appointmentDate').val() == 0){
				alert("Appointment date must be selected!");
				$('#appointmentDate').focus();
				return false;
			}
		}
		if($('#endDate').val() == ''){
			alert("End date must be selected!");
			$('#endDate').focus();
			return false;
		}
		if($('#engagementLetterId').val() == ''){
			alert("Engagement Letter Id must be provided!");
			$('#engagementLetterId').focus();
			return false;
		}
		var inputJson = { "employeeId" : $('#employeeId').val(), "appointmentDate" : $('#appointmentDate').val(),  
				"endDate" : $('#endDate').val(), "engagementLetterId": $('#engagementLetterId').val(), "addUpdate": $('#addUpdate').val()};
		$.ajax({
	        url: '../Payroll/addUpdateContractualEmp',
	        data: JSON.stringify(inputJson),
	        type: "POST",           
	        contentType: "application/json;charset=utf-8",
	        success: function(data){
	            if(data == "Yes"){
	            	window.location = "../Payroll/viewContractEmp";
	            }else{
	            	$("#errMsgDiv").text(data);
		        	$("#errMsgDiv").show();
	            }
	        }
	    });
	    event.preventDefault();
	    
	    
	    
	    
	});
	
	$('#appointmentDate').datepick({dateFormat: 'dd/mm/yyyy'});
	$('#endDate').datepick({dateFormat: 'dd/mm/yyyy'});
});

</script>

</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/viewContractEmp')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div style="display: none;color: red; font-weight:bold; height: 15px;" id="errMsgDiv"></div>
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;">
				<c:if test='${employeeContract.employeeId eq "0"}' >	Update</c:if><c:if test='${!employeeContract.employeeId eq "0"}'>Add</c:if> Contractual Employee
			</h4>
		<div class="col-lg-12 card-block bg-faded" style="margin-bottom: 10px;">
			<div class="row">
				<form:form method = "POST" action = "">
					<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Employee:</label>
									<select id="employeeId" class="form-control"
									<c:if test='${!employeeContract.employeeId eq "0"}' >disabled = "disabled" </c:if>>
										<option value="0">-- Select Employee --</option>
									</select>
								</div>
								<div class="col-sm-6 form-group">
									<label>Engagement Letter Id</label>
									<form:input path="engagementLetterId"  id="engagementLetterId" placeholder="Enter Engagement Letter Id" class="form-control"/>
									<input type="hidden" name="engagementLetterId" id="engagementLetterId" <c:if test="${employeeContract.employeeId != '0'}" > value="1" </c:if>/>
									<input type="hidden" name="addUpdate" id="addUpdate"
											<c:if test="${employeeContract.employeeId != '0'}" > value="1" </c:if> />
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
										<label>Appointment Date</label> 
										<form:input type="text" id="appointmentDate" path="appointmentDate" placeholder="Enter Appointment Date (DD/MM/YYYY)" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate"
										<c:if test="${employeeContract.employeeId != '0'}" > value="1" </c:if> />
								</div>
								<div class="col-sm-6 form-group">
										<label>End Date</label> 
										<form:input type="text" id="endDate" path="endDate" placeholder="Enter End Date (DD/MM/YYYY)" class="form-control"/>
										<input type="hidden" name="addUpdate" id="addUpdate"
										<c:if test="${employeeContract.employeeId != '0'}" > value="1" </c:if> />
								</div>
							</div>
							<div class="row">	
								<div class="text-right">
									<button type="button" id="addContrEmpBtn" class="btn">Submit</button>
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