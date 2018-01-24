<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Add User</title>

<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jquery.datepick.css.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
select {
	min-width: 200px;
	min-height: 30px;
}

td, th {
	padding: 3px;
}

.buttonPadding {
	padding: 5px;
}
.btn-color{
	background-color: #0101DF;
}

.custom-combobox {
	font-family:inherit;
    position: relative;
    display: inline-block;
    width:100%;
  }
   .custom-combobox-toggle {
    position: absolute;
    top: 0;
    bottom: 0;
    margin-left: -1px;
    padding: 0;
    border-radius:2px;
	border:1px solid #aeaeae;
	border-left:0px;
    background:rgba(255, 255, 255, 0.6);
  }
  .custom-combobox-input {
  	font-family:inherit;
    padding:10px 15px;
	height:48px;
	border-radius:2px;
	border:1px solid #aeaeae;
	border-right:0px;
	font-size:13px;
	font-style:italic;
	color:#616161;
	background:rgba(255, 255, 255, 0.6);
	width:93%;
  }
 .custom-combobox-input:focus{
    outline: 0px;
 	border-radius:2px;
 	border:1px solid #00913e;
 }
 
</style>
<script type="text/javascript">
var rolesList ;
var userNameAvailable = false;
$(document).ready(function() {
	var departmentList = ${sessionScope.departments};
	$.each(departmentList, function( index, value ) {
		$('<option>').val(value.departmentId).text(value.departmantName).appendTo('#deptId');
	});
	var deptId = '${user.listDeptId}';
	$('#deptId').val(deptId==''?0:deptId);
	deptId = $('#deptId').val();
	
	if (deptId != 0)
	getEmployeesByDept(deptId);
	
	rolesList = ${sessionScope.roles};
	$.each(rolesList, function( index, value ) {
		$('<option>').val(value.roleId).text(value.roleName).appendTo('#roleId');
	});
	
	var roleId = '${user.listRoleId}';
	roleId = roleId == ''? 0 : roleId;
	$('#roleId').val(roleId);
	if (roleId != 0) roleSelection();
	
	$.widget( "custom.combobox", {
	      _create: function() {
	        this.wrapper = $( "<span>" )
	          .addClass( "custom-combobox" )
	          .insertAfter( this.element );
	 
	        this.element.hide();
	        this._createAutocomplete();
	        this._createShowAllButton();
	      },
	 
	      _createAutocomplete: function() {
	        var selected = this.element.children( ":selected" ),
	          value = selected.val() ? selected.text() : "";
	 
	        this.input = $( "<input id='autoIn'>" )
	          .appendTo( this.wrapper )
	          .val( value )
	          .attr( "title", "" )
	          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
	          .autocomplete({
	            delay: 0,
	            minLength: 0,
	            source: $.proxy( this, "_source" )
	          })
	          .tooltip({
	            classes: {
	              "ui-tooltip": "ui-state-highlight"
	            }
	          });
	 
	        this._on( this.input, {
	          autocompleteselect: function( event, ui ) {
	            ui.item.option.selected = true;
	            this._trigger( "select", event, {
	              item: ui.item.option
	            });
	          },
	 
	          autocompletechange: "_removeIfInvalid"
	        });
	      },
	 
	      _createShowAllButton: function() {
	        var input = this.input,
	          wasOpen = false;
	 
	        $( "<a>" )
	          .attr( "tabIndex", -1 )
	          .attr( "title", "" )
	          .tooltip()
	          .appendTo( this.wrapper )
	          .button({
	            icons: {
	              primary: "ui-icon-triangle-1-s"
	            },
	            text: false
	          })
	          .removeClass( "ui-corner-all" )
	          .addClass( "custom-combobox-toggle ui-corner-right" )
	          .on( "mousedown", function() {
	            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
	          })
	          .on( "click", function() {
	            input.trigger( "focus" );
	 
	            // Close if already visible
	            if ( wasOpen ) {
	              return;
	            }
	 
	            // Pass empty string as value to search for, displaying all results
	            input.autocomplete( "search", "" );
	          });
	      },
	 
	      _source: function( request, response ) {
	        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
	        response( this.element.children( "option" ).map(function() {
	          var text = $( this ).text();
	          if ( this.value && ( !request.term || matcher.test(text) ) )
	            return {
	              label: text,
	              value: text,
	              option: this
	            };
	        }) );
	      },
	 
	      _removeIfInvalid: function( event, ui ) {
	 
	        // Selected an item, nothing to do
	        if ( ui.item ) {
	          return;
	        }
	 
	        // Search for a match (case-insensitive)
	        var value = this.input.val(),
	          valueLowerCase = value.toLowerCase(),
	          valid = false;
	        this.element.children( "option" ).each(function() {
	          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
	            this.selected = valid = true;
	            return false;
	          }
	        });
	 
	        // Found a match, nothing to do
	        if ( valid ) {
	          return;
	        }
	 
	        // Remove invalid value
	        this.input
	          .val( "" );
	          //.attr( "title", value + " didn't match any item" )
	          //.tooltip( "open" );
	        this.element.val( "" );
	        this._delay(function() {
	          this.input.tooltip( "close" ).attr( "title", "" );
	        }, 2500 );
	        this.input.autocomplete( "instance" ).term = "";
	      },
	 
	      _destroy: function() {
	        this.wrapper.remove();
	        this.element.show();
	      }
	    });
	 
	    $( "#empId" ).combobox();
	    
	    $("#resetBtn").click(function() {       // apply to reset button's click event
	        this.form.reset();   
	    
	        $('#available').hide();
	    	$('#notAvailable').hide();
	    	$('#available').html('');
	    	$('#notAvailable').html('');
	        return false;                         // prevent reset button from resetting again
	    });
	    
	    $('#usersListBtn').click(function(event) {
	    	$("#listForm").attr("action", "../Payroll/usersListFilter");
			$("#listForm").submit();
	    });
	    
	    $('#addUserBtn').click(function(event) {
	    	
	    	if($('#roleId').val() == 0){
	    		alert("Role must be selected!");
	    		$('#roleId').focus();
	    		return false;
	    	}
	    	
	    	if($('#deptId').val() == 0){
	    		alert("Department must be selected!");
	    		$('#deptId').focus();
	    		return false;
	    	}
	    	if($('#empId').val() == 0 || $('#empId').val() == null || $('#empId').val() == 'null'){
	    		alert("Employee must be selected!");
	    		$('#empId').focus();
	    		return false;
	    	}
	    	if($('#userId').val() == ''){
	    		alert("User Id must be entered");
	    		$('#userId').focus();
	    		return false;
	    	}
	    	if (!userNameAvailable) {
	    		alert("User Id is not available. Please try with other user id.");
	    		//$('#userId').focus();
	    		return false;
	    	}
	    	if($('#password').val() == ''){
	    		alert("Password must be entered");
	    		$('#Password').focus();
	    		return false;
	    	}
	    	if($('#confirmPassword').val() == ''){
	    		alert("Confirm Password must be entered");
	    		$('#confirmPassword').focus();
	    		return false;
	    	}
	    	if($('#password').val() != $('#confirmPassword').val()){
	    		alert("Password & Confirm Password are not same");
	    		$('#Password').focus();
	    		return false;
	    	}
	    	
	    	if($('#password').val().length < 6 || $('#confirmPassword').val() < 6){
	    		alert("Password or Confirm Password lenght must have atleast 6 characters");
	    		$('#Password').focus();
	    		return false;
	    	}
	    	
	    	var passReg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/; ///^(?=.*\d)(?=.*[a-zA-Z]).{6,20}$/;
	    	if (!passReg.test($('#password').val())) {
	    		alert("Password must have atleast one letter, one number ");
	    		$('#Password').focus();
	    		return false;
	    		
	    	}
	    	
	    	$("#userForm").attr("action", "../Payroll/addUserConfirm");
			$("#userForm").submit();
	    });
	    
});
  
