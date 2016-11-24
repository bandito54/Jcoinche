package cards;

public class Cards
{
	 public static void main(String[] args)
	 {
		    int[] deck = new int[32];
		    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
		    String[] ranks = {"Ace", "7", "8", "9", "10", "Jack", "Queen", "King"};

		    // Initialize cards
		    for (int i = 0; i < deck.length; i++) {
		      deck[i] = i;
		    }

		    // Shuffle the cards
		    for (int i = 0; i < deck.length; i++) {
		      int index = (int)(Math.random() * deck.length);
		      int temp = deck[i];
		      deck[i] = deck[index];
		      deck[index] = temp;
		    }

		    // Display the all the cards
		    for (int i = 0; i < 32; i++) {
		      String suit = suits[deck[i] / 8];
		      String rank = ranks[deck[i] % 8];
		      System.out.println( rank + " of " + suit);
		    }
		  }
}