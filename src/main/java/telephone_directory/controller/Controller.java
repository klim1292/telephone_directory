package main.java.telephone_directory.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import main.java.telephone_directory.model.Repository;
import main.java.telephone_directory.model.RepositoryException;
import main.java.telephone_directory.model.Subscriber;
import main.java.telephone_directory.view.View;

public class Controller {
	
	private Repository<Subscriber> model;
	private View view;
	
	public Controller(Repository<Subscriber> model, View view) {
		this.model = model;
		this.view = view;
	}
	
	public void run() {
		view.addSubscriberMouseListener(new SubscriberMouseListener());
		view.addResetActionListener(new ResetActionListener());
		view.addSearchActionListener(new SearchActionListener());
		view.addAddActionListener(new AddActionListener());
		view.addUpdateActionListener(new UpdateActionListener());
		view.addDeleteActionListener(new DeleteActionListener());
		view.setVisible(true);
	}
	
	private class SubscriberMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			long phone = Long.parseLong(view.readSubscriber()[0]);
			Optional<Subscriber> op = model.read(phone);
			if(op.isPresent()) {
				Subscriber subscriber = op.get();
				view.setPhone(subscriber.getPhone());
				view.setSurname(subscriber.getFullName().getSurname());
				view.setName(subscriber.getFullName().getName());
				view.setPatronymic(subscriber.getFullName().getPatronymic());
				view.setPopulatedArea(subscriber.getAddress().getPopulatedArea());
				view.setStreet(subscriber.getAddress().getStreet());
				view.setHouse(subscriber.getAddress().getHouse());
				view.setCorps(subscriber.getAddress().getCorps());
				view.setFlat(subscriber.getAddress().getFlat());
				view.setUpdateEnabled(true);
				view.setDeleteEnabled(true);
			}
		}
		
	}
	
	private class ResetActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			view.resetForm();
			view.setUpdateEnabled(false);
			view.setDeleteEnabled(false);
		}
		
	}
	
	private class SearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Predicate<Subscriber> criterion = sub -> sub != null;
			if(view.getPhone() != 0L) {
				criterion = criterion.and(sub -> view.getPhone() == sub.getPhone());
			}
			if(!view.getSurname().isBlank()) {
				criterion = criterion.and(sub -> view.getSurname().equals(sub.getFullName().getSurname()));
			}
			if(!view.getName().isBlank()) {
				criterion = criterion.and(sub -> view.getName().equals(sub.getFullName().getName()));
			}
			if(!view.getPatronymic().isBlank()) {
				criterion = criterion.and(sub -> view.getPatronymic().equals(sub.getFullName().getPatronymic()));
			}
			if(!view.getPopulatedArea().isBlank()) {
				criterion = criterion.and(sub -> view.getPopulatedArea().equals(sub.getAddress().getPopulatedArea()));
			}
			if(!view.getStreet().isBlank()) {
				criterion = criterion.and(sub -> view.getStreet().equals(sub.getAddress().getStreet()));
			}
			if(view.getHouse() != 0) {
				criterion = criterion.and(sub -> view.getHouse() == sub.getAddress().getHouse());
			}
			if(!view.getCorps().isBlank()) {
				criterion = criterion.and(sub -> view.getCorps().equals(sub.getAddress().getCorps()));
			}
			if(view.getFlat() != 0) {
				criterion = criterion.and(sub -> view.getFlat() == sub.getAddress().getFlat());
			}
			
			List<Subscriber> subscribers = model.search(criterion);
			view.clearSubscribers();
			for(Subscriber subscriber : subscribers) {
				view.addSubscriber(Long.toString(subscriber.getPhone()), subscriber.getFullName().toString(), subscriber.getAddress().toString());
			}
			
			view.setUpdateEnabled(false);
			view.setDeleteEnabled(false);
		}
		
	}
	
	private class AddActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Subscriber subscriber = new Subscriber(view.getPhone());
				subscriber.setFullName(subscriber.new FullName(view.getSurname(), view.getName(), view.getPatronymic()));
				subscriber.setAddress(subscriber.new Address(view.getPopulatedArea(), view.getStreet(), view.getHouse(), view.getCorps(), view.getFlat()));
				
				model.create(subscriber);
				view.addSubscriber(Long.toString(subscriber.getPhone()), subscriber.getFullName().toString(), subscriber.getAddress().toString());
				
				view.resetForm();
				view.setUpdateEnabled(false);
				view.setDeleteEnabled(false);
			} catch(IllegalArgumentException | RepositoryException ex) {
				view.showExceptionMessage(ex.getMessage());
			}
		}
		
	}
	
	private class UpdateActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Subscriber subscriber = new Subscriber(view.getPhone());
				subscriber.setFullName(subscriber.new FullName(view.getSurname(), view.getName(), view.getPatronymic()));
				subscriber.setAddress(subscriber.new Address(view.getPopulatedArea(), view.getStreet(), view.getHouse(), view.getCorps(), view.getFlat()));
				
				model.update(Long.parseLong(view.readSubscriber()[0]), subscriber);
				view.updateSubscriber(Long.toString(subscriber.getPhone()), subscriber.getFullName().toString(), subscriber.getAddress().toString());
				
				view.resetForm();
				view.setUpdateEnabled(false);
				view.setDeleteEnabled(false);
			} catch(IllegalArgumentException | RepositoryException ex) {
				view.showExceptionMessage(ex.getMessage());
			}
		}
		
	}
	
	private class DeleteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.delete(Long.parseLong(view.readSubscriber()[0]));
				view.deleteSubscriber();
				
				view.resetForm();
				view.setUpdateEnabled(false);
				view.setDeleteEnabled(false);
			} catch(RepositoryException ex) {
				view.showExceptionMessage(ex.getMessage());
			}
		}
		
	}
	
}