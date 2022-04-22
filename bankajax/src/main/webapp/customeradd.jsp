<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="banking.BankingLogic"%>
<%@ page import="java.util.Map"%>
<%@ page import="customer.CustomerData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add or Update Customer</title>
<link href="CommonStyles.css" rel="stylesheet" />
<script type="text/javascript" src="new.js">

<script>
function isNumberKey(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode < 48 || charCode > 57))
	return false;

	return true;
}

	function resetform() {
		document.getElementById("customerform").reset();
	}
</script>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

<script>
		$(function() {
				$.ajax(
				{
					url:"getAccounts",
				type:"GET",
				success:function(data)
				{ 
 					 var objkeys = $.parseJSON(data); 
					$.each(objkeys, function (a, b) {
						
 	                      $.each(b, function (c, d) {
 	                    	 var accData=d.accID;
 	      					$("#dispTable").append('<tr id="' + c + '"></tr>');
 	                    	  var addEle='<td>'+accData+'</td><td>'+d.custID+'</td><td>'+d.accType+'</td><td>'+d.location+'</td><td>'+d.ifscCode+'</td><td>'+d.balance+'</td>';
 	                    	 var dataRowId = '#' + c;
//  	                    	 $(dispTable).append(addEle);
    	                          $(dataRowId).append(addEle);
    	                          
    	                          $(dataRowId).append($('<td/>').html("<a href=AddorUpdateAccount.jsp?accountId="+accData+" class=idanchor><img src=editing.png class=anchorimg title=Edit-Info></a><a href=deleteAccount?accountId="+accData+" class=idanchor><img src=delete-icon.png class=anchorimg title=De-Activate></a>"));
    	                   		
 	                      });

	                  });					
					
					
				},
				error:function(data)
				{
					alert('none');
				}
				}
				
				);
			});
		
	</script>
<body>
	<div class="div" id="div">
		<table id="dispTable">
			<tr>
				<td colspan="7"><h1>Account Details</h1></td>
			</tr>

			<tr class="hrow">
				<td><Label>Account Number</Label></td>
				<td><Label>Customer ID</Label></td>
				<td><Label>Account Type</Label></td>
				<td><Label>Branch</Label></td>
				<td><Label>IFSC Code</Label></td>
				<td><Label>Account Balance</Label></td>
				<td><Label>Operations</Label></td>
			</tr>
		</table>

	</div>
</body>
</html>