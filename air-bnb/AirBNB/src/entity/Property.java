package entity;

import java.util.ArrayList;
import java.util.List;

public class Property {
	   
	private int id;
	private PropertyType type;
	private int roomsNumber;
	private int bathroomsNumber;
	private String size;
	private String location;
	private double basePrice;
	private List<String> ameneties = new ArrayList<String>();
	private List<String> photos = new ArrayList<String>();
	private List<String> features = new ArrayList<String>();   // things that differ from a property type to another
	private String title;
	private String description;
	private String rules;
	
	// Constructor 
	
	public Property(int id, PropertyType type, String title, int roomsNumber,int bathroomsNumber, String size, String location, double basePrice) { 
		
		this.id = id;
		this.type = type;
		this.title = title;
		this.roomsNumber = roomsNumber;
		this.bathroomsNumber = bathroomsNumber;
		this.size = size;
		this.location = location;                                      
		this.basePrice = basePrice;
		
	}
	
	
	// Getters - Setters
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public PropertyType getType() {
		return type;
	}


	public void setType(PropertyType type) {
		this.type = type;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public double getPrice() {
		return basePrice;
	}

	public void setPrice(double price) {
		this.basePrice = price;
	}

	public List<String> getAmeneties() {
		return ameneties;
	}

	public void setAmeneties(List<String> ameneties) {
		this.ameneties = ameneties;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}


	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public int getBathroomsNumber() {
		return bathroomsNumber;
	}

	public void setBathroomsNumber(int bathroomsNumber) {
		this.bathroomsNumber = bathroomsNumber;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
	public boolean equalsProperty(Property p) {
		
		return this.id == p.id;
	}


	public List<String> getFeatures() {
		return features;
	}


	public void setFeatures(List<String> features) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		
		return "Id: " + this.id + ", type: " + String.valueOf(this.type) + ", title: " + this.title + ", location: " + this.location 
				
				+ ", price: " + this.basePrice;
	}
	
	



	
	
	
	

	
	
	
	

	
	
	
	
	
}
