package Thread08Sept;

class PrintNameThread1 extends Thread {
   PrintNameThread1(String name) {
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