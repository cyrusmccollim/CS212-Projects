public class ATMService {
    private static boolean isContinuingService = true; // True while the main program is still running.
    private static boolean isInteractingWithBankAccount = true; // True while a bank account is being interacted with.

    public static void main(String[] args) {
        // Loads bank account information from database.
        BankAccount.loadDatabase();

        System.out.println("\n----- Welcome to this ATM service. -----");

        while(isContinuingService) {
            System.out.println("Sign into account?");
            isContinuingService = ErrorHandler.inputBooleanResponse();
            if (!isContinuingService) break;

            // Obtain username from user.
            System.out.println("\t-- Enter your username.");
            String usernameInput = ErrorHandler.inputValidUsername();
            boolean isExistingBankAccount = BankAccount.BANK_ACCOUNTS.containsKey(usernameInput);

            // If the username is not found in the database, the option to create a new bank account is provided.
            // The service loop is always restarted if the username is not initially found.
            if (!isExistingBankAccount) {
                System.out.println("Couldn't find your account. \nCreate new one?");
                boolean wantsCreateNewBankAccount = ErrorHandler.inputBooleanResponse();
                if (wantsCreateNewBankAccount) createNewBankAccount();
                continue;
            }

            // Obtain password from user.
            System.out.println("\t-- Enter your password.");
            String passwordInput = ErrorHandler.inputValidPassword();
            boolean enteredCorrectPassword = BankAccount.BANK_ACCOUNTS.get(usernameInput).isCorrectPassword(passwordInput);

            // If the incorrect password for the associated bank account is entered, the option to re-try is provided.
            while (!enteredCorrectPassword) {
                System.out.println("\t-- Incorrect password. Try again?");
                boolean wantsRetry = ErrorHandler.inputBooleanResponse();
                if (!wantsRetry) break;

                System.out.println("\t-- Enter your password.");
                passwordInput = ErrorHandler.inputValidPassword();
                enteredCorrectPassword = BankAccount.BANK_ACCOUNTS.get(usernameInput).isCorrectPassword(passwordInput);
            }

            // If the user does not wish to retry, the service loop is restarted.
            if (!enteredCorrectPassword) continue;

            // A reference to the associated bank account is obtained once the password entered is correct.
            BankAccount userBankAccount = BankAccount.BANK_ACCOUNTS.get(usernameInput);
            isInteractingWithBankAccount = true;

            // The selection menu for interacting with the associated bank account is presented once the correct password is entered.
            while(isInteractingWithBankAccount) {
                presentOptionMenu(userBankAccount);
            }

            System.out.println("Signed out.");
        }

        System.out.println("Thank you. Goodbye!");
    }

    // Displays the selection menu for interacting with a bank account.
    public static void presentOptionMenu(BankAccount userBankAccount){
        System.out.println("----- Options Menu ----- " +
                           "\n0: Deposit " +
                           "\n1: Withdrawal " +
                           "\n2: Transfer " +
                           "\n3: Account Statistics " +
                           "\n4: Recent Transactions " +
                           "\n5: Sign-Out" +
                           "\n------------------------ ");
        int menuOption = ErrorHandler.inputMenuOption(6);
        switch (menuOption) {
            case 0:
                handleDeposit(userBankAccount);
                break;
            case 1:
                handleWithdrawal(userBankAccount);
                break;
            case 2:
                handleTransfer(userBankAccount);
                break;
            case 3:
                displayBankAccountStatistics(userBankAccount);
                break;
            case 4:
                displayBankAccountTransactions(userBankAccount);
                break;
            default:
                isInteractingWithBankAccount = false;
                break;
        }
    }

    // Creates a new bank account.
    public static void createNewBankAccount() {
        System.out.println("\t-- Choose your username.");
        String usernameInput = ErrorHandler.inputValidUsername();

        // Re-prompts for username choice if it already exists.
        while (BankAccount.BANK_ACCOUNTS.containsKey(usernameInput)){
            System.out.println("\t-- Username already exists, try again.");
            usernameInput = ErrorHandler.inputValidUsername();
        }

        System.out.println("\t-- Choose your password.");
        String passwordInput = ErrorHandler.inputValidPassword();

        new BankAccount(usernameInput, passwordInput);

        System.out.println("Account created successfully!");
    }

    // Provides a formatted report of a bank account's account balances, largest transaction, and average transaction.
    public static void displayBankAccountStatistics(BankAccount userBankAccount) {
        System.out.println(userBankAccount.getStatisticsReport());
    }

    // Provides the last five transactions that occurred on a bank account.
    public static void displayBankAccountTransactions(BankAccount userBankAccount) {
        System.out.println(userBankAccount.getTransactionsReport());

    }

    // Displays a formatted report of a bank account's account balances.
    public static void displayAccountBalances(BankAccount userBankAccount){
        System.out.println("------- Accounts -------" +
                           userBankAccount.getAccountBalancesReport("\n", true, true) +
                           "\n------------------------");
    }

    // Obtains the relevant account and amount for handling the deposit process.
    public static void handleDeposit(BankAccount userBankAccount) {
        displayAccountBalances(userBankAccount);

        int accountIDInput = ErrorHandler.inputMenuOption(Account.ACCOUNT_TYPES.size());
        Account account = userBankAccount.getAccountByID(accountIDInput);

        System.out.println("\t-- Enter deposit amount.");
        double depositAmount = ErrorHandler.inputPositiveDouble();

        String transactionResult = userBankAccount.deposit(account, depositAmount);
        System.out.println(transactionResult);
    }

    // Obtains the relevant account and amount for handling the withdrawal process.
    public static void handleWithdrawal(BankAccount userBankAccount) {
        displayAccountBalances(userBankAccount);

        int accountIDInput = ErrorHandler.inputMenuOption(Account.ACCOUNT_TYPES.size());
        Account account = userBankAccount.getAccountByID(accountIDInput);

        System.out.println("\t-- Enter withdrawal amount.");
        double depositAmount = ErrorHandler.inputPositiveDouble();

        String transactionResult = userBankAccount.withdraw(account, depositAmount);
        System.out.println(transactionResult);
    }

    // Obtains the relevant accounts and amount for handling the transfer process.
    public static void handleTransfer(BankAccount userBankAccount){
        displayAccountBalances(userBankAccount);

        System.out.println("\t-- From: ");
        int fromAccountIDInput = ErrorHandler.inputMenuOption(Account.ACCOUNT_TYPES.size());
        Account fromAccount = userBankAccount.getAccountByID(fromAccountIDInput);

        System.out.println("\t-- To: ");
        int toAccountIDInput = ErrorHandler.inputMenuOption(Account.ACCOUNT_TYPES.size());
        Account toAccount = userBankAccount.getAccountByID(toAccountIDInput);

        System.out.println("\t-- Enter transfer amount.");
        double transferAmount = ErrorHandler.inputPositiveDouble();

        String transactionResult = userBankAccount.transfer(fromAccount, toAccount, transferAmount);
        System.out.println(transactionResult);
    }
}
