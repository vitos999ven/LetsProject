package hibernate.logic;




public enum AdvertisementTypeEnum  {
    BOTH(0),SINGLE(1),GROUP(2);
    
    private final int value;
    
    AdvertisementTypeEnum(int value){
        this.value = ((value > 2) || (value < 0))? (byte)0 : (byte)value;
    }
    
    public int getValue(){
        return value;
    }
}
