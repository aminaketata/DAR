package ServerPackage;

//import java.io.BufferedReader;
//import java.io.PrintWriter;
//import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Operation.Operation;

public class Server 
{
	public static void main(String[] args) 
	{
		try 
		{
			// Lancement du server
			ServerSocket serverSocket = new ServerSocket(1234);
			System.out.println("Je suis un serveur en attente la connexion d'un client");
			
			//Acceptation du connexion
			Socket socket = serverSocket.accept();
			System.out.println("un client est connecté");
			
			// Flux de comunication
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// Flux de traitement
			/* Utlisation du InputStreamReader et BufferedReader pour 
			   pouvoir lire tous les caractères reçus du client*/
			ObjectOutputStream oos = new ObjectOutputStream(os);//sérialiser
			ObjectInputStream ois = new ObjectInputStream(is);//déserialiser
			
			/*InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(os,true);*/
			
			//Reçeption du premier entier
			/*System.out.println("Waiting for a number1 ... ");
			String s = br.readLine();
			int nb1 = Integer.parseInt(s);
			
			// Reçeption du deuxieme entier
			System.out.println("Waiting for a number2 ... ");
			s = br.readLine();
			int nb2 = Integer.parseInt(s);
			
			//Reception du type d'operation 
			String s1 = br.readLine();*/
			
			// Reçeption de l'objet
			Operation o = (Operation)ois.readObject();
						
			// Récupération des parameters
			int nb1 = o.getOp1();
			int nb2 = o.getOp2();
			char op = o.getOperation();
			
			//int rep=0;
			
			// Calculatrice
			switch (op) 
			{
			case '+':
				o.setResult(nb1+nb2);
				break;
			case '-':
				o.setResult(nb1-nb2);
				break;
			case '*':
				o.setResult(nb1*nb2);
				break;
			case '/':
				o.setResult(nb1/nb2);
				break;
			default:
				break;
			}
			
			/*//Envoie du résultat
			pw.println(rep);*/
			
			// Envoie de l'objet au client 
			oos.writeObject(o);
			
			// Fermeture du  socket
			System.out.println("Closing the socket...");
			socket.close();
			serverSocket.close();
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}

