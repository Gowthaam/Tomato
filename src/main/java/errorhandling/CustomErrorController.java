package errorhandling;


import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CustomErrorController implements ErrorController {

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
