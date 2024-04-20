import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] rollDistribution = new int[11];
        Scanner input = new Scanner(System.in);

        System.out.print("Welcome to Rolling Dice! Simulate rolling a pair of dice.");

        System.out.print("\n\tHow many rolls? ");
        String numRollsInput = input.nextLine();
        while (!isValidInteger(numRollsInput)){
            System.out.print("\tInvalid, try again: ");
            numRollsInput = input.nextLine();
        }
        int numRolls = Integer.parseInt(numRollsInput);

        for (int i = 0; i < numRolls; i++){
            int rollOne = (int) (Math.random() * 6) + 1;
            int rollTwo = (int) (Math.random() * 6) + 1;
            rollDistribution[rollTwo + rollOne - 2] += 1;
        }

        String result = "Distribution:\n";
        for (int i = 0; i < rollDistribution.length; i++) {
            result += String.format("%2s", i + 2) + " " + "*".repeat(rollDistribution[i]) + "\n";
        }
        System.out.print(result);
    }

    public static boolean isValidInteger(String input){
        try {
            return Integer.parseInt(input) >= 0;
        } catch (Exception e){
            return false;
        }
    }
}
