package handler.server;

import javax.xml.ws.Endpoint;
/**
 * 客户端�?�过jdk bin目录下的wsimport命令生成辅助调用代码
 * wsimport -keep -p com.demo.client http://localhost:8080/Demo/services/MyService?wsdl
 * @author zhangpeng
 *
 */
public class Server {

	/**
	 * 使用SunJAX-WS 2中Endpoint.publish进行发布
	 * java中使用WebService须首先将服务端的服务根据描述生成相应的WSDL文件，并将应用及此WSDL文件放入HTTP服务�?
	 * 借助java服务工具根据WSDL文件生成客户端代码；
	 * 此代码的作用是将产生的对象请求信息分装成标准的SOAP格式数据，并发�?�请求服务端�?
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Endpoint.publish("http://localhost:9527/handlerServer", new BusinessServiceImpl());
		System.out.println("Server has been started...");
	}

}
