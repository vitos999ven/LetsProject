package hibernate.logic;




public enum AccessEnum {
    USER(0), ADMIN(1), GOD(2);
    
    private final byte value;
    
    AccessEnum(int value){
        this.value = ((value > 2) || (value < 0))? (byte)0 : (byte)value;
    }
    
    public byte getValue(){
        return value;
    } 
}
