package self;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class Ex7LambdaTest {
    public List<String> toUpperCase(List<String> origin){
        return origin.stream().map(String::toUpperCase).collect(toList());
    }
    @Test
    public void testUpperCase(){
        List<String> datas= asList("a","hello","b");
        List<String> res=toUpperCase(datas);
        assertEquals(asList("A","HELLO","B"),res);

    }

    @Test
    public void testFirstCharToUppperCase() {
     List<String>  datas=asList("hello");
     List<String> res=firstCharToUpperCase(datas);
     assertEquals(asList("Hello"),res);
    }

    //这个不好测试，改为用Method Reference
    public List<String> firstCharToUpperCase(List<String> origin){
        return origin.stream().map(s->{
            String firstStr=""+Character.toUpperCase(s.charAt(0));
            return firstStr+s.substring(1);
        }).collect(toList());
    }

    public  static String firstCharToUpperCaseMethodRef(String s){
        String firstStr=""+Character.toUpperCase(s.charAt(0));
        return firstStr+s.substring(1);
    }
    @Test
    public void testFirstCharToUppperCaseMethodRef(){
        String s="hello";
        assertEquals("Hello",firstCharToUpperCaseMethodRef(s));
    }
    @Test
    public void firstCharToUppperCaseByMethodRef() {
        List<String>  datas=asList("hello");
        List<String> res=datas.stream().map(Ex7LambdaTest::firstCharToUpperCaseMethodRef).collect(toList());
        System.out.println(res);
    }
}
