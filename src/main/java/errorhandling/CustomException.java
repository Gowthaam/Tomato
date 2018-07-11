package errorhandling;
import org.hibernate.exception.DataException;
public class CustomException extends RuntimeException {

	int status;
	String message;
	public CustomException()
	{
		
	}
	
	public void setStatus(int status)
	{	
		this.status=status;
	}
	public int getStatus()
	{
		return status;
	}
	
	public void setMessage(String message)
	{
		this.message=message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
