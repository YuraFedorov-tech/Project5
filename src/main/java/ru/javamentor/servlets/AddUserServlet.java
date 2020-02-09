package ru.javamentor.servlets;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
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
import java.io.PrintWriter;
import java.util.Properties;

import static ru.javamentor.service.ServiceCrudDao.db;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
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
        req.getServletContext().getRequestDispatcher("/jsp/addUser.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String ageStr=req.getParameter("age");
        String role=req.getParameter("role");
        int age=Integer.parseInt(ageStr);
        User user=new User(name,password,age,role);
       try{
           serviceCrudDao.save(user);
       }catch(Throwable throwable){
           throw new IllegalArgumentException();
       }
        resp.getWriter().write("adding is succsessfull");
    }
}
