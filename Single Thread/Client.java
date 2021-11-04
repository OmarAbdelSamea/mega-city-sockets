package ds.multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            //1.create socket and connect to the server
            //with IP:127.0.0.1(localhost)
            //and with port number: 5000
            Socket s = new Socket("127.0.0.1", 5000);
            //2. Create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            //3.perform IO with server 
            System.out.print("Enter the required destination: ");
            String usr_msg = sc.next();
            dos.writeUTF(usr_msg);
                /*flush the content of the buffer to the output stream.
                A buffer is a portion in
                memory that is used to store a stream of data(characters)*/
            dos.flush();
            String bestRoute = dis.readUTF();
            System.out.println(bestRoute);

            //4.close connections
            dis.close();
            dos.close();
            s.close();
            
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }   
}
