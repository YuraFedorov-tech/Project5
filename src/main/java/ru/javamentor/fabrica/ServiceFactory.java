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

import java.util.Properties;

public class ServiceFactory {
       public ServiceCrudDao getServiceCrudDao(String type, Properties properties) {

        String dbUrl = properties.getProperty("db.JDBC");
        String dbUr2 = properties.getProperty("db.Hibernate");
        System.out.println(dbUr2 + " 11 " + dbUrl);
        if (type.equals(dbUr2))
            return ServiceDaoHiberbate.getInstance();
        if (type.equals(dbUrl))
            return ServiceJdbcDAO.getInstance();
        throw new IllegalArgumentException("type=" + type);
    }
}
