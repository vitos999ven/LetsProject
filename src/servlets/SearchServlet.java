package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.Impl.UsersServiceImpl;
import service.UsersService;


public class SearchServlet extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException { 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String cookieUser;
        String search = request.getParameter("search").replace("!:.", "&");
        String login = request.getHeader("Login"); 
        boolean next = Boolean.parseBoolean(request.getHeader("next"));
        cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        UsersService userService = new UsersServiceImpl();
        try (PrintWriter sw = response.getWriter()) {
            String json = userService.getUsersByJson(search, login, next, 11);
            sw.print(json);
        }
    }
    
}