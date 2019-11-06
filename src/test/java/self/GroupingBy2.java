package self;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy2<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
    Function<T,K> f;

    public GroupingBy2(Function<T,K> f) {
        this.f = f;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (Map<K, List<T>> map, T ele) -> {
            K key=f.apply(ele);
            map.computeIfAbsent(key,key2->new ArrayList<>());
            map.get(key).add(ele);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (left,right)->{
            left.forEach((key,value)->{
                if(right.containsKey(key)){
                    left.get(key).addAll(right.get(key));
                    right.remove(key);
                }
            });
            left.putAll(right);
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