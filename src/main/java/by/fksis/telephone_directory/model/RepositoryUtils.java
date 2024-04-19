package by.fksis.telephone_directory.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class RepositoryUtils {
	
	private RepositoryUtils() {
		
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable>void open(File file, Repository<T> repository) throws IOException, ClassNotFoundException, RepositoryException {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			repository.clear();
			T obj;
			while((obj = (T)ois.readObject()) != null) {
				repository.create(obj);
			}
		} catch (FileNotFoundException ex) {
			throw new RepositoryException("FILE_NOT_FOUND", ex);
		}
	}
	
	public static <T extends Serializable> void save(File file, Repository<T> repository) throws IOException, RepositoryException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for(T obj : repository.list()) {
				oos.writeObject(obj);
			}
			oos.writeObject(null);
		} catch (FileNotFoundException ex) {
			throw new RepositoryException("FILE_NOT_FOUND", ex);
		}
	}
	
}