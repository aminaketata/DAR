package ClientPackage;

//import java.io.BufferedReader;
//import java.io.PrintWriter;
//import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Operation.Operation;

public class Client 
{
	public static void main(String[] args) 
	{
		try 
		{
			//connection au serveur
			System.out.println("Je suis un client pas encore connecté...");

	        Socket socket = new Socket("localhost", 1234);
	        System.out.println("Je suis un client connecté");
	        
	        //flux de communication
	        InputStream is = socket.getInputStream();
	        OutputStream os = socket.getOutputStream(); 
	        
	        //Flux de traitement
			/*InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			PrintWriter pw = new PrintWriter(os,true);*/
	        
	        // Flux de traitement
	        ObjectOutputStream oos = new ObjectOutputStream(os);
	     	ObjectInputStream ois = new ObjectInputStream(is);
			
			//Lecture d'un entier
	        Scanner scanner = new Scanner(System.in);
	        
	        //Envoie du premier entier  
	        /*System.out.println("donner un nombre1:");
	        int nb1 = scanner.nextInt();
	        pw.println(nb1);
	        System.out.println("J'ai envoié le nombre: " +nb1+" au serveur");*/
	        
	        //Envoie du deuxieme entier
	        /* System.out.println("donner un nombre2:");
	        int nb2 = scanner.nextInt();
	        pw.println(nb2);
	        System.out.println("J'ai envoié le nombre: " +nb2+" au serveur");*/
	        
	        //Envoie du type d'operation
	    	/*System.out.print("\nDonner l'operation : ");
	        String op;
	        
	        do
	        {
	        	System.out.println("Choix d'operation:");
	        	op = scanner.nextLine();
	        }
	        while(!(op.equals("*")) && !(op.equals("+")) && !(op.equals("-")) && !(op.equals("/")));
	      
	        pw.println(op);
			//Reçeption du résultat
			String s = br.readLine();
			int result = Integer.parseInt(s);
			//Afficahge du résultat
			System.out.println(nb1+" "+op+" "+nb2+"="+result);*/
	        
	        //Envoie de objet
			Operation O1= new Operation(100,2,'*');
			oos.writeObject(O1);
			 //Reçeption de l'objet
			O1 = (Operation)ois.readObject();
			System.out.println("Résultat = "+O1.getResult());
	     } 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		catch (IOException |ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}