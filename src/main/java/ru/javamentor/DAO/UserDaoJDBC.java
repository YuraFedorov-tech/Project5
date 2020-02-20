package ru.javamentor.DAO;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import ru.javamentor.model.User;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements CrudDAO<User> {
    Connection connection;
    //language=SQL
    private final String ADD_MODEL = "INSERT INTO myUsers ( name, password, age,role) values (?,?,?,?)";
    //language=SQL
    private final String SELECT_ALL = "SELECT * FROM myUsers";
    //language=SQL
    private final String DELETE_MODEL = "DELETE FROM myUsers WHERE id=?";
    //language=SQL
    private final String UPDATE_MODEL = "UPDATE myUsers SET name=?, password=?, age=?, role=? WHERE id=?";
    //language=SQL
    private final String SELECT_AT_NAME_AND_PASSWORD =
            "SELECT *FROM myUsers WHERE name=? and password=?";

    //SELECT * FROM Customers
    //WHERE Country='Germany' AND City='Berlin';
    public UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findAtPasswordAndName(String name, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AT_NAME_AND_PASSWORD);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int age = resultSet.getInt("age");
            String role = resultSet.getString("role");
            Long id=resultSet.getLong("id");
            User user = new User(id, name, password, age, role);
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User find(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM myUsers WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String role = resultSet.getString("role");
            User user = new User(id, name, password, age, role);
            return user;
        } catch (SQLException e) {
            throw new IllegalArgumentException("id=" + id);
        }

    }

    @Override
    public void save(User model) {




        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MODEL);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setString(4, model.getRole());
            preparedStatement.execute();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_MODEL);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(User model) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MODEL);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setString(4, model.getRole());
            preparedStatement.setLong(5, model.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String role = resultSet.getString("role");
                users.add(new User(id, name, password, age, role));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return users;
    }

    public void create() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS myUsers(id  SERIAL PRIMARY KEY ,name VARCHAR(30),password VARCHAR(30),age int,role  VARCHAR(30));");
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }
}
