package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;

import java.util.function.ToLongFunction;


public class Ex7 {
    @Test
    public void countRunningTime(){
        long time=SampleData.getThreeAlbums().stream().flatMap(Album::getTracks)
                .map(Track::getLength).reduce(Integer::sum).get();
        System.out.println(time);
    }
    @Test
    public void countRunningTime2(){
        long time=SampleData.getThreeAlbums().stream()
                .mapToLong(
                        album->album.getTracks().
                                mapToLong(track->track.getLength())
                                .sum())
                .sum();
        System.out.println(time);
    }
    @Test
    public void countMusicians(){
        long count=SampleData.getThreeAlbums().stream().flatMap(Album::getMusicians).count();
        System.out.println(count);

    }
    @Test
    public void countMusicians2(){
        long count=SampleData.getThreeAlbums().stream()
                .mapToLong(album->album.getMusicians().count()).sum();
        System.out.println(count);

    }
    @Test
    public void countTracks(){
        /*long num=SampleData.getThreeAlbums().stream().flatMap(Album::getTracks).count();
        System.out.println(num);*/
        long num2=SampleData.getThreeAlbums().stream().map(album->album.getTrackList().size()).reduce(Integer::sum).get();
        System.out.println(num2);
    }
    public void countFeature(ToLongFunction<Album> function){
        long res=SampleData.getThreeAlbums().stream().mapToLong(function).sum();
        System.out.println(res);
    }
    @Test
    public void countTracks3(){
        countFeature(album->album.getTrackList().size());
    }


}
