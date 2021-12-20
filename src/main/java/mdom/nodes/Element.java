package mdom.nodes;

import mdom.attributes.Attribute_m;
import mdom.tags.Tag_m;

import java.util.List;

public class Element extends Node_m {
    public Element(Tag_m tag, List<Attribute_m> attributes) {
        super(tag, attributes);
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
