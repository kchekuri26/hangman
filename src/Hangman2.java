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
            System.out.println("Enter a single letter!!!");
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
        StringBuilder sb = new StringBuilder(str);
        for (int i=0;i<str.length();i++){
            if (Character.isLetter(sb.charAt(i))) {
                sb.setCharAt(i, '*');
            } else {
                sb.setCharAt(i, str.charAt(i));
            }

        }
        return sb;
    }
    //returns whether a letter matches and modifies the partially hidden phrase, and modifies the hidden phrase if there is a match.
    public StringBuilder processGuess(String str1, String str2, StringBuilder str3){
        if (str2.contains(str1.toLowerCase()) || str2.contains(str1.toUpperCase())){
            ArrayList<Integer> index = new ArrayList<Integer>();
            for (int i=0;i<str2.length();i++){
                if (Character.toLowerCase(str1.charAt(0)) == Character.toLowerCase(str2.charAt(i))){

                    index.add(i);
                }
            }
            for (int j : index){
                str3.setCharAt(j,str2.charAt(j));
            }

        }
        return str3;
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
        //System.out.println(phraseList);

        ArrayList<String> previousGuesses = new ArrayList<String>();

        String phraseToGuess = hangman.randomPhrase(phraseList);

        //System.out.println(phraseToGuess);

        StringBuilder hiddenWord = hangman.generateHiddenPhrase(phraseToGuess);



        int numOfChances = 10;
        while (numOfChances>=1 && String.valueOf(hiddenWord).compareTo(phraseToGuess)!=0) {
            System.out.println("No. of chances remaining: " + numOfChances);
            System.out.println("Previous Guesses: " + previousGuesses);
            System.out.println("Phrase: " + hiddenWord);
            String userGuess = hangman.getGuess();
            while (previousGuesses.contains(userGuess.toLowerCase()) || previousGuesses.contains(userGuess.toUpperCase())) {
                System.out.println("Already guessed!! Guess Again!!");
                userGuess = hangman.getGuess();
            }
            previousGuesses.add(userGuess);
            String previousHiddenWord = String.valueOf(hiddenWord);

            hiddenWord = hangman.processGuess(userGuess, phraseToGuess, hiddenWord );


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
