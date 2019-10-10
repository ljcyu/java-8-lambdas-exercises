package self;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class Ex8 {
  /**计算素数*/
  public static boolean isPrime(int num){
    for(int i=2;i<num;i++){
      if(num%i==0){
        return false;
      }
    }
    return true;
  }
  public static boolean isPrimeByStream(int num){
    return IntStream.range(2,num).allMatch(x->num%x!=0);
  }
  /**有几个素数*/
  public long count(int upto){
    return IntStream.range(2,upto).filter(Ex8::isPrime).count();
  }
  @Test
  public void testIsPrime(){
    assertTrue(isPrimeByStream(5));
    assertTrue(isPrimeByStream(7));
    System.out.println(count(100));

  }
}
