package callsManagement;

import java.util.List;

public class TotalCost implements TotalCostCalculator {

	public double totalCost(List<Call> calls) {
		double cost = 0;
		for (Call call : calls) {
			cost += call.cost();
		}
		return cost;
	}

}
