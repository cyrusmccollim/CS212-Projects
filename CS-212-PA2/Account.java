public class Account{
    private final String name;
    private double balance;

    public Account(String name, double balance){
        this.name = name;
        this.balance = balance;
    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return this.balance;
    }

    public void deposit(double amount){
        this.balance += amount;
    }

    public void withdraw(double amount){
        this.balance -= amount;
    }

    public void transfer(Account toAccount, double amount){
        this.balance -= amount;
        toAccount.balance += amount;
    }
}
