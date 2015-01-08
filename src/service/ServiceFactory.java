package service;

import service.Impl.DialogsServiceImpl;
import service.Impl.PhotoCommentsServiceImpl;
import service.Impl.PhotoDescriptionsServiceImpl;
import service.Impl.PhotoLikesServiceImpl;
import service.Impl.UsersServiceImpl;


public class ServiceFactory {
    
    private static DialogsService dialogsService = null;
    private static UsersService usersService = null;
    private static PhotoDescriptionsService photoDescriptionsService = null;
    private static PhotoCommentsService photoCommentsService = null;
    private static PhotoLikesService photoLikesService = null;
    
    private static class FactoryHolder{
        private final static ServiceFactory instance = new ServiceFactory();
    }
    
    public static synchronized ServiceFactory getInstance(){
        return FactoryHolder.instance;
    }
    
    public DialogsService getDialogsService(){
        if (dialogsService == null)
            dialogsService = new DialogsServiceImpl();
        return dialogsService;
    }
     
    public UsersService getUsersService(){
        if (usersService == null)
            usersService = new UsersServiceImpl();
        return usersService;
    }
     
    public PhotoDescriptionsService getPhotoDescriptionsService(){
        if (photoDescriptionsService == null)
            photoDescriptionsService = new PhotoDescriptionsServiceImpl();
        return photoDescriptionsService;
    }

    public PhotoCommentsService getPhotoCommentsService(){
        if (photoCommentsService == null)
            photoCommentsService = new PhotoCommentsServiceImpl();
        return photoCommentsService;
    }
    
    public PhotoLikesService getPhotoLikesService(){
        if (photoLikesService == null)
            photoLikesService = new PhotoLikesServiceImpl();
        return photoLikesService;
    }
    
}
