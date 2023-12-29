/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihan;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Rosa
 */    

public class LatBannerApp {    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Banner App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null); // Center the frame
    
        JPanel panel = new JPanel() {
            private String text = "Selamat Datang di Banner App Isnurosa Pertiwi";
            private int xPos = 0;
    
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawString(text, xPos, 50);
    
                // Pindahkan teks ke kanan
                xPos++;
                if (xPos > getWidth()) {
                    xPos = -g.getFontMetrics().stringWidth(text);
                }
            }
        };
    
        frame.add(panel);
        frame.setVisible(true);
    
        // Menggerakkan teks dari kiri ke kanan
        while (true) {
            panel.repaint();
            try {
                TimeUnit.MILLISECONDS.sleep(10); // Jeda 10 milidetik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}


