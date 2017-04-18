package application;

/**
 * @author Arafin
 *
 */

import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {
	
	private Socket socket;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dataIp = new DataInputStream(socket.getInputStream());
			String msg = dataIp.readUTF();
			System.out.println(msg);

			DataOutputStream dataOp = new DataOutputStream(socket.getOutputStream());
//			dataOp.writeUTF("Welcome to Socket Programming");
			dataOp.writeUTF(MyServer.myMsg);
		} catch(NullPointerException e) {
            return;
		} catch (IOException e) {
			return;
		} catch(Exception e) {
			e.printStackTrace();
            return;
		}
	}
}
