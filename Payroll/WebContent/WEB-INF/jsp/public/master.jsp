<script>
function getEmployeesByIds(deptId, desgId, empId){
	var inputJson = { "departmentId" : deptId,"designationId" : desgId};
	  $.ajax({
      url: '../Payroll/loadEmployees',
      data: JSON.stringify(inputJson),
      type: "POST",           
      beforeSend: function(xhr) {
          xhr.setRequestHeader("Accept", "application/json");
          xhr.setRequestHeader("Content-Type", "application/json");
      },
      success: function(data){ 
      	$('#employeeId').empty();
      	$('<option>').val(0).text("-- Select Employee --").appendTo('#employeeId');
      	$(data).each(function(i, employee){
      		$('<option>').val(employee.employeeId).text(employee.fullName).appendTo('#employeeId');
      	});
      	<%--var empId = "${salary.empId}";--%>
      	if(empId !=0) {
    		$('#employeeId').val(empId);
    		
    	}
      },
      failure: function (){
      	alert('Unable to load Employees');
      }
  });
  
}

function getEmployees() {
	if($('#departmentId').val() == 0 || $('#designationId').val() == 0){
		alert("Department and Designation must be selected to get Employees!");
		$('#departmentId').focus();
		return false;
	}
	var deptId = $('#departmentId').val();
	var desgId = $('#designationId').val();
	getEmployeesByIds(deptId, desgId, 0);
}
function getHeads(){
	if($('#departmentId').val() == 0){
		alert("Department must be selected to get Heads!");
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

function loadDesignations(){
	if($('#headId').val() == 0){
		alert("Head must be selected to get Designations!");
		$('#headId').focus();
		return false;
	}
	var headId = $('#headId').val();
	loadDesgByHead(headId, 0);
}

function loadDesgByHead(headId, designationId) {
	var inputJson = { "headId" : headId};
	  $.ajax({
	    url: '../Payroll/loadDesignations',
	    data: JSON.stringify(inputJson),
	    type: "POST",           
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    success: function(data){ 
	    	$('#designationId').empty();
	    	$('<option>').val(0).text("-- Select Designation --").appendTo('#designationId');
	    	$(data).each(function(i, designation){
	    		$('<option>').val(designation.designationId).text(designation.designationName).appendTo('#designationId');
	    	});
	    	<%--var designationId = "${salary.designationId}";--%>
	    	if(designationId !=0) {
	  			$('#designationId').val(designationId);
	  		}
	    },
	    failure: function (){
	    	alert('Unable to load Heads');
	    }
	});
}

</script>