package callsManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contact {
	private int id;
	private String name;
	private List<String> phoneNumbers = new ArrayList<String>();

	// Constructors
	public Contact(int id, String name, String... phoneNumber) {
		this.id = id;
		this.name = name;
		this.phoneNumbers.addAll(Arrays.asList(phoneNumber));
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getPhoneNums() {
		return phoneNumbers;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNums(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	// Check if a phone number is affected to this contact :
	public boolean isItContactPhoneNumber(int n) {
		return this.phoneNumbers.contains(n);
	}

	// Check if the contact's name starts with a keyword :
	public boolean doesNameStartWith(String keyword) {
		return this.name.startsWith(keyword);
	}

	// Define 2 equal contacts :

	public boolean equals(Contact c) {
	return this.getId() == c.getId();
	}

	@Override
	public boolean equals(Object o) {
		 // Check if the object is compared with itself
		 if (this == o) {
            return true;
        }

		// Check if the object is null or not of the same class
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

		// Cast the object to the same class
        Contact other = (Contact) o;

		return this.equals(other);
	}

}
