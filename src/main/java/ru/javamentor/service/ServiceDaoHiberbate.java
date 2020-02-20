package ru.javamentor.service;
/*
 *
 *@Data 02.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javamentor.DAO.UserDaoHiberbate;
import ru.javamentor.model.User;
import ru.javamentor.util.DBConnection;

import java.util.List;


public class ServiceDaoHiberbate implements ServiceCrudDao<User> {
    private static ServiceDaoHiberbate serviceDaoHiberbate;
    private static UserDaoHiberbate userDaoHiberbate;
    private static SessionFactory sessionFactory;

    private ServiceDaoHiberbate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    public static ServiceDaoHiberbate getInstance() {
        if (serviceDaoHiberbate == null) {
            DBConnection dbConnection = DBConnection.getInstance();
            serviceDaoHiberbate = new ServiceDaoHiberbate(dbConnection.getSessionFactory());
            userDaoHiberbate= UserDaoHiberbate.getInstance( sessionFactory);
        }
        return serviceDaoHiberbate;
    }

    @Override
    public User findAtPasswordAndName(String name, String password) {
        User user = userDaoHiberbate.findAtPasswordAndName(name, password);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> cars = userDaoHiberbate.findAll();
        return cars;
    }
    @Override
    public void save(User user) {
        userDaoHiberbate.save(user);
    }

    @Override
    public void delete(Long id) {
        userDaoHiberbate.delete(id);
    }

    @Override
    public User find(Long id) {
        return userDaoHiberbate.find(id);
    }

    @Override
    public void update(User user) {
        userDaoHiberbate.update(user);
    }
}
