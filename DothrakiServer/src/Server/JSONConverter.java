package Server;

import java.util.ArrayList;

import org.json.*;

public class JSONConverter {
	private static String ENGLISH = "english";
	private static String DOTHRAKI = "dothraki";
	private static String SPEECHPART = "speechPart";
	private static String PASTSG = "pastSG";
	private static String QUOTEENGLISH = "quoteEnglish";
	private static String QUOTEDOTHRAKI = "quoteDothraki";
	
	public static ArrayList<Word> jsonToWords(String jsonString) {
		ArrayList<Word> words = new ArrayList<Word>();
		
		try {
			JSONArray objs = new JSONArray(jsonString);
			
			for (int i = 0; i < objs.length(); i++) {
				JSONObject obj = (JSONObject)objs.get(i);
				Word w = new Word();
				w.setDothraki(obj.getString(DOTHRAKI));
				w.setPastSG(obj.getString(PASTSG));
				w.setQuoteDothraki(obj.getString(QUOTEDOTHRAKI));
				w.setQuoteEnglish(obj.getString(QUOTEENGLISH));
				w.setSpeechPart(obj.getString(SPEECHPART));
				
				String english = treatWord(obj.getString(ENGLISH));
				
				if(english.contains(",")) {
					Word aux = new Word();
					aux.setDothraki(obj.getString(DOTHRAKI));
					aux.setPastSG(obj.getString(PASTSG));
					aux.setQuoteDothraki(obj.getString(QUOTEDOTHRAKI));
					aux.setQuoteEnglish(obj.getString(QUOTEENGLISH));
					aux.setSpeechPart(obj.getString(SPEECHPART));
					aux.setEnglish(treatWord(english.split(",")[1]));
					w.setEnglish(treatWord(english.split(",")[0]));
					
					words.add(aux);
				} else {
					w.setEnglish(treatWord(english));
				}
				
				words.add(w);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return words;
	}
	
	public static String treatWord(String word) {
		if (word.contains("(")) {
			word = word.split("\\(")[0].trim();
		}
		
		if (word.contains("'''")) {
			word = word.split("'''")[0].trim();
		}
		
		if (word.contains("[")) {
			word = word.split("\\[")[0].trim();
		}
		return word.trim();
	}
}
