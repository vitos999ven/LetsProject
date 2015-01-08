package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.Impl.PhotoCommentsServiceImpl;
import service.Impl.PhotoDescriptionsServiceImpl;
import service.PhotoCommentsService;
import service.PhotoDescriptionsService;


public class PhotoCommentServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long photoId = Long.parseLong(request.getHeader("photoId"));
        long lastCommentId = Long.parseLong(request.getHeader("lastCommentId"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoDescriptionsService photoDescriptionsService = new PhotoDescriptionsServiceImpl();
        String json = photoDescriptionsService.getPhotoByJson(photoId, lastCommentId, 11, cookieUser);
        try (PrintWriter sw = response.getWriter()) {
            sw.print(json);
        }
    }
 
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long photoId = Long.parseLong(request.getHeader("photoId"));
        String value = request.getParameter("value").replace("!:.", "&");
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoCommentsService photoCommentsService = new PhotoCommentsServiceImpl();
        String json = photoCommentsService.addPhotoComment(photoId, cookieUser, value);
        try (PrintWriter sw = response.getWriter()) {
            sw.print(json);
        }
    }
     
    
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long commentId = Long.parseLong(request.getHeader("commentId"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoCommentsService photoCommentsService = new PhotoCommentsServiceImpl();
        photoCommentsService.setDeletedPhotoComment(commentId, cookieUser);
    }
    
}