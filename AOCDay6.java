import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays.*;

/**
 * AOC Day6
 * Custom Customs
 * 
 * @author Jeff Fitzgerald
 * @version 12/06/2020
 */

public class AOCDay6 {
    
    /**
     * Reads the input file of question answers
     * 
     * @param filename name of file to read
     * @return an array of input values
     */
    public static String[] readFile(String fileName) {

        ArrayList<String> customsAnswers = new ArrayList<>();
        Scanner inFile = null;

        try {
            inFile = new Scanner(new File(fileName));
            
            while (inFile.hasNext()) {
                customsAnswers.add(inFile.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + fileName);
            System.exit(0);

        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
        // Convert to Array
        String[] customsAnswersArray = new String[customsAnswers.size()]; 
        customsAnswersArray = customsAnswers.toArray(customsAnswersArray); 
        return customsAnswersArray;
    }

    /**
     * Calculates all unique questions answered by a group
     * 
     * @param allAnswers String Array of each person and groups answers
     * @return Sum of the unique answers for each group
     */
    public static int calcNumOfQuestions(String[] allAnswers){
        String oneAnswerSet;
        // Used to store each question answered for a group, no duplication is allowed in HashMap
        HashMap<Character, Boolean> uniqueAnswers = new HashMap<Character, Boolean>();
        int totalUniqueAnswers = 0;

        for(int i = 0; i < allAnswers.length; i++){
            oneAnswerSet = allAnswers[i];
            // Adds answers if new group
            if(oneAnswerSet.length() == 0){
                totalUniqueAnswers += uniqueAnswers.size();
                uniqueAnswers.clear();
            } else {
                // Loops through new set of answers
                for(int j = 0; j < oneAnswerSet.length(); j++){
                    uniqueAnswers.put(oneAnswerSet.charAt(j), true);
                }
            }
        }
        // Adds last group's unique answers
        totalUniqueAnswers += uniqueAnswers.size();
        return totalUniqueAnswers;
    }
    
    public static void main(String[] args) {

        String fileName = "AOCDay6Input.txt"; //ShortList //ShortListPart2
        String[] customsAnswers = readFile(fileName);
        System.out.println("Sum of unique questions answered per group is " + calcNumOfQuestions(customsAnswers));
    }
}
