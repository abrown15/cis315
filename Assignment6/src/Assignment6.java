import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Assignment6 {

    public static Set<String> dictionary = new HashSet<>();

    //FIXME                                                        Still need to memoize but accurately returns
    //                                                             whether given string can be split into words.
    public static Boolean recursiveSplit(String currentString){

        //Length of current stirng/sub-string
        int n = currentString.length();

        //Base Case
        if (n == 0){
            return true;
        }

        //Try all prefixes of length from 0 to n-1
        for(int i = 0; i < n; i ++){

//            System.out.println(currentString.substring(0,i));       //DEBUG: Check current Substring
            //Recurrence Relation
            if((dictionary.contains(currentString.substring(0, i+1))) && recursiveSplit(currentString.substring(i+1, n))){
                return true;
            }
        }

        //If we've tried every possible prefix and none work, we return false
        return false;
    }

    public static void loadDictionary(String dictionaryFileName) {
        File inFile = new File(dictionaryFileName);

        try {
            Scanner scan = new Scanner(inFile);
            String line;

            while (scan.hasNext()) {
                line = scan.next();
                dictionary.add(line.trim());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //Load Dictionary
        loadDictionary("diction10k.txt");

//        //Test Dictionary
//        System.out.println("Test Dictionary");
//        System.out.println("Word a: " + dictionary.contains("a"));
//        System.out.println("Word the: " + dictionary.contains("the"));

        Scanner scanner = new Scanner(System.in);
        int numStrings = scanner.nextInt();         //retrieve the number of lines
        scanner.nextLine();                         //move to next line of document

        String[] line = new String[numStrings];     //Initialize array which stores strings in given file
        int[] lineLengths = new int[numStrings];    //Initialize array which stores string Line lengths in given file

        for (int i = 0; i < numStrings; i ++){
            line[i] = scanner.nextLine();           //Stores strings to be split
            lineLengths[i] = line[i].length();      //Stores lengths of strings to be split

            System.out.println(line[i]);            //DEBUG: Prove that ^ works
            System.out.println(lineLengths[i]);     //DEBUG: Prove that we have ^ lengths
        }

        //FIXME
        for(int j = 0; j < numStrings; j++){
            String currentString = line[j];         //Initialze current string being split
            int n = currentString.length();         //Store current string length for later use

            System.out.println("Memoized Attempt:");
            System.out.println("Testing: " + currentString);    //DEBUG: Check current String to be split

            if(recursiveSplit(currentString)){
                System.out.println("YES, can be split");
                System.out.println("");
                //printSplit(recursiveSplitData);
            } else {
                System.out.println("NO, cannot be split");
                System.out.println("");
            }
        }

    }
}

