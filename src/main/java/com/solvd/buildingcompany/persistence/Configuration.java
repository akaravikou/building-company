package com.solvd.buildingcompany.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public static final String DRIVER;
    public static final String URL;
    public static final String PASSWORD;
    public static final String USERNAME;

    static {
        USERNAME = getValue("username");
        DRIVER = getValue("driver");
        URL = getValue("url");
        PASSWORD = getValue("password");
    }

    public static String getValue(String field){
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(field);
    }
}
