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

@Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String>
{
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception
	{
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
	public void channelRead0(ChannelHandlerContext ctx, String request) throws Exception
	{
		int i, j;
		String[] str1 = null;
		String[] str2 = null;
		if (TelnetServer.rq.isEmpty()) 
		{
			TelnetServer.rq.add(request);
		}
		else 
		{
			str1 = TelnetServer.rq.get(0).split(" ");
			i = Integer.parseInt(str1[2]);
			TelnetServer.rq.add(request);
			str2 = TelnetServer.rq.get(1).split(" ");
			j = Integer.parseInt(str2[2]);
			System.out.println("valeur de i : " + i + "valeur de j : " + j);
			TelnetServer.ctxs.get(i - 1).writeAndFlush("crd " + str2[1] + "\r\n");
			TelnetServer.ctxs.get(j - 1).writeAndFlush("crd " + str1[1] + "\r\n");
			Arrays.fill(str1, null);
			Arrays.fill(str2, null);
			TelnetServer.rq.clear();
		}
	}
}