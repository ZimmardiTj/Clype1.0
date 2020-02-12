
package Test;
import Data.*;
import Main.*;
/**
 * <h1>This class is used to test the ClypeData Object</h1>
 * 
 * @author TJ
 */
public abstract class TestClypeData{
	
    /**
     * The main method
     * @param args The main arguments
     */
        public static void main(String[] args) {
        	
        //Testing all ClypeData constructors
        MessageClypeData Test1 = new MessageClypeData("tester","my message","LEMON", 1);
        //Testing FileClypeData Constructor
        FileClypeData Test5 = new FileClypeData("fileTester","file1.txt", 3);
        //Testing MessageClypeData Default Constructor
        MessageClypeData Test7 = new MessageClypeData();
        //Creating new Instance of FileClypeData as ClypeData Object
        ClypeData Test6 = new FileClypeData();
       
  
        //Testing getUserName in MessageClypeData
        System.out.println(Test1.getData());
        Test5.writeFileContents();
        Test5.readFileContents();
        //Testing getFileName in FileClypeData
        System.out.println(Test5.getFileName());
        //Testing mutator setFileName in FileClypeData
        Test5.setFileName("newFileName");
                System.out.println(Test5.getFileName());
        //Testing toString method in FileClypeData
        System.out.println(Test5.toString());
        System.out.println();
        //Testing toString method in FileClypeData
        System.out.println(Test7.toString());
   
    }
}
