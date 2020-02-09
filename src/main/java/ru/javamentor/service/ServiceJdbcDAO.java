package ru.javamentor.service;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import ru.javamentor.DAO.UserDaoJDBC;
import ru.javamentor.model.User;
import ru.javamentor.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class ServiceJdbcDAO implements ServiceCrudDao <User> {
    private Connection connection;
    private static ServiceJdbcDAO serviceJdbcDAO;

    private ServiceJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public static ServiceJdbcDAO getInstance() {
        if (serviceJdbcDAO == null) {
            serviceJdbcDAO = new ServiceJdbcDAO(DBConnection.getInstance().getConnection());
            serviceJdbcDAO.create();
        }
        return serviceJdbcDAO;
    }

    @Override
    public User findAtPasswordAndName(String name, String password) {
        return new UserDaoJDBC(connection).findAtPasswordAndName(name,password);
    }

    private void create() {
        new UserDaoJDBC(connection).create();
    }

    @Override
    public User find(Long id) {
        return new UserDaoJDBC(connection).find(id);
    }

    @Override
    public void save(User model) {
        new UserDaoJDBC(connection).save(model);
    }

    @Override
    public void delete(Long id) {
        new UserDaoJDBC(connection).delete(id);
    }

    @Override
    public void update(User model) {
        new UserDaoJDBC(connection).update(model);
    }

    @Override
    public List<User> findAll() {
        return  new UserDaoJDBC(connection).findAll();
    }


//    public static UserServiceJDBC getUserServiceJDBC() {
//        return userServiceJDBC;
//    }
//
//    public List<User> getAllUsers() {
//        return new UserDaoJDBC(connection).findAll();
//    }
//
//    public void create() {
//        new UserDaoJDBC(connection).create();
//    }
//
//    public void addUser(User user) {
//        new UserDaoJDBC(connection).save(user);
//    }
//
//    public void removeUser(Long id) {
//        new UserDaoJDBC(connection).delete(id);
//    }
//
//    public void update(User user) {
//        new UserDaoJDBC(connection).update(user);
//    }
//
//    public User findUserAtId(Long id) {
//
//    }
}
