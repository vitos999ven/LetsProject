package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.PhotoLikesService;
import service.ServiceFactory;


public class PhotoLikeServlet extends HttpServlet{
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long photoId = Long.parseLong(request.getHeader("photoId"));
        String lastUser = request.getHeader("lastUser");
        Boolean next = Boolean.parseBoolean(request.getHeader("next"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoLikesService photoLikesService = ServiceFactory.getInstance().getPhotoLikesService();
        String json = photoLikesService.getPhotoLikesByJson(photoId, next, lastUser, 11);
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
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoLikesService photoLikesService = ServiceFactory.getInstance().getPhotoLikesService();
        photoLikesService.addPhotoLike(photoId, cookieUser);
    }
     
     
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long photoId = Long.parseLong(request.getHeader("photoId"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoLikesService photoLikesService = ServiceFactory.getInstance().getPhotoLikesService();
        photoLikesService.setDeletedPhotoLike(photoId, cookieUser);
    }
 
}
