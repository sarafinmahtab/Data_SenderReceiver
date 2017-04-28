package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Arafin
 *
 */
public class MyController implements Initializable, Runnable {
	
	private String dataStr, dataMsg;
	private int index = 0;
	
	private Thread t, updater;
	private MyClient myClient;
	
	Button send, receive;
	@FXML Label status, data;
	@FXML TextField messages;
	
	public static ArrayList<String> arrayList = new ArrayList<>();
	private ArrayList<String> list = new ArrayList<>();
	private ObservableList<String> dataList;
	
	static boolean isClient = false, clientDataInput = false;
	
	@FXML
	public void openServer() {
		try {
			t = new MyServer();
			t.start();
			status.setText("Seaching clients....");
			
		} catch (Exception e) {
			status.setText("Failed to Search");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void connectServer() {
		isClient = true;
		
		myClient = new MyClient("192.168.0.63");
//		mc = new MyClient("10.100.5.145");
//		mc = new MyClient("127.0.0.1");
		
		if(myClient.client.isConnected()) {
			status.setText("Connected to Server");
		} else {
			status.setText("Not Connected");
		}
	}
	
	@FXML
	public void sendData() {
		dataMsg = messages.getText();
		
		if(isClient) {
			try {
				clientDataInput = true;
				Handler.setClientMsg(dataMsg);
				myClient.sendDataToServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			clientDataInput = false;
			Handler.setServerMsg(dataMsg);
			new ClientHandler().start();
		}
	}
	
	@FXML
	public void receiveData() {
		if(isClient) {
			try {
				clientDataInput = false;
				myClient.receiveDataFromServer();
				data.setText(Handler.getServerMsg());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			clientDataInput = true;
			new ClientHandler().start();
			
			list.add(Handler.getClientMsg());
			while(Handler.getClientMsg() == list.get(index)) {
				continue;
			}
			data.setText(Handler.getClientMsg());
			index++;
		}
	}
	
	@Override
	public void run() {
		
	}
	
	@FXML
	public void devices() {
        final Stage deviceList = new Stage();
        deviceList.initModality(Modality.APPLICATION_MODAL);
        deviceList.initOwner(Main.getStage());
        
        dataList = FXCollections.observableArrayList();
        
        for(int j = 0; j < arrayList.size(); j++) {
        	dataList.add(arrayList.get(j));
        }
        
        ListView<String> list = new ListView<String>();
        list.setItems(dataList);
        
        StackPane stack = new StackPane();
        stack.getChildren().add(list);
        
        Scene dialogScene = new Scene(stack, 450, 200);
        deviceList.setScene(dialogScene);
        deviceList.setTitle("List of Connected Devices");
        deviceList.show();
    }
	
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
	
	@Override
	public void initialize(URL address, ResourceBundle resource) {
		
		updater = new Thread(this, "Updater");
		updater.start();
		
//		InetAddress ipA;
//		
//		try {
//			ipA = InetAddress.getLocalHost();
//			ip = ipA.getHostAddress();
//			ipAdd.setText("Your IP Address is:\n" + ip);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
	}
	
	public String getDataString() {
		return dataStr;
	}
}
