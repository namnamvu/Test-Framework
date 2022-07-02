package nvu1HW7;

/** 
 * TextAnalytics.java
 * Find the occurence of words in a file and top 5 most used words using hashMap
 * Part 2 + 3 of homework 7
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextAnalytics {
    private static ObjectHashMap hashMap = new ObjectHashMap(0.8);

    /**
     * Try/catch fileName
     */
    private static Scanner checkFile(Scanner scan, String fileName){
        try {
            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException fnfe) {
            System.err.println("File does not exist");
            System.exit(1);
        }
        return scan;
    }

    /**
     * Scan and initialize the hashMap with words from the book
     * Time complexity: O(n^2) - Loop through each word in each line of text
     */
    private static void scanFile(Scanner scan, String input){
        String startPoint = "*** START OF THE PROJECT GUTENBERG";
        String endPoint = "*** END OF THE PROJECT GUTENBERG";
        // Loop until startPoint
        while(!input.contains(startPoint)){
            input = scan.nextLine();
        }
        input = scan.nextLine();
        // Loop until endPoint
        while (scan.hasNextLine() && !input.contains(endPoint)){
            // Neglect blank line and all special character, upper case
            if (input.length() >0){
                String[] words = input.split("\\s+");
                // Put each word in the hashMap as a key and their occurence as value 
                // Complexity: O(n)
                for (String word : words){
                    word = word.toLowerCase().replaceAll("[^a-z]", "");
                    if (hashMap.containsKey(word)){
                        Integer value = (Integer)hashMap.find(word);
                        hashMap.put(word, value + 1);
                    }else{
                        hashMap.put(word, 1);
                    }
                }
            }
            input = scan.nextLine();
        }
    }

    /**
     * UI method to find and print out the occurence of words
     */
    private static void printOccurrence(Scanner scan, String input){
        boolean run = true;
        while(run){
            System.out.print("Type a word or type 'q' to quit: ");
            input = scan.nextLine();
            // Break the loop if input is 'q'
            if (input.equals("q")){
                run = false;
            }else{
                // Print out the occurence of the word prompted by user
                input = input.toLowerCase();
                if (hashMap.containsKey(input)){
                    System.out.print("The word " + input + " occurs " + hashMap.find(input) + " times\n");
                }else{
                    System.out.print("The word " + input + " is not present\n");
                }
            }
        }
    }

    /**
     * Insertion sort method to sort all entry in the hashMap
     * Time complexity: O(N^2) - 2 for loops to sort
     * @param array array of entry in hashMap
     */
    private static Entry[] insertionSort(Entry[] array){
        Entry tmp;
        for (int i = 1; i < array.length; i++){
            // Set current entry and compare to previous one
            Entry curr = array[i];
            for (int j = i-1; j >= 0; j--){
                if ((Integer)curr.value > (Integer) array[j].value){
                    break;
                // If previous entry is larger than current -> sort
                }else{
                    // Set previous entry to current and current entry to previous position
                    tmp = array[j];
                    array[j+1] = tmp;
                    array[j] = curr;
                }
            }
        }
        return array;
    }
    public static void main(String[] args){
        Scanner scan = null; 
        String input = "";
        // Check file and set up the hashMap
        scanFile(checkFile(scan, args[0]), input);
        Entry[] sortArray = insertionSort(hashMap.getEntries());

        if (sortArray.length > 5){
            //Print out the 5 most frequent words 
            System.out.println("--Top 5 Most Frequent Words--");
            for (int k = 1; k < 6; k++){
                System.out.println(k +  ".) " + sortArray[sortArray.length-k].key + "    " + sortArray[sortArray.length-k].value + " times");
            }
        }else{
            System.out.println("--Top " + sortArray.length + " Most Frequent Words--");
            for (int k = 1; k < sortArray.length+1; k++){
                System.out.println(k +  ".) " + sortArray[sortArray.length-k].key + "    " + sortArray[sortArray.length-k].value + " times");
            }
        }
        
        scan = new Scanner(System.in);
        // Interact with user by printing out occurence of words
        printOccurrence(scan, input);
        scan.close();
    }
}
