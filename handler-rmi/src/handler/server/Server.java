package handler.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub

		//其实现实例是以字符串的方式绑定到RMI注册对象上，
		//应该支持多线程的并发操作
		int port  = 9527;
		String name = "businessService";
		Business business = new BusinessImpl();
		UnicastRemoteObject.exportObject(business,port);
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.rebind(name, business);
		System.out.println("远程服务已经启动。。。。。");
	}

}
