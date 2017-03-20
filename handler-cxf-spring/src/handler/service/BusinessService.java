package handler.service;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**
 * 必须加上此注解
 * @author zhangpeng
 *
 */
@WebService
@SOAPBinding(style=Style.RPC)
public interface BusinessService {

	public String echo(String message);
}
