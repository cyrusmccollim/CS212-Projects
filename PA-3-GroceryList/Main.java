import java.util.*;
public class Main {
    public static void main(String[] args){
        GroceryList usersGroceries = new GroceryList();

        System.out.println("\nWelcome to your grocery list manager!\n");

        boolean usingMenu = true;
        while (usingMenu) {
            System.out.println("Options:\n" +
                    "---------------------------\n" +
                    "I: Import\n" +
                    "E: Export\n" +
                    "P: Print\n" +
                    "1: Add Item\n" +
                    "2: Delete Item\n" +
                    "3: Change Item Quantity\n" +
                    "4: Erase All\n" +
                    "Q: Quit\n" +
                    "---------------------------");
            String option = Utility.inputMenuOption(new ArrayList<>(Arrays.asList("I", "E", "P", "1", "2", "3", "4", "Q")));
            switch(option){
                case "I": // Import
                    System.out.println("Import what file? ");
                    String importFile = Utility.inputString();
                    System.out.println("Combine/Overwrite? (C/O) ");
                    boolean append = Utility.inputString().equalsIgnoreCase("C");
                    if (Utility.actionCancelled()) break;
                    usersGroceries.importFile(importFile, append);
                    System.out.println("\n" + usersGroceries.getLatestLog() + "\n");
                    break;
                case "E": // Export
                    System.out.println("Export to what file? ");
                    String exportFile = Utility.inputString();
                    if (Utility.actionCancelled()) break;
                    usersGroceries.exportFile(exportFile);
                    System.out.println("\n" + usersGroceries.getLatestLog() + "\n");
                    break;
                case "P": //Print
                    System.out.println("Include quantities? (Y/N) ");
                    boolean includeQuantities = Utility.inputString().equalsIgnoreCase("Y");
                    System.out.println("\nGrocery List:" + usersGroceries.toString(includeQuantities) + "\n");
                    break;
                case "1": // Add Item
                    System.out.println("Name of item? ");
                    String addItemName = Utility.inputString();
                    System.out.println("Quantity of item? ");
                    int quantity = Utility.inputPositiveInteger();
                    usersGroceries.add(addItemName, quantity);
                    System.out.println("\n" + usersGroceries.getLatestLog() + "\n");
                    break;
                case "2": // Remove Item
                    System.out.println("Name of item? ");
                    String removeItemName = Utility.inputString();
                    usersGroceries.remove(removeItemName);
                    System.out.println("\n" + usersGroceries.getLatestLog() + "\n");
                    break;
                case "3": // Change Item Quantity
                    System.out.println("Name of item? ");
                    String changeQuantityItemName = Utility.inputString();
                    System.out.println("Quantity to change by? ");
                    int changeQuantity = Utility.inputInteger();
                    usersGroceries.changeQuantity(changeQuantityItemName, changeQuantity);
                    System.out.println("\n" + usersGroceries.getLatestLog() + "\n");
                    break;
                case "4": // Erase List
                    if (Utility.actionCancelled()) break;
                    usersGroceries.erase();
                    System.out.println("\n" + usersGroceries.getLatestLog() + "\n");
                    break;
                case "Q": // Quit
                    usingMenu = false;
                    break;
            }
        }

        System.out.println("\nThank you. Goodbye!");
    }
}
