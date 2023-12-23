package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Booking;
import entity.Guest;

public interface IGuestDao {
	
	Guest getGuestById(int id) throws ClassNotFoundException, SQLException;
	
	Guest createGuest(int id, String fname, String lname, String sex, int age, String phoneNum, String email, String adress) 
			
			throws ClassNotFoundException, SQLException;
	
	boolean deleteGuest(int id) throws ClassNotFoundException, SQLException;
	
		
	
}
