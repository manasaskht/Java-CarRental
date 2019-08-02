package com.group4.carrental.dao.implementation;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.connection.implementation.DatabaseConnection;
import com.group4.carrental.dao.IForgotPasswordDAO;



@Repository("ForgotPasswordDAO")
public class ForgotPasswordDAO implements IForgotPasswordDAO{
 private LoggerInstance loggerInstance;
 private IDatabaseConnection databaseConnection;
 private final String VALIDATE_EMAIL= "{call validateEmail(?)}";
 private final String ADD_Token="{call addToken(?,?)}";
 private final String GET_TOKEN="{call getToken(?)}";
 private final String UPDATE_PASSWORD="{call updatePassword(?,?)}";
 private final String VALIDATE_TOKEN= "{call validateToken(?)}";

	 @Autowired
	    public ForgotPasswordDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance){
	        this.databaseConnection = databaseConnection;
		 this.loggerInstance = loggerInstance;
	    }

	
	public String findUserByEmail(String email)
	{
		Connection dbconnect = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		String emailDB=null;
		
		try {
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(VALIDATE_EMAIL);
			callableStatement.setString(1,email);
			rs =callableStatement.executeQuery();
			while (rs.next()) {
				 emailDB = rs.getString("email");
				 loggerInstance.log(0,"ForgotPassword DAO Success: ");
			}
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(callableStatement != null) {
                    callableStatement.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
				loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
                e.printStackTrace();
            }
        

		}
		return emailDB;
		
		
	}

	public void addToken(String Email,String Token) {
		Connection dbconnect = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try {
			loggerInstance.log(0,"ForgotPassword DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(ADD_Token);
			callableStatement.setString(1, Token);
			callableStatement.setString(2,Email);
			callableStatement.execute();
	
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(callableStatement != null) {
                    callableStatement.close();
                }
                if(rs != null) {
                    rs.close();
                }
                
            } catch (SQLException e) {
				loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
                e.printStackTrace();
            }
        

		
		
	}

	}
	
	public String findUserByResetToken(String Email)
	{
		
		Connection dbconnect = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		String TokenDB=null;
		
		try {
			loggerInstance.log(0,"ForgotPassword DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(GET_TOKEN);
			callableStatement.setString(1,Email);
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				TokenDB = rs.getString("Token_ID");
			}
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(callableStatement != null) {
                    callableStatement.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
				loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
                e.printStackTrace();
            }
        

		}
		return TokenDB;
		
		
	
}

	@Override
	public String validate(String Token) {
		Connection dbconnect = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		String TokenDB=null;
		
		try {
			loggerInstance.log(0,"ForgotPassword DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(VALIDATE_TOKEN);
			callableStatement.setString(1,Token);
			rs = callableStatement.executeQuery();
			while (rs.next()) {
				TokenDB = rs.getString("Token_ID");
			}
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(callableStatement != null) {
                    callableStatement.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
				loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
                e.printStackTrace();
            }
        

		}
		return TokenDB;
		
	}

	public void updatePassword(String email, String password) {
		
		Connection dbconnect = null;
		CallableStatement callableStatement = null;
		try {
			loggerInstance.log(0,"ForgotPassword DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(UPDATE_PASSWORD);
			callableStatement.setString(1,email);
			callableStatement.setString(2, password);
			callableStatement.execute();
	
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(callableStatement != null) {
                    callableStatement.close();
                }
                
            } catch (SQLException e) {
				loggerInstance.log(2,"ForgotPassword DAO Error: "+e.toString());
                e.printStackTrace();
            }
        

		
		
	}
	}
}
