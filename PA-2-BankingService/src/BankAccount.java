import java.io.*;
import java.util.*;

public class BankAccount {
    // "BankAccounts.txt" (Database) <--> BANK_ACCOUNTS (Map)
    // Database Format: "\nName,  Password,    Account Balances,    Transaction Log"
    //                  "\nCyrus, CyrusIsCool, 76.9:56.2:null:null, null:null:null:$[441.89] Checking Deposit:$[-55.98] Savings Withdrawal"
    public static HashMap<String, BankAccount> BANK_ACCOUNTS = new HashMap<>(); // Example: {"Cyrus": (BankAccount), "JaneDoe": (BankAccount)}
    private final String username;
    private final String password;
    private final String[] transactionLog; // Example: [null, null, null, "$[441.89] Checking Deposit", "$[-55.98] Savings Withdrawal"]
    private final ArrayList<Account> accounts = new ArrayList<>(); // Example: <Checking, Savings, EmergencyFund, RetirementAccount>

    //////////////////////
    //
    //  CONSTRUCTORS
    //
    //////////////////////

    // Initializes a brand-new bank account.
    public BankAccount(String username, String password){
        this.username = username;
        this.password = password;

        transactionLog = new String[5];

        // Initializes all account types at an inactive state.
        for (Integer accountTypeID : Account.ACCOUNT_TYPES.keySet())
            accounts.add(new Account(accountTypeID)); // Inactive

        BANK_ACCOUNTS.put(username, this);
        addBankAccountToDatabase();
    }

    // Initializes an existing bank account.
    public BankAccount(String username, String password, String[] accountBalances, String[] latestTransactions){
        this.username = username;
        this.password = password;

        // Initializes the transaction log. If there isn't a transaction at any given position, the log is filled with null.
        transactionLog = latestTransactions;
        for (int i = 0; i < transactionLog.length; i++)
            if (transactionLog[i].equals("null"))
                transactionLog[i] = null;

        // Initializes all account balances.
        // If there is no balance on an account type, the account is initialized at an inactive state.
        for (int i = 0; i < accountBalances.length; i++){
            try {
                accounts.add(new Account(i, Double.parseDouble(accountBalances[i]))); // Active
            } catch (Exception e){
                accounts.add(new Account(i)); // Inactive
            }
        }

        BANK_ACCOUNTS.put(username, this);
    }

    //////////////////////
    //
    //  DATA ACCESSORS
    //
    //////////////////////

    // Returns whether a password entry is correct.
    public boolean isCorrectPassword(String passwordInput){
        return password.equals(passwordInput);
    }

    // Returns the account object with the corresponding ID number.
    public Account getAccountByID(int ID){
        for (Account account : accounts)
            if (account.getID() == ID)
                return account;
        return accounts.getFirst();
    }

