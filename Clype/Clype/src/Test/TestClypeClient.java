
package Test;
import Main.*;
import Data.*;
public class TestClypeClient {
    /**
     * This class is used to test the ClypeClient class
     * @param args main arguments
     */
    public static void main(String[] args) {
    //All constructors used to generate a new instance of ClypeClient Object
    ClypeClient Tester1 = new ClypeClient("TestUser","TestHost",2345);
    ClypeClient Tester2 = new ClypeClient("TestUser2","TestHost2");
    ClypeClient Tester3 = new ClypeClient("TestUser3");
    ClypeClient Tester4 = new ClypeClient();
    
    //Testing getUserName method
    System.out.println(Tester1.getUserName());
    //Testing getHostName method
    System.out.println(Tester2.getHostName());
    //Testing getPort method
    System.out.println(Tester3.getPort());
    //Testing toString method
    System.out.println(Tester4.toString());
    
    ClypeClient TestClient = new ClypeClient("TestUserName", "TestHostName", 1050);
    TestClient.start();
    
    FileClypeData TestFileData = new FileClypeData("TestUser4","document.txt",1025);
    System.out.print(TestFileData);
 
    }   
}
