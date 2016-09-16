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
	
	public Word getTranslation(String englishWord) {
		return this.words.stream().filter(n -> n.getEnglish().split("\\(")[0].split("\\[")[0].trim().equalsIgnoreCase(englishWord)).findFirst().get();
	}
	
	public String translate(String sentence) {
		String result = "";
		
		String[] splitSentence = sentence.split(" ");
		
		for (int i = 0; i < splitSentence.length; i++) {
			String toTranslate = splitSentence[i];
			
			if ((i + 1) < splitSentence.length) {
				String nextWord = splitSentence[i + 1];
				if (nextWord.equalsIgnoreCase("up")) {
					toTranslate += " " + nextWord;
					i++;
				}
			}
			
			if (toTranslate.equalsIgnoreCase("to")) {
				if ((i + 1) < splitSentence.length) {
					String nextWord = splitSentence[i + 1];
					toTranslate += " " + nextWord;
					i++;
				}
			}
			
			
			Word w = null;
			
			try {
				w = this.getTranslation(toTranslate);
				result += w.getDothraki() + " ";
			} catch (Exception e) {
				System.out.println(e.getMessage());
				result += "[" + toTranslate + "] ";
			}
		}
		
		return result.trim();
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
