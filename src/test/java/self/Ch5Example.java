package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import javassist.tools.rmi.Sample;
import org.junit.Test;


import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
}
