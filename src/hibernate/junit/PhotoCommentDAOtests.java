package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.PhotoComment;
import hibernate.logic.PhotoDescription;
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
public class PhotoCommentDAOtests {
    
    @Test
    public void test01_addPhotoComment(){
        List<PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser2");
           
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments();
           
            Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", (long) 1));
            Factory.getInstance().getUserDAO().addUser(new User("testUser2", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1992, 0, 15), "asdz22", "xbab3a", (long) 1));
            
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 333333, "testUser1", "sdfdsfv"));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 333333, "testUser2", "sdfdsfvcc"));
           
            List <PhotoDescription> testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            List <PhotoDescription> testUser2Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true);
           
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,testUser1Desc.get(0).getId(), (long) 132424, "testUser2", "sdaasdasd"));
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,testUser1Desc.get(0).getId(), (long) 232425, "testUser2", "sdaasdasd"));
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,testUser1Desc.get(0).getId(), (long) 332426, "testUser1", "sdaasdasd"));
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,testUser2Desc.get(0).getId(), (long) 432427, "testUser1", "sdaasdasd"));
           
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments();
            assertTrue(photoCommentsBefore.size() + 4 == photoCommentsAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test02_getAllPhotoComments(){
        try{
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments();
            
            assertTrue(!photoComments.isEmpty()); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test03_getAllPhotoCommentsByUser(){
        try{
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            PhotoComment Comment1 =  new PhotoComment(photoComments.get(0).getId(),Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0).getId(),new Long(432427),"testUser1","sdaasdasd");
            PhotoComment Comment2 = new PhotoComment(photoComments.get(1).getId(),Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId(),new Long(332426),"testUser1","sdaasdasd");
         
            assertTrue((photoComments.size() == 2) && (Comment1.equals(photoComments.get(0)))  && (Comment2.equals(photoComments.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test04_getAllPhotoCommentsByUserLogin(){
        try{
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", true);
            PhotoComment Comment1 =  new PhotoComment(photoComments.get(0).getId(),Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true).get(0).getId(),new Long(432427),"testUser1","sdaasdasd");
            PhotoComment Comment2 = new PhotoComment(photoComments.get(1).getId(),Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId(),new Long(332426),"testUser1","sdaasdasd");
         
            assertTrue((photoComments.size() == 2) && (Comment1.equals(photoComments.get(0)))  && (Comment2.equals(photoComments.get(1)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test05_getAllPhotoCommentsByPhoto(){
        try{
            PhotoDescription photo = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(photo.getId(), true);
            PhotoComment Comment3 = new PhotoComment(photoComments.get(2).getId(),photo.getId(),new Long(132424),"testUser2","sdaasdasd");
            PhotoComment Comment2 = new PhotoComment(photoComments.get(1).getId(),photo.getId(),new Long(232425),"testUser2","sdaasdasd");
            PhotoComment Comment1 = new PhotoComment(photoComments.get(0).getId(),photo.getId(),new Long(332426),"testUser1","sdaasdasd");
         
            assertTrue((photoComments.size() == 3) && (Comment1.equals(photoComments.get(0))) && (Comment2.equals(photoComments.get(1))) && (Comment3.equals(photoComments.get(2)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }

    @Test
    public void test06_getPhotoCommentById(){
        try{
            PhotoComment photoComment = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", true).get(0);
            PhotoComment photoCommentById = Factory.getInstance().getPhotoCommentDAO().getPhotoCommentById(photoComment.getId());
            assertTrue(photoCommentById.equals(photoComment)); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test07_updatePhotoComment(){
        try{
            PhotoComment photoComment = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", true).get(0);
            photoComment.setValue("NEW VALUE");
            Factory.getInstance().getPhotoCommentDAO().updatePhotoComment(photoComment);
            PhotoComment photoCommentAfter = Factory.getInstance().getPhotoCommentDAO().getPhotoCommentById(photoComment.getId());
            
            assertTrue(photoCommentAfter.equals(photoComment)); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test08_getLastPhotoComment(){
        try{
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", true);
            PhotoComment photoComment = Factory.getInstance().getPhotoCommentDAO().getLastPhotoComment("testUser1");
            
            assertTrue(photoComments.get(0).equals(photoComment)); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test09_getAllPhotoCommentsBeforeId(){
        try{
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId(), true);
            PhotoComment Comment1 = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", true).get(0);
            List <PhotoComment> photoCommentsBeforeId = Factory.getInstance().getPhotoCommentDAO().getPhotoCommentsBeforeId(Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId(), Comment1.getId(), 10, true);
            
            assertTrue((photoCommentsBeforeId.size() == 1) && (photoCommentsBeforeId.get(0).equals(photoComments.get(2)))); 
        }catch(SQLException | NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test10_setDeletedPhotoCommentById(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            PhotoComment CommentDelete = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", true).get(0);
         
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", false);
            Factory.getInstance().getPhotoCommentDAO().setDeletedPhotoCommentById(CommentDelete.getId());
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", false);
           
            assertTrue(photoCommentsBefore.size() == photoCommentsAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test11_setDeletedAllPhotoCommentsByUser(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", false);
            Factory.getInstance().getPhotoCommentDAO().setDeletedAllPhotoComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", false);
           
            assertTrue(!photoCommentsBefore.isEmpty() && photoCommentsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test12_setDeletedAllPhotoCommentsByUserLogin(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", false);
            Factory.getInstance().getPhotoCommentDAO().setDeletedAllPhotoComments("testUser2");
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", false);
           
            assertTrue(!photoCommentsBefore.isEmpty() && photoCommentsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test13_setDeletedAllPhotoCommentsByPhoto(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            long descId = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId();
            List <PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(descId, true);
            for (PhotoComment c : photoComments){
                c.setDeleted(false);
                Factory.getInstance().getPhotoCommentDAO().updatePhotoComment(c);
            }
            
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(descId, false);
            Factory.getInstance().getPhotoCommentDAO().setDeletedAllPhotoComments(descId);
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(descId, false);
           
            assertTrue(!photoCommentsBefore.isEmpty() && photoCommentsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test14_deletePhotoCommentById(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            PhotoComment CommentDelete = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", true).get(0);
         
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments();
            Factory.getInstance().getPhotoCommentDAO().deletePhotoCommentById(CommentDelete.getId());
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments();
           
            assertTrue(photoCommentsBefore.size() == photoCommentsAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test15_deleteAllPhotoCommentsByUser(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", true);
            Factory.getInstance().getPhotoCommentDAO().deleteAllPhotoComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser1", true);
           
            assertTrue(!photoCommentsBefore.isEmpty() && photoCommentsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test16_deleteAllPhotoCommentsByUserLogin(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", true);
            Factory.getInstance().getPhotoCommentDAO().deleteAllPhotoComments("testUser2");
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments("testUser2", true);
           
            assertTrue(!photoCommentsBefore.isEmpty() && photoCommentsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test17_deleteAllPhotoCommentsByPhoto(){
        List <PhotoComment> photoCommentsBefore, photoCommentsAfter;
        try{
            long descId = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0).getId();
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,descId, (long) 132424, "testUser2", "sdaasdasd"));
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,descId, (long) 232425, "testUser2", "sdaasdasd"));
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,descId, (long) 332426, "testUser1", "sdaasdasd"));
            
            photoCommentsBefore = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(descId, true);
            Factory.getInstance().getPhotoCommentDAO().deleteAllPhotoComments(descId);
            photoCommentsAfter = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(descId, true);
           
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser2");
           
            assertTrue(!photoCommentsBefore.isEmpty() && photoCommentsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
}