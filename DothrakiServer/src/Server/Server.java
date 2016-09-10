package Server;

public class Server {
	
	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		
		for (int i = 0; i < dictionary.getWords().size(); i++) {
			System.out.println(dictionary.getWords().get(i).getEnglish() + ": " + dictionary.getWords().get(i).getDothraki());
		}
	}	
}

