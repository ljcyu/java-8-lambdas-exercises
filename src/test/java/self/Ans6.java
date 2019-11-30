package self;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Ans6 {
    @Test
    public void sumOfSquares(){
        long start=System.currentTimeMillis();
        long res=IntStream.range(1,1_000_000).mapToLong(x->x*x).sum();
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(res);
    }
    @Test
    public void sumOfSquaresByParallel(){
        long start=System.currentTimeMillis();
        long res=IntStream.range(1,1_000_000).parallel().mapToLong(x->x*x).sum();

        System.out.println(System.currentTimeMillis()-start);
        System.out.println(res);
    }
}
