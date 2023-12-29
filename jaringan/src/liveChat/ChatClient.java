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
public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Ganti dengan alamat server jika berbeda

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("You: ");
                String message = consoleInput.readLine();
                out.println(message);

                String serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

