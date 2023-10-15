package ServerPackage;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Server {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try
		{
			ServerSocket ss=new ServerSocket(1234);
			System.out.println("Je suis un serveur en attente la connexion d'un client");
			Socket s=ss.accept();
			InputStream is=s.getInputStream();
			OutputStream os= s.getOutputStream();
			System.out.println("j'attend un nombre");
			int x=is.read();
			int nb=x*5;
			os.write(nb);
			s.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
