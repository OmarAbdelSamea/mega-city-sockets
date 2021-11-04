package ds.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ComputerHandler implements Runnable{

    Socket serverSocket;

    public ComputerHandler(Socket computerSocket)
    {
        this.serverSocket = computerSocket;
    }

    @Override
    public void run()
    {
        try
        {

            while (true)
            {

                //3.create I/O streams
                DataInputStream dis = new DataInputStream(serverSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(serverSocket.getOutputStream());

                //4.perform IO with client
                dos.writeUTF("Route 4 \n");
                dos.flush();

                //5.close connection
                dis.close();
                dos.close();

            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
