public class BlackjackGame {
    private static Deck gameDeck;

    public static void main(String[] args){
        // Introducing user to program.
        System.out.println("Welcome to Blackjack! How many are playing (1-7)? ");
        int numPlayers = Utilities.inputInt(1, 7);

        // Creating a fresh deck.
        gameDeck = new Deck(numPlayers);

        // Creating player objects.
        Player.initializePlayers(numPlayers);
        Player dealer = new Player("[Dealer]");

        // Distributes initial two cards.
        for (Player player : Player.PLAYERS){
            player.addCard(gameDeck.grabCard());
            player.addCard(gameDeck.grabCard());
            player.displayHand(false);
        }

        dealer.addCard(gameDeck.grabCard());
        dealer.addCard(gameDeck.grabCard());
        dealer.displayHand(true);

        // The decision round, where players can either hit or stand.
        // For loop runs backwards to avoid conflicts with removing players who bust.
        for (int i = Player.PLAYERS.size() - 1; i >= 0; i--){
            playerTurn(Player.PLAYERS.get(i));
        }

        // Dealer hits until total is large enough.
        while (dealer.getPoints() < 17){
            dealer.addCard(gameDeck.grabCard());
            dealer.displayHand(false);
        }

        // All remaining players win their bet if the dealer busts.
        boolean dealerBusts = dealer.getPoints() > 21;
        if (dealerBusts) {
            System.out.println("Dealer has busted!");
            for (Player player : Player.PLAYERS){
                System.out.println(player.getName() + " won their bet!");
            }
        // Otherwise, all remaining players receive an outcome dependent on their point relation to the dealer.
        }  else {
            for (Player player : Player.PLAYERS){
                if (player.getPoints() == dealer.getPoints())
                    System.out.println(player.getName() + " tied and keeps their bet.");
                else if (player.getPoints() > dealer.getPoints())
                    System.out.println(player.getName() + " won their bet!");
                else
                    System.out.println(player.getName() + " lost their bet!");
            }
        }

        System.out.println("Thank you for playing!");
    }

    // Handles a player's turn. They may choose to stand immediately or hit until they bust.
    public static void playerTurn(Player player){
        while (playerHits(player)){
            player.addCard(gameDeck.grabCard());
            player.displayHand(false);
            boolean playerBusts = player.getPoints() > 21;
            if (playerBusts) {
                System.out.println(player.getName() + " lost their bet and is out of the game!");
                Player.PLAYERS.remove(player);
                break;
            }
        }
    }

    // Simply returns a player's choice to hit or stand.
    public static boolean playerHits(Player player){
        System.out.println(player.getName() + ", Hit (H) or Stand (S)? ");
        return Utilities.inputString(new String[]{"H", "S"}).equalsIgnoreCase("H");
    }
}
