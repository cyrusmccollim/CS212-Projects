import java.text.*;
import java.util.*;

public class Utility {
    private static Scanner input;
    public static DecimalFormat formatter = new DecimalFormat("#,##0.00");

    public static int inputMenuOption(int min, int max){
        input = new Scanner(System.in);
        System.out.print("\tEnter option: ");
        String optionInput = input.next();
        while (!isValidInteger(optionInput)
                || !(Integer.parseInt(optionInput) >= min && Integer.parseInt(optionInput) <= max)){
            System.out.print("\tInvalid, try again: ");
            optionInput = input.next();
        }
        return Integer.parseInt(optionInput);
    }

    public static double inputValidExchange(){
        input = new Scanner(System.in);
        System.out.print("\tEnter value: $");
        String amount = input.next();
        while (!isPositiveDouble(amount)){
            System.out.print("\tInvalid, try again: $");
            amount = input.next();
        }
        return Double.parseDouble(amount);
    }

    public static boolean inputBooleanResponse(){
        input = new Scanner(System.in);
        System.out.print("\t[Y/N] Enter response: ");
        return input.next().strip().equalsIgnoreCase("Y");
    }

    public static String inputValidUsername(){
        input = new Scanner(System.in);
        System.out.print("\tEnter response: ");
        String usernameInput = input.next().strip().toLowerCase();
        while (!isValidUsername(usernameInput)){
            System.out.print("\tInvalid, try again: ");
            usernameInput = input.next().strip().toLowerCase();
        }
        return usernameInput;
    }

    public static String inputValidPassword(){
        input = new Scanner(System.in);
        System.out.print("\tEnter response: ");
        String passwordInput = input.next().strip();
        while (!isValidPassword(passwordInput)){
            System.out.print("\t-- Invalid, try again: ");
            passwordInput = input.next().strip();
        }
        return passwordInput;
    }

    public static boolean isValidInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean isPositiveDouble(String str){
        try {
            return Double.parseDouble(str) > 0;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean isValidUsername(String usernameInput){
        ArrayList<String> alphaNumericSymbols = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        for (int i = 0; i < usernameInput.length(); i++)
            if (!alphaNumericSymbols.contains(usernameInput.substring(i, i + 1)))
                return false;
        return true;
    }

    public static boolean isValidPassword(String passwordInput){
        return !passwordInput.strip().contains(" ");
    }
}
