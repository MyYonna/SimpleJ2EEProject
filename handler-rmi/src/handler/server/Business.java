package handler.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import handler.data.Message;

public interface Business extends Remote {

	public Message echo(String message) throws RemoteException;
}
