package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.AdvComment;
import hibernate.logic.Advertisement;
import hibernate.logic.AdvertisementFieldEnum;
import hibernate.logic.AdvertisementTypeEnum;
import hibernate.logic.Message;
import hibernate.logic.PhotoComment;
import hibernate.logic.PhotoDescription;
import hibernate.logic.PhotoLike;
import hibernate.logic.Response;
import hibernate.logic.SexEnum;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOtests {
    
    @Test
    public void test01_addUser(){
        List<User> usersBefore, usersAfter;
        try{
            usersBefore = Factory.getInstance().getUserDAO().getAllUsers();
            Factory.getInstance().getUserDAO().addUser(new User("testUser", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a", (long) 1));
            usersAfter = Factory.getInstance().getUserDAO().getAllUsers();
            assertTrue(usersBefore.size()+1 == usersAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test02_getUserByLogin(){
        try{
            User userBefore = new User("testUser", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a", new Long(1));     
            User userAfter = Factory.getInstance().getUserDAO().getUserByLogin("testUser");
            assertTrue(userBefore.equals(userAfter)); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test03_updateUser(){
     try{
            User userBefore = new User("testUser", "1111", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1976, 1, 23), "asd11", "bab3a", new Long(1));
            Factory.getInstance().getUserDAO().updateUser(userBefore);
            User userAfter = Factory.getInstance().getUserDAO().getUserByLogin("testUser");    
            assertEquals(userBefore, userAfter); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test04_setUserAvatarByPhotoDescriptionIdByUser(){
        try{
            User user = Factory.getInstance().getUserDAO().getUserByLogin("testUser");  
            Factory.getInstance().getUserDAO().setUserAvatarByPhotoDescriptionId(user, (long) 300);
            User userAfter = Factory.getInstance().getUserDAO().getUserByLogin("testUser");    
            assertEquals((long) 300, (long) userAfter.getAvatar()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test05_setUserAvatarByPhotoDescriptionIdByLogin(){
     try{
            Factory.getInstance().getUserDAO().setUserAvatarByPhotoDescriptionId("testUser", (long) 400);
            User userAfter = Factory.getInstance().getUserDAO().getUserByLogin("testUser");    
            assertEquals((long) 400, (long) userAfter.getAvatar()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test06_setLastOnlineTimeByUser(){
        try{
            User user = Factory.getInstance().getUserDAO().getUserByLogin("testUser");  
            Factory.getInstance().getUserDAO().setUserLastOnlineTime(user, (long) 323323);
            User userAfter = Factory.getInstance().getUserDAO().getUserByLogin("testUser");    
            assertEquals((long) 323323, (long) userAfter.getLastOnlineTime()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test07_setLastOnlineTimeByLogin(){
     try{
            Factory.getInstance().getUserDAO().setUserLastOnlineTime("testUser", (long) 545455);
            User userAfter = Factory.getInstance().getUserDAO().getUserByLogin("testUser");    
            assertEquals((long) 545455, (long) userAfter.getLastOnlineTime()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test08_getAllUsers(){
     try{
           List <User> users = Factory.getInstance().getUserDAO().getAllUsers();   
           assertTrue(!users.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test09_getUsersByFullSearchAfterLogin(){
     try{
           User user = Factory.getInstance().getUserDAO().getUserByLogin("testUser");
           List <User> users = Factory.getInstance().getUserDAO().getAllUsers(); 
           List <User> usersTrue = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", SexEnum.MALE, 0, 100, "", "testA", users.size());   
           List <User> usersFalse1 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", SexEnum.FEMALE, 0, 100, "", "testA", users.size());   
           List <User> usersFalse2 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", SexEnum.BOTH, 0, 20, "", "testA", users.size());   
           List <User> usersFalse3 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", SexEnum.BOTH, 50, 20, "", "testA", users.size());   
           List <User> usersFalse4 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", SexEnum.BOTH, 0, 100, "", "testV", users.size());   
           List <User> usersFalse5 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("rfg", SexEnum.BOTH, 0, 20, "", "testA", users.size());   
           
           assertTrue((usersTrue.contains(user)) && (!usersFalse1.contains(user)) && (!usersFalse2.contains(user)) && (!usersFalse3.contains(user)) && (!usersFalse4.contains(user)) && (!usersFalse5.contains(user))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test10_getUsersBySearchAfterLogin(){
     try{
            User user = Factory.getInstance().getUserDAO().getUserByLogin("testUser");
            List <User> users = Factory.getInstance().getUserDAO().getAllUsers();
            List <User> usersTrue = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", "testA", users.size()); 
            List <User> usersFalse1 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("test", "testV", users.size()); 
            List <User> usersFalse2 = Factory.getInstance().getUserDAO().getUsersBySearchAfterLogin("rdfg", "testA", users.size());
           
            assertTrue((usersTrue.contains(user)) && (!usersFalse1.contains(user)) && (!usersFalse2.contains(user))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test11_getUsersBySearchBegoreLogin(){
     try{
            User user = Factory.getInstance().getUserDAO().getUserByLogin("testUser");
            List <User> users = Factory.getInstance().getUserDAO().getAllUsers(); 
            List <User> usersTrue = Factory.getInstance().getUserDAO().getUsersBySearchBeforeLogin("test", "testV", users.size()); 
            List <User> usersFalse1 = Factory.getInstance().getUserDAO().getUsersBySearchBeforeLogin("test", "testA", users.size()); 
            List <User> usersFalse2 = Factory.getInstance().getUserDAO().getUsersBySearchBeforeLogin("rdfg", "testV", users.size());   
           
            assertTrue((usersTrue.contains(user)) && (!usersFalse1.contains(user)) && (!usersFalse2.contains(user))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test12_getUsersAfterLogin(){
     try{
            User user = Factory.getInstance().getUserDAO().getUserByLogin("testUser");
            List <User> users = Factory.getInstance().getUserDAO().getAllUsers();
            List <User> usersTrue = Factory.getInstance().getUserDAO().getUsersAfterLogin("testA", users.size()); 
            List <User> usersFalse = Factory.getInstance().getUserDAO().getUsersAfterLogin("testV", users.size()); 
           
            assertTrue((usersTrue.contains(user)) && (!usersFalse.contains(user))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test13_deleteUserByLogin(){
        List<User> usersBefore, usersAfter;
     try{
           usersBefore = Factory.getInstance().getUserDAO().getAllUsers();
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser");
           usersAfter = Factory.getInstance().getUserDAO().getAllUsers();
           assertTrue(usersBefore.size()==usersAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
        @Test
    public void test14_deleteUserByLoginAndAllSuccessors(){
        List<User> usersBefore, usersAfter;
     try{
           Factory.getInstance().getUserDAO().addUser(new User("testUser", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a", (long) 1));
           Factory.getInstance().getAdvertisementDAO().addAdvertisement(new Advertisement(null, (long) 123121231,"testUser","Let's GO!","WASSUP!","Chicago",AdvertisementFieldEnum.DATE,AdvertisementTypeEnum.GROUP,(byte)1,(byte)30,(byte)35));
           Factory.getInstance().getAdvertisementDAO().addAdvertisement(new Advertisement(null, (long) 1231231231,"testUser","Lefdst's GO!","WASSUfP!","Chifcago",AdvertisementFieldEnum.SPORTS,AdvertisementTypeEnum.GROUP,(byte)0,(byte)30,(byte)35));
           Factory.getInstance().getResponseDAO().addResponse(new Response(null, (long) 34223, "testUser",Factory.getInstance().getAdvertisementDAO().getAllAdvertisement().get(0).getAdvId(), false));
           Factory.getInstance().getAdvCommentDAO().addAdvComment(new AdvComment(null, (long) 34223, "testUser",Factory.getInstance().getAdvertisementDAO().getAllAdvertisement().get(0).getAdvId(), "dsadas"));
           Factory.getInstance().getMessageDAO().addMessage(new Message(null, (long) 342423, "admin1", "testUser", "sasdsdd", null));
           Factory.getInstance().getMessageDAO().addMessage(new Message(null, (long) 342423, "testUser", "god", "sasdsddd", null));
           Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(new PhotoDescription(null,"testUser","sdfdsfv"));
           PhotoDescription testUserDesc = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser", true).get(0);
           Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions().get(0).getId(),"testUser"));
           Factory.getInstance().getPhotoLikeDAO().addPhotoLike(new PhotoLike(null,testUserDesc.getId(),"testUser"));
           Factory.getInstance().getPhotoCommentDAO().addPhotoComment(new PhotoComment(null,testUserDesc.getId(), (long) 324324,"testUser","asdadasd"));
           usersBefore = Factory.getInstance().getUserDAO().getAllUsers();
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser");
           usersAfter = Factory.getInstance().getUserDAO().getAllUsers();
           assertTrue(usersBefore.size()==usersAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    
}


