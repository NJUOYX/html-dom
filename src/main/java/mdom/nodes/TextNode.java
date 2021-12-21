package mdom.nodes;

public class TextNode extends Node_m{
    private String text;

    public TextNode(String text) {
        super();
        if(text == null){
            this.text = "";
        }else {
            this.text = text;
        }
    }

    @Override
    public void accept(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }

    @Override
    public String toString(){
        return text.toString();
    }

    public void setText(String text){
        if(text == null){
            this.text = "";
        }else {
            this.text = text;
        }
    }
}
