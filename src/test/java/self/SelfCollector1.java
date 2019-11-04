package self;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
//T,A,R
public class SelfCollector1 implements Collector<String, SelfCombiner1,String> {
  String delimeter,prefix,suffix;
  public SelfCollector1(String _delimeter, String _prefix, String _suffix){
    this.delimeter=_delimeter;
    this.prefix=_prefix;
    this.suffix=_suffix;
  }
  @Override
  public Supplier<SelfCombiner1> supplier() {
    return ()->new SelfCombiner1(delimeter,prefix,suffix);
  }

  @Override
  public BiConsumer<SelfCombiner1, String> accumulator() {
    return SelfCombiner1::add;
  }

  @Override
  public BinaryOperator<SelfCombiner1> combiner() {
    return SelfCombiner1::merge;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }

  @Override
  public Function<SelfCombiner1, String> finisher() {
    return SelfCombiner1::toString;
  }
}
