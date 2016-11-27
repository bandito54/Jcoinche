package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline; 
import java.util.ArrayList;
import java.util.List;
import io.netty.*;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * Handler implementation for the object echo client. It initiates the ping-pong
 * traffic between the object echo client and server by sending the first
 * message to the server.
 */
public class ObjectEchoClientHandler extends ChannelInboundHandlerAdapter {

private final List<Integer> firstMessage; 
	/**
	 * Creates a client-side handler.
	 */
	public ObjectEchoClientHandler()
	{
		firstMessage = new ArrayList<Integer>(ObjectEchoClient.SIZE);
		for (int i = 0; i < ObjectEchoClient.SIZE; i++)
		{
			firstMessage.add(Integer.valueOf(i));
		}
	}

	
	@Override
	public void channelActive(ChannelHandlerContext ctx)
	{
		// Send the first message if this handler is a client-side handler.
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// Echo back the received object to the server.
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
