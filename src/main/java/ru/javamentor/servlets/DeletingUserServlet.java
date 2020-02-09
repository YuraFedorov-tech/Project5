package ru.javamentor.servlets;
/*
 *
 *@Data 02.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */


import ru.javamentor.fabrica.ServiceFactory;
import ru.javamentor.model.User;
import ru.javamentor.service.ServiceCrudDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ru.javamentor.service.ServiceCrudDao.db;

@WebServlet("/admin/deleteUser")
public class DeletingUserServlet extends HttpServlet {
    private ServiceCrudDao serviceCrudDao;

    @Override
    public void init() throws ServletException {
        ServiceFactory serviceFactory = new ServiceFactory();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        serviceCrudDao = serviceFactory.getServiceCrudDao(db, properties);
        // serviceCrudDao = serviceFactory.getServiceCrudDao("JDBC", properties);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] items = req.getParameterValues("Delete");
//assuming Order is your order class and orderList is your item list
        List<User> users= serviceCrudDao.findAll();
        if (users == null)
            users = new ArrayList<>();
        for (User user : users) {
            for (String str : items) {
                if (str.equals(user.getId().toString())) {
                    serviceCrudDao.delete(user.getId());
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/admin/admin");
    }
}
