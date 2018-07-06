<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
body {font-family: "Times New Roman", Georgia, Serif;}
h1,h2,h3,h4,h5,h6 {
    font-family: "Playfair Display";
    letter-spacing: 5px;
}
</style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-padding w3-card" style="letter-spacing:4px;">
    <a href="/Tomato-v1/" class="w3-bar-item w3-button">Tomato ~ The Food Ordering App</a>
    <!-- Right-sided navbar links. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
    <a href="/Tomato-v1/view-reviews"class="w3-bar-item w3-button">VIEW RATINGS & REVIEWS</a>
    <a href="/Tomato-v1/view-orders" class="w3-bar-item w3-button">VIEW-ORDERS</a>
		<a href="/Tomato-v1/app-logout" class="w3-bar-item w3-button">LOGOUT</a>
    </div>
  </div>
</div>




</head>
<body>
<br />
<div class="w3-container w3-padding-64" id="contact">
<h4>Welcome to ${place} restaurant!!</h4>
<table border=1>
<tr>
<th>NAME</th>
<th>PRICE</th>
<th>QUANTITY</th>
<th>ADD</th>
</tr>
<c:forEach var="x" items="${menu}">
<tr>
<td>${x.item}</td> 
<td>${x.price}</td>
<form method="post" action="/Tomato-v1/addItem">
<input type="hidden" name="item" value="${x.item}">
<td><input type="number" name="quantity" value="0"></td>
<input type="hidden" name="price" value="${x.price}">
<td><input type="submit" value="Add" >
</form>
</tr> 
</c:forEach>

</table>

<br />
<h4>YOUR FOOD BASKET ::</h4>

<table border="1" >
<tr>
<th>NAME</th>
<th>TOTAL PRICE</th>
<th>QUANTITY</th>
<th>REMOVE</th>
</tr>
<c:forEach var="x" items="${cart}">
<tr>
<td>${x.item}</td> 
<td>${x.price}</td>
<td>${x.quantity}</td>
<form method="post" action="/Tomato-v1/removeItem">
<input type="hidden" name="item" value="${x.item}">
<td><input type="submit" value="Remove" >
</form>
</tr> 
</c:forEach>

</table>
<br />

<h3>Total Bill : ${totalBill}</h3>
<c:if test = "${totalBill > 0}" >
<br />
<form method="post" action="/Tomato-v1/checkout">
<input class="w3-button w3-light-grey w3-section" type="submit" value="CHECKOUT">
</form>
</c:if>

<br />
</div>
</body>
</html>