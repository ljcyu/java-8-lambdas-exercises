package self;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalId {
    private final static AtomicInteger atomicInteger=new AtomicInteger(0);
    public final static ThreadLocal<Integer> threadLocalId=ThreadLocal.withInitial(()->
            atomicInteger.incrementAndGet());
    public static int get(){
        return threadLocalId.get();
    }
}
