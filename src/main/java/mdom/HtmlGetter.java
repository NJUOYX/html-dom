package mdom;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlGetter{
    /**
     * get string context from a html file
     * @param file the html file ,which should in resources direction
     * @return html format string
     */
    public static String fileGetter(String file) {
        String res = "";
        try {
            InputStream inputStream =  HtmlGetter.class.getClassLoader().getResource(file).openStream();
            res = readFrom(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }

    public static String webGetter(String strUrl) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            InputStream inputStream = conn.getInputStream();
            return readFrom(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if (conn != null) {
                    conn.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String readFrom(InputStream inputStream) throws IOException{
        String res = "";
        int character = 0;
        while((character = inputStream.read()) != -1){
            res += (char)character;
        }
        return res;
    }
}
