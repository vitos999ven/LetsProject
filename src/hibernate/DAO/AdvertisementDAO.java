package hibernate.DAO;


import java.sql.SQLException;
import java.util.List;
import hibernate.logic.Advertisement;
import hibernate.logic.AdvertisementFieldEnum;
import hibernate.logic.AdvertisementTypeEnum;
import hibernate.logic.User;
         
         
public interface AdvertisementDAO {
    
    public void addAdvertisement(Advertisement advertisement) throws SQLException;
    public void updateAdvertisement(Advertisement advertisement) throws SQLException;
    public Advertisement getAdvertisementById(Long id) throws SQLException;
    public List<Advertisement> getAllAdvertisement() throws SQLException;
    public List<Advertisement> getAllAdvertisement(User user) throws SQLException;
    public List<Advertisement> getAllAdvertisement(Long date_from, Long date_to, String city, AdvertisementFieldEnum field, AdvertisementTypeEnum type, User user) throws SQLException;
    public List<Advertisement> getAllAdvertisementByLogin(String login) throws SQLException;
    public void deleteAdvertisementById(Long id) throws SQLException; 
    public void deleteAllAdvertisements(User user) throws SQLException;
    
}
