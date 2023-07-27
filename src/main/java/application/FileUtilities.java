package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class FileUtilities {

    private int numOfWords = 0;

    private List<String> wordList = new ArrayList<>();


    public static final String VOCAB_FILE = "words.txt";

    public File vocabFile = new File(VOCAB_FILE);

    public int getNumOfWords() {
        return numOfWords;
    }

    public List<String> getWordList() {
        return wordList;
    }

    /*
     * Use this to get a random word from the VOCAB_FILE
     */
    public String readRandomLineFromFile() {
        try(Scanner wordReader = new Scanner(vocabFile)){
            while(wordReader.hasNext()){
                String word = wordReader.nextLine();
                wordList.add(word);
                numOfWords++;
            }
        }catch (FileNotFoundException e){
            System.out.println("Problem reading file");
        }
        return wordList.get(getRandomNumber());
    }

    public int getRandomNumber(){
        return new Random().nextInt(getNumOfWords());
    }



}

