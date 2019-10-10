package self;

import org.junit.Test;

import java.util.function.Predicate;

interface IntPred{
    boolean test(Integer num);
}
public class Ex1 {
    void check(Predicate<Integer> predicate){
        System.out.println("check predicate");
    }
    void check(IntPred test){
        System.out.println("check IntPred");
    }
    @Test
    public void test(){
        //check(x->x>5);//有歧义，编译有问题
        check((IntPred) x->x>5);
    }
}
