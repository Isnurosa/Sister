/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liveChat;
import java.io.*;
import java.net.*;

/**
 *
 * @author Rosa
 */
public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Port
            System.out.println("Server started. Waiting for clients...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String clientMessage = in.readLine();
                System.out.println("Client: " + clientMessage);

                System.out.print("You: ");
                String message = consoleInput.readLine();
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


