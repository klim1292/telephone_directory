package main.java.telephone_directory.model;

public class RepositoryException extends Exception {
	
	private static final long serialVersionUID = -6636766629161967265L;

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
	
}