package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollectorBySelf implements Collector<String,StringCombinerBySelf,StringCombinerBySelf> {
  @Override
  public Supplier<StringCombinerBySelf> supplier() {
    return ()->new StringCombinerBySelf(",","[","]");
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
    return null;
  }

  @Override
  public Function<StringCombinerBySelf, StringCombinerBySelf> finisher() {
    return (it)->{
      if(it.sb.length()!=0){
        it.sb.append(it.suffix);
      }
      return it;
    };
  }

}
