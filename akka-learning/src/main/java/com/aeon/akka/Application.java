package com.aeon.akka;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by roshane on 3/30/2017.
 */
public class Application {

    private static final ScheduledExecutorService exec= Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        Props props = Props.create(Counter.class);
        ActorSystem actorSystem = ActorSystem.create("counter-actors");
        final ActorRef actorRef = actorSystem.actorOf(props);

        exec.scheduleAtFixedRate(()->{
            actorRef.tell(new Counter.Message(), ActorRef.noSender());
        },1L,1L, TimeUnit.SECONDS);

        System.out.println("Exit");
//        System.exit(0);
    }


    static class Counter extends AbstractLoggingActor {

        private static int count = 0;

        {
            System.out.println("configuring counter actor");
            receive(ReceiveBuilder.match(Message.class, this::onMessage).build());
        }

        private void onMessage(Message message) {
            log().info("message received {}",message);
//            System.out.println("message received " + message);
            count += 1;
//            System.out.printf("counter increased [%d]\n", count);
            log().info("counter increased {}",count);
        }


        static class Message {
            @Override
            public String toString() {
                return "Message{}";
            }
        }
    }
}
