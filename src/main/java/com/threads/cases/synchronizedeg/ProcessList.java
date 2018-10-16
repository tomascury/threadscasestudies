package com.threads.cases.synchronizedeg;

public class ProcessList {

    static int counter = 100;

    public static void main(String[] args){

        processList();
        processListSynchronized();
    }


    private static void processList() {

        for (int i = 0; i < counter; i++) {
           new AddNumberThread(i);
        }

        System.out.println("Threads[activeCount]: " + Thread.activeCount());

        StaticList.printLn();
    }

    private static void processListSynchronized() {

        for (int i = 0; i < counter; i++) {
            new AddNumberThreadSynchronized(i);
        }

        System.out.println("Threads[activeCount]: " + Thread.activeCount());

        StaticListSynchonized.println();
    }
}
