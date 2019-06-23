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
        
        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i< 5; i++) {
            philosophers.add(new Philosopher("#"+(i+1)));
        }

        for (int i = 0; i< 5; i++) {
            int left = (i+5-1)%5;
            int right = (i+1)%5;
            philosophers.get(i).setLeft(philosophers.get(left));
            philosophers.get(i).setLeft(philosophers.get(right));
        }

        for (Philosopher p:philosophers) {
            p.start();
        }
    }
}
