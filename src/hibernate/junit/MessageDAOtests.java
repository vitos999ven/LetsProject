package hibernate.junit;


import hibernate.logic.AccessEnum;
import hibernate.logic.Message;
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageDAOtests {
    
    @Test
    public void test01_addMessage(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllMessages();
            Factory.getInstance().getUserDAO().addUser(new User("testUser1", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a", (long) 1));
            Factory.getInstance().getUserDAO().addUser(new User("testUser2", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a", (long) 1));
           
            Factory.getInstance().getMessageDAO().addMessage(new Message(null, (long) 4323432, "testUser1", "testUser2", "sasdsdd", null));
            messagesAfter = Factory.getInstance().getMessageDAO().getAllMessages();
            assertTrue(messagesBefore.size()+2 == messagesAfter.size()); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test02_getAllMessages(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages();
            assertTrue(!messages.isEmpty()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test03_getAllMessagesByUser(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), false, true);
            Message m1 = new Message(messages.get(0).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", messages.get(0).getUser());
            Message m2 = new Message(messages.get(1).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", messages.get(1).getUser());
          
            assertTrue((messages.size() == 2) && (m1.equals(messages.get(0))) && (m2.equals(messages.get(1)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test04_getAllMessagesByUserLogin(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages("testUser1", false, true);
            Message m1 = new Message(messages.get(0).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", messages.get(0).getUser());
            Message m2 = new Message(messages.get(1).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", messages.get(1).getUser());
          
            assertTrue((messages.size() == 2) && (m1.equals(messages.get(0))) && (m2.equals(messages.get(1)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test05_getAllUserToUserMessagesByUsers(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), true);
            Message m1 = new Message(messages.get(0).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", "testUser1");
          
            assertTrue((messages.size() == 1) && (m1.equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test06_getAllUserToUserMessagesByUsersLogins(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser1", "testUser2", true);
            Message m1 = new Message(messages.get(0).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", "testUser1");
            assertTrue((messages.size() == 1) && (m1.equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test07_getUserToUserMessagesBeforeIdByUsers(){
        try{
            Factory.getInstance().getMessageDAO().addMessage(new Message(null, (long) 9999999, "testUser2", "testUser1", "message2", null));
            List<Message> allUserToUserMessages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), true);
            long lastId = allUserToUserMessages.get(0).getId();
            List<Message> messages = Factory.getInstance().getMessageDAO().getUserToUserMessagesBeforeId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), lastId, allUserToUserMessages.size(),  true);
            
            assertTrue((messages.size() == 1) && (allUserToUserMessages.get(1).equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test08_getUserToUserMessagesBeforeIdByUsersLogins(){
        try{
            List<Message> allUserToUserMessages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), true);
            long lastId = allUserToUserMessages.get(0).getId();
            List<Message> messages = Factory.getInstance().getMessageDAO().getUserToUserMessagesBeforeId("testUser1", "testUser2", lastId, allUserToUserMessages.size(), true);
            
            assertTrue((messages.size() == 1) && (allUserToUserMessages.get(1).equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test09_getAllUserToUserMessagesFromIdByUsers(){
        try{
            List<Message> allUserToUserMessages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), true);
            long lastId = allUserToUserMessages.get(allUserToUserMessages.size() - 1).getId();
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllUserToUserMessagesFromId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), lastId, true);
            
            assertTrue((messages.size() == 1) && (allUserToUserMessages.get(0).equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test10_getAllUserToUserMessagesFromIdByUsersLogins(){
        try{
            List<Message> allUserToUserMessages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"),Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), true);
            long lastId = allUserToUserMessages.get(allUserToUserMessages.size() - 1).getId();
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllUserToUserMessagesFromId("testUser1", "testUser2", lastId, true);
            
            assertTrue((messages.size() == 1) && (allUserToUserMessages.get(0).equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test11_getMessageById(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), false, true);
            assertTrue((messages.get(0).equals(Factory.getInstance().getMessageDAO().getMessageById(messages.get(0).getId()))) && (messages.get(1).equals(Factory.getInstance().getMessageDAO().getMessageById(messages.get(1).getId())))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test12_updateMessage(){
        try{
            User testUser1 = Factory.getInstance().getUserDAO().getUserByLogin("testUser1");
            User testUser2 = Factory.getInstance().getUserDAO().getUserByLogin("testUser2");
            Message messageBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
            messageBefore.setDate((long) 345);
            messageBefore.setValue("new value");
            Factory.getInstance().getMessageDAO().updateMessage(messageBefore);
            Message messageAfter = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(1);
         
            assertTrue(messageAfter.equals(messageBefore)); 
        }catch(SQLException e){
            assertTrue(false); 
        } 
     
    }
    
    @Test
    public void test13_changeMessageUnread(){
        try{
            User testUser1 = Factory.getInstance().getUserDAO().getUserByLogin("testUser1");
            User testUser2 = Factory.getInstance().getUserDAO().getUserByLogin("testUser2");
            Message messageBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
            Factory.getInstance().getMessageDAO().changeMessageUnread(messageBefore.getId());
            Message message1 = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
         
            Factory.getInstance().getMessageDAO().changeMessageUnread(messageBefore.getId());
            Message message2 = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
         
            assertTrue(!message1.getUnread() && !message2.getUnread()); 
        }catch(SQLException e){
            assertTrue(false); 
        }      
    }
    
    @Test
    public void test14_changeAllUserToUserMessagesUnreadByUsers(){
        try{
            User testUser1 = Factory.getInstance().getUserDAO().getUserByLogin("testUser1");
            User testUser2 = Factory.getInstance().getUserDAO().getUserByLogin("testUser2");
            Message messageBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
            messageBefore.setUnread(true);
            Factory.getInstance().getMessageDAO().updateMessage(messageBefore);
            Factory.getInstance().getMessageDAO().changeAllUserToUserMessagesUnread(testUser1, testUser2);
            Message message1 = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
         
            Factory.getInstance().getMessageDAO().changeAllUserToUserMessagesUnread(testUser2, testUser1);
            Message message2 = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
         
            assertTrue(!message1.getUnread() && !message2.getUnread()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test15_changeAllUserToUserMessagesUnreadByUsersLogins(){
        try{
            String testUser1 = "testUser1", testUser2 = "testUser2";
            Message messageBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
            messageBefore.setUnread(true);
            Factory.getInstance().getMessageDAO().updateMessage(messageBefore);
            Factory.getInstance().getMessageDAO().changeAllUserToUserMessagesUnread(testUser1, testUser2);
            Message message1 = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
         
            Factory.getInstance().getMessageDAO().changeAllUserToUserMessagesUnread(testUser2, testUser1);
            Message message2 = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(testUser1,testUser2, true).get(0);
            
            Factory.getInstance().getMessageDAO().updateMessage(messageBefore);
            
            assertTrue(!message1.getUnread() && !message2.getUnread()); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test16_getDialogsOfAllUsersWhoTalkedWithByUser(){
        try{
            Factory.getInstance().getUserDAO().addUser(new User("testUser3", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1975, 0, 15), "asd22", "bab3a", (long) 1));
           
            Factory.getInstance().getMessageDAO().addMessage(new Message(null, (long) 432323432, "testUser3", "testUser1", "sasdsdd", null));
           
            List<Message> messages = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWith(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), true);
            Message m2 = new Message(messages.get(1).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", "testUser1", true);
            Message m1 = new Message(messages.get(0).getId(), new Long(432323432), "testUser3", "testUser1", "sasdsdd", "testUser1", true);
            
            assertTrue((messages.size() == 2) && (m1.equals(messages.get(0))) && (m2.equals(messages.get(1)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test17_getDialogsOfAllUsersWhoTalkedWithByUserLogin(){
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWith("testUser1", true);
            Message m2 = new Message(messages.get(1).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", "testUser1", true);
            Message m1 = new Message(messages.get(0).getId(), new Long(432323432), "testUser3", "testUser1", "sasdsdd", "testUser1", true);
         
            assertTrue((messages.size() == 2) && (m1.equals(messages.get(0))) && (m2.equals(messages.get(1)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test18_getDialogsOfAllUsersWhoTalkedWithBeforeIdByUser(){
        try{
            List<Message> AllDialogs = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWith("testUser1", true);
            long id = AllDialogs.get(0).getId();
            List<Message> messages = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWithBeforeId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), id, AllDialogs.size(), true);
            
            Message m1 = new Message(messages.get(0).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", "testUser1", true);
            
            assertTrue((messages.size() == 1) && (m1.equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test19_getDialogsOfAllUsersWhoTalkedWithBeforeIdByUserLogin(){
        try{
            List<Message> AllDialogs = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWith("testUser1", true);
            long id = AllDialogs.get(0).getId();
            List<Message> messages = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWithBeforeId("testUser1", id, AllDialogs.size(), true);
            
            Message m1 = new Message(messages.get(0).getId(), new Long(4323432), "testUser1", "testUser2", "sasdsdd", "testUser1", true);
            
            assertTrue((messages.size() == 1) && (m1.equals(messages.get(0)))); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test20_getLastUserToUserMessageIdByUsers(){
        try{
            long id = Factory.getInstance().getMessageDAO().getLastUserToUserMessageId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"), Factory.getInstance().getUserDAO().getUserByLogin("testUser2"));
            Message message = Factory.getInstance().getMessageDAO().getMessageById(id);
            
            assertTrue(message.getDate() == (long) 345); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test21_getLastUserToUserMessageIdByUsersLogins(){
        try{
            long id = Factory.getInstance().getMessageDAO().getLastUserToUserMessageId("testUser1", "testUser2");
            Message message = Factory.getInstance().getMessageDAO().getMessageById(id);
            
            assertTrue(message.getDate() == (long) 345); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test22_getLastDialogsMessageIdByUser(){
        try{
            long id = Factory.getInstance().getMessageDAO().getLastDialogsMessageId(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            Message message = Factory.getInstance().getMessageDAO().getMessageById(id);
            
            assertTrue(message.getDate() == (long) 432323432); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test23_getLastDialogsMessageIdByUserLogin(){
        try{
            long id = Factory.getInstance().getMessageDAO().getLastDialogsMessageId("testUser1");
            Message message = Factory.getInstance().getMessageDAO().getMessageById(id);
            
            assertTrue(message.getDate() == (long) 432323432); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test24_getNumberOfUnreadDialogsByUser(){
        try{
            int number = Factory.getInstance().getMessageDAO().getNumberOfUnreadDialogs(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            assertTrue(number == 1); 
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test25_getNumberOfUnreadDialogsByUserLogin(){
        try{
            int number = Factory.getInstance().getMessageDAO().getNumberOfUnreadDialogs("testUser1");
            assertTrue(number == 1);
        }catch(SQLException e){
            assertTrue(false); 
        }     
    }
    
    @Test
    public void test26_setDeletedMessageById(){
        List<Message> messagesBefore, messagesAfter;
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), false, true);
         
            messagesBefore = Factory.getInstance().getMessageDAO().getAllMessages("testUser2", false, false);
            Factory.getInstance().getMessageDAO().setDeletedMessageById(messages.get(0).getId());
            messagesAfter = Factory.getInstance().getMessageDAO().getAllMessages("testUser2", false, false);
          
            assertTrue(messagesBefore.size() == messagesAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test27_setDeletedDialogByUsers(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser3", "testUser1", false);
            Factory.getInstance().getMessageDAO().setDeletedDialog(Factory.getInstance().getUserDAO().getUserByLogin("testUser3"), Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            messagesAfter = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser3", "testUser1", false);
          
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty())); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test28_setDeletedDialogByUsersLogins(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser1", "testUser3", false);
            Factory.getInstance().getMessageDAO().setDeletedDialog("testUser1", "testUser3");
            messagesAfter = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser1", "testUser3", false);
          
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty()));
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test29_setDeletedAllMessagesByUser(){
        List<Message> messagesBefore, messagesAfter;
        try{
            Factory.getInstance().getMessageDAO().addMessage(new Message(null, (long) 8888888, "testUser3", "testUser1", "message3", null));
            
            messagesBefore = Factory.getInstance().getMessageDAO().getAllMessages("testUser1", true, false);
            Factory.getInstance().getMessageDAO().setDeletedAllMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            messagesAfter = Factory.getInstance().getMessageDAO().getAllMessages("testUser1", true, false);
          
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty())); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test30_setDeletedAllMessagesByUserLogin(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllMessages("testUser3", true, false);
            Factory.getInstance().getMessageDAO().setDeletedAllMessages("testUser3");
            messagesAfter = Factory.getInstance().getMessageDAO().getAllMessages("testUser3", true, false);
         
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty()));
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test31_deleteMessageById(){
        List<Message> messagesBefore, messagesAfter;
        try{
            List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages(Factory.getInstance().getUserDAO().getUserByLogin("testUser2"), false, true);
         
            messagesBefore = Factory.getInstance().getMessageDAO().getAllMessages();
            Factory.getInstance().getMessageDAO().deleteMessageById(messages.get(1).getId());
            messagesAfter = Factory.getInstance().getMessageDAO().getAllMessages();
          
            assertTrue(messagesBefore.size() == messagesAfter.size()+1); 
        }catch(SQLException e){
            assertTrue(false); 
        }    
    }
    
    @Test
    public void test32_deleteDialogByUsers(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser3", "testUser1", true);
            Factory.getInstance().getMessageDAO().deleteDialog(Factory.getInstance().getUserDAO().getUserByLogin("testUser3"), Factory.getInstance().getUserDAO().getUserByLogin("testUser1"));
            messagesAfter = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser3", "testUser1", true);
          
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty())); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test33_deleteDialogByUsersLogins(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser2", "testUser1", true);
            Factory.getInstance().getMessageDAO().deleteDialog("testUser2", "testUser1");
            messagesAfter = Factory.getInstance().getMessageDAO().getAllUserToUserMessages("testUser2", "testUser1", true);
          
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty())); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
    @Test
    public void test34_setDeletedAllMessages(){
        List<Message> messagesBefore, messagesAfter;
        try{
            messagesBefore = Factory.getInstance().getMessageDAO().getAllMessages("testUser1", false, true);
            Factory.getInstance().getMessageDAO().deleteAllMessages("testUser1");
            messagesAfter = Factory.getInstance().getMessageDAO().getAllMessages("testUser1", false, true);
            
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser1");
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser2");
            Factory.getInstance().getUserDAO().deleteUserByLogin("testUser3");
          
            assertTrue((!messagesBefore.isEmpty()) && (messagesAfter.isEmpty())); 
        }catch(SQLException e){
            assertTrue(false); 
        }   
    }
    
}


