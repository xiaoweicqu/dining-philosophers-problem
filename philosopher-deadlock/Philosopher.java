package com.example.philosopher;

import android.util.Log;

import java.util.Random;

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
                synchronized (left) {
                    Log.i(TAG, name + " grabbed chopstick " + left.id);
                    Log.i(TAG, name + " tried to grab chopstick " + left.id);
                    // Grab the right chopstick.
                    synchronized (right) {
                        Log.i(TAG, name + " grabbed chopstick " + right.id);
                        // Eat for while.
                        Thread.sleep(random.nextInt(1000));
                        Log.i(TAG, name + " finished eating.");
                    }
                }
            }
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    static final class Chopstick {
        int id;
        public Chopstick(int id) {
            this.id = id;
        }
    }
}
