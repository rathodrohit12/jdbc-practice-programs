package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyChecker {
    private static Properties envProperty = new Properties();  
    private static Properties configProperty = new Properties(); 

    static {

        try {                  // read env value 
            FileInputStream read_env = new FileInputStream(System.getProperty("user.dir")
                    + ".\\src\\resources\\env.properties");

            envProperty.load(read_env);  // call load methos help with property object 	
            String env = envProperty.getProperty("env");

            FileInputStream read_config = new FileInputStream(System.getProperty("user.dir") + ".\\src\\main\\java\\com\\school\\properties\\" + env + "-config.properties");
            configProperty.load(read_config);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getDataProperty(String key) {
        return configProperty.getProperty(key);

    }
    
    
}
