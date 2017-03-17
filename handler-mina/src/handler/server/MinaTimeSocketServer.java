package handler.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeSocketServer {

	
	private static final int port = 8888;
	/**
	 * 注意的点
	 * 1、使用自定义的ThreadModel(2.0之前)
	 * 2、合理配置IO处理线程池
	 * 3、监听写超时
	 * 4、借助Mina IoSession上未发送的byte信息实现流控  ioSession上的getScheduledWriteBytes来获取
	 * 5、messageReceived方法占用IO处理线程
	 * @param args
	 */
	public static void main(String[] args){
		//创建服务端监控线程
		IoAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		//设置读取的数组大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		//设置闲置时间
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
		//加日志记录器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//对象序列化过滤器
		acceptor.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		//加编码过滤器,有对象序列化器的话，编码过滤需要屏蔽，且客户端和服务端的过滤要是一样
		//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//设置业务逻辑处理器
		acceptor.setHandler(new TimeServerHandler());
		try {
			//设置端口号
			acceptor.bind(new InetSocketAddress(port));
			//启动监听线程
			acceptor.bind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
