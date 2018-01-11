<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>

<style type="text/css">
select {
	min-width: 200px;
	min-height: 30px;
}

.buttonPadding {
	padding: 5px;
}
.btn-color{
	background-color: #0101DF;
}

.rptTblClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.rptTblClass th, td {
	text-align: left;
	padding: 5px;
}

.rptTblClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.rptTblClass th {
	background-color: #8B9DC3;
	color: white;
}

table.dataTable thead .sorting_asc { 
background: url('../Payroll/resources/images/uparrow.png') no-repeat right bottom 8px; 
background-size: 25px; 
background-color: #8B9DC3;
color: white;
}
table.dataTable thead .sorting_desc { 
background: url('../Payroll/resources/images/downarrow.png') no-repeat right bottom 8px; 
background-size: 25px; 
background-color: #8B9DC3;
color: white;
}

table.dataTable thead .sorting:after, table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_desc_disabled:after {
display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	<%--$("#collapse").hide();
	$("#expand").show(); --%>
	var departmentList = ${sessionScope.departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
	});
	
	<c:if test="${not empty headDetails}">
	var headList = ${headDetails};
	$.each(headList, function( index, value ) {
		$('<option>').val(value.headId).text(value.headName).appendTo('#headId');
	});
	</c:if>
	
	var deptId = "${search.departmentId}";
	$('#departmentId').val(deptId);
	var headId = "${search.headId}";
	$('#headId').val(headId);
	
	$('#closeBtn').click(function(event) {
		   $("#searchDiv").toggle();
		   $("#collapse").toggle();
		   $("#expand").toggle();
	});
	
	$('#closeSearch').hide();

	$('#modifySearch').click(function(event) {
		$('#searchDiv').show();
		$('#closeSearch').show();
		$('#modifySearch').hide();
	});
	
	$('#closeSearch').click(function(event) {
		$('#searchDiv').hide();
		$('#closeSearch').hide();
		$('#modifySearch').show();
	});
	
	<c:if test="${sessionScope.recordsSize gt 0}">
	$('#searchDiv').hide();
	</c:if>	
	
});

function getHeads(){
	if($('#departmentId').val() == 0){
		$('#headId').val(0);
		$('#departmentId').focus();
		return false;
	}
	var deptId = $('#departmentId').val();
	getHeadsByDept(deptId, 0);
}

function getHeadsByDept(deptId, headId) {
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
	    	<%--$('#designationId').empty();
	    	$('<option>').val(0).text("-- Select Designation --").appendTo('#designationId'); --%>
	    	$(data).each(function(i, headInfo){
	    		$('<option>').val(headInfo.headId).text(headInfo.headName).appendTo('#headId');
	    	});
	    	<%--var headId = "${salary.headId}";--%>
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

<%-- 
<div style="width:100%;"> 
<h6 style="color: #0101DF;margin-bottom:0px;"><a id="closeBtn" href="#" style="color: blue;"><label id="collapse" style="color: blue;" class="glyphicon-plus"></label> <label id="expand" class="glyphicon-minus" style="color: blue;"></label> Show / Hide Search</a></h6>
</div>  --%>

	<form:form id="formSearch" method = "POST" action = "" >
	<div id="searchDiv" class="container" style ="position: relative;">
	 <div class="panel panel-primary">
      <div class="panel-heading" style="margin:0px;padding:5px;background-color: #8B9DC3;"><b>${sessionScope.reportName}</b></div>
<div  class="panel-body" style="padding:10px;margin:0px;border: 1px solid #8B9DC3;">
	<div class="col-sm-3">
		<label>Department </label> 
		<select id="departmentId" class="form-control" name="departmentId" onchange="getHeads()">
		<option value="0">-- Select Department --</option></select>
	</div>
	<div class="col-sm-3">
		<label>Head </label> 
		<select id="headId" class="form-control" name="headId"><option value="0">-- Select Head --</option></select>
	</div>
	<div class="col-sm-3">
		<label>Name</label>
		<form:input type="text" id="fname" path="firstName" placeholder="Enter Name" class="form-control"/>
		<%-- <label>Designation:</label>
		<select id="designationBox" class="form-control" name="designationId"><option value="0">-- Select Designation --</option></select> --%>
	</div>
	<div class="col-sm-3">
		
	</div><div class="col-sm-3">
		<br>
		<div class="formDiv" style="width:100%;margin-top:0px;border: 0px;">
		<button type="button" id="searchBtn"  class="btn" >Search</button>
		<button type="reset"  class="btn">Reset</button>	
		</div>
	</div></div>
	</div></div>
	</form:form> 
<c:if test="${not empty sessionScope.recordsSize}">
<div class="container" style ="position: relative;">
<div class="col-sm-12" style ="width:100%;padding-left:0px;margin-top:0px;margin-left:0px;">
	<div class="col-sm-6" style ="padding-left:0px;margin-left:0px;"><h6 style="color: #0101DF;margin-bottom:0px;" id="reportName">${sessionScope.reportName}</h6></div> 
	<div class="col-sm-6" style ="padding-left:0px;margin-left:0px;">
	<c:if test="${sessionScope.recordsSize gt 0}">
	<a id="modifySearch" href="javascript:void(0)" style="color: #0101DF;text-decoration: underline;"><b>Modify Search</b></a>
	<a id="closeSearch" href="javascript:void(0)" style="color: #0101DF;text-decoration: underline;"><b>Close Search</b></a>
	</c:if>
	
	<c:if test="${sessionScope.recordsSize gt 0}">
	<a id="downloadLink" href="javascript:void(0)" style="color: #0101DF;text-decoration: underline;margin-right:15px;float:right;"><b>Download</b></a>
	<a id="printLink" href="javascript:void(0)" style="color: #0101DF;text-decoration: underline;margin-right:15px;float:right;"><b>Print</b></a>
	</c:if>
	</div>
</div></div>
</c:if>	

<iframe id="rptPrintFrame" name="rptPrintFrame" src="../Payroll/printTemplate" style="display:none;">
</iframe>	
