package com.example.philosopher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Philosopher.Chopstick c1 = new Philosopher.Chopstick(1);
        Philosopher.Chopstick c2 = new Philosopher.Chopstick(2);
        Philosopher.Chopstick c3 = new Philosopher.Chopstick(3);
        Philosopher.Chopstick c4 = new Philosopher.Chopstick(4);
        Philosopher.Chopstick c5 = new Philosopher.Chopstick(5);

        Philosopher p1 = new Philosopher("#1", c1, c2);
        Philosopher p2 = new Philosopher("#2", c2, c3);
        Philosopher p3 = new Philosopher("#3", c3, c4);
        Philosopher p4 = new Philosopher("#4", c4, c5);
        Philosopher p5 = new Philosopher("#5", c5, c1);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
