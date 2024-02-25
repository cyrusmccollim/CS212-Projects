import java.text.*;
import java.util.*;

public class ErrorHandler {
    private static Scanner input;
    private static String errorMessage;
    public static DecimalFormat formatter = new DecimalFormat("#,##0.00");

    public static void resetErrorMessage(){
        errorMessage = "Unknown error";
    }

    //////////////////////////////////
    //
    //  INPUT METHODS
    //
    //////////////////////////////////

    // Obtains an integer within a certain range from the user.
    public static int inputMenuOption(int numOptions){
        input = new Scanner(System.in);
        System.out.print("\tEnter option: ");
        String optionInput = input.nextLine().strip();

        while (!isValidMenuOption(optionInput, numOptions)){
            System.out.print("\t" + errorMessage + ", try again: ");
            optionInput = input.nextLine().strip();
        }

        resetErrorMessage();
        return Integer.parseInt(optionInput);
    }

    // Obtains a positive double from the user.
    public static double inputPositiveDouble(){
        input = new Scanner(System.in);
        System.out.print("\tEnter value: $");
        String amount = input.nextLine().strip();

        while (!isPositiveDouble(amount)){
            System.out.print("\t" + errorMessage + ", try again: $");
            amount = input.nextLine().strip();
        }

        resetErrorMessage();
        return Double.parseDouble(amount);
    }

    // Obtains a yes/no response from the user.
    public static boolean inputBooleanResponse(){
        input = new Scanner(System.in);
        System.out.print("\t[Y/N] Enter response: ");
        String response = input.nextLine().strip();

        while (!isValidBooleanResponse(response)){
            System.out.print("\t" + errorMessage + ", try again: ");
            response = input.nextLine().strip();
        }

        resetErrorMessage();
        return response.equalsIgnoreCase("Y");
    }

    // Obtains a valid username from the user, which must contain only alphanumeric characters and be between 5 and 15 characters.
    public static String inputValidUsername(){
        input = new Scanner(System.in);
        System.out.print("\tEnter response: ");
        String usernameInput = input.nextLine().strip().toLowerCase();

        while (!isValidUsername(usernameInput)){
            System.out.print("\t" + errorMessage + ", try again: ");
            usernameInput = input.nextLine().strip().toLowerCase();
        }

        resetErrorMessage();
        return usernameInput;
    }

    // Obtains a valid password from the user, which must not contain any spaces and be between 8 and 25 characters.
    public static String inputValidPassword(){
        input = new Scanner(System.in);
        System.out.print("\tEnter response: ");
        String passwordInput = input.nextLine().strip();

        while (!isValidPassword(passwordInput)){
            System.out.print("\t" + errorMessage + ", try again: ");
            passwordInput = input.nextLine().strip();
        }

        resetErrorMessage();
        return passwordInput;
    }

    //////////////////////////////////
    //
    //  VALIDITY CHECKS
    //
    //////////////////////////////////

    public static boolean isValidMenuOption(String optionInput, int numOptions){
        optionInput = optionInput.strip();

        int option;
        try {
            option = Integer.parseInt(optionInput);
        } catch (Exception e){
            errorMessage = "Not an integer";
            return false;
        }

        if (!(option >= 0 && option <= numOptions-1)){
            errorMessage = "Not within range";
            return false;
        }

        return true;
    }

    public static boolean isPositiveDouble(String str){
        try {
            Double.parseDouble(str);
        } catch (Exception e){
            errorMessage = "Not a number";
            return false;
        }

        if (Double.parseDouble(str) <= 0) {
            errorMessage = "Not positive";
            return false;
        }

        return true;
    }

    public static boolean isValidBooleanResponse(String response) {
        if (!(response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("N"))){
            errorMessage = "Not 'Y' or 'N'";
            return false;
        }
        return true;
    }

    public static boolean isValidUsername(String usernameInput){
        ArrayList<String> alphaNumericSymbols = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

        // Check if within length limitations.
        if (!(usernameInput.length() >= 5 && usernameInput.length() <= 15)) {
            errorMessage = "Not 5-15 characters";
            return false;
        }

        // Check if alphanumeric.
        for (int i = 0; i < usernameInput.length(); i++)
            if (!alphaNumericSymbols.contains(usernameInput.substring(i, i + 1))) {
                errorMessage = "Not alphanumeric";
                return false;
            }

        return true;
    }

    public static boolean isValidPassword(String passwordInput){
        if (passwordInput.contains(" ")) {
            errorMessage = "Must not contain spaces";
            return false;
        }
        if (!(passwordInput.length() >= 8 && passwordInput.length() <= 25)){
            errorMessage = "Not 8-25 characters";
            return false;
        }
        return true;
    }
}
