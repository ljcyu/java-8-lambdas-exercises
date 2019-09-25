package self;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Arrays.*;
import static org.junit.Assert.assertEquals;

public class Ex7LambdaTest {
    public List<String> toUpperCase(List<String> origin){
        return origin.stream().map(String::toUpperCase).collect(Collectors.toList());
    }
    @Test
    public void testUpperCase(){
        List<String> datas= asList("a","hello","b");
        List<String> res=toUpperCase(datas);
        assertEquals(asList("A","HELLO","B"),res);

    }
}
