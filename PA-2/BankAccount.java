import java.io.*;
import java.util.*;

// Account Database Information ("BankAccounts.txt")
// Format: "\nName, Password, CheckingBalance, SavingsBalance, LatestTransactionsLog"
// Example: "\nCyrus, CyrusIsCool, 78.90, 578.14, :::$[441.89] Deposit to Checking:$[-55.98] Savings Withdrawal"
public class BankAccount {
    public static HashMap<String, BankAccount> ACCOUNTS = new HashMap<>();
    private final String username, password;
    private final Account checkingAccount, savingsAccount;
    private final String[] transactionLog;

    // Constructor for new account creation.
    public BankAccount(String username, String password){
        this.username = username;
        this.password = password;
        this.checkingAccount = new Account("Checking", 0.0);
        this.savingsAccount = new Account("Savings", 0.0);
        this.transactionLog = new String[5];

        ACCOUNTS.put(username, this);
        addNewAccountToFile();
    }

    // Constructor for loading existing accounts.
    public BankAccount(String username, String password, String checkingBalance, String savingsBalance, String latestTransactionsString){
        this.username = username;
        this.password = password;
        this.checkingAccount = new Account("Checking", Double.parseDouble(checkingBalance));
        this.savingsAccount = new Account("Savings", Double.parseDouble(savingsBalance));

        String[] tempTransactions = latestTransactionsString.split(":");
        for (int i = 0; i < tempTransactions.length; i++)
            if (tempTransactions[i].equals("null"))
                tempTransactions[i] = null;
        this.transactionLog = tempTransactions;

        ACCOUNTS.put(username, this);
    }

    public Account getCheckingAccount(){
        return checkingAccount;
    }

    public Account getSavingsAccount(){
        return savingsAccount;
    }

    // Returns whether a password entry is correct.
    public boolean hasPassword(String passwordInput){
        return this.password.equals(passwordInput);
    }

    // Attempts to perform a deposit, returns message about transaction results.
    public String deposit(Account account, double depositAmount){
        account.deposit(depositAmount);

        setLastTransactionLog("[$" + depositAmount + "] " + account.getName() + " Deposit");
        updateAccountInDatabase();

        return "Transaction successful!";
    }

    // Attempts to perform a withdrawal, returns message about transaction results.
    public String withdraw(Account account, double requestedWithdrawal){
        double finalWithdrawalAmount = (account.getBalance() - requestedWithdrawal >= 0)? requestedWithdrawal: account.getBalance();
        account.withdraw(finalWithdrawalAmount);

        setLastTransactionLog("[$" + finalWithdrawalAmount + "] " + account.getName() + " Withdrawal");
        updateAccountInDatabase();

        if (finalWithdrawalAmount < requestedWithdrawal)
            return "Withdrew maximum amount of $" + finalWithdrawalAmount + " due to insufficient funds.";

        return "Transaction successful!";
    }

    public String transfer(Account fromAccount, Account toAccount, double requestedTransfer){
        double finalTransferAmount = (fromAccount.getBalance() - requestedTransfer >= 0)? requestedTransfer: fromAccount.getBalance();
        fromAccount.transfer(toAccount, finalTransferAmount);

        setLastTransactionLog("[$" + finalTransferAmount + "] " + fromAccount.getName() + " -> " +  toAccount.getName() + " Transfer");
        updateAccountInDatabase();

        if (finalTransferAmount < requestedTransfer)
            return "Withdrew maximum amount of $" + finalTransferAmount + " due to insufficient funds.";

        return "Transaction successful!";
    }

    // Shifts all transactions down one position then updates the last transaction.
    public void setLastTransactionLog(String log){
        int lastIndex = this.transactionLog.length - 1;
        for (int i = 0; i < lastIndex; i++){
            this.transactionLog[i] = this.transactionLog[i + 1];
        }
        transactionLog[transactionLog.length-1] = log;
    }

    // Returns a raw string of the transactions log.
    public String getTransactions(){
        ArrayList<String> transactionLogFiltered = new ArrayList<>();
        for (String log : this.transactionLog)
            if (log != null)
                transactionLogFiltered.add(log);

        String rawTransactionsString = String.join("\n\t", transactionLogFiltered).strip();

        if (rawTransactionsString.isBlank())
            return "\tNo recent transactions.";

        return "(Earliest)\n\t" + rawTransactionsString + "\n(Latest)";
    }

