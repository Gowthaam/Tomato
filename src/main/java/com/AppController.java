package com;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
 Map<String,ArrayList<OrderDetails>> order = new HashMap<>();


  String hotelName ;
  String username ;
  int orderid;
 int bill;
static final Logger logger =Logger.getLogger(AppController.class);
static final String ERROR="error";
@RequestMapping("/login")
public String login(Principal principal)
{	if(principal==null)
	{
	order = new HashMap<>();	
	logger.info("entered login page");
	return "login";
	
	}
else 
	return "redirect:/home"; 
}
	
@RequestMapping(value= {"/","/home"})
public String home(Principal principal)
{
	return "redirect:/users/"+principal.getName();
}

@RequestMapping(value= {"/users/{uname}"})
public String home(@PathVariable String uname ,Principal principal,Model model)
{

	username=principal.getName().toUpperCase();
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

@RequestMapping(value="/menu",method= {RequestMethod.POST,RequestMethod.GET})


public String menu(@ModelAttribute("inp") Hotels inp,Model model)
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



@RequestMapping(value="/addItem",method=RequestMethod.POST)
public String menu(@ModelAttribute("inp") OrderDetails inp,Model model)
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
	ArrayList<OrderDetails> temp1 = new ArrayList<>();

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


@RequestMapping(value="/register",method = RequestMethod.GET)
public ModelAndView register()
{
	return new ModelAndView("register","command",new User());
}

@RequestMapping(value="/register",method = RequestMethod.POST)
public String addUser(@ModelAttribute("inp") User inp , Model model) 
{
	User user = new User();


	if(inp.getUname().length()>20||inp.getPassword().length()>20)
	{
		CustomException ce = new CustomException();
		ce.setMessage("UserName and password cannot be greater than 20 chars! ");
		
		throw ce;
		}

	user.setUname(inp.getUname());
	user.setPassword(inp.getPassword());
	if(usersrepository.findByUname(inp.getUname()).isEmpty())
	{
		
		usersrepository.save(user);		
		return "login";
	}
	else
	{
		model.addAttribute(ERROR,  "Invalid credentials or UserName is already taken! please choose another one.");
		return "register";
	}
	
}
@ExceptionHandler(CustomException.class)
public String basicException(CustomException e,Model model) 
{
CustomException ce = e;
model.addAttribute(ERROR,ce.getMessage());
return ERROR;
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
public String forgotReply(@ModelAttribute("inp") User inp , Model model)
{
	if(usersrepository.findByUname(inp.getUname()).isEmpty())
		{
		model.addAttribute("answer",  "Username not found ! Please Register");
		return "forgotreply";
		}
	else
		{
		model.addAttribute("answer",  "Hi "+inp.getUname().toUpperCase()+"! Password has been sent to your registered Mail.");
		return "forgotreply";
		}	
	

}

@Override
public String getErrorPath() {
	return "/error";
}


@RequestMapping("/error")
public String error(Model model)
{
	model.addAttribute(ERROR,"You are Unauthorised to access the resource!");
	return ERROR;
}



}

