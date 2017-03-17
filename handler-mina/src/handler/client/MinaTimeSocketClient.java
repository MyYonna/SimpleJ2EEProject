package handler.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import handler.data.Message;

public class MinaTimeSocketClient {

	public static void main(String[] args){
		//创建客户端连接器
		NioSocketConnector connector = new NioSocketConnector(Runtime.getRuntime().availableProcessors() + 1);
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		//connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//设置连接超时检查时间
		connector.setConnectTimeoutCheckInterval(30);
		connector.setHandler(new TimeClientHandler());
		
		//建立连接
		ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 8888));
	
		//等待连接创建完成
		cf.awaitUninterruptibly();
		int i=0;
		while(i<10){
			//WriteFuture writeResult = cf.getSession().write("HI SERVER");
			//cf.getSession().write("quit");
			Message msg = new Message();
			msg.setContent("TCP协议");
			msg.setReceiver("服务器");
			msg.setSender("客户端");
			WriteFuture writeResult = cf.getSession().write(msg);
			//监听是否成功写入操作系统的发送缓冲区
			writeResult.addListener(new IoFutureListener<IoFuture>() {
				
				@Override
				public void operationComplete(IoFuture ioFuture) {
					// TODO Auto-generated method stub
					if(ioFuture.isDone()){
						return ;
					}
					System.out.println("未能成功写入缓冲区>>>>");
				}
				
			});
			i++;
		}
		//等待连接断开
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		//释放连接
		connector.dispose();
	}
}
