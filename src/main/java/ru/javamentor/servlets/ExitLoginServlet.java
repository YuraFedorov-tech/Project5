package ru.javamentor.servlets;
/*
 *
 *@Data 09.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/unlogin")
public class ExitLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        session.removeAttribute("user");
        session.removeAttribute("admin");
        resp.getWriter().write("you are logged out");
    }
}
