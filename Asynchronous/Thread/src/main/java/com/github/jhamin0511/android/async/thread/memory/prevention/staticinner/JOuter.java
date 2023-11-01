package com.github.jhamin0511.android.async.thread.memory.prevention.staticinner;

import android.os.SystemClock;
import com.github.jhamin0511.android.async.thread.memory.Counter;

// 외부 객체는 일단 자신을 참조하는 다른 객체가 사라지면 가비지 컬렉션될 수 있다.
public class JOuter {
    public void sampleMethod() {
        // 외부 클래스에 대한 참조를 유지 하지 않는다.
        // StaticInner thread = new JOuter.StaticInner();
        StaticInner thread = new StaticInner();
        thread.start();
    }

    // 정적 내부 클래스 - 외부 클래스에 대한 참조를 저장하지 않음
    static class StaticInner extends Thread {
        @Override
        public void run() {
            Object sampleObject = new Object();
            SystemClock.sleep(Counter.INSTANCE.getTime());
        }
    }
}
