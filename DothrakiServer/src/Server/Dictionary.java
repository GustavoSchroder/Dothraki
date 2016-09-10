package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	private ArrayList<Word> words;
	
	public Dictionary () {
		words = new ArrayList<Word>();
		
		loadDictionary();
	}
	
	public void loadDictionary() {
		BufferedReader br = null;
		String x = "";
		String stringJSON = "";
		try {
			br = new BufferedReader(new FileReader("src/Server/Dothraki.JSON"));
            while ( (x = br.readLine()) != null ) {
                stringJSON += x;
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		this.words = JSONConverter.jsonToWords(stringJSON);
	}

	public ArrayList<Word> getWords() {
		return words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}
}
