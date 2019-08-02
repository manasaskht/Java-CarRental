package com.group4.carrental.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.group4.carrental.service.implementation.LoggerInstance;
import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IAdminResponseDAO;
import com.group4.carrental.model.AdminCar;

@Repository("AdminResponseDAO")
public class AdminResponseDAO implements IAdminResponseDAO{
	

	private IDatabaseConnection databaseConnection;
	private LoggerInstance loggerInstance;
	private final String CAR_APPROVAL = "{call carApproval(?)}";
	private final String CAR_REJECT = "{call carReject(?)}";
	private final String PENDING_REQUESTS="{call getAllPendingRequests(?)}";
	private final String GET_OWNER_EMAIL = "{call getOwnerEmail(?)}";
	@Autowired
	public AdminResponseDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
		this.databaseConnection = databaseConnection;
		this.loggerInstance = loggerInstance;
	}
	public void carApproval (int id)
	{
		Connection dbconnect=null;
		CallableStatement callableStatement = null;
		try {
			loggerInstance.log(0,"AdminResponse DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(CAR_APPROVAL);
			 callableStatement.setInt(1,id);
			 callableStatement.execute();
		}
			 
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Admin DAO Approval requests Error: "+e.toString());
			e.printStackTrace();
		} finally {
			try {
				 databaseConnection.closeDBConnection(dbconnect);
	                if(callableStatement != null) {
	                    callableStatement.close();
	                }
	                 }catch (SQLException e) {
				loggerInstance.log(2,"Admin DAO Approval requests Error: "+e.toString());
				e.printStackTrace();
			}

		}
			
	}
	public void carReject (int id)
	{
		Connection dbconnect=null;
		CallableStatement callableStatement = null;
		try {
			loggerInstance.log(0,"AdminResponse DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(CAR_REJECT);
			 callableStatement.setInt(1,id);
			callableStatement.execute();
		}
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Admin DAO reject requests Error: "+e.toString());
			e.printStackTrace();
		} finally {
			try {
				 databaseConnection.closeDBConnection(dbconnect);
	                if(callableStatement != null) {
	                    callableStatement.close();
	                }
	               } catch (SQLException e) {
				loggerInstance.log(2,"Admin DAO reject requests Error: "+e.toString());
				e.printStackTrace();
			}

		}
			
	}
	public ArrayList<AdminCar> getAllPendingRequests(int status)
	{
		Connection dbconnect=null;
		CallableStatement callableStatement = null;
		ArrayList<AdminCar> carList = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			loggerInstance.log(0,"AdminResponse DAO Success: ");
			dbconnect = databaseConnection.getDBConnection();
			callableStatement = dbconnect.prepareCall(PENDING_REQUESTS);
			callableStatement.setInt(1,status);
			 rs = callableStatement.executeQuery();
				
		if (rs != null) {
            while (rs.next()) {
            	AdminCar car = new AdminCar();
                int id = rs.getInt("car_id");
                car.setCarId(id);
                String ownerName = rs.getString("owner_name");
                car.setCarOwnerName(ownerName);
                String description = rs.getString("car_description");
                car.setCarDescription(description);
                double carRate = rs.getDouble("car_rate");
                car.setCarRate(carRate);
                String model = rs.getString("car_model");
                car.setCarModel(model);
                String ownerEmail = rs.getString("owner_email");
                car.setCarOwnerMail(ownerEmail);
                carList.add(car);
            	
            }
		}
	}
            catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				loggerInstance.log(2,"Admin DAO pending requests Error: "+e.toString());
    			e.printStackTrace();
    		} finally {
    			try {
   				 databaseConnection.closeDBConnection(dbconnect);
   	                if(callableStatement != null) {
   	                    callableStatement.close();
   	                }
   	                if(rs != null) {
   	                    rs.close();
   	                }}catch (SQLException e) {
					loggerInstance.log(2,"Admin DAO pending requests Error: "+e.toString());
    				e.printStackTrace();
    			}

    		}
		return carList;
	}
	public String getEmail(int carId) {
		Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        String email="";
        try{
        	loggerInstance.log(0,"AdminResponse DAO Email Success: ");
            connection = databaseConnection.getDBConnection();
            callableStatement =connection.prepareCall(GET_OWNER_EMAIL);
            callableStatement.setInt(1,carId);
            rs = callableStatement.executeQuery();

            if(rs != null){
                while (rs.next()){
                    email = rs.getString(1);
                }
            }

        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Admin DAO email response error: "+e.toString());
            e.printStackTrace();
        }finally {
        	try {
				 databaseConnection.closeDBConnection(connection);
	                if(callableStatement != null) {
	                    callableStatement.close();
	                }
	                if(rs != null) {
	                    rs.close();
	                }
            } catch (SQLException e) {
				loggerInstance.log(2,"Admin DAO email response Error: "+e.toString());
                e.printStackTrace();
            }
        }
        return email;
    
	}}
    			
	

		
		
