package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.DialogsService;
import service.ServiceFactory;


public class DialogServlet extends HttpServlet{
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        String other = request.getHeader("other");
        response.addHeader("other", other);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user = CookieMethods.getCookieValue(cookies, "username");
        if (user.equals("")) return;
        long lastMessageId = (request.getHeader("lastId") != null) ? Long.parseLong(request.getHeader("lastId")) : -1;
        boolean getOldMessages = (request.getHeader("getOldMessages") != null) ? Boolean.parseBoolean(request.getHeader("getOldMessages")) : false;
        DialogsService dialogsService = ServiceFactory.getInstance().getDialogsService();
        String json = dialogsService.getDialogMessagesByJson(other, lastMessageId, getOldMessages, user);
        try (PrintWriter sw = response.getWriter()) {
            sw.print(json);
        }
    }
     
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        String other = request.getHeader("other");     
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user;
        user = CookieMethods.getCookieValue(cookies, "username");
        if (user.equals("")) return;
        DialogsService dialogsService = ServiceFactory.getInstance().getDialogsService();
        if (request.getHeader("addMessage").equals("true")){
            String value = request.getParameter("value").replace("!:.", "&");
            dialogsService.addMessage(user, other, value);
        } else {
            try (PrintWriter sw = response.getWriter()) {
                String json = dialogsService.getLastMessageIdByJson(user, other);
                sw.print(json);
            }
        }
    }
   
}
