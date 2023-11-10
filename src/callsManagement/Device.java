package callsManagement;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class Device {
	private List<Call> calls = new ArrayList();
	private List<Contact> contacts = new ArrayList();
	private TotalCostCalculator totalCostCalculator;
	
	// Constructors
	
	
	public Device(List<Call> calls) {
		this.calls = calls;
	}
	public Device( ) {
		
	}
	
	// Getter
	
	public List<Call> getCalls() {
		return this.calls;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	
	// Setters
	
	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	// Get a contact by its phone number :
	
	public Contact getContactByPhoneNumber(int num) {
		for (Contact contact : this.contacts) {
			if (contact.isItContactPhoneNumber(num)) return contact;
		}
		return null;
	}
	
	// Get a contact by a keyword :
	
	public Contact getContactByKeyWord(String keyword) {
		for (Contact contact : this.contacts) {
			if (contact.doesNameStartWith(keyword)) return contact;
		}
		return null;
	}
	
	// Calls total cost :
	
	public double totalCallsCost() {
		return this.totalCostCalculator.totalCost(this.getCalls());
	}
	
	// Calls total cost between 2 dates :
	
	public double totalCallsCostInBetween(LocalDate startDate, LocalDate endDate) {
		List<Call> callsInBetween = new ArrayList();
		for (Call call : this.calls) {
			if (call.getCallDate().isAfter(startDate) && call.getCallDate().isBefore(endDate)) {
				callsInBetween.add(call);
			}
		}
		return this.totalCostCalculator.totalCost(callsInBetween);
	}
	
	// A certain contact's total cost :
	
	public double contactTotalCost(Contact contact) {
		List<Call> contactCalls = new ArrayList();
		for (Call call : this.calls) {
			if (call.getContact().equalsContact(contact)) {
				contactCalls.add(call);
			}
		}
		if (contactCalls.size() != 0) return this.totalCostCalculator.totalCost(contactCalls);
		
		throw new RuntimeException("This contact is not saved on this device !!");
	}
	
	
	
	
	
}
