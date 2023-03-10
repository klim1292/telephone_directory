package main.java.telephone_directory;

import main.java.telephone_directory.controller.Controller;
import main.java.telephone_directory.model.SubscriberRepositoryImpl;
import main.java.telephone_directory.view.View;

public class App {
	
	public static void main(String[] args) {
		Controller controller = new Controller(SubscriberRepositoryImpl.INSTANCE, new View("Telephone directory"));
		controller.run();
	}
	
}
