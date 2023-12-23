package callsManagement;

import java.time.Duration;
import java.time.LocalDate;

public class Test {
	public static void main(String[] args) {

		Contact contact = new Contact(1, "toto", "0621443366", "014477755");

		IncomingCall incomingCall = new IncomingCall("0622554477",
				LocalDate.now(),
				Duration.ofMinutes(15),
				contact);

		incomingCall.setCallDuration(Duration.ofMinutes(15));

		IDevice device = new Device();

		device.addContact(contact);

		device.addCall(incomingCall);

		System.out.println(device.totalCallsCost());
	}
}
