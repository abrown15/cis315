import java.util.Scanner;


public class assignment0 {
    public static void main(String[] args){
        Scanner jeff = new Scanner(System.in);

        int numPairs = jeff.nextInt();          //Retrieve number of lines

        for(int i = 0; i < numPairs; i ++){
            int a = jeff.nextInt();             //Retrieve left and right operands
            int b = jeff.nextInt();

            int sum = a + b;
            int mult = a * b;

            System.out.println(sum + " " + mult);
        }

        jeff.close();

    }
}
