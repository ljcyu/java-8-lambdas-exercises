package self;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.SampleData;
import org.junit.Test;

public class StringCombinerBySelf {
  String delimeter,prefix,suffix;
  static StringBuilder sb=new StringBuilder();
  public  nStringCombinerBySelf(String _delimeter,String _prefix,String _suffix){
    this.delimeter=_delimeter;
    this.prefix=_prefix;
    this.suffix=_suffix;

  }
  public StringCombinerBySelf add(String ele){
    if(sb.length()!=0){
      sb.append(delimeter);
    }
     sb.append(ele);
    return this;
  }
  public StringCombinerBySelf merge(StringCombinerBySelf other){
    sb.append(other.sb);
    return this;
  }

  @Override
  public String toString() {
    return prefix+sb.toString()+suffix;
  }
  @Test
  public void testSelfCollector(){
    StringCombinerBySelf res=SampleData.getThreeAlbums().stream().map(Album::getName)
        .reduce(new StringCombinerBySelf(",","[","]"),
            StringCombinerBySelf::add,
            StringCombinerBySelf::merge);
    System.out.println(res);
  }
}

