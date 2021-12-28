package user.exception;



public class CustomException extends Exception
{
	

	private static final long serialVersionUID = -5037713227166949226L;
	

	public CustomException(String message)
	{
		super(message);
	}

	public CustomException(Exception ex) {
		super(ex);
	}
}
