/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enkrip13Okt;

/**
 *
 * @author Rosa
 */
public class Enkrip1 {
    public static void main(String[] args) {
        String text = "Selamat Datang";
        String temp = "";
        int tambahan = 2;
        for (int i=0; i<text.length(); i++){
            int h = (int) (text.charAt(i));
            char t = (char) (h+tambahan);
            temp += t;
        }
        System.out.println(temp);
    }
    
}
