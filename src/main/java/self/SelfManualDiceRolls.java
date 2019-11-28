package self;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

//模仿第六章例子的人工掷骰子
public class SelfManualDiceRolls {
  final static int N=1000000;
  ExecutorService service;
  Map<Integer,Double> results=new HashMap<>();
  int workPerThread;
  public static void main(String[] args){
    long start=System.currentTimeMillis();
    //new SelfManualDiceRolls().test();
    new SelfManualDiceRolls().parallel();
    System.out.println(System.currentTimeMillis()-start);
  }
  public void test(){
    List<Future> futures=new ArrayList<>();
    int processsors=Runtime.getRuntime().availableProcessors();
    service= Executors.newFixedThreadPool(processsors);
    workPerThread=N/processsors;
    for(int i=0;i<processsors;i++){
      Runnable r=()->{
        for(int j=0;j<workPerThread;j++) {
          ThreadLocalRandom random = ThreadLocalRandom.current();
          int firstNum = random.nextInt(1, 7);
          int secondNum = random.nextInt(1, 7);
          int sum = firstNum + secondNum;
          results.compute(sum, (key, previous) -> previous == null ? 1.0 / N : previous + 1.0 / N);
        }
      };
      futures.add(service.submit(r));
    }
    futures.forEach(future->{
      try {
        future.get();
      } catch (InterruptedException |ExecutionException e) {
        e.printStackTrace();
      }
    });
    results.entrySet().forEach(System.out::println);
    service.shutdown();
  }
  public void parallel(){
    IntStream.range(0,N).parallel().map(i->{
      ThreadLocalRandom random = ThreadLocalRandom.current();
      int firstNum = random.nextInt(1, 7);
      int secondNum = random.nextInt(1, 7);
      int sum = firstNum + secondNum;
      return sum;
    }).boxed().collect(groupingBy(it->it, summingDouble(it->1.0/N))).entrySet().forEach(System.out::println);
  }
}
