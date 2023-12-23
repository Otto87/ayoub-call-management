package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.Booking;
import entity.Guest;
import entity.Property;
import entity.PropertyType;

public interface IBookingService {
	
///////////////////////////////////////////////////////////////// BOOKINGS /////////////////////////////////////////////////////////////////////////	

	
    Booking createBooking(int idProperty, int idGuest, LocalDate startDate, LocalDate endDate, int guestsNumber, double basePrice) 
	
			throws ClassNotFoundException, SQLException;
    
    Booking getBookingById(String ref) throws ClassNotFoundException, SQLException;
    
	boolean deleteBooking(String ref) throws ClassNotFoundException, SQLException;
	
	public List<Booking> getBookingsByGuest(int idGuest) throws ClassNotFoundException, SQLException;
	
	double getTotalBookingsAmountByGuest(int idGuest) throws ClassNotFoundException, SQLException;
	
	public List<Booking> getBookingsByProperty(int idProperty) throws ClassNotFoundException, SQLException;
	
	double getTotalBookingsAmountByProperty(int idProperty) throws ClassNotFoundException, SQLException;
	
	public List<Booking> getBookingsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException;
	
	double getTotalEarningsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException;

///////////////////////////////////////////////////////////////// PROPERTIES /////////////////////////////////////////////////////////////////////////	


	// The following method is defined in both interfaces IPropertyDao and IBookingDao for dependencies reasons :
	
	boolean checkAvailability(int idProperty, LocalDate startDate) throws ClassNotFoundException, SQLException;
	
	
	Property createProperty(int id, PropertyType type, String title, int roomsNumber,int bathroomsNumber, String size, String location,
			
			boolean availability, double basePrice, List<String> ameneties) throws ClassNotFoundException, SQLException;
	
	Property getPropertyById(int id) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyAmeneties(int idProperty, List<String> ameneties) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyBasePrice(int idProperty, double basePrice) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyAvailability(int idProperty, boolean availability) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyDetails(int idProperty, String title, String description, String rules) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyFeatures(int idProperty, List<String> featuresToAdd) throws ClassNotFoundException, SQLException;
	
//////////////////////////////////////////////////////////// GUEST SEARCH FILTERS ///////////////////////////////////////////////////////////////////	

	List<Property> findPropertiesByfeature(String feature) throws ClassNotFoundException, SQLException;
	
	List<Property> findPropertiesByPriceRange(double lowerThreshold, double higherThreshold) throws ClassNotFoundException, SQLException;
	
	List<Property> findPropertiesByDateAvailability(LocalDate startDate) throws ClassNotFoundException, SQLException;

	
///////////////////////////////////////////////////////////////// Guests ////////////////////////////////////////////////////////////////////////////	

	
	Guest getGuestById(int id) throws ClassNotFoundException, SQLException;
	
	Guest createGuest(int id, String fname, String lname, String sex, int age, String phoneNum, String email, String adress) 
			
			throws ClassNotFoundException, SQLException;
	
	boolean deleteGuest(int id) throws ClassNotFoundException, SQLException;

}
