package errorhandling;
/**
 * This class is used to throw custom exceptions by adding status and message to it
 * @author gowtham
 *
 */
public class CustomException extends RuntimeException {

	int status;
	String message;
	public CustomException()
	{
		//to create custom exceptions
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
