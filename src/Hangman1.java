import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Hangman1 {

	public static void main(String [] args) {

			ArrayList<String> previousGuesses = new ArrayList<String>();
		Hangman1 hangman = new Hangman1();

        	List<String> phraseList=null;
        	// Get the phrase from a file of phrases
        	try {
            		phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        	} catch (IOException e) {
        	    	System.out.println(e);
        	}
        	//System.out.println(phraseList);

        	Random random = new Random();
			String phraseToGuess = phraseList.get(random.nextInt(phraseList.size()));

			StringBuilder hiddenWord = new StringBuilder(phraseToGuess);
			for (int i=0;i<phraseToGuess.length();i++){
				if (Character.isLetter(hiddenWord.charAt(i))) {
					hiddenWord.setCharAt(i, '*');
				} else {
					hiddenWord.setCharAt(i, phraseToGuess.charAt(i));
				}

			}

			int numOfChances = 10;
			while (numOfChances>=1 && String.valueOf(hiddenWord).compareTo(phraseToGuess)!=0) {
				System.out.println("No. of chances remaining: " + numOfChances);
				System.out.println("Previous Guesses: " + previousGuesses);
				System.out.println("Word: " + hiddenWord);

				Scanner scanner = new Scanner(System.in);
				System.out.print("enter guess:");
				String userGuess = scanner.nextLine();
				while (!Character.isLetter(userGuess.charAt(0)) || userGuess.length()>1) {
					System.out.println("Enter a letter!!!");
					System.out.print("enter guess:");
					userGuess = scanner.nextLine();
				}
				while (previousGuesses.contains(userGuess.toLowerCase()) || previousGuesses.contains(userGuess.toUpperCase())) {
					System.out.println("Already guessed!! Guess Again!!");
					System.out.print("enter guess:");
					userGuess = scanner.nextLine();
				}
				previousGuesses.add(userGuess);

				String previousHiddenWord = String.valueOf(hiddenWord);

				if (phraseToGuess.contains(userGuess.toLowerCase()) || phraseToGuess.contains(userGuess.toUpperCase())){
					ArrayList<Integer> index = new ArrayList<Integer>();
					for (int i=0;i<phraseToGuess.length();i++){
						if (Character.toLowerCase(userGuess.charAt(0)) == Character.toLowerCase(phraseToGuess.charAt(i))){

							index.add(i);
						}
					}
					for (int j : index){
						hiddenWord.setCharAt(j,phraseToGuess.charAt(j));
					}



				}

				if (previousHiddenWord.equals(String.valueOf(hiddenWord))) {
					System.out.println("Incorrect!! Try again!!");
					System.out.println("=================================================");
					numOfChances = numOfChances - 1;
				} else {
					System.out.println("Correct!! Keep it up!!");
					System.out.println("=================================================");
				}
			}
			if (numOfChances==0) {
				System.out.println("You loose!!!");
			} else {
				System.out.println("You win!!!");
			}

    	}

}
