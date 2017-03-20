package handler.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

public class Client {

	public static void main(String[] args) {
		QName qName = new QName("http://server.handler/", "HandlerService");
		try {
			HandlerService handlerService = new HandlerService(new URL("http://localhost:9527/handlerServer?wsdl"), qName);
			BusinessService businessService = (BusinessService) handlerService.getPort(BusinessService.class);
			People p = (People) businessService.echo("nimeide");
			System.out.print(p.getLocation());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
