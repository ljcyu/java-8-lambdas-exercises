package self;

public class SelfCombiner2{
    String prefix,delimeter,suffix;
    StringBuilder sb;

    SelfCombiner2(String prefix,String delimeter,String suffix){
        this.prefix=prefix;this.delimeter=delimeter;this.suffix=suffix;
        sb=new StringBuilder();
    }

    public SelfCombiner2 add(String val){
        if(sb.length()!=0) sb.append(delimeter);
        sb.append(val);
        return this;
    }

    public SelfCombiner2 merge(SelfCombiner2 other){
        sb.append(other.sb);
        return this;
    }
    @Override public String toString(){
        return prefix+sb+suffix;
    }
}
