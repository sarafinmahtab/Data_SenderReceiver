package application;

/**
 * @author Arafin
 *
 */

import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class MyController implements Initializable {
	
	String ip, dataStr, dataMsg;
	Thread t;
	MyClient mc;
	Button send, receive;
	@FXML Label status, ipAdd, data;
	@FXML TextField messages;
	
	boolean isClient = false, clientDataInput = false;
	
	@FXML
	public void browse() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Any File");
        
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Document",
                        "*.pdf", "*.docx"),
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Video Files",
                        "*.mkv", "*.mp4"),
                new FileChooser.ExtensionFilter("Audio Files",
                        "*.mp3", "*.m4p")
                );
        
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {

        	dataStr = selectedFile.toURI().toURL().toString();
        }
	}
	
	@FXML
	public void sendData() {
		dataMsg = messages.getText();
		
		try {
			Handler.setServerMsg(dataMsg);
			t = new MyServer();
			t.start();
			
			status.setText("Seaching clients....");
			
		} catch (Exception e) {
			status.setText("Failed to Search");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void receiveData() {		
		mc = new MyClient("192.168.0.63");
//		mc = new MyClient("10.100.5.145");
//		mc = new MyClient("127.0.0.1");
		
		if(mc.client.isConnected()) {
			status.setText("Connected to Cilent");
			data.setText(Handler.getServerMsg());
		} else {
			status.setText("Not Connected");
		}
	}
	
	@FXML
	public void saveData() {
		
	}
	
	@Override
	public void initialize(URL address, ResourceBundle resource) {
						
		InetAddress ipA;
		
		try {
			ipA = InetAddress.getLocalHost();
			ip = ipA.getHostAddress();
			ipAdd.setText("Your IP Address is:\n" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public String getDataString() {
		return dataStr;
	}
}

//IP Address of Foyaz 192.168.0.9
//IP Address of Mahtab 192.168.0.63
//IP Address of Sajib 192.168.0.62
//IP Address of Mong 192.168.0.64
//IP Address of Tonmoy 192.168.0.70
