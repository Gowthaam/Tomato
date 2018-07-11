package errorhandling;

import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ControllerAdvice
public class ErrorHandler {

@ExceptionHandler(CustomException.class)
public String basicException(CustomException e) throws Exception
{
//CustomException ce = new CustomException();
//ce.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//ce.setMessage(e.getMessage());
System.out.println("error handled");
return "thanks";
}


	
	
}
