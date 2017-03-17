package handler.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext  acc = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*Business business = (Business)acc.getBean("business");
		business.echo("这是什么意思");*/
	}

}
