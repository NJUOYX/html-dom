package mdom.attributes;

import java.util.Locale;

public class AttributeFactory {
    public static Attribute_m attributeMaker(String attribute_key, String attribute_value){
        switch (attribute_key.toLowerCase(Locale.ROOT)){
            case "class":return new Attribute_class(attribute_value);
            case "id":return new Attribute_id(attribute_value);
            default:return new DefaultAttribute(attribute_key, attribute_value);
        }
    }
}
