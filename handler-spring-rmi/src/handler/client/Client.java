package handler.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import handler.service.Business;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext  acc = new ClassPathXmlApplicationContext("applicationContext_client.xml");
		Business business = (Business)acc.getBean("business");
		String response = business.echo("这是什么意思");
		System.out.println(response);
	}

}
