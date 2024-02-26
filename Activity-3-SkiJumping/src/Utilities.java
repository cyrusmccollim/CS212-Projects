import java.util.Scanner;

public class Utilities {

    private static final Scanner input = new Scanner(System.in);
    public static double inputDouble(){
        System.out.print("\tEnter response: ");
        String str = input.nextLine();

        while (!isDouble(str)){
            System.out.print("\tInvalid entry. Try again: ");
            str = input.nextLine();
        }

        return Double.parseDouble(str);
    }

    public static boolean isDouble(String str){
        try {
            Double.parseDouble(str);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
