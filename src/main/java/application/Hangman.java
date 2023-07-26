package application;

import ui.UserInput;
import ui.UserOutput;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Hangman {
    UserOutput userOutput;
    UserInput userInput;
    FileUtilities fileUtilities;
    Scanner scanner = new Scanner(System.in);


    public Hangman() {
        userInput = new UserInput();
        userOutput = new UserOutput();
        fileUtilities = new FileUtilities();
    }

    public void run() {

        userOutput.displayWelcome();


        // 1. From the FileUtilities class, get a random word to use as the
        // secret word to guess.


        // 2. Create a char array (or String) containing underscores for each
        // letter in the secret word. For example,
        // Secret word ...: giving (6 letters)
        // current guess .: ______ (6 underscores)
        String secretWord = fileUtilities.readRandomLineFromFile();
        String wordToGuess = "";
        String[] stringArr = new String[secretWord.length()];


        for (int i = 0; i < secretWord.length(); i++){
            stringArr[i] = "_";
        }

        // 3. Create a variable (maybe a final variable?) to hold the
        // maximum number of guesses before losing
        final int numOfGuesses = 12;
        int guessCounter = 0;


        // 4. Create a variable to keep track of the number of failed guesses
        int wrongGuessesCounter = 0;
        int wrongGuessesMax = 6;

        // 5. While the user hasn't reached the max number of failed guesses,
        //   * Print out the current letters the user has guessed. For example,
        //       gi_i__  (secret word: giving)
        //   * Ask the user for a letter. (Optional: check for a valid letter)
        //   * Check if the letter is in the secret word
        //   * If the letter is in the secret word, replace the '_' with the letter
        //   * If the letter is NOT in the secret word, increase the variable that
        //     keeps track of the failed guesses by one.
        //   * If the users guess matches the secret word, print a congratulations
        //     message.
        //   * If the user has reached the maximum number of guesses without solving
        //     the secret word, print a message saying: try again.
        int winner = 0;
        String lettersGuessed = "";
        String formattedString = "";
        while (wrongGuessesCounter < wrongGuessesMax && guessCounter < numOfGuesses){
            String arrayString = Arrays.toString(stringArr);
            formattedString = arrayString.replace("[","").replace("]","").replace(", ", "");
            if(secretWord.equals(formattedString)) {
                winner++;
                break;
            }

            System.out.println(formattedString);
            System.out.println("**********************************************");
            System.out.println("Guesses left: " + (numOfGuesses-guessCounter) + " | Wrong Guesses Left: " + (wrongGuessesMax-wrongGuessesCounter));
            System.out.println("Letters Guessed: " + lettersGuessed);
            System.out.println("**********************************************");
            System.out.print("Guess a letter: ");
            String input = scanner.nextLine();

            guessCounter++;
            String newLetter = input;
            if (input.contains(" ")) {
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
        if(winner == 1){
            System.out.println("ANSWER: " + formattedString);
            System.out.println("You win!!!");
            System.out.println("You solved it in " + guessCounter + " guesses and had " + wrongGuessesCounter+ " failed guesses!");
            System.out.println("***********************************************************");
        } else if (winner == 0){
            System.out.println(formattedString);
            System.out.println("You lose! :( The word was " +  "<" + secretWord+ ">.");
            System.out.println("You had " + guessCounter + " guesses and " + wrongGuessesCounter + " failed guesses.");
            System.out.println("***********************************************************");
        }

        // Challenge: add other theme mode for hangman words
        // Challenge: add a hit option
        // Challenge: create Wordle

    }
}
