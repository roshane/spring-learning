package com.rx;

import com.rx.model.Customer;
import com.rx.service.CustomerService;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.List;
import java.util.Random;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by roshane on 4/17/2017.
 */
public class Application {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final Object lock = new Object();

    public static void main(String[] args) {

        logItem("main");

        List<Customer> customer = CustomerService.getCustomer(10);
        customer.forEach(System.out::println);
        System.out.println("\n\n");
        Observable.fromIterable(customer)
                .takeLast(3)
                .subscribe(System.out::println,
                        System.err::println,
                        ()->System.out.println("end of stream"));

        System.exit(0);
    }

    private static void futureTask() {
        FutureTask<Integer> runnableFuture = new FutureTask<>(() -> {
            logItem("runnable");
            synchronized (lock) {
                lock.notifyAll();
            }
            logItem("notified");
        }, 0);
        Scheduler computation = Schedulers.computation();
        computation.scheduleDirect(runnableFuture);
        computation.start();
    }

    private static void observableFuture() {
        final FutureTask<Integer> futureTask = new FutureTask(Application::randomInt);
        Observable<Integer> futureObservable = Observable.fromFuture(futureTask);
        Schedulers.newThread().scheduleDirect(futureTask::run);
        futureObservable.subscribe(Application::logItem);
    }

    private static Integer randomInt() {
        System.out.println("generator thread " + Thread.currentThread());
        return random.nextInt(1000);
    }

    private static <T> void logItem(T item) {
        System.out.println(Thread.currentThread() + " - " + System.currentTimeMillis() + " : " + item.toString());
    }

    private static void observablePlain() {
        Observable
                .fromIterable(intList(5))
                .subscribe(i -> {
                            Thread.sleep(1000L);
                            logItem(i);
                            if (i == 4) {
                                throw new RuntimeException("system crashed");
                            }
                        }, (error) -> logItem(error.getMessage()),
                        () -> logItem("completed"));
    }

    private static void observablePlainScheduler() {
        synchronized (lock) {
            Observable
                    .fromIterable(intList(5))
//                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(i -> {
                                Thread.sleep(1000L);
                                logItem(i);
                                if (i == 4) {
                                    throw new RuntimeException("system crashed");
                                }
                            }, (error) -> {
                                logItem(error.getMessage());
                                synchronized (lock) {
                                    lock.notifyAll();
                                }
                            },
                            () -> {
                                logItem("completed");
                                synchronized (lock) {
                                    lock.notifyAll();
                                }
                            });
        }
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static Iterable<Integer> intList(int limit) {
        return IntStream.iterate(1, i -> i + 1)
                .limit(limit)
                .boxed()
                .collect(Collectors.toList());
    }
}
