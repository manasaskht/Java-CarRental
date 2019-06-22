package com.group4.carrental.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ILoginDAO;

@Repository("LoginDAO")
public class LoginDAO implements ILoginDAO {
	
	@Autowired
	private IDatabaseConnection databaseConnection;

	public String getPassword(String email) {
		Connection dbconnect;
		String query = ("select password from User where email='" + email + "';");
		PreparedStatement st = null;
		ResultSet rs = null;
		String passwordDB = null;

		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
			while (rs.next()) {
				 passwordDB = rs.getString("password");
				
			}

			
		} 
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return  passwordDB;	
		
	}

}
