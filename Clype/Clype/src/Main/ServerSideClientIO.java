package Main;


import java.io.*;
import java.net.*;

import Data.*;

public class ServerSideClientIO implements Runnable{

private Data.ClypeData dataToRecieveFromClient;
private Data.ClypeData dataToSendToClient;
public ObjectOutputStream outToClient;
public ObjectInputStream inFromClient;
public boolean closeConnection;
ClypeServer server;
Socket clientSocket;

public ServerSideClientIO(ClypeServer server, Socket clientSocket){
	this.server = server;
	this.clientSocket = clientSocket;
	closeConnection = false;
	dataToRecieveFromClient = null;
	dataToSendToClient = null;
	outToClient = null;
	inFromClient = null;
	
}
public void recieveData() {
    try {
        dataToRecieveFromClient = (ClypeData)inFromClient.readObject();
        
        if(dataToRecieveFromClient.getType() == 0) {
            dataToSendToClient = dataToRecieveFromClient;
            this.sendData();
            this.server.remove(this);
            closeConnection = true;
        }
        
        if(dataToRecieveFromClient.getType() == 1) {
            dataToSendToClient = new MessageClypeData(dataToRecieveFromClient.getUserName(),
                                    server.userList(), 1);
            this.sendData();
        }
    } catch (IOException ioe) {
        System.err.println("Cannot receive.");
    } catch (NullPointerException npe) {
        System.err.println("There is no data to get.");
    } catch (ClassNotFoundException cne) {
        System.err.println("ClypeData class not found.");
    }

//		if(dataToRecieveFromClient == null) {
//			closeConnection = true;
//			//server.remove();
//			inFromClient.close();
//		}
		
} 
public void sendData() {
    try {
        //System.out.println(dataToSendToClient);
        if(dataToSendToClient != null)
            outToClient.writeObject(dataToSendToClient);
    } catch (IOException ioe) {
        System.err.println("Cannot output to client.");
    } catch (NullPointerException npe) {
        System.err.println("There is no data");
    }
}

	 @Override
	    public void run() {
	        try{
	            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
	            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
	            
	            while(closeConnection == false) {
	                recieveData();
	                dataToSendToClient = dataToRecieveFromClient;
	                if(!dataToSendToClient.getData().equalsIgnoreCase("DONE") && 
	                        !dataToSendToClient.getData().equalsIgnoreCase("LISTUSERS"))
	                    this.server.broadcast(dataToSendToClient);
	            }
	        } catch(IOException ioe) {
	            System.err.println("Issue with IO");
	        }
	    }
	
	public void setDataToSendToClient(ClypeData dataToSendToClient) {
		
		this.dataToSendToClient = dataToSendToClient;
	}
	
	public ClypeData getDataToRecieveFromClient() {
		return dataToRecieveFromClient;
	}
}
