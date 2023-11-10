package callsManagement;

public class IncomingCall extends Call {
	@Override
	public double cost() {
		if (this.getCallDuration() != null) return 0;
		throw new RuntimeException("The call duration can't be null !!");
		
	}
}
