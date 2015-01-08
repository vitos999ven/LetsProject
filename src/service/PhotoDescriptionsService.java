package service;



public interface PhotoDescriptionsService {
    public String getPhotoByJson(long photoId, long lastCommentId, int count, String cookieUser);
    public boolean setUserAvatarByPhotoDescriptionId(long photoId, String cookieUser);
    public void setDeletedPhotoDescriptionById(long photoId, String cookieUser, long cookieAvatar);
}
