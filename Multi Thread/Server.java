package ds.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            /* Initialize computer socket */
            ServerSocket sv = new ServerSocket(5002);
            System.out.println("Server is now initialized");
            while (true)
            {
                /* connect with the client */
                Socket serverSocket = sv.accept();
                System.out.println("Computer is now connected \n");

                /* create a socket for the handler */
                ComputerHandler computerHandlerInstance = new ComputerHandler(serverSocket);
                Thread serverThread = new Thread(computerHandlerInstance);
                serverThread.start();
            }
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
