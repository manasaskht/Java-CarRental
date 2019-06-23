package com.group4.carrental.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private Properties properties;
    private String userName;
    private String password;
    private String databaseUrl;
    private static DatabaseConfig databaseConfig = null;

    private DatabaseConfig(){
        loadDatabase();
        this.userName = properties.getProperty("spring.datasource.username");
        this.password = properties.getProperty("spring.datasource.password");
        this.databaseUrl = properties.getProperty("spring.datasource.url");
    }

    public static DatabaseConfig getDatabaseConfig(){
        if(databaseConfig == null){
            databaseConfig = new DatabaseConfig();
            return databaseConfig;
        }else{
            return databaseConfig;
        }
    }

    public void loadDatabase(){
        this.properties = new Properties();
        try {
            FileReader fileReader = new FileReader("src/main/resources/application.properties");
            this.properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }
}
