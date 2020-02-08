public class HangmanPlayer {

    private static int botIndex = 0;


    //Smart bot that guesses vowels first and then the rest of the alphabets as vowels occur frequently in words.
    public String getBotGuess(){
        String  guessList = "aeioubcdfghjklmnpqrstvwxyz";
        botIndex+=1;
        return String.valueOf(guessList.charAt(botIndex-1));
    }

}
