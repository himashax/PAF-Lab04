<%@ page import="com.codebind.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% if (request.getParameter("itemCode") != null)
{
	 Item itemObj = new Item();
	 String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),
			 request.getParameter("itemName"),
			 request.getParameter("itemPrice"),
			 request.getParameter("itemDesc"));
	 
	 System.out.println(stsMsg);
	 session.setAttribute("statusMsg", stsMsg);
} %>

<%
	if (request.getParameter("itemID") != null)
	{
	Item itemObj = new Item();
	String stsMsg = itemObj.deleteItem(request.getParameter("itemID"));
	session.setAttribute("statusMsg", stsMsg);
	} 
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="Views/bootstrap.min.css">

<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Items Management</h1>
<form method="POST" action="items.jsp">
	Item Code: <input type='text' name='itemCode' class="form-control"></input><br>
	Item Name: <input type='text' name='itemName' class="form-control"></input><br>
	Item Price: <input type='number' name='itemPrice' class="form-control"></input><br>
	Item Description: <input type='text' name='itemDesc' class="form-control"></input><br>
	<input type = "submit" value = "Submit" class="btn btn-primary" />
</form>
	<br>
	<div class="alert alert-success">
	<% out.print(session.getAttribute("statusMsg")); %>
	</div>
	
	<br><br>
	<%
	 Item itemObj = new Item();
	 out.print(itemObj.readItems());
	%>
	
</div>	
</body>
</html>