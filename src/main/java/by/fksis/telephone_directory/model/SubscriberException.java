package by.fksis.telephone_directory.model;

import java.util.ResourceBundle;

public class SubscriberException extends RuntimeException {

	private static final long serialVersionUID = -7812148264356440939L;

	enum MessageKey {
		INVALID_NUMBER, NULL_FULL_NAME, NULL_ADDRESS, INVALID_SURNAME, INVALID_NAME, INVALID_PATRONYMIC,
		INVALID_POPULATED_AREA, INVALID_STREET, INVALID_HOUSE, INVALID_FLAT
	}
	
	public SubscriberException() {
		
	}
	
	public SubscriberException(String message) {
		super(message);
	}
	
	public SubscriberException(Throwable cause) {
		super(cause);
	}
	
	public SubscriberException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SubscriberException(MessageKey messageKey) {
		super(messageKey.name());
	}
	
	public SubscriberException(MessageKey messageKey, Throwable cause) {
		super(messageKey.name(), cause);
	}
	
	@Override
	public String getLocalizedMessage() {
		ResourceBundle rb = ResourceBundle.getBundle("exceptmess");
		return rb.containsKey(getMessage()) ? rb.getString(getMessage()) : getMessage();
	}
	
}