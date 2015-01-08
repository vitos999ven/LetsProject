

package hibernate.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="adv_id", unique = true, nullable = false)
    private Long adv_id;
    @Column(name="date")
    private Long date;
    @Column(name="user")
    private String user;
    @Column(name="topic")
    private String topic;
    @Column(name="value")
    private String value;
    @Column(name="city")
    private String city;
    @Column(name="field")
    private Byte field;
    @Column(name="type")
    private Byte type;
    @Column(name="sex")
    private Byte sex;
    @Column(name="age_from")
    private Byte age_from;
    @Column(name="age_to")
    private Byte age_to;
    
    public Advertisement(){}
    
    public Advertisement(Long id, Long date, String user, String topic, String value, String city, AdvertisementFieldEnum field, AdvertisementTypeEnum type, Byte sex, Byte age_from, Byte age_to){
      this.adv_id = id;
      this.date = date;
      this.user = user;
      this.topic = topic;
      this.value = value;
      this.city = city;
      this.field = (byte)field.getValue();
      this.type = (byte)type.getValue();
      this.sex = sex;
      if (age_from > age_to){
          age_from =(byte)(age_from + age_to) ;
          age_to =(byte)(age_from - age_to) ;
          age_from =(byte)(age_from - age_to) ; 
      }
      this.age_from = age_from;
      this.age_to = age_to;
    }
    
    public void setAdvId(Long id){
        this.adv_id = id;
    }
    
    public Long getAdvId(){
        return this.adv_id;
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
    
    public void setTopic(String topic){
        this.topic = topic;
    }
    
    public String getTopic(){
        return this.topic;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
    
     public void setCity(String city){
        this.city = city;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public void setField(Byte field){
        this.field = field;
    }
    public void setField(AdvertisementFieldEnum field){
        this.field = (byte)field.getValue();
    }
    
    public void setField(String field){
        this.field = (byte)AdvertisementFieldEnum.valueOf(field).getValue();
    }
    
    public Byte getField(){
        return this.field;
    }
    
    public void setType(Byte type){
        this.type = type;
    }
    
    public void setType(AdvertisementTypeEnum type){
        this.type = (byte)type.getValue();
    }
    
    public void setType(String type){
        this.type = (byte)AdvertisementTypeEnum.valueOf(type).getValue();
    }
    
    public Byte  getType(){
        return this.type;
    }
    
    public void setSex(Byte sex){
        this.sex = ((sex>2)||(sex<0))? (byte)0: sex;
    }
    
    public Byte getSex(){
        return this.sex;
    }
    
     public void setAgeFrom(Byte age){
        this.age_from = age;
    }
    
    public Byte getAgeFrom(){
        return this.age_from;
    }
    
    public void setAgeTo(Byte age){
        this.age_to = age;
    }
    
    public Byte getAgeTo(){
        return this.age_to;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Advertisement){
            Advertisement other = (Advertisement)obj;
            return ((this.adv_id.equals(other.adv_id)) && (this.age_from.equals(other.age_from)) 
                    && (this.age_to.equals(other.age_to)) && (this.city.equals(other.city))
                    && (this.date.equals(other.date)) && (this.field.equals(other.field))
                    && (this.sex.equals(other.sex)) && (this.topic.equals(other.topic))
                    && (this.type.equals(other.type)) && (this.user.equals(other.user))
                    && (this.value.equals(other.value)));
        }
        
        return false;
    }
}
