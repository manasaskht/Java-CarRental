package com.group4.carrental.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ILoginDAO;
import com.group4.carrental.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
@Repository("LoginDAO")
public class LoginDAO implements ILoginDAO {

	private IDatabaseConnection databaseConnection;
	private LoggerInstance loggerInstance;

	@Autowired

    public LoginDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
			this.databaseConnection = databaseConnection;
			this.loggerInstance = loggerInstance;
		}
	public String getPassword(String email) {
		Connection dbconnect;
		String query = ("select password from User where email='" + email + "';");
		PreparedStatement st = null;
		ResultSet rs = null;
		String passwordDB = null;

		try {
			loggerInstance.log(0,"Login DAO Error: ");
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
			while (rs.next()) {
				passwordDB = rs.getString("password");

			}


		}
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Login DAO Error: "+e.toString());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				loggerInstance.log(2,"Login DAO Error: "+e.toString());
				e.printStackTrace();
			}

		}
		return  passwordDB;

	}

	@Override
	public int getUserId(User user) {
		// TODO Auto-generated method stub
		Connection dbconnect;
		String query = ("select user_id from User where email='" + user.getEmail() + "';");
		PreparedStatement st = null;
		ResultSet rs = null;
		int userId = 0;

		try {
			loggerInstance.log(0,"Login DAO Error: ");
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
			while (rs.next()) {
				userId = rs.getInt("user_id");

			}


		}
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Login DAO Error: "+e.toString());
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				loggerInstance.log(2,"Login DAO Error: "+e.toString());
				e.printStackTrace();
			}

		}
		return  userId;
	}

}
