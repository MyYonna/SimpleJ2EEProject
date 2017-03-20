package handler.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import handler.service.Business;

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

	}
}
