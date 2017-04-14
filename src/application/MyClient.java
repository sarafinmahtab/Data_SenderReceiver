package application;

/**
 * @author Arafin
 *
 */

import java.net.*;
import java.io.*;

public class MyClient{
	
	String ip;
	Socket client;
	public static String msg;
	
	public MyClient(String ip) {
		this.ip = ip;
		connectServer();
	}
	
	public void connectServer() {
		try {
			client = new Socket(ip, 3000); /* After the server is waiting, a client instantiates a Socket object,
			 								specifying the server name and the port number to connect to. */
			
			DataOutputStream dataOp = new DataOutputStream(client.getOutputStream());
			dataOp.writeUTF("Sabir Sir is my most favourite teacher!!!");
			
			DataInputStream dataIp = new DataInputStream(client.getInputStream());
			msg = dataIp.readUTF();
			System.out.println(msg);
			
			client.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMsg() {
		return msg;
	}
}
