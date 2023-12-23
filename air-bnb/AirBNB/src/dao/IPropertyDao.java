package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.Property;
import entity.PropertyType;

public interface IPropertyDao {
	
	Property getPropertyById(int id) throws ClassNotFoundException, SQLException;
	
	Property createProperty(int id, PropertyType type, String title, int roomsNumber,int bathroomsNumber, String size, String location,
			
			 double basePrice) throws ClassNotFoundException, SQLException;
	
	// I had no choice but to redifine this method here, to avoid big dependencies in the service layer :
	
	boolean checkAvailability(int idProperty, LocalDate startDate) throws ClassNotFoundException, SQLException;
		
	boolean setPropertyAmeneties(int idProperty, List<String> ameneties) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyBasePrice(int idProperty, double basePrice) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyAvailability(int idProperty, boolean availability) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyDetails(int idProperty, String title, String description, String rules) throws ClassNotFoundException, SQLException;
	
	boolean setPropertyFeatures(int idProperty, List<String> featuresToAdd) throws ClassNotFoundException, SQLException;
	
	List<Property> findPropertiesByfeature(String feature) throws ClassNotFoundException, SQLException;
	
	List<Property> findPropertiesByPriceRange(double lowerThreshold, double higherThreshold) throws ClassNotFoundException, SQLException;
	
	List<Property> findPropertiesByDateAvailability(LocalDate startDate) throws ClassNotFoundException, SQLException;


}
