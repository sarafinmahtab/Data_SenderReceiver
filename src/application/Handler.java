package application;

public class Handler {
	
	public static String clientMsg, serverMsg;
	
	public static String getClientMsg() {
		return clientMsg;
	}

	public static String getServerMsg() {
		return serverMsg;
	}

	public static void setClientMsg(String clientMsg) {
		Handler.clientMsg = clientMsg;
	}

	public static void setServerMsg(String serverMsg) {
		Handler.serverMsg = serverMsg;
	}	
}
