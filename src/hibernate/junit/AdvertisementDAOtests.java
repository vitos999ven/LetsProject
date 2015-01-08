package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.AdvComment;
import hibernate.logic.Advertisement;
import hibernate.logic.AdvertisementFieldEnum;
import hibernate.logic.AdvertisementTypeEnum;
import hibernate.logic.Message;
import hibernate.logic.Response;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdvertisementDAOtests {
    @Test
    public void A_addAdvertisementTest(){
        List<Advertisement> advertisementBefore, advertisementAfter;
        try{
           advertisementBefore = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
           Factory.getInstance().getUserDAO().addUser(new User("testUser", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a",  new Long(1)));
           Factory.getInstance().getAdvertisementDAO().addAdvertisement(new Advertisement(
                null,new Long(33423),"testUser","Let's GO!","WASSUP!","Chicago",AdvertisementFieldEnum.DATE,AdvertisementTypeEnum.GROUP,(byte)1,(byte)30,(byte)35));
        advertisementAfter = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
           assertTrue(advertisementBefore.size()+1==advertisementAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void B_getAllAdvertisementByUserTest(){
     try{
         List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement( 
                  Factory.getInstance().getUserDAO().getUserByLogin("testUser"));
         Advertisement adv =  new Advertisement(advertisement.get(0).getAdvId(),new Long(33423),"testUser","Let's GO!","WASSUP!","Chicago",AdvertisementFieldEnum.DATE,AdvertisementTypeEnum.GROUP,(byte)1,(byte)30,(byte)35);
         assertTrue((advertisement.size()==1) && (adv.equals(advertisement.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
 
    @Test
    public void C_getAdvertisementByIdTest(){
     try{
         List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(Factory.getInstance().getUserDAO().getUserByLogin("testUser"));
         Advertisement adv = Factory.getInstance().getAdvertisementDAO().getAdvertisementById(advertisement.get(0).getAdvId());
         assertTrue(adv.equals(advertisement.get(0))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void D_updateAdvertisementTest(){
     try{
         Advertisement advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(Factory.getInstance().getUserDAO().getUserByLogin("testUser")).get(0);
         advertisement.setAgeFrom((byte)0);
         advertisement.setAgeTo((byte)0);
         advertisement.setValue("ewqeqweqweqw");
         Factory.getInstance().getAdvertisementDAO().updateAdvertisement(advertisement);
         Advertisement advertisementAfter = Factory.getInstance().getAdvertisementDAO().getAdvertisementById(advertisement.getAdvId());
        assertTrue(advertisement.equals(advertisementAfter)); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    
    @Test
    public void E_deleteAdvertisementByIdTest(){
        List<Advertisement> advertisementBefore, advertisementAfter;
     try{
         List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(Factory.getInstance().getUserDAO().getUserByLogin("testUser"));
         
           advertisementBefore = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
           Factory.getInstance().getAdvertisementDAO().deleteAdvertisementById(advertisement.get(0).getAdvId());
           advertisementAfter = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
           
           assertTrue(advertisementBefore.size()== advertisementAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void F_deleteAdvertisementByIdAndAllSuccessorsTest(){
        List<Advertisement> advertisementBefore, advertisementAfter;
     try{
           Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a",  new Long(1)));
           Factory.getInstance().getAdvertisementDAO().addAdvertisement(new Advertisement(
                  null, (long) 33423,"testUser","Let's GO!","WASSUP!","Chicago",AdvertisementFieldEnum.DATE,AdvertisementTypeEnum.GROUP,(byte)1,(byte)30,(byte)35));
           Factory.getInstance().getResponseDAO().addResponse(new Response(null, (long) 34223, "testUser1",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser").get(0).getAdvId(), false));
           Factory.getInstance().getAdvCommentDAO().addAdvComment(new AdvComment(null, (long) 34223, "testUser",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser").get(0).getAdvId(), "dsadas"));
           
           List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(Factory.getInstance().getUserDAO().getUserByLogin("testUser"));
         
           advertisementBefore = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
           Factory.getInstance().getAdvertisementDAO().deleteAdvertisementById(advertisement.get(0).getAdvId());
           advertisementAfter = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser");
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
           
           assertTrue(advertisementBefore.size()== advertisementAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
}


