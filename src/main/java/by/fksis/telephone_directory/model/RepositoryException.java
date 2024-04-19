package by.fksis.telephone_directory.model;

import java.util.ResourceBundle;

public class RepositoryException extends Exception {
	
	private static final long serialVersionUID = 4056957928958745140L;

	public RepositoryException() {
		
	}
	
	public RepositoryException(String message) {
		super(message);
	}
	
	public RepositoryException(Throwable cause) {
		super(cause);
	}
	
	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	@Override
	public String getLocalizedMessage() {
		ResourceBundle rb = ResourceBundle.getBundle("exceptmess");
		return rb.containsKey(getMessage()) ? rb.getString(getMessage()) : getMessage();
	}
	
}