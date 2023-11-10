package callsManagement;

import java.time.LocalDate;
import java.time.Duration;

public abstract class Call {
	private int phoneNumber;
	private LocalDate callDate;
	private Duration callDuration;
	private Contact contact;
	// Constructors

	public Call(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Call() {

	}

	public int setDurationToMinutes() {
		return (int) this.callDuration.toMinutes();
	}

	// Getters

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getCallDate() {
		return callDate;
	}

	public Duration getCallDuration() {
		return callDuration;
	}

	public Contact getContact() {
		return contact;
	}

	// Setters

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCallDate(LocalDate callDate) {
		this.callDate = callDate;
	}

	public void setCallDuration(Duration callDuration) {
		this.callDuration = callDuration;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	// Cost

	public abstract double cost();

}
