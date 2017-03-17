package handler.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import handler.data.Message;

public class TimeClientHandler extends IoHandlerAdapter {

    public void messageReceived(IoSession iosession, Object obj)
            throws Exception
        {
    	if (obj instanceof Message) {
			Message msg = (Message) obj;
			System.out.println("客户端收到的消息>>>>>"+msg);
		}
        }

        public void messageSent(IoSession iosession, Object obj)
            throws Exception
        {
        	if (obj instanceof Message) {
    			Message msg = (Message) obj;
    			System.out.println("客户端发出的消息>>>>>"+msg);
    		}
        	System.out.println("还有数据未发送>>>>"+iosession.getScheduledWriteBytes());
        }
        
}
