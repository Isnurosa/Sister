/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jaringan29Sept;
import java.io.*;
import java.net.*;
/**
 *
 * @author Rosa
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            //Socket client = new Socket("133.0.0.1", 1234);
            Socket client = new Socket(InetAddress.getLocalHost(), 
            1234);
            InputStream clientIn = client.getInputStream();
            OutputStream clientOut = client.getOutputStream();
            PrintWriter pw = new PrintWriter(clientOut, true);
            BufferedReader br = new BufferedReader(new 
            InputStreamReader(clientIn));
            BufferedReader stdIn = new BufferedReader(new 
            InputStreamReader(System.in));
            System.out.println("Type a message for the server: ");
            pw.println(stdIn.readLine());
            System.out.println("Server message: ");
            System.out.println(br.readLine());
            pw.close();
            br.close();
            client.close();
        } catch (ConnectException ce) {
            System.out.println("Cannot connect to the server.");
        } catch (IOException ie) {
            System.out.println("I/O Error.");
        }

    }
    
}
