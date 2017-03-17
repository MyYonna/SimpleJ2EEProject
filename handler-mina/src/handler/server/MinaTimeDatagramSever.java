package handler.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class MinaTimeDatagramSever {

	public static void main(String[] args) {
		IoAcceptor ioAcceptor = new NioDatagramAcceptor(Executors.newCachedThreadPool());
		ioAcceptor.getSessionConfig().setMaxReadBufferSize(2048);
		ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 1000);
		ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
		ioAcceptor.getFilterChain().addLast("stringserialize", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		ioAcceptor.setHandler(new TimeServerHandler());
		try {
			ioAcceptor.bind(new InetSocketAddress("127.0.0.1", 8888));
			ioAcceptor.bind();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("UDP服务启动");
	}

}
