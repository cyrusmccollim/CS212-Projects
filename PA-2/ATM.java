public class ATM {
    private static boolean isContinuingService = true;
    private static boolean isUsingMenu = true;

    public static void main(String[] args) {
        // Loads bank account information for all holders into memory.
        BankAccount.loadAccountsFile();

        System.out.println("\n----- Welcome to this ATM service. -----");

        while(isContinuingService) {
            System.out.println("Sign into account?");

            isContinuingService = Utility.inputBooleanResponse();
            if (!isContinuingService)
                break;

            // Obtain username from user.
            System.out.println("\t-- Enter your username.");
            String usernameInput = Utility.inputValidUsername();

            // If the username is not found in the database, the option to create a new bank account is provided.
            // The service loop is always restarted if the username is not initially found.
            boolean isExistingAccount = BankAccount.ACCOUNTS.containsKey(usernameInput);
            if (!isExistingAccount) {
                System.out.println("Couldn't find your account. \nCreate new one?");

                boolean createNewAccount = Utility.inputBooleanResponse();
                if (createNewAccount)
                    createNewBankAccount();

                continue;
            }

            // A reference to the associated bank account is obtained once the username is found in the database.
            BankAccount userBankAccount = BankAccount.ACCOUNTS.get(usernameInput);

            // Obtain password from user.
            System.out.println("\t-- Enter your password.");
            String passwordInput = Utility.inputValidPassword();

            // If the incorrect password for the associated bank account is entered, the option to re-try is provided.
            boolean isCorrectPassword = userBankAccount.hasPassword(passwordInput);
            while (!isCorrectPassword) {
                System.out.println("\t-- Incorrect password. Try again?");

                boolean wantsRetry = Utility.inputBooleanResponse();
                if (!wantsRetry)
                    break;

                System.out.println("-- Enter your password.");
                passwordInput = Utility.inputValidPassword();
                isCorrectPassword = userBankAccount.hasPassword(passwordInput);
            }

            // If the user does not wish to retry, the service loop is restarted.
            if (!isCorrectPassword)
                continue;

            // The selection menu for interacting with the associated bank account is presented once the correct password is entered.
            isUsingMenu = true;
            while(isUsingMenu) {
                presentOptionMenu(userBankAccount);
            }

            System.out.println("Signed out.");
        }

        System.out.println("Thank you. Goodbye!");
    }

    // Displays the selection menu for interacting with a bank account.
    public static void presentOptionMenu(BankAccount userBankAccount){
        System.out.println("----- Option Menu: ----- " +
                           "\n1: Deposit " +
                           "\n2: Withdrawal " +
                           "\n3: Transfer " +
                           "\n4: Account Statistics " +
                           "\n5: Recent Transactions " +
                           "\n6: Sign-Out" +
                           "\n------------------------ ");
        int menuOption = Utility.inputMenuOption(1, 6);
        switch (menuOption) {
            case 1:
                handleDeposit(userBankAccount);
                break;
            case 2:
                handleWithdrawal(userBankAccount);
                break;
            case 3:
                handleTransfer(userBankAccount);
                break;
            case 4:
                viewBankAccountStatistics(userBankAccount);
                break;
            case 5:
                viewRecentBankAccountTransactions(userBankAccount);
                break;
            case 6:
                isUsingMenu = false;
                break;
        }
    }

    // Creates a new bank account.
    public static void createNewBankAccount() {
        System.out.println("\t-- Choose your username.");
        String usernameInput = Utility.inputValidUsername();

        while (BankAccount.ACCOUNTS.containsKey(usernameInput)){
            System.out.println("\t-- Username already exists, try again.");
            usernameInput = Utility.inputValidUsername();
        }

        System.out.println("\t-- Choose your password.");
        String passwordInput = Utility.inputValidPassword();

        new BankAccount(usernameInput, passwordInput);

        System.out.println("Account created successfully!");
    }

    // Provides a formatted report of an account's checking balance, savings balance, smallest transaction, largest transaction, and average transaction.
    public static void viewBankAccountStatistics(BankAccount userBankAccount) {
        System.out.println("------ Statistics ------\n" + userBankAccount.getStatistics());
    }

    // Provides the last 5 transactions that occurred on an account.
    public static void viewRecentBankAccountTransactions(BankAccount userBankAccount) {
        System.out.println("----- Transactions -----\n" + userBankAccount.getTransactions());

    }

    // Obtains relevant account and amount for deposit and handles the process.
    public static void handleDeposit(BankAccount userBankAccount) {
        displayAccounts(userBankAccount);
        Account account = switch (Utility.inputMenuOption(1, 2)) {
            case 1 -> userBankAccount.getCheckingAccount();
            case 2 -> userBankAccount.getSavingsAccount();
            default -> userBankAccount.getCheckingAccount();
        };

        System.out.println("\t-- Enter deposit amount.");
        double depositAmount = Utility.inputValidExchange();

        String transactionResult = userBankAccount.deposit(account, depositAmount);
        System.out.println(transactionResult);
    }

    // Obtains relevant account and amount for withdrawal and handles the process.
    public static void handleWithdrawal(BankAccount userBankAccount) {
        displayAccounts(userBankAccount);
        Account account = switch (Utility.inputMenuOption(1, 2)) {
            case 1 -> userBankAccount.getCheckingAccount();
            case 2 -> userBankAccount.getSavingsAccount();
            default -> userBankAccount.getCheckingAccount();
        };

        System.out.println("\t-- Enter withdrawal amount.");
        double depositAmount = Utility.inputValidExchange();

        String transactionResult = userBankAccount.withdraw(account, depositAmount);
        System.out.println(transactionResult);
    }

    // Obtains relevant accounts and amount for transfer and handles the process.
    public static void handleTransfer(BankAccount userBankAccount){
        displayAccounts(userBankAccount);
        System.out.println("\t-- From: ");
        Account fromAccount = switch (Utility.inputMenuOption(1, 2)) {
            case 1 -> userBankAccount.getCheckingAccount();
            case 2 -> userBankAccount.getSavingsAccount();
            default -> userBankAccount.getCheckingAccount();
        };

        System.out.println("\t-- To: ");
        Account toAccount = switch (Utility.inputMenuOption(1, 2)) {
            case 1 -> userBankAccount.getCheckingAccount();
            case 2 -> userBankAccount.getSavingsAccount();
            default -> userBankAccount.getCheckingAccount();
        };

        System.out.println("\t-- Enter transfer amount.");
        double transferAmount = Utility.inputValidExchange();

        String transactionResult = userBankAccount.transfer(fromAccount, toAccount, transferAmount);
        System.out.println(transactionResult);
    }

    public static void displayAccounts(BankAccount userBankAccount){
        System.out.println("------- Accounts -------" +
                "\n1: Checking ($" + Utility.formatter.format(userBankAccount.getCheckingAccount().getBalance()) + ")" +
                "\n2: Savings ($" + Utility.formatter.format(userBankAccount.getSavingsAccount().getBalance()) + ")" +
                "\n------------------------");
    }
}
