import java.util.*;

public class Deck {
    private static final List<String> DECK = new ArrayList<>();
    public static final HashMap<String, Integer> SPECIAL_CASES = new HashMap<>();

    static {
        SPECIAL_CASES.put("J", 10);
        SPECIAL_CASES.put("Q", 10);
        SPECIAL_CASES.put("K", 10);
    }

    public Deck(int numPlayers) {

        String[] values = new String[]{"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; 
        String[] suits = new String[]{"C", "H", "S", "D"};

        for (int i = 1; i <= (numPlayers / 2) + 1; i++)
            for (String value : values)
                for (String suit : suits)
                    DECK.add(value + "-" + suit);
    }

    public String grabCard(){
        int randomIndex = (int) (Math.random() * DECK.size());
        String card = DECK.get(randomIndex);
                      DECK.remove(randomIndex);
        return card;
    }
}
