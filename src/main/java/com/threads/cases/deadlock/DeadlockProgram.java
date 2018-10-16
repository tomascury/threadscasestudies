package com.threads.cases.deadlock;

/**
 * @see <a href="https://dzone.com/articles/how-to-read-a-thread-dump">How to Read a Thread Dump</a>
 *
 * thread dump
 *
 * "Thread-1" #12 prio=5 os_prio=31 tid=0x00007f847380b000 nid=0xa703 waiting for monitor entry [0x0000700003ef6000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.threads.cases.deadlock.DeadlockProgram$DeadlockRunnable.run(DeadlockProgram.java:34)
 *         - waiting to lock <0x000000076ada3820> (a java.lang.Object)
 *         - locked <0x000000076ada3830> (a java.lang.Object)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 *    Locked ownable synchronizers:
 *         - None
 *
 * "Thread-0" #11 prio=5 os_prio=31 tid=0x00007f847600e800 nid=0xa803 waiting for monitor entry [0x0000700003df3000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *         at com.threads.cases.deadlock.DeadlockProgram$DeadlockRunnable.run(DeadlockProgram.java:34)
 *         - waiting to lock <0x000000076ada3830> (a java.lang.Object)
 *         - locked <0x000000076ada3820> (a java.lang.Object)
 *         at java.lang.Thread.run(Thread.java:748)
 *
 */
public class DeadlockProgram {


    public static void main(String[] args) throws Exception {

        Object resourceA = new Object();
        Object resourceB = new Object();
        Thread threadLockingResourceAFirst = new Thread(new DeadlockRunnable(resourceA, resourceB));
        Thread threadLockingResourceBFirst = new Thread(new DeadlockRunnable(resourceB, resourceA));
        threadLockingResourceAFirst.start();
        Thread.sleep(500);
        threadLockingResourceBFirst.start();
    }

    private static class DeadlockRunnable implements Runnable {

        private final Object firstResource;
        private final Object secondResource;

        public DeadlockRunnable(Object firstResource, Object secondResource) {
            this.firstResource = firstResource;
            this.secondResource = secondResource;
        }

        public void run() {
            try {
                synchronized(firstResource) {
                    printLockedResource(firstResource);
                    Thread.sleep(1000);
                    synchronized(secondResource) {
                        printLockedResource(secondResource);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e);
            }
        }
        private static void printLockedResource(Object resource) {
            System.out.println(Thread.currentThread().getName() + ": locked resource -> " + resource);
        }
    }
}
