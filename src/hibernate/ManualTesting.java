package hibernate;

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
import hibernate.logic.User;
import hibernate.util.Factory;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;


public class ManualTesting {
    public static void main(String[] args) throws SQLException, InterruptedException{     
        //changeAllUserToUserMessagesUnread();
      
        getAllPhotoLikes();
      System.exit(0);
    }
    
  
       
    
    
    //***********************************     
    //USER TESTS
    //***********************************
    public static void printUsersList(List<User> users) {
        System.out.println("\n*USERS*");
        for (User m : users) {
            System.out.println("*************************\nlogin: "+m.getLogin()+", password: "+m.getPassword()+", access: "+m.getAccess()+", sex: "+m.getSex()+", birthday: "+m.getBirthday().getTime()+", city: "+m.getCity()+", about: "+m.getAbout()+", avatar: "+m.getAvatar()+", AGE: "+m.getAge());
        }
        System.out.println("*************************\n");
    }
    
    private static void getAllUsers() throws SQLException{
        List<User> users = Factory.getInstance().getUserDAO().getUsersBySearchBeforeLogin("user", "user5", 11);   
        printUsersList(users);
    }
    private static void addUser() throws SQLException{
        Factory.getInstance().getUserDAO().addUser(new User("Loader", "ewirowevuxcviop339", AccessEnum.USER.getValue(), (byte)1, new GregorianCalendar(1976, 9, 17), "", "", (long) 1));
        getAllUsers();
    }
    
    private static void updateUser() throws SQLException{
        Factory.getInstance().getUserDAO().updateUser(new User("admin2", "0000", AccessEnum.ADMIN.getValue(), (byte)2, new GregorianCalendar(1976, 1, 23), "asd", "baba", (long) 1));
        getAllUsers();
    }
    
    private static void getUserByLogin() throws SQLException{
        User m = Factory.getInstance().getUserDAO().getUserByLogin("uio");
        System.out.println(m);
        if (m != null) System.out.println("*************************\nlogin: "+m.getLogin()+", password: "+m.getPassword()+", access: "+m.getAccess()+", sex: "+m.getSex()+", birthday: "+m.getBirthday()+", city: "+m.getCity()+", about: "+m.getAbout()+", avatar: "+m.getAvatar()+", AGE: "+m.getAge()+"\n*************************\n");
    }
      
    private static void deleteUserByLogin() throws SQLException{
        String login = "sdfsdfsd";
        System.out.print("\n\n*BEGORE DELETE OF "+login+" USER*   ");
        getAllUsers();
        getAllAdvertisement();
        getAllResponses();
        getAllMessages();
        getAllPhotoDescriptions();
        getAllPhotoLikes();
        Factory.getInstance().getUserDAO().deleteUserByLogin(login);
        System.out.print("\n\n*AFTER DELETE OF "+login+" USER*   ");
        getAllUsers();
        getAllAdvertisement();
        getAllResponses();
        getAllMessages();
        getAllPhotoDescriptions();
        getAllPhotoLikes();
    }
    
    
    
    
    
