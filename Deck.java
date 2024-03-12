import java.util.ArrayList;
public class Deck {
    public ArrayList<Card> deck;

    // Constructor
    public Deck(){
        deck = getDeck();
        deckShuffler();
    }

    // Functions
    public ArrayList<Card> getDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        String[] colors = new String[]{"red", "yellow", "green", "blue"};
        String[] numbers = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        // Number Cards
        for (int i = 0; i < colors.length; i++){
            for (int j = 0; j < numbers.length; j++) {
                // There's only one zero of each color in a deck, but two of every other number
                if (j > 0) {
                    deck.add(new Card(colors[i], numbers[j], 0));
                    deck.add(new Card(colors[i], numbers[j], 0));
                } else {
                    deck.add(new Card(colors[i], numbers[j], 0));
                }
            }
        }

        // Special Cards
        // There won't be any reverse cards in the deck as there are only two players

        // Two Skip cards and two Draw 2 cards of each color
        for (int i = 0; i < colors.length; i++){
            deck.add(new Card(colors[i], "skip", 0));
            deck.add(new Card(colors[i], "skip", 0));
            deck.add(new Card(colors[i], "draw 2", 2));
            deck.add(new Card(colors[i], "draw 2", 2));
        }

        // Four Wild Cards and four Draw 4 cards
        deck.add(new Card("wild", "wild card", 0));
        deck.add(new Card("wild", "wild card", 0));
        deck.add(new Card("wild", "wild card", 0));
        deck.add(new Card("wild", "wild card", 0));

        deck.add(new Card("wild", "draw 4", 4));
        deck.add(new Card("wild", "draw 4", 4));
        deck.add(new Card("wild", "draw 4", 4));
        deck.add(new Card("wild", "draw 4", 4));

        return deck;
    }

    public int getSize(){
        return deck.size();
    }

    public Card getCard(int cardIndex){
        return deck.get(cardIndex);
    }

    public void removeCard(int cardIndex){
        deck.remove(cardIndex);
    }


    public void printDeck(){
        String deckDisplay = "";

        for(int i = 0; i < deck.size(); i++){
            deckDisplay += deck.get(i).printCard();
            deckDisplay += "\n";
        }

        System.out.print(deckDisplay);
    }

    public void deckShuffler(){
        for(int i = 0; i < deck.size(); i++){
            int randInt = (int) Math.round(Math.random()*(deck.size() - 1));

            Card holdCard = deck.get(i);
            deck.set(i, deck.get(randInt));
            deck.set(randInt, holdCard);

        }
    }

}