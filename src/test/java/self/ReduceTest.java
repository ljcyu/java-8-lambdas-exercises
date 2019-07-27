package self;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Use the reduce operation when you’ve got a collection of values and you want to generate
 * a single result. In earlier examples, we used the count, min, and max methods, which are
 * all in the standard library because they are common use cases. All of these are forms of
 * reduction.
 */
public class ReduceTest {
  @Test
  public void sumOp() {
    int res = Stream.of(1, 2, 3).reduce(0, (sum, x) -> sum + x);
    assertEquals(6, res);
  }

  @Test
  public void maxOp() {
    int res = Stream.of(2, 3, 24, 5).reduce(Integer.MIN_VALUE, (max, x) -> {
      return max > x ? max : x;
    });
    assertEquals(24, res);
  }
  @Test
  /**reduce一参数版本和两参数版本区别*/
  public void sumOp2(){
    int res=Stream.of(1, 2, 3).reduce((x,y)->x+y).get();
    assertEquals(6, res);
    String resStr=Stream.of("hello","java","test").reduce((x,y)->x+y).get();
    System.out.println(resStr);
    int resMax = Stream.of(2, 3, 24, 5).reduce(Integer::max).get();
    assertEquals(24, resMax);
    System.out.println(resMax);
  }
}
