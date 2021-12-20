package mdom.attributes;

public class DefaultAttribute extends Attribute_m{
    private final String attribute_key;
    public DefaultAttribute(String attribute_key, String attribute_value) {
        super(attribute_value);
        this.attribute_key = attribute_key;
    }

    @Override
    public void accept(AttributeVisitor attributeVisitor) {
        attributeVisitor.visit(this);
    }

    @Override
    public Boolean same(String attribute_key) {
        return attribute_key.equalsIgnoreCase(attribute_key);
    }

    @Override
    public String getAttribute_Key() {
        return attribute_key;
    }

}
