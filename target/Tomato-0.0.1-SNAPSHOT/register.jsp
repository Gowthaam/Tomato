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


<div class="w3-container w3-padding-64" id="contact">
    <h3>Please Enter Your Details ~</h3><br>
    <form action="/register" method="post">
      <p><input class="w3-input w3-padding-16" type="text" placeholder="UserName" required name="uname"></p>
      <p><input class="w3-input w3-padding-16" type="password" placeholder="Password" required name="password"></p>
      <p><button class="w3-button w3-light-grey w3-section" type="submit">SIGNUP</button></p>
    
    <p>${error}</p>
    </form>
  </div>


</body>
</html>