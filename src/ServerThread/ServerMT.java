package ServerThread;

import java.net.*;
import java.io.*;
import OperationPAK.Operation;

public class ServerMT extends Thread
{
	int ord ;
	public static void main(String[] args) 
	{
		// lancement du serveur
		new ServerMT().start(); 
	} 
	public void run() 
	{
		try 
		{
			ServerSocket ss = new ServerSocket(1234);
			System.out.println("je suis un serveur,j'attends une connexion");
			while (true) 
			{
				// serveur bloquant en attente de communication 
				Socket s=ss.accept();
				new ClientProcess(s,ord++).start();
				System.out.println("vous etes connecté_bienvenue");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public class ClientProcess extends Thread 
	{
		Socket s;
		int num_client;
		public ClientProcess(Socket S, int num_client) 
		{
			super();
			this.s=S;
			this.num_client=num_client;
		}
		public void run()
		{
			try 
			{
				// Flux de comminication et de traitement :
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				// Opération:
				Operation op=(Operation)ois.readObject();
				switch(op.getOperation()) 
				{
				case'+': 
					op.setResult(op.getOp1()+op.getOp2());
					break;
				case '-':
					op.setResult(op.getOp1()-op.getOp2());
					break;
				case '*':
					op.setResult(op.getOp1()*op.getOp2());
					break;
				case '/':
					op.setResult(op.getOp1()/op.getOp2());
					break;
				default:
					break;
				}
				// Envoie d'objet
				oos.writeObject(op);
				//Fermeture de la connexion :
				s.close();
			} 
			catch (IOException | ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
      
	
	


	