package hibernate.DAO.Impl;


import hibernate.DAO.ResponseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hibernate.logic.Advertisement;
import hibernate.logic.Response;
import hibernate.logic.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import hibernate.util.Factory;
import hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;


public class ResponseDAOImpl implements ResponseDAO{
    
    @Override
    public void addResponse(Response response) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM Responses r " 
              + "WHERE r.user = :user AND r.advertisement = :adv_id "
                ).addEntity(Response.class).setString("user", response.getUser())
                .setLong("adv_id", response.getAdvertisement());
            session.getTransaction().commit();
            Advertisement advertisement = Factory.getInstance().getAdvertisementDAO().getAdvertisementById(response.getAdvertisement());
            if ((query.list().isEmpty()) && (!advertisement.getUser().equals(response.getUser()))){  
                session.beginTransaction();
                session.save(response);
                session.getTransaction().commit();
            }
        }catch(ConstraintViolationException e){
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nERROR! FOREIGN KEY IS WRONG!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }catch(NullPointerException e){
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nNullPointerException!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    }
   

    @Override
    public void changeResponseConfirmation(Long id) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Response response = Factory.getInstance().getResponseDAO().getResponseById(id);
            if (response == null) return;
            session.beginTransaction();
            response.setConfirmation(!response.getConfirmation());
            session.update(response);
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    }
   
    
    @Override
    public Response getResponseById(Long id) throws SQLException{
        Session session = null;
        Response response = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * "
              + "FROM responses r "
              + "WHERE r.id = :id"
                ).addEntity(Response.class).setLong("id", id);
            if(!query.list().isEmpty()) {
                response = (Response)query.list().get(0);
            }
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return response;
    }
    
    
    @Override
    public List<Response> getAllResponses() throws SQLException{
        Session session = null;
        List<Response> responses = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            responses = session.createCriteria(Response.class).list();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }   
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return responses;
    }
    
    
    @Override
      public List<Response> getAllResponses(User user) throws SQLException{
        Session session = null;
        List<Response> responses = new ArrayList<>();  
        if (user == null) return responses;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM responses r " 
              + "WHERE r.user = :login " 
              + "ORDER by r.date desc"
                ).addEntity(Response.class).setString("login", user.getLogin());
            if(!query.list().isEmpty()) {
                responses = (List<Response>)query.list();
            }
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return responses;
    }
    
      
    @Override
    public List<Response> getAllResponses(Advertisement advertisement) throws SQLException{
       Session session = null;
       List<Response> responses = new ArrayList<>();
       if (advertisement == null) return responses;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM responses r " 
              + "WHERE r.advertisement = :adv_id " 
              + "ORDER by r.date desc"
                ).addEntity(Response.class).setLong("adv_id", advertisement.getAdvId());
            if(!query.list().isEmpty()) {
                responses = (List<Response>)query.list();
            }
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
       return responses;
    }
    
    
    @Override
    public List<Response> getAllResponsesByLogin(String login) throws SQLException{
        Session session = null;
        List<Response> responses = new ArrayList<>();        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM responses r " 
              + "WHERE r.user = :login " 
              + "ORDER by r.date desc"
                ).addEntity(Response.class).setString("login", login);
             
            if(!query.list().isEmpty()) {
                responses = (List<Response>)query.list();
            }
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return responses;
    }

    
    @Override
    public void deleteResponseById(Long id) throws SQLException{
        Session session = null;
        Response response = getResponseById(id);
        if (response == null) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(response);
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    } 
     
    
    @Override
    public void deleteAllResponses(Advertisement advertisement) throws SQLException{
        Session session = null;
        List<Response> responses = getAllResponses(advertisement);
        if (responses.isEmpty()) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Response response : responses) {
                session.delete(response);
            }
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    } 
     
    
    @Override
    public void deleteAllResponses(User user) throws SQLException{
        Session session = null;
        List<Response> responses = getAllResponses(user);
        if (responses.isEmpty()) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Response response : responses) {
                session.delete(response);
            }
            session.getTransaction().commit();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
    } 
    
}
