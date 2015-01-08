package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.DialogsService;
import service.Impl.DialogsServiceImpl;


public class DialogsServlet extends HttpServlet{
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        long lastId = Long.parseLong(request.getHeader("lastId"));
        
        Cookie[] cookies = request.getCookies();
        String user = CookieMethods.getCookieValue(cookies, "username");
        if (user == null) return;
        DialogsService dialogsService = new DialogsServiceImpl();
        try (PrintWriter sw = response.getWriter()) {
            String json = dialogsService.getDialogsByJson(user, lastId, 21);
            sw.print(json);
        }
    }
     
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        String other = request.getHeader("other");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user;
        user = CookieMethods.getCookieValue(cookies, "username");
        if (user.equals("")) return;
        DialogsService dialogsService = new DialogsServiceImpl();
        if (other.equals(user)){
            try (PrintWriter sw = response.getWriter()) {
                sw.print(dialogsService.getLastDialogsMessageId(user));
            }
        }else{
            dialogsService.setDeletedDialog(user, other);
        } 
    }
   
}
