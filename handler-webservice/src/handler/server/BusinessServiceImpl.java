package handler.server;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import handler.service.BusinessService;
import handler.service.People;
/**
 * name 为客户端生成的远程调用类名，serviceName为客户端生成的远程服务类
 * @author zhangpeng
 *
 */
@WebService(name="BusinessService",serviceName="HandlerService")
@SOAPBinding(style=Style.RPC)
public class BusinessServiceImpl implements BusinessService {

	@Override
	public People echo(String message) {
		// TODO Auto-generated method stub
		System.out.println("Message from client>>>>>>>>>>:"+message);
		People p = new People();
		if("quit".equalsIgnoreCase(message)){
			System.out.println("Server will be shutdown");
			System.exit(0);
		}
		p.setLocation("湖南");
		p.setAge(25);
		p.setName("张鹏");
	
		return p;
	}

}
