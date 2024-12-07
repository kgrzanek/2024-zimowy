// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency;

import edu.san.concurrency.utils.Threads;

public class Program10 {

  static void exec(Object monitor, Runnable body) {
    synchronized (monitor) {
      body.run();
    }
  }

  public static void main(String[] args) {
    final var monitor1 = new Object();
    final var monitor2 = new Object();

    final var t1 = Threads.startNew(() -> {
      exec(monitor1, () -> {
        Threads.sleep(100);
        exec(monitor2, () -> {
          System.out.println("t1 has monitor1 and monitor2");
        });
      });
    });

    final var t2 = Threads.startNew(() -> {
      synchronized (monitor2) {
        Threads.sleep(200);
        synchronized (monitor1) {
          System.out.println("t2 has monitor2 and monitor1");
        }
      }
    });

    Threads.exec(t1::join);
    Threads.exec(t2::join);

    System.out.println("Done");
  }

}
