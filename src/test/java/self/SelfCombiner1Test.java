package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

import java.util.ArrayList;

public class SelfCombiner1Test {
  @Test
  public void testSelfCombiner(){
    SelfCombiner1 res= SampleData.getThreeAlbums().stream().map(Album::getName)
        .reduce(new SelfCombiner1(",","[","]"),
            SelfCombiner1::add,
            SelfCombiner1::merge);
    System.out.println(res);
  }
    @Test
    public void testJoinByReduce(){
        ArrayList<String> res= SampleData.getThreeAlbums().stream().map(Album::getName)
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);
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
        .collect(new SelfCollector1(",","[","]"));
    System.out.println(res);
  }
}
