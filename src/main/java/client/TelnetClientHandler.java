package client;

//import cards.*;
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
    	 if (cutt[0].equals("gv"))
    	 {
    		 TelnetClient.CARD.D1.add(Integer.parseInt(cutt[1]));
    	 }
    	 else if (cutt[0].equals("crd"))
    	 {
    		 System.out.println(msg);
    	 }
      }
      
      @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
          cause.printStackTrace();
          ctx.close();
      }
  }