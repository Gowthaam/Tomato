<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
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
    <a href="/Tomato-v1/view-orders" class="w3-bar-item w3-button">VIEW-ORDERS</a>
		<a href="/Tomato-v1/app-logout" class="w3-bar-item w3-button">LOGOUT</a>
    </div>
  </div>
</div>


<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>


<div class="w3-container w3-padding-64" id="contact">
<h5>HI ${name} , WELCOME TO TOMATO!!<h5>
    <h3>CHOOSE YOUR LOCATION</h3><br>
    <form action="/Tomato-v1/hotels" method="get">
      
      <select name="name" class="w3-input w3-padding-16">
	<c:forEach var="x" items="${locations}">
	<option value=${x} name="name" class="w3-input w3-padding-16"> ${x} </option>
	</c:forEach>
	</select>
      
      
      <p><button class="w3-button w3-light-grey w3-section" type="submit">SUBMIT</button></p>
    </form>

  </div>




</body>
</html>