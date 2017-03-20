package handler.server;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import handler.service.BusinessService;
/**
 * name默认为此类的类名，serviceName为类名加上Service,targetNameSpace为倒序的包名
 * @author zhangpeng
 *
 */
@WebService
@SOAPBinding(style=Style.RPC)
public class BusinessServiceImpl implements BusinessService {

	@Override
	public String echo(String message) {
		// TODO Auto-generated method stub
		if("quit".equalsIgnoreCase(message)){
			System.out.println("Server will be shutdown");
			System.exit(0);
		}
		System.out.println("Message from client>>>>>>>>>>:"+message);
	
		return "i have receviced you message:"+message+"<<<<<<<<<";
	}

}
