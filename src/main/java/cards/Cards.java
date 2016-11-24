package cards;

public class Cards {
	public static void main(String[] args)
	 {
		    int[] deck = new int[32];
		    String[] suits = {"Pique", "Coeur", "Carreau", "Trèfle"};
		    int[] ranks = {7, 8, 9, 10, 11, 12, 13, 14};

		    // Initialize cards
		    for (int i = 0; i < deck.length; i++)
		    {
		      deck[i] = i;
		    }

		    // Shuffle the cards
		    for (int i = 0; i < deck.length; i++)
		    {
		      int index = (int)(Math.random() * deck.length);
		      int temp = deck[i];
		      deck[i] = deck[index];
		      deck[index] = temp;
		    }

		    // Display the all the cards
		    for (int i = 0; i < 32; i++)
		    {
		    	  if (i >= 16)
			      	{
			    	  String suit2 = suits[deck[i] / 8];
				      int rank2 = ranks[deck[i] % 8];
				      System.out.println(suit2 + " de " + rank2 + " ----->Joueur 2");
			      	}
		    	  else
		    	  {
		    		  String suit = suits[deck[i] / 8];
		    		  int rank = ranks[deck[i] % 8];
		    		  System.out.println(suit + " de " + rank);
		    	  }
		      }
		    }
}