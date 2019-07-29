package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.ISignUpFormRuleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository("SignUpFormRuleDAO")
public class SignUpFormRuleDAO  implements ISignUpFormRuleDAO {

    private IDatabaseConnection databaseConnection;

    @Autowired
    public SignUpFormRuleDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public HashMap<String, Integer> getRules() {


       HashMap<String,Integer> rulesMap= new HashMap<String, Integer>();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement getRules=null;

        try {
            connection = databaseConnection.getDBConnection();
            String getUserQuery = "select * from Form_Validation";
            getRules = connection.prepareStatement(getUserQuery);

            resultSet = getRules.executeQuery();

            while (resultSet.next()) {
                String rules = resultSet.getString("rule_name");
                Integer rulesValue = resultSet.getInt("rule_value");
                rulesMap.put(rules,rulesValue);
            }



        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {

            e.printStackTrace();
        }
        finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(getRules!=null)
                {
                    getRules.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return rulesMap;
    }
}
