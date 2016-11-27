package cards;

import java.util.Vector;
import java.util.Iterator;


public class Cards 
{
	private static Vector<Integer> victor = new Vector<Integer>(32);

	public static void main(String[] args)
	{
		fill_deck();
		check_value();
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
	
	public static void check_value()
	{
		int res = victor.get(1) / 10;
		int res2 = victor.get(31) / 10;
		if (res < res2)
			System.out.println(res + " < " + res2);
	}
}