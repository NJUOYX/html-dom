package mdom.attributes;

public abstract class Attribute_m {
    protected String attribute_value;

    public Attribute_m(String attribute_value){
        this.attribute_value = attribute_value;
    }

    public String getAttribute_value(){
        return attribute_value.toString();
    }

    public abstract void accept(AttributeVisitor attributeVisitor);

    @Override
    public String toString(){
        return String.format("%s=\"%s\"",getAttribute_Key(),attribute_value);
    }

    public abstract Boolean same(String attribute_key);

    public abstract String getAttribute_Key();
}
