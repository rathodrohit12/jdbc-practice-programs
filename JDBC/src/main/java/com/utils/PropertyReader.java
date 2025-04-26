package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Properties envProperty = new Properties();
    private static final Properties configProperty = new Properties();

    static {

        try {
            FileInputStream read_env = new FileInputStream(System.getProperty("user.dir")
                    + ".\\src\\resources\\env.properties");
            envProperty.load(read_env);
            String env = envProperty.getProperty("env");

            FileInputStream read_config = new FileInputStream(System.getProperty("user.dir")
                    + ".\\src\\resources\\" + env + "-config.properties");
            configProperty.load(read_config);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getDataProperty(String key) {
        return configProperty.getProperty(key);

    }
    
    

}
