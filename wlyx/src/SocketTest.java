import java.io.InputStream;
import java.net.Socket;


public class SocketTest {
	public static void main(String[] args)throws Exception {
		String url = "";
		try{
			Socket s = new Socket(url,1080);
			System.out.println(url+" success");
		}catch(Exception e){
			System.out.println("time out");
		}
	}
	
}
