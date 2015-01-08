package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.AdvComment;
import hibernate.logic.Advertisement;
import hibernate.logic.AdvertisementFieldEnum;
import hibernate.logic.AdvertisementTypeEnum;
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
public class AdvCommentDAOtests {
    @Test
    public void A_addAdvCommentTest(){
        List<AdvComment> advCommentsBefore, advCommentsAfter;
        try{
           advCommentsBefore = Factory.getInstance().getAdvCommentDAO().getAllAdvComments();
           Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)1, new GregorianCalendar(1991, 0, 15), "asd22", "bab3a", new Long(1)));
           
           Factory.getInstance().getAdvertisementDAO().addAdvertisement(new Advertisement(
                null,new Long(33423),"testUser1","Let's GO!","WASSUP!","Chicago",AdvertisementFieldEnum.DATE,AdvertisementTypeEnum.GROUP,(byte)1,(byte)0,(byte)35));
           Factory.getInstance().getAdvCommentDAO().addAdvComment(new AdvComment(null,new Long(3423333),"testUser1",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),"dsadasd"));
           
           advCommentsAfter = Factory.getInstance().getAdvCommentDAO().getAllAdvComments();
           assertTrue(advCommentsBefore.size()+1==advCommentsAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void B_getAllAdvCommentsByUserTest(){
     try{
         List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
         AdvComment comment =  new AdvComment(advComments.get(0).getId(),new Long(3423333),"testUser1",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),"dsadasd");
        
         assertTrue((advComments.size()==1) && (comment.equals(advComments.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void C_getAllAdvCommentsByAdvertisementTest(){
     try{
         List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(
                         Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(
                                  Factory.getInstance().getUserDAO().getUserByLogin("testUser1")).get(0));
        AdvComment comment =  new AdvComment(advComments.get(0).getId(),new Long(3423333),"testUser1",Factory.getInstance().getAdvertisementDAO().getAllAdvertisementByLogin("testUser1").get(0).getAdvId(),"dsadasd");
         assertTrue((advComments.size()==1) && (comment.equals(advComments.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
 
    @Test
    public void D_getAdvCommentByIdTest(){
     try{
         List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
         AdvComment comment = Factory.getInstance().getAdvCommentDAO().getAdvCommentById(advComments.get(0).getId());
         assertTrue(comment.equals(advComments.get(0))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void E_updateAdvCommentValueTest(){
     try{
       AdvComment advCommentBefore = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1")).get(0);
         Factory.getInstance().getAdvCommentDAO().updateAdvCommentValue(advCommentBefore.getId(),advCommentBefore.getValue()+"ds");
       AdvComment advCommentAfter = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1")).get(0);
       
         assertTrue(advCommentBefore.getValue() != advCommentAfter.getValue());  
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    
    @Test
    public void F_deleteAdvCommentByIdTest(){
        List<AdvComment> advCommentsBefore, advCommentsAfter;
     try{
         List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
         
           advCommentsBefore = Factory.getInstance().getAdvCommentDAO().getAllAdvComments();
           Factory.getInstance().getAdvCommentDAO().deleteAdvCommentById(advComments.get(0).getId());
           advCommentsAfter = Factory.getInstance().getAdvCommentDAO().getAllAdvComments();
           Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
          
           assertTrue(advCommentsBefore.size()== advCommentsAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
}


