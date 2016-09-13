package Server;

public class Server {
	
	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		
		String word = "wound";
		
		Word w = dictionary.getTranslation(word);
		
		System.out.println(w.getDothraki());
	}	
}

