package ClientThread;

import java.net.*;
import java.util.*;
import OperationPAK.Operation;
import java.io.*;

public class ClientMT extends Thread 
{
	public static void main(String[] args) 
	{
		try 
		{
			// premiére partie:
			System.out.println("Je suis un client pas encore connecté...");
			Socket socket = new Socket("localhost", 1234);
			// Etablir connexion 
			System.out.println("Je suis un client connecté");
		
			InputStream is = socket.getInputStream();
			// deusiéme partie: 
			// flux de communication et traitement 
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectInputStream ois = new ObjectInputStream(is);
			//Envoie de objet
			System.out.println("Donnez le premier entier");
			Scanner scanner = new Scanner(System.in);
			int nb1= scanner.nextInt();		
			System.out.println("Donnez le deuxieme entier");
			int nb2= scanner.nextInt();
			char op;
			do 
			{
				System.out.println("choisissez votre opération:");
				op = scanner.next().charAt(0);
			} 
			while (!(op =='+') && !(op == '-') && !(op == '*')&& !(op == '/'));		
			Operation O1= new Operation(nb1,nb2,op);
			oos.writeObject(O1);
			// Réception de l'objet:
			O1 = (Operation)ois.readObject();
			System.out.println("Résultat = "+O1.getResult());
			//Fermeture du Connexion
			socket.close();
		} 
		catch (IOException  |ClassNotFoundException e)
		{
			e.printStackTrace();
	    }
	}
}
	