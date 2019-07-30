package com.group4.carrental.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	@Autowired
	public AdminResponseDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection, LoggerInstance loggerInstance) {
		this.databaseConnection = databaseConnection;
		this.loggerInstance = loggerInstance;
	}
	public void carApproval (int id)
	{
		Connection dbconnect;
		String query = ("Update Car set car_status_id=2 where car_id='"+ id + "';");
		PreparedStatement st = null;
		

		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			st.execute();
			
		}
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Admin DAO Approval requests Error: "+e.toString());
			e.printStackTrace();
		} finally {
			try {

				st.close();
			} catch (SQLException e) {
				loggerInstance.log(2,"Admin DAO Approval requests Error: "+e.toString());
				e.printStackTrace();
			}

		}
			
	}
	public void carReject (int id)
	{
		Connection dbconnect;
		String query = ("Update Car set car_status_id=5 where car_id='"+ id + "';");
		PreparedStatement st = null;
		

		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
		st.execute();
			
		}
		catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			loggerInstance.log(2,"Admin DAO reject requests Error: "+e.toString());
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				loggerInstance.log(2,"Admin DAO reject requests Error: "+e.toString());
				e.printStackTrace();
			}

		}
			
	}
	public ArrayList<AdminCar> getAllPendingRequests(int status)
	{
		Connection dbconnect;
		String query = ("select car_id,car_model,car_description,car_rate,(select name from User where user_id = owner_id) as owner_name,(select email from User where user_id = owner_id) as owner_email from Car where car_status_id='"+ status + "';");
		ArrayList<AdminCar> carList = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			dbconnect = databaseConnection.getDBConnection();
			st = dbconnect.prepareStatement(query);
			rs = st.executeQuery(query);
				
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
    				rs.close();
    				st.close();
    			} catch (SQLException e) {
					loggerInstance.log(2,"Admin DAO pending requests Error: "+e.toString());
    				e.printStackTrace();
    			}

    		}
		return carList;
	}
	public String getEmail(int carId) {
		String query = "select (select email from User where user_id = owner_id) from Car where car_id =?";
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String email="";
        try{
            connection = databaseConnection.getDBConnection();
            st = connection.prepareStatement(query);
            st.setInt(1,carId);
            rs = st.executeQuery();

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
                if(st != null) {
                    st.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
				loggerInstance.log(2,"Admin DAO email response Error: "+e.toString());
                e.printStackTrace();
            }
        }
		loggerInstance.log(0,"Admin DAO email success");
        return email;
    
	}}
    			
	

		
		
