package handler.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleClientSocket {
	private PrintWriter pw;
	private BufferedReader br;
	private String message ;
	private static ExecutorService exec = Executors.newCachedThreadPool();  
	
	public void createClient(){
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket("localhost",8888);
			System.out.println("启动客户端。。。。。服务器连接成功");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			pw= new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			exec.execute(new ClientHandlerThread()); 
			while((message = br.readLine())!=null){
				System.out.println(message);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public class ClientHandlerThread implements  Runnable {
		public void run() {
			try {
			//初始化输入输出流
				String msg = "";
                BufferedReader sysbr = new BufferedReader(new InputStreamReader(  
                        System.in));
                while((msg = sysbr.readLine())!=null){
                	pw.println(msg);
					pw.flush();
                }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
