package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.Booking;
import entity.Guest;
import entity.Property;

public interface IBookingDao {
	
	
	boolean checkAvailability(int idProperty, LocalDate startDate) throws ClassNotFoundException, SQLException;
	
	Booking getBookingByRef(String ref) throws ClassNotFoundException, SQLException;
	
	Booking createBooking(int idProperty, int idGuest, LocalDate startDate, LocalDate endDate, int guestsNumber, double basePrice) 
	
			throws ClassNotFoundException, SQLException;
	
	boolean deleteBooking(String ref) throws ClassNotFoundException, SQLException;
	
	public List<Booking> getBookingsByGuest(int idGuest) throws ClassNotFoundException, SQLException;
	
	double getTotalBookingsAmountByGuest(int idGuest) throws ClassNotFoundException, SQLException;
	
	public List<Booking> getBookingsByProperty(int idProperty) throws ClassNotFoundException, SQLException;
	
	double getTotalBookingsAmountByProperty(int idProperty) throws ClassNotFoundException, SQLException;
	
	public List<Booking> getBookingsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException;
	
	double getTotalEarningsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException;
}
