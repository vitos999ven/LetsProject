package hibernate.DAO.Impl;


import hibernate.DAO.AdvertisementDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hibernate.logic.Advertisement;
import hibernate.logic.AdvertisementFieldEnum;
import hibernate.logic.AdvertisementTypeEnum;
import hibernate.logic.User;
import org.hibernate.Query;
import org.hibernate.Session;
import hibernate.util.Factory;
import hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;


public class AdvertisementDAOImpl implements AdvertisementDAO{
    
    @Override
    public void addAdvertisement(Advertisement advertisement) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(advertisement);
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
    public void updateAdvertisement(Advertisement advertisement) throws SQLException{
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(advertisement);
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
    public Advertisement getAdvertisementById(Long id) throws SQLException{
        Session session = null;
        Advertisement advertisement = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * "
              + "FROM advertisement a "
              + "WHERE a.adv_id = :id"
                ).addEntity(Advertisement.class).setLong("id", id);
            if (!query.list().isEmpty()){
                advertisement = (Advertisement)query.list().get(0);
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
        return advertisement;
    }
    
    
    @Override
    public List<Advertisement> getAllAdvertisement() throws SQLException{
        Session session = null;
        List<Advertisement> advertisement = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            advertisement = session.createCriteria(Advertisement.class).list();
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }  
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return advertisement;
    }
    
    
    @Override
    public List<Advertisement> getAllAdvertisement(User user) throws SQLException{
        Session session = null;
        List<Advertisement> advertisement = new ArrayList<>(); 
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM Advertisement a " 
              + "WHERE a.user = :login " 
              + "ORDER BY a.date DESC"
                ).addEntity(Advertisement.class).setString("login", user.getLogin());
             
            if (!query.list().isEmpty()){
                advertisement = (List<Advertisement>)query.list();
            }
            session.getTransaction().commit();
        }catch(NullPointerException e){
            System.out.println("\n!!!!!!!!!!!!!!!!!\nWRONG USER LOGIN!\n!!!!!!!!!!!!!!!!!");
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return advertisement;
    }
    
     
    @Override
    public List<Advertisement> getAllAdvertisement(Long date_from, Long date_to, String city, AdvertisementFieldEnum field, AdvertisementTypeEnum type, User user) throws SQLException{
        date_from = (date_from >= 0)? date_from : (long) 0; 
        date_to = (date_to >= 0)? date_to : Long.MAX_VALUE; 
        if (date_to < date_from) {
            date_to = date_from;
        }
        String cityQuery = "",fieldQuery = "", typeQuery = "";
        if (!city.equals("")) cityQuery = "and (a.city = \'" + city + "\')\n ";
        if (field.getValue() != 0) fieldQuery = "and (a.field = \'" + field.getValue() + "\')\n ";
        if (type.getValue() != 0) typeQuery = "and (a.type = \'" + type.getValue() + "\')\n ";
        Session session = null;
        List<Advertisement> advertisement = new ArrayList<>();        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String login = user.getLogin();
            Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM Advertisement a " 
              + "WHERE (a.date >= :date_from) and (a.date <= :date_to) " 
              + cityQuery + fieldQuery + typeQuery 
              + "AND ((a.sex = :sex)or(a.sex = '0')) " 
              + "AND (a.age_from <= :age) " 
              + "AND (a.age_to >= :age) " 
              + "ORDER BY a.date DESC"
                ).addEntity(Advertisement.class).setLong("date_from", date_from)
                .setLong("date_to", date_to).setByte("sex", user.getSex()).setInteger("age", user.getAge());
             
            if (!query.list().isEmpty()){
                advertisement = (List<Advertisement>)query.list();
            }
            session.getTransaction().commit();
        }catch(NullPointerException e){
            System.out.println("\n!!!!!!!!!!!!!!!!!\nWRONG USER LOGIN!\n!!!!!!!!!!!!!!!!!");
        }catch(HibernateException e){
            System.out.println(e.toString());
            StackTraceElement[] el = e.getStackTrace();
                for (StackTraceElement el1 : el) {
                    System.out.println(el1);
                }
        }finally{
            if((session != null)&&(session.isOpen())) session.close();
        }
        return advertisement;
    }
    
    
    @Override
    public List<Advertisement> getAllAdvertisementByLogin(String login) throws SQLException{
        Session session = null;
        List<Advertisement> advertisement = new ArrayList<>();        
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
             Query query = session.createSQLQuery(
                "SELECT * " 
              + "FROM Advertisement a " 
              + "WHERE a.user = :login " 
              + "ORDER BY a.date DESC"
                ).addEntity(Advertisement.class).setString("login", login);
             
            if (!query.list().isEmpty()){
                advertisement = (List<Advertisement>)query.list();
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
        return advertisement;
    }

    
    @Override
    public void deleteAdvertisementById(Long id) throws SQLException{
        Session session = null;
        Advertisement advertisement = getAdvertisementById(id);
        if (advertisement == null) return;       
        Factory.getInstance().getResponseDAO().deleteAllResponses(advertisement);
        Factory.getInstance().getAdvCommentDAO().deleteAllAdvComments(advertisement);
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(advertisement);
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
    public void deleteAllAdvertisements(User user) throws SQLException{
        Session session = null;
        List<Advertisement> advertisement = getAllAdvertisement(user);
        if (advertisement.isEmpty()) return;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Advertisement adv : advertisement) {
                Factory.getInstance().getResponseDAO().deleteAllResponses(adv);
                Factory.getInstance().getAdvCommentDAO().deleteAllAdvComments(adv);
                session.delete(adv);
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
