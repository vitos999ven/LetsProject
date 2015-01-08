package service.Impl;


import hibernate.logic.PhotoComment;
import hibernate.logic.PhotoDescription;
import hibernate.logic.PhotoLike;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.List;
import service.JsonObject;
import service.PhotoDescriptionsService;



public class PhotoDescriptionsServiceImpl implements PhotoDescriptionsService{

    private JsonObject json;
    
    @Override
    public String getPhotoByJson(long photoId, long lastCommentId, int count, String cookieUser) {
        json = new JsonObject();
        try{
            List<PhotoComment> comments = Factory.getInstance().getPhotoCommentDAO().getPhotoCommentsBeforeId(photoId, lastCommentId, count, false);
            boolean NoMorePhotos = true;
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionById(photoId);
            if (comments.size() == count){
                NoMorePhotos = false;
                comments.remove(comments.get(count - 1));
            }
            json.put("length", comments.size());
            json.put("photoId", photoId);
            json.put("user", photo.getUser());
            json.put("noMore", NoMorePhotos);
            if (lastCommentId == -1){
                User user = Factory.getInstance().getUserDAO().getUserByLogin(photo.getUser());
                PhotoLike likeByUser = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(photoId, cookieUser);
                int likeNumber = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(photoId, false).size();
                int commentNumber = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(photoId, false).size();
                json.put("userAvatar", user.getAvatar());
                json.put("likeNumber", likeNumber);
                json.put("commentNumber", commentNumber);
                json.put("description", photo.getDescription());
                json.put("likedByUser", (likeByUser != null));
            }
            for (int i = 0; i < comments.size(); i++){
                PhotoComment m = comments.get(i);
                JsonObject commentJson = new JsonObject();
                User userFrom = Factory.getInstance().getUserDAO().getUserByLogin(m.getUserFrom());
                commentJson.put("id", m.getId());
                commentJson.put("date", m.getDate());
                commentJson.put("photoId", m.getPhotoId());
                commentJson.put("userFrom", m.getUserFrom());
                commentJson.put("avatarFrom", userFrom.getAvatar());
                commentJson.put("value", m.getValue());
                json.put(i, commentJson.toJsonString());
            }
        }catch(NullPointerException | SQLException e){}
        return json.toJsonString();
    }

    
    @Override
    public boolean setUserAvatarByPhotoDescriptionId(long photoId, String cookieUser) {
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionById(photoId);
            if ((photo != null) && (photo.getUser().equals(cookieUser))){
                Factory.getInstance().getUserDAO().setUserAvatarByPhotoDescriptionId(cookieUser, photoId);
                return true;
            }
        }catch(SQLException e){}
        return false;
    }

    
    @Override
    public void setDeletedPhotoDescriptionById(long photoId, String cookieUser, long cookieAvatar) {
        try{
            Factory.getInstance().getPhotoDescriptionDAO().setDeletedPhotoDescriptionById(photoId);
            if (photoId == cookieAvatar){
                User m = Factory.getInstance().getUserDAO().getUserByLogin(cookieUser);
                m.setAvatar(new Long(0));
                Factory.getInstance().getUserDAO().updateUser(m);
            }
        }catch(SQLException e){}
    }

}
