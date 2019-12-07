package self;

import org.junit.Test;

import java.util.stream.IntStream;

public class Ex8_2 {
    public void dispPrime(int upto){
        long num=IntStream.range(2,upto).parallel().filter(this::isPrime).count();
        System.out.println(num);
    }
    public boolean isPrime(int num){
        return IntStream.range(2,num).allMatch(i->num%i!=0);
    }
    @Test
    public void showPrimes(){
        dispPrime(1000000);
    }
}