function roleSelection() {
	var roleId = $('#roleId').val();
	$('#roleDesc').html('');
	$.each(rolesList, function( index, value ) {
		if (value.roleId == roleId) {
			$('#roleDesc').html(value.roleDesc);
		}
	});
}

function loadEmployees(){
	if($('#deptId').val() == 0){
		$('#empId').val(0);
		$('#deptId').focus();
		return false;
	}
	var deptId = $('#deptId').val();
	getEmployeesByDept(deptId);
}

function getEmployeesByDept(deptId) {
	var deptId = $('#deptId').val();
	var inputJson = { "deptId" : deptId};
	  $.ajax({
	    url: '../Payroll/loadDeptEmployees',
	    data: JSON.stringify(inputJson),
	    type: "POST",           
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    success: function(data){ 
	    	$('#empId').empty();
	    	$('<option>').val(0).text("-- Select Employee --").appendTo('#empId');
	    	$(data).each(function(i, empVo){
	    		$('<option>').val(empVo.employeeId).text(empVo.fullName).appendTo('#empId');
	    	});
	    	$('#autoIn').val('');
	    	$('#autoIn').attr("placeholder", "-- Select or Enter Employee Name --");
	    },
	    failure: function (){
	    	alert('Unable to load Employees');
	    }
	});
}

