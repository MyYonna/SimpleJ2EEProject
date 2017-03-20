package handler.service;

import javax.jws.WebService;

@WebService
public interface BusinessService {

	public People echo(String message);
}
