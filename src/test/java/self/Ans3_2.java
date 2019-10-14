package self;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class Ans3_2 {

  /**
   * A function that takes in artists and returns a list of strings with their names and
   * places of origin
   */
  public static String mapArtistToNameAndOrigin(Artist artist) {
    return String.format("[%s,%s]", artist.getName(), artist.getNationality());
  }

  @Test
  public void getNamesAndOrigin() {
    List<String> res = SampleData.getThreeArtists().stream()
        .map(Ans3_2::mapArtistToNameAndOrigin)
        .collect(Collectors.toList());
    System.out.println(res);
  }
}
