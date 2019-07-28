package self;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BinaryOperator;
import java.util.function.Function;
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

  /**reduce实现filter*/
  public <T> List<T> filter(Stream<T> stream, Predicate<T> predicate) {
    List<T> res = new ArrayList<>();
    stream.reduce(res, (List<T> accu, T element) -> {
      if (predicate.test(element)) accu.add(element);
      return accu;
    }, (left, right) -> {
      System.out.println("-->left "+left);
      System.out.println("-->right "+right);
      left.addAll(right);
      System.out.println("<--left "+left);
      return left;
    });
    return res;
  }
  /**reduce三参数和两参数的区别*/
  @Test public void testFilter(){
    List<Integer> res=filter(Stream.of(1,7,8,9,2,3,5,6),num->num>3);
    System.out.println(res.size());
  }
  public <T> List<T> filterByParallel(Stream<T> stream, Predicate<T> predicate) {
    CopyOnWriteArrayList<T> res = new CopyOnWriteArrayList<>();
    stream.reduce(res, (List<T> accu, T element) -> {
      if (predicate.test(element)) accu.add(element);
      return accu;
    }, (left, right) -> {
      System.out.println("-->left "+left);
      System.out.println("-->right "+right);
      left.addAll(right);
      System.out.println("<--left "+left);
      return left;
    });
    return res;
  }
  @Test public void testFilterByParallel(){
    List<Integer> res=filterByParallel(Stream.of(1,7,8,9,2,3,5,6).parallel(),num->num>3);
    System.out.println(res.size());
  }
  public <T,R> List<R> map(Stream<T> stream, Function<? super T,? extends R> mapper){
    List<R> res=new ArrayList<>();
    stream.reduce(res,(List<R> accu,T element)->{
      accu.add(mapper.apply(element));
      return accu;
    },(left,right)->{
      left.addAll(right);
      return left;
    });
    return res;
  }
  @Test
  public void testSelfMap(){
    List<Character> res=map(Stream.of("hello","world","test"),str->str.charAt(0));
    assertEquals(res, Arrays.asList('h','w','t'));
  }
}
