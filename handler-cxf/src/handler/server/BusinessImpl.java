package handler.server;

import javax.jws.WebParam;
import javax.jws.WebService;

import handler.service.Business;

public class BusinessImpl implements Business {

	@Override
	public String echo(@WebParam(name = "message")String message) {
		// TODO Auto-generated method stub
		if("quit".equalsIgnoreCase(message)){
			System.out.println("Server will be shutdown");
			System.exit(0);
		}
		System.out.println("Message from client>>>>>>>>>>:"+message);
	
		return "i have receviced you message:"+message+"<<<<<<<<<";
	}

}
