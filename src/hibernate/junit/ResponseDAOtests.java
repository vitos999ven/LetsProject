package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.Advertisement;
import hibernate.logic.AdvertisementFieldEnum;
import hibernate.logic.AdvertisementTypeEnum;
import hibernate.logic.Response;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResponseDAOtests {
    @Test
    public void A_addResponseTest(){
        List<Response> responsesBefore, responsesAfter;
        try{
           responsesBefore = Factory.getInstance().getResponseDAO().getAllResponses();
           Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", new Long(1)));
           Factory.getInstance().getUserDAO().addUser(new User("testUser2", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", new Long(1)));
           
           Factory.getInstance().getAdvertisementDAO().addAdvertisement(new Advertisement(
                null,new Long(33423),"testUser1","Let's GO!","WASSUP!","Chicago",AdvertisementFieldEnum.DATE,AdvertisementTypeEnum.GROUP,(byte)1,(byte)0,(byte)35));
           Factory.getInstance().getResponseDAO().addResponse(new Response(null,new Long(3423333),"testUser1",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),false));
           Factory.getInstance().getResponseDAO().addResponse(new Response(null,new Long(3423333),"testUser2",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),false));
           
           responsesAfter = Factory.getInstance().getResponseDAO().getAllResponses();
           assertTrue(responsesBefore.size()+1==responsesAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void B_getAllResponsesByUserTest(){
     try{
         List <Response> responses = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getUserDAO().getUserByLogin("testUser2"));
         Response resp =  new Response(responses.get(0).getId(),new Long(3423333),"testUser2",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),false);
         assertTrue((responses.size()==1) && (resp.equals(responses.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
        @Test
    public void C_getAllResponsesByAdvertisementTest(){
     try{
         List <Response> responses = Factory.getInstance().getResponseDAO().getAllResponses(
                         Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(
                                  Factory.getInstance().getUserDAO().getUserByLogin("testUser1")).get(0));
         Response resp =  new Response(responses.get(0).getId(),new Long(3423333),"testUser2",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),false);
         assertTrue((responses.size()==1) && (resp.equals(responses.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
 
    @Test
    public void D_getResponseByIdTest(){
     try{
         List<Response> responses = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getUserDAO().getUserByLogin("testUser2"));
         Response resp = Factory.getInstance().getResponseDAO().getResponseById(responses.get(0).getId());
         assertTrue(resp.equals(responses.get(0))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void E_changeResponseConfirmationTest(){
     try{
        Response responseBefore = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getUserDAO().getUserByLogin("testUser2")).get(0);
        Factory.getInstance().getResponseDAO().changeResponseConfirmation(responseBefore.getId());
        Response responseAfter = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getUserDAO().getUserByLogin("testUser2")).get(0);
       
         assertTrue(responseBefore.getConfirmation() != responseAfter.getConfirmation());  
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    
    @Test
    public void F_deleteResponseByIdTest(){
        List<Response> responsesBefore, responsesAfter;
     try{
         List<Response> responses = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getUserDAO().getUserByLogin("testUser2"));
         
           responsesBefore = Factory.getInstance().getResponseDAO().getAllResponses();
           Factory.getInstance().getResponseDAO().deleteResponseById(responses.get(0).getId());
           responsesAfter = Factory.getInstance().getResponseDAO().getAllResponses();
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser2");
          
           assertTrue(responsesBefore.size()== responsesAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
}