function userIdAvailability() {
	var userId = $('#userId').val();
	
	$('#available').hide();
	$('#notAvailable').hide();
	$('#available').html('');
	$('#notAvailable').html('');
	
	if (userId.length == 0) {
		return false;
	}
	if (userId.length < 6) {
		$('#notAvailable').html('<br>' + userId+' is invalid. <br>It must be minimum 6 chars.');
		$('#notAvailable').show();
		return false;
	} else {
		var usernameRegex = /^[A-Za-z0-9._-]{6,20}$/;
		if (usernameRegex.test(userId) == false) {
			$('#notAvailable').html('<br>' + userId+' is invalid. <br>It contains invalid characters.');
			$('#notAvailable').show();
			return false;
		}
	}
	
	var inputJson = { "userId" : userId};
	  $.ajax({
	    url: '../Payroll/userNameCheck',
	    data: JSON.stringify(inputJson),
	    type: "POST",           
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    success: function(data){ 
	    	if (data.userNameAvailabe) {
	    		$('#available').html('<br>' + userId+' available');
	    		$('#available').show();
	    		userNameAvailable = true;
	    	} else {
	    		$('#notAvailable').html('<br>' + userId+' is not available. <br>Please try with other user id.');
	    		$('#notAvailable').show();
	    		userNameAvailable = false;
	    	}
	    	
	    },
	    failure: function (){
	    	$('#notAvailable').html('<br> A System problem occurred ');
    		$('#notAvailable').show();
	    	userNameAvailable = false;
	    }
	});
}

</script>
</head>
<body >
	<div class="contain-wrapp bodyDivCss">	
		<div class="container">
		<div class="formDiv">
			<h4 style="color: #fff; padding:5px; background-color: #8B9DC3; text-transform: none;font-size:12pt;">
				Add	User
			</h4>
			
			<div class="col-lg-12 card-block bg-faded" style="padding-bottom: 5px;">
			<div class="row">
			<form:form method = "POST" action = "" id="userForm" autocomplete="off">
				<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Role</label>
								<select id="roleId" name="roleId" class="form-control" onchange="roleSelection()"> 
									<option value="0">-- Select Role --</option></select>
							</div>
							
							<div class="col-sm-6 form-group" style="vertical-align: bottom">
								<span style="color: blue;font-weight:bold;" id="roleDesc"></span>
							</div>
						</div>
						<div class="row">	
							<div class="col-sm-6 form-group">
								<label>Department</label>
								<select id="deptId" name="deptId" class="form-control" onchange="loadEmployees()">
								<option value="0">-- Select Department --</option></select>
							</div>
							<div class="col-sm-6 form-group">
								<label>Employee</label>
								<div class="ui-widget">
								<select id="empId" name="empId">
								</select>
								</div>
							</div>
							
						</div>	
						
						<div class="row">
							<div class="col-sm-6 form-group">
							<label>User Name</label>
							<form:input type="text" id="userId" path="userId" placeholder="Enter User Name" class="form-control" onblur="userIdAvailability()"/>
							</div>
							
							<div class="col-sm-6 form-group">
							<span style="color: blue;font-weight:bold;">Valid characters are A-Z a-z 0-9 . _ -</span>
							<span style="color: green;font-weight:bold;display:none;" id="available"></span>
							<span style="color: red;font-weight:bold;display:none;" id="notAvailable"></span>
							</div>
						</div>	
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Password</label>
								<form:input type="password" id="password" path="password" placeholder="Enter Password" class="form-control"/>
							</div>	
							<div class="col-sm-6 form-group">
								<label>Confirm Password</label>
								<form:input type="password" id="confirmPassword" path="confirmPassword" placeholder="Enter Confirm Password" class="form-control"/>
							</div>		
						</div>			
						
						<div class="row">
							<div class="col-sm-12 form-group text-right">
							<button type="button" id="addUserBtn" class="btn">Submit</button>
							<button type="reset" class="btn" id="resetBtn">Reset</button>	
							<button type="button" id="usersListBtn"  class="btn">Users List</button>	
						</div>	
						</div>			
					</div>	
			</form:form> 
		</div>
	</div>
	</div>
	</div>
	</div>
	<form:form  action="" id="listForm" method="post">
		<form:input type="hidden" path="listDeptId" />
		<form:input type="hidden" path="listRoleId" />
	</form:form >
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>