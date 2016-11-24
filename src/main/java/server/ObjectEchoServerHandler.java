package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ObjectEchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		//Echo back the received object to the client.
		ctx.write(msg);
		//Permet de lire le contenu d'un objet
		System.out.println("ctx ! " + ctx + "msg : " + msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		//ecrit tous les messages contenu dans les buffers
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		//Ecrit toutes les erreurs et ferme le programme
		cause.printStackTrace();
		ctx.close();
	}
}