package cards;

public class Cards
	{
	public static void main(String[] args)
	{
		set_deck();
		int i = 0;
		while (i != 32)
		{
			System.out.println(deck[i]);
			i++;
		}
	
	}
		private static String[] deck = new String[32];
		
		private static void set_deck()
		{
		    int i = 0;
		    while (i != 32)
			{
		    	if (i <= 7)
		    		deck[i] = "heart" + i;
		    	else if (i >= 8 && i <= 16)
		    		deck[i] = "diamond" + i;
		    	else if (i >= 17 && i <= 25)
		    		deck[i] = "spade" + i;
		    	else if (i >= 26 && i <= 32)
		    		deck[i] = "club" + i;
				i++;
			}
		}
	}