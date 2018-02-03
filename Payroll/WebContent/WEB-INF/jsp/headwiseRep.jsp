<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<title>Headwise Report</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<jsp:include page="../jsp/public/jdatePicker.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	var departmentList = ${departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	$('#monthDate').datepick({dateFormat: 'dd/mm/yyyy'});
});
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
</script>

</head>
<body>
<div class="contain-wrapp bodyDivCss">	
<div class="container">

<div style="margin-top:0px;">
	<form method = "POST" action = "../Payroll/employee" name="paybillForm">
	<div class="panel panel-primary" style="width: 40%; margin-left: 25%;">
    	<div class="panel-heading" style="margin:0px;padding:10px;background-color: #8B9DC3; font-size: 1.2em;"><b>Headwise Report</b></div>
		<div  class="panel-body formDiv" style="padding:5px;margin:0px; width: 100%;">
		<div style="margin-left: 10px;">
	<div class="row">
		<div class="col-sm-6 form-group">
			<label>Department </label> 
			<select id="departmentId" class="form-control" name="departmentId">
			<option value="0">-- Select Department --</option></select>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6 form-group">
			<label>Date:</label>
			<input type="text" id="monthDate" name="monthDate" placeholder="Select 1st Date of Month" class="form-control"/>
		</div>
	</div>			
		
	<div class="row">
		<div class="col-sm" style="float: right; margin-right: 20px; margin-bottom: 5px;">
			<button type="button" id="searchBtn"  class="btn" onclick="headwiseRpt()">Get Report</button>
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