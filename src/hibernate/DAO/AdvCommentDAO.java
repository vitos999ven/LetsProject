package hibernate.DAO;

 
import java.sql.SQLException;
import java.util.List;
import hibernate.logic.AdvComment;
import hibernate.logic.Advertisement;
import hibernate.logic.User;
         
         
public interface AdvCommentDAO {
    public void addAdvComment(AdvComment advComment) throws SQLException;
    public void updateAdvCommentValue(Long id, String value) throws SQLException;
    public AdvComment getAdvCommentById(Long id) throws SQLException;
    public List<AdvComment> getAllAdvComments() throws SQLException;
    public List<AdvComment> getAllAdvComments(User user) throws SQLException;
    public List<AdvComment> getAllAdvComments(Advertisement advertisement) throws SQLException;
    public List<AdvComment> getAllAdvCommentsByLogin(String login) throws SQLException;
    public void deleteAdvCommentById(Long id) throws SQLException; 
    public void deleteAllAdvComments(Advertisement advertisement) throws SQLException;
    public void deleteAllAdvComments(User user) throws SQLException;
}
