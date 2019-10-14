package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Ex3 {
    @Test
    public void upperCase(){
        List<String> res=asList("a","b","c").stream().map(String::toUpperCase).collect(toList());
        System.out.println(res);
    }
    /**是否数字开始*/
    @Test
    public void testDigitStart(){
        List<String> res=asList("a","23daf","b").stream().filter(Ex3::isDigitStart).collect(toList());
        System.out.println(res);
    }
    public static boolean isDigitStart(String str){
        return Character.isDigit(str.charAt(0));
    }

    @Test
    public void testFlatMap(){
        List<Integer> res=Stream.of(asList(1,2),asList(3,4)).flatMap(it->it.stream()).collect(toList());
        System.out.println(res);
    }

    @Test
    public void minByReduce(){
        int res=asList(4,5,6,3,4).stream().reduce((accum,x)->{
            return accum<x?accum:x;
        }).get();
        int res2=asList(4,5,6,3,4).stream().reduce(Integer::min).get();
        System.out.println(res+" "+res2);
    }
    @Test
    public void minByReduce2(){
        int res=asList(4,5,6,3,4).stream().reduce(Integer.MAX_VALUE,Integer::min).intValue();
        System.out.println(res);
    }

    @Test
    public void sumByReduce(){
        int res=asList(3,4,5,2,1).stream().reduce(Integer::sum).get();
        System.out.println(res);
    }
    public static boolean timeMoreThan60s(Track track){
        return track.getLength()>60;
    }
    /**找到时长超过1分钟*/
    @Test
    public void findTracks(){
        List<String> res=SampleData.getThreeAlbums().stream()
            .flatMap(Album::getTracks)
            .filter(Ex3::timeMoreThan60s)
            .map(Track::getName)
            .collect(toList());
        System.out.println(res);
    }
    @Test
    public void addUp(){
        int res=asList(2,3,4,4).stream().reduce(Integer::sum).get();
        System.out.println(res);
    }
}
