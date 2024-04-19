package by.fksis.telephone_directory.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T> {
	
	void create(T obj) throws RepositoryException;
	Optional<T> read(long id);
	void update(long id, T obj) throws RepositoryException;
	void delete(long id) throws RepositoryException;
	List<T> search(Predicate<T> criterion);
	List<T> list();
	void clear();
	
}