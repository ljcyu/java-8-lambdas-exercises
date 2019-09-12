package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import com.insightfullogic.java8.examples.chapter1.Track;
import org.junit.Test;


public class Ex7 {
    @Test
    public void countRunningTime(){
        long time=SampleData.getThreeAlbums().stream().flatMap(Album::getTracks)
                .map(Track::getLength).reduce(Integer::sum).get();
        System.out.println(time);
    }
    @Test
    public void countMusicians(){
        long count=SampleData.getThreeAlbums().stream().flatMap(Album::getMusicians).count();
        System.out.println(count);

    }
    @Test
    public void countTracks(){
        long num=SampleData.getThreeAlbums().stream().flatMap(Album::getTracks).count();
        System.out.println(num);
        long num2=SampleData.getThreeAlbums().stream().map(album->album.getTrackList().size()).reduce(Integer::sum).get();
        System.out.println(num2);
    }
}
