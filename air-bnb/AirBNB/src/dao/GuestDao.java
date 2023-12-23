package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Booking;
import entity.Guest;
import entity.Property;
import entity.PropertyType;

public class GuestDao implements IGuestDao {

	
	public Guest getGuestById(int id) throws ClassNotFoundException, SQLException {

		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("select fname, lname, sex, age, phoneNum, email, address from Guest where id = ?;");
				
				
		prepStat.setInt(1, id);
		
		ResultSet rs = prepStat.executeQuery();
		
		if (rs.next()) {
			
			String fname = rs.getString(1);
			
			String lname = rs.getString(2);
			
			String sex = rs.getString(3);
			
			int age = rs.getInt(4);
			
			String phoneNumber = rs.getString(5);
			
			String email = rs.getString(6);
		
			String adress = rs.getString(7);
			
			return new Guest(id, fname, lname, sex, age, phoneNumber, email, adress);
			
		}
		
		throw new RuntimeException("No guest is saved with the given id : " + id);
	}

	
	public Guest createGuest(int id, String fname, String lname, String sex, int age, String phoneNum, String email,
			
			String adress) throws ClassNotFoundException, SQLException {

		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("insert into Guest values(?,?,?,?,?,?,?,?,?);");
		
		prepStat.setInt(1, id);
		
		prepStat.setString(2, fname);
		
		prepStat.setString(3, lname);
		
		prepStat.setString(4, sex);
		
		prepStat.setInt(5, age);
		
		prepStat.setString(6, phoneNum);
		
		prepStat.setString(7, email);
		
		prepStat.setString(8, adress);
		
		int i = prepStat.executeUpdate();
		
		if (i != 0) {
			
			return new Guest(id, fname, lname, sex, age, phoneNum, email, adress);
		}
		
		throw new RuntimeException("Insert Failure");
	}

	
	public boolean deleteGuest(int id) throws ClassNotFoundException, SQLException {

		Connection con = Connect.getConnection();
		
		PreparedStatement prepStat = con.prepareStatement("delete from Guest where id = ?;");
		
		prepStat.setInt(1, id);
		
		int i = prepStat.executeUpdate();
		
		return i != 0;
	}

	

	

}
