package Framework.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringsGenerator {

    public static String generateRandomName(int minLength, int maxLength){
        String name = RandomStringUtils.randomAlphabetic(minLength, maxLength);
        LoggerUtil.info("Randomly generated Name: " + name);
        return name;
    }
}