    //***********************************
    //ADVERTISEMENT TESTS
    //***********************************
    public static void printAdvertisementList(List<Advertisement> advertisement) {
        System.out.println("\n*ADVERTISEMENTS*");
        for (Advertisement m : advertisement) {
            System.out.println("*************************\n(adv_id) "+m.getAdvId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (topic) "+m.getTopic()+", (value) "+m.getValue()+", (city) "+m.getCity()+", (field) "+m.getField()+", (type) "+m.getType()+", | SEX: "+m.getSex()+", AGE_FROM: "+m.getAgeFrom()+", AGE_TO: "+m.getAgeTo());
        }
        System.out.println("*************************\n");
    }
    
    private static void addAdvertisement() throws SQLException{
        GregorianCalendar c = new GregorianCalendar();
        Advertisement m = new Advertisement(
                null,//adv_id
                c.getTimeInMillis(),//date
                "admin3",//user
                "Let's GO!",//topic
                "WASSUP!",//value
                "Chicago",//city 
                AdvertisementFieldEnum.DATE,//field
                AdvertisementTypeEnum.GROUP,//type
                (byte)1,//sex
                (byte)30,//age_from
                (byte)35);//age_to
        System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
                + "ADD NEW ADVERTISEMENT(adv_id) "
                +m.getAdvId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (topic) "+m.getTopic()+", (value) "+m.getValue()+", (city) "+m.getCity()+", (field) "+m.getField()+", (type) "+m.getType()+", | SEX: "+m.getSex()+", AGE_FROM: "+m.getAgeFrom()+", AGE_TO: "+m.getAgeTo()
                +"\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
       
        Factory.getInstance().getAdvertisementDAO().addAdvertisement(m);
        getAllAdvertisement();
    }
    
    private static void updateAdvertisement() throws SQLException{
        Advertisement m = new Advertisement(
                new Long(17),//adv_id
                new Long(58893),//date
                "god",//user
                "Hello",//topic
                "How are You?",//value
                "Los Angeles",//city 
                AdvertisementFieldEnum.DATE,//field
                AdvertisementTypeEnum.SINGLE,//type
                (byte)2,//sex
                (byte)25,//age_from
                (byte)30);//age_to
        System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
                + "UPDATE ADVERTISEMENT(adv_id) "
                +m.getAdvId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (topic) "+m.getTopic()+", (value) "+m.getValue()+", (city) "+m.getCity()+", (field) "+m.getField()+", (type) "+m.getType()+", | SEX: "+m.getSex()+", AGE_FROM: "+m.getAgeFrom()+", AGE_TO: "+m.getAgeTo()
                +"\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
       
        Factory.getInstance().getAdvertisementDAO().updateAdvertisement(m);
        getAllAdvertisement();
    }
    
    private static void getAdvertisementById() throws SQLException{
        Long id = (long) 17;
        getAllAdvertisement();
        Advertisement m = Factory.getInstance().getAdvertisementDAO().getAdvertisementById(id);
       try{ System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
                + "GET ADVERTISEMENT("+id+")  (adv_id) "
                +m.getAdvId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (topic) "+m.getTopic()+", (value) "+m.getValue()+", (city) "+m.getCity()+", (field) "+m.getField()+", (type) "+m.getType()+", | SEX: "+m.getSex()+", AGE_FROM: "+m.getAgeFrom()+", AGE_TO: "+m.getAgeTo()
                +"\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
       }catch(NullPointerException e){
           System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!MESSAGE WITH ID "+id+" EXISTS!!!!!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
       }
        }
    
    private static void getAllAdvertisement() throws SQLException{
        List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement();
        printAdvertisementList(advertisement);
    }
    
    private static void getAllAdvertisementByUser() throws SQLException{
        String login = "admin2";
        getAllAdvertisement();
         List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(Factory.getInstance().getUserDAO().getUserByLogin(login));
        System.out.println("\n*"+login+" USERs ADVERTISEMENT*");
        printAdvertisementList(advertisement);
    }
     
    private static void getAllAdvertisementForAuditory() throws SQLException{
        String login = "user2";
        getAllAdvertisement();
         User m = Factory.getInstance().getUserDAO().getUserByLogin(login);
        System.out.println("*************************\nUSER login: "+m.getLogin()+", password: "+m.getPassword()+", access: "+m.getAccess()+", sex: "+m.getSex()+", birthday: "+m.getBirthday()+", city: "+m.getCity()+", about: "+m.getAbout()+", avatar: "+m.getAvatar()+", AGE: "+m.getAge()+"\n*************************\n");
      
        List<Advertisement> advertisement = Factory.getInstance().getAdvertisementDAO().getAllAdvertisement(
                            new Long(10),
                            new Long(40000000),
                            "asd", 
                            AdvertisementFieldEnum.ALL,
                            AdvertisementTypeEnum.BOTH,
                            m);
        printAdvertisementList(advertisement);
    } 
    
    private static void deleteAdvettisementById() throws SQLException{
        Long id = (long) 17;
        System.out.print("\n*BEGORE DELETE OF "+id+" Advettisement AND RESPONSES*   ");
        getAllAdvertisement();
        getAllResponses();
        Factory.getInstance().getAdvertisementDAO().deleteAdvertisementById(id);
         System.out.print("\n*AFTER DELETE OF "+id+" Advettisement AND RESPONSES*   ");
        getAllAdvertisement();
        getAllResponses();
    }
     
     
    
    
    //***********************************
    //RESPONSES TESTS
    //***********************************
    public static void printResponsesList(List<Response> response) {
        System.out.println("\n*RESPONSES*");
        for (Response m : response) {
            System.out.println("*************************\n(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (advertisement) "+m.getAdvertisement()+", (confirned) "+m.getConfirmation());
        }
        System.out.println("*************************\n");
    }
    
    private static void addResponse() throws SQLException{
        GregorianCalendar c = new GregorianCalendar();
        
        Response m = new Response(
                null,//id
                c.getTimeInMillis(),//date
                "user2",//user
                new Long(2),//advertisement
                false);//confirned
       System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "NEW RESPONSE(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (advertisement) "+m.getAdvertisement()+", (confirned) "+m.getConfirmation()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        
        Factory.getInstance().getResponseDAO().addResponse(m);
        getAllResponses();
    }
    
    private static void changeResponseConfirmation() throws SQLException{
        GregorianCalendar c = new GregorianCalendar();
        System.out.println("*BEFORE UPDATE*");
        getAllResponses();
        Long id = (long) 15;
        System.out.println("*CHANGE "+id+" IDs CONFIRMATION*");
        Factory.getInstance().getResponseDAO().changeResponseConfirmation(id);
        getAllResponses();
    }
    
    private static void getResponseById() throws SQLException{
        Long id = (long) 15;
        getAllResponses();
        Response m = Factory.getInstance().getResponseDAO().getResponseById(id);
        System.out.println("\n*RESPONSE "+id+"*");
        if (m!= null) System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "RESPONSE(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (advertisement) "+m.getAdvertisement()+", (confirned) "+m.getConfirmation()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
      
    }
    
    private static void getAllResponses() throws SQLException{
        List<Response> response = Factory.getInstance().getResponseDAO().getAllResponses();
        printResponsesList(response);
    }
    
    private static void getAllResponsesByUser() throws SQLException{
        System.out.print("\n*ALL*");
        getAllResponses();
        String login = "user2";
        List<Response> response = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getUserDAO().getUserByLogin(login));
        System.out.print("\n*SELECTED FOR "+login+" USER*");
        printResponsesList(response);
    }
    
