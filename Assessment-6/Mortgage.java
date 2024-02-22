import java.util.*;
public class Mortgage {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Principal? ");
        double principal = input.nextDouble();

        System.out.println("APR? ");
        double apr = input.nextDouble();

        System.out.println("Years of Ownership? ");
        int years = input.nextInt();

        double interestRate = apr / 12 / 100;
        double numPayments = years * 12;
        double monthlyPayment =
                principal * (
                    (interestRate * Math.pow(1 + interestRate, numPayments))
                    / (Math.pow(1 + interestRate, numPayments) - 1)
                );

        System.out.println("Monthly Payment: " + monthlyPayment);
    }
}
