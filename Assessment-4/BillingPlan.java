import java.util.*;
public class BillingPlan {
    public static final HashMap<String, BillingPlan> PLANS = new HashMap<>();
    private final double flatRate, dataOverageRate, dataProvided, couponAmount, minCostForCoupon;

    public static List<String> getPlanNames(){
        List<String> planNames = new ArrayList<>();
        for (Map.Entry<String, BillingPlan> plan : PLANS.entrySet()){
            planNames.add(plan.getKey());
        }
        return planNames;
    }

    public static String inputValidPlanName(){
        Scanner input = new Scanner(System.in);

        System.out.print("\tEnter response: ");
        String planName = input.nextLine().strip();

        while (!PLANS.containsKey(planName)){
            System.out.print("\tInvalid. Try again: ");
            planName = input.nextLine().strip();
        }

        return planName;
    }

    public BillingPlan(String name, double flatRate, double dataOverageRate, double dataProvided, double couponAmount, double minCostForCoupon){
        this.flatRate = flatRate;
        this.dataOverageRate = dataOverageRate;
        this.dataProvided = dataProvided;
        this.couponAmount = couponAmount;
        this.minCostForCoupon = minCostForCoupon;
        PLANS.put(name, this);
    }

    public double getCouponAmount(){
        return couponAmount;
    }

    public double calculateMonthlyPayment(double dataUsed, boolean hasCoupon){
        double monthlyPayment = flatRate;

        double overage = (dataUsed > dataProvided)? dataOverageRate * (dataUsed - dataProvided) : 0.0;
        monthlyPayment += overage;

        double coupon = (hasCoupon && monthlyPayment > minCostForCoupon)? couponAmount : 0.0;
        monthlyPayment -= coupon;

        return monthlyPayment;
    }
}
