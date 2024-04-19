package by.fksis.telephone_directory.model;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class SubscriberDirectory implements Repository<Subscriber> {
	
	private Set<Subscriber> subscribers;
	
	public SubscriberDirectory() {
		subscribers = new HashSet<>();
	}

	@Override
	public void create(Subscriber subscriber) throws RepositoryException {
		if(!subscribers.add(subscriber)) {
			throw new RepositoryException("SUBNUM_EXIST");
		}
	}

	@Override
	public Optional<Subscriber> read(long number) {
		return subscribers.stream().filter(sub -> sub.getNumber() == number).findAny();
	}

	@Override
	public void update(long number, Subscriber subscriber) throws RepositoryException {
		if(subscriber.getNumber() != number && subscribers.contains(subscriber)) {
			throw new RepositoryException("SUBNUM_EXIST");
		}
		read(number).ifPresent(sub -> {
			sub.setNumber(subscriber.getNumber());
			sub.setFullName(subscriber.getFullName());
			sub.setAddress(subscriber.getAddress());
		});
	}

	@Override
	public void delete(long number) throws RepositoryException {
		if(!subscribers.removeIf(sub -> sub.getNumber() == number)) {
			throw new RepositoryException("SUBNUM_DSNT_EXIST");
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
	
	@Override
	public void clear() {
		subscribers.clear();
	}

}