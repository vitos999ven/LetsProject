package servlets;


import hibernate.logic.PhotoDescription;
import hibernate.util.Factory;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class LoadPhotoServlet extends HttpServlet{
 
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{ 
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
	}  
        response.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String user;
        user = CookieMethods.getCookieValue(cookies, "username");
        if (user.equals("")) return;
        try{
            String description = null;
            FileItem fileItem = null;
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(3*1024*1024);
            File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(tempDir);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 10);
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
		if (item.isFormField()) {			    	
                    if (item.getFieldName().equals("description"))  {
                        description = item.getString().replace("!:.", "&");
                    }
                } else {
                    fileItem = item;
                }
            }
         			
            BufferedImage lowImage = ImageIO.read(fileItem.getInputStream());
            lowImage = getResizedImage(lowImage);
            GregorianCalendar c = new GregorianCalendar();
            PhotoDescription desc = new PhotoDescription( null, c.getTimeInMillis(),"Loader","&user:" + user + "&" + description);
            
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(desc);
            List <PhotoDescription> descs = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("Loader", false);
           
            for (PhotoDescription i : descs){
                if ((!i.getDescription().equals("&user:" + user + "&" + description)) && (i.getDate() != c.getTimeInMillis()))
                    continue;
                
                long photoId = i.getId();
                ImageIO.write(lowImage, "jpg", new File(getServletContext().getRealPath("/images/"+photoId+"low.jpg")));
                File uploadedFile = new File(getServletContext().getRealPath("/images/"+ photoId +".jpg"));
                uploadedFile.createNewFile();
                fileItem.write(uploadedFile);
            
                i.setUser(user);
                i.setDescription(description);
                TimeUnit.SECONDS.sleep(5);
                Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(i);
                break;
            }
        }catch(SQLException e){
        }catch(Exception e){}
    }
    
    
    public BufferedImage getResizedImage(BufferedImage image){
        int height = image.getHeight();
        int width = image.getWidth();
        double ScaleFactor = 0.9;
        BufferedImage newImage = image;
        while(height > 300 || width > 300){
            height = (int)(height*ScaleFactor);
            width = (int)(width*ScaleFactor);
            newImage = resize(newImage, width, height);
        }
        return newImage;
    }
    
    
    public BufferedImage resize(BufferedImage image, int width, int height){
        BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D;
        graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.fillRect(0, 0, width, height);
        graphics2D.drawImage(image, 0, 0,width, height, null);//.getScaledInstance(width, height, Image.SCALE_SMOOTH)
	
        if(graphics2D != null) {
            graphics2D.dispose();
        }       
	return bufferedImage;
    }
 
}

