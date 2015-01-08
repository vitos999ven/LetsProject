package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.DialogsService;
import service.ServiceFactory;


public class ChangeUnreadMessageServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        String other = request.getHeader("other");     
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user = CookieMethods.getCookieValue(cookies, "username");
        if (user.equals("")) return;
        DialogsService dialogsService = ServiceFactory.getInstance().getDialogsService();
        dialogsService.changeAllUserToUserMessagesUnread(user, other);
    }
   
}