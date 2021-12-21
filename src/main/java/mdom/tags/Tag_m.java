package mdom.tags;

public class Tag_m {
    private final String tag;
    public Tag_m(String tag){
        this.tag = tag.toLowerCase();
    }

    public String getBeginTag(String attribute){
        return String.format("<%s %s>", tag, attribute);
    }

    public String getEndTag(){return String.format("</%s>", tag);}

    public Boolean equals(String tagName){
        return tag.equalsIgnoreCase(tagName);
    }

    public String toString(){
        return tag;
    }

}
