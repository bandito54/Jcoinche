package server;

import cards.*;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;

/**
 * Handles a server-side channel.
 */

@Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send greeting for a new connection.
		System.out.println("wesh");
		TelnetServer.ctxs.add(ctx);
		ctx.write("Welcome player " + TelnetServer.ctxs.size() + " !\r\n");
		if (TelnetServer.ctxs.size() == 1)
			for (int i = 0; i < 16; i++)
				ctx.write("gv " + Cards.D1.get(i) + "\r\n");
		else
			for (int i = 0; i < 16; i++)
				ctx.write("gv " + Cards.D2.get(i) + "\r\n");
		ctx.flush();
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
		// Generate and write a response
		int i, j;
		String[] str1 = null;
		String[] str2 = null;
		if (TelnetServer.rq.isEmpty()) 
		{
			TelnetServer.rq.add(request);
			System.out.println("dans le if");
		}
		else 
		{
			System.out.println("dans le else");
			str1 = TelnetServer.rq.get(0).split(" ");
			System.out.println("Split");
			i = Integer.parseInt(str1[2]);
			System.out.println("Avant de rajouter");
			TelnetServer.rq.add(request);
			System.out.println("Après rajouter");
			str2 = TelnetServer.rq.get(1).split(" ");
			j = Integer.parseInt(str2[2]);
			System.out.println("valeur de i : " + i + "valeur de j : " + j);
			TelnetServer.ctxs.get(i - 1).writeAndFlush("crd " + str2[1] + "\r\n");
			TelnetServer.ctxs.get(j - 1).writeAndFlush("crd " + str1[1] + "\r\n");
			Arrays.fill(str1, null);
			Arrays.fill(str2, null);
			TelnetServer.rq.clear();
			//wesh
		}
	}
}
	// We do not need to write a ChannelBuffer here.
	// We know the encoder inserted at TelnetPipelineFactory will do the
	// conversion.
	//ChannelFuture future = ctx.write(response);

	// Close the connection after sending 'Have a good day!'
	// if the client has sent 'bye'.
//	if(close)
//	{
//		future.addListener(ChannelFutureListener.CLOSE);
//	}
//	}
//
//	@Override
//      public void channelReadComplete(ChannelHandlerContext ctx) {
//          ctx.flush();
//      }
//
//	@Override
//      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//          cause.printStackTrace();
//          ctx.close();
//      }
//}