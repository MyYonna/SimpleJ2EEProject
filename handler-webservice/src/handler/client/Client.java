package handler.client;

public class Client {

	public static void main(String[] args){
		//生成服务类
		HandlerService handlerService = new HandlerService();
		//生成远程调用类
		Handler business = handlerService.getHandlerPort();
		//发起远程调用
		People response = business.echo("");
		System.out.println(response.getLocation() +"  "+response.getName()+"  "+response.getAge());
	}
}
