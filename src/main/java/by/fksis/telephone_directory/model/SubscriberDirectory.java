package by.fksis.telephone_directory.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class SubscriberDirectory implements Repository<Subscriber> {
	
	public static final SubscriberDirectory INSTANCE = new SubscriberDirectory();
	private Set<Subscriber> subscribers;
	
	private SubscriberDirectory() {
		subscribers = new HashSet<>();
	}

	@Override
	public void create(Subscriber subscriber) throws SubscriberDirectoryException {
		if(!subscribers.add(subscriber)) {
			throw new SubscriberDirectoryException(SubscriberDirectoryException.MessageKey.SUBNUM_EXIST);
		}
	}

	@Override
	public Optional<Subscriber> read(long number) {
		return subscribers.stream().filter(sub -> sub.getNumber() == number).findAny();
	}

	@Override
	public void update(long number, Subscriber subscriber) throws SubscriberDirectoryException {
		if(subscriber.getNumber() != number && subscribers.contains(subscriber)) {
			throw new SubscriberDirectoryException(SubscriberDirectoryException.MessageKey.SUBNUM_EXIST);
		}
		read(number).ifPresent(sub -> {
			sub.setNumber(subscriber.getNumber());
			sub.setFullName(subscriber.getFullName());
			sub.setAddress(subscriber.getAddress());
		});
	}

	@Override
	public void delete(long number) throws SubscriberDirectoryException {
		if(!subscribers.removeIf(sub -> sub.getNumber() == number)) {
			throw new SubscriberDirectoryException(SubscriberDirectoryException.MessageKey.SUBNUM_DSNT_EXIST);
		}
	}

	@Override
	public List<Subscriber> search(Predicate<Subscriber> criterion) {
		return subscribers.stream().filter(criterion).collect(Collectors.toList());
	}

	@Override
	public List<Subscriber> list() {
		return List.copyOf(subscribers);
	}
	
	public void open(File file) throws IOException, ClassNotFoundException, SubscriberDirectoryException {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			subscribers.clear();
			Subscriber subscriber;
			while((subscriber = (Subscriber)ois.readObject()) != null) {
				create(subscriber);
			}
		} catch (FileNotFoundException ex) {
			throw new SubscriberDirectoryException(SubscriberDirectoryException.MessageKey.FILE_NOT_FOUND, ex);
		}
	}
	
	public void save(File file) throws IOException, SubscriberDirectoryException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for(Subscriber subscriber : subscribers) {
				oos.writeObject(subscriber);
			}
			oos.writeObject(null);
		} catch (FileNotFoundException ex) {
			throw new SubscriberDirectoryException(SubscriberDirectoryException.MessageKey.FILE_NOT_FOUND, ex);
		}
	}

}