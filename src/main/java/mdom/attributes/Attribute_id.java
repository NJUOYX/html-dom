package mdom.attributes;

public class Attribute_id extends Attribute_m{
    public Attribute_id(String attribute_value) {
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
        return "id";
    }

}
