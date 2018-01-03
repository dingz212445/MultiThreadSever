import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable{
	Socket csocket;
	Server(Socket sock){
		this.csocket = sock;
	}
	static Integer count = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(8080);
		System.out.println("listening");
		
		while(true) {
			Socket client = server.accept();
			System.out.println("connected");
			new Thread(new Server(client)).start();

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		OutputStream outStream;
		try {
			outStream = csocket.getOutputStream();
			PrintWriter output = new PrintWriter(outStream, true);
			synchronized(count) {
				count++;
			}
			output.println("Hello" + count);
			output.close();
			csocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("disconnected");
	}

}
