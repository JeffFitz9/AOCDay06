import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AOC Day6
 * Custom Customs
 * 
 * @author Jeff Fitzgerald
 * @version 12/06/2020
 */

public class AOCDay6Part2 {
    
    /**
     * Reads the input file of question answers.
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
     * Compares the intersection of 2 strings
     * 
     * @param str1 first string to compare
     * @param str2 second string to compare
     * @return a String of the intersection of the 2 strings
     */
    public static String compareStrings(String str1, String str2){
        String common = "";

        for(int i = 0; i < str1.length(); i++){
            for(int j = 0; j < str2.length(); j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    common += str1.charAt(i);
                }
            }
        }
        return common;
    }

    /**
     * Calculates the number of questions in common from all group members
     * 
     * @param allAnswers String Array of each person and groups answers
     * @return Sum of the common answers for each group
     */
    public static int calcNumOfQuestions(String[] allAnswers){

        int totalCommonAnswers = 0;
        String common = "";
        Boolean newGroup = true;

        // Loop through each line
        for(int i = 0; i < allAnswers.length; i++){
            // Set common to first set of answers for a newgroup
            if(newGroup){
                common = allAnswers[i];
                newGroup = false;
            } else { 
                // If the end of a group, add the total common questions
                if(allAnswers[i].length() == 0){
                    totalCommonAnswers += common.length();
                    newGroup = true;
                } else { // If not the end of a group, compare the next set of answers
                    common = compareStrings(common, allAnswers[i]);
                }
            }
        }
        // Add the common answers for the last group
        totalCommonAnswers += common.length();
        return totalCommonAnswers;
    }

    public static void main(String[] args) {

        String fileName = "AOCDay6Input.txt"; //ShortList //ShortListPart2
        String[] customsAnswers = readFile(fileName);
        System.out.println("Sum of common questions answered per group is " + calcNumOfQuestions(customsAnswers));
    }
}
