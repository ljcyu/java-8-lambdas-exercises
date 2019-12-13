package self;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex8_2 {
    public void dispPrime(int upto){
        long num=IntStream.range(2,upto).parallel().filter(this::isPrime).count();
        System.out.println(num);
    }
    public boolean isPrime(int num){
        return IntStream.range(2,num).allMatch(i->num%i!=0);
    }
    @Test
    public void showPrimes(){
        dispPrime(1000000);
    }
    @Test
    public void readLines()throws IOException {
        Path path= Paths.get("H:\\kuaipan\\develop\\projects\\jse\\tlj\\build.xml");
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile()),"gb18030"))){
            reader.lines().forEach(System.out::println);
        }
    }

    public void findHeadings(Reader input){
        try(BufferedReader reader=new BufferedReader(input)){
            reader.lines().filter(line->line.endsWith(":"))
                    .map(line->line.substring(0,line.length()-1))
                    .collect(Collectors.toList());
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
    public void findHeading2(Reader input){
        doT(input,lines->lines.filter(line->line.endsWith(":"))
                .map(line->line.substring(0,line.length()-1))
                .collect(Collectors.toList()),RuntimeException::new);
    }
    public <T> T doT(Reader input, Function<Stream<String>,T> handler,Function<IOException,RuntimeException> error){
        try(BufferedReader reader=new BufferedReader(input)){
            return handler.apply(reader.lines());
        }catch(IOException ex){
            throw error.apply(ex);
        }
    }
}
