<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Employee Deductions</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
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
<script type="text/javascript">
      
	$(document).ready(function() {
		$("#collapse").hide();
		$("#expand").show();
		
		var departmentList = ${departments};
		$.each(departmentList, function( index, value ) {
			$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#departmentId');
		});
		var deptId = "${employee.departmentId}";
		$('#departmentId').val(deptId);
		var headId = "${employee.headId}";
		if(deptId !=0) {
			getHeadsByDept(deptId, headId);		
		}
		var firstName = "${employee.firstName}";
		$('#firstName').val(firstName);
		$('#closeBtn').click(function(event) {
			   $("#searchDiv").toggle();
			   $("#collapse").toggle();
			   $("#expand").toggle();
		});
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
      function viewDeductions(id){
    	  var f = document.forms['empDedSearch'];
		  f.employeeId.value=id;
		  f.action="../Payroll/inputEmpDeductions";
		  f.submit();
	  }
      function inputDeductions(){
    	  var f = document.forms['empDedSearch'];
		  f.action="../Payroll/inputEmpDeductions";
		  f.submit();
	  }
      function deleteDeductions(id){
    	  if(confirm("Are you sure want to delete Employee ITax Exemption?")){
    		  var f = document.forms['empDedSearch'];
    		  f.employeeId.value=id;
    		  f.action="../Payroll/deleteEmpDeductions";
    		  f.submit();
    	  }
      }
      function searchEmps(){
    		if($('#departmentId').val() == 0 && $('#firstName').val().trim() == ""){
    			alert('Either Department or Name must be provided to get List!');
    			$('#departmentId').focus();
    			return false;
    		}
    		var f = document.forms['empDedSearch'];
    		f.action="../Payroll/listEmpDeductions";
    		f.submit();
    	}
      </script>
</head>
<body>
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/employeeMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
			<%--<div style="margin-top: 12px; float: left;"> --%>
				<h4 style="color: #0101DF;">Employee Exemptions Details</h4>
			<%--<div><div class="tblClass" id="deductionListDiv" style="overflow-x:auto;"> --%>
			<div style="width:100%;"> 
				<h6 style="color: #0101DF;margin-bottom:0px;"><a id="closeBtn" href="#" style="color: #0101DF;"><label id="collapse" style="color: #0101DF;" class="glyphicon-plus">&nbsp;Show</label> <label id="expand" class="glyphicon-minus" style="color: #0101DF;">&nbsp;Hide</label> </a></h6>
			</div> 
			<div id="searchDiv" style="width:100%;margin-top:0px;" class="formDiv">
				<form method = "GET" action = "../Payroll/listEmpDeductions" name="empDedSearch">
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
							<div class="col-sm-3" style="margin-top: 25px; float: right;">
								
								<button type="button" id="searchBtn"  class="btn" onclick="searchEmps()">Search</button>
								<%--<button type="reset"  class="btn">Reset</button> --%>
								<button type="button" id="addBtn"  class="btn" onclick="inputDeductions()" style="margin-left: 15px;">Add</button>	
							</div>
						</div>
					</div>
				</form> 
			</div>
			<div style="width: 100%;">
			<c:if test="${empDeductions.size() gt 0}">
			<div style="margin-top: 6px; float: left; max-width: 100%;">
			<div id="deductionListDiv" class="tblClass" style="overflow-x:auto;">
				<table>
				<tr>
								<th>Employee</th>
								<th>Section 80C</th>
								<th>Home Loan Interest 88EE</th>
								<th>Cess</th>
								<th>Self Disable 80U</th>
								<th>Loan Principal</th>
								<th>School Fees</th>
								<th>LIC</th>
								<th>MutualFund</th>
								<th>Section 80D</th>
								<th>Section 80E</th>
								<th>NSC</th>
								<th>PPF</th>
								<th>Donation</th>
								<th>Section 80DD</th>
								<th>Bonus</th>
								<th>Arrears</th>
								<th>OT Amount</th>
								<th>OT Wages</th>
								<th>HRA Section 10(3A):</th>
								<th>Income tax rebate 87C:</th>
								<th>Child Transport Allowance 10(14):</th>
								<th>Home loan 24B:</th>
								<th>HLP PF LIC 80C:</th>
								<th>NSP 80CCD(1B):</th>
								<th>Health Insurance 80D:</th>
								<th>Des Dep 80DD:</th>
								<th>Medical 80DDB:</th>
								<th>EDU Load 80D:</th>
								<th>Donation 80G:</th>
								<th>RENT 80GG:</th>
								<th>Int Bank 80TTA:</th>
								<th>Phys Dis Per 80U:</th>
					<th><a href="#" onclick="inputDeductions()" title="Add">
						<img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/>
					</a></th>
				</tr>
				<c:forEach var="employee" items="${empDeductions}">
				<tr>
								<td> ${employee.fullName} </td>
								<td> ${employee.section80C}</td>
								<td> ${employee.homeLoanIntrst88EE}</td>
								<td> ${employee.cess}</td>
								<td> ${employee.selfDisable80U}</td>
								<td> ${employee.loanPrincipal}</td>
								<td> ${employee.schoolFees}</td>
								<td> ${employee.lic}</td>
								<td> ${employee.mutualFund}</td>
								<td> ${employee.section80D}</td>
								<td> ${employee.section80E}</td>
								<td> ${employee.nsc}</td>
								<td> ${employee.ppf}</td>
								<td> ${employee.donation}</td>
								<td> ${employee.section80DD}</td>
								<td> ${employee.bonus}</td>
								<td> ${employee.arrears}</td>
								<td> ${employee.otAmount}</td>
								<td> ${employee.otWages}</td>
								<td> ${employee.hra_section10_13A}</td>
								<td> ${employee.income_tax_rebate_section_87C}</td>
								<td> ${employee.child_trans_allw_10_14}</td>
								<td> ${employee.home_loan_section_24B}</td>
								<td> ${employee.hlp_pf_lic_80C}</td>
								<td> ${employee.nps_80CCD_1B}</td>
								<td> ${employee.health_insu_80D}</td>
								<td> ${employee.des_dep_80DD}</td>
								<td> ${employee.medical_80DDB}</td>
								<td> ${employee.edu_load_80D}</td>
								<td> ${employee.donation_80G}</td>
								<td> ${employee.rent_80GG}</td>
								<td> ${employee.int_bank_section_80TTA}</td>
								<td> ${employee.phys_dis_per_section_80U}</td>
					<td><a href="#" onclick="viewDeductions('${employee.employeeId}')" title="Edit">
							<img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/>
						</a>
						<a href="#" onclick="deleteDeductions('${employee.employeeId}')">
							<img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/>
						</a>
					</td>
				</tr>
				</c:forEach>
			</table>
				</div>
		</div>
		</c:if>
	</div>
	</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="employeeId" value="0">
		<input type="hidden" name="departmentId" />
		<input type="hidden" name="headId" />
		<input type="hidden" name="firstName"/>
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>