package main.java.telephone_directory.model;

public class Subscriber {
	
	private long phone;
	private FullName fullName;
	private Address address;
	
	public Subscriber(long phoneNumber) {
		setPhoneNumber(phoneNumber);
	}
	
	public class FullName {
		
		private String surname;
		private String name;
		private String patronymic;
		
		public FullName(String surname, String name, String patronymic) {
			setSurname(surname);
			setName(name);
			setPatronymic(patronymic);
		}
		
		public String getSurname() {
			return surname;
		}
		
		private void setSurname(String surname) {
			if(surname == null || !surname.matches("([A-Z]\\p{Lower}+)|([A-Z]\\p{Lower}+-[A-Z]\\p{Lower}+)")) {
				throw new IllegalArgumentException("Invalid surname");
			}
			this.surname = surname;
		}
		
		public String getName() {
			return name;
		}
		
		private void setName(String name) {
			if(name == null || !name.matches("[A-Z]\\p{Lower}+")) {
				throw new IllegalArgumentException("Invalid name");
			}
			this.name = name;
		}
		
		public String getPatronymic() {
			return patronymic;
		}
		
		private void setPatronymic(String patronymic) {
			if(patronymic == null || (!patronymic.isBlank() && !patronymic.matches("[A-Z]\\p{Lower}+"))) {
				throw new IllegalArgumentException("Invalid patronymic");
			}
			this.patronymic = patronymic;
		}
		
		@Override
		public String toString() {
			return String.format("%s %c.%s", surname, name.charAt(0), (!patronymic.isBlank()) ? patronymic.charAt(0) + "." : "");
		}
		
	}
	
	public class Address {
		
		private String populatedArea;
		private String street;
		private int house; 
		private String corps;
		private int flat;
		
		public Address(String populatedArea, String street, int house, String corps, int flat) {
			setPopulatedArea(populatedArea);
			setStreet(street);
			setHouse(house);
			setCorps(corps);
			setFlat(flat);
		}
		
		public String getPopulatedArea() {
			return populatedArea;
		}
		
		private void setPopulatedArea(String populatedArea) {
			if(populatedArea == null || populatedArea.isBlank()) {
				throw new IllegalArgumentException("Invalid populated area");
			}
			this.populatedArea = populatedArea;
		}
		
		public String getStreet() {
			return street;
		}
		
		private void setStreet(String street) {
			if(street == null || street.isBlank()) {
				throw new IllegalArgumentException("Invalid street");
			}
			this.street = street;
		}
		
		public int getHouse() {
			return house;
		}
		
		private void setHouse(int house) {
			if(house < 1) {
				throw new IllegalArgumentException("Invalid house");
			}
			this.house = house;
		}
		
		public String getCorps() {
			return corps;
		}
		
		private void setCorps(String corps) {
			if(corps == null || (!corps.isBlank() && !corps.matches("\\p{Alnum}+"))) {
				throw new IllegalArgumentException("Invalid corps");
			}
			this.corps = corps;
		}
		
		public int getFlat() {
			return flat;
		}
		
		private void setFlat(int flat) {
			if(flat < 0) {
				throw new IllegalArgumentException("Invalid flat");
			}
			this.flat = flat;
		}
		
		@Override
		public String toString() {
			return String.format("%s st. %s h. %d%s%s", populatedArea, street, house, (!corps.isBlank()) ? "/" + corps : "", (flat != 0) ? " fl. " + flat : "");
		}
		
	}
	
	public long getPhone() {
		return phone;
	}
	
	public final void setPhoneNumber(long phone) {
		if(!Long.toString(phone).matches("80\\d{9}")) {
			throw new IllegalArgumentException("Invalid phone");
		}
		this.phone = phone;
	}
	
	public FullName getFullName() {
		return fullName;
	}
	
	public void setFullName(FullName fullName) {
		if(fullName == null) {
			throw new IllegalArgumentException("Full name is null");
		}
		this.fullName = fullName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		if(address == null) {
			throw new IllegalArgumentException("Address is null");
		}
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		return (int)phone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Subscriber subscriber = (Subscriber)obj;
		return phone == subscriber.phone;
	}
	
	@Override
	public String toString() {
		return String.format("%d %s %s", phone, fullName, address);
	}
	
}