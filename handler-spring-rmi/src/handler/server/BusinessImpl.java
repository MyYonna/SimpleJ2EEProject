package handler.server;

import handler.service.Business;

public class BusinessImpl implements Business {

	@Override
	public String echo(String message) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if("quit".equalsIgnoreCase(message)){
			System.out.println("Server will be shutdown");
			System.exit(0);
		}
		System.out.println("Message from client>>>>>>>>>>:"+message);
	
		return "server has recived client message:"+message+"<<<<<<<<<";
	}

}
