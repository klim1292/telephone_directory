package main.java.telephone_directory.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubscriberRepositoryImpl implements Repository<Subscriber> {
	
	private Set<Subscriber> subscribers;
	public static final SubscriberRepositoryImpl INSTANCE = new SubscriberRepositoryImpl();
	
	private SubscriberRepositoryImpl() {
		subscribers = new HashSet<>();
	}
	
	@Override
	public void create(Subscriber obj) throws RepositoryException {
		if(!subscribers.add(obj)) {
			throw new RepositoryException("The subscriber with the entered phone number exists");
		}
	}

	@Override
	public Optional<Subscriber> read(long id) {
		return subscribers.stream().filter(sub -> sub.getPhone() == id).findAny();
	}

	@Override
	public void update(long id, Subscriber obj) throws RepositoryException {
		if(id != obj.getPhone() && subscribers.contains(obj)) {
			throw new RepositoryException("The subscriber with the entered phone number exists");
		}
		Optional<Subscriber> op = read(id);
		if(op.isPresent()) {
			Subscriber sub = op.get();
			sub.setPhoneNumber(obj.getPhone());
			sub.setFullName(obj.getFullName());
			sub.setAddress(obj.getAddress());
		}
	}

	@Override
	public void delete(long id) throws RepositoryException {
		if(!subscribers.removeIf(sub -> sub.getPhone() == id)) {
			throw new RepositoryException("The selected subscriber does not exist");
		}
	}

	@Override
	public List<Subscriber> search(Predicate<Subscriber> criterion) {
		return subscribers.stream().filter(criterion).collect(Collectors.toList());
	}

	@Override
	public List<Subscriber> list() {
		return new ArrayList<Subscriber>(subscribers);
	}

}