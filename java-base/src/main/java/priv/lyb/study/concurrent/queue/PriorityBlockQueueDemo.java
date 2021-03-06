package priv.lyb.study.concurrent.queue;

import priv.lyb.study.concurrent.threadpool.Nap;

import java.util.List;
import java.util.Queue;
import java.util.SplittableRandom;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LiuYingBo 2021/02/01 06:51
 */
class Prioritized implements Comparable<Prioritized> {
    private static AtomicInteger counter = new AtomicInteger();
    private static List<Prioritized> sequence = new CopyOnWriteArrayList<>();
    private final int id = counter.getAndIncrement();
    private final int priority;

    Prioritized(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @SuppressWarnings("uncheck")
    @Override
    public int compareTo(Prioritized arg) {
        return priority < arg.priority ? 1 : (priority > arg.priority ? -1 : 0);
    }

    @Override
    public String toString() {
        return String.format("[%d] Prioritized %d ", priority, id);
    }

    public void displaySequence() {
        int count = 0;
        for (Prioritized pt : sequence) {
            System.out.printf("(%d:%d)", pt.id, pt.priority);
        }
    }

    public static class EndSentinel extends Prioritized {
        EndSentinel() {
            super(-1);
        }
    }
}

class Producer implements Runnable{
    private static AtomicInteger seed = new AtomicInteger(47);
    private SplittableRandom rand = new SplittableRandom(seed.getAndAdd(10));
    private Queue<Prioritized> queue;
    Producer(Queue<Prioritized> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        rand.ints(0,100)
                .mapToObj(Prioritized::new)
                .peek(p->new Nap(rand.nextDouble()/10))
                .forEach(p->queue.add(p));
        queue.add(new Prioritized.EndSentinel());
    }
}

class Consumer implements Runnable{
    private PriorityBlockingQueue<Prioritized> q;
    private SplittableRandom rand = new SplittableRandom(47);
    Consumer(PriorityBlockingQueue<Prioritized> q){
        this.q = q;
    }
    @Override
    public void run() {
        while (true){
            try {
                Prioritized pt = q.take();
                System.out.println(pt);
                if(pt instanceof Prioritized.EndSentinel){
                    pt.displaySequence();
                    break;
                }
                new Nap(rand.nextDouble()/10);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }
}

public class PriorityBlockQueueDemo {
    public static void main(String[] args) {
        PriorityBlockingQueue<Prioritized> queue =
                new PriorityBlockingQueue<>();
        CompletableFuture.runAsync(new Producer(queue));
        CompletableFuture.runAsync(new Producer(queue));
        CompletableFuture.runAsync(new Producer(queue));
        CompletableFuture.runAsync(new Consumer(queue)).join();
    }
}
