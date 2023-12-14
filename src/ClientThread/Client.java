package ClientThread;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client
{
	public static void main(String[] args) 
	{
		try 
		{
			//Connexion
			System.out.println("Je suis un client pas encore connecté...");
			InetAddress ia = InetAddress.getByName("192.168.1.136");
			InetSocketAddress isa = new InetSocketAddress(ia,1234);
			Socket socket = new Socket();
			socket.connect(isa);
			System.out.println("Je suis un client connecté");

			//Fermeture du Connexion
			socket.close();
		} 
		catch (Exception e) 
		{
		}
	}
}
