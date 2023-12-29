/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread08Sept;

/**
 *
 * @author Rosa
 */
public class PrintNameThread extends Thread {

    PrintNameThread(String name) {
        super(name);
        // menjalankan thread dengan satu kali instantiate
        start();
    }

    public void run() {
        String name = getName();
        for (int i = 0; i < 100; i++) {
            System.out.print(name);
        }
    }
}

class TestThread {

    public static void main(String args[]) {
        PrintNameThread pnt1 = new PrintNameThread("A");
        PrintNameThread pnt2 = new PrintNameThread("B");
        PrintNameThread pnt3 = new PrintNameThread("C");
        PrintNameThread pnt4 = new PrintNameThread("D");
    }
}
