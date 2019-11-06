package self;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Ex5_2 {
    private Stream<String> s;

    @BeforeMethod
    public void init() {
        s = Stream.of("John", "Paul", "George", "John","Paul", "John");
    }

    @Test
    public void count() {
        Map<String, Long> names = Stream.of("John", "Paul", "George", "John",
                "Paul", "John").collect(groupingBy(it -> it, counting()));
        System.out.println(names);
    }

    @Test
    public void countByReduce() {
        long count = s.mapToLong(it -> 1L).reduce(Long::sum).getAsLong();
        System.out.println(count);
    }
    @Test
    public void countByReduce2(){
        long count2 = s.reduce(0L, (sum, ele) -> sum + 1, (left, right) -> left + right);
        System.out.println(count2);
    }
}
