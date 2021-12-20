package mdom;

import mdom.attributes.*;
import mdom.nodes.Element;
import mdom.nodes.NodeFactory;
import mdom.nodes.NodeVisitor;
import mdom.nodes.Node_m;
import mdom.tags.TagFactory;
import mdom.tags.Tag_m;
import org.htmlparser.*;
import org.htmlparser.Tag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.ParserException;

import java.util.*;

public class Mdom {

    private Parser parser;
    private Node_m document;

    private Mdom(String htmlStr) {
        try {
            parser = new Parser(htmlStr);
            parser.setEncoding("");
            build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Mdom fromString(String htmlStr) {
        return new Mdom(htmlStr);
    }

    public static Mdom fromFile(String htmlFile) {
        return new Mdom(HtmlGetter.fileGetter(htmlFile));
    }

    public static Mdom fromWeb(String url) {
        return new Mdom(HtmlGetter.webGetter(url));
    }

    public Element getElementById(String id) {
        List<Element>res = new LinkedList<>();
        NodeVisitor nodeVisitor = new NodeVisitor() {
            @Override
            public void visit(Element element) {
                AttributeVisitor attributeVisitor = new AttributeVisitor() {
                    @Override
                    public void visit(Attribute_id attribute_id) {
                        res.add(element);
                    }
                };
                element.visitAllAttributes(attributeVisitor);
            }
        };
        visitAll(nodeVisitor);
        if(res.isEmpty()){
            return null;
        }else{
            return res.get(0);
        }
    }

    public List<Element> getElementsByTagName(String tagName){
        List<Element>res = new LinkedList<>();
        NodeVisitor nodeVisitor = new NodeVisitor() {
            @Override
            public void visit(Element element) {
                if(element.getTag().equals(tagName)){
                    res.add(element);
                }
            }
        };
        visitAll(nodeVisitor);
        return res;
    }

    public List<Element> getElementsByClassName(String className){
        List<Element>res = new LinkedList<>();
        NodeVisitor nodeVisitor = new NodeVisitor() {
            @Override
            public void visit(Element element) {
                AttributeVisitor attributeVisitor = new AttributeVisitor() {
                    @Override
                    public void visit(Attribute_class attribute_class) {
                        if(attribute_class.equals(className)){
                            res.add(element);
                        }
                    }
                };
                element.visitAllAttributes(attributeVisitor);
            }
        };
        visitAll(nodeVisitor);
        return res;
    }

    public String printHtml(){
        return document.toString();
    }


    private void build() {
        try {
            Node node = buildRoot();
            if(node != null){
                recurseBuild(document, node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recurseBuild(Node_m parentM,Node parent){
        Node child = parent.getFirstChild();
        while(child != null){
            if(child instanceof Tag) {
                Node_m childM = nodeMaker((Tag) child);
                NodeFactory.addChild(parentM, childM);
                recurseBuild(childM, child);
            }else if(child instanceof Text){
                Text text = (Text)child;
                if(!text.getText().isBlank()) {
                    NodeFactory.addChild(parentM, nodeMaker(text));
                }
            }
            child = child.getNextSibling();
        }
    }

    private Node buildRoot()throws ParserException{
        NodeIterator nodeIterator = parser.elements();
        while(nodeIterator.hasMoreNodes()){
            Node node = nodeIterator.nextNode();
            if(node instanceof Tag){
                Tag tag = (Tag)node;
                if(tag.getTagName().equalsIgnoreCase("html")) {
                    document = nodeMaker((Tag) node);
                    return node;
                }
            }
        }
        return null;
    }

    private Node_m nodeMaker(Tag tag){
        Tag_m tagM = TagFactory.tagMaker(tag.getRawTagName());
        Vector<Attribute> attributes = tag.getAttributesEx();
        List<Attribute_m> attributesM = new LinkedList<>();
        attributes.forEach(attribute -> {
            if(attribute.getName()!=null) {
                attributesM.add(AttributeFactory.attributeMaker(attribute.getName(), attribute.getValue()));
            }
        });
        Node_m nodeM = NodeFactory.nodeMaker(tagM, attributesM);
        return nodeM;
    }

    private Node_m nodeMaker(Text text){
        return NodeFactory.nodeMaker(text.getText());
    }

    private void visitAll(NodeVisitor nodeVisitor){
        visitNode(document, nodeVisitor);
    }

    private void visitNode(Node_m nodeM, NodeVisitor nodeVisitor){
        nodeM.accept(nodeVisitor);
        nodeM.getChildren().forEach(child->visitNode(child, nodeVisitor));
    }

}
