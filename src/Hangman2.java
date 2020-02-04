import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman2 {
    //gets input from user and returns it.
    public String getGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter guess:");
        String word = scanner.nextLine();
        while (!Character.isLetter(word.charAt(0)) || word.length()>1) {
            System.out.println("Enter a letter!!!");
            System.out.print("enter guess:");
            word = scanner.nextLine();
        }
        return word;
    }
    //returns a single phrase randomly chosen from a list.
    public String randomPhrase(List<String> listName){
        Random random = new Random();
        String word = listName.get(random.nextInt(listName.size()));
        return word;
    }
    //returns the initial hidden phrase.
    public StringBuilder generateHiddenPhrase(String str){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<str.length();i++){
            sb.setCharAt(i, '*');
        }
        return sb;
    }
    //returns whether a letter matches and modifies the partially hidden phrase, and modifies the hidden phrase if there is a match.
    public StringBuilder processGuess(String str1, String str2, StringBuilder str3){
        if (str2.contains(str1)){
            ArrayList<Integer> index = new ArrayList<Integer>();
            for (int i=0;i<str2.length();i++){
                if (Character.toLowerCase(str1.charAt(0)) == Character.toLowerCase(str2.charAt(i))){
                    index.add(i);
                }
            }
            for (int i : index){
                str3.setCharAt(i,str2.charAt(i));
            }
            return str3;

        } else {
            StringBuilder value = new StringBuilder("false");
            return value;
        }
    }



    public static void main(String [] args) {
        Hangman2 hangman = new Hangman2();

        List<String> phraseList=null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(phraseList);

        ArrayList<String> previousGuesses = new ArrayList<String>();
        String phraseToGuess = hangman.randomPhrase(phraseList);

        StringBuilder hiddenWord = hangman.generateHiddenPhrase(phraseToGuess);

        System.out.println("Word: " + hiddenWord);

        for (int i=0; i<phraseToGuess.length(); i++) {
            String guess = hangman.getGuess();
            while (previousGuesses.contains(guess) == true) {
                System.out.println("Already guessed");
                guess = hangman.getGuess();
            }
            previousGuesses.add(guess);

            StringBuilder result = hangman.processGuess(guess, phraseToGuess, hiddenWord );

            if (result == "false") {
                System.out.println("incorrect!! Guess again!!");
                System.out.println("Word: " + hiddenWord);
            } else {
                System.out.println("Correct!! Keep going!!");
                hiddenWord = result;
                System.out.println("Word: " + hiddenWord);
            }


        }


    }















}
