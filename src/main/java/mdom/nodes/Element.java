package mdom.nodes;

import mdom.attributes.Attribute_m;
import mdom.tags.Tag_m;

import java.util.LinkedList;
import java.util.List;

public class Element extends Node_m {
    public Element(Tag_m tag, List<Attribute_m> attributes) {
        super(tag, attributes);
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    /**
     *
     * @return TextNode or null if no TextNode exist in this node
     */
    public TextNode getInnerHTML(){
        List<TextNode>res = new LinkedList<>();
        visitAllChildren(new NodeVisitor() {
            @Override
            public void visit(TextNode textNode) {
                res.add(textNode);
            }
        });
        if(res.isEmpty()){
            return null;
        }else {
            return res.get(0);
        }
    }

}
