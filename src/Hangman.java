import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Hangman {

	public static void main(String [] args) {
			int chancesRemaining = 7;
			String hidden = "";
			String guess;
			ArrayList<String> previousGuesses = new ArrayList<String>();
		Hangman hangman = new Hangman();

        	List<String> phraseList=null;
        	// Get the phrase from a file of phrases
        	try {
            		phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        	} catch (IOException e) {
        	    	System.out.println(e);
        	}
        	System.out.println(phraseList);





        	Random random = new Random();
			String word = phraseList.get(random.nextInt(phraseList.size()));
			System.out.println(word);

			for (int i=0;i<word.length();i++) {
				if (word.charAt(i)==' ') {
					hidden+=" ";
				} else {
					hidden+="*";
				}
			}

			System.out.println("Sentence: " + hidden);

			for (int i=0;i<chancesRemaining;i++) {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Chances remaining: "+chancesRemaining);
				System.out.println("Previous Guesses: "+previousGuesses);
				System.out.print("enter guess:");
				guess = scanner.nextLine();

				for (int j=0;j<guess.length();j++) {
					if (guess.charAt(j))
				}

				while ((guess.))
				previousGuesses.add(guess);


				if (word.contains(guess)) {
					System.out.println("Correct!!");
					int index = word.indexOf(guess);
				}
				System.out.println(hidden);

			}

    	}

}
