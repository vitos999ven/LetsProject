package service;

import hibernate.logic.SexEnum;
import java.util.Calendar;



public interface UsersService {
    public String getUserByJson(String user, long lastPhotoId, int count, String cookieUser);
    public String getUsersByJson(String search, String city, SexEnum sex, int ageFrom, int ageTo, String lastLogin, int count, String cookieUser);
    public String getUsersByJson(String search, String lastLogin, boolean next, int count);
    public boolean checkLoginUnique(String login);
    public boolean addNewUser(String login, String password, byte sex, Calendar birthday, String city, String about);
    public boolean checkPassword(String login, String password);
    public Long getUserAvatarId(String login);
}
