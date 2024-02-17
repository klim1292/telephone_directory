package by.fksis.telephone_directory.model;

import java.util.ResourceBundle;

class SubscriberDirectoryException extends RepositoryException {
	
	private static final long serialVersionUID = 984176133801277176L;

	enum MessageKey {
		SUBNUM_EXIST, SUBNUM_DSNT_EXIST, FILE_NOT_FOUND
	}
	
	public SubscriberDirectoryException(MessageKey messageKey) {
		super(messageKey.name());
	}
	
	public SubscriberDirectoryException(MessageKey messageKey, Throwable cause) {
		super(messageKey.name(), cause);
	}
	
	@Override
	public String getLocalizedMessage() {
		return ResourceBundle.getBundle("exceptmess").getString(getMessage());
	}
	
}