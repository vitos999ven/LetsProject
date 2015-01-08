package hibernate.junit;

import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

class TestListener extends RunListener {
    @Override
    public void testStarted(Description desc){
        System.out.println("\nSTARTED: " + desc.getDisplayName());
    }
    
     @Override
    public void testFinished(Description desc){
        System.out.println("FINISHED: " + desc.getDisplayName()+"\n");
    }
    
    @Override
    public void testFailure(Failure fail){
        Messages.addMessage("FAILED: " + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
        System.out.println("FAILED: " + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
        
    }
}

class Messages{
   private static ArrayList<String> messages;
   private static int num = 0;
   
   public static synchronized void addMessage(String str){
       num++;
       messages.add(str);
   }
   
   public static void InitMessages(){
       messages = new ArrayList<String>();
       
   }
   
   public static List<String> getMessages(){
       List<String> m = messages;
       messages = new ArrayList<String>();
       return m;
   }
   
    public static int getNumberOfFailures(){
       int n = num;
       num = 0;
       return n;
   }
}
    