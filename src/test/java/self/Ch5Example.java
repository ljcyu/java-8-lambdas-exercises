package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import javassist.tools.rmi.Sample;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

public class Ch5Example {
  @Test
  public void maxBySelf(){
    Function<Artist,Long> map= artist->artist.getMembers().count();
    Artist artist=SampleData.threeArtists().collect(maxBy(comparing(map))).get();
    assertNotNull(artist);
  }
  @Test
  public void partitionSelf(){
    Map<Boolean, List<Artist>> res=SampleData.threeArtists().collect(partitioningBy(Artist::isSolo));
    assertEquals(2,res.size());
    System.out.println(res);
  }
  @Test
  public void groupBySelf(){
    Map<Artist,List<Album>> res=SampleData.getThreeAlbums().stream().collect(groupingBy(Album::getMainMusician));
    assertFalse(res.isEmpty());
    System.out.println(res);
  }
  @Test
  public void albumCountByArtist(){
    Map<Artist,Long> res=SampleData.getThreeAlbums().stream().collect(groupingBy(Album::getMainMusician,counting()));
    System.out.println(res);
  }
  @Test
  public void albumNameByArtist(){
    Map<Artist,List<String>> res=SampleData.getThreeAlbums().stream().collect(groupingBy(Album::getMainMusician,mapping(Album::getName,toList())));
    System.out.println(res);
  }
  @Test
  public void joingSelf(){
    String res=SampleData.threeArtists().map(Artist::getName).collect(Collectors.joining(",","[","]"));
    System.out.println(res);
  }
  /**用reduce实现joint*/
  @Test
  public void selfJoinByReduce(){
    StringBuilder res=SampleData.getThreeArtists().stream()
            .map(Artist::getName)
            .reduce(new StringBuilder(),(builder,ele)->{
      if(builder.length()>0) builder.append(",");
      builder.append(ele);
      return builder;
    },(left,right)->left.append(right));
    System.out.println(res);

  }
}
