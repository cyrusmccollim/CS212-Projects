import java.util.*;

public class Player {
    // Contains references to all players.
    public static final List<Player> PLAYERS = new ArrayList<>();

    // Creates player objects.
    public static void initializePlayers(int numPlayers){
        for (int i = 0; i < numPlayers; i++){
            System.out.print("What's the name of player " + (i + 1) + "? ");
            Player.PLAYERS.add(new Player(Utilities.inputString()));
        }
    }

    // Attributes.
    private final List<String> hand = new ArrayList<>();
    private final String name;
    private int points;

    // General constructor.
    public Player(String name){
        this.name = name;
        points = 0;
    }

    // Returns the name of the player.
    public String getName(){
        return name;
    }

    // Returns the total point value of a player's cards.
    public int getPoints(){
        return points;
    }

    // Adds a card to the player's hand.
    public void addCard(String card){
        hand.add(card);

        String cardValue = card.substring(0, card.indexOf("-"));

        // Requests for a player's desired point value upon drawing an ace.
        if (cardValue.equals("A")) {
            System.out.println("An ace was drawn! Count as 1 (A) or 11 (B) points?");
            String choice = Utilities.inputString(new String[]{"A", "B"});
            points += (choice.equalsIgnoreCase( "A")) ? 1 : 11;
            return;
        }

        points += (Utilities.isInt(cardValue)) ? Integer.parseInt(cardValue) : Deck.SPECIAL_CASES.get(cardValue);
    }

    // Prints a visualization of a player's hand.
    public void displayHand(boolean hideLast){
        List<String> handTemp = new ArrayList<>(hand);
        if (hideLast) {
            handTemp.set(handTemp.size() - 1, "?-?");
            System.out.println("\t" + name + ": " + String.join(" ", handTemp) + " (?)");
            return;
        }
        System.out.println("\t" + name + ": " + String.join(" ", handTemp) + " (" + getPoints() + ")");
    }
}
