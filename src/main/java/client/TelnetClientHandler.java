package client;

import cards.*;
//import java.util.Vector;
import io.netty.channel.ChannelHandler.Sharable;
//import server.TelnetServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
  
  /**
   * Handles a client-side channel.
   */
  @Sharable
  public class TelnetClientHandler extends SimpleChannelInboundHandler<String>
  {
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
    				System.out.println("unlucky");
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
    			 System.out.println("unlucky");
    			 System.exit(1);
    		 }
    		 System.out.println(msg);
    	 }
      }
      
      @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
          cause.printStackTrace();
          ctx.close();
      }
  }