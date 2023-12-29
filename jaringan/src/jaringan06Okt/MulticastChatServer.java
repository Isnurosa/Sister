/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jaringan06Okt;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.*;
import java.io.*;

/**
 *
 * @author Rosa
 */
public class MulticastChatServer {
    private static final String MULTICASTA_ADDRESS = "230.0.0.1";
    private static final int MULTICAST_PORT = 12345;
    private static final int MAX_USERS = 10;
    
    private static Map<String, InetAddress> users = new HashMap<>();
    
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(MULTICASTA_ADDRESS);
            MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT); 
            multicastSocket.joinGroup(group);
            
            System.out.println("Multicat Chat Server is running...");
            
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);
                
                String message = new String(packet.getData(), 0, packet.getLength());
                String[] parts = message.split(":", 2);
                
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String content = parts[1].trim();
                    
                    if (!users.containsKey(username)) {
                        if (users.size() >= MAX_USERS) {
                            System.out.println("User limit reached. " + username + " cannot join.");
                            continue;
                        }
                        users.put(username, packet.getAddress());
                        System.out.println(username + " joined the chat.");
                    }
                    // Forward the received message to all connected clients
                    
                    for (Map.Entry<String, InetAddress> entry : users.entrySet()) {
                        if (!entry.getKey().equals(username)) {
                            InetAddress userAddress = entry.getValue();
                            DatagramPacket sendPacket = new DatagramPacket(
                            message.getBytes(),
                            message.length(),
                            userAddress,
                            MULTICAST_PORT
                            );
                            multicastSocket.send(sendPacket);
                        }
                    }
                    
                }   
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
}
