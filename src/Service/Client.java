package Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client 
{
	final static int PORT = 1234;
	private static byte[] buffer = new byte[1024];
	public static void main(String[] args)
	{
		System.out.println("Donner votre nom");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		try 
		{
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket packet = new DatagramPacket(username.getBytes(), username.length(), InetAddress.getByName("localhost"), PORT);
			socket.send(packet);
				
			DatagramPacket receiveData = new DatagramPacket(buffer, buffer.length);
			socket.receive(receiveData);
				
			System.out.println(new String(receiveData.getData(),0,receiveData.getLength()));
			System.out.println("Adresse du serveur: " + receiveData.getAddress() + ", Port du serveur: " + receiveData.getPort());
			socket.close();
				
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

public class Client 
{
    public static void main(String[] args)
    {
        try 
        {
        	System.out.println("Je suis un client non conncté");
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
        	System.out.println("Je suis un client conncté");

            String request = "Demande de date ";
            byte[] sendData = request.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Date et heure fournies par le serveur: " + response);
            clientSocket.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
*/

