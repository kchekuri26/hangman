import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman3 {

    private static String userGuess;
    private static StringBuilder hiddenWord;
    private static String phraseToGuess;
    private static List<String> phraseList;
    private static ArrayList<String> previousGuesses;


    //gets input from user and returns it.
    public String getGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter guess:");
        String word = scanner.nextLine();
        while (!Character.isLetter(word.charAt(0)) || word.length()>1) {
            System.out.println("Enter a single letter!!!");
            System.out.print("enter guess:");
            word = scanner.nextLine();
        }
        return word;
    }
    //returns a single phrase randomly chosen from a list.
    public String randomPhrase(){
        Random random = new Random();
        String word = phraseList.get(random.nextInt(phraseList.size()));
        return word;
    }
    //returns the initial hidden phrase.
    public StringBuilder generateHiddenPhrase(){
        StringBuilder sb = new StringBuilder(phraseToGuess);
        for (int i=0;i<phraseToGuess.length();i++){
            if (Character.isLetter(sb.charAt(i))) {
                sb.setCharAt(i, '*');
            } else {
                sb.setCharAt(i, phraseToGuess.charAt(i));
            }

        }
        return sb;
    }
    //returns whether a letter matches and modifies the partially hidden phrase, and modifies the hidden phrase if there is a match.
    public StringBuilder processGuess(){
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
        return hiddenWord;
    }



    public static void main(String [] args) {
        Hangman3 hangman = new Hangman3();

        phraseList=null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        //System.out.println(phraseList);

        previousGuesses = new ArrayList<String>();

        phraseToGuess = hangman.randomPhrase();

        //System.out.println(phraseToGuess);

        hiddenWord = hangman.generateHiddenPhrase();



        int numOfChances = 10;
        while (numOfChances>=1 && String.valueOf(hiddenWord).compareTo(phraseToGuess)!=0) {
            System.out.println("No. of chances remaining: " + numOfChances);
            System.out.println("Previous Guesses: " + previousGuesses);
            System.out.println("Phrase: " + hiddenWord);
            userGuess = hangman.getGuess();
            while (previousGuesses.contains(userGuess.toLowerCase()) || previousGuesses.contains(userGuess.toUpperCase())) {
                System.out.println("Already guessed!! Guess Again!!");
                userGuess = hangman.getGuess();
            }
            previousGuesses.add(userGuess);
            String previousHiddenWord = String.valueOf(hiddenWord);

            hiddenWord = hangman.processGuess();


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
            System.out.println("Answer: " + phraseToGuess);
        } else {
            System.out.println("You win!!!");
        }


    }















}

