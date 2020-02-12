
package Test;
import Main.*;

public class TestClypeServer {
    /**
     * The test class for ClypeServer
     * @param args main method arguments
     */
        public static void main(String[] args) {
        
         //Two ClypeServer objects
         //Constructor invoked passing Type
         ClypeServer Tester1 = new ClypeServer(3); 
         //No argument constructor invokation
         ClypeServer Tester2 = new ClypeServer();
         
         //Testing getPort method
         System.out.println(Tester1.getPort());
         //Testing toString method
         System.out.println(Tester2.toString());
        }
}
