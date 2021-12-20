package mdom.nodes;

import mdom.nodes.Document;
import mdom.nodes.Element;

public abstract class NodeVisitor {
    public void visit(Element element){}

    public void visit(Document document){}

    public void visit(TextNode textNode){}
}
