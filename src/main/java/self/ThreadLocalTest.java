package self;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {
    public final static ThreadLocal<DateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat());

    public void testDateFormat(){
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            service.submit(() -> {
                System.out.printf("%s %s%n",dateFormatThreadLocal.get().format(new Date()),ThreadLocalId.get());
            });
        service.shutdown();
    }

    public static void main(String[] args){
        ThreadLocalTest test = new ThreadLocalTest();
        test.testDateFormat();
    }
}