    private static void getAllResponsesByAdvertisement() throws SQLException{
        System.out.print("\n*ALL*");
        getAllResponses();
        Long id = (long) 17;
        List<Response> response = Factory.getInstance().getResponseDAO().getAllResponses(Factory.getInstance().getAdvertisementDAO().getAdvertisementById(id));
        System.out.print("\n*SELECTED FOR "+id+" ADVERTISEMENT*");
        printResponsesList(response);
    }
    
    private static void deleteResponseById() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllResponses();
        Long id = (long) 16;
        Factory.getInstance().getResponseDAO().deleteResponseById(id);
        System.out.print("\n*AFTER DELETE OF "+id+" RESPONSE*   ");
        getAllResponses();
    }
    
    private static void deleteAllResponsesByAdvertisement() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllResponses();
        Long id = (long) 1;
        Advertisement advertisement = Factory.getInstance().getAdvertisementDAO().getAdvertisementById(id);
        Factory.getInstance().getResponseDAO().deleteAllResponses(advertisement);
        System.out.print("\n*AFTER DELETE OF "+id+" ADVERTISEMENTs RESPONSES*   ");
        getAllResponses();
    }
    
    private static void deleteAllResponsesByUser() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllResponses();
        User user = Factory.getInstance().getUserDAO().getUserByLogin("god");
        Factory.getInstance().getResponseDAO().deleteAllResponses(user);
        System.out.print("\n*AFTER DELETE OF "+user.getLogin()+" USERs RESPONSES*   ");
        getAllResponses();
    }
    

    
    //***********************************
    //MESSAGES TESTS
    //***********************************
    public static void printMessagesList(List<Message> message) {
        System.out.println("\n*MESSAGES*");
        for (Message m : message) {
            System.out.println("*************************\n(id) "+m.getId()+", (date) "+m.getDate()+", (from_user) "+m.getFrom_id()+", (to_user) "+m.getTo_id()+", (value) "+m.getValue()+", (user) "+m.getUser()+", (unread) "+m.getUnread()+", (delete) "+m.getDeleted());
        }
        System.out.println("*************************\n");
    }
    
    private static void addMessage() throws SQLException{
        GregorianCalendar c = new GregorianCalendar();
        Factory.getInstance().getMessageDAO().addMessage(new Message(null, c.getTimeInMillis(), "admin1", "admin2", "sasdsddsd", null));
        getAllMessages();
    }
    
    private static void getMessageById() throws SQLException{
        Long id = (long) 11;
        getAllMessages();
        Message m = Factory.getInstance().getMessageDAO().getMessageById(id);
        System.out.println("\n*MESSAGE "+id+"*");
        if (m!= null) System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n(id) "+m.getId()+", (date) "+m.getDate()+", (from_user) "+m.getFrom_id()+", (to_user) "+m.getTo_id()+", (value) "+m.getValue()+", (user) "+m.getUser()+", (unread) "+m.getUnread()+", (deleted) "+m.getDeleted()+ "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
       
    }
    
    private static void getAllMessages() throws SQLException{
        List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages();
        printMessagesList(messages);
    }
    
    private static void getAllMessagesByUser() throws SQLException{
        System.out.print("\n*ALL*");
        getAllMessages();
        String login = "admin4"; 
        User user = Factory.getInstance().getUserDAO().getUserByLogin(login);
        List<Message> messages = Factory.getInstance().getMessageDAO().getAllMessages(user, false, true);
        System.out.print("\n*SELECTED FOR "+login+" USER*");
        printMessagesList(messages);
    }
    
    private static void getAllUserToUserMessages() throws SQLException{
        String firstLogin = "admin2", secondLogin = "god";
        getAllMessages();
        List<Message> messages = Factory.getInstance().getMessageDAO().getUserToUserMessagesBeforeId(firstLogin, secondLogin, -1, 20, true);
        System.out.println("\n*USERs "+firstLogin+" USER "+firstLogin+" toUSER "+secondLogin+" MESSAGES*");
        printMessagesList(messages);
    }
    
    private static void changeAllUserToUserMessagesUnread() throws SQLException{
        String firstLogin = "admin5", secondLogin = "admin2";
        User first = Factory.getInstance().getUserDAO().getUserByLogin(firstLogin);
        User second = Factory.getInstance().getUserDAO().getUserByLogin(secondLogin);
        Factory.getInstance().getMessageDAO().changeAllUserToUserMessagesUnread(first, second);
        getAllMessages();
        
        List<Message> messages = Factory.getInstance().getMessageDAO().getAllUserToUserMessages(first, second, true);
        System.out.println("\n*USERs "+firstLogin+" USER "+firstLogin+" toUSER "+secondLogin+" MESSAGES*");
        printMessagesList(messages);
    }
    
    private static void getDialogsOfAllUsersWhoTalkedWith() throws SQLException{
        String login = "god";
        User user = Factory.getInstance().getUserDAO().getUserByLogin(login);
        getAllMessages();
        List<Message> messages = Factory.getInstance().getMessageDAO().getDialogsOfAllUsersWhoTalkedWithBeforeId(user, -1, 11, false);
        System.out.println("\n*USERs "+login+" DIALOGS*\nUnread: "+ Factory.getInstance().getMessageDAO().getNumberOfUnreadDialogs(login));
        printMessagesList(messages);
    }
    
    private static void deleteMessageById() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllMessages();
        Long id = (long) 3;
        Factory.getInstance().getMessageDAO().deleteMessageById(id);
        System.out.print("\n*AFTER DELETE OF "+id+" MESSAGE*   ");
        getAllMessages();
    }
    
    private static void deleteAllMessagesByUser() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllMessages();
        String login = "god";
        User user = Factory.getInstance().getUserDAO().getUserByLogin(login);
        Factory.getInstance().getMessageDAO().deleteAllMessages(user);
        System.out.print("\n*AFTER DELETE OF "+login+" USERs Messages*   ");
        getAllMessages();
    }
    
    
    
    //***********************************     
    //ADVCOMMENTS TESTS
    //***********************************
    public static void printAdvCommentsList(List<AdvComment> advComments) {
        System.out.println("\n*ADVCOMMENTS*");
        for (AdvComment m : advComments) {
            System.out.println("*************************\n(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (advertisement) "+m.getAdvertisement()+", (value) "+m.getValue());
        }
        System.out.println("*************************\n");
    }
    
    private static void addAdvComment() throws SQLException{
        GregorianCalendar c = new GregorianCalendar();
        
        AdvComment m = new AdvComment(
                null,//id
                c.getTimeInMillis(),//date
                "god",//user
                new Long(11),//advertisement
                "daxsdfvvx");//confirned
       System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "NEW ADVCOMMENT(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (advertisement) "+m.getAdvertisement()+", (value) "+m.getValue()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        
        Factory.getInstance().getAdvCommentDAO().addAdvComment(m);
        getAllAdvComments();
    }
    
    private static void updateAdvCommentValue() throws SQLException{
        
        System.out.println("*BEFORE UPDATE*");
        getAllAdvComments();
        Long id = (long) 30;
        String value = "kkdf;dskf;s";
        System.out.println("* UPDATE ID = "+id+", VALUE = '"+value+"' *");
        Factory.getInstance().getAdvCommentDAO().updateAdvCommentValue(id, value);
        getAllAdvComments();
    }
    
    private static void getAdvCommentById() throws SQLException{
        Long id = (long) 20;
        getAllAdvComments();
        AdvComment m = Factory.getInstance().getAdvCommentDAO().getAdvCommentById(id);
        System.out.println("\n*ADVCOMMENT "+id+"*");
        if (m!= null) System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "ADVCOMMENT(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (advertisement) "+m.getAdvertisement()+", (value) "+m.getValue()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
    }
    
    private static void getAllAdvComments() throws SQLException{
        List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments();
        printAdvCommentsList(advComments);
    }
        
    private static void getAllAdvCommentsByUser() throws SQLException{
        System.out.print("\n*ALL*");
        getAllAdvComments();
        String login = "user60";
        List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getUserDAO().getUserByLogin(login));
        System.out.print("\n*SELECTED FOR "+login+" USER*");
        printAdvCommentsList(advComments);
    }      
    
    private static void getAllAdvCommentsByAdvertisement() throws SQLException{
        System.out.print("\n*ALL*");
        getAllAdvComments();
        long id = 19;
        List<AdvComment> advComments = Factory.getInstance().getAdvCommentDAO().getAllAdvComments(Factory.getInstance().getAdvertisementDAO().getAdvertisementById(id));
        System.out.print("\n*SELECTED FOR "+id+" Advertisement*");
        printAdvCommentsList(advComments);
    } 
    
    private static void deleteAdvCommentById() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllAdvComments();
        long id = 15;
        Factory.getInstance().getAdvCommentDAO().deleteAdvCommentById(id);
        System.out.print("\n*AFTER DELETE OF "+id+" COMMENT*   ");
        getAllAdvComments();
    }    
    
    private static void deleteAllAdvCommentsByAdvertisement() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllAdvComments();
        long id = 100;
        Advertisement advertisement = Factory.getInstance().getAdvertisementDAO().getAdvertisementById(id);
        Factory.getInstance().getAdvCommentDAO().deleteAllAdvComments(advertisement);
        System.out.print("\n*AFTER DELETE OF "+id+" ADVERTISEMENTs COMMENTS*   ");
        getAllAdvComments();
    }    
    
    private static void deleteAllAdvCommentsByUser() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllAdvComments();
        String login = "user1";
        User user = Factory.getInstance().getUserDAO().getUserByLogin(login);
        Factory.getInstance().getAdvCommentDAO().deleteAllAdvComments(user);
        System.out.print("\n*AFTER DELETE OF "+login+" USERs COMMENTS*   ");
        getAllAdvComments();
    }   
    
    
    //***********************************     
    //PHOTODESCRIPTION TESTS
    //***********************************
    public static void printPhotoDescriptionList(List<PhotoDescription> photoDescriptions) {
        System.out.println("\n*PhotoDESCRIPTIONS*");
        for (PhotoDescription m : photoDescriptions) {
            System.out.println("*************************\n(id) "+m.getId()+", (date) "+m.getDate()+", (user) "+m.getUser()+", (description) "+m.getDescription()+", (deleted) "+m.getDeleted());
        }
        System.out.println("*************************\n");
    }
    
    private static void addPhotoDescription() throws SQLException{
        PhotoDescription m = new PhotoDescription(
                null,//id
                "admin2",//user
                "sdfdsfv");//description
       System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "NEW DESCRIPTION(id) "+m.getId()+", (user) "+m.getUser()+", (description) "+m.getDescription()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        
        Factory.getInstance().getPhotoDescriptionDAO().addPhotoDescription(m);
        getAllPhotoDescriptions();
    }
        
    private static void updatePhotoDescription() throws SQLException{
        
        System.out.println("*BEFORE UPDATE*");
        getAllPhotoDescriptions();
        Long id = (long) 10;
        String value = "kkdf;dskf;s";
        System.out.println("* UPDATE ID = "+id+", DESCRIPTION = '"+value+"' *");
        Factory.getInstance().getPhotoDescriptionDAO().updatePhotoDescription(id, value);
        getAllPhotoDescriptions();
    }
       
    private static void getPhotoDescriptionById() throws SQLException{
        Long id = (long) 8;
        getAllPhotoDescriptions();
        PhotoDescription m = Factory.getInstance().getPhotoDescriptionDAO().getPhotoDescriptionById(id);
        System.out.println("\n*ADVCOMMENT "+id+"*");
        if (m!= null)  System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "DESCRIPTION(id) "+m.getId()+", (user) "+m.getUser()+", (description) "+m.getDescription()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
       }
    
   
    
    private static void getAllPhotoDescriptions() throws SQLException{
        List<PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions();
        printPhotoDescriptionList(photoDescriptions);
    }
    
    private static void getAllPhotoDescriptionsByLogin() throws SQLException{
        getAllPhotoDescriptions();
        List<PhotoDescription> photoDescriptions = Factory.getInstance().getPhotoDescriptionDAO().getAllPhotoDescriptions("testUser1", true);
        printPhotoDescriptionList(photoDescriptions);
    }
    
    private static void deletePhotoDescriptionById() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllPhotoDescriptions();
        long id = 3;
        Factory.getInstance().getPhotoDescriptionDAO().deletePhotoDescriptionById(id);
        System.out.print("\n*AFTER DELETE OF "+id+" DESCRIPTION*   ");
        getAllPhotoDescriptions();
    } 
    
    
    private static void deletePhotoDescriptionsByUser() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllPhotoDescriptions();
        String user = "testUser1";
        Factory.getInstance().getPhotoDescriptionDAO().deleteAllPhotoDescriptions(user);
        System.out.print("\n*AFTER DELETE OF "+user+" USERs photo DESCRIPTIONs*   ");
        getAllPhotoDescriptions();
    } 
    
    
    
    //***********************************     
    //PHOTOLIKES TESTS
    //***********************************
    public static void printPhotoLikeList(List<PhotoLike> photoLikes) {
        System.out.println("\n*PhotoLikes*");
        for (PhotoLike m : photoLikes) {
            System.out.println("*************************\n(id) "+m.getId()+", (date) "+m.getDate()+", (photo_id) "+m.getPhotoId()+", (user_from) "+m.getUserFrom() + ", (deleted) "+m.getDeleted());
        }
        System.out.println("*************************\n");
      
    }
    
    private static void addPhotoLike() throws SQLException{
        
        PhotoLike m = new PhotoLike(
                null,//id
                new Long(10),//photo_id
                "admin5");//user_from
       System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "NEW PhotoLIKE(id) "+m.getId()+", (photo_id) "+m.getPhotoId()+", (user_from) "+m.getUserFrom()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        
        Factory.getInstance().getPhotoLikeDAO().addPhotoLike(m);
        getAllPhotoLikes();
    }
    
    public static void getPhotoLikeById() throws SQLException{
        Long id = (long) 10;
        getAllPhotoLikes();
        PhotoLike m = Factory.getInstance().getPhotoLikeDAO().getPhotoLikeById(id);
        System.out.println("\n*PhotoLIKE "+id+"*");
        if (m!= null)  System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "PhotoLIKE(id) "+m.getId()+", (photo_id) "+m.getPhotoId()+", (user_from) "+m.getUserFrom()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        }
    
    public static void getPhotoLikeByPhotoAndUser() throws SQLException{
        Long id = (long) 9;
        String user_from = "admin1";
        getAllPhotoLikes();
        PhotoLike m = Factory.getInstance().getPhotoLikeDAO().getPhotoLikeById(id);
        System.out.println("\n*"+user_from+" USERs PhotoLIKE FOR "+id+" ID PHOTO*");
        if (m!= null)  System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "PhotoLIKE(id) "+m.getId()+", (photo_id) "+m.getPhotoId()+", (user_from) "+m.getUserFrom()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        }
    
    private static void getAllPhotoLikes() throws SQLException{
        List<PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikesBeforeLogin((long) 179, "user26", 11, false);  
        printPhotoLikeList(photoLikes);
    }  
    
    private static void getAllPhotoLikesAndDescriptionsByUser() throws SQLException{
        getAllPhotoLikes();
        List<PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes("admin5", true);
        printPhotoLikeList(photoLikes);
    } 
    
    private static void getAllPhotoLikesByPhotoId() throws SQLException{
        getAllPhotoLikes();
        List<PhotoLike> photoLikes = Factory.getInstance().getPhotoLikeDAO().getAllPhotoLikes(new Long(9), true);
        printPhotoLikeList(photoLikes);
    } 
    
    private static void deletePhotoLikeById() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllPhotoLikes();
        long id = 3;
        Factory.getInstance().getPhotoLikeDAO().deletePhotoLikeById(id);
        System.out.print("\n*AFTER DELETE OF "+id+" Like*   ");
        getAllPhotoLikes();
    } 
    
    private static void deletePhotoLike() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllPhotoLikes();
        long id = 5;
        String str = "god";
        Factory.getInstance().getPhotoLikeDAO().deletePhotoLike(id, str);
        System.out.print("\n*AFTER DELETE OF "+id+" "+str+" Users Like*   ");
        getAllPhotoLikes();
    }    
    
    private static void deleteAllPhotoLikesByUser() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllPhotoLikes();
        String user = "user1";
        Factory.getInstance().getPhotoLikeDAO().deleteAllPhotoLikes(user);
        System.out.print("\n*AFTER DELETE OF "+user+" USERs photo Likes*   ");
        getAllPhotoLikes();
    }
    

    
    //***********************************     
    //PHOTOCOMMENTS TESTS
    //***********************************
    public static void printPhotoCommentList(List<PhotoComment> photoComments) {
        System.out.println("\n*PhotoCOMMENTS*");
        for (PhotoComment m : photoComments) {
            System.out.println("*************************\n(id) "+m.getId()+", (photo_id) "+m.getPhotoId()+", (date) "+m.getDate()+", (user_from) "+m.getUserFrom()+", (value) "+m.getValue());
        }
        System.out.println("*************************\n");
    }
    
    private static void addPhotoComment() throws SQLException{
        GregorianCalendar c = new GregorianCalendar();
        
        PhotoComment m = new PhotoComment(
                null,//id
                new Long(84),//photo_id
                c.getTimeInMillis(),//date
                "admin8",//user_from
                "рsолxcxzc");//value
       System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "NEW PhotoCOMMENT(id) "+m.getId()+", (photo_id) "+m.getPhotoId()+", (date) "+m.getDate()+", (user_from) "+m.getUserFrom()+", (value) "+m.getValue()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
        
        Factory.getInstance().getPhotoCommentDAO().addPhotoComment(m);
        getAllPhotoComments();
    }    
    
    public static void getPhotoCommentById() throws SQLException{
        Long id = (long) 3;
        getAllPhotoComments();
        PhotoComment m = Factory.getInstance().getPhotoCommentDAO().getPhotoCommentById(id);
        System.out.println("\n*PhotoComment "+id+"*");
        if (m!= null)  System.out.println("?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n"
               + "PhotoCOMMENT(id) "+m.getId()+", (photo_id) "+m.getPhotoId()+", (date) "+m.getDate()+", (user_from) "+m.getUserFrom()+", (value) "+m.getValue()+
               "\n?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?&?\n");
       } 
    
    private static void getAllPhotoComments() throws SQLException{
        List<PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments();
        printPhotoCommentList(photoComments);
    } 
    
    private static void getAllPhotoCommentsByUser() throws SQLException{
        getAllPhotoComments() ;
        String user = "admin5";
        List<PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(user, true);
        System.out.println("**"+user+" USERs PHOTOCOMMENTS**");
        printPhotoCommentList(photoComments);
    }    
    
    private static void getAllPhotoCommentsByPhoto() throws SQLException{
        getAllPhotoComments() ;
        Long id = (long) 99;
        List<PhotoComment> photoComments = Factory.getInstance().getPhotoCommentDAO().getAllPhotoComments(id, true);
        System.out.println("**"+id+" ID PHOTOs COMMENTS**");
        printPhotoCommentList(photoComments);
    } 
    
    private static void deletePhotoCommentById() throws SQLException{
        System.out.print("\n*BEFORE DELETE*");
        getAllPhotoComments();
        long id = 3;
        Factory.getInstance().getPhotoCommentDAO().deletePhotoCommentById(id);
        System.out.print("\n*AFTER DELETE OF "+id+" PhotoCOMMENT*   ");
        getAllPhotoComments();
    } 
    
    
}
