package ru.javamentor.util;
/*
 *
 *@Data 18.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperties(InputStream propFile) {
        Properties properties = new Properties();
        try {
            properties.load(propFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
