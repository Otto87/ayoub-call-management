package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Booking;
import entity.Guest;
import entity.Pricing;
import entity.Property;

public class BookingDao implements IBookingDao {
	
	
	public boolean checkAvailability(int idProperty, LocalDate startDate) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select title, max(endDate) from Booking where id = ? group by title;");
		
		prepStat.setInt(1, idProperty);
		
		ResultSet rs= prepStat.executeQuery();
		
		if (rs.next()) {
			
			LocalDate endDate = rs.getDate(2).toLocalDate();
			
			return startDate.isAfter(endDate);
			
		}
		
		return true;
		
		
	}
	
	public Booking getBookingByRef(String ref) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select idGuest, idProperty, startDate, endDate, guestsNumber, price "
				
				+ "from Booking where ref = ?;");
		
		prepStat.setString(1, ref);
		
		ResultSet rs = prepStat.executeQuery();
		
		if (rs.next()) {
			
			int idGuest = rs.getInt(1);
			
			int idProperty = rs.getInt(2);
			
			LocalDate startDate = rs.getDate(3).toLocalDate();
			
			LocalDate endDate = rs.getDate(4).toLocalDate();
			
			int guestsNumber = rs.getInt(5);
			
			double price = rs.getDouble(6);
			
			return new Booking(ref, idGuest, idProperty, startDate, endDate, guestsNumber, price);
	
		}
		
		throw new RuntimeException("No booking is corresponding to the given reference " + ref);
	}
	
	public Booking createBooking(int idProperty, int idGuest, LocalDate startDate, LocalDate endDate, int guestsNumber, double basePrice) 
	
			throws ClassNotFoundException, SQLException {
		
		if (this.checkAvailability(idProperty, startDate)) {
			
			Connection con = Connect.getConnection();
			PreparedStatement prepStat = con.prepareStatement("insert into Booking values(?,?,?,?,?,?,?");
			
			String ref = BookingRefGenerator.generateRef(6);
			
			prepStat.setString(1, ref);
			
			prepStat.setInt(2, idGuest);
			
			prepStat.setInt(3, idProperty);
			
			prepStat.setDate(4, Date.valueOf(startDate));
			
			prepStat.setDate(5, Date.valueOf(endDate));
			
			prepStat.setInt(6, guestsNumber);
			
			double bookingPrice = Pricing.bookingPriceCalculator(basePrice, startDate, endDate);
			
			prepStat.setDouble(7, bookingPrice);
			
			prepStat.executeUpdate();
			
			return new Booking(ref, idGuest, idProperty, startDate, endDate, guestsNumber, bookingPrice);
			
		}
		
		throw new RuntimeException("This property is not available for booking");
		
	}

	
	public boolean deleteBooking(String ref) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("delete from Booking where ref = ?;");
		
		prepStat.setString(1, ref);
		
		int i = prepStat.executeUpdate();
		
		return i != 0;
	}
	
	public List<Booking> getBookingsByGuest(int idGuest) throws ClassNotFoundException, SQLException {
		
		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select ref from Booking where idGuest = ?;");
		
		prepStat.setInt(1, idGuest);
		
		ResultSet rs = prepStat.executeQuery();
		
		while (rs.next()) {
			
			String ref = rs.getString(1);
			
			Booking booking = this.getBookingByRef(ref);
			
			bookings.add(booking);
			
		}
		
		return bookings;
		

	}

	
	public double getTotalBookingsAmountByGuest(int idGuest) throws ClassNotFoundException, SQLException {

		double total = 0;
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select ref from Booking where idGuest = ?;");
		
		prepStat.setInt(1, idGuest);
		
		ResultSet rs = prepStat.executeQuery();
		
		while (rs.next()) {
			
			String ref = rs.getString(1);
			
			Booking booking = this.getBookingByRef(ref);
			
			total += booking.getPrice();
		}
		
		return total;
	}

	
	public List<Booking> getBookingsByProperty(int idProperty) throws ClassNotFoundException, SQLException {

		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select ref from Booking where idProperty = ?;");
		
		prepStat.setInt(1, idProperty);
		
		ResultSet rs = prepStat.executeQuery();
		
		while (rs.next()) {
			
			String ref = rs.getString(1);
			
			Booking booking = this.getBookingByRef(ref);
			
			bookings.add(booking);
			
		}
		
		return bookings;
	}

	
	public double getTotalBookingsAmountByProperty(int idProperty) throws ClassNotFoundException, SQLException {
		
		double total = 0;
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select ref from Booking where idProperty = ?;");
		
		prepStat.setInt(1, idProperty);
		
		ResultSet rs = prepStat.executeQuery();
		
		while (rs.next()) {
			
			String ref = rs.getString(1);
			
			Booking booking = this.getBookingByRef(ref);
			
			total += booking.getPrice();
		}
		
		return total;
	}

	
	public List<Booking> getBookingsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException {

		List<Booking> bookings = new ArrayList<Booking>();
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select ref from Booking where startDate >= ? and endDate <= ?;");
		
		prepStat.setDate(1, Date.valueOf(startDate));
		
		prepStat.setDate(2, Date.valueOf(endDate));
		
		ResultSet rs = prepStat.executeQuery();
		
		while (rs.next()) {
			
			String ref = rs.getString(1);
			
			Booking booking = this.getBookingByRef(ref);
			
			bookings.add(booking);
			
		}
		
		return bookings;
	}

	public double getEarningsFromBookings(List<Booking> bookings) {
		
		double total = 0;
		
		for (Booking booking : bookings) {
			
			total += booking.getPrice();
		}
		
		return total;
	}
	
	public double getTotalEarningsBetween(LocalDate startDate, LocalDate endDate) throws ClassNotFoundException, SQLException {
		
		List<Booking> bookings = this.getBookingsBetween(startDate, endDate);
		
		return this.getEarningsFromBookings(bookings);
		
	} 
	






	

	
}
