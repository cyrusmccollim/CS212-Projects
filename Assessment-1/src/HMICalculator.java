import java.util.*;

public class HMICalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("\nWeight in pounds? ");
        double weight = input.nextDouble() / 2.205;

        System.out.print("Height in feet? ");
        double height = input.nextDouble() / 3.281;

        double hmi = (int) (weight / (height * height));

        if (hmi < 18.5)         System.out.println("HMI of " + hmi + " needs calorie increase.");
        else if (hmi < 25)      System.out.println("HMI of " + hmi + " should maintain current calorie intake.");
        else if (hmi < 30)      System.out.println("HMI of " + hmi + " should reduce calorie intake.");
        else                    System.out.println("HMI of " + hmi + " needs calorie deficit.");
    }
}