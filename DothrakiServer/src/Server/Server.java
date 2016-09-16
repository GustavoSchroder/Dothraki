package Server;

public class Server {
	
	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		
		String sentence = "I love you";
		String result = dictionary.translate(sentence);
		
		System.out.println("English: " + sentence);
		System.out.println("Dothraki: " + result);
	}	
}

