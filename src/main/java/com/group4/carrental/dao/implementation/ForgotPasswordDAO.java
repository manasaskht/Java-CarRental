package com.group4.carrental.dao.implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.connection.implementation.DatabaseConnection;
import com.group4.carrental.dao.IForgotPasswordDAO;



@Repository("ForgotPasswordDAO")
public class ForgotPasswordDAO implements IForgotPasswordDAO{
	
	 private IDatabaseConnection databaseConnection;
	  @Autowired
	    public ForgotPasswordDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection){
	        this.databaseConnection = databaseConnection;
	    }




	
	public String findUserByEmail(String email)
	{
		Connection dbconnect = null;
		String query = ("select email from User where email='" + email + "';");
		PreparedStatement st = null;
		ResultSet rs = null;
		String emailDB=null;
		
		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
			while (rs.next()) {
				 emailDB = rs.getString("email");
			}
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(st != null) {
                    st.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        

		}
		return emailDB;
		
		
	}

	public void addToken(String Email,String Token) {
		Connection dbconnect = null;
		String query = ("update User SET Token_ID =? where email='" + Email + "';");
		PreparedStatement st = null;
		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			 st.setString(1, Token);
			 st.execute();
	
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(st != null) {
                    st.close();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        

		
		
	}

	}
	
	public String findUserByResetToken(String Email)
	{
		
		Connection dbconnect = null;
		String query = ("select Token_ID from User where email='" + Email + "';");
		PreparedStatement st = null;
		ResultSet rs = null;
		String TokenDB=null;
		
		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
			while (rs.next()) {
				TokenDB = rs.getString("Token_ID");
			}
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(st != null) {
                    st.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        

		}
		return TokenDB;
		
		
	
}

	@Override
	public String validate(String Token) {
		Connection dbconnect = null;
		String query = ("select Token_ID from User where Token_ID='" + Token + "';");
		PreparedStatement st = null;
		ResultSet rs = null;
		String TokenDB=null;
		
		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
			while (rs.next()) {
				TokenDB = rs.getString("Token_ID");
			}
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(st != null) {
                    st.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        

		}
		return TokenDB;
		
	}

	public void updatePassword(String email, String password) {
		
		Connection dbconnect = null;
		String query = ("update User SET password =? where email='" + email + "';");
		PreparedStatement st = null;
		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			 st.setString(1, password);
			 st.execute();
	
		}catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseConnection.closeDBConnection(dbconnect);
                if(st != null) {
                    st.close();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        

		
		
	}
	}
}
