package handler.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import handler.service.Business;

public class Client {

	public static void main(String[] args){
		 JaxWsProxyFactoryBean soapFactoryBean = new JaxWsProxyFactoryBean();

		 soapFactoryBean.setWsdlURL("http://localhost:8888/webservice?wsdl");

		 soapFactoryBean.setServiceClass(Business.class);

		 Object o = soapFactoryBean.create();

		 Business business = (Business)o; 
		 
		 String res = business.echo("i have send a message");
		 
		 System.out.println(res);
	}
}
