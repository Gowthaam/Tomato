package com;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model.User;
import com.config.CustomAuth;
import com.model.Order;
import com.model.Review;
import com.repository.HotelRepository;
import com.repository.ItemRepository;
import com.repository.OrderRepository;
import com.repository.ReviewRepository;
import com.repository.UsersRepository;

import errorhandling.CustomException;

import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;


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
public  Map<String,ArrayList<OrderDetails>> order = new HashMap<String,ArrayList<OrderDetails>>();


public static String hotelName = new String("");
public static String username = new String("");
public static int orderid;
public int bill;
static Logger logger =Logger.getLogger(AppController.class);

@RequestMapping("/login")
public String login(Principal principal)
{	if(principal==null)
	{
	order = new HashMap<String,ArrayList<OrderDetails>>();	
	logger.info("entered login page");
	return "login";
	
	}
else 
	return "redirect:/home"; 
}
	
@RequestMapping(value= {"/","/home"})
public String home()
{
	return "redirect:/users/"+CustomAuth.username;
}

@RequestMapping(value= {"/users/{uname}"})
public String home(@PathVariable String uname ,Model model)
{
//username=uname.toUpperCase();
	username=CustomAuth.username.toUpperCase();
	logger.info("User"+username+" logged in");

model.addAttribute("name",username);
model.addAttribute("locations", hotelrepository.findAllLocations());	
return "home";
}

@RequestMapping(value="/hotels",method=RequestMethod.GET)
public String hotels(@ModelAttribute("inp") Locations inp,Model model)
{	
	logger.info("Location"+inp.name+" Selected");

	model.addAttribute("place", inp.name);
	model.addAttribute("hotels",hotelrepository.findByLocation(inp.name));
	return "hotels";	
}

