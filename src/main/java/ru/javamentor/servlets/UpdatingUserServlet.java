package ru.javamentor.servlets;
/*
 *
 *@Data 03.02.2020
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

@WebServlet("/admin/updateUser")
public class UpdatingUserServlet extends HttpServlet {
    private Long id;
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
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        id = Long.parseLong(idString);
        User user = (User) serviceCrudDao.find(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        req.setAttribute("usersInJDBC", users);
        req.getServletContext().getRequestDispatcher("/jsp/change.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String ageString = req.getParameter("age");
        int age = Integer.parseInt(ageString);
        String role = req.getParameter("role");
        User user = new User(id, name, password, age,role);

        serviceCrudDao.update(user);
        resp.sendRedirect(req.getContextPath() + "/admin/admin");

    }
}
