package hibernate.util;


import hibernate.DAO.AdvCommentDAO;
import hibernate.DAO.AdvertisementDAO;
import hibernate.DAO.Impl.AdvCommentDAOImpl;
import hibernate.DAO.Impl.AdvertisementDAOImpl;
import hibernate.DAO.Impl.MessageDAOImpl;
import hibernate.DAO.Impl.PhotoCommentDAOImpl;
import hibernate.DAO.Impl.PhotoDescriptionDAOImpl;
import hibernate.DAO.Impl.PhotoLikeDAOImpl;
import hibernate.DAO.Impl.ResponseDAOImpl;
import hibernate.DAO.Impl.UserDAOImpl;
import hibernate.DAO.MessageDAO;
import hibernate.DAO.PhotoCommentDAO;
import hibernate.DAO.PhotoDescriptionDAO;
import hibernate.DAO.PhotoLikeDAO;
import hibernate.DAO.ResponseDAO;
import hibernate.DAO.UserDAO;


public class Factory {
    private static AdvertisementDAO advertisementDAO = null;
    private static AdvCommentDAO advCommentDAO = null;
    private static MessageDAO messageDAO = null;
    private static PhotoCommentDAO photoCommentDAO = null;
    private static PhotoDescriptionDAO photoDescriptionDAO = null;
    private static PhotoLikeDAO photoLikeDAO = null;
    private static ResponseDAO responseDAO = null;
    private static UserDAO userDAO = null;
    private static Factory instance = null;
    public static String a = "dads";
    
    public static synchronized Factory getInstance(){
        if (instance == null)
            instance = new Factory();
        return instance;
    }
    
    public AdvertisementDAO getAdvertisementDAO(){
        if (advertisementDAO == null)
            advertisementDAO = new AdvertisementDAOImpl();
        return advertisementDAO;
    }
     
    public AdvCommentDAO getAdvCommentDAO(){
        if (advCommentDAO == null)
            advCommentDAO = new AdvCommentDAOImpl();
        return advCommentDAO;
    }
     
    public MessageDAO getMessageDAO(){
        if (messageDAO == null)
            messageDAO = new MessageDAOImpl();
        return messageDAO;
    }

    public PhotoCommentDAO getPhotoCommentDAO(){
        if (photoCommentDAO == null)
            photoCommentDAO = new PhotoCommentDAOImpl();
        return photoCommentDAO;
    }
    
    public PhotoDescriptionDAO getPhotoDescriptionDAO(){
        if (photoDescriptionDAO == null)
            photoDescriptionDAO = new PhotoDescriptionDAOImpl();
        return photoDescriptionDAO;
    }
    
    public PhotoLikeDAO getPhotoLikeDAO(){
        if (photoLikeDAO == null)
            photoLikeDAO = new PhotoLikeDAOImpl();
        return photoLikeDAO;
    }
    
    public ResponseDAO getResponseDAO(){
        if (responseDAO == null)
            responseDAO = new ResponseDAOImpl();
        return responseDAO;
    }
    
    public UserDAO getUserDAO(){
        if (userDAO == null)
            userDAO = new UserDAOImpl();
        return userDAO;
    }
    
}
