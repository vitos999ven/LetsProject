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
    
    private static class FactoryHolder{
        private final static Factory instance = new Factory();
    }
    
    private static class AdvertisementDAOHolder{
        private final static AdvertisementDAO advertisementDAO = new AdvertisementDAOImpl();
    }
    
    private static class AdvCommentDAOHolder{
        private final static AdvCommentDAO advCommentDAO = new AdvCommentDAOImpl();
    }
    
    private static class MessageDAOHolder{
        private final static MessageDAO messageDAO = new MessageDAOImpl();
    }
    
    private static class PhotoCommentDAOHolder{
        private final static PhotoCommentDAO photoCommentDAO = new PhotoCommentDAOImpl();
    }
    
    private static class PhotoDescriptionDAOHolder{
        private final static PhotoDescriptionDAO photoDescriptionDAO = new PhotoDescriptionDAOImpl();
    }
    
    private static class PhotoLikeDAOHolder{
        private final static PhotoLikeDAO photoLikeDAO = new PhotoLikeDAOImpl();
    }
    
    private static class ResponseDAOHolder{
        private final static ResponseDAO responseDAO = new ResponseDAOImpl();
    }
    
    private static class UserDAOHolder{
        private final static UserDAO userDAO = new UserDAOImpl();
    }
    
    public static Factory getInstance(){
        return FactoryHolder.instance;
    }
    
    
    public AdvertisementDAO getAdvertisementDAO(){
        return AdvertisementDAOHolder.advertisementDAO;
    }
     
    public AdvCommentDAO getAdvCommentDAO(){
        return AdvCommentDAOHolder.advCommentDAO;
    }
     
    public MessageDAO getMessageDAO(){
        return MessageDAOHolder.messageDAO;
    }

    public PhotoCommentDAO getPhotoCommentDAO(){
        return PhotoCommentDAOHolder.photoCommentDAO;
    }
    
    public PhotoDescriptionDAO getPhotoDescriptionDAO(){
        return PhotoDescriptionDAOHolder.photoDescriptionDAO;
    }
    
    public PhotoLikeDAO getPhotoLikeDAO(){
        return PhotoLikeDAOHolder.photoLikeDAO;
    }
    
    public ResponseDAO getResponseDAO(){
        return ResponseDAOHolder.responseDAO;
    }
    
    public UserDAO getUserDAO(){
        return UserDAOHolder.userDAO;
    }
    
}