    // Returns a list of the values of every logged transaction.
    private ArrayList<Double> getTransactionLogAmounts() {
        ArrayList<Double> transactionAmounts = new ArrayList<>();

        // Traverses the transaction log, finds the position of the amount, adds the amount to the list.
        for (String log : transactionLog){
            if (log == null) continue;

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

        // If there are no logs at all, this prevents an error.
        if (transactionAmounts.isEmpty()) transactionAmounts.add(0.0);

        return transactionAmounts;
    }

    //////////////////////
    //
    //  TRANSACTION HANDLERS
    //
    //////////////////////

    // Attempts to perform a deposit, also returns message about transaction results.
    public String deposit(Account account, double depositAmount){
        account.deposit(depositAmount);

        // Updating the transaction log and database.
        setLastLoggedTransaction("[$" + ErrorHandler.formatter.format(depositAmount) + "] " + account.getName() + " Deposit");
        updateBankAccountDatabase();

        return "Transaction successful!";
    }

    // Attempts to perform a withdrawal, also returns message about transaction results.
    public String withdraw(Account account, double requestedWithdrawal){
        // Prevents an overdraft.
        double finalWithdrawalAmount = (account.getBalance() - requestedWithdrawal >= 0)?
                                       requestedWithdrawal: account.getBalance();

        account.withdraw(finalWithdrawalAmount);

        // Updating the transaction log and database.
        setLastLoggedTransaction("[$" + ErrorHandler.formatter.format(finalWithdrawalAmount) + "] " + account.getName() + " Withdrawal");
        updateBankAccountDatabase();

        // Let the user know if they could not withdraw their desired amount.
        if (finalWithdrawalAmount < requestedWithdrawal) return "Withdrew $" + finalWithdrawalAmount + " due to insufficient funds.";

        return "Transaction successful!";
    }

    // Attempts to perform a transfer, also returns message about transaction results.
    public String transfer(Account fromAccount, Account toAccount, double requestedTransfer){
        // Prevents an overdraft.
        double finalTransferAmount = (fromAccount.getBalance() - requestedTransfer >= 0)?
                                     requestedTransfer: fromAccount.getBalance();

        fromAccount.transfer(toAccount, finalTransferAmount);

        setLastLoggedTransaction("[$" + ErrorHandler.formatter.format(finalTransferAmount) + "] " + fromAccount.getName() + " -> " +  toAccount.getName() + " Transfer");
        updateBankAccountDatabase();

        // Let the user know if they could not transfer their desired amount.
        if (finalTransferAmount < requestedTransfer) return "Transferred $" + finalTransferAmount + " due to insufficient funds.";

        return "Transaction successful!";
    }

    // Shifts all transactions down one position then updates the last transaction.
    public void setLastLoggedTransaction(String log){
        int lastIndex = transactionLog.length - 1;
        for (int i = 0; i < lastIndex; i++)
            transactionLog[i] = transactionLog[i + 1];
        transactionLog[lastIndex] = log;
    }

    //////////////////////
    //
    //  FORMATTED REPORTS
    //
    //////////////////////

    // Returns a formatted report of all account balances.
    // Boolean Parameters:
    //          showInactiveAccounts: Will display accounts that haven't been interacted with.
    //          showID: Will show an account's ID number next to its name.
    public String getAccountBalancesReport(String delimiter, boolean showInactiveAccounts, boolean showID){
        String accountsDetails = "";

        for (Account account : accounts){
            if (!showInactiveAccounts && !account.isActive()) continue;

            String ID = (showID)? "[" + account.getID() + "] " : "";
            String balance = (account.isActive())? "$" + ErrorHandler.formatter.format(account.getBalance()) : "(inactive)";

            accountsDetails += delimiter + ID + account.getName() + ": " + balance;

        }

        if (accountsDetails.isEmpty()) return delimiter + "(none active)";

        return accountsDetails;
    }

    // Returns a formatted report of the transactions log.
    public String getTransactionsReport(){
        ArrayList<String> transactionLogFiltered = new ArrayList<>();
        for (String log : transactionLog)
            if (log != null)
                transactionLogFiltered.add(log);

        String rawTransactionsString = String.join("\n\t", transactionLogFiltered).strip();

        if (rawTransactionsString.isBlank()) return "No recent transactions.";

        return "----- Transactions -----" +
               "\n(Earliest)" +
               "\n\t" + rawTransactionsString +
               "\n(Latest)";
    }

    // Returns a formatted report of a bank account's account balances, smallest transaction, largest transaction, and average transaction.
    public String getStatisticsReport(){
        ArrayList<Double> transactionValues = getTransactionLogAmounts();

        // Obtains smallest, largest, and average transaction.
        double average = 0;
        double min = Math.abs(transactionValues.getFirst());
        double max = Math.abs(transactionValues.getFirst());
        for (Double amount : transactionValues) {
            average += amount / transactionValues.size();
            if (Math.abs(amount) < min) min = Math.abs(amount);
            if (Math.abs(amount) > max) max = Math.abs(amount);
        }

        return  "------ Statistics ------\n" +
                "[Accounts]" +
                getAccountBalancesReport("\n\t", false, false) +
                "\n[Recent Transactions]" +
                "\n\tSmallest: $" + ErrorHandler.formatter.format(min) +
                "\n\tLargest: $" + ErrorHandler.formatter.format(max) +
                "\n\tAverage: $" + ErrorHandler.formatter.format(average);
    }

    //////////////////////
    //
    //  DATABASE MODIFIERS
    //
    //////////////////////

    // Used only when a new account is created.
    // Writes the attributes of all accounts to 'BankAccounts.txt', comma delimited.
    public void addBankAccountToDatabase(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BankAccounts.txt", true));

            // Gathers the banks account's account balances into a list.
            // If an account is inactive, "inactive" is entered in place of "0.0".
            ArrayList<String> accountBalances = new ArrayList<>();
            for (Account account : accounts){
                if (account.isActive()) accountBalances.add(String.valueOf(account.getBalance()));
                else                    accountBalances.add("inactive");
            }

            // Writing the data to the file.
            writer.write(username + ", " +
                          password + ", " +
                          String.join(":", accountBalances) + ", " +
                          String.join(":", transactionLog) +
                          "\n");
            writer.close();
        } catch (Exception e){
            System.out.println("[Database Error] Unable to save bank account " + username + ".");
        }
    }

