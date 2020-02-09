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

public class DBConnection {
    private static DBConnection dbConnection;
    private static SessionFactory sessionFactory;

    private DBConnection() {
    }

    public static DBConnection getInstance(){
        if(dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "123");
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

    public  Connection getConnection() {
        Connection connection;
        try {
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
            String dbPassword = "123";
            String dbUserName = "postgres";
            String driverClassName = "org.postgresql.Driver";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException();
        }
    }
}
