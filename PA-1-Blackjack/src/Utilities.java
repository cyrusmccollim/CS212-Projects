import java.util.*;

public class Utilities {
    private static final Scanner input = new Scanner(System.in);

    // Inputs a string that is contained within a list of valid strings.
    public static String inputString(String[] validChars){
        System.out.print("\tEnter response: ");
        String str = input.nextLine().toUpperCase().strip();

        while (!Arrays.asList(validChars).contains(str)){
            System.out.print("\tInvalid entry. Try again: ");
            str = input.nextLine().toUpperCase().strip();
        }

        return str;
    }

    // Inputs a raw string.
    public static String inputString(){
        return input.nextLine();
    }

    // Inputs an integer within a range.
    public static int inputInt(int min, int max){
        System.out.print("\tEnter response: ");
        String str = input.nextLine().strip();

        while (!isInt(str) || !(Integer.parseInt(str) >= min && Integer.parseInt(str) <= max)){
            System.out.print("\tInvalid entry. Try again: ");
            str = input.nextLine().strip();
        }

        return Integer.parseInt(str);
    }

    // Returns whether a string can be parsed as an integer.
    public static boolean isInt(String str){
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
