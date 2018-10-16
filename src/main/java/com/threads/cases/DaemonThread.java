package com.threads.cases;

/**
 * A daemon thread is a thread that does not prevent the JVM from exiting when the program finishes but the thread is
 * still running. An example for a daemon thread is the garbage collection.
 *
 * You can use the setDaemon(boolean) method to change the Thread daemon properties before the thread starts.
 *
 *
 *
 * When a new thread is created it inherits the daemon status of its parent.
 * When all non-daemon threads finish, the JVM halts, and any remaining daemon threads are abandoned:
 *
 *      * finally blocks are not executed,
 *      * stacks are not unwound - the JVM just exits.
 *
 * Due to this reason daemon threads should be used sparingly, and it is dangerous to use them for tasks that might
 * perform any sort of I/O.
 *
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html">Class Thread</a>
 */
public class DaemonThread {

    public static void main(String[] args) {
        new WorkerThread().start();

        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("Main Thread ending");
    }
}

class WorkerThread extends Thread {

    public WorkerThread() {
        // When false, (i.e. when it's a user thread),
        // the Worker thread continues to run.
        // When true, (i.e. when it's a daemon thread),
        // the Worker thread terminates when the main
        // thread terminates.
        setDaemon(true);
    }

    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Hello from Worker " + count++);

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
