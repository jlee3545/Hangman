package application;

import ui.UserInput;
import ui.UserOutput;

import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    UserOutput userOutput;
    UserInput userInput;
    FileUtilities fileUtilities;
    Scanner scanner = new Scanner(System.in);
    private String formattedString = "";
    private String secretWord;
    private int wrongGuessesCounter = 0;
    private int wrongGuessesMax = 6;
    private final int numOfGuesses = 12;
    private int guessCounter = 0;
    public Hangman() {
        userInput = new UserInput();
        userOutput = new UserOutput();
        fileUtilities = new FileUtilities();
    }

    public void run() {

        userOutput.displayWelcome();

        secretWord = fileUtilities.readRandomLineFromFile();
        String wordToGuess = "";
        String[] stringArr = new String[secretWord.length()];


        for (int i = 0; i < secretWord.length(); i++){
            stringArr[i] = "_";
        }


        int winner = 0;
        String lettersGuessed = "";
        while (wrongGuessesCounter < wrongGuessesMax && guessCounter < numOfGuesses){
            String arrayString = Arrays.toString(stringArr);
            formattedString = arrayString.replace("[","").replace("]","").replace(", ", "");
            if(secretWord.equals(formattedString)) {
                winner++;
                break;
            }

            System.out.println(formattedString);
            System.out.println("*****************************************************");
            System.out.println("Guesses left: " + (numOfGuesses-guessCounter) + " | Wrong Guesses Left: " + (wrongGuessesMax-wrongGuessesCounter));
            System.out.println("Letters Guessed: " + lettersGuessed);
            System.out.println("*****************************************************");
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine();

            guessCounter++;
            String newLetter = input;
            if (input.contains(" ") || input.length()>1) {
                guessCounter--;
                wrongGuessesCounter--;
            }else if((lettersGuessed.contains(input) && formattedString.contains(input))){
                guessCounter--;
            }else if (lettersGuessed.contains(input)){
                continue;
            } else {
                lettersGuessed = lettersGuessed + input + " ";
            }

            if(!secretWord.contains(newLetter)){
                wrongGuessesCounter++;
            }
            for(int i = 0; i < stringArr.length; i++) {

                if (secretWord.substring(i,i+1).equals(newLetter)) {
                    stringArr[i] = newLetter;
                }
            }
        }

        winOrLose(winner);

        // Challenge: add other theme mode for hangman words
        // Challenge: add a hit option
        // Challenge: create Wordle

    }
    public void winOrLose(int winner){
        if(winner == 1){
            System.out.println("ANSWER: " + formattedString);
            System.out.println("You win!!!");
            System.out.println("You solved it in " + guessCounter + " guesses and had " + wrongGuessesCounter+ " failed guesses!");
            System.out.println("*****************************************************");
        } else if (winner == 0){
            System.out.println(formattedString);
            System.out.println("You lose! :( The word was " +  "<" + secretWord+ ">.");
            System.out.println("You had " + guessCounter + " guesses and " + wrongGuessesCounter + " failed guesses.");
            System.out.println("*****************************************************");
        }
    }
}

