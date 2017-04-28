package application;

import java.net.*;
import java.io.*;

/**
 * @author Arafin
 *
 */
public class MyClient{
	
	String ip;
	Socket client;
	public static String msg, ipAdd;
	
	public MyClient(String ip) {
		try {
			client = new Socket(ip, 3000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveDataFromServer() throws IOException {
		try {
			DataInputStream dataIp = new DataInputStream(client.getInputStream());
			msg = dataIp.readUTF();
			Handler.setServerMsg(msg);
//			System.out.println(Handler.getServerMsg() + " from Server");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendDataToServer() throws IOException {
		try {
			DataOutputStream dataOp = new DataOutputStream(client.getOutputStream());
//			Handler.setClientMsg(InetAddress.getLocalHost().getHostName() + "\nMessages from Client");
			dataOp.writeUTF(Handler.getClientMsg());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
