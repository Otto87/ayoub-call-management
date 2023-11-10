package callsManagement;

public class OutGoingCall extends Call {

	@Override
	public double cost() {
		if (this.getCallDuration() != null) return this.setDurationToMinutes() * 2;
		throw new RuntimeException("The call duration can't be null !!");
	}

}
