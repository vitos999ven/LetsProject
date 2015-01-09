package service;

import service.Impl.DialogsServiceImpl;
import service.Impl.PhotoCommentsServiceImpl;
import service.Impl.PhotoDescriptionsServiceImpl;
import service.Impl.PhotoLikesServiceImpl;
import service.Impl.UsersServiceImpl;


public class ServiceFactory {
    
    private static class FactoryHolder{
        private final static ServiceFactory instance = new ServiceFactory();
    }
    
    private static class DialogsServiceHolder{
        private final static DialogsService dialogsService = new DialogsServiceImpl();
    }
    
    private static class UsersServiceHolder{
        private final static UsersService usersService = new UsersServiceImpl();
    }
    
    private static class PhotoDescriptionsServiceHolder{
        private final static PhotoDescriptionsService photoDescriptionsService = new PhotoDescriptionsServiceImpl();
    }
    
    private static class PhotoCommentsServiceHolder{
        private final static PhotoCommentsService photoCommentsService = new PhotoCommentsServiceImpl();
    }
    
    private static class PhotoLikesServiceHolder{
        private final static PhotoLikesService photoLikesService = new PhotoLikesServiceImpl();
    }
    
    
    public static ServiceFactory getInstance(){
        return FactoryHolder.instance;
    }
    
    public DialogsService getDialogsService(){
        return DialogsServiceHolder.dialogsService;
    }
     
    public UsersService getUsersService(){
        return UsersServiceHolder.usersService;
    }
     
    public PhotoDescriptionsService getPhotoDescriptionsService(){
        return PhotoDescriptionsServiceHolder.photoDescriptionsService;
    }

    public PhotoCommentsService getPhotoCommentsService(){
        return PhotoCommentsServiceHolder.photoCommentsService;
    }
    
    public PhotoLikesService getPhotoLikesService(){
        return PhotoLikesServiceHolder.photoLikesService;
    }
    
}
