package ru.javamentor.util;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.javamentor.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private static SessionFactory sessionFactory;
    private static Properties properties;

    private DBConnection() {
    }

    public static DBConnection getInstance(){
        if(dbConnection==null){
            dbConnection=new DBConnection();
            properties = PropertyReader.getProperties(DBConnection.class.getClassLoader().getResourceAsStream("db.properties"));
        }
        return dbConnection;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.connection.url", properties.getProperty("dbUrl"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("dbPassword"));
        configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("driverClassName"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("dbUserName"));
        configuration.setProperty("hibernate.dialect", properties.getProperty("dialect"));
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }
//        @SuppressWarnings("UnusedDeclaration")
//        private static Configuration getMySqlConfiguration() {
//            Configuration configuration = new Configuration();
//            configuration.addAnnotatedClass(User.class);
//
//            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//            // configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
//            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC");
//            configuration.setProperty("hibernate.connection.username", "root");
//            configuration.setProperty("hibernate.connection.password", "123");
//            configuration.setProperty("hibernate.show_sql", "true");
//            configuration.setProperty("hibernate.hbm2ddl.auto", "create");
//            return configuration;
//        }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getConnection() {
        Connection connection;
        try {
            String dbUrl=properties.getProperty("dbUrl");
            String dbPassword = properties.getProperty("dbPassword");
            String dbUserName = properties.getProperty("dbUserName");
            String driverClassName = properties.getProperty("driverClassName");
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }
    }
}
