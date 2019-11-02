package self;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SelfCollector2 implements Collector<String,SelfCombiner2,String> {
    String prefix,delimeter,suffix;


    SelfCollector2(String prefix,String delimeter,String suffix){
        this.prefix=prefix;this.delimeter=delimeter;this.suffix=suffix;
    }
    @Override
    public Supplier<SelfCombiner2> supplier() {
        return ()->new SelfCombiner2(prefix,delimeter,suffix);
    }

    @Override
    public BiConsumer<SelfCombiner2, String> accumulator() {
        return SelfCombiner2::add;
    }

    @Override
    public BinaryOperator<SelfCombiner2> combiner() {
        return SelfCombiner2::merge;
    }

    @Override
    public Function<SelfCombiner2, String> finisher() {
        return SelfCombiner2::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
