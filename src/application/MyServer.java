package application;

/**
 * @author Arafin
 *
 */

import java.net.*;
import java.io.*;

public class MyServer extends Thread{
	
	private ServerSocket serverSocket;
	String myMsg;
	
	public MyServer(String myMsg) {
		this.myMsg = myMsg;
		
		try {
			serverSocket = new ServerSocket(3000); /*server instantiates a ServerSocket object,
			 										denoting which port number communication is to occur on. */
		    serverSocket.setSoTimeout(10000);
		} catch (IOException e) {
			
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {			
				Socket server = serverSocket.accept(); //This method waits until a client connects to the server on the given port
				
//				System.out.println("Connected to Client");
				DataInputStream dataIp = new DataInputStream(server.getInputStream());
				String msg = dataIp.readUTF();
				System.out.println(msg);

				DataOutputStream dataOp = new DataOutputStream(server.getOutputStream());
//				dataOp.writeUTF("Welcome to Socket Programming");
				dataOp.writeUTF(myMsg);
				
	            server.close();
	            
			} catch(SocketTimeoutException e) {
	            break;
			} catch(NullPointerException e) {
	            break;
			} catch (IOException e) {
	            break;
			} catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
