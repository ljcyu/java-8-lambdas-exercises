package self;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SelfGroupingBy<K,T> implements Collector<T,Map<K,List<T>>, Map<K,List<T>>> {
  Function<T,K> getKey;
  public SelfGroupingBy(Function<T,K> getKey){
    this.getKey=getKey;
  }
  @Override
  public Supplier<Map<K, List<T>>> supplier() {
    return HashMap<K,List<T>>::new;
  }

  @Override
  public BiConsumer<Map<K, List<T>>, T> accumulator() {
    return (Map<K,List<T>> map,T t)->{
      K key=getKey.apply(t);
      if(map.containsKey(key)){
        map.get(key).add(t);
        return;
      }
      List<T> list=new ArrayList<>();
      list.add(t);
      map.put(key,list);
      return;
    };
  }

  @Override
  public BinaryOperator<Map<K, List<T>>> combiner() {
    return (Map<K,List<T>> left,Map<K,List<T>> right)->{
      left.putAll(right);
      return left;
    };
  }

  @Override
  public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
    return (Map<K,List<T>> res)->res;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.EMPTY_SET;
  }
}
