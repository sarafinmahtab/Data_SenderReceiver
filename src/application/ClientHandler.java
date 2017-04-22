package application;

/**
 * @author Arafin
 *
 */

import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {
	
	private Socket socket;
	private DataInputStream dataIp;
	private DataOutputStream dataOp;
	private String msg;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		dataIp = null;
		dataOp = null;
	}
	
	@Override
	public void run() {
		try {
			dataIp = new DataInputStream(socket.getInputStream());
			msg = dataIp.readUTF();
			System.out.println(msg);
			
			dataOp = new DataOutputStream(socket.getOutputStream());
//			dataOp.writeUTF("Welcome to Socket Programming");
			dataOp.writeUTF(Handler.getServerMsg());
		} catch(NullPointerException e) {
            return;
		} catch (IOException e) {
			return;
		} catch(Exception e) {
			e.printStackTrace();
            return;
		} finally {
			if(dataIp != null) {
				try {
					dataIp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(dataOp != null) {
				try {
					dataOp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(socket != null) {
				try {
					dataIp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
