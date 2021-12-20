package mdom.attributes;

public class Attribute_class extends Attribute_m{
    public Attribute_class(String attribute_value) {
        super(attribute_value);
    }

    @Override
    public void accept(AttributeVisitor attributeVisitor) {
        attributeVisitor.visit(this);
    }

    @Override
    public Boolean same(String attribute_key) {
        return getAttribute_Key().equalsIgnoreCase(attribute_key);
    }

    @Override
    public String getAttribute_Key() {
        return "class";
    }

    public Boolean equals(String value){
        return attribute_value.equalsIgnoreCase(value);
    }


}
