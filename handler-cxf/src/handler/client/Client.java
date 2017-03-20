package handler.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import handler.service.Business;
import handler.service.BusinessService;

public class Client {

	public static void main(String[] args){
		//静态调用
		JaxWsProxyFactoryBean soapFactoryBean = new JaxWsProxyFactoryBean();

		 soapFactoryBean.setWsdlURL("http://localhost:8888/webservice?wsdl");

		 soapFactoryBean.setServiceClass(Business.class);

		 Object o = soapFactoryBean.create();

		 Business business = (Business)o; 
		 
		 String res = business.echo("i have send a static  message");
		 
		 System.out.println(res);
		
		 /////////////////////////////////////////////////动态调用客户端////////////////////////////////////
		 JaxWsDynamicClientFactory dcf =JaxWsDynamicClientFactory.newInstance();

		 org.apache.cxf.endpoint.Client client = dcf.createClient("http://localhost:8888/webservice?wsdl");

		 //sayHello 为接口中定义的方法名称  张三为传递的参数   返回一个Object数组
		try {
			Object[] objects = client.invoke("echo","i have send a dynamic message");
			System.out.println(objects[0].toString()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 //输出调用结果
		//spring集成的webservice
		//静态调用
		 JaxWsProxyFactoryBean soapFactoryBean1 = new JaxWsProxyFactoryBean();

		 soapFactoryBean1.setWsdlURL("http://localhost:8081/handler-cxf-spring/ws/businessService?wsdl");

		 soapFactoryBean1.setServiceClass(BusinessService.class);

		 Object o1 = soapFactoryBean1.create();

		 BusinessService business1 = (BusinessService)o1; 
		 
		 String res1 = business1.echo("i have send a static  message");
		 
		 System.out.println(res1);
		
		 /////////////////////////////////////////////////动态调用客户端////////////////////////////////////
		 JaxWsDynamicClientFactory dcf1 =JaxWsDynamicClientFactory.newInstance();

		 org.apache.cxf.endpoint.Client client1 = dcf1.createClient("http://localhost:8081/handler-cxf-spring/ws/businessService?wsdl");

		 //sayHello 为接口中定义的方法名称  张三为传递的参数   返回一个Object数组
		try {
			Object[] objects1 = client1.invoke("echo","i have send a dynamic message");
			System.out.println(objects1[0].toString()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
