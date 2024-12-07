// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.concurrency.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public final class Threads {

  @FunctionalInterface
  public interface ThrowingRunnable {

    void run() throws InterruptedException;

  }

  public static void exec(ThrowingRunnable body) {
    try {
      body.run();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  public static void execAcquiring(Semaphore s, Runnable body) {
    var isAcquired = false;
    try {
      s.acquire();
      isAcquired = true;
      body.run();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      if (isAcquired) {
        s.release();
      }
    }
  }

  public static void execLocking(Lock lock, Runnable body) {
    var isLocked = false;
    try {
      lock.lock();
      isLocked = true;
      body.run();
    } finally {
      if (isLocked) {
        lock.unlock();
      }
    }

  }

  public static Thread startNew(Runnable body) {
    final var thread = new Thread(body);
    thread.start();
    return thread;
  }

  public static void sleep(long millis) {
    exec(() -> Thread.sleep(millis));
  }

  private Threads() {}

}
