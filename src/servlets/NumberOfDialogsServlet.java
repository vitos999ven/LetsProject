package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.DialogsService;
import service.Impl.DialogsServiceImpl;


public class NumberOfDialogsServlet extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user;
        user = CookieMethods.getCookieValue(cookies, "username");
        if (user.equals("")) return;
        DialogsService dialogsService = new DialogsServiceImpl();
        try (PrintWriter sw = response.getWriter()) {
            sw.print(dialogsService.getNumberOfUnreadDialogs(user));
        }
    }
   
}
