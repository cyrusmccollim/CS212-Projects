import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    private static final Scanner input = new Scanner(System.in);

    public static boolean actionCancelled(){
        System.out.println("Are you sure? (Y/N) ");
        return !inputString().equalsIgnoreCase("Y");
    }

    public static String inputMenuOption(ArrayList<String> validOptions) {
        String optionInput = inputString().toUpperCase();
        while (!validOptions.contains(optionInput)) {
            optionInput = inputString().toUpperCase();
        }
        return optionInput;
    }

    public static String inputString(){
        System.out.print("\tEnter response: ");
        return input.nextLine().strip();
    }

    public static int inputPositiveInteger(){
        System.out.print("\tEnter value: ");
        String s = input.nextLine().strip();
        while(!isPositiveInteger(s)){
            System.out.print("\tInvalid, try again: ");
            s = input.nextLine().strip();
        }
        return Integer.parseInt(s);
    }

    public static int inputInteger(){
        System.out.print("\tEnter value: ");
        String s = input.nextLine().strip();
        while(!isInteger(s)){
            System.out.print("\tInvalid, try again: ");
            s = input.nextLine().strip();
        }
        return Integer.parseInt(s);
    }

    public static boolean isPositiveInteger(String s) {
        try {
            return Integer.parseInt(s) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