    // Returns a formatted report of an account's checking balance, savings balance, smallest transaction, largest transaction, and average transaction.
    public String getStatistics(){
        ArrayList<Double> transactionAmounts = new ArrayList<>();

        // Obtains the values of every logged transaction.
        for (String log : this.transactionLog){
            int amountStartIndex = log.indexOf("[$") + 2;
            int amountEndIndex = log.indexOf("]");

            double logAmount;
            try {
                logAmount = Double.parseDouble(log.substring(amountStartIndex, amountEndIndex));
            } catch (Exception e){
                continue;
            }

            transactionAmounts.add(logAmount);
        }

        if (transactionAmounts.isEmpty()) transactionAmounts.add(0.0);

        // Obtains smallest, largest, and average transaction.
        double average = 0;
        double min = Math.abs(transactionAmounts.getFirst());
        double max = Math.abs(transactionAmounts.getFirst());
        for (Double amount : transactionAmounts) {
            average += amount / transactionAmounts.size();
            if (Math.abs(amount) < min) min = Math.abs(amount);
            if (Math.abs(amount) > max) max = Math.abs(amount);
        }

        return  "[Accounts]" +
                "\n\tChecking: $" + Utility.formatter.format(this.checkingAccount.getBalance()) +
                "\n\tSavings: $" +  Utility.formatter.format(this.savingsAccount.getBalance()) +
                "\n[Recent Transactions]" +
                "\n\tSmallest: $" + Utility.formatter.format(min) +
                "\n\tLargest: $" + Utility.formatter.format(max) +
                "\n\tAverage: $" + Utility.formatter.format(average);
    }

    // Used only when a new account is created.
    // Writes the attributes of all accounts to 'BankAccounts.txt', comma delimited.
    public void addNewAccountToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BankAccounts.txt", true));
            writer.write(this.username + ", " +
                          this.password + ", " +
                          this.checkingAccount.getBalance() + ", " +
                          this.savingsAccount.getBalance() + ", " +
                          String.join(":", this.transactionLog) +
                          "\n");
            writer.close();
        } catch (Exception e){
            System.out.println("[System Error] Unable to save bank account " + this.username + ".");
        }
    }

    // Used only when a transaction occurs.
    // Saves "BankAccounts.txt" contents into a new list, updating the information of one account within that list.
    // Rewrites the contents of the new list to "BankAccounts.txt".
    public void updateAccountInDatabase(){
        ArrayList<String> lines;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("BankAccounts.txt"));
            lines = new ArrayList<>(reader.lines().toList());
            for(int i = 0; i < lines.size(); i++) {
                String[] accountInfo = lines.get(i).split(", ");
                String name = accountInfo[0];
                if (name.equals(this.username)){
                    lines.set(i, this.username + ", " +
                                 this.password + ", " +
                                 this.checkingAccount.getBalance() + ", " +
                                 this.savingsAccount.getBalance() + ", " +
                                 String.join(":", this.transactionLog));
                    break;
                }
            }
            reader.close();
        } catch (Exception e){
            System.out.println("[System Error] Unable to update account " + this.username + ".");
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BankAccounts.txt"));
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (Exception e){
            System.out.println("[System Error] Unable to rewrite database.");
        }
    }

    // For program launch only.
    // Reads all account information from 'BankAccounts.txt' and saves them into the ACCOUNTS map.
    public static void loadAccountsFile() {
        long accountID = -1;
        long loadedAccounts = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("BankAccounts.txt"));
            for(String line : reader.lines().toList()) {
                accountID++;
                try {
                    String[] accountInfo = line.split(", ");
                    new BankAccount(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3], accountInfo[4]);
                    loadedAccounts++;
                } catch (Exception e){
                    System.out.println("[System Error] Account #" + accountID + " failed to load.");
                }
            }
            reader.close();
        } catch (Exception e){
            System.out.println("[System Error] Unable to fetch database.");
            return;
        }

        System.out.println("[System Message] Found " + loadedAccounts + " bank accounts.");
    }
}
