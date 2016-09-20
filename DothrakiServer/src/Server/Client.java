package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String argv[]) throws Exception {

		String palavra = "";
		String palavraModificada;

		Scanner doUsuario = new Scanner(System.in);

		Socket socketCliente = new Socket("localhost", 6789);
		DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());
		BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

		while (true) {
			System.out.print("Digite uma palavra: ");
			while (true) {
				char x = doUsuario.next().charAt(0);
				if (x == ' ') {
					paraServidor.writeBytes(palavra);
					palavraModificada = " " + doServidor.readLine();
				} else if (x == '0') {
					socketCliente.close();
					return;
				} else {
					palavra += x;
				}
			}
		}
	}
}
