package by.fksis.telephone_directory.model;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = -4121102897254475095L;

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