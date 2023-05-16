package Framework.Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {
    /**This class reads data from any Json File using Json Parser.*/

    private static Object jsonData;

    public static void readFilePath(String filePath) {
        /**
         * Reads JSON data from a file specified by the file path and assigns it to the class variable jsonData.
         * @param filePath The path of the JSON file to be read
         */

        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(new FileReader(filePath));
            // Check if the parsed data is a valid JSON object or array, and assign it to jsonData
            if (obj instanceof JSONObject || obj instanceof JSONArray) {
                jsonData = obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(String key) {
        /**Returns the value of the given key from the parsed JSON object*/

        if (jsonData instanceof JSONObject) {
            return ((JSONObject) jsonData).get(key);
        }
        return null;
    }

    public static List<JSONObject> getJsonObjectList() {
        /**
         * Returns a list of JSONObjects extracted from the given jsonData object.
         * If the jsonData object is a single JSONObject, it adds it to the jsonObjectList.
         * If the jsonData object is a JSONArray, it iterates through the array and adds each JSONObject to the jsonObjectList.
         * @return A List of JSONObjects extracted from the jsonData object.
         */

        List<JSONObject> jsonObjectList = new ArrayList<>();

        if (jsonData instanceof JSONObject) {
            jsonObjectList.add((JSONObject) jsonData);
        } else if (jsonData instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) jsonData;
            for (Object obj : jsonArray) {
                if (obj instanceof JSONObject) {
                    jsonObjectList.add((JSONObject) obj);
                }
            }
        }
        return jsonObjectList;
    }

    public static JSONArray getJsonArray(String key) {
        /**
         * This method gets the JSONArray corresponding to the given key from the JSON data.
         * @param key The key of the JSONArray to be retrieved.
         * @return The JSONArray object corresponding to the given key, or null if the key is not found or the value is not a JSONArray.
        */

        Object value = getValue(key);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        return null;
    }
}
