import mdom.Mdom;
import mdom.nodes.Element;


public class Builder {
    public static void main(String[] strings){
        //Mdom mdom = Mdom.fromFile("index0.html");
        Mdom mdom = Mdom.fromFile("index0.html");
        Element element = mdom.getElementById("1");
    }
}
