package Framework.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    private LoggerUtil(){}

    public static void info(String msg){
        logger.info(msg);
    }

    public static void warn(String msg){
        logger.warn(msg);
    }
}