@RequestMapping(value="/menu",method=RequestMethod.POST)
public String menu(@ModelAttribute("inp") Hotels inp,Model model)
{
if(inp.name!=null||inp.name!="")
hotelName=inp.name; 	
logger.info("hotel: "+hotelName+" Selected");

model.addAttribute("place",hotelName);
model.addAttribute("menu",itemrepository.findByHname(hotelName));

int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<OrderDetails>();

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

@RequestMapping(value="/menu")
public String menuGet(Model model)
{

model.addAttribute("place",hotelName);
model.addAttribute("menu",itemrepository.findByHname(hotelName));

int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<OrderDetails>();

	if(order.get(hotelName)!=null)
	for(OrderDetails y : order.get(hotelName))
		{
		temp1.add(new OrderDetails(y.getItem(),y.getQuantity(),y.quantity*y.getPrice()));
		totalBill+=y.quantity*y.getPrice();
		}

bill=totalBill;
model.addAttribute("cart", temp1);
model.addAttribute("totalBill",totalBill);

return "menu";
}

@RequestMapping(value="/addItem",method=RequestMethod.POST)
public String menu(@ModelAttribute("inp") OrderDetails inp,Model model)
{

	if(inp.quantity>0)
	{
	int flag=0;
	if(order.get(hotelName)==null)
	{	
		ArrayList<OrderDetails> al=new ArrayList<OrderDetails>();
		al.add(new OrderDetails(inp.item,inp.quantity,inp.price));
		order.put(hotelName,al);
	}
		else
	{
		for(OrderDetails x : order.get(hotelName))
			if(x.item.equals(inp.item))
			{
				x.quantity+=inp.quantity;
				System.out.println(x.item +"!");
				flag=1;
			}
	
		if(flag==0)
			{
			System.out.println("added!");
			order.get(hotelName).add(new OrderDetails(inp.item,inp.quantity,inp.price));
			}
	}
	
	}

 

int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<OrderDetails>();

	if(order.get(hotelName)!=null)
	for(OrderDetails y : order.get(hotelName))
		{
		temp1.add(new OrderDetails(y.getItem(),y.getQuantity(),y.quantity*y.getPrice()));
		totalBill+=y.quantity*y.getPrice();
		}

bill=totalBill;

return "redirect:/menu"; 

}

@RequestMapping(value="removeItem",method=RequestMethod.POST)
public String removeItem(@ModelAttribute("inp") OrderDetails inp,Model model)
{
	for(OrderDetails x : order.get(hotelName))
			if(x.getItem().equals(inp.item))
			{
				order.get(hotelName).remove(x);
				break;
			}
	int totalBill=0;
	ArrayList<OrderDetails> temp1 = new ArrayList<OrderDetails>();

		if(order.get(hotelName)!=null)
		for(OrderDetails y : order.get(hotelName))
			{
			temp1.add(new OrderDetails(y.getItem(),y.getQuantity(),y.quantity*y.getPrice()));
			totalBill+=y.quantity*y.getPrice();
			}

		bill=totalBill;
	return "redirect:/menu"; 
}

@RequestMapping(value="/checkout",method = RequestMethod.POST)
public String checkout(Model model)
{

Random rand = new Random();
 orderid=rand.nextInt(1000);
while(orderrepository.findByOrderid(orderid).isEmpty()==false)
	orderid=rand.nextInt(1000);
System.out.println(orderid);
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

@RequestMapping(value="/checkout")
public String checkoutPage(Model model)
{
return "checkout";	
}

@RequestMapping(value="/submit-rating",method=RequestMethod.POST)
public String review(@ModelAttribute("inp") Rating inp,Model model)
{
	Review r = new Review();
	r.setHname(hotelName);
	r.setUname(username);
	r.setRating(inp.rating);
	r.setReview(inp.review);
	reviewrepository.save(r);
	return "redirect:/submit-rating";
}
@RequestMapping(value="/submit-rating")
public String review(Model model)
{
	return "thanks";
}



@RequestMapping("view-reviews")
public String viewReviews(Model model)
{
	List<Review> r = reviewrepository.findByHname(hotelName);
	model.addAttribute("name",hotelName);
	model.addAttribute("reviews",r);

	
	return "view-reviews";
}


@RequestMapping(value="/view-orders",method = RequestMethod.GET)
public String viewOrders(Model model)
{
	
	model.addAttribute("name", username);
	//model.addAttribute("invoices",invoices.get(username));
	List<Order> x = orderrepository.findByUname(username);
	List<OrderClone> temp = new ArrayList<OrderClone>();
	for(Order y : x )
		temp.add(new OrderClone(y.getOrderid(),y.getUname(),y.getHname(),y.getItem(),y.getPrice(),y.getQuantity(),y.getTotal()));
	Map<Integer,ArrayList<OrderClone>> tempmap = new HashMap<Integer,ArrayList<OrderClone>>();
	
	for(OrderClone y : temp)
	{
		if(tempmap.get(y.orderid)==null)
		{
			ArrayList<OrderClone> temp1 = new ArrayList<OrderClone>();
			temp1.add(y);
			tempmap.put(y.orderid, temp1);
		}
		else
			tempmap.get(y.orderid).add(y);
	}
	
	
	ArrayList<DisplayOrder> disp = new ArrayList<DisplayOrder>();
	Set<Integer> keys = tempmap.keySet();

	for(int i : keys)
	{
		System.out.println("id: " + i);
		
		DisplayOrder tempo=new DisplayOrder();
		tempo.orderid=i;
		tempo.items=new ArrayList<OrderClone>();
		for(OrderClone e : tempmap.get(i))
			{
			
			tempo.items.add(e);
			System.out.println(e.item);
			tempo.hname=e.hname;
			tempo.total=e.total;
			

			}
		
		
		disp.add(tempo);
	}
	 
	for(DisplayOrder r : disp)
		for(OrderClone e : r.items )
			
	
	model.addAttribute("invoices",disp);
	return "vieworder";
}


@RequestMapping(value="/register",method = RequestMethod.GET)
public ModelAndView register()
{
	return new ModelAndView("register","command",new User());
}

@RequestMapping(value="/register",method = RequestMethod.POST)
public String addUser(@ModelAttribute("inp") Users inp , Model model) 
{
	User user = new User();


	if(inp.uname.length()>20||inp.password.length()>20)
	{
		CustomException ce = new CustomException();
		ce.setMessage("UserName and password cannot be greater than 20 chars! ");
		
		throw ce;
		}

	user.setUname(inp.uname);
	user.setPassword(inp.password);
	System.out.println(inp.uname);
	if(usersrepository.findByUname(inp.uname).isEmpty())
	{
		
		if(usersrepository.save(user)!=null);
		System.out.println("added to db!");
		
		return "login";
	}
	else
	{
		model.addAttribute("error",  "Invalid credentials or UserName is already taken! please choose another one.");
		return "register";
	}
	
}
@ExceptionHandler(CustomException.class)
public String basicException(CustomException e,Model model) throws CustomException
{
CustomException ce = e;
System.out.println("entered handler");
model.addAttribute("error",ce.getMessage());
return "error";
}


@RequestMapping("/login-failure")
public String loginFail()
{
return "login-failure"; 	
}

@RequestMapping("/forgot-password")
public String forgotPassword()
{
return "forgot";
}

@RequestMapping(value="/forgot-password",method = RequestMethod.POST)
public String forgotReply(@ModelAttribute("inp") Users inp , Model model)
{
	if(usersrepository.findByUname(inp.uname).isEmpty())
		{
		model.addAttribute("answer",  "Username not found ! Please Register");
		return "forgotreply";
		}
	else
		{
		model.addAttribute("answer",  "Hi "+inp.uname.toUpperCase()+"! Password has been sent to your registered Mail.");
		return "forgotreply";
		}	
	

}

@Override
public String getErrorPath() {
	System.out.println("set");
	return "/error";
}


@RequestMapping("/error")
public String error(Model model)
{
	model.addAttribute("error","You are Unauthorised to access the resource!");
	return "error";
}



}

