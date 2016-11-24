package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public final class ObjectEchoServer {

	static final boolean SSL = System.getProperty("ssl") != null;
	//getProperty(string, type)
	static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

	public static void main(String[] args) throws Exception {
		// Configure SSL.
		//Création d'une socket sécurisée via la factory ssl
		final SslContext sslCtx;
		if (SSL) {
			//Genere un certificat & clé temporaire pour faire des tests | supprimé à l'extinction de la JVM
			//NOTE: Ne pas utiliser le certificat et la clé générée par cette classe en PROD
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslCtx = null;
		}

		//EventLoopGroup retourne les EventLoop (handle all the I/O operations for a Channel once registered)
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			//Set the EventLoopGroup for the parent (acceptor) and the child (client). These EventLoopGroup's are used to handle all the events and IO for ServerChannel and Channel's.
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							//Création du flux I/O
							ChannelPipeline p = ch.pipeline();
							if (sslCtx != null) {
								p.addLast(sslCtx.newHandler(ch.alloc()));
							}
							p.addLast(new ObjectEncoder(), new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
									new ObjectEchoServerHandler());
						}
					});

			// Bind and start to accept incoming connections.
			b.bind(PORT).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}