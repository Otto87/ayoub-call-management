package callsManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Contact {
	private int num;
	private String name;
	private List<Integer> phoneNums = new ArrayList<Integer>();

	// Constructors

	public Contact(int num, String name, List<Integer> phoneNumbers) {
		this.num = num;
		this.name = name;
		this.phoneNums = phoneNumbers;
	}

	public Contact() {

	}

	// Getters

	public int getNum() {
		return num;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getPhoneNums() {
		return phoneNums;
	}

	// Setters

	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNums(List<Integer> phoneNums) {
		this.phoneNums = phoneNums;
	}

	// Check if a phone number is affected to this contact :

	public boolean isItContactPhoneNumber(int n) {
		return this.phoneNums.contains(n);
	}

	// Check if the contact's name starts with a keyword :

	public boolean doesNameStartWith(String keyword) {
		return this.name.startsWith(keyword);
	}

	// Define 2 equal contacts :

	public boolean equalsContact(Contact c) {
		return this.getNum() == c.getNum();
	}

}
