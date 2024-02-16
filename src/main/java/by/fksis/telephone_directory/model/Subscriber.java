package by.fksis.telephone_directory.model;

import java.io.Serializable;

public class Subscriber implements Serializable {
	
	private static final long serialVersionUID = 8570283219678083732L;
	private long number;
	private FullName fullName;
	private Address address;
	
	public Subscriber(long number) {
		setNumber(number);
	}
	
	public long getNumber() {
		return number;
	}
	
	public final void setNumber(long number) {
		if(!Long.toString(number).matches("80\\d{9}")) {
			throw new SubscriberException(SubscriberException.MessageKey.INVALID_NUMBER);
		}
		this.number = number;
	}
	
	public FullName getFullName() {
		return fullName;
	}
	
	public void setFullName(FullName fullName) {
		if(fullName == null) {
			throw new SubscriberException(SubscriberException.MessageKey.NULL_FULL_NAME);
		}
		this.fullName = fullName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		if(address == null) {
			throw new SubscriberException(SubscriberException.MessageKey.NULL_ADDRESS);
		}
		this.address = address;
	}
	
	public final class FullName implements Serializable {
		
		private static final long serialVersionUID = 985158851772874602L;
		private String surname;
		private String name;
		private String patronymic;
		
		public FullName(String surname, String name, String patronymic) {
			if(surname == null || !surname.matches("[A-ZА-Я][a-zа-я]+(-[A-ZА-Я][a-zа-я]+)?")) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_SURNAME);
			}
			if(name == null || !name.matches("[A-ZА-Я][a-zа-я]+(-[A-ZА-Я][a-zа-я]+)?")) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_NAME);
			}
			if(patronymic != null && !patronymic.matches("[A-ZА-Я][a-zа-я]+(-[A-ZА-Я][a-zа-я]+)?")) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_PATRONYMIC);
			}
			this.surname = surname;
			this.name = name;
			this.patronymic = patronymic;
		}
		
		public String getSurname() {
			return surname;
		}
		
		public String getName() {
			return name;
		}
		
		public String getPatronymic() {
			return patronymic;
		}
		
		@Override
		public String toString() {
			return String.format("%s %c.%s", surname, name.charAt(0), patronymic != null ? patronymic.charAt(0) + "." : "");
		}
		
	}
	
	public final class Address implements Serializable {
		
		private static final long serialVersionUID = 3152068978596230181L;
		private String populatedArea;
		private String street;
		private int house;
		private int flat;
		
		public Address(String populatedArea, String street, int house, int flat) {
			if(populatedArea == null || populatedArea.isBlank()) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_POPULATED_AREA);
			}
			if(street == null || street.isBlank()) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_STREET);
			}
			if(house < 1) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_HOUSE);
			}
			if(flat < 0) {
				throw new SubscriberException(SubscriberException.MessageKey.INVALID_FLAT);
			}
			this.populatedArea = populatedArea;
			this.street = street;
			this.house = house;
			this.flat = flat;
		}
		
		public String getPopulatedArea() {
			return populatedArea;
		}
		
		public String getStreet() {
			return street;
		}
		
		public int getHouse() {
			return house;
		}
		
		public int getFlat() {
			return flat;
		}
		
		@Override
		public String toString() {
			return String.format("%s, %s %d%s", populatedArea, street, house, flat > 0 ? "-" + flat : "");
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || obj.getClass() != getClass()) {
			return false;
		}
		Subscriber subscriber = (Subscriber)obj;
		return subscriber.number == number;
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(number);
	}
	
	@Override
	public String toString() {
		return "Subscriber[Number=" + number + ", FullName=" + fullName + ", Address=" + address+"]";
	}
	
}