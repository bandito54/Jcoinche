package client;

import cards.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

  @Sharable
  public class TelnetClientHandler extends SimpleChannelInboundHandler<String>
  {  
	  void	which_card(int x)
	  {
		  if (x / 10 == 1)
			  System.out.print(" 7 de ");
		  else if (x / 10 == 2)
			  System.out.print(" 8 de ");
		  else if (x / 10 == 3)
			  System.out.print(" 9 de ");
		  else if (x / 10 == 4)
			  System.out.print(" 10 de ");
		  else if (x / 10 == 5)
			  System.out.print(" Valet de ");
		  else if (x / 10 == 6)
			  System.out.print(" Dame de ");
		  else if (x / 10 == 7)
			  System.out.print(" Roi de ");
		  else if (x / 10 == 8)
			  System.out.print(" As de ");
		  if (x % 10 == 1)
			  System.out.println("Coeur");
		  else if (x % 10 == 2)
			  System.out.println("Pique");
		  else if (x % 10 == 3)
			  System.out.println("Carreau");
		  else if (x % 10 == 4)
			  System.out.println("Trèfle");
	  }
      @Override
      protected void channelRead0(ChannelHandlerContext ctx, String msg)
      {
    	  int k = 0;
    	 String[] cutt = null;
    	 cutt = msg.split(" ");
    	 if (cutt[0].equals("Welcome"))
    		 TelnetClient.player = Integer.parseInt(cutt[2]);
    	 if (cutt[0].equals("gv"))
    	 {
    		 Cards.D1.add(Integer.parseInt(cutt[1]));
    		 System.out.println(cutt[1]);
    	 }
    		 else if (cutt[0].equals("crd"))
    	 {
    			 System.out.print("Votre adversaire a tiré");
    			 which_card(Integer.parseInt(cutt[1]));
    		 if (Integer.parseInt(cutt[1]) / 10 > Cards.D1.get(0) / 10)
    			 Cards.D1.remove(0);
    		 else if (Integer.parseInt(cutt[1]) / 10 < Cards.D1.get(0) / 10)
    		 {
    			 Cards.D2.add(Cards.D1.get(0));
    			 Cards.D2.add(Integer.parseInt(cutt[1]));
    			 Cards.D1.remove(0);
    			 for (int j = 0; j < Cards.D2.size(); j++)
    				 System.out.println(Cards.D2.get(j));
    		 }
    		 else
    		 {
    			 Cards.D2.add(Cards.D1.get(0));
    			 Cards.D1.remove(0);
    		 }
    		 if (Cards.D1.size() == 0)
    		 {
    			if (Cards.D2.size() == 0)
    			{
    				System.out.println("Wasted");
    				System.exit(1);
    			}
    			else
    			{
    				k = Cards.D2.size();
    				for (int i = 0; i < k; i++)
    				{
    					Cards.D1.add(Cards.D2.get(0));
    					Cards.D2.remove(0);
    				}
    			}
    		 }
    		 else if (Cards.D1.size() == 32)
    		 {
    			 System.out.println("Victoire");
    			 System.exit(1);
    		 }
    		 System.out.println("je détiens Exodia et " + (Cards.D1.size() + Cards.D2.size()) + " autre(s) cartes en main");
    	 }
      }
      @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
      {
          cause.printStackTrace();
          ctx.close();
      }
  }