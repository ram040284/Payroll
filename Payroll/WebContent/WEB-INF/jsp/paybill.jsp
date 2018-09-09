<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<title>Paybill Report</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.section).text(value.section).appendTo('#departmentId');
	});
	<%--$('#monthDate').datepick({dateFormat: 'dd/mm/yyyy'});--%>
});
function generatePaybill(){
	var radioChecked = $("input[name='billType']:checked").val();
	if(radioChecked < 1){
		alert('Bill Type must be selected!');
		$("input[name='billType']").focus();
		return false;
	}
	if($('#departmentId').val() == ""){
		alert('Department Section must be provided to generate Bill!');
		$('#departmentId').focus();
		return false;
	}
	if($('#monthDate').val().trim()== "0"){
		alert('Month must be provided!');
		$('#monthDate').focus();
		return false;
	}
	var f = document.forms['paybillForm'];
	f.action="../Payroll/downloadPaybill";
	f.target="_blank";
	f.submit();
}
function generateMonthlyRpt(){
	if($('#departmentId').val() == 0){
		alert('Department must be provided generate Bill!');
		$('#departmentId').focus();
		f.target="_blank";
		return false;
	}
	var f = document.forms['paybillForm'];
	f.action="../Payroll/downloadPDF";
	f.target="_blank";
	f.submit();
}

function headwiseRpt(){
	if($('#departmentId').val() == 0){
		alert('Department must be provided generate Bill!');
		$('#departmentId').focus();
		return false;
	}
	var f = document.forms['paybillForm'];
	f.action="../Payroll/headwiseReport";
	f.target="_blank";
	f.submit();
}
function bankwiseRpt(){
	if($('#departmentId').val() == ""){
		alert('Section must be provided generate Bill!');
		$('#departmentId').focus();
		return false;
	}
	var f = document.forms['paybillForm'];
	f.action="../Payroll/bankwiseReport";
	f.target="_blank";
	f.submit();
}
<%--
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
}--%>
</script>

</head>
<body>
<div class="contain-wrapp bodyDivCss">
<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/reportsMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
<div class="container">
<%--<h5 style="color: #0101DF;">Paybill Report</h5> --%>
<div style="margin-top:0px;">
	<form method = "POST" action = "../Payroll/employee" name="paybillForm">
	<%--<div  class="col-sm-12" style="margin-top:0px; margin-bottom:10px; padding-top:5px; padding-bottom:10px;float: left;"> --%>
	<div class="panel panel-primary" style="width: 45%; margin-left: 25%;">
    	<div class="panel-heading" style="margin:0px;padding:10px;background-color: #8B9DC3; font-size: 1.2em;"><b>Paybill Report</b></div>
		<div  class="panel-body formDiv" style="padding:5px;margin:0px; width: 100%;">
		<div style="margin-left: 10px;">
	<div class="row">
			<div class="col-sm-10 form-group">
				<label style="margin-top: 10px;">Bills Type: </label> 
				<input type="radio" name= "billType" style="margin-left: 8px;" checked="checked" value="1"/> Permanent
				<input type="radio" name= "billType" style="margin-left: 8px;" value="2"/> Contract
				<input type="radio" name= "billType" style="margin-left: 8px;" value="3"/> Honorary
			</div>
		</div>
	<div class="row">
		<div class="col-sm-6 form-group">
			<label>Department Section: </label> 
			<select id="departmentId" class="form-control" name="section"><%-- onchange="getHeads()"> --%>
			<option value="">-- Select Section --</option></select>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6 form-group">
			<label>Month:</label>
			<%--<input type="text" id="monthDate" name="monthDate" placeholder="Select 1st Date of Month" class="form-control"/>--%>
			<select id="monthDate" class="form-control" name="monthDate"><%-- onchange="getHeads()"> --%>
			<option value="0">-- Select Month --</option>
			<option value="1">January</option>
			<option value="2">February</option>
			<option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
			<option value="12">December</option>
			</select>
		</div>
	</div>			
		<%--<div class="col-sm-3">
			<label>Head:</label>
			<select id="headId" class="form-control">
			<option value="0">-- Select Head --</option></select>
		</div>--%>
	
	<div class="row">
		<div class="col-sm" style="float: right; margin-right: 20px; margin-bottom: 5px;">
			<button type="button" id="searchBtn"  class="btn" onclick="generatePaybill()">Get Report</button>
			<%--<button type="button" id="searchBtn"  class="btn" onclick="generateMonthlyRpt()">Monthly Bill</button>
			<button type="button" id="searchBtn"  class="btn" onclick="headwiseRpt()">Headwise Report</button>
			<button type="button" id="searchBtn"  class="btn" onclick="bankwiseRpt()">Bankwise Report</button>
			 --%>
		</div>
	</div>
	</div>
	</div>
	</div>
	
	</form> 
</div></div></div>
<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>