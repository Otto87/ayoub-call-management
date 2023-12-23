package callsManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Device implements IDevice {
	public Device() {
		super();
	}

	private List<Call> calls = new ArrayList<Call>();
	private List<Contact> contacts = new ArrayList<Contact>();

	// Get a contact by its phone number
	@Override
	public Contact getContactByPhoneNumber(int num) {
		for (Contact contact : this.contacts) {

			if (contact.isItContactPhoneNumber(num)) // A method of the class Contact that checks if a phone number is
														// associated to a contact
				return contact;
		}
		throw new RuntimeException("This number is not saved in any contact on this device !!");
	}

	// Get a contact by a keyword

	@Override
	public Contact getContactByKeyWord(String keyword) {
		for (Contact contact : this.contacts) {

			if (contact.doesNameStartWith(keyword)) // A method of the class Contact that checks if a contact name
													// starts with a keyword
				return contact;
		}
		throw new RuntimeException("No contact name starts with " + keyword + " on this device !!");
	}

	// A certain contact's total cost :

	@Override
	public double totalCallsCostByContact(Contact contact) {

		// this.calls.stream().filter(c -> c.equals(contact)).map(c ->
		// c.cost()).mapToDouble(Double::doubleValue).sum();

		List<Call> contactCalls = this.calls.stream()
				.filter(c -> c.getContact()
						.equals(contact))
				.collect(Collectors.toList());

		double cost = this.totalCost(contactCalls);

		return cost;
	}

	@Override
	public double totalCost(List<Call> calls) {
		double cost = 0;
		for (Call call : calls) {
			cost += call.cost();
		}
		return cost;
	}

	// Calls total cost :

	@Override
	public double totalCallsCost() {
		double cost = 0;
		for (Call call : this.calls) {

			cost += call.cost();
		}
		return cost;
	}

	// Get the calls made between 2 dates inside a calls list
	@Override
	public List<Call> getCallsBetween(LocalDate startDate, LocalDate endDate) {
		List<Call> callsInBetween = new ArrayList<Call>();
		for (Call call : this.calls) {
			if (call.getCallDate().isAfter(startDate) &&
					call.getCallDate().isBefore(endDate)) {
				callsInBetween.add(call);
			}
		}
		return callsInBetween;
	}

	// Calls total cost between 2 dates :
	@Override
	public double totalCallsCostInBetween(LocalDate startDate, LocalDate endDate) {
		return this.totalCost(this.getCallsBetween(startDate, endDate));
	}

	@Override
	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}

	@Override
	public void addCall(Call call) {
		this.calls.add(call);
	}

}
