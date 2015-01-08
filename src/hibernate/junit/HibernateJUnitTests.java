package hibernate.junit;


import java.io.*;
import java.util.List;
import org.junit.runner.JUnitCore;


public class HibernateJUnitTests {
    
    private static int sum = 0, num = 0;
    private static PrintWriter writer;
    
    public static void main(String[] args) {
        try{
            File file = new File("src/hibernate/junit/Failures.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            writer = new PrintWriter(file);
        }catch(IOException e){
            System.out.println("File is not found!");
            System.exit(0);
        }
        Messages.InitMessages();
        JUnitCore core = new JUnitCore();
        core.addListener(new TestListener());
        
        System.out.println("\n*****************\n"
                           + "***USERs TESTS***\n"
                           + "*****************\n");
        core.run(UserDAOtests.class);
        printMessages("USER");
        
        System.out.println("\n**************************\n"
                           + "***ADVERTISEMENTs TESTS***\n"
                           + "**************************\n");
        core.run(AdvertisementDAOtests.class);
        printMessages("ADVERTISEMENT");
        
        System.out.println("\n*********************\n"
                           + "***RESPONSEs TESTS***\n"
                           + "*********************\n");
        core.run(ResponseDAOtests.class);
        printMessages("RESPONSE");
        
        System.out.println("\n***********************\n"
                           + "***AdvCOMMENTs TESTS***\n"
                           + "***********************\n");
        core.run(AdvCommentDAOtests.class);
        printMessages("AdvCOMMENT");
                
        System.out.println("\n********************\n"
                           + "***MESSAGEs TESTS***\n"
                           + "********************\n");
        core.run(MessageDAOtests.class);
        printMessages("MESSAGE");
        
        System.out.println("\n*****************************\n"
                           + "***PHOTODESCRIPTIONs TESTS***\n"
                           + "*****************************\n");
        core.run(PhotoDescriptionDAOtests.class);
        printMessages("PHOTODESCRIPTION");
        
        System.out.println("\n**********************\n"
                           + "***PHOTOLIKEs TESTS***\n"
                           + "**********************\n");
        core.run(PhotoLikeDAOtests.class);
        printMessages("PHOTOLIKE");
        
        System.out.println("\n*************************\n"
                           + "***PHOTOCOMMENTs TESTS***\n"
                           + "*************************\n");
        core.run(PhotoCommentDAOtests.class);
        printMessages("PHOTOCOMMENT");
        
        System.out.println("\n**********************************************\n"
                           + "***Hibernate TESTS:" + sum + " FAILED SUMMARY***\n"
                           + "**********************************************\n");
        writer.close();
        System.exit(0);
    }
    
    private static void printMessages(String str){
        num = Messages.getNumberOfFailures();
        sum += num;
        System.out.println(str + ": " + num + " TESTS FAILED");
        if (num > 0)
          {
            List<String> messages = Messages.getMessages();
            for (String message: messages){
                System.out.println(message);
                writer.println(str + " TESTS: " + message);
            }
          }
    } 
}

