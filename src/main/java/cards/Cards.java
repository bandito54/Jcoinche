package cards;

import java.util.Vector;
import java.util.Iterator;


public class Cards 
{
	private static Vector<Integer> victor = new Vector<Integer>(32);
	private static Vector<Integer> TMP = new Vector<Integer>(32);
	public static Vector<Integer> D1 = new Vector<Integer>(32);
	public static Vector<Integer> D2 = new Vector<Integer>(32);
	private static Vector<Integer> Cim = new Vector<Integer>(32);
	private static Vector<Integer> Cim2 = new Vector<Integer>(32);
	
	public static void main(String[] args)
	{
		fill_deck();
		check_value(82, 84);
		split_deck();
		
		Iterator<Integer> iterator = D1.iterator();  
 	   	Iterator<Integer> iterator2 = D2.iterator(); 
 	   while (iterator.hasNext())
 	   {
 		   System.out.println("D1 contains "+iterator.next() + " ");  
 		   System.out.println("D2 contains "+iterator2.next() + " ");  
 	   }
	}
	
	public static void check_value(int res, int res2)
	{
		if (res / 10 < res2 / 10)
			System.out.println(res + " < " + res2 + " Player Two Win");
		else if (res / 10 > res2 / 10)
			System.out.println(res + " > " + res2 + " Player One Win");
		else
			System.out.println("It's a draw !");
	}
	
	public static void split_deck()
	{
    	for (int K = 0; K < 32; K++)
    	{
    	    int add = (int)(Math.random() * 32);
    	    while (TMP.contains(add)) 
    	    {
    	        add = (int) (Math.random() * 32);
    	    }
    	    TMP.add(add);
    	}
    	   int i = 0;
    	   while (i != 16)
    	   {
    		   D1.add(victor.get(TMP.get(i)));
    		   i++;
    	   }
    	   while (i != 32)
    	   {
    		   D2.add(victor.get(TMP.get(i)));
    		   i++;
    	   }	   
	}
	
	public static void fill_deck()
	{
		victor.add(11);
		victor.add(12);
		victor.add(13);
		victor.add(14);
		victor.add(21);
		victor.add(22);
		victor.add(23);
		victor.add(24);
		victor.add(31);
		victor.add(32);
		victor.add(33);
		victor.add(34);
		victor.add(41);
		victor.add(42);
		victor.add(43);
		victor.add(44);
		victor.add(51);
		victor.add(52);
		victor.add(53);
		victor.add(54);
		victor.add(61);
		victor.add(62);
		victor.add(63);
		victor.add(64);
		victor.add(71);
		victor.add(72);
		victor.add(73);
		victor.add(74);
		victor.add(81);
		victor.add(82);
		victor.add(83);
		victor.add(84);
	}
}