<html>
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
		<a href="/Tomato-v1/register" class="w3-bar-item w3-button">REGISTER</a>
		<a href="/Tomato-v1/forgot-password" class="w3-bar-item w3-button">FORGOT-PASSWORD</a>
    </div>
  </div>
</div>



<div class="w3-container w3-padding-64" id="contact">
    <h3>Login</h3><br>
    <form action="/Tomato-v1/login" method="post">
      <p><input class="w3-input w3-padding-16" type="text" placeholder="UserName" required name="username"></p>
      <p><input class="w3-input w3-padding-16" type="password" placeholder="Password" required name="password"></p>
      <p><button class="w3-button w3-light-grey w3-section" type="submit">LOGIN</button></p>
    </form>
  </div>
 



</html>