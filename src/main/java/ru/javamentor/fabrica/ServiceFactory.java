package ru.javamentor.fabrica;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import ru.javamentor.service.ServiceCrudDao;
import ru.javamentor.service.ServiceDaoHiberbate;
import ru.javamentor.service.ServiceJdbcDAO;
import ru.javamentor.util.DBConnection;
import ru.javamentor.util.PropertyReader;

import java.util.Properties;

public class ServiceFactory {
       public ServiceCrudDao getServiceCrudDao() {
           Properties properties = PropertyReader.getProperties(DBConnection.class.getClassLoader().getResourceAsStream("db.properties"));
           String dbUrl = properties.getProperty("userDao");
           if (dbUrl.equals("Hibernate"))
               return ServiceDaoHiberbate.getInstance();
           if (dbUrl.equals("JDBC"))
               return ServiceJdbcDAO.getInstance();
           throw new IllegalArgumentException("type=" );
       }
}
