package com.example.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *  本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 *  异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 *  写出你的方法，越多越好，提交到github。
 */
public class Homework03 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //创建线程池
        //异步执行下面方法
        int result = semaphoreTest(); //这是得到的返回值
        // 拿到result 并输出
        System.out.println ("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - startTime) + " ms");
        // 然后退出main线程
    }

    // 通过Main线程Sleep,让出CPU资源,睡眠时间内不参与竞争
    public static int sleepTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        new Thread(() -> {
            atomicInteger.set(sum());
        }).start();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return atomicInteger.get();
    }

    // 通过CountDownLatch,使线程等待CountLatch为0
    public static int countDownLatchTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            atomicInteger.set(sum());
            //每次调用CountDown()，计数减1
            countDownLatch.countDown();
        }).start();
        try {
            //主程序执行到await()函数会阻塞等待线程的执行，直到计数为0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return atomicInteger.get();
    }

    // CyclicBarrier
    public static int cyclicBarrierTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> new Thread(() -> {
            atomicInteger.set(sum());
        }).start());
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        return atomicInteger.get();
    }

    // Semaphore  通过Semaphore, 当semaphore为1的时候，Main线程才能acquire
    public static int semaphoreTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
 //尝试获取一个可用的许可证 , 如果无可用许可证 ,
//当前线程会阻塞 , 如果线程被中断则抛出InterruptedException , 并停止阻塞继续执行
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.set(sum());
            semaphore.release();
        }).start();

        return atomicInteger.get();
    }

    // Lock -> condition
    public static int lockConditionTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        try {
            lock.lock();
            new Thread(() -> {
                lock.lock();
                atomicInteger.set(sum());
                condition.signalAll();
                lock.unlock();
            }).start();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return atomicInteger.get();
    }

    // LockSupport
    public static int lockSupportTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            atomicInteger.set(sum());
            LockSupport.unpark(mainThread);//解除阻塞
        });
        thread.start();
        LockSupport.park();
        return atomicInteger.get();
    }

    // 线程join使main线程等待
    public static int joinTest() {
        AtomicInteger atomicInteger = new AtomicInteger();
        final Thread thread = new Thread(() -> {
            atomicInteger.set(sum());
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return atomicInteger.get();
    }

    // 使用 FutureTask 异步执行任务
    public static int futureTaskTest() {
        FutureTask futureTask = new FutureTask(() -> sum());
        try {
            Thread thread = new Thread(futureTask);
            thread.start();
            Integer integer = (Integer) futureTask.get();
            return integer;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int sum() {
        System.out.println("执行任务的线程：" + Thread.currentThread().getName());
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }


}
