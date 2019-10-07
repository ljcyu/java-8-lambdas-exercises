package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.Set;
import java.util.function.ToLongFunction;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;


public class Ex7 {
  @Test
  public void countRunningTime() {
    long time = SampleData.getThreeAlbums().stream().flatMap(Album::getTracks)
        .map(Track::getLength).reduce(Integer::sum).get();
    System.out.println(time);
  }

  @Test
  public void countRunningTime2() {
    long time = SampleData.getThreeAlbums().stream()
        .mapToLong(
            album -> album.getTracks().
                mapToLong(track -> track.getLength())
                .sum())
        .sum();
    System.out.println(time);
  }

  @Test
  public void countMusicians() {
    long count = SampleData.getThreeAlbums().stream().flatMap(Album::getMusicians).count();
    System.out.println(count);

  }

  @Test
  public void countMusicians2() {
    long count = SampleData.getThreeAlbums().stream()
        .mapToLong(album -> album.getMusicians().count()).sum();
    System.out.println(count);

  }

  @Test
  public void countTracks() {
        /*long num=SampleData.getThreeAlbums().stream().flatMap(Album::getTracks).count();
        System.out.println(num);*/
    long num2 = SampleData.getThreeAlbums().stream().map(album -> album.getTrackList().size()).reduce(Integer::sum).get();
    System.out.println(num2);
  }

  public void countFeature(ToLongFunction<Album> function) {
    long res = SampleData.getThreeAlbums().stream().mapToLong(function).sum();
    System.out.println(res);
  }

  @Test
  public void countTracks3() {
    countFeature(album -> album.getTrackList().size());
  }

  /**
   * peek和forEach的不同，peek不会结束流，forEach会结束流
   */
  @Test
  public void testPeek() {
    Set<Integer> nums=asList(1,2,3,4,5).stream().peek(System.out::println).collect(toSet());

  }
}
