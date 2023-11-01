package com.github.jhamin0511.android.async.thread.queue;

import java.util.LinkedList;

public class ProdConsSync {
    private final LinkedList<Integer> list = new LinkedList<>();
    private static final int LIMIT = 10;
    private final Object lock = new Object();

    // Synchronized로 작업 생산 동기화
    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                Thread.sleep(500);
                System.out.println("[" + Thread.currentThread().getName() + "] " + "Produce : " + value);
                list.add(value++);
                lock.notify();
            }
        }
    }

    // Synchronized로 작업 소비 동기화
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                Thread.sleep(1000);
                int value = list.removeFirst();
                System.out.println("[" + Thread.currentThread().getName() + "] " + "Consume : " + value);
                lock.notify();
            }
        }
    }
}
