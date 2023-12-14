package Serveur;

import java.io.IOException;
import java.net.*;

public class Serveur 
{
	final static int PORT=1234;
	private static byte[] buffer = new byte[1024];
	public static void main(String[] args) 
	{
		try 
		{
			DatagramSocket socket = new DatagramSocket(PORT);
			System.out.println("Démarrage du Server");
					
			while(true)
			 {
			  	DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			 	socket.receive(packet);
						 
			 	String username = new String(packet.getData(), 0, packet.getLength());
			 	String message = "Bienvenue " + username;
						 
			 	DatagramPacket messageTOSend = new DatagramPacket(message.getBytes(), message.length(), packet.getAddress(), packet.getPort());
				socket.send(messageTOSend);
			}
					
		}
	 	catch (IOException e)
	 	{
	 		e.printStackTrace();
	 	}			
	}
}
/*

import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Serveur extends Thread{

	public static void main(String[] args)
	{
		new Serveur().start();
	}
	public void run()
	{
		try 
		{
			System.out.println("lancement du Server");
			DatagramSocket serverSocket = new DatagramSocket(1234);
			while(true)
			{
				new ClientProcess(serverSocket).start();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public class ClientProcess extends Thread 
	{
		DatagramSocket socket;
		public ClientProcess(DatagramSocket serverSocket)
		{
			super();
			this.socket = serverSocket;
		}
		public void run()
		{
			try 
			{
				byte[] receiveData = new byte[1024];        
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePacket);
				System.out.println("Client d'adresse :"+receivePacket.getAddress()+" demande la date");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = dateFormat.format(new Date());
				byte[] sendData = currentTime.getBytes();

				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
				System.out.println("Envoie terminé pour "+receivePacket.getAddress());
			}
			catch (IOException  e) 
			{
				e.printStackTrace();
			}
		}
	}
}
*/