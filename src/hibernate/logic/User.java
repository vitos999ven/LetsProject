package hibernate.logic;


import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Витос
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="login")
    private String login;
    @Column(name="password")
    private String password;
    @Column(name="access")
    private Byte access;
    @Column(name="sex")
    private Byte sex;
    @Column(name="birthday")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthday;
    @Column(name="city")
    private String city;
    @Column(name="about")
    private String about;
    @Column(name="avatar")
    private Long avatar;
    @Column(name="last_online_time")
    private Long lastOnlineTime;
    @Column(name="status")
    private Byte status;

    
    public User(){}
    
    public User(String login, String password, Byte access, Byte sex, Calendar birthday, String city, String about, Long avatar){
        this.login = login;
        this.password = password;
        this.access = access;
        this.sex = sex;
        this.birthday = (Calendar)birthday.clone();
        this.city = city;
        this.about = about;
        this.avatar = avatar;
        this.lastOnlineTime = (long) 0;
        this.status = (byte) 0;
    }
    
    public User(String login, String password, Byte access, Byte sex, Calendar birthday, String city, String about){
        this.login = login;
        this.password = password;
        this.access = access;
        this.sex = sex;
        this.birthday = (Calendar)birthday.clone();
        this.city = city;
        this.about = about;
        this.avatar = new Long(0);
        this.lastOnlineTime = (long) 0;
        this.status = (byte) 0;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setAccess(Byte access){
        this.access = ((access>1)||(access<0))? (byte)0: access;
    }
    
    public void setAccess(AccessEnum access){
        if (access != AccessEnum.GOD) this.access = access.getValue();
    }
    
    public Byte getAccess(){
        return this.access;
    }
    
    public void setSex(Byte sex){
        this.sex = ((sex>2)||(sex<0))? (byte)0: sex;
    }
    
    public Byte getSex(){
        return this.sex;
    }
    
    public void setBirthday(Calendar birthday){
        this.birthday = (Calendar)birthday.clone();
    }
    
    public Calendar getBirthday(){
        return (Calendar)this.birthday.clone();
    }
    
    public int getAge(){
        Calendar Today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(birthday.getTimeInMillis());
        dob.add(Calendar.DAY_OF_MONTH, -1);
        
        int age = Today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (Today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
            age--;
                
        return age;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public void setAbout(String about){
        this.about = about;
    }
    
    public String getAbout(){
        return this.about;
    }
    
    public void setAvatar(Long avatar){
        this.avatar = avatar;
    }
    
    public Long getAvatar(){
        return this.avatar;
    }
    
    public void setLastOnlineTime(Long time){
        this.lastOnlineTime = time;
    }
    
    public Long getLastOnlineTime(){
        return this.lastOnlineTime;
    }
    
    public void setStatus(Byte status){
        this.status = ((status>2)||(status<0))? (byte)0: status;
    }
    
     public void setStatus(UserStatusEnum status){
        this.status = status.getValue();
    }
    
    public Byte getStatus(){
        return this.status;
    }
    
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof User){
            User other = (User)obj;
            return ((this.login.equals(other.login)) && (this.password.equals(other.password)) 
                    && (this.access.equals(other.access)) && (this.sex.equals(other.sex))
                    && (this.birthday.getTimeInMillis() == other.birthday.getTimeInMillis()) && (this.city.equals(other.city))
                    && (this.about.equals(other.about)) && (this.avatar.equals(other.avatar))
                    && (this.lastOnlineTime.equals(other.lastOnlineTime)) && (this.status.equals(other.status)));
        }
        return false;
    }
    
}
