package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.Impl.UsersServiceImpl;
import service.UsersService;


public class LoginServlet extends HttpServlet{
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user = CookieMethods.getCookieValue(cookies, "username");
        if (!user.equals("")) return;
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        UsersService usersService = new UsersServiceImpl();
        boolean input = usersService.checkPassword(login, password);
        if (input) {
            response.addCookie(new Cookie("username", login));
            response.addCookie(new Cookie("useravatar", usersService.getUserAvatarId(login).toString()));
        }
        try (PrintWriter sw = response.getWriter()) {
            sw.print(input);
        }
    }
   
}