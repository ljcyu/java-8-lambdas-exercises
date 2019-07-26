package self;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class Ans3 {
  public int addUp(Stream<Integer> numbers){
    return numbers.reduce(Integer::sum).get();
  }
  @Test
  public void testSum(){
    assertEquals(10,addUp(Stream.of(1,2,3,4)));
    assertEquals(12,addUp(Stream.of(3,4,5)));
  }
}
