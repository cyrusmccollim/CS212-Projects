import java.util.*;

public class Account {
    // Map containing all possible types of accounts.
    public static HashMap<Integer, String> ACCOUNT_TYPES = new HashMap<>();
    static {
        ACCOUNT_TYPES.put(0, "Checking");
        ACCOUNT_TYPES.put(1, "Savings");
        ACCOUNT_TYPES.put(2, "EmergencyFund");
        ACCOUNT_TYPES.put(3, "IndividualRetirement");
    }

    private final int ID;
    private final String name;
    private double balance;
    private boolean isActive;

    // Initializes inactive accounts.
    public Account(int ID){
        this.ID = ID;
        name = ACCOUNT_TYPES.get(ID);
        balance = 0.0;
        isActive = false;
    }

    // Initializes active accounts.
    public Account(int ID, double balance){
        this.ID = ID;
        name = ACCOUNT_TYPES.get(ID);
        this.balance = balance;
        isActive = true;
    }

    //////////////////////
    //
    //  DATA ACCESSORS
    //
    //////////////////////

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }

    public boolean isActive(){
        return isActive;
    }

    //////////////////////
    //
    //  DATA MODIFIERS
    //
    //////////////////////

    public void deposit(double amount){
        isActive = true;
        balance += amount;
    }

    public void withdraw(double amount){
        isActive = true;
        balance -= amount;
    }

    public void transfer(Account toAccount, double amount){
        isActive = true;
        toAccount.isActive = true;
        balance -= amount;
        toAccount.balance += amount;
    }
}
