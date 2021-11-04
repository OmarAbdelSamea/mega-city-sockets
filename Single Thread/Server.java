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
            //1.open server socket
            ServerSocket sv = new ServerSocket(5002);
            System.out.println("Server is now initialized");
            
            //2.accept connection
            Socket s = sv.accept();
            System.out.println("Computer is now connected");
            //3.create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            //4.perform IO with client

            dos.writeUTF("Route 4 \n");
            dos.flush();


            //5.close connection
            dis.close();
            dos.close();
            s.close();

            
            //6.close server
            //sv.close();
            
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
