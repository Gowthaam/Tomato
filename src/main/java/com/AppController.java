package com;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.model.Order;
import com.model.Review;
import com.repository.HotelRepository;
import com.repository.ItemRepository;
import com.repository.OrderRepository;
import com.repository.ReviewRepository;
import com.repository.UsersRepository;

import errorhandling.CustomException;

import org.apache.log4j.Logger;

/**
 * This Class consists of all the Required controllers.
 * @author Gowtham
 */
@Controller
public class AppController implements ErrorController
{

@Autowired
UsersRepository usersrepository;
@Autowired
HotelRepository hotelrepository;
@Autowired
ItemRepository itemrepository;
@Autowired 
OrderRepository orderrepository;
@Autowired 
ReviewRepository reviewrepository;
/**
 * It's used to maintain the user's cart in a session by mapping the name of the hotel
 * to the list of items added to the cart by the user.
 */
Map<String,ArrayList<OrderDetails>> order = new HashMap<>();

RefactoredImplementation refImpl = new RefactoredImplementation();
 
  String hotelName ;
  String username ;
  int orderid;
 int bill;
static final Logger logger =Logger.getLogger(AppController.class);
static final String ERROR="error";

/**
 * It's the default page user if directed for login by the Spring security.
 * @param principal , principal is an object returned by the Spring security 
 * 						that consists of the logged in user details.
 * @return If the user is not logged in ,it returns Login page else it returns home page.
 */
@RequestMapping("/login")
public String userLogin(Principal principal)
{	if(principal==null)
	{
	order = new HashMap<>();	
	logger.info("entered login page");
	return "login";
	
	}
else 
	return "redirect:/home"; 
}
	

/**
 * It's used to return a JSP HomePage consisting of all the locations available.
 * @param principal , Object returned by the Spring Security.
 * @param model It's used for setting the attributes of the JSP page.
 * @return it returns a JSP page consisting of all the available locations.
 */
@RequestMapping(value= {"/","home"})
public String userHome(Principal principal,Model model)
{

	username=principal.getName().toUpperCase();
	logger.info("User"+username+" logged in");

model.addAttribute("name",username);
model.addAttribute("locations", hotelrepository.findAllLocations());	
return "home";
}


/**
 * It's used to get all the available restaurants in the selected location.
 * @param inp , It takes in the selected location of the user as input from the Locations object 
 * @param model ,It's used for setting the attributes of the JSP page.
 * @return, it returns a JSP page consisting of all the hotels in the selected Location.
 */
@RequestMapping(value="/hotels",method=RequestMethod.GET)
public String getHotels(@ModelAttribute("inp") Locations inp,Model model)
{	
	logger.info("Location"+inp.name+" Selected");

	model.addAttribute("place", inp.name);
	model.addAttribute("hotels",hotelrepository.findByLocation(inp.name));
	return "hotels";	
}

/**
 * It's used to get all the menu items from the selected restaurants.
 * @param inp , It takes in the selected hotel of the user as input from the Hotels object
 * @param model ,It's used for setting the attributes of the JSP page.
 * @return , it returns a JSP page consisting of the menu in the selected hotel.
 */
@RequestMapping(value="/menu",method= {RequestMethod.POST,RequestMethod.GET})
public String getMenu(@ModelAttribute("inp") Hotels inp,Model model)
{
if(inp.name!=null) {
hotelName=inp.name; }	
logger.info("hotel: "+hotelName+" Selected");

model.addAttribute("place",hotelName);
model.addAttribute("menu",itemrepository.findByHname(hotelName));

int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<>();

	if(order.get(hotelName)!=null)
	for(OrderDetails y : order.get(hotelName))
		{
		temp1.add(new OrderDetails(y.getItem(),y.getQuantity(),y.quantity*y.getPrice()));
		totalBill+=y.quantity*y.getPrice();
		}

bill=totalBill;
model.addAttribute("cart", temp1);
model.addAttribute("totalBill",totalBill);

return "menu";}


/**
 * It's used to add an Item to the cart.
 * It makes use of the addItemLogic for business logic.
 * @param inp , It takes order from the user in the OrderDetails object.
 * @return , It redirects to menu page after adding item to the cart.
 */
@RequestMapping(value="/addItem",method=RequestMethod.POST)
public String addItem(@ModelAttribute("inp") OrderDetails inp)
{
addItemImplementation(inp);
return "redirect:/menu"; 

}

/**
 * It consists of the Business Logic for adding items to the Cart.
 * @param inp , It takes the OrderDetails from the addItem method.
 */

public void addItemImplementation(OrderDetails inp)
{
	if(inp.quantity>0)
	{
	int flag=0;
	if(order.get(hotelName)==null)
	{	
		ArrayList<OrderDetails> al=new ArrayList<>();
		al.add(new OrderDetails(inp.item,inp.quantity,inp.price));
		order.put(hotelName,al);
	}
		else
	{
		for(OrderDetails x : order.get(hotelName))
			if(x.item.equals(inp.item))
			{
				x.quantity+=inp.quantity;
				flag=1;
			}
	
		if(flag==0)
			{
			order.get(hotelName).add(new OrderDetails(inp.item,inp.quantity,inp.price));
			}
	}
	
	}

 

int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<>();

	if(order.get(hotelName)!=null)
	for(OrderDetails y : order.get(hotelName))
		{
		temp1.add(new OrderDetails(y.getItem(),y.getQuantity(),y.quantity*y.getPrice()));
		totalBill+=y.quantity*y.getPrice();
		}

bill=totalBill;
	
}

/**
 * Its used to remove an Item from the Cart.
 * It makes use of removeItemLogic for business logic.
 * @param inp, It takes the Item to be removed in the form of OrderDetails object.
 * @return, It redirects to the menu page after removing the selected item.
 */
@RequestMapping(value="removeItem",method=RequestMethod.POST)
public String deleteItem(@ModelAttribute("inp") OrderDetails inp)
{
	deleteItemImplementation(inp);
	return "redirect:/menu"; 
}

/**
 * It consists of the Business Logic for removing items from the Cart.
 * @param inp , it takes the OrderDetails from the removeItem method.
 */
public void deleteItemImplementation(OrderDetails inp)
{
	for(OrderDetails x : order.get(hotelName))
		if(x.getItem().equals(inp.item))
		{
			order.get(hotelName).remove(x);
			break;
		}
int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<>();

	if(order.get(hotelName)!=null)
	for(OrderDetails y : order.get(hotelName))
		{
		temp1.add(new OrderDetails(y.getItem(),y.getQuantity(),y.quantity*y.getPrice()));
		totalBill+=y.quantity*y.getPrice();
		}
	bill=totalBill;

}

/**
 * It's used to checkout the Items in the cart.
 * It saves the Order to the Database.
 * @return after adding the order to the database it redirects to the checkout.
 */
@RequestMapping(value="/checkout",method = RequestMethod.POST)
public String userCheckout()
{

Random rand = new Random();
 orderid=rand.nextInt(1000);
while(!orderrepository.findByOrderid(orderid).isEmpty())
{	orderid=rand.nextInt(1000);}
	for(OrderDetails x : order.get(hotelName))
{
	Order temp = new Order();
	temp.setOrderid(orderid);
	temp.setHname(hotelName);
	temp.setUname(username);
	temp.setItem(x.item);
	temp.setPrice(x.price);
	temp.setQuantity(x.getQuantity());
	temp.setTotal(bill);
	orderrepository.save(temp);	
}
	order.put(hotelName, null);
return "redirect:/checkout";	
}


/**
 * It returns the checkout page that asks the user for Rating and Reviews.
 * @return, checkout JSP that asks the user for Rating and Reviews.
 */
@RequestMapping(value="/checkout")
public String checkoutPage()
{
return "checkout";	
}


/**
 * It's used to submit the ratings and reviews of a hotel
 * It stores the reviews to the database.
 * @param inp , It takes the rating and review from user in form of Rating object.
 * @return , it redirects the user to submit-rating page.
 */
@RequestMapping(value="/submit-rating",method=RequestMethod.POST)
public String addReview(@ModelAttribute("inp") Rating inp)
{
	Review r = new Review();
	r.setHname(hotelName);
	r.setUname(username);
	r.setRating(inp.getRating());
	r.setReview(inp.getReview());
	reviewrepository.save(r);
	return "redirect:/submit-rating";
}

/**
 * @return It thanks the user for submitting the rating and review.
 */
@RequestMapping(value="/submit-rating")
public String thankReview()
{
	return "thanks";
}


/**
 * It's used to get the Rating and reviews of hotels.
 * @param model , Its used to add attributes to JSP pages.
 * @return , It returns JSP pages consisting of reviews.
 */
@RequestMapping("view-reviews")
public String getReviews(Model model)
{
	List<Review> r = reviewrepository.findByHname(hotelName);
	model.addAttribute("name",hotelName);
	model.addAttribute("reviews",r);

	
	return "view-reviews";
}

/**
 * It's used to retrieve all the orders made by the signed in user.
 * @param model , Its used to add attributes to JSP pages.
 * @return
 */
@RequestMapping(value="/view-orders",method = RequestMethod.GET)
public String getOrders(Model model)
{
	
	model.addAttribute("name", username);
	List<Order> x = orderrepository.findByUname(username);
	List<Order> temp = new ArrayList<>();
	for(Order y : x )
		temp.add(new Order(y.getOrderid(),y.getUname(),y.getHname(),y.getItem(),y.getPrice(),y.getQuantity(),y.getTotal()));
	Map<Integer,ArrayList<Order>> tempmap = new HashMap<>();
	
	for(Order y : temp)
	{
		if(tempmap.get(y.getOrderid())==null)
		{
			ArrayList<Order> temp1 = new ArrayList<>();
			temp1.add(y);
			tempmap.put(y.getOrderid(), temp1);
		}
		else
			tempmap.get(y.getOrderid()).add(y);
	}
	
	
	ArrayList<DisplayOrder> disp = new ArrayList<>();
	Set<Integer> keys = tempmap.keySet();

	for(int i : keys)
	{
		
		DisplayOrder tempo=new DisplayOrder();
		tempo.orderid=i;
		tempo.items=new ArrayList<Order>();
		for(Order e : tempmap.get(i))
			{
			
			tempo.items.add(e);
			tempo.hname=e.getHname();
			tempo.total=e.getTotal();
			

			}
		
		
		disp.add(tempo);
	}
	 	
	
	model.addAttribute("invoices",disp);
	return "vieworder";
}


/**
 * Its used to return a new view for the user to register.
 * @return, returns a view for the user to register.
 */
@RequestMapping(value="/register",method = RequestMethod.GET)
public ModelAndView register()
{
	return new ModelAndView("register","command",new User());
}

/**
 * It checks if the UserName exists or not, and create a new user if the entered name
 * does not exist.
 * @param inp, it takes the user details in the for of User object.
 * @param model , it's used for adding attributes to the JSP page
 * @return, if username isn't already registered it returns login page else returns 
 * register page for re-sign-up. 
 */
@RequestMapping(value="/register",method = RequestMethod.POST)
public String addUser(@ModelAttribute("inp") User inp , Model model) 
{
	

	if(inp.getUname().length()>20||inp.getPassword().length()>20)
	{
		CustomException ce = new CustomException();
		ce.setMessage("UserName and password cannot be greater than 20 chars! ");
		
		throw ce;
		}

		if(refImpl.addUserImplementation(inp, usersrepository).equals("login"))
				{
			return "login";
				}
		else
		{
			model.addAttribute(ERROR,  "Invalid credentials or UserName is already taken! please choose another one.");
			return "register";
		}
}

/**
 * It's used to handle exception when the username or password are greater than 20 characters.
 * @param e , CustomException object that consists of the error message of the exception
 * @param model , it's used for adding attributes to JSP pages
 * @return, It return an Error page consisting of the description of error.
 */
@ExceptionHandler(CustomException.class)
public String lengthException(CustomException e,Model model) 
{
CustomException ce = e;
model.addAttribute(ERROR,ce.getMessage());
return ERROR;
}

/**
 * It's used to handle login using invalid credentials
 * @return, it returns login-failure JSP page
 */
@RequestMapping("/login-failure")
public String loginFail()
{
return "login-failure"; 	
}

/**
 * It's used to handle forgot password
 * @return, forgot JSP page.
 */
@RequestMapping("/forgot-password")
public String forgotPassword()
{
return "forgot";
}

/**
 * Asks the user for the username for which they want to reset the password for.
 * @param inp , it takes user details in the form of User object.
 * @param model, it's used for adding details to JSP page
 * @return , it returns forgotreply JSP page.
 */
@RequestMapping(value="/forgot-password",method = RequestMethod.POST)
public String forgotPasswordReply(@ModelAttribute("inp") User inp , Model model)
{
	
	model.addAttribute("answer",refImpl.forgetPasswordReplyImplementation(inp,usersrepository));
	return "forgotreply";

}

/**
 * It's used for overriding default white label error path
 */
@Override
public String getErrorPath() {
	return "/error";
}

/**
 * It's used override White label error page
 * @param model, it's used to add attributes to the JSP pages
 * @return, It returns a JSP page describing the reason for error.
 */
@RequestMapping("/error")
public String error(Model model)
{
	logger.info("user tried accessing unauthorized resources");
	model.addAttribute(ERROR,"You are Unauthorised to access the resource!");
	return ERROR;
}
}

