package server;

import java.util.ArrayList;
import java.util.Vector;

import io.netty.bootstrap.ServerBootstrap;
  import io.netty.channel.EventLoopGroup;
  import io.netty.channel.nio.NioEventLoopGroup;
  import io.netty.channel.socket.nio.NioServerSocketChannel;
  import io.netty.handler.logging.LogLevel;
  import io.netty.handler.logging.LoggingHandler;
  import io.netty.handler.ssl.SslContext;
  import io.netty.handler.ssl.SslContextBuilder;
  import io.netty.handler.ssl.util.SelfSignedCertificate;
  import io.netty.channel.ChannelHandlerContext;
  
  /**
   * Simplistic telnet server.
   */
  public final class TelnetServer {
  
	  public static final ArrayList<ChannelHandlerContext> ctxs = new ArrayList<ChannelHandlerContext>(2);
	  public static final Vector<String> rq = new Vector<String>(2); 
	  static final boolean SSL = System.getProperty("ssl") != null;
      static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "7575" : "6565"));
  
     public static void main(String[] args) throws Exception {
          // Configure SSL.
          final SslContext sslCtx;
          if (SSL) {
              SelfSignedCertificate ssc = new SelfSignedCertificate();
              sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
          } else {
              sslCtx = null;
          }
  
          EventLoopGroup bossGroup = new NioEventLoopGroup(1);
          EventLoopGroup workerGroup = new NioEventLoopGroup();
          try {
              ServerBootstrap b = new ServerBootstrap();
              b.group(bossGroup, workerGroup)
               .channel(NioServerSocketChannel.class)
               .handler(new LoggingHandler(LogLevel.INFO))
               .childHandler(new TelnetServerInitializer(sslCtx));
              
              b.bind(PORT).sync().channel().closeFuture().sync();
          } finally {
              bossGroup.shutdownGracefully();
              workerGroup.shutdownGracefully();
          }
      }
  }