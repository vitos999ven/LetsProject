package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.PhotoDescription;
import hibernate.logic.PhotoLike;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhotoLikeDAOtests {
    
    @Test
    public void test01_addPhotoLike(){
        List<PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", (long) 1));
            Factory.getInstance().getUserDAO().addUser(new User("testUser2", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1992, 0, 15), "asdz22", "xbab3a", (long) 1));
           
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 333333, "testUser1", "sdfdsfv"));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 333333, "testUser2", "sdfdsfvcc"));
           
            List <PhotoDescription> testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            List <PhotoDescription> testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true);
           
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null, (long) 333333,testUser1Desc.get(0).getId(),"testUser2"));
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null, (long) 333333,testUser1Desc.get(0).getId(),"testUser2"));//failed
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null, (long) 333333,testUser1Desc.get(0).getId(),"testUser1"));
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null, (long) 333333,testUser2Desc.get(0).getId(),"testUser1"));
           
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            assertTrue(photoLikesBefore.size() + 3 == photoLikesAfter.size()); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test02_getAllPhotoLikes(){
        try{
            List <PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
         
            assertTrue(!photoLikes.isEmpty()); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test03_getAllPhotoLikesByUser(){
        try{
            PhotoDescription testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
         
            List <PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
         
            PhotoLike Like1 = new PhotoLike(photoLikes.get(0).getId(),(long) 333333,testUser1Desc.getId(),"testUser1");
            PhotoLike Like2 =  new PhotoLike(photoLikes.get(1).getId(),(long) 333333,testUser2Desc.getId(),"testUser1");
         
            assertTrue((photoLikes.size() == 2) && (Like1.equals(photoLikes.get(0))) && (Like2.equals(photoLikes.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test04_getAllPhotoLikesByUserWithDeletedDescription(){
        try{
            PhotoDescription testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            testUser1Desc.setDeleted(true);
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(testUser1Desc);
            List <PhotoLike> photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), false);
         
            PhotoLike LikeBefore1 = new PhotoLike(photoLikesBefore.get(0).getId(),(long) 333333,testUser2Desc.getId(),"testUser1");
         
            testUser1Desc.setDeleted(false);
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(testUser1Desc);
         
            List <PhotoLike> photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), false);
         
            PhotoLike LikeAfter1 = new PhotoLike(photoLikesAfter.get(0).getId(),(long) 333333,testUser1Desc.getId(),"testUser1");
            PhotoLike LikeAfter2 =  new PhotoLike(photoLikesAfter.get(1).getId(),(long) 333333,testUser2Desc.getId(),"testUser1");
         
            assertTrue((photoLikesBefore.size() == 1) && (LikeBefore1.equals(photoLikesBefore.get(0))) && (photoLikesAfter.size() == 2) && (LikeAfter1.equals(photoLikesAfter.get(0))) && (LikeAfter2.equals(photoLikesAfter.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test05_getAllPhotoLikesByUserLogin(){
        try{
            PhotoDescription testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
         
            List <PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            
            PhotoLike Like1 = new PhotoLike(photoLikes.get(0).getId(),(long) 333333,testUser1Desc.getId(),"testUser1");
            PhotoLike Like2 =  new PhotoLike(photoLikes.get(1).getId(),(long) 333333,testUser2Desc.getId(),"testUser1");
         
            assertTrue((photoLikes.size() == 2) && (Like1.equals(photoLikes.get(0))) && (Like2.equals(photoLikes.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test06_getAllPhotoLikesByUserLoginWithDeletedDescription(){
        try{
            PhotoDescription testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            testUser1Desc.setDeleted(true);
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(testUser1Desc);
            List <PhotoLike> photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", false);
         
         
            PhotoLike LikeBefore1 = new PhotoLike(photoLikesBefore.get(0).getId(),(long) 333333,testUser2Desc.getId(),"testUser1");
         
            testUser1Desc.setDeleted(false);
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(testUser1Desc);
         
            List <PhotoLike> photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", false);
         
            PhotoLike LikeAfter1 = new PhotoLike(photoLikesAfter.get(0).getId(),(long) 333333,testUser1Desc.getId(),"testUser1");
            PhotoLike LikeAfter2 =  new PhotoLike(photoLikesAfter.get(1).getId(),(long) 333333,testUser2Desc.getId(),"testUser1");
         
            assertTrue((photoLikesBefore.size() == 1) && (LikeBefore1.equals(photoLikesBefore.get(0))) && (photoLikesAfter.size() == 2) && (LikeAfter1.equals(photoLikesAfter.get(0))) && (LikeAfter2.equals(photoLikesAfter.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test07_getAllPhotoLikesByPhoto(){
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            List <PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(photo.getId(), true);
            PhotoLike Like1 = new PhotoLike(photoLikes.get(0).getId(),(long) 333333, photo.getId(),"testUser2");
            PhotoLike Like2 =  new PhotoLike(photoLikes.get(1).getId(),(long) 333333, photo.getId(),"testUser1");
         
            assertTrue((photoLikes.size() == 2) && (Like1.equals(photoLikes.get(0)))  && (Like2.equals(photoLikes.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test08_getAllPhotoLikesByDeletedPhoto(){
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            photo.setDeleted(true);
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(photo);
            List <PhotoLike> photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(photo.getId(), false);
         
            photo.setDeleted(false);
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(photo);
         
            List <PhotoLike> photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(photo.getId(), false);
            PhotoLike Like1 = new PhotoLike(photoLikesAfter.get(0).getId(),(long) 333333, photo.getId(),"testUser2");
            PhotoLike Like2 =  new PhotoLike(photoLikesAfter.get(1).getId(),(long) 333333, photo.getId(),"testUser1");
         
            assertTrue((photoLikesBefore.isEmpty()) && (photoLikesAfter.size() == 2) && (Like1.equals(photoLikesAfter.get(0)))  && (Like2.equals(photoLikesAfter.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }

    @Test
    public void test09_getPhotoLikeById(){
        try{
            PhotoLike photoLike = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true).get(0);
            PhotoLike photoLikeById = Factory.getInstance().getPhotoLikeDAO().getPhotoLikeById(photoLike.getId());
            assertTrue(photoLikeById.equals(photoLike)); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test10_getPhotoLikeByPhotoAndUser(){
        try{
            PhotoLike photoLike = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true).get(0);
            PhotoLike photoLikeById = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId(), Factory.getInstance().getUserDAO().getUserByLogin("testUser2"));
            
            assertTrue(photoLikeById.equals(photoLike)); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test11_getPhotoLikeByPhotoAndUserLogin(){
        try{
            PhotoLike photoLike = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true).get(0);
            PhotoLike photoLikeById = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId(), "testUser2");
            assertTrue(photoLikeById.equals(photoLike)); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test12_updatePhotoLike(){
        try{
            PhotoLike photoLikeBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true).get(0);
            
            photoLikeBefore.setDate((long) 1);
            Factory.getInstance().getPhotoLikeDAO().updatePhotoLike(photoLikeBefore);
            
            PhotoLike photoLikeAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true).get(0);
            
            photoLikeBefore.setDate((long) 333333);
            Factory.getInstance().getPhotoLikeDAO().updatePhotoLike(photoLikeBefore);
            
            assertTrue(photoLikeAfter.getDate() == (long) 1); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test13_getAllPhotoLikesLikesAfterLogin(){
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            List <PhotoLike> photoLikes1 = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikesAfterLogin(photo.getId(), "", 10, true);
            List <PhotoLike> photoLikes2 = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikesAfterLogin(photo.getId(), "testUser1", 10, true);
            
            PhotoLike Like1 =  new PhotoLike(photoLikes1.get(0).getId(),(long) 333333, photo.getId(),"testUser1");
            PhotoLike Like2 = new PhotoLike(photoLikes1.get(1).getId(),(long) 333333, photo.getId(),"testUser2");
            
            assertTrue((photoLikes1.size() == 2) && (Like1.equals(photoLikes1.get(0)))  && (Like2.equals(photoLikes1.get(1))) && (photoLikes2.size() == 1) && (Like2.equals(photoLikes2.get(0)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test14_getAllPhotoLikesBeforeLogin(){
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            List <PhotoLike> photoLikes1 = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikesBeforeLogin(photo.getId(), "", 10, true);
            List <PhotoLike> photoLikes2 = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikesBeforeLogin(photo.getId(), "testUser2", 10, true);
            
            PhotoLike Like1 =  new PhotoLike(photoLikes2.get(0).getId(),(long) 333333, photo.getId(),"testUser1");
            
            assertTrue((photoLikes1.isEmpty()) && (photoLikes2.size() == 1) && (Like1.equals(photoLikes2.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }catch(NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test15_setDeletedPhotoLikeById(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter, AllPhotoLikesBefore, AllPhotoLikesAfter;
        try{
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,testUser2Desc.getId(),"testUser1"));
             
            PhotoLike likeDelete = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(testUser2Desc.getId(), "testUser1");
         
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", false);
            AllPhotoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            Factory.getInstance().getPhotoLikeDAO().setDeletedPhotoLikeById(likeDelete.getId());
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", false);
            AllPhotoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            assertTrue((photoLikesBefore.size()== photoLikesAfter.size()+1) && (AllPhotoLikesBefore.size()== AllPhotoLikesAfter.size())); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test16_setDeletedPhotoLikeByPhotoAndUser(){
        try{
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,testUser2Desc.getId(),"testUser1"));
             
            PhotoLike likeDelete = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(testUser2Desc.getId(), "testUser1");
            likeDelete.setDeleted(false);
            Factory.getInstance().getPhotoLikeDAO().updatePhotoLike(likeDelete);
            Factory.getInstance().getPhotoLikeDAO().setDeletedPhotoLike(testUser2Desc.getId(), "testUser1");
            likeDelete = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(testUser2Desc.getId(), "testUser1");
            assertTrue(likeDelete.getDeleted()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test17setDeletedAllPhotoLikesByUser(){
        try{
            List<PhotoLike> likes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            for (PhotoLike like : likes){
                like.setDeleted(false);
                Factory.getInstance().getPhotoLikeDAO().updatePhotoLike(like);
            }
            Factory.getInstance().getPhotoLikeDAO().setDeletedAllPhotoLikes(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            likes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", false);
              
            assertTrue(likes.isEmpty()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test18_setDeletedAllPhotoLikesByUserLogin(){
        try{
            List<PhotoLike> likes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            for (PhotoLike like : likes){
                like.setDeleted(false);
                Factory.getInstance().getPhotoLikeDAO().updatePhotoLike(like);
            }
            Factory.getInstance().getPhotoLikeDAO().setDeletedAllPhotoLikes("testUser1");
            likes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", false);
                
            assertTrue(likes.isEmpty()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test19_setDeletedAllPhotoLikesByPhoto(){
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            List<PhotoLike> likes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(photo.getId(), true);
            for (PhotoLike like : likes){
                like.setDeleted(false);
                Factory.getInstance().getPhotoLikeDAO().updatePhotoLike(like);
            }
            Factory.getInstance().getPhotoLikeDAO().setDeletedAllPhotoLikes(photo.getId());
            likes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(photo.getId(), false);
            
            assertTrue(likes.isEmpty()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test20_deletePhotoLikeById(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,testUser2Desc.getId(),"testUser1"));
             
            PhotoLike likeDelete = Factory.getInstance().getPhotoLikeDAO().getPhotoLike(testUser2Desc.getId(), "testUser1");
         
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            Factory.getInstance().getPhotoLikeDAO().deletePhotoLikeById(likeDelete.getId());
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            assertTrue(photoLikesBefore.size()== photoLikesAfter.size()+1); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test21_deletePhotoLikeByPhotoAndUser(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,testUser2Desc.getId(),"testUser1"));
   
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            Factory.getInstance().getPhotoLikeDAO().deletePhotoLike(testUser2Desc.getId(),Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            
            assertTrue(photoLikesBefore.size()== photoLikesAfter.size()+1); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test22_deletePhotoLikeByPhotoAndUserLogin(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            PhotoDescription testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,testUser2Desc.getId(),"testUser1"));
   
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
            Factory.getInstance().getPhotoLikeDAO().deletePhotoLike(testUser2Desc.getId(),"testUser1");
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes();
           
            assertTrue(photoLikesBefore.size()== photoLikesAfter.size()+1); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test23_deleteAllPhotoLikesByUser(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            Factory.getInstance().getPhotoLikeDAO().deleteAllPhotoLikes(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser1", true);
            
            assertTrue(!photoLikesBefore.isEmpty() && photoLikesAfter.isEmpty()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test24_deleteAllPhotoLikesByUserLogin(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true);
            Factory.getInstance().getPhotoLikeDAO().deleteAllPhotoLikes(Factory.getInstance().getUserDAO().getUserByLogin("testUser2"));
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("testUser2", true);
            
            assertTrue(!photoLikesBefore.isEmpty() && photoLikesAfter.isEmpty()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test25_deleteAllPhotoLikesByUserLogin(){
        List <PhotoLike> photoLikesBefore, photoLikesAfter;
        try{
            PhotoDescription testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null, (long) 333333,testUser1Desc.getId(),"testUser2"));
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null, (long) 333333,testUser1Desc.getId(),"testUser1"));
            
            photoLikesBefore = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(testUser1Desc.getId(), true);
            Factory.getInstance().getPhotoLikeDAO().deleteAllPhotoLikes(testUser1Desc.getId());
            photoLikesAfter = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(testUser1Desc.getId(), true);
            
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser2");
           
            assertTrue(!photoLikesBefore.isEmpty() && photoLikesAfter.isEmpty()); 
        }catch(SQLException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
}