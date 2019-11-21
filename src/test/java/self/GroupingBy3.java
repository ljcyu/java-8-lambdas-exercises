package self;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy3<T,K> implements Collector<T,Map<K, List<T>>, Map<K, List<T>>> {
    Function<T,K> function;
    public GroupingBy3(Function<T,K> _function){
        this.function=_function;
    }
    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (Map<K,List<T>> map,T t)->{
            K val=function.apply(t);
            map.putIfAbsent(val,new ArrayList<>());
            map.get(val).add(t);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (Map<K,List<T>> left,Map<K,List<T>> right)->{
             right.forEach((key, val) ->{
                 left.merge(key,val,(List<T> leftVal,List<T> rightVal)->{
                     leftVal.addAll(rightVal);
                     return leftVal;
                 });
             });
             return left;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return it->it;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
