package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.PhotoComment;
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
public class PhotoDescriptionDAOtests {
    
    @Test
    public void test01_addPhotoDescription(){
        List<PhotoDescription> photoDescriptionsBefore, photoDescriptionsAfter;
        try{
            photoDescriptionsBefore = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", (long) 1));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 3333333,"testUser1","sdfdsfv"));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 3333333,"testUser1","sdfdsfvcc"));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 3333333,"testUser1","sdfd"));
           
            photoDescriptionsAfter = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            assertTrue(photoDescriptionsBefore.size() + 3 == photoDescriptionsAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test02_getAllPhotoDescriptions(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            
            assertTrue(!photoDescriptions.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }catch(NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test03_getAllPhotoDescriptionsByUser(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            PhotoDescription desc1 = new PhotoDescription(photoDescriptions.get(0).getId(), (long) 3333333,"testUser1","sdfdsfv");
            PhotoDescription desc2 = new PhotoDescription(photoDescriptions.get(1).getId(), (long) 3333333,"testUser1","sdfdsfvcc");
            PhotoDescription desc3 = new PhotoDescription(photoDescriptions.get(2).getId(), (long) 3333333,"testUser1","sdfd");
            assertTrue((photoDescriptions.size() == 3) && (desc1.equals(photoDescriptions.get(0))) && (desc2.equals(photoDescriptions.get(1))) && (desc3.equals(photoDescriptions.get(2)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }catch(NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test04_getAllPhotoDescriptionsByUserLogin(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            PhotoDescription desc1 = new PhotoDescription(photoDescriptions.get(0).getId(), (long) 3333333,"testUser1","sdfdsfv");
            PhotoDescription desc2 = new PhotoDescription(photoDescriptions.get(1).getId(), (long) 3333333,"testUser1","sdfdsfvcc");
            PhotoDescription desc3 = new PhotoDescription(photoDescriptions.get(2).getId(), (long) 3333333,"testUser1","sdfd");
            assertTrue((photoDescriptions.size() == 3) && (desc1.equals(photoDescriptions.get(0))) && (desc2.equals(photoDescriptions.get(1))) && (desc3.equals(photoDescriptions.get(2)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }catch(NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test05_getAllPhotoDescriptionsBeforeIdByUser(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            PhotoDescription desc = new PhotoDescription(photoDescriptions.get(2).getId(), (long) 3333333,"testUser1","sdfd");
            List <PhotoDescription> photoDescriptionsBeforeId1 = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionsBeforeId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), photoDescriptions.get(2).getId() + 1, 10, true);
            List <PhotoDescription> photoDescriptionsBeforeId2 = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionsBeforeId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), photoDescriptions.get(2).getId(), 10, true);
         
            assertTrue((photoDescriptionsBeforeId1.size() == 3) && (photoDescriptionsBeforeId1.contains(desc)) && (photoDescriptionsBeforeId2.size() == 2) && (!photoDescriptionsBeforeId2.contains(desc))); 
        }catch(SQLException e){
            assertTrue(false); 
        }catch(NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test06_getAllPhotoDescriptionsBeforeIdByUserLogin(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            PhotoDescription desc = new PhotoDescription(photoDescriptions.get(2).getId(), (long) 3333333,"testUser1","sdfd");
            List <PhotoDescription> photoDescriptionsBeforeId1 = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionsBeforeId("testUser1", photoDescriptions.get(2).getId() + 1, 10, true);
            List <PhotoDescription> photoDescriptionsBeforeId2 = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionsBeforeId("testUser1", photoDescriptions.get(2).getId(), 10, true);
            
            assertTrue((photoDescriptionsBeforeId1.size() == 3) && (photoDescriptionsBeforeId1.contains(desc)) && (photoDescriptionsBeforeId2.size() == 2) && (!photoDescriptionsBeforeId2.contains(desc))); 
        }catch(SQLException e){
            assertTrue(false); 
        }catch(NullPointerException e){
            System.out.println(e.toString());
            assertTrue(false); 
        }     
    }

    @Test
    public void test07_getPhotoDescriptionById(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            PhotoDescription description = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionById(photoDescriptions.get(0).getId());
            assertTrue(description.equals(photoDescriptions.get(0))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test08_updatePhotoDescriptionById(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            photoDescriptions.get(0).setDescription("NEW DESCRIPTION");
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(photoDescriptions.get(0).getId(), photoDescriptions.get(0).getDescription());
            PhotoDescription photoDescription = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionById(photoDescriptions.get(0).getId());
       
            assertTrue(photoDescription.equals(photoDescriptions.get(0)));  
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test09_updateFullPhotoDescription(){
        try{
            List <PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            photoDescriptions.get(0).setDescription("OTHER NEW DESCRIPTION");
            Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(photoDescriptions.get(0));
            PhotoDescription photoDescription = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionById(photoDescriptions.get(0).getId());
       
            assertTrue(photoDescription.equals(photoDescriptions.get(0)));  
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test10_setDeletedPhotoDescriptionById(){
        try{
            PhotoDescription desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            Factory.getInstance().getPhotoDescriptionDAO().setDeletedPhotoDescriptionById(desc.getId());
            desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            Factory.getInstance().getPhotoDescriptionDAO().setDeletedPhotoDescriptionById(desc.getId());
            PhotoDescription desc1 = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
         
            assertTrue(desc.getDeleted() && desc1.getDeleted()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test11_setDeletedAllPhotoDescriptionsByUser(){
        try{
            List<PhotoDescription> descs = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            for (PhotoDescription desc : descs){
                desc.setDeleted(false);
                Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(desc);
            }
            Factory.getInstance().getPhotoDescriptionDAO().setDeletedAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            descs = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", false);
            List<PhotoDescription> descsWithDeleted = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            assertTrue(descs.isEmpty() && !descsWithDeleted.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test12_setDeletedAllPhotoDescriptionsByUserLogin(){
        try{
            Factory.getInstance().getUserDAO().addUser(new User("testUser2", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", (long) 1));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 3333333,"testUser2","sdfdsfv"));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 3333333,"testUser2","sdfdsfvcc"));
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null, (long) 3333333,"testUser2","sdfd"));
           
            Factory.getInstance().getPhotoDescriptionDAO().setDeletedAllPhotoDescriptions("testUser2");
            List<PhotoDescription> descs = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", false);
            List<PhotoDescription> descsWithDeleted = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true);
            assertTrue(descs.isEmpty() && !descsWithDeleted.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test13_deletePhotoDescriptionById(){
        List<PhotoDescription> photoDescriptionsBefore, photoDescriptionsAfter;
        try{
            Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null,"testUser1","sdfdsfvc"));
         
            List <PhotoDescription> testUser1Desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            PhotoDescription descriptionDelete = testUser1Desc.get(testUser1Desc.size() - 1);
         
            photoDescriptionsBefore = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            Factory.getInstance().getPhotoDescriptionDAO().deletePhotoDescriptionById(descriptionDelete.getId());
            photoDescriptionsAfter = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            assertTrue(photoDescriptionsBefore.size()== photoDescriptionsAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
       
    
    @Test
    public void test14_deletePhotoDescriptionAndAllSuccessors(){
        List<PhotoDescription> photoDescriptionsBefore, photoDescriptionsAfter;
        try{
            PhotoDescription desc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true).get(0);
            Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,desc.getId(),"testUser1"));
            Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,desc.getId(), (long) 132424,"testUser1","sdaasdasd"));
           
            photoDescriptionsBefore = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            Factory.getInstance().getPhotoDescriptionDAO().deletePhotoDescriptionById(desc.getId());
            photoDescriptionsAfter = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
            
            assertTrue(photoDescriptionsBefore.size()== photoDescriptionsAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test15_deleteAllPhotoDescriptionsByUser(){
        try{
            List<PhotoDescription> descsBefore = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            Factory.getInstance().getPhotoDescriptionDAO().deleteAllPhotoDescriptions(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            List<PhotoDescription> descsAfter = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
            
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
           
            assertTrue(!descsBefore.isEmpty() && descsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test16_deleteAllPhotoDescriptionsByUserLogin(){
        try{
            List<PhotoDescription> descsBefore = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true);
            Factory.getInstance().getPhotoDescriptionDAO().deleteAllPhotoDescriptions("testUser2");
            List<PhotoDescription> descsAfter = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser2", true);
            
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser2");
           
            assertTrue(!descsBefore.isEmpty() && descsAfter.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
}


