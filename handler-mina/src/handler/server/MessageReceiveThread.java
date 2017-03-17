package handler.server;

import handler.data.Message;

public class MessageReceiveThread implements Runnable{

	private Object message;
	
	MessageReceiveThread(Object message){
	this.message = message;	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(message instanceof Message){
			Message msg = (Message)message;
			System.out.println("服务器收到的消息++++++"+msg.getContent());
		}else{
			String msg = message.toString();
			//会占用IO处理县城，为了避免业务处理接收的消息的速度影响IO处理性能，需要另外开启一个县城处理业务
			System.out.println("服务器收到的消息>>>>>"+msg);
		}
	}

}
