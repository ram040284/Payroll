<!DOCTYPE html>
<html>
<head>
<title>Incometax Slabs</title>
<jsp:include page="../jsp/public/postHeader.jsp" />
<jsp:include page="../jsp/public/jqueryPluginMin.jsp"/>
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

.taxSlabsListTableClass table {
	border-collapse: collapse;
	width: 100%;
	float: left;
	margin: 0;
  	padding: 0;
	border: 1px solid #aaa;
	table-layout: auto;
}

.taxSlabsListTableClass th,td {
	text-align: left;
	padding: 5px;
}

.taxSlabsListTableClass tr:nth-child(odd) {
	background-color: #f2f2f2; !important
}

.taxSlabsListTableClass th {
	background-color: #8B9DC3;
	color: #fff;
	cursor: pointer;
}
table.dataTable thead:first-child .sorting_asc { 
	background: url('../Payroll/resources/images/uparrow.png') no-repeat right bottom 8px; 
	background-size: 25px; 
	background-color: #8B9DC3;
	color: white;
}
table.dataTable thead:first-child .sorting_desc { 
	background: url('../Payroll/resources/images/downarrow.png') no-repeat right bottom 8px; 
	background-size: 25px; 
	background-color: #8B9DC3;
	color: white;
}
.dataTables_paginate {
	text-align: right;
}
</style>
     <!--  /* function getTaxSlabsList() {
          $.ajax({
              url : '../Payroll/listTaxSlabs',
              type:"GET",
              contentType: "application/json;charset=utf-8",
              success : function(data) {
                 
                var taxSlabsTab = $('<table/>').appendTo($('#taxSlabsListDiv'));
                  $(data).each(function(i, taxSlab){
                	  $('<tr/>').appendTo(taxSlabsTab)
                	  		.append($('<td/>').text(taxSlab.financialYear))
                	  		.append($('<td/>').text(taxSlab.lowerSlab.toFixed(2)))
                			.append($('<td/>').text(taxSlab.higherSlab.toFixed(2)))
                			.append($('<td/>').text(taxSlab.incomtaxPercent))
                	  		.append($('<td/>').text(taxSlab.surcharge.toFixed(2)))
                			.append($('<td/>').text(taxSlab.educationCess.toFixed(2)))
                			.append($('<td/>').text(taxSlab.otherCess.toFixed(2)))
                			.append($('<td/>').append('<a href="#" onclick=viewTaxSlab('+taxSlab.incomtaxId+')><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a><a href="#" onclick=deleteTaxSlab('+taxSlab.incomtaxId+')><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"/></a>'));
                  });
              }
          });
      } */ -->
      
      <script type="text/javascript">
      $(document).ready(function(){
	  $.ajax({
      url : '../Payroll/listTaxSlabs',
      type:"GET",
      contentType: "application/json;charset=utf-8",
      success : function(taxData) {
     $('#taxSlabsListTable').DataTable({
    	 "scrollY": "300px",
            data: taxData,
              columns: [
				{ data: 'financialYear', title: 'Financial Year',"autoWidth": false},
              	{ data: 'lowerSlab', title: 'Lower Slab',"autoWidth": false},
                { data: 'higherSlab', title: 'Higher Slab',"autoWidth": false},
                { data: 'incomtaxPercent', title: 'Incometax Percentage',"autoWidth": false},
                { data: 'surcharge', title: 'Surcharges',"autoWidth": false},
              	{ data: 'educationCess', title: 'Education Cess',"autoWidth": false},
                { data: 'otherCess', title: 'Other Cess',"autoWidth": false},
                {
				 'data': null,title:'<a href="#" onclick="inputTaxSlab()" title:"Add"><img src="../Payroll/resources/images/add.jpg" alt="Add" class="addImg"/></a>',
				 'render': function (taxData, type, row) {
    			   return '<a id="' + row.Id +'" href="#" onclick="viewTaxSlab('+taxData.incomtaxId+')" title:"Edit"><img src="../Payroll/resources/images/edit.png" alt="Edit" class="listImg"/></a> <a id="' + row.Id +'"  href="#" onclick="deleteTaxSlab('+taxData.incomtaxId+')" title:"Delete"><img src="../Payroll/resources/images/delete.png" alt="Delete" class="listImg"></a>'
	            	}
				}
             ] 
          });
		   }
	   });
});   	      
     function viewTaxSlab(id){
    	  var f = document.forms['editForm'];
    	  f.incomtaxId.value=id;
		  f.action="../Payroll/inputTaxSlab";
		  f.submit();
	  }
      function inputTaxSlab(){
    	  var f = document.forms['editForm'];
		  f.action="../Payroll/inputTaxSlab";
		  f.submit();
	  }
      function deleteTaxSlab(id){
    	  if(confirm("Are you sure want to delete Incometax Slab?")){
    		  var f = document.forms['editForm'];
    		  f.incomtaxId.value=id;
    		  f.action="../Payroll/deleteTaxSlab";
    		  f.submit();
    	  }
      }
      </script>
</head>
<body>
	 <div class="contain-wrapp bodyDivCss">	
		<div class="container">
			<div class="formDiv" style="border: none;">
				<div class="row">
					<div class="text-left" style="margin-left: 15px;">
						<button type="button" id="backBtn" class="btn" onclick="backNav('../Payroll/mastersMenu')">Back</button>
					</div>
				</div>
			</div>	
		</div>
		<div class="container">
			<div style="margin-top: 12px; float: left; width: 98%;">
				<h4 style="color: #0101DF;">Incometax Slabs</h4>
					<div id="taxSlabsListDiv" class="taxSlabsListTableClass" style ="width:100%; margin-top: 25px">
							<table id="taxSlabsListTable" class="table table-striped table-bordered table-responsive"></table>
					</div>
			</div>
		</div>
	</div>
	<form action="" name="editForm" method="post">
		<input type="hidden" name="incomtaxId" value="0">
	</form>
	<jsp:include page="../jsp/public/postFooter.jsp" />
</body>
</html>