package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
//T,A,R
public class StringCollectorBySelf implements Collector<String,StringCombinerBySelf,String> {
  String delimeter,prefix,suffix;
  public StringCollectorBySelf(String _delimeter,String _prefix,String _suffix){
    this.delimeter=_delimeter;
    this.prefix=_prefix;
    this.suffix=_suffix;
  }
  @Override
  public Supplier<StringCombinerBySelf> supplier() {
    return ()->new StringCombinerBySelf(delimeter,prefix,suffix);
  }

  @Override
  public BiConsumer<StringCombinerBySelf, String> accumulator() {
    return StringCombinerBySelf::add;
  }

  @Override
  public BinaryOperator<StringCombinerBySelf> combiner() {
    return StringCombinerBySelf::merge;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }

  @Override
  public Function<StringCombinerBySelf, String> finisher() {
    return StringCombinerBySelf::toString;
  }
}
