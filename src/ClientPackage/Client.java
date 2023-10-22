package ClientPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
        	System.out.println("Je suis un client");
            try 
            {
                InetAddress serverAddress = InetAddress.getByName("192.168.1.14");

                InetSocketAddress serverSocketAddress = new InetSocketAddress(serverAddress, 1234);

                Socket s = new Socket();
                s.connect(serverSocketAddress);

                System.out.println("Je suis un client connecté");

                Scanner scanner = new Scanner(System.in);
                System.out.println("Donnez un nombre : ");
                int nb = scanner.nextInt();

                System.out.println("Choisissez l'opération :");
                System.out.println("1. Addition");
                System.out.println("2. Soustraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                int operation = scanner.nextInt();

                OutputStream os = s.getOutputStream();
                os.write(nb);
                os.write(operation);

                InputStream is = s.getInputStream();
                int rep = is.read();

                System.out.println("Le résultat est : " + rep);

                s.close();
            }
            catch (IOException e)
			{
				e.printStackTrace();
			}
	}
}
