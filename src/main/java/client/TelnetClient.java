package client;

import graphic.*;
import cards.*;
 import io.netty.bootstrap.Bootstrap;
  import io.netty.channel.Channel;
  import io.netty.channel.ChannelFuture;
  import io.netty.channel.EventLoopGroup;
  import io.netty.channel.nio.NioEventLoopGroup;
  import io.netty.channel.socket.nio.NioSocketChannel;
  import io.netty.handler.ssl.SslContext;
  import io.netty.handler.ssl.SslContextBuilder;
  import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
  
  import java.io.BufferedReader;
  import java.io.InputStreamReader;

  public final class TelnetClient
  {
	  static final Cards CARD = new Cards();
      static final boolean SSL = System.getProperty("ssl") != null;
      static final String HOST = System.getProperty("host", "10.18.207.82");
      static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "6060" : "2626"));
      public static int player = 0; 

	 static void	which_card(int x)
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
			  System.out.println("Carreau");
		  else if (x % 10 == 3)
			  System.out.println("Trèfle");
		  else if (x % 10 == 4)
			  System.out.println("Pique");
	  }
      
      public static void main(String[] args) throws Exception
      {
          final SslContext sslCtx;
          if (SSL) {
              sslCtx = SslContextBuilder.forClient()
                  .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
          } else {
              sslCtx = null;
          }
          EventLoopGroup group = new NioEventLoopGroup();
          try {
              Bootstrap b = new Bootstrap();
              b.group(group)
               .channel(NioSocketChannel.class)
               .handler(new TelnetClientInitializer(sslCtx));
              Channel ch = b.connect(HOST, PORT).sync().channel(); 
              ChannelFuture lastWriteFuture = null;
              BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
              for (;;)
              {
                  String line = in.readLine();
                  if (line == null)
                  {
                      break;
                  }
                	  lastWriteFuture = ch.writeAndFlush("crd " + Cards.D1.get(0) + " " + player + "\r\n");
                	  System.out.print("Je joue la carte :");
                	  which_card(Cards.D1.get(0));
                  if ("bye".equals(line.toLowerCase())) {
                      ch.closeFuture().sync();
                      break;
                  }
              }
              if (lastWriteFuture != null) {
                  lastWriteFuture.sync();
              }
          }
          finally
          {
              group.shutdownGracefully();
          }
      }
  }