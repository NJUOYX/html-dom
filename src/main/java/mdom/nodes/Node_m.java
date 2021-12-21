package mdom.nodes;

import mdom.attributes.AttributeVisitor;
import mdom.attributes.Attribute_m;
import mdom.tags.Tag_m;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Node_m {
    protected Tag_m tag;
    protected List<Attribute_m> attributes;

    protected Node_m parent;
    protected List<Node_m> children;

    public Node_m() {
        this.tag = null;
        this.attributes = new LinkedList<>();
        ;
        this.children = new LinkedList<>();
        ;
        this.parent = null;
    }

    public Node_m(Tag_m tag, List<Attribute_m> attributes) {
        this.tag = tag;
        this.attributes = new LinkedList<>();
        this.attributes.addAll(attributes);
        this.children = new LinkedList<>();
        this.parent = null;
    }

    public Node_m(Tag_m tag, Attribute_m attribute) {
        this.tag = tag;
        this.attributes = new LinkedList<>();
        this.attributes.add(attribute);
        this.children = new LinkedList<>();
        this.parent = null;
    }

    public Tag_m getTag() {
        return tag;
    }

    public Attribute_m getAttribute(int index) {
        return attributes.get(index);
    }

    /**
     * @param key attribute key
     * @return attribute value or null if key not exist(or has no value)
     */
    public String getAttribute(String key) {
        for (Attribute_m attributeM : attributes) {
            if (attributeM.same(key)) {
                return attributeM.getAttribute_value();
            }
        }
        return null;
    }

    public void visitAllChildren(NodeVisitor nodeVisitor) {
        children.forEach(child -> {
            child.accept(nodeVisitor);
            child.visitAllChildren(nodeVisitor);
        });
    }

    public void visitAllAttributes(AttributeVisitor attributeVisitor) {
        attributes.forEach(attributeM -> attributeM.accept(attributeVisitor));
    }

    public void addChild(Node_m child) {
        children.add(child);
    }

    public Boolean removeChild(Node_m child){
        return children.remove(child);
    }

    public List<Node_m> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addParent(Node_m parent) {
        this.parent = parent;
    }

    public Node_m getParent() {
        return parent;
    }

    public abstract void accept(NodeVisitor nodeVisitor);

    @Override
    public String toString() {
        String strAttributes = "";
        if (!attributes.isEmpty()) {
            int i = 0;
            for (; i < attributes.size() - 1; ++i) {
                strAttributes += attributes.get(i) + ",";
            }
            strAttributes += attributes.get(i);
        }
        String beginTag = tag.getBeginTag(strAttributes);
        String elements = "";
        for (Node_m nodeM : children) {
            elements += nodeM;
        }
        return beginTag + elements + tag.getEndTag();
    }


}
