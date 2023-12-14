package ServerThread;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
	int ord;
	public static void main(String[] args) 
	{
		new Server().start();
	}
	public void run()
	{
		try 
		{
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("Démarrage du serveur");
			while(true)
			{
				Socket s = ss.accept();
				new ClientProcess(s,++ord).start();
				System.out.println("Client connecté :\nIP Adresse"+s.getRemoteSocketAddress()+"\nNuméro:"+ord);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public class ClientProcess extends Thread
	{
		Socket socket;
		private int numClient;
		public ClientProcess(Socket socket,int numClient) 
		{
			super();
			this.numClient=numClient;
			this.socket=socket;
		}
		public void run()
		{
			try 
			{
				PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
				pw.print("Client numéro "+numClient+"Votre address IP:"+socket.getRemoteSocketAddress());
				socket.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}
