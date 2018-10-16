package com.threads.cases.synchronizedeg;

import java.util.ArrayList;
import java.util.List;

public class AddNumberThreadSynchronized implements Runnable {

    private int number;

    public AddNumberThreadSynchronized(int number) {
        this.number = number;
        new Thread(this).start();
    }

    public void run() {
        StaticListSynchonized.add(number);
    }
}


class StaticListSynchonized {

    private static List<Integer> staticList = new ArrayList<Integer>();

    /**
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html">Synchronized Methods</a>
     * @param integer
     */
    public synchronized static void add(Integer integer){
        staticList.add(integer);
    }

    /**
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html">Synchronized Methods</a>
     */
    public synchronized static void println(){
        System.out.println(staticList);
    }
}
