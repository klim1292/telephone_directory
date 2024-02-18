package by.fksis.telephone_directory;

import javax.swing.SwingUtilities;

import by.fksis.telephone_directory.controller.Controller;
import by.fksis.telephone_directory.model.SubscriberDirectory;
import by.fksis.telephone_directory.view.View;

public class App {

    public static void main(String[] args) {
    	Controller controller = new Controller(SubscriberDirectory.INSTANCE, View.INSTANCE);
    	SwingUtilities.invokeLater(() -> controller.run());
    }
    
}