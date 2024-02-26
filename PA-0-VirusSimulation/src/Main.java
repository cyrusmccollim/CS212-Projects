import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("What is the R number? ");
        float rNumber = input.nextFloat();

        System.out.println("Day Case(s): ");
        float infected = 1;
        float multipler = rNumber;
        for (int i = 0; i < 10; i++){
            System.out.println(i + "\t" + infected);
            infected += multipler;
            multipler *= rNumber;
        }
    }
}