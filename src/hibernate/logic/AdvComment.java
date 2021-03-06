

package hibernate.logic;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="advcomments")
public class AdvComment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique = true, nullable = false)
    private Long id;
    @Column(name="date")
    private Long date;
    @Column(name="user") 
    private String user;
    @Column(name="advertisement")
    private Long advertisement;
    @Column(name="value")
    private String value;
    
    
    public AdvComment(){}
    
    public AdvComment(Long id, Long date, String user, Long advertisement, String value){
      this.id = id;
      this.date = date;
      this.user = user;
      this.advertisement = advertisement;
      this.value = value;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public void setDate(Long date){
       
        this.date = date;
    }
    
    public Long getDate(){
        return this.date;
    }
    
    public void setUser(String id){
        this.user = id;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public void setAdvertisement(Long advertisement){
        this.advertisement = advertisement;
    }
    
    public Long getAdvertisement(){
        return this.advertisement;
    }    
    
    public void setValue(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof AdvComment){
            AdvComment other = (AdvComment)obj;
            return ((this.advertisement.equals(other.advertisement)) && (this.value.equals(other.value)) 
                    && (this.date.equals(other.date)) && (this.id.equals(other.id))
                    && (this.user.equals(other.user)));
        }
        
        return false;
    }
}
