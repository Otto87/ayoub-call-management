package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.Booking;
import entity.Guest;
import entity.Property;
import entity.PropertyType;
import dao.PropertyDao;
import dao.BookingDao;
import dao.GuestDao;
import dao.IPropertyDao;
import dao.IBookingDao;
import dao.IGuestDao;


public class BookingService implements IBookingService {
	
	private IPropertyDao iproperty = new PropertyDao();
	
	private IBookingDao ibooking = new BookingDao();
	
	private IGuestDao iguest = new GuestDao();
	
	
///////////////////////////////////////////////////////////////// BOOKINGS /////////////////////////////////////////////////////////////////////////	
	
	public Booking createBooking(int idProperty, int idGuest, LocalDate startDate, LocalDate endDate, int guestsNumber,
			
			double basePrice) throws ClassNotFoundException, SQLException {
		
		Booking booking = ibooking.createBooking(idProperty, idGuest, startDate, endDate, guestsNumber, basePrice);
		
		iproperty.setPropertyAvailability(idProperty, false);  // set the booked property's availability to false
		
		return booking;
	}
	
	public Booking getBookingById(String ref) throws ClassNotFoundException, SQLException {
		
		return ibooking.getBookingByRef(ref);
		
	}

	
	public boolean deleteBooking(String ref) throws ClassNotFoundException, SQLException {
		
		return ibooking.deleteBooking(ref);
	}
	
	public List<Booking> getBookingsByGuest(int idGuest) throws ClassNotFoundException, SQLException {

		return ibooking.getBookingsByGuest(idGuest);
		
	}
	
	public double getTotalBookingsAmountByGuest(int idGuest) throws ClassNotFoundException, SQLException {

		return ibooking.getTotalBookingsAmountByGuest(idGuest);
	}

	
	public List<Booking> getBookingsByProperty(int idProperty) throws ClassNotFoundException, SQLException {

		return ibooking.getBookingsByProperty(idProperty);
	}

	
	public double getTotalBookingsAmountByProperty(int idProperty) throws ClassNotFoundException, SQLException {

		return ibooking.getTotalBookingsAmountByProperty(idProperty);
	}
	
///////////////////////////////////////////////////////////////// PROPERTIES /////////////////////////////////////////////////////////////////////////	

	
	public boolean checkAvailability(int idProperty, LocalDate startDate) throws ClassNotFoundException, SQLException {
		
		return ibooking.checkAvailability(idProperty, startDate);
		
	}
	
	
	public Property getPropertyById(int id) throws ClassNotFoundException, SQLException {

		return iproperty.getPropertyById(id);
	}

	
	public Property createProperty(int id, PropertyType type, String title, int roomsNumber, int bathroomsNumber,
			
			String size, String location, boolean availability, double basePrice, List<String> ameneties)
	
			throws ClassNotFoundException, SQLException {
	
		return iproperty.createProperty(id, type, title, roomsNumber, bathroomsNumber, size, location, basePrice);
		
	}

	
	public boolean setPropertyAmeneties(int idProperty, List<String> ameneties) throws ClassNotFoundException, SQLException {
		
		return iproperty.setPropertyAmeneties(idProperty, ameneties);
		
	}

	
	public boolean setPropertyBasePrice(int idProperty, double basePrice) throws ClassNotFoundException, SQLException {
		
		return iproperty.setPropertyBasePrice(idProperty, basePrice);
	
	}

	public boolean setPropertyAvailability(int idProperty, boolean availability) throws ClassNotFoundException, SQLException {

		return iproperty.setPropertyAvailability(idProperty, availability);
	}

	
	public boolean setPropertyDetails(int idProperty, String title, String description, String rules) throws ClassNotFoundException, SQLException {

		return iproperty.setPropertyDetails(idProperty, title, description, rules);
		
	}

	
	public boolean setPropertyFeatures(int idProperty, List<String> featuresToAdd) throws ClassNotFoundException, SQLException {

		return iproperty.setPropertyFeatures(idProperty, featuresToAdd);
		
	}

//////////////////////////////////////////////////////////// GUEST SEARCH FILTERS ///////////////////////////////////////////////////////////////////	

	public List<Property> findPropertiesByfeature(String feature) throws ClassNotFoundException, SQLException {

		return iproperty.findPropertiesByfeature(feature);
		
	}

	
	public List<Property> findPropertiesByPriceRange(double lowerThreshold, double higherThreshold) throws ClassNotFoundException, SQLException {

		return iproperty.findPropertiesByPriceRange(lowerThreshold, higherThreshold);
		
	}

	
	public List<Property> findPropertiesByDateAvailability(LocalDate startDate) throws ClassNotFoundException, SQLException {

		return iproperty.findPropertiesByDateAvailability(startDate);
		
	}

	
	public List<Booking> getBookingsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException {

		return ibooking.getBookingsBetween(startDate, endDate);
	}

	
	public double getTotalEarningsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException {

		return ibooking.getTotalEarningsBetween(startDate, endDate);
	}

///////////////////////////////////////////////////////////////// Guests ////////////////////////////////////////////////////////////////////////////	

	
	public Guest getGuestById(int id) throws ClassNotFoundException, SQLException {

		return iguest.getGuestById(id);
	}

	
	public Guest createGuest(int id, String fname, String lname, String sex, int age, String phoneNum, String email,
			
			String adress) throws ClassNotFoundException, SQLException {

		return iguest.createGuest(id, fname, lname, sex, age, phoneNum, email, adress);
	}

	
	public boolean deleteGuest(int id) throws ClassNotFoundException, SQLException {

		return iguest.deleteGuest(id);
	}

	


	







}
