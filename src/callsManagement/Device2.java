package callsManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Device2 {
	private Map<Contact,List<Call>> history = new HashMap<Contact,List<Call>>(); 
	
	// We declare a map containing the saved contacts in our device as keys and each contact's call history / list as values
	                                                         
	private TotalCostCalculator totalCostCalculator;  // the interface we'll use to calculate the different cost methods
	
	public Device2(Map<Contact,List<Call>> history) {
		this.history = history;
	}
	// Getters
	
	
	public Map<Contact, List<Call>> getHistory() {
		return history;
	}
	public TotalCostCalculator getTotalCostCalculator() {
		return totalCostCalculator;
	}
	
	// Setters
	
	
	public void setHistory(Map<Contact, List<Call>> history) {
		this.history = history;
	}

	public void setTotalCostCalculator(TotalCostCalculator totalCostCalculator) {
		this.totalCostCalculator = totalCostCalculator;
	}
	
	// Get a contact by its phone number
	
	public Contact getContactByPhoneNumber(int num) {
		for (Contact contact : this.history.keySet()) {
			
			if (contact.isItContactPhoneNumber(num)) // A method of the class Contact that checks if a phone number is associated to a contact
				return contact;
		}
		throw new RuntimeException("This number is not saved in any contact on this device !!");
	}

	// Get a contact by a keyword
	
	public Contact getContactByKeyWord(String keyword) {
		for (Contact contact : this.history.keySet()) {
			
			if (contact.doesNameStartWith(keyword)) // A method of the class Contact that checks if a contact name starts with a keyword
				return contact;
		}
		throw new RuntimeException("No contact name starts with " + keyword + " on this device !!");
	}
	
	// A certain contact's total cost :
	
	public double totalCallsCostByContact(Contact contact) {
		
		List<Call> contactCalls = this.history.get(contact); // The contact's calls list
		
		double cost = this.getTotalCostCalculator().totalCost(contactCalls); // We calculate the cost using the interface
		
		return cost;                    // We return the value
	}
	
	// Calls total cost :
	
	public double totalCallsCost() {
		double cost = 0;
		for (Contact contact : this.history.keySet()) {     // We iterate through every contact in our device
			
			cost += this.totalCallsCostByContact(contact);  // We calculate the cost of every contact's calls history and add it to the double "cost"
		}
		return cost;
	}
	
	// Get the calls made between 2 dates inside a calls list :
	
	public List<Call> getCallsBetween(List<Call> callsList, LocalDate startDate, LocalDate endDate) {
		List<Call> callsInBetween = new ArrayList();
		for (Call call : callsList) {
			if (call.getCallDate().isAfter(startDate) && call.getCallDate().isBefore(endDate)) {
				callsInBetween.add(call);
			}
		}
		return callsInBetween;
	}
	
	// Calls total cost between 2 dates :
	
	public double totalCallsCostInBetween(LocalDate startDate, LocalDate endDate) {
		
		List<Call> callsInBetween = new ArrayList();   // We create a list where we'll store all the calls beween startDate and EndDate
		
		for (List<Call> callsList : this.history.values()) {
			
			// We iterate through each contact's calls history to find the calls between the two dates
			
			List<Call> contactCallsInBetween = this.getCallsBetween(callsList, startDate, endDate);  
			
			
			callsInBetween.addAll(contactCallsInBetween);   // We add the all the calls between the two dates 
		}
		
		return this.getTotalCostCalculator().totalCost(callsInBetween); // We calculate the cost using the interface 
	}
	
	
	
	
	
	
	
	
	
}
