package hibernate.DAO.Impl;

import hibernate.DAO.AdvCommentDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hibernate.logic.AdvComment;
import hibernate.logic.Advertisement;
import hibernate.logic.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import hibernate.util.Factory;
import hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;


public class AdvCommentDAOImpl implements AdvCommentDAO{
    
    @Override
    public void addAdvComment(AdvComment advComment) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(advComment);
            session.getTransaction().commit();
        }catch(ConstraintViolationException e){
            System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nERROR! FOREIGN KEY IS WRONG!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
    public void updateAdvCommentValue(Long id, String value) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            AdvComment advComment = Factory.getInstance().getAdvCommentDAO().getAdvCommentById(id);
            if (advComment == null) return; 
            session.beginTransaction();
            advComment.setValue(value);
            session.update(advComment);
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
    public AdvComment getAdvCommentById(Long id) throws SQLException{
        Session session = null;
        AdvComment advComment = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * "
              + "FROM advcomments c "
              + "WHERE c.id = :id"
                ).addEntity(AdvComment.class).setLong("id", id);
            if(!query.list().isEmpty()){
                advComment = (AdvComment)query.list().get(0);
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
        return advComment;
    }
    
    
    @Override
    public List<AdvComment> getAllAdvComments() throws SQLException{
        Session session = null;
        List<AdvComment> advComments = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            advComments = session.createCriteria(AdvComment.class).list();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }   
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return advComments;
    }
    
    
    @Override
    public List<AdvComment> getAllAdvComments(User user) throws SQLException{
        Session session = null;
        List<AdvComment> advComments = new ArrayList<>();  
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM advcomments c " 
              + "WHERE c.user = :login " 
              + "ORDER BY c.date DESC"
                ).addEntity(AdvComment.class).setString("login", user.getLogin());
             
            if(!query.list().isEmpty()){
                advComments = (List<AdvComment>)query.list();
            }
            session.getTransaction().commit();
        }catch(NullPointerException e){
            System.out.println("!!!!!USER EXISTED!!!!!");
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return advComments;
    }
    
    
    @Override
    public List<AdvComment> getAllAdvComments(Advertisement advertisement) throws SQLException{
        Session session = null;
        List<AdvComment> advComments = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM advcomments c " 
              + "WHERE c.advertisement = :adv_id " 
              + "ORDER BY c.date DESC"
                ).addEntity(AdvComment.class).setLong("adv_id", advertisement.getAdvId());
             
            if(!query.list().isEmpty()){
                advComments = (List<AdvComment>)query.list();
            }    
            session.getTransaction().commit();
        }catch(NullPointerException e){
            System.out.println("!!!!!ADVERTISEMENT EXISTED!!!!!");
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
       return advComments;
    }
    
    
    @Override
    public List<AdvComment> getAllAdvCommentsByLogin(String login) throws SQLException{
        Session session = null;
        List<AdvComment> advComments = new ArrayList<>();        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM advcomments c " 
              + "WHERE c.user = :login " 
              + "ORDER BY c.date DESC"
                ).addEntity(AdvComment.class).setString("login", login);
             
            if(!query.list().isEmpty()){
                advComments = (List<AdvComment>)query.list();
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
        return advComments;
    }

    
    @Override
    public void deleteAdvCommentById(Long id) throws SQLException{
        Session session = null;
        AdvComment advComment = getAdvCommentById(id);
        if (advComment == null) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(advComment);
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
    public void deleteAllAdvComments(Advertisement advertisement) throws SQLException{
        Session session = null;
        List<AdvComment> advComments = getAllAdvComments(advertisement);
        if (advComments.isEmpty()) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (AdvComment advComment : advComments) {
                session.delete(advComment);
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
    public void deleteAllAdvComments(User user) throws SQLException{
        Session session = null;
        List<AdvComment> advComments = getAllAdvComments(user);
        if (advComments.isEmpty()) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (AdvComment advComment : advComments) {
                session.delete(advComment);
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
