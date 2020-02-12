
package Data;

import java.io.Serializable;
import java.util.Date;
/**
 * <h1>This is the Super ClypeData Class</h1>
 * This is the Super class that represents the data sent between the Client and Server.
 * 
 * The data contained in the ClypeData object includes the username, type of user,
 * and the date. This class will also hold the data itself, being sent between ClypeClient and 
 * ClypeServer. 
 * 
 * @author TJ
 */

public abstract class ClypeData implements Serializable {

    protected String userName;
    protected int type;
    protected Date nowDate = new Date();
    protected final String KEY = "LEMON";
    protected final int TYPE_0 = 0;
    protected final int TYPE_1 = 1;
    protected final int TYPE_2 = 2;
    protected final int TYPE_3 = 3;
    
    
    /**
     * This constructor is used to create a new instance of ClypeData
     * @param userName The username to be associated with the newly created Object
     * @param type The type of user connection
     */
    public ClypeData(String userName, int type ){
        this.type = type;
        this.userName = userName;  
        this.nowDate = new Date();
    }
    
    /**
     * This constructor defaults the username to "Anon" 
     * @param type The type of user connection
     */
    public ClypeData(int type){
        this("Anon",type);
        }
       
    
    /**
     * This no argument constructor defaults username to "Anon"
     * It also defaults the Type to 0.
     */
    public ClypeData(){
        this(0);
    //no argument constructor
    }
    /**
     * This method returns the type of user connection
     * @return Returns the type of user connection when invoked
     */
    public int getType(){
    return type;
    }
    /**
     * This method returns the username of the ClypeData Object
     * @return Returns the type of user connection when invoked
     */
    public String getUserName(){
    return userName;
    }
    /**
     * This method returns the date
     * @return Returns the date when invoked
     */
    public Date getDate(){
        return nowDate;
    }
    
    
 
    
    /* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + type;
	result = prime * result + ((userName == null) ? 0 : userName.hashCode());
	return result;
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj) {
		return true;
	}
	if (obj == null) {
		return false;
	}
	if (getClass() != obj.getClass()) {
		return false;
	}
	ClypeData other = (ClypeData) obj;
	if (type != other.type) {
		return false;
	}
	if (userName == null) {
		if (other.userName != null) {
			return false;
		}
	} else if (!userName.equals(other.userName)) {
		return false;
	}
	return true;
}

protected String encrypt(String inputStringToEncrypt, String key) {
    String encrypted = "";
    inputStringToEncrypt = inputStringToEncrypt.toUpperCase();
    for (int cnt = 0, i = 0; cnt < inputStringToEncrypt.length(); cnt++) {
         char m = inputStringToEncrypt.charAt(cnt);
         if (m >= 'A' && m <= 'Z') {
             encrypted += (char)((m + key.charAt(i) - 2 * 'A') % 26 + 'A');
             i++;
             if(i == key.length())
                 i = 0;
         } else{
             encrypted += m;
         }
    }
    return encrypted;
}
protected String decrypt(String inputStringToDecrypt, String key) {
    String decrypted = "";
    inputStringToDecrypt = inputStringToDecrypt.toUpperCase();
    for (int cnt = 0, i = 0; cnt < inputStringToDecrypt.length(); cnt++) {
         char m = inputStringToDecrypt.charAt(cnt);
         if (m >= 'A' && m <= 'Z') {
             decrypted += (char)((m - key.charAt(i) + 26) % 26 + 'A');
             i++;
             if(i == key.length())
                 i = 0;
         } else {
             decrypted += m;
         }
    }
    return decrypted;
}

	public abstract String getData();


    }
     

