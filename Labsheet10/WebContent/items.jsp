<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@page import = "com.Item" %>
      
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>


<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/items.js"></script>



</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col">

		<h1>Order Management</h1>
		
	<form id="formItem" name="formItem"  >
		Seller Name:
		<input id="sellername" name="sellername" type="text" class="form-control form-control-sm"><br>
		 Order Name:
		<input id="ordername" name="ordername" type="text" class="form-control form-control-sm"><br> 
		Order Date:
		<input id="orderdate" name="orderdate" type="text" class="form-control form-control-sm"><br>
		 Order description:
		<input id="orderDesc" name="orderDesc" type="text" class="form-control form-control-sm"><br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    
    <br>
	<div id="divItemsGrid">
	<%
	Item itemObj = new Item();
	out.print(itemObj.readItems());
	%>
	</div>

<br>


</div>
</div>
</div>


</body>
</html>