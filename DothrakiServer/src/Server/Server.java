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
			System.out.println(sentence);
			String[] parts = sentence.split(" ");
			System.out.println(parts.length);
			String result = "";
			String output = "";
			for (int i = 0; i < parts.length; i++) {
				if (!parts[i].equals("to")) {
					result = dictionary.translate(parts[i]);
					if (result.contains("[")) {
						result = "to " + parts[i];
						result = " " + dictionary.translate(result);
						if (result.contains("[")) {
							output += " [" + result.substring(5, result.length());
						} else {
							output += result;
						}
					} else {
						output += " " + result;
					}
				}
			}

			// prints dothraki in console
			System.out.print(" " + result);
			toClient.writeBytes(output);
			connectionSocket.close();
		}
	}
}
