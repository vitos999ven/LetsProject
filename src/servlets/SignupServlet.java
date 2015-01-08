package servlets;


import hibernate.logic.SexEnum;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.*;
import javax.servlet.http.*;
import service.Impl.UsersServiceImpl;
import service.UsersService;


public class SignupServlet extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException { 
        response.setCharacterEncoding("UTF-8");
        UsersService usersService = new UsersServiceImpl();
        try (PrintWriter sw = response.getWriter()) {
            sw.print(usersService.checkLoginUnique(request.getHeader("username")));
        }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException { 
        response.setCharacterEncoding("UTF-8");
        String login = request.getHeader("username");
        String password = request.getHeader("password");
        String city = request.getParameter("city").replace("!:.", "&");
        byte sex = SexEnum.valueOf(request.getHeader("sex")).getValue();
        int day = Integer.parseInt(request.getHeader("day"));
        int month = Integer.parseInt(request.getHeader("month"));
        int year = Integer.parseInt(request.getHeader("year"));
        Calendar birthday = new GregorianCalendar(year, month, day);
        String about = request.getParameter("about").replace("!:.", "&");
        UsersService usersService = new UsersServiceImpl();
        boolean success = usersService.addNewUser(login, password, sex, birthday, city, about);
        if (success){
            response.addCookie(new Cookie("username", login));
            response.addCookie(new Cookie("useravatar", "0"));
        }
        try (PrintWriter sw = response.getWriter()) {
            sw.print(success);
        }
    }
   
}