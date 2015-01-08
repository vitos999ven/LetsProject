package servlets;


import hibernate.logic.SexEnum;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.ServiceFactory;
import service.UsersService;


public class UsersServlet extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException { 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String cookieUser, json;
        cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        UsersService usersService = ServiceFactory.getInstance().getUsersService();
        if (request.getHeader("user") != null){
            String login = request.getHeader("user");
            long descId = Long.parseLong(request.getHeader("descId"));
            json = usersService.getUserByJson(login, descId, 21, cookieUser);
        } else {
            String search = request.getParameter("search").replace("!:.", "&");
            String city = request.getParameter("city").replace("!:.", "&");
            SexEnum sex = SexEnum.valueOf(request.getHeader("sex"));
            int ageFrom = Integer.parseInt(request.getHeader("ageFrom"));
            int ageTo = Integer.parseInt(request.getHeader("ageTo"));
            String lastLogin = request.getHeader("lastLogin");
            json = usersService.getUsersByJson(search, city, sex, ageFrom, ageTo, lastLogin, 21, cookieUser);
            }
        try (PrintWriter sw = response.getWriter()) {
            sw.print(json);
        }
    }
    
}
