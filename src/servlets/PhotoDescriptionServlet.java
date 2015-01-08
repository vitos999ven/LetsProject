package servlets;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import service.PhotoDescriptionsService;
import service.ServiceFactory;


public class PhotoDescriptionServlet extends HttpServlet{
 
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long photoId = Long.parseLong(request.getHeader("photoId"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username"); 
        if (cookieUser.equals("")) return;
        PhotoDescriptionsService photoDescriptionsService = ServiceFactory.getInstance().getPhotoDescriptionsService();
        String json = photoDescriptionsService.getPhotoByJson(photoId, (long) -1, 11, cookieUser);
        try (PrintWriter sw = response.getWriter()) {
            sw.print(json);
        }
    }
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        Long photoId = Long.parseLong(request.getHeader("photoId"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        if (cookieUser.equals("")) return;
        PhotoDescriptionsService photoDescriptionsService = ServiceFactory.getInstance().getPhotoDescriptionsService();
        if (photoDescriptionsService.setUserAvatarByPhotoDescriptionId(photoId, cookieUser)){
            response.addCookie(new Cookie("useravatar", photoId.toString()));
        }
    }
     
     
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        long photoId = Long.parseLong(request.getHeader("photoId"));
        String cookieUser = CookieMethods.getCookieValue(cookies, "username");
        String cookieAvatarString = CookieMethods.getCookieValue(cookies, "useravatar");
        long cookieAvatar = (!cookieAvatarString.equals("")) ? Long.parseLong(cookieAvatarString) : (long) 0;
        if (cookieUser.equals("")) return;
        PhotoDescriptionsService photoDescriptionsService = ServiceFactory.getInstance().getPhotoDescriptionsService();
        photoDescriptionsService.setDeletedPhotoDescriptionById(photoId, cookieUser, cookieAvatar);
        if (photoId == cookieAvatar){
            response.addCookie(new Cookie("useravatar", "0"));
        }
    }
    
}