    // Used only when a transaction occurs.
    // Saves "BankAccounts.txt" contents into a new list of bank accounts, updating the information of this account within that list.
    // Rewrites the contents of the new list to "BankAccounts.txt".
    public void updateBankAccountDatabase(){
        List<String> lines;

        // Loads database into a list.
        // Edits this bank account's information within that list.
        try {
            // Opens and traverses "BankAccounts.txt", placing each line into the list.
            BufferedReader reader = new BufferedReader(new FileReader("BankAccounts.txt"));
            lines = reader.lines().toList();

            // Traverses the list of bank accounts.
            // Once it finds the bank account belonging to this object (via username), its information in the list is updated.
            for(int i = 0; i < lines.size(); i++) {
                String username = lines.get(i).split(", ")[0];
                if (username.equals(this.username)){
                    // Gathers the banks account's account balances into a list.
                    // If an account is inactive, "inactive" is entered in place of "0.0".
                    ArrayList<String> accountBalances = new ArrayList<>();
                    for (Account account : accounts){
                        if (account.isActive()) accountBalances.add(String.valueOf(account.getBalance()));
                        else                    accountBalances.add("inactive");
                    }

                    // Updating the information of this account within the bank accounts list.
                    lines.set(i, this.username + ", " +
                                 password + ", " +
                                 String.join(":", accountBalances) + ", " +
                                 String.join(":", transactionLog));
                    break;
                }
            }
            reader.close();
        } catch (Exception e){
            System.out.println("[Database Error] Unable to update account " + username + ".");
            return;
        }

        // Re-writes the database.
        // Runs if the information for this bank account was successfully updated in the list.
        try {
            // Opens "BankAccounts.txt" and rewrites all lines, using the list with updated data.
            BufferedWriter writer = new BufferedWriter(new FileWriter("BankAccounts.txt"));
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (Exception e){
            System.out.println("[Database Error] Unable to rewrite database.");
        }
    }

    // For program launch only.
    // Reads all account information from 'BankAccounts.txt' and saves them into the BANK_ACCOUNTS map.
    public static void loadDatabase() {
        long loadedAccounts = 0; // Used for logging the amount of successfully loaded bank accounts.

        try {
            // Opens and traverses "BankAccounts.txt", creating a new BankAccount object with the information in each line.
            BufferedReader reader = new BufferedReader(new FileReader("BankAccounts.txt"));
            List<String> lines = reader.lines().toList();
            for(int i = 0; i < lines.size(); i++) {
                try {
                    String[] accountInfo = lines.get(i).split(", ");
                    new BankAccount(accountInfo[0], accountInfo[1], accountInfo[2].split(":"), accountInfo[3].split(":"));
                    loadedAccounts++;
                } catch (Exception e){
                    System.out.println("[Database Error] Account #" + (i + 1) + " failed to load.");
                }
            }
            reader.close();
        } catch (Exception e){
            System.out.println("[Database Error] Unable to fetch database.");
            return;
        }

        System.out.println("[Database Message] Found " + loadedAccounts + " bank accounts.");
    }
}
