package self;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class Ans3 {
  public int addUp(Stream<Integer> numbers) {
    return numbers.reduce(Integer::sum).get();
  }

  @Test
  public void testSum() {
    assertEquals(10, addUp(Stream.of(1, 2, 3, 4)));
    assertEquals(12, addUp(Stream.of(3, 4, 5)));
  }

  public <T> List<T> filter(Stream<T> stream, Predicate<T> predicate) {
    List<T> res = new ArrayList<>();
    stream.reduce(res, (List<T> accu, T element) -> {
      if (predicate.test(element)) accu.add(element);
      return accu;
    }, (left, right) -> {
      left.addAll(right);
      return left;
    });
    return res;
  }
}
