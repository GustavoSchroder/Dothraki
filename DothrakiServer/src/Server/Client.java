package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String argv[]) throws Exception {

		String frase;
		String fraseModificada;

		BufferedReader doUsuario = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			//have to press enter
			Socket socketCliente = new Socket("127.0.0.1", 6789);
			DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());
			BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			System.out.println("Escreva sua frase");
			frase = doUsuario.readLine();
			paraServidor.writeBytes(frase + '\n');
			fraseModificada = doServidor.readLine();
			System.out.println(fraseModificada);
			socketCliente.close();
		}
	}
}