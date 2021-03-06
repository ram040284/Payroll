<link href="../Payroll/resources/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
<script src="../Payroll/resources/js/jquery.dataTables.min.js"></script>
<script src="../Payroll/resources/js/dataTables.bootstrap.min.js"></script>
<style type="text/css">
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
<script>
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
	    	$('#designationId').empty();
	    	$('<option>').val(0).text("-- Select Designation --").appendTo('#designationId');
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
<div style="width:100%;"> 
<h6 style="color: #0101DF;margin-bottom:0px;"><a id="closeBtn" href="#" style="color: #0101DF;"><label id="collapse" style="color: #0101DF;" class="glyphicon-plus">&nbsp;Show</label> <label id="expand" class="glyphicon-minus" style="color: #0101DF;">&nbsp;Hide</label> </a></h6>
</div>  
<div id="searchDiv" style="width:100%;margin-top:0px;" class="formDiv">
	<form method = "GET" action = "../Payroll/employee" name="empSearch">
	<div  class="col-sm-12" style="width:100%;margin-top:0px; margin-bottom:10px; padding-top:5px; padding-bottom:10px;float: left;">
	<div class="row">
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
		<input type="text" id="firstName" name="firstName" placeholder="Enter First Name" class="form-control"/>
		<%-- <label>Designation:</label>
		<select id="designationBox" class="form-control" name="designationId"><option value="0">-- Select Designation --</option></select> --%>
		<input type="hidden" name="employeeId" value="0">
		<input type="hidden" name="empId" value="0">
	</div>
	<div class="col-sm-3">
		<label>Employee Type </label> 
		<select id="employeeType" class="form-control" name="employeeType">
			<option value="1">Permanent</option>
			<option value="2">Contract</option>
			<option value="3">Honorary</option>
		</select>
	</div>
	<div class="col-sm-3" style="margin-top: 25px; float: right;">
		
		<button type="button" id="searchBtn"  class="btn"onclick="searchEmps()"  style="margin-left: 15px;">Search</button>
		<%--<button type="reset"  class="btn">Reset</button> --%>
		<button type="button" id="addBtn"  class="btn" onclick="inputPage()" style="margin-left: 15px;">Add</button>	
	</div>
	
	</div>
	</div>
	</form> 
</div>