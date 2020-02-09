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
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static ru.javamentor.service.ServiceCrudDao.db;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   //     HttpSession session=null;
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        User user= (User) serviceCrudDao.findAtPasswordAndName(name,password);
        if(user==null){
            resp.sendRedirect(req.getContextPath()+"/login");
        }
        HttpSession    session = req.getSession();

        if(user.getRole().equals("user")){
            session.setAttribute("user", name);
            resp.getWriter().write("good day user "+name+" you are loginned");
        }else{
            session.setAttribute("admin", name);
            resp.getWriter().write("good day admin "+name+" you can start working");
        }


    }
}
