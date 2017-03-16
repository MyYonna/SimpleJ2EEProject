package handler.server;

import java.rmi.RemoteException;

public class BusinessImpl implements Business {

	@Override
	public Message echo(String message) throws RemoteException {
		// TODO Auto-generated method stub
		if("quit".equalsIgnoreCase(message)){
			System.out.println("Server will be shutdown");
			System.exit(0);
		}
		System.out.println("Message from client:"+message);
		//应该支持多线程的并发操作，不会阻塞
		Message msg = new Message();
		if(message.equals("1")){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.setMessage("I have reviced your message");
		}else{
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.setMessage("your message is not current");
		}
		return msg;
	}

}
