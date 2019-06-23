package com.example.philosopher;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    private static final String TAG = "Philosopher";
    private final String name;
    private final Condition condition;
    private final Random random;
    private boolean eatting;

    private Philosopher left;
    private Philosopher right;
    private ReentrantLock table;

    public Philosopher3(String name) {
        this.name = name;
        table = new ReentrantLock();
        condition = table.newCondition();
        random = new Random();
    }

    public void setLeft(Philosopher3 philosopher) {
        left = philosopher;
    }

    public void setRight(Philosopher3 philosopher) {
        right = philosopher;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    private void think() throws InterruptedException {
        table.lock();
        try {
            eatting = false;
            left.condition.signal();
            right.condition.signal();
        } finally {
            table.unlock();
        }
        Thread.sleep(random.nextInt(1000));
        Log.i(TAG, name + " finished thinking.");
    }

    private void eat() throws InterruptedException {
        table.lock();
        try {
            Log.i(TAG, name + " tried to grab chopstick from " + left.name + " and " + right.name);
            while (left.eatting || right.eatting) {
                condition.wait();
            }
            Log.i(TAG, name + " grabbed chopsticks.");
            eatting = true;
        } finally {
            table.unlock();
        }
        Thread.sleep(random.nextInt(1000));
        Log.i(TAG, name + " finished eating.");
    }
}
