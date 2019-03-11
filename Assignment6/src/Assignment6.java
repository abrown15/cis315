import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Assignment6 {

    public static Set<String> dictionary = new HashSet<>();
    public static SplitData recursiveSplitData;    //Both split arrays should have length n
    public static SplitData iterativeSplitData;    //  ''      ''      ''      ''      ''

    public static int[][] recursiveLex;               /*recursiveLex[i] returns number of possible words starting at
                                                      recursiveSplitArray[i]*/

    public static int[][] iterativeLex;               /*iterativeLex[i] returns number of possible words starting at
                                                      iterativeSplitArray[i]*/

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


    //FIXME                                                        //SplitArray should have length n
    public static Boolean recursiveSplit(int i){
        return true;
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

    //solutionArray[i] returns whether or not it is possible to add spaces to Xi,X(i+1),...,Xn
    Boolean[] solutionArray;

    //subStrings[i] returns array of possible words starting with Xi
    //subStrings[i][j] returns 'jth' possible words starting with Xi
    String[][] subStrings;

    //Takes as input, length of original string
    public SplitData(String originalString){
        this.originalString = originalString;
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
}

