package by.fksis.telephone_directory.model;

import java.util.ResourceBundle;

public class SubscriberException extends RuntimeException {

	private static final long serialVersionUID = 5274245247193534573L;

	enum MessageKey {
		INVALID_NUMBER, NULL_FULL_NAME, NULL_ADDRESS, INVALID_SURNAME, INVALID_NAME, INVALID_PATRONYMIC,
		INVALID_POPULATED_AREA, INVALID_STREET, INVALID_HOUSE, INVALID_FLAT
	}
	
	public SubscriberException(MessageKey messageKey) {
		super(messageKey.name());
	}
	
	public SubscriberException(MessageKey messageKey, Throwable cause) {
		super(messageKey.name(), cause);
	}
	
	@Override
	public String getLocalizedMessage() {
		return ResourceBundle.getBundle("exceptmess").getString(getMessage());
	}
	
}