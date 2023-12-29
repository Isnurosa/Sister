/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jaringan06Okt;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Rosa
 */
public class MulticastChatClien {

    private static final String MULTICAST_ADDRESS = "230.0.0.1";
    private static final int MULTICAST_PORT = 12345;

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
            multicastSocket.joinGroup(group);

            System.out.print("Enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine().trim();
            String joinMessage = username + " has joined the chat.";
            byte[] joinData = joinMessage.getBytes();
            DatagramPacket joinPacket = new DatagramPacket(
                    joinData,
                    joinData.length,
                    group,
                    MULTICAST_PORT
            );
            multicastSocket.send(joinPacket);

            System.out.println("Multicast Chat Client is running...");

            // Thread untuk membaca pesan dari server dan menampilkannya di layar
            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        multicastSocket.receive(packet);
                        
                        String message = new String(packet.getData(), 0, packet.getLength());
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readThread.start();
            
            // Mengirim pesan ke server
            while (true) {
                String message = scanner.nextLine();
                String fullMessage = username + ": " + message;
                byte[] sendData = fullMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData,
                        sendData.length,
                        group,
                        MULTICAST_PORT
                );
                multicastSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
