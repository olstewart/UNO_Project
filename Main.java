import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create first deck
        Deck mainDeck = new Deck();

        // Create two hands of cards
        ArrayList<Card> cpuHand = new ArrayList<>();
        ArrayList<Card> playerHand = new ArrayList<>();

        // Each hand (of seven cards, to start) is passed out
        for (int i = 0; i < 7; i++) {
            cpuHand.add(mainDeck.getCard(0));
            mainDeck.removeCard(0);
        }

        for (int i = 0; i < 7; i++) {
            playerHand.add(mainDeck.getCard(0));
            mainDeck.removeCard(0);
        }

        // Assign the first Card
        Card topCard = mainDeck.getCard(0);
        mainDeck.removeCard(0);

        // The player will go first
        boolean playersTurn = true;
        // no one has won yet
        boolean playerWin = false;
        boolean cpuWin = false;
        // Scanner for choice input
        Scanner input = new Scanner(System.in);
        int choice;
        // to see if a skip is in play
        boolean skipInPlay = false;


        //------------------------------------------------------------------//
        // Main loop
        while (mainDeck.getSize() >= 1 && (!playerWin && !cpuWin)) {

            // Player Turn **NO DRAW 2, DRAW 4, OR SKIP YET**
            while (playersTurn && !cpuWin && !playerWin) {
                // declare turn
                System.out.println("\n-----------------Player turn--------------\n");

                // special card stuff
                // if skip, no turn
                //if(playersTurn && topCard.getType().equals("skip")){
                    //System.out.println("Your Turn is skipped.\n");
                    //playersTurn = false;
                //}


                // carcass of whatever the card drawing system was supposed to be
                // if draw, draw said cards
                //if(playersTurn && topCard.getType().equals("draw")){
                    // print draw message
                    //System.out.println("You must draw " + topCard.getDraw() + " cards.");
                    // accept draw input until all cards are drawn
                    //int cardsDrawn = topCard.getDraw();
                    //while(cardsDrawn > 0 && mainDeck.getSize() >= 1){
                        //System.out.println("Enter 0 to draw.");
                        //choice = input.nextInt();
                        //if(choice == 0){
                            // draw card if 0 is pressed
                            //playerHand.add(mainDeck.getCard(0));
                            //System.out.println("You have drawn: " + mainDeck.getCard(0).printCard());
                            //mainDeck.removeCard(0);
                            //cardsDrawn--;
                        //}
                    //}
                    // declare that all cards have been drawn
                    //System.out.println("All cards have been drawn.\n");
                    //if (mainDeck.getSize() < 1){
                       // System.out.println("There are no more cards in the deck.");
                        //return;
                   // }
                //}

                if(playersTurn && playerHand.size() >= 1){
                    //print hand
                    System.out.println("Your Hand:");
                    printHand(playerHand);

                    // if uno, print out UNO!
                    if(playerHand.size() == 1){System.out.println("You have UNO!");}

                    // display the Top Card
                    System.out.println("Top Card: " + topCard.printCard());

                    // Choose card or draw card if possible
                    System.out.println("Choose a card to play or enter 0 to draw");

                    //accept card input
                    choice = input.nextInt() - 1;

                    // if a valid choice, play card.
                    while (playersTurn){
                        //if invalid choice, choose new input
                        if (choice > playerHand.size()){
                            System.out.println("Invalid choice, please try again.");
                            choice = input.nextInt() - 1;
                        }
                        // choose card if choice is above zero
                        else if(choice >= 0 && (playerHand.get(choice).getType().equals(topCard.getType()) || playerHand.get(choice).getColor().equals(topCard.getColor()) || playerHand.get(choice).getColor().equals("wild"))) {
                            System.out.println("You play: " + playerHand.get(choice).printCard());

                            // if played card is a wild card, change the color.
                            if(playerHand.get(choice).getColor().equals("wild")){
                                System.out.print("""
                                        You played a color changing card. What should the new card color be?
                                        [1] Red
                                        [2] Yellow
                                        [3] Green
                                        [4] Blue
                                        """);
                                int colorChoice = input.nextInt();
                                while(colorChoice > 4 || colorChoice < 1){
                                    System.out.println("Choose a color above.");
                                    colorChoice = input.nextInt();
                                }
                                switch (colorChoice) {
                                    case 1 -> {
                                        playerHand.get(choice).setColor("red");
                                        System.out.println("Color changed to red!");
                                    }
                                    case 2 -> {
                                        playerHand.get(choice).setColor("yellow");
                                        System.out.println("Color changed to yellow!");
                                    }
                                    case 3 -> {
                                        playerHand.get(choice).setColor("green");
                                        System.out.println("Color changed to green!");
                                    }
                                    case 4 -> {
                                        playerHand.get(choice).setColor("blue");
                                        System.out.println("Color changed to blue!");
                                    }
                                }
                            }

                            topCard.setCard(playerHand.get(choice));

                            // if this is the players last turn, they win!
                            if (playerHand.size() == 1){
                                playerWin = true;
                            }

                            playerHand.remove(choice);
                            playersTurn = false;
                        }
                        // draw card if choice is less than 0
                        else if (choice < 0){
                            while(choice < 0){
                                // if draw is chosen, draw card
                                if(mainDeck.getSize() >= 1){
                                    System.out.println("You draw: " + mainDeck.getCard(0).printCard() + "\n");
                                    playerHand.add(mainDeck.getCard(0));
                                    mainDeck.removeCard(0);

                                    printHand(playerHand);
                                    System.out.println("Top card: " + topCard.printCard());
                                    System.out.println("Enter a new choice.");
                                    choice = input.nextInt() - 1;
                                }
                            }
                        }
                        // if the card isnt playable, accept new input
                        else{

                            System.out.println("That card is not playable. Please enter new choice.");
                            choice = input.nextInt() - 1;
                        }
                    }

                }
            }

            // Computer Turn **UNFINISHED** --------//
            while (!playersTurn && !cpuWin && !playerWin){
                // declare turn
                System.out.println("\n-----------------CPU turn--------------\n");

                //play the first card that matches the topCard
                //cycle through every card until you find a playable one
                //play it

                boolean cpuCardPlayable = false;
                for (int i = 0; i < cpuHand.size(); i++){
                    // if a match, play the card, set cpuCardPlayable to true
                    if(cpuHand.get(i).getColor().equals(topCard.getColor()) || cpuHand.get(i).getType().equals(topCard.getType()) || cpuHand.get(i).getColor().equals("wild")){
                        topCard.setCard(cpuHand.get(i));
                        cpuCardPlayable = true;
                        break;
                    }
                }
                //if no matches, draw until match



                playersTurn = true;
            }
        }

        // declare winner if deck is out
        if (mainDeck.getSize() < 1){
            if (cpuHand.size() > playerHand.size()){
                System.out.println("No more cards in the deck, Computer wins.");
            }
            else{
                System.out.println("No more cards in the deck, you win!");
            }
        }

        // Declare winner otherwise
        if (playerWin){
            System.out.println("\n-------------------------");
            System.out.println("Congratulations! You win!");
            System.out.println("-------------------------");
        }
        if (cpuWin){
            System.out.println("\n-------------------------------------");
            System.out.println("Computer wins. Better luck next time!");
            System.out.println("-------------------------------------");
        }

    }

    public static void printHand(ArrayList<Card> hand){
        for(int i = 0; i < hand.size(); i++){
            System.out.println("[" + (i + 1) + "] " + hand.get(i).printCard());
        }
    }

    void playerTurn(){}

    void CPUTurn(){}


}