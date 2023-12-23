package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Property;
import entity.PropertyType;
import dao.BookingDao;

public class PropertyDao implements IPropertyDao {
	
	
	
	public Property getPropertyById(int id) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select title, type, size, roomsNumber, bathroomsNumber, location,"
				
				+ " basePrice where id = ?;");
				
				
		prepStat.setInt(1, id);
		
		ResultSet rs = prepStat.executeQuery();
		
		if (rs.next()) {
			
			String title = rs.getString(1);
			
			String type = rs.getString(2);
			
			String size = rs.getString(3);
			
			int roomsNumber = rs.getInt(4);
			
			int bathroomsNumber = rs.getInt(5);
			
			String location = rs.getString(6);
		
			double price = rs.getDouble(7);
			
			return new Property(id, PropertyType.valueOf(type),title, roomsNumber, bathroomsNumber, size, location, price);
			
		}
		
		throw new RuntimeException("No property is saved with the given id : " + id);
	}
	
	public String listToString(List<String> amenetiesToAdd) {
	
		String ameneties = "";
		
		for (String amenety : amenetiesToAdd) {                   // a small method to collect all the amenities in one string and add it in one line
			
			ameneties += amenety + ",";
			
		}
		
		return ameneties;
	}
	
	public Property createProperty(int id, PropertyType type, String title, int roomsNumber, int bathroomsNumber,
			
			String size, String location, double basePrice) 
					
					throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("insert into Property values(?,?,?,?,?,?,?,?,?);");
		
		prepStat.setInt(1, id);
		
		prepStat.setString(2, String.valueOf(type));
		
		prepStat.setString(3, title);
		
		prepStat.setInt(4, roomsNumber);
		
		prepStat.setInt(5, bathroomsNumber);
		
		prepStat.setString(6, size);
		
		prepStat.setString(7, location);
		
		prepStat.setDouble(8, basePrice);
		
		int i = prepStat.executeUpdate();
		
		if (i != 0) {
			
			return new Property(id, type, title, roomsNumber, bathroomsNumber, size, location, basePrice);
		}
		
		throw new RuntimeException("Insert Failure");
		
		
	}
	
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
	
	public boolean setPropertyAmeneties(int idProperty, List<String> amenetiesToAdd) throws ClassNotFoundException, SQLException {
		
		String ameneties = listToString(amenetiesToAdd);

		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("update table Property set ameneties = ? where id = ?;");
		
		prepStat.setString(1, ameneties);
		
		prepStat.setInt(2, idProperty);
		
		int i = prepStat.executeUpdate();
		
		return i != 0;
		
		
	}


	
	public boolean setPropertyBasePrice(int idProperty, double basePrice) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("update table Property set basePrice = ? where id = ?;");
		
		prepStat.setDouble(1, basePrice);
		
		prepStat.setInt(2, idProperty);
		
		int i = prepStat.executeUpdate();
		
		
		return i != 0;
		
		
	}

	
	public boolean setPropertyAvailability(int idProperty,boolean availability) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("update table Property set availability = ? where id = ?;");
		
		prepStat.setBoolean(1, availability);
		
		prepStat.setInt(2, idProperty);
		
		
		int i = prepStat.executeUpdate();
		
		
		return i != 0;
	}

	
	public boolean setPropertyDetails(int idProperty, String title, String description, String rules) throws ClassNotFoundException, SQLException {
		
		Connection con = Connect.getConnection();
		PreparedStatement prepStat = con.prepareStatement("update table Property set title = ?,"
				
				+ " description = ?, rules = ? where id = ?;");
		
		prepStat.setString(1, title);
		
		prepStat.setString(2, description);
		
		prepStat.setString(3, rules);
		
		prepStat.setInt(4, idProperty);
		
		int i = prepStat.executeUpdate();
		
		
		return i != 0;
	}

	
	public boolean setPropertyFeatures(int idProperty, List<String> featuresToAdd) throws ClassNotFoundException, SQLException {

		String features = listToString(featuresToAdd);

		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("update table Property set features = ? where id = ?;");
		
		prepStat.setString(1, features);
		
		prepStat.setInt(2, idProperty);
		
		int i = prepStat.executeUpdate();
		
		return i != 0;
	}

	public static ResultSet searchQuery(String query) throws ClassNotFoundException, SQLException {   // Just assembling a redundant code
		
		Connection con = Connect.getConnection();
		
		Statement statement = con.createStatement();
		
		ResultSet rs = statement.executeQuery(query);
		
		return rs;
	}
	
	public List<Property> findPropertiesByfeature(String feature) throws ClassNotFoundException, SQLException {
		
		List<Property> properties = new ArrayList<Property>();
		
		ResultSet rs = searchQuery("select id, features from Property;");
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			List<String> features = Arrays.asList(rs.getString(2).split(",")); //Supposing the features are represented as strings separated by comas
			
			if (features.contains(feature)) {
				
				properties.add(this.getPropertyById(id));
				
			}
		}
		
		return properties;
		
	}
	
	

	
	public List<Property> findPropertiesByPriceRange(double lowerThreshold, double higherThreshold) throws ClassNotFoundException, SQLException {

		List<Property> properties = new ArrayList<Property>();
		
		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select id, basePrice from Property where basePrice between ? and ?;");
		
		prepStat.setDouble(1, lowerThreshold);
		
		prepStat.setDouble(2, higherThreshold);
	
		ResultSet rs = prepStat.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			
			properties.add(this.getPropertyById(id));
				
		
		}
		
		return properties;
	}

	
	public List<Property> findPropertiesByDateAvailability(LocalDate startDate)throws ClassNotFoundException, SQLException {
		
		List<Property> properties = new ArrayList<Property>();
		
		Connection con = Connect.getConnection();
		
		Statement statement = con.createStatement();
		
		String query = "select id from Property;";
		
		// our createBooking method sets the availability of the concerned property to false, so we just select the available ones directly
	
		ResultSet rs = statement.executeQuery(query);
		
		while (rs.next()) {
			
			int id = rs.getInt(1);
			
			if (this.checkAvailability(id, startDate)) {
				
				properties.add(this.getPropertyById(id));
				
			}
		
		}
		
		return properties;
		
	
		
	}

	

	
	





	

	
	
	
	
}
