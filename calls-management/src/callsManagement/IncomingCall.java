package callsManagement;

import java.time.Duration;
import java.time.LocalDate;

public class IncomingCall extends Call {

	public IncomingCall(String phoneNumber, LocalDate callDate, Duration callDuration, Contact contact) {
		super(phoneNumber, callDate, callDuration, contact);
	}

	@Override
	public double cost() {
		if (this.getCallDuration() != null)
			return 0;
		throw new RuntimeException("The call duration can't be null !!");

	}
}
