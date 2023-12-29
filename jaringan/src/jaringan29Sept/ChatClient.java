/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jaringan29Sept;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Rosa
 */
public class ChatClient {
    public static void main(String[] args) throws Exception{
        MulticastSocket chat = new MulticastSocket(1234);
        InetAddress group = InetAddress.getByName("234.5.6.7");
        chat.joinGroup(group);
        String msg = "";
        System.out.println("Type a messsage for the server:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, 1234);
        chat.send(data);
        chat.close();
    }
    
}
