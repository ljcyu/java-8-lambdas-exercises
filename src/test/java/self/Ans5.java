package self;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ans5 {
  Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
      "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
  @Test
  public void longestNameByReduce(){
    Comparator<String> byLength=Comparator.comparing(String::length);
    String name=names.reduce((acc,val)->{
      return byLength.compare(acc,val)>0?acc:val;
    }).get();
    System.out.println(name);
  }
  @Test
  public void longestNameByCollect(){
    Comparator<String> byLength=Comparator.comparing(String::length);
    String res=names.max(byLength).get();
    //String res2=names.collect(Collectors.maxBy(byLength)).get();
    System.out.println(res);
  }
  //computeIfAbsent会在计算完后放入Map中，不要自己put
  Map<Integer,Integer> fibs=new HashMap<>();
  public int fib(int i){
    return fibs.computeIfAbsent(i,(num)->{
      System.out.println("..computing..."+num);
      return fib(num-1)+fib(num-2);
    });
  }
  @Test
  public void testFib(){
    fibs.put(1,1);
    fibs.put(2,1);
    System.out.println(fib(4));
    System.out.println(fib(5));
    System.out.println(fib(4));
    System.out.println(fib(6));
    System.out.println(fibs);
  }
}
