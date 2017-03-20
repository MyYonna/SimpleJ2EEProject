package handler.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Business {

	public String echo(@WebParam(name = "message") String message);
}
