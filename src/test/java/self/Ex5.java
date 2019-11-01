package self;

import org.junit.Test;

import java.util.stream.Stream;

public class Ex5 {
    @Test
    public void join(){
        StringBuilder res=Stream.of("java","python","c#").reduce(new StringBuilder(),(sb,val)->{
            if(sb.length()!=0){
                sb.append(",");
            }
            sb.append(val);
            return sb;
        },(left,right)->{
            left.append(right);
            return left;
        });
        System.out.println(res);
    }
}
