package hibernate.logic;




public enum AdvertisementFieldEnum {
    ALL(0), SPORTS(1), TRAVEL(2), DATE(3);
    
    private final int value;
    
    AdvertisementFieldEnum(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
