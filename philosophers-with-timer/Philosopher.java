package com.example.philosopher;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    private static final String TAG = "Philosopher";
    private String name;
    private Chopstick left;
    private Chopstick right;
    private Random random;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        this.name = name;
        this.left = left;
        this.right = right;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Think for a while.
                Thread.sleep(random.nextInt(1000));
                Log.i(TAG, name + " finished thinking.");
                // Grab the left chopstick.
                Log.i(TAG, name + " tried to grab chopstick " + left.id);
                try {
                    left.grab();
                    Log.i(TAG, name + " grabbed chopstick " + left.id);
                    Log.i(TAG, name + " tried to grab chopstick " + left.id);
                    if (right.tryGrab()) {
                        // Grab the right chopstick.
                        Log.i(TAG, name + " grabbed chopstick " + right.id);
                        try {
                            // Eat for while.
                            Thread.sleep(random.nextInt(1000));
                            Log.i(TAG, name + " finished eating.");
                        } finally {
                            right.putDown();
                        }
                    }
                } finally {
                    left.putDown();
                }
            }
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    static final class Chopstick {
        int id;
        ReentrantLock lock;
        public Chopstick(int id) {
            this.id = id;
            lock = new ReentrantLock();
        }

        public void grab() {
            lock.lock();
        }

        public boolean tryGrab() throws InterruptedException {
           return lock.tryLock(1000, TimeUnit.MILLISECONDS);
        }

        public void putDown() {
            lock.unlock();
        }
    }
}
