package self;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import javassist.tools.rmi.Sample;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

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
  @Test
  public void total(){
    long sum=SampleData.getThreeArtists().stream().mapToLong(artist->artist.getMembers().count()).sum();
    System.out.println(sum);
  }
  public static long lowerNum(String str){
    return str.chars().filter(Character::isLowerCase).count();
  }

  @Test
  public void count(){
    String s="stewtRerreRt";
    long num=s.chars().filter(Character::isLowerCase).count();
    System.out.println(num);
  }
  /**小写字母最多的字符串*/
  @Test
  public void findLongestLower() {
    List<String> strs=asList("yuRere","Rere","dfsafRms","Hello World");
    String res=strs.stream().reduce((accum,str)->{
      return lowerNum(accum)>lowerNum(str)?accum:str;
    }).get();
    System.out.println(res);
  }
  @Test
  public void testSelfMap(){
    List<String> res=mapBySelf(SampleData.getThreeArtists().stream(),Artist::getName);
    System.out.println(res);
  }
  public <T,R> List<R> mapBySelf(Stream<T> stream, Function<T,R> function){
    List<R> res=stream.reduce(new ArrayList<R>(),(List<R> accu,T ele)->{
      accu.add(function.apply(ele));
      return accu;
      },(left,right)->{
        left.addAll(right);
        return left;
      });
    return res;
  }
}
