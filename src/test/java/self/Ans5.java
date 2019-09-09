package self;

import org.junit.Test;

import java.util.Comparator;
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
}
