package ClientPackage;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Client {

	public static void main(String[] args)  
	{
		// TODO Auto-generated method stub
		try 
		{
			Socket socket=new Socket("localhost",1234);
			System.out.println("un client connecté");
			InputStream is=socket.getInputStream();
			OutputStream os= socket.getOutputStream();
			Scanner scanner=new Scanner(System.in);
			System.out.println("donner un nombre");
			int x=scanner.nextInt();
			os.write(x);
			int rep=is.read();
			System.out.println("Réponse="+rep);
			socket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
