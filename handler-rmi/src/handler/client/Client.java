package handler.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import handler.data.Message;
import handler.server.Business;

public class Client {

	public static void main(String[] args) throws RemoteException{
		System.out.println("远程调用请求发出.....");
		Registry registry = LocateRegistry.getRegistry("localhost", 1099);
		String name = "businessService";
		try {
			//生成的类必须和服务端的类完全一样，包括类全名
			Business business = (Business)registry.lookup(name);
			System.out.println(System.currentTimeMillis());
			for(int i=0;i<100;i++){
				final int i1 = i;
				Thread thread = new Thread(){
					@Override
					public void run(){
						Message response;
						try {
							response = business.echo(String.valueOf(i1%2));
							System.out.println(response.getMessage());
							System.out.println(System.currentTimeMillis());
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				thread.start();
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
