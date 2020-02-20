package ru.javamentor.filters;
/*
 *
 *@Data 08.02.2020
 *@autor Fedorov Yuri
 *@project UserAdmin
 *
 */

import ru.javamentor.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthFiltrAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

//       если сессия не была, или у сессии отсутствует атрибут user, перенаправляем пользователя на страницу с логином
        if (session == null || session.getAttribute("user") == null)
            servletRequest.getServletContext().getRequestDispatcher("/login").forward(req, resp);
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals("admin"))
            filterChain.doFilter(req, resp);
        else
            servletRequest.getServletContext().getRequestDispatcher("/login").forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
