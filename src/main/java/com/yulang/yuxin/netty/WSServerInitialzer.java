package com.yulang.yuxin.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipe = ch.pipeline();
		pipe.addLast(new HttpServerCodec());
		pipe.addLast(new ChunkedWriteHandler());

		pipe.addLast(new HttpObjectAggregator(1024*64));

		pipe.addLast(new WebSocketServerProtocolHandler("/ws"));

		pipe.addLast(new ChatHandler());
	}

}
