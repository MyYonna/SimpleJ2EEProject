package handler.server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import handler.service.Business;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Business service = new BusinessImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(Business.class);
		svrFactory.setAddress("http://localhost:8888/webservice");
		svrFactory.setServiceBean(service);
		svrFactory.create();
		System.out.println("webservice启动成功。。。。");
	}

}
