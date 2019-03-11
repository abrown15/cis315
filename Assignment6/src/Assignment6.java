import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Assignment6 {

    public static Set<String> dictionary = new HashSet<>();
    public static SplitData recursiveSplitData;    //Both SplitData objects should have length n for both arrays
    public static SplitData iterativeSplitData;    //  ''      ''      ''      ''      ''


    public static void main(String[] args) {
        //Load Dictionary
        loadDictionary("diction10k.txt");

        //Test Dictionary
        System.out.println("Test Dictionary");
        System.out.println("Word a: " + dictionary.contains("a"));
        System.out.println("Word the: " + dictionary.contains("the"));

        Scanner scanner = new Scanner(System.in);
        int numStrings = scanner.nextInt();          //retrieve the number of lines

        for(int i = 0; i < numStrings; ++i){
            String currentString = scanner.nextLine();
            int n = currentString.length();

            System.out.println("Phrase Number: " + i);
            System.out.println(currentString);
            System.out.println("");

            System.out.println("Memoized Attempt:");
            if(recursiveSplit(0)){
                System.out.println("YES, can be split");
                printSplit(recursiveSplitData);
            } else {
                System.out.println("NO, cannot be split");
            }
        }

        return;
    }


    //FIXME                                                        //Still need to memoize
    public static Boolean recursiveSplit(String currentString){

        //Length of current stirng/sub-string
        int n = currentString.length();

        //Base Case
        if (n == 0){
            return true;
        }

        //Try all prefixes of length from 1 to n
        for(int i = 0; i < n; i ++){

            //Recurrence Relation
            if(dictionary.contains(currentString.substring(0, i)) && recursiveSplit(currentString.substring(i, n - i))){
                return true;
            }
        }

        //If we've tried every possible prefix and none work, we return false
        return false;
    }

    //Takes iterative/recursive SplitArray AND iterative/recursive lex as input
    //Prints valid string w/spaces
    public static void printSplit(SplitData data){
        int length = data.solutionArray.length;

        int i = 0;
        while(i < length){

            /*We are ONLY calling printSplit on valid strings. So, if lex[0].length == 1,
            Then there is only one word that starts with x0 which we must use*/
            if(data.subStrings[i].length == 1){
                System.out.print(data.subStrings[0][0] + " ");

                //Since there's only one possible word here, we increment i by it's length
                i += data.subStrings[0][0].length();

            //FIXME
            } else {
                return;
            }
        }

    }

    public static void loadDictionary(String dictionaryFileName){
        File inFile = new File(dictionaryFileName);

        try{
            Scanner scan = new Scanner(inFile);
            String line;

            while(scan.hasNext()){
                line = scan.next();
                dictionary.add(line.trim());
            }
            scan.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}

// Modified this class from one I found at https://stackoverflow.com/questions/17296571/making-a-mixed-2d-array-in-java
class SplitData {

    //Holds original string
    private String originalString;

    //Holds length of original string
    public int length;

    //solutionArray[i] returns whether or not it is possible to add spaces to Xi,X(i+1),...,Xn
    Boolean[] solutionArray;

    //subStrings[i] returns array of possible words starting with Xi
    //subStrings[i][j] returns 'jth' possible words starting with Xi
    String[][] subStrings;

    //Takes as input, length of original string
    public SplitData(String originalString){

        //Initialize with original String and associated length
        this.originalString = originalString;
        this.length = originalString.length();

        //Initialze solution array, make all elements false at init
        this.solutionArray = new Boolean[length + 1];
        for(int i = 0; i < this.length; i ++){
            this.solutionArray[i] = false;
        }
    }

    public String getOriginalString() {
        return originalString;
    }

    public Boolean[] getSolutionArray(){
        return  solutionArray;
    }

    public String[][] getSubStrings(){
        return subStrings;
    }

    //Takes as input, length of original string
    public void setSolutionArray(int length){

    }
}

