package mdom;

import static org.junit.Assert.*;

import mdom.attributes.AttributeFactory;
import mdom.nodes.Element;
import mdom.nodes.NodeFactory;
import mdom.nodes.Node_m;
import mdom.nodes.TextNode;
import mdom.tags.TagFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class DOMTest {

    @Test
    public void testGetElementById(){
        Mdom mdom = Mdom.fromFile("GetElementById.html");
        Element element = mdom.getElementById("p1");
        TextNode textNode = element.getInnerHTML();
        assertEquals("Hello world!", textNode.toString());

        element = mdom.getElementById("p2");
        textNode = element.getInnerHTML();
        assertEquals("Hello world!",textNode.toString());
    }

    @Test
    public void testSetElement(){
        Mdom mdom = Mdom.fromFile("SetElement.html");
        String id = "main paragraph";
        Element element = mdom.getElementById(id);
        TextNode textNode = element.getInnerHTML();
        assertEquals("This is main paragraph!",textNode.toString());

        String newStr = "test pass!";
        textNode.setText(newStr);
        assertEquals(newStr, mdom.getElementById(id).getInnerHTML().toString());
    }

    @Test
    public void testAddElement(){
        Mdom mdom = Mdom.fromFile("AddElement.html");
        List<Element> elements = mdom.getElementsByTagName("body");
        assertEquals(1, elements.size());

        Element body = elements.get(0);
        Node_m text0 = NodeFactory.nodeMaker("paragraph");
        Node_m p0 = NodeFactory.nodeMaker(TagFactory.tagMaker("p"));
        NodeFactory.addChild(p0, text0);
        NodeFactory.addChild(body, p0);

        Node_m text1 = NodeFactory.nodeMaker("paragraph1");
        Node_m p1 = NodeFactory.nodeMaker(TagFactory.tagMaker("p"), AttributeFactory.attributeMaker("id","1"));
        NodeFactory.addChild(p1, text1);

        Node_m div = NodeFactory.nodeMaker(TagFactory.tagMaker("div"), AttributeFactory.attributeMaker("class","hide"));
        NodeFactory.addChild(div, p1);

        NodeFactory.addChild(body, div);

        assertTrue(htmlEquals(mdom.printHtml(), fileToStr("testAddElement.html")));
    }

    @Test
    public void testRemoveElement(){
        Mdom mdom = Mdom.fromFile("RemoveElement.html");
        Element p1 = mdom.getElementById("1");
        NodeFactory.removeChild(p1.getParent(), p1);

        assertTrue(htmlEquals(mdom.printHtml(), fileToStr("testRemoveElement.html")));
    }

    private Boolean htmlEquals(String target, String src){
        target = target.strip();
        src = src.strip();

        target = target.replace(" ","");
        target = target.replace("\r\n","");
        src = src.replace(" ", "");
        src = src.replace("\r\n","");
        return src.contentEquals(target);
    }

    private String fileToStr(String file){
        try {
            InputStream inputStream = DOMTest.class.getClassLoader().getResource(file).openStream();
            String str = readText(inputStream);
            inputStream.close();
            return str;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String readText(InputStream inputStream)throws IOException {
        String res = "";
        int character = 0;
        while((character = inputStream.read()) != -1){
            res += (char)character;
        }
        return res;
    }
}
