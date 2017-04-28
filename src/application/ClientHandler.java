package application;

import java.net.*;
import java.io.*;

/**
 * @author Arafin
 *
 */
public class ClientHandler extends Thread {
	
	private Socket socket;

	private String msg;
	
	public ClientHandler() {
		socket = Handler.getSocketServer();
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dataIp = new DataInputStream(socket.getInputStream());
			msg = dataIp.readUTF();
			Handler.setClientMsg(msg);
			System.out.println(Handler.getClientMsg());
			
			DataOutputStream dataOp = new DataOutputStream(socket.getOutputStream());
			dataOp.writeUTF(Handler.getServerMsg());
			
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
