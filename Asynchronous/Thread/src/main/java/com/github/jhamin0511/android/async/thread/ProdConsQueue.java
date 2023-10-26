package com.github.jhamin0511.android.async.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdConsQueue {
    // 모든 생상 소비 동기화 작업은 Queue 내부에서 동작
    private final BlockingQueue<Integer> list = new LinkedBlockingQueue<>(10);

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            Thread.sleep(200);
            System.out.println("[" + Thread.currentThread().getName() + "] " + "Produce : " + value + " / size : " + list.size());
            list.put(value++);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            int value = list.take();
            System.out.println("[" + Thread.currentThread().getName() + "] " + "Consume : " + value);
        }
    }
}
