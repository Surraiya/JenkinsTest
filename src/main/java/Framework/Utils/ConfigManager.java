package Framework.Utils;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private static final String CONFIG_FILE_PATH = "src/main/resources/ConfigData.json";
    private static final String BROWSER_KEY = "browser";
    private static final String URL_KEY = "url";
    private static final String TIMEUNIT_KEY = "timeUnit";
    private static final String OPTIONS_KEY = "options";
    private static String browser;
    private static String url;
    private static int timeUnit;
    private static JSONArray optionsArray;
    private static List<String> options = new ArrayList<>();

    static {
        loadConfiguration();
    }

    private ConfigManager() {}

    private static void loadConfiguration() {
        JsonFileReader.readFilePath(CONFIG_FILE_PATH);
        browser = (String) JsonFileReader.getValue(BROWSER_KEY);
        url = (String) JsonFileReader.getValue(URL_KEY);
        Long timeUnitLong = (Long) JsonFileReader.getValue(TIMEUNIT_KEY);
        timeUnit = timeUnitLong.intValue();
        optionsArray = JsonFileReader.getJsonArray(OPTIONS_KEY);
        if (optionsArray != null) {
            optionsArray.forEach(option -> options.add((String) option));
        }
    }

    public static String getBrowser() {
        return browser;
    }
    public static String getBrowserFromSystemProperty() {
        return System.getProperty("browser");
    }

    public static String getUrl() {
        return url;
    }

    public static int getTimeUnit() {
        return timeUnit;
    }

    public static List<String> getOptions(){
        return options;
    }
}
