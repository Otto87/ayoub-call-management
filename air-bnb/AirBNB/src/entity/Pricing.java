package entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pricing {

	
	
/////////////////////////////////////////////////////////// BASIC CASE CALCULATOR ///////////////////////////////////////////////////////////////////
	

	// discounts for longer stays 
	
	public static double discountPrice(double basePrice, double discount) {
		
		return basePrice * (1 - discount);
		
	}
	
	// seasonality 
	
	public static double seasonalPrice(double basePrice, double seasonalMultiplier) {
		
		return basePrice * seasonalMultiplier;
		
	}
	
	// special events
	
	public static double specialEventPrice(double basePrice, double specialEventMultiplier) {
		
		return basePrice * specialEventMultiplier;
		
	}
	
	// seasonality and special events
	
	public static double seasonalAndSpecialEventPrice(double basePrice, double seasonalMultiplier, double specialEventMultiplier) {
		
		return basePrice * seasonalMultiplier * specialEventMultiplier;
	}
	
	// discount and seasonal
	
	public static double discountAndSeasonalPrice(double basePrice, double discount, double seasonalMultiplier) {
		
		return discountPrice(basePrice, seasonalPrice(basePrice, seasonalMultiplier));
	}
	
	// discount and special event
	
	public static double discountAndSpecialEventPrice(double basePrice, double discount, double specialEventMultiplier) {
		
		return discountPrice(basePrice, seasonalPrice(basePrice, specialEventPrice(basePrice, specialEventMultiplier)));
	}
	
	// discount and seasonality and special events
	
	public static double discountAndSeasonalAndSpecialEventPrice(double basePrice, double discount, double seasonalMultiplier, double specialEventMultiplier) {
		
		return discountPrice(basePrice,seasonalAndSpecialEventPrice(basePrice, seasonalMultiplier, specialEventMultiplier));
	}
	
	
	
	
//////////////////////////////////////////////////// GENERAL BOOKING PRICE CALCULATOR ////////////////////////////////////////////////////////////////

	
	
	// finds if there is a long stay discount or not based on the start and end dates , returns a boolean and the discount multiplier 
	
	public static List<Object> isDiscount(LocalDate startDate, LocalDate endDate) {
		
		List<Object> status = new ArrayList<Object>();
		
		int bookingLength = (int) ChronoUnit.DAYS.between(startDate, endDate);
		
		status.add(Arrays.asList(bookingLength > BookingCalendar.minDaysForLongStaysDiscount, BookingCalendar.discountMultiplier));
		
		return status;
	}
	
	// finds if there is a seasonality discount or not based on the start and end dates , returns a boolean and the seasonality multiplier 
	
	public static List<Object> isSeasonality(LocalDate startDate, LocalDate endDate) {
		
		List<Object> status = new ArrayList<Object>();
		
		for (LocalDate[] datesInterval : BookingCalendar.seasonalities.keySet()) {
			
			if (startDate.isAfter(datesInterval[0]) && startDate.isBefore(datesInterval[1])) {
				
				status.add(Arrays.asList(true, BookingCalendar.seasonalities.get(datesInterval)));
				
				return status;
			}
		}
		
		status.add(false);
		
		return status;
	}
	
	// finds if there is a special event discount or not based on the start and end dates , returns a boolean and the special event multiplier 
	
	public static List<Object> isSpecialEvent(LocalDate startDate, LocalDate endDate) {
		
		List<Object> status = new ArrayList<Object>();
		
		for (LocalDate[] datesInterval : BookingCalendar.specialEvents.keySet()) {
			
			if (startDate.isAfter(datesInterval[0]) && startDate.isBefore(datesInterval[1])) {
				
				status.add(Arrays.asList(true, BookingCalendar.specialEvents.get(datesInterval)));
				
				return status;
			}
		}
		
		status.add(false);
		
		return status;
	}
	
	
	
	// The one method that treats all possible scenarios 
	
	public static double bookingPriceCalculator(double basePrice, LocalDate startDate, LocalDate endDate) {
		
		double bookingPrice = 0;
		
		boolean DiscountStatus = (boolean) isDiscount(startDate, endDate).get(0);
		
		boolean SeasonalityStatus = (boolean) isSeasonality(startDate, endDate).get(0);
		
		boolean SpecialEventStatus = (boolean) isSpecialEvent(startDate, endDate).get(0);
		
		if (DiscountStatus) {         // if it's a long stay discount 
			
			double discountMultiplier = BookingCalendar.discountMultiplier;
			
			if (SeasonalityStatus) {    // if it's a seasonality discount
				
				double seasonalityMultiplier = (double) isSeasonality(startDate, endDate).get(1);
				
				if (SpecialEventStatus) {  // if it's a special event discount
					
					double specialEventMultiplier = (double) isSpecialEvent(startDate, endDate).get(1);
					
					bookingPrice = discountAndSeasonalAndSpecialEventPrice(
							
							basePrice, discountMultiplier, seasonalityMultiplier, specialEventMultiplier); /*is a long stay, seasonality 
							
					                                                                                         and special event discount*/
					
				}
				
				else bookingPrice = discountAndSeasonalPrice(basePrice, discountMultiplier, seasonalityMultiplier);
			}
			
			else {   // is a long stay, no seaonality discount
				
				if (SpecialEventStatus) {  // if there's a special event discount
					
					double specialEventMultiplier = (double) isSpecialEvent(startDate, endDate).get(1);
					
					bookingPrice = discountAndSpecialEventPrice(basePrice, discountMultiplier, specialEventMultiplier);
				}
				
				else bookingPrice = discountPrice(basePrice, discountMultiplier); // is a long stay, neither a seasonality nor a special event discount 
					
			}
		}
		
		else {   // not a long stay
			
			if (SeasonalityStatus) {    // seasonality discount
				
				double seasonalityMultiplier = (double) isSeasonality(startDate, endDate).get(1);
								
				if (SpecialEventStatus) {   // special event discout
					
					double specialEventMultiplier = (double) isSpecialEvent(startDate, endDate).get(1);
					
					bookingPrice = seasonalAndSpecialEventPrice(basePrice, seasonalityMultiplier, specialEventMultiplier);
					
				}
				
				else bookingPrice = seasonalPrice(basePrice, seasonalityMultiplier);    // not a long stay, no special event discount
			}
			
			else {  // no seasonality discount
				
				if (SpecialEventStatus) {  // special event discount
					
					double specialEventMultiplier = (double) isSpecialEvent(startDate, endDate).get(1);
					
					bookingPrice = specialEventPrice(basePrice, specialEventMultiplier);    // not a long stay, no seasonality discount
				}
			}
		}
		
		
		// not a long stay, neither a seasonality nor a special event discount, the booking price is the base price :
		
		bookingPrice = basePrice;
		
		return bookingPrice;    
	
		
	}

}

