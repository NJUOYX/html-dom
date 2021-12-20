package mdom.tags;

import java.util.Locale;

public class TagFactory {
    /**
     *
     * @param tag tag name
     * @return
     */
    public static Tag_m tagMaker(String tag){
        switch (tag.toLowerCase(Locale.ROOT)){
            case "html":return new Tag_document(tag);
            default:return new Tag_m(tag);
        }
    }
}
