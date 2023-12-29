/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jaringan29Sept;

import java.net.*;
import java.io.*;

/**
 *
 * @author Rosa
 */
public class EchoingServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket server = null;
        Socket client;
        try {
            server = new ServerSocket(1234);
        } catch (IOException ie) {
            System.out.println("Cannot open socket.");
            System.exit(1);
        }
        while(true) {
            try {
                client = server.accept(); 
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);
                InputStream clientIn = client.getInputStream(); 
                BufferedReader br = new BufferedReader(new 
                InputStreamReader(clientIn));
                pw.println(br.readLine());
            } catch (IOException ie) {
            }
        }

    }
    
}
