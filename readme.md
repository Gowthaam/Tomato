TOMATO,THE FOOD ORDERING APP:

This is a webapplication that provides user with the functionalities to order the food online starting by choosing his location, restaurant of his choice serving in that location,checking out it's rating and reviews, placing the order and giving a review and rating to the restaurant.

PREREQUISITES:

Java JRE, 
Tomcat,
MySQL database. 

INSTALLING :

start the mysql service by using the command :

mysql -u root -p 

Now Enter your MySQL password.

Create a new user using the command :

grant all privileges on  * . * to 'gowtham'@'localhost' identified by 'gowtham';

Now quit MySQL using 'quit' command.

Now log into user using the command :

mysql -u gowtham -p

Password : gowtham

Now Run the following command :

create database if not exists project; 

Then copy the Tomato-v1.war file from the Target folder of the project and place it in the webapps folder of the Tomcat residing on your system.
Now you can start using the webapp from the your tomcat.
Eg : if your tomcat is configured to be on 8080, you can access the app using:
	
  http://localhost:8080/Tomato-v1/

