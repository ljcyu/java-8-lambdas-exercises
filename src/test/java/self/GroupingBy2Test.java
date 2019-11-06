package self;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingBy2Test {
    @Test
    public void test(){
        Stream<String> s = Stream.of("John", "Paul", "George", "John","Paul", "John");
        Map<String, List<String>> res=s.collect(new GroupingBy2<>(it->""+it.charAt(0)));
        System.out.println(res);
    }

    @Test
    public void testArtist(){
        Map<String,List<Artist>> res=SampleData.getThreeArtists().stream().collect(new GroupingBy2<>(Artist::getNationality));
        System.out.println(res);
    }
}
