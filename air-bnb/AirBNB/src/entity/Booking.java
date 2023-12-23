package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking {
	
	private String ref;            // booking reference
	private int idGuest;
	private int idProperty;
	private LocalDate startDate;
	private LocalDate endDate;
	private int guestsNumber;
	private double price;
	
	// Constructor
	
	public Booking(String ref, int idGuest, int idProperty, LocalDate startDate, LocalDate endDate, int guestsNumber, double price) {
		
		this.ref = ref;
		this.startDate = startDate;
		this.endDate = endDate;
		this.idGuest = idGuest;
		this.idProperty = idProperty;
		this.guestsNumber = guestsNumber;
		this.price = price;
	}
	
	// Getters - Setters
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public int getGuestsNumber() {
		return guestsNumber;
	}

	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(int idGuest) {
		this.idGuest = idGuest;
	}

	public int getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(int idProperty) {
		this.idProperty = idProperty;
	}
	
	@Override
	public String toString() {
		
		return "Booking reference: " + this.ref + ", guest id: " + this.idGuest + ", property id: " + this.idProperty + ", booking price: " 
				
				+ this.price + ", starts at: " + String.valueOf(startDate) + ", and ends at: " + String.valueOf(endDate);
	}
	
	
	
	
	
	
	
	
	
	
	
}
