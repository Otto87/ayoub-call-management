package callsManagement;

import java.time.Duration;
import java.time.LocalDate;

public class OutGoingCall extends Call {

	public OutGoingCall(String phoneNumber, LocalDate callDate, Duration callDuration, Contact contact) {
		super(phoneNumber, callDate, callDuration, contact);
	}

	@Override
	public double cost() {
		if (this.getCallDuration() != null)
			return this.setDurationToMinutes() * 2;
		throw new RuntimeException("The call duration can't be null !!");
	}

}
