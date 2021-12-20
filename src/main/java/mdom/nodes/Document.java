package mdom.nodes;

import mdom.attributes.Attribute_m;
import mdom.tags.Tag_document;

import java.util.List;

public class Document extends Node_m {
    public Document(Tag_document tag, List<Attribute_m> attributes){
        super(tag, attributes);
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
