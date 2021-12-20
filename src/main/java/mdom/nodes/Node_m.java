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

    protected List<Node_m> children;

    public Node_m(){
        tag = null;
        attributes = Collections.emptyList();
    }

    public Node_m(Tag_m tag, List<Attribute_m>attributes){
        this.tag = tag;
        this.attributes = new LinkedList<>();
        this.attributes.addAll(attributes);
        this.children = new LinkedList<>();
    }

    public Node_m(Tag_m tag, Attribute_m attribute){
        this.tag = tag;
        this.attributes = new LinkedList<>();
        this.attributes.add(attribute);
        this.children = new LinkedList<>();
    }

    public Tag_m getTag(){
        return tag;
    }

    public Attribute_m getAttribute(int index){
        return attributes.get(index);
    }

    /**
     *
     * @param key attribute key
     * @return attribute value or null if key not exist(or has no value)
     */
    public String getAttribute(String key){
        for(Attribute_m attributeM: attributes){
            if(attributeM.same(key)){
                return attributeM.getAttribute_value();
            }
        }
        return null;
    }

    public void visitAllAttributes(AttributeVisitor attributeVisitor){
        attributes.forEach(attributeM -> attributeM.accept(attributeVisitor));
    }

    public void addChild(Node_m child){
        children.add(child);
    }

    public List<Node_m> getChildren(){
        return Collections.unmodifiableList(children);
    }

    public abstract void accept(NodeVisitor nodeVisitor);

    @Override
    public String toString() {
        String strAttributes = "";
        if (attributes.size() > 1) {
            int i = 1;
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
