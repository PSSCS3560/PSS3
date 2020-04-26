import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Wrld!" );
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("Name", "Jason");
//        jsonObject.put("Name1", "Alex");
//        jsonObject.put("Name19", "ZEDD");
//        JSONArray keys = jsonObject.names();
//
//        for(int i = keys.length() -1; i >= 0; i--) {
//            String keyName = keys.getString(i);
//            System.out.println(jsonObject.get(keyName));
//        }

        PSS pss = new PSS("Set1.json");
        pss.scanFromJSONFile();
        pss.edit();
    }
}
