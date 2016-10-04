package br.unisinos.dothrakiweb.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translator")
public class TranslateController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String foo(@RequestParam("toTranslate") String toTranslate) 
	{
		String mensagem = "";
		
		if (toTranslate == null || toTranslate.isEmpty()) return "";
		
		try {
			Socket socketCliente = new Socket("127.0.0.1", 6789);
			DataOutputStream paraServidor = new DataOutputStream(socketCliente.getOutputStream());
			BufferedReader doServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			paraServidor.writeBytes(toTranslate + '\n');
			mensagem = doServidor.readLine();
			socketCliente.close();
		} catch (Exception e) {
			mensagem = e.getMessage();
		}
		
		return mensagem;
	}
}