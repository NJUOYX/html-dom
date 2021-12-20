package mdom.nodes;

import mdom.attributes.Attribute_m;
import mdom.tags.Tag_document;
import mdom.tags.Tag_m;

import java.util.List;

public class NodeFactory {
    public static Node_m nodeMaker(Tag_m tag, List<Attribute_m> attributes){
        if(tag instanceof Tag_document){
            return maker((Tag_document) tag, attributes);
        }
        return maker(tag, attributes);
    }

    public static Node_m nodeMaker(Tag_m tag, Attribute_m attribute){
        if(tag instanceof Tag_document){
            return maker((Tag_document) tag, List.of(attribute));
        }
        return maker(tag, List.of(attribute));
    }

    public static Node_m nodeMaker(String text){
        return maker(text);
    }

    public static void addChild(Node_m parent, Node_m child){
        parent.addChild(child);
    }

    private static Document maker(Tag_document tag, List<Attribute_m> attributes){
        return new Document(tag, attributes);
    }

    private static Element maker(Tag_m tag, List<Attribute_m> attributes){
        return new Element(tag, attributes);
    }

    private static TextNode maker(String text){
        return new TextNode(text);
    }
}
