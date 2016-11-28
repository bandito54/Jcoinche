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
    	 String[] cutt = null;
    	 cutt = msg.split(" ");
    	 if (cutt[0].equals("Welcome"))
    		 TelnetClient.player = Integer.parseInt(cutt[2]);
    	 if (cutt[0].equals("gv"))
    	 {
    		 Cards.D1.add(Integer.parseInt(cutt[1]));
    		 System.out.println(msg);
    	 }
    	 else if (cutt[0].equals("crd"))
    	 {
    		 if (Integer.parseInt(cutt[1]) > Cards.D1.get(0))
    		 {
    			 ctx.write("gv " + Cards.D1.get(0));
    			 Cards.D1.remove(0);
    		 }
    		 else if (Integer.parseInt(cutt[1]) < Cards.D1.get(0))
    		 {
    			 Cards.D2.add(Cards.D1.get(0));
    			 Cards.D1.remove(0);
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