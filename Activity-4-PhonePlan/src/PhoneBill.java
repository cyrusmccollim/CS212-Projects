import java.text.DecimalFormat;

public class PhoneBill {
    public static void main(String[] args) {
        String usersPlanName;
        BillingPlan usersPlan;
        double monthlyDataUsage, monthlyCost;
        boolean hasCoupon;

        DecimalFormat formatter = new DecimalFormat("#,###.00");

        new BillingPlan("green", 49.99, 15.0, 2.0, 20.0, 75.0);
        new BillingPlan("blue", 70.0, 10.0, 4.0, 0.0, 0.0);
        new BillingPlan("purple", 49.99, 0.0,0.0, 0.0, 0.0);
        new BillingPlan("verycool", 19.99, 1.99,100.0, 5.0, 0.0);

        System.out.println("\nAvailable Plans: " + BillingPlan.getPlanNames());

        System.out.println("\nWhat's your plan? ");
        usersPlanName = BillingPlan.inputValidPlanName();
        usersPlan = BillingPlan.PLANS.get(usersPlanName);

        System.out.println("\nWhat's your monthly data usage? ");
        monthlyDataUsage = Utilities.inputDouble();

        if (usersPlan.getCouponAmount() > 0.0){
            System.out.println("\nDo you have a coupon? ");
            hasCoupon = Utilities.inputBoolean();
        } else {
            hasCoupon = false;
        }

        monthlyCost = usersPlan.calculateMonthlyPayment(monthlyDataUsage, hasCoupon);
        System.out.println("\nYour monthly cost: $" + formatter.format(monthlyCost));
    }
}
