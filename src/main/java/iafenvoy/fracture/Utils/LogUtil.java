package iafenvoy.fracture.Utils;

import iafenvoy.fracture.Fracture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    public static final Logger LOGGER = LogManager.getLogger(Fracture.MOD_NAME);

    public static void info(String msg) {
        LOGGER.info("[Fracture] " + msg);
    }

    public static void debug(String msg) {
        LOGGER.debug("[Fracture] " + msg);
    }

    public static void error(String msg) {
        LOGGER.error("[Fracture] " + msg);
    }

    public static void warn(String msg) {
        LOGGER.warn("[Fracture] " + msg);
    }

    public static void trace(String msg) {
        LOGGER.trace("[Fracture] " + msg);
    }

    public static void fatal(String msg) {
        LOGGER.fatal("[Fracture] " + msg);
    }
}
