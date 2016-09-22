package Server;

import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws Exception {

		Dictionary dictionary = new Dictionary();
		ServerSocket receptionSocket = new ServerSocket(6789);
		
		while (true) {
			Socket connectionSocket = receptionSocket.accept();
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());
			String sentence = fromClient.readLine();
			String result = dictionary.translate(sentence);
			//prints dorthaki in console
			System.out.print(" " + result);
			toClient.writeBytes(result);
			connectionSocket.close();
		}
	}
}
