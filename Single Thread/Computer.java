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
            //1.open server socket
            ServerSocket sv = new ServerSocket(5000);
            System.out.println("Computer is now initialized");


            //2.accept connection
            Socket computerSocket = sv.accept();
            System.out.println("Client is now connected \n");
            //3.create I/O streams
            DataInputStream dIComputer = new DataInputStream(computerSocket.getInputStream());
            DataOutputStream dOComputer = new DataOutputStream(computerSocket.getOutputStream());

            //4.perform IO with client
            String clientMessage = dIComputer.readUTF();
            System.out.println("Client destination: " + clientMessage);

            /******************** Sensors ********************/
            Socket sensorsSocket = new Socket("127.0.0.1", 5001);

            DataInputStream dISensor = new DataInputStream(sensorsSocket.getInputStream());
            DataOutputStream dOSensor = new DataOutputStream(sensorsSocket.getOutputStream());

            System.out.print("Getting sensor readings .... \n");
            dOSensor.writeUTF("Get Reading \n");
                /*flush the content of the buffer to the output stream.
                A buffer is a portion in
                memory that is used to store a stream of data(characters)*/
            dOSensor.flush();
            String sensorResponse = dISensor.readUTF();
            System.out.println(sensorResponse);


            //5.close connection
            dISensor.close();
            dOSensor.close();
            sensorsSocket.close();
            /*************************************************/


            /******************** Server ********************/
            Socket serverSocket = new Socket("127.0.0.1", 5002);

            DataInputStream dIServer = new DataInputStream(serverSocket.getInputStream());
            DataOutputStream dOServer = new DataOutputStream(serverSocket.getOutputStream());

            System.out.print("Getting server recommendation .... \n");
            dOServer.writeUTF("Take sensor readings: " + sensorResponse + "\n");
                /*flush the content of the buffer to the output stream.
                A buffer is a portion in
                memory that is used to store a stream of data(characters)*/
            dOServer.flush();
            String serverResponse = dIServer.readUTF();
            System.out.println(serverResponse);

            //5.close connection
            dIServer.close();
            dOServer.close();
            serverSocket.close();
            /*************************************************/

            dOComputer.writeUTF("The Recommended Route is : " + serverResponse + "\n");
            dOComputer.flush();

            //5.close connection
            dISensor.close();
            dISensor.close();
            computerSocket.close();
            
            //6.close server
            //sv.close();
            
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
