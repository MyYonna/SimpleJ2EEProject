package handler.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import handler.service.BusinessService;

public class Client {

	public static void main(String[] args){
        @SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-cxf-client.xml");
        BusinessService service = ctx.getBean("businessServiceClient", BusinessService.class);
        System.out.println(service.echo("i send a message"));
	}
}
