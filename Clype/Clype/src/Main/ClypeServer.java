
package Main;

import java.io.*;
import java.net.*;
import java.util.*;


import Data.ClypeData;
//TO BE DONE: Check Start, RecieveData, SendData methods.
import Main.ServerSideClientIO;

/**
 * <h1> This Class is used as a Server for Client Connection </h1>
 * 
 * This class lays out the frame work for the ClypeServer Object.
 * It contains all the information needed for a client to connect to the server
 * and engage in communication with other clients who share the same port and host name
 * 
 * @author TJ
 */
public class ClypeServer {

	private int port;
	private boolean closeConnection;
	private static final int DEFAULT_PORT = 7000;
	ArrayList<ServerSideClientIO> serverSideClientIOList;


	public static void main(String[] args) {
		Scanner scan;
		int port;
		String temp;
		if(args.length == 0) {
			ClypeServer client1 = new ClypeServer();
			client1.start();
		}
		else if(args.length == 1) {
			scan = new Scanner(args[0]);
			temp = scan.next();
			port = Integer.parseInt(temp);
			ClypeServer client2 = new ClypeServer(port);
			client2.start();
		}
	}

	/**
	 * The constructor used to set up a new ClypeServer connection, with a specified port number
	 * @param port this is used to generate a new connection at the specific port
	 */
	public ClypeServer(int port){
		if(port < 1024) {
			throw new IllegalArgumentException("Port cannot be less than 1024");
		}
		this.port = port;
		this.serverSideClientIOList = new ArrayList<ServerSideClientIO>();
	}
	/**
	 * The default case. Sets the port to a constant of 7000.
	 */
	public ClypeServer(){
		this(DEFAULT_PORT);
		serverSideClientIOList = new ArrayList<ServerSideClientIO>();

	}

	/**
	 * unused methods currently
	 */



	public void start(){

		try {
			ServerSocket sskt = new ServerSocket(this.port);
			while(closeConnection == false) {
				Socket clientSkt = sskt.accept();
				ServerSideClientIO client1 = new ServerSideClientIO(this, clientSkt);//What to pass here?
				serverSideClientIOList.add(client1);
				Thread thread1 = new Thread(client1);
				thread1.start();
			}
			sskt.close();

		}catch(IOException ioe) {
			System.out.println("IOException: "+ioe.getMessage());
		}
	}

	synchronized public void broadcast(ClypeData dataToBroadcastToClients) {
		for(int i=0; i < serverSideClientIOList.size();i++) {
			ServerSideClientIO something = 	serverSideClientIOList.get(i);
			something.setDataToSendToClient(dataToBroadcastToClients);
			something.sendData();
		}
	}
	synchronized public void remove(ServerSideClientIO serverIO) {
		serverSideClientIOList.remove(serverIO);

	}
	
	   public String userList() {
	        String names = "";
	        for(int i = 0; i < serverSideClientIOList.size(); i++) {
	            ServerSideClientIO clientIO = serverSideClientIOList.get(i);
	            names += clientIO.getDataToRecieveFromClient().getUserName() + "\n";//Character.toString('\n');
	        }
	        return names;
	    }
	
	
	/**
	 * This method returns the port number for the object it is invoked on
	 * @return Returns the integer port number 
	 */
	public int getPort(){
		return this.port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + port;
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
		if (!(obj instanceof ClypeServer)) {
			return false;
		}
		ClypeServer other = (ClypeServer) obj;
		if (port != other.port) {
			return false;
		}
		return true;
	}

	/**
	 * This method is used to create a descriptive output of all instance variables
	 * @return This returns a multi-line detailed output
	 */
	@Override
	public String toString(){
		return 
				"The port number is: "+port+"\n";//Only able to output port for now
	}

}
