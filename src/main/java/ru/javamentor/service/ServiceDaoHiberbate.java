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
    private SessionFactory sessionFactory;


    private ServiceDaoHiberbate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static ServiceDaoHiberbate getInstance() {
        if (serviceDaoHiberbate == null) {
            DBConnection dbConnection=DBConnection.getInstance();
            serviceDaoHiberbate = new ServiceDaoHiberbate(dbConnection.getSessionFactory());
        }
        return serviceDaoHiberbate;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> cars = new UserDaoHiberbate(session).findAll();
        session.close();
        return cars;
    }

    @Override
    public User findAtPasswordAndName(String name, String password) {
        Session  session =sessionFactory.openSession();
        User user = new UserDaoHiberbate(session).findAtPasswordAndName(name, password);
        session.close();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        new UserDaoHiberbate(session).save(user);
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        new UserDaoHiberbate(session).delete(id);
        session.close();
    }
    @Override
    public User find(Long id) {
        Session  session =sessionFactory.openSession();
        User user = new UserDaoHiberbate(session).find(id);
        session.close();
        return user;
    }
    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        new UserDaoHiberbate(session).update(user);
        session.close();

    }
}
