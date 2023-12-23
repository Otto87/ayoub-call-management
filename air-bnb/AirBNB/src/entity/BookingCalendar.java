package entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingCalendar {
	
	// for every date, the bookings references that were made on that date.
	private static Map<LocalDate,List<String>> bookings = new HashMap<LocalDate,List<String>>();
	
	// the keys are date tables of 2 elements, a start and an end dates, and the values are the corresponding seasonal multipliers
	public static Map<LocalDate[],Double> seasonalities = new HashMap<LocalDate[],Double>();
	
	                                                                                          // these 2 are used for the pricing
	
	// the keys are date tables of 2 elements, a start and an end dates, and the values are the corresponding special events multipliers
	public static Map<LocalDate[],Double> specialEvents = new HashMap<LocalDate[],Double>();
	
	public static int minDaysForLongStaysDiscount; 
	
	public static double discountMultiplier;
	
	// we suppose these two are not variables and if they are we can just create another calendar for each one like we did
	
    // with the seasonality and special events discount
	

}
