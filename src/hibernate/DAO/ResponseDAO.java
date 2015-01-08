package hibernate.DAO;


import java.sql.SQLException;
import java.util.List;
import hibernate.logic.Advertisement;
import hibernate.logic.Response;
import hibernate.logic.User;
         
         
public interface ResponseDAO {
    public void addResponse(Response response) throws SQLException;
    public void changeResponseConfirmation(Long id) throws SQLException;
    public Response getResponseById(Long id) throws SQLException;
    public List<Response> getAllResponses() throws SQLException;
    public List<Response> getAllResponses(User user) throws SQLException;
    public List<Response> getAllResponses(Advertisement advertisement) throws SQLException;
    public List<Response> getAllResponsesByLogin(String login) throws SQLException;
    public void deleteResponseById(Long id) throws SQLException; 
    public void deleteAllResponses(Advertisement advertisement) throws SQLException;
    public void deleteAllResponses(User user) throws SQLException;
}
