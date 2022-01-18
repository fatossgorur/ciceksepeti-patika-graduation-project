package helper;

import org.json.JSONObject;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class HelperMethods {


    public static JSONObject readJsonFile(String jsonFileName){
        URL jsonFile = Resources.getResource(jsonFileName+".json");
        String json = null;
        try {
            json = Resources.toString(jsonFile, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(json);
        Log4j.info("Read json file: " + jsonObject);
        return jsonObject;
    }


}
