package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

public class SelfCombinerTest {
  @Test
  public void testSelfCombiner(){
    StringCombinerBySelf res= SampleData.getThreeAlbums().stream().map(Album::getName)
        .reduce(new StringCombinerBySelf(",","[","]"),
            StringCombinerBySelf::add,
            StringCombinerBySelf::merge);
    System.out.println(res);
  }
  @Test
  public void testCombinerByStringBuilder(){
    StringBuilder res=SampleData.getThreeAlbums().stream().map(Album::getName)
        .reduce(new StringBuilder(),(builder,ele)->{
          if(builder.length()==0) builder.append(",");
          builder.append(ele);
          return builder;
        },(left,right)->left.append(right));
  }
  @Test
  public void testCombinerByCollector() {
    String res = SampleData.getThreeAlbums().stream().map(Album::getName)
        .collect(new StringCollectorBySelf(",","[","]"));
    System.out.println(res);
  }
}
