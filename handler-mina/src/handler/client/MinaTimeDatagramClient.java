package handler.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import handler.data.Message;

public class MinaTimeDatagramClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IoConnector ioConnector = new NioDatagramConnector(Runtime.getRuntime().availableProcessors() + 1);
		ioConnector.getFilterChain().addLast("logger", new LoggingFilter());
		ioConnector.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		ioConnector.setHandler(new TimeClientHandler());
		
		ioConnector.setConnectTimeoutMillis(10000);
		//连接远程主机，设置iP和端口
		ConnectFuture connectFuture = ioConnector.connect(new InetSocketAddress("127.0.0.1", 8888));
		//等待连接建立
		connectFuture.awaitUninterruptibly();
		//连接建立后返回session
		final IoSession ioSession = connectFuture.getSession(); 
		connectFuture.addListener(new IoFutureListener<ConnectFuture>() {
			
			@Override
			public void operationComplete(ConnectFuture ioFuture) {
				// TODO Auto-generated method stub
				if(ioFuture.isConnected()){
					int i = 0 ;
					Message msg = new Message();
					msg.setContent("数据报协议");
					msg.setReceiver("服务器");
					msg.setSender("客户端");
					while(i<1000){
						WriteFuture wf = ioSession.write(msg);
						wf.addListener(new IoFutureListener<IoFuture>() {
							@Override
							public void operationComplete(IoFuture ioFuture) {
								// TODO Auto-generated method stub
								if(ioFuture.isDone()){
									return ;
								}else{
									System.out.println("消息写入未完成>>>>>>>>>>>>>>>>>>>>>>>>");
								}
							}
							
						});
						i++;
					}
				}
			}
		});
		
		ioSession.getCloseFuture().awaitUninterruptibly();
		
		ioConnector.dispose();
	}

}
