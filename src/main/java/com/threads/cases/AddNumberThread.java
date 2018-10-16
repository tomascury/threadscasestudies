package com.threads.cases;

import java.util.ArrayList;
import java.util.List;

public class AddNumberThread implements Runnable {

    private int number;

    public AddNumberThread(int number) {
        this.number = number;
        new Thread(this).start();
    }

    public void run() {
        StaticList.add(number);
    }
}

class StaticList {

    private static List<Integer> staticList = new ArrayList<Integer>();

    public static void add(Integer integer){
        staticList.add(integer);
    }

    public static void printLn(){
        System.out.println(staticList);
    }
}

