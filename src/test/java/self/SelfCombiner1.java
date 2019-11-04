package self;

public class SelfCombiner1 {
  String delimeter,prefix,suffix;
  StringBuilder sb;
  public SelfCombiner1(String _delimeter, String _prefix, String _suffix){
    this.delimeter=_delimeter;
    this.prefix=_prefix;
    this.suffix=_suffix;
    sb = new StringBuilder();
  }
  public SelfCombiner1 add(String ele){
    if(sb.length()!=0){
      sb.append(delimeter);
    }
     sb.append(ele);
    return this;
  }
  public SelfCombiner1 merge(SelfCombiner1 other){
    sb.append(other.sb);
    return this;
  }

  @Override
  public String toString() {
    return prefix+sb.toString()+suffix;
  }
}

