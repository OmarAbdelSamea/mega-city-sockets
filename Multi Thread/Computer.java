package ds.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Computer
{
    public static void main(String[] args)
    {
        try
        {
            /* Initialize computer socket */
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Computer is now initialized");
            while (true)
            {
                /* connect with the client */
                Socket computerSocket = serverSocket.accept();
                System.out.println("Client is now connected \n");

                /* create a socket for the handler */
                ClientHandler clientHandlerInstance = new ClientHandler(computerSocket);
                Thread computerThread = new Thread(clientHandlerInstance);
                computerThread.start();
            }
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
