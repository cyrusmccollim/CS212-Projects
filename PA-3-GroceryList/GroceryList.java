import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class GroceryList {
    private HashMap<String, Integer> groceryList;
    private String[] log;
    
    public GroceryList(){
        groceryList = new HashMap<>();
        log = new String[10];
    }

    public String getLatestLog(){
       return log[log.length - 1];
    }

    private void updateLog(String latestLog){
        for (int i = 0; i < log.length - 1; i++)
            log[i] = log[i + 1];
        log[log.length - 1] = latestLog;
    }
    
    public void add(String itemName, int quantity){
        if (groceryList.containsKey(itemName)){
            changeQuantity(itemName, quantity);
            return;
        }
        groceryList.put(itemName, quantity);
        updateLog("Successfully added " + quantity + " \"" + itemName + "\".");
    }

    public void remove(String itemName){
        groceryList.remove(itemName);
        updateLog("Successfully removed \"" + itemName + "\".");
    }

    public void erase(){
        groceryList = new HashMap<>();
        updateLog("Successfully erased grocery list.");
    }

    public void changeQuantity(String itemName, int amount){
        if (groceryList.containsKey(itemName)){
            int newQuantity = (groceryList.get(itemName) + amount >= 0)?
                               groceryList.get(itemName) + amount : 0;
            groceryList.put(itemName, newQuantity);
            updateLog("Quantity for \"" + itemName + "\" successfully updated to " + newQuantity + ".");
        } else {
            updateLog("Couldn't find \"" + itemName + "\".");
        }
    }

    public void importFile(String fileName, boolean append){
        HashMap<String, Integer> newGroceryList = new HashMap<>();

        try {
            List<String> lines;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            lines = reader.lines().toList();
            reader.close();
            for (String line : lines){
                String[] groceryItem = line.strip().split(",");
                newGroceryList.put(groceryItem[0], Integer.parseInt(groceryItem[1]));
            }
        } catch (Exception e) {
            updateLog("Failed to complete import from \"" + fileName + "\".");
            return;
        }

        if (!newGroceryList.isEmpty()) {
            if (append) {
                for (String item : newGroceryList.keySet()){
                    groceryList.put(item, newGroceryList.get(item));
                }
            } else {
                groceryList = newGroceryList;
            }
        }

        updateLog("Successfully imported from \"" + fileName + "\".");
    }
    
    public void exportFile(String fileName){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (String item : groceryList.keySet()){
                writer.write(item + "," + groceryList.get(item) + "\n");
            }
            writer.close();
        } catch (Exception e) {
            updateLog("Failed to complete export to \"" + fileName + "\".");
            return;
        }

        updateLog("Successfully exported to \"" + fileName + "\".");
    }

    public String toString(boolean showQuantity){
        String result = "";

        for (String item : groceryList.keySet()){
            result += "\n\t" + item;
            if (showQuantity) result += " (" + groceryList.get(item) + ")";
        }

        if (result.isEmpty()) result += "\n\t (no items)";

        return result;
    }
}
