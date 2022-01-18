package com.mizu.utils;


import com.mizu.driver.BrowserExec;
import com.mizu.driver.DriverExec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ReadProperties {

    public static ResourceBundle readProp(String systemSourcesDir){

        ResourceBundle bundle = null;
        try {
            String propertyDir = "/src/test/resources/properties/"+systemSourcesDir;
            propertyDir = propertyDir.replace("/","\\") ;
            InputStream propertiesStream = new FileInputStream(System.getProperty("user.dir") + propertyDir);
            bundle = new PropertyResourceBundle(new InputStreamReader(propertiesStream, StandardCharsets.UTF_8));
            propertiesStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bundle;
    }
}
