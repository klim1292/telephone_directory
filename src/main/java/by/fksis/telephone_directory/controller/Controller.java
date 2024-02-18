package by.fksis.telephone_directory.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

import by.fksis.telephone_directory.model.Repository;
import by.fksis.telephone_directory.model.RepositoryException;
import by.fksis.telephone_directory.model.Subscriber;
import by.fksis.telephone_directory.model.SubscriberDirectory;
import by.fksis.telephone_directory.model.SubscriberException;
import by.fksis.telephone_directory.view.View;

public class Controller {
	
	private Repository<Subscriber> model;
	private View view;
	
	public Controller(Repository<Subscriber> model, View view) {
		this.model = Objects.requireNonNull(model);
		this.view = Objects.requireNonNull(view);
	}
	
	public void run() {
		view.addOpenActionListener(new OpenActionListener());
		view.addSaveActionListener(new SaveActionListener());
		view.addExitActionListener(new ExitActionListener());
		view.addLanguageActionListener(new LanguageActionListener());
		view.addSubscribersMouseListener(new SubscribersMouseListener());
		view.addResetActionListener(new ResetActionListener());
		view.addSearchActionListener(new SearchActionListener());
		view.addAddActionListener(new AddActionListener());
		view.addUpdateActionListener(new UpdateActionListener());
		view.addDeleteActionListener(new DeleteActionListener());
		view.setVisible(true);
	}
	
	private class OpenActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.showOpenDialog().ifPresent(file -> {
				try {
					((SubscriberDirectory)model).open(file);
					view.reset();
					view.deleteAllSubscribers();
					model.list().forEach(sub -> view.addSubscriber(sub.getNumber(), sub.getFullName().toString(), sub.getAddress().toString()));
				} catch (RepositoryException ex) {
					view.showExceptionMessage(ex.getLocalizedMessage());
				} catch (ClassNotFoundException | IOException ex) {
					view.showExceptionMessage(ex.toString());
				}
			});
		}
		
	}
	
	private class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.showSaveDialog().ifPresent(file -> {
				try {
					((SubscriberDirectory)model).save(file);
				} catch (RepositoryException ex) {
					view.showExceptionMessage(ex.getLocalizedMessage());
				} catch (IOException ex) {
					view.showExceptionMessage(ex.toString());
				}
			});
		}
		
	}
	
	private class ExitActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			view.dispose();
			System.exit(0);
		}
		
	}
	
	private class LanguageActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.changeLocale();
		}
		
	}
	
	private class SubscribersMouseListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			model.read(view.getSelectedSubscriberNumber()).ifPresent(sub -> {
				view.setNumber(sub.getNumber());
				view.setSurname(sub.getFullName().getSurname());
				view.setName(sub.getFullName().getName());
				view.setPatronymic(sub.getFullName().getPatronymic());
				view.setPopulatedArea(sub.getAddress().getPopulatedArea());
				view.setStreet(sub.getAddress().getStreet());
				view.setHouse(sub.getAddress().getHouse());
				view.setFlat(sub.getAddress().getFlat());
				view.setUpdateEnabled(true);
				view.setDeleteEnabled(true);
			});
		}
		
	}
	
	private class ResetActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.reset();
		}
		
	}
	
	private class SearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Predicate<Subscriber> criterion = Objects::nonNull;
			if(view.getNumber() != 0L) {
				criterion = criterion.and(sub -> sub.getNumber() == view.getNumber());
			}
			if(!view.getSurname().isBlank()) {
				criterion = criterion.and(sub -> sub.getFullName().getSurname().equalsIgnoreCase(view.getSurname()));
			}
			if(!view.getName().isBlank()) {
				criterion = criterion.and(sub -> sub.getFullName().getName().equalsIgnoreCase(view.getName()));
			}
			if(view.getPatronymic() != null) {
				criterion = criterion.and(sub -> view.getPatronymic().equalsIgnoreCase(sub.getFullName().getPatronymic()));
			}
			if(!view.getPopulatedArea().isBlank()) {
				criterion = criterion.and(sub -> sub.getAddress().getPopulatedArea().equalsIgnoreCase(view.getPopulatedArea()));
			}
			if(!view.getStreet().isBlank()) {
				criterion = criterion.and(sub -> sub.getAddress().getStreet().equalsIgnoreCase(view.getStreet()));
			}
			if(view.getHouse() != 0) {
				criterion = criterion.and(sub -> sub.getAddress().getHouse() == view.getHouse());
			}
			if(view.getFlat() != 0) {
				criterion = criterion.and(sub -> sub.getAddress().getFlat() == view.getFlat());
			}
			view.deleteAllSubscribers();
			model.search(criterion).forEach(sub -> view.addSubscriber(sub.getNumber(), sub.getFullName().toString(), sub.getAddress().toString()));
			view.setUpdateEnabled(false);
			view.setDeleteEnabled(false);
		}
		
	}
	
	private class AddActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Subscriber subscriber = new Subscriber(view.getNumber());
				subscriber.setFullName(subscriber.new FullName(view.getSurname(), view.getName(), view.getPatronymic()));
				subscriber.setAddress(subscriber.new Address(view.getPopulatedArea(), view.getStreet(), view.getHouse(), view.getFlat()));
				model.create(subscriber);
				view.addSubscriber(subscriber.getNumber(), subscriber.getFullName().toString(), subscriber.getAddress().toString());
				view.reset();
			} catch(SubscriberException | RepositoryException ex) {
				view.showExceptionMessage(ex.getLocalizedMessage());
			}
		}
		
	}
	
	private class UpdateActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Subscriber subscriber = new Subscriber(view.getNumber());
				subscriber.setFullName(subscriber.new FullName(view.getSurname(), view.getName(), view.getPatronymic()));
				subscriber.setAddress(subscriber.new Address(view.getPopulatedArea(), view.getStreet(), view.getHouse(), view.getFlat()));
				model.update(view.getSelectedSubscriberNumber(), subscriber);
				view.updateSubscriber(subscriber.getNumber(), subscriber.getFullName().toString(), subscriber.getAddress().toString());
				view.reset();
			} catch(SubscriberException | RepositoryException ex) {
				view.showExceptionMessage(ex.getLocalizedMessage());
			}
		}
		
	}
	
	private class DeleteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.delete(view.getSelectedSubscriberNumber());
				view.deleteSubscriber();
				view.reset();
			} catch(RepositoryException ex) {
				view.showExceptionMessage(ex.getLocalizedMessage());
			}
		}
		
	}
	
}