package DataLayer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ConfigFileManager extends JSONManager{
    protected HashMap<String, String> properties = new HashMap<String, String>();

    public ConfigFileManager(String folderPath){
        super(folderPath);
        fileSuffix = ".cfg";
    }

    public void loadProperties(String fileName){
        JSONObject root = new JSONObject(getFileContents(fileName));
        JSONArray propArr = root.getJSONArray("properties");
        for(int i = 0; i < propArr.length(); i++){
            JSONObject property = propArr.getJSONObject(i);
            properties.put(property.getString("name"), property.getString("value"));
        }
    }

    public String getProperty(String property){
        return properties.getOrDefault(property, null);
    }
}
