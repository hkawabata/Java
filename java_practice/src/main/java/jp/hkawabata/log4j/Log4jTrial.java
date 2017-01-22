package jp.hkawabata.log4j;

import org.apache.log4j.*;

/**
 * Apache Log4J のトライアル
 *
 * Created by kawabatahiroto on 2017/01/22.
 */
public class Log4jTrial {
    public static void main(String[] args) {
        Layout layout = new PatternLayout("%-4r [%t] %-5p %c %x - %m%n");

        Appender appender = new ConsoleAppender(layout, ConsoleAppender.SYSTEM_OUT);
        appender.setLayout(layout);

        Logger logger = Logger.getLogger(Log4jTrial.class);
        logger.setLevel(Level.WARN);
        logger.addAppender(appender);

        logger.debug("not displayed");
        logger.info("not displayed");
        logger.warn("displayed");
        logger.error("displayed");
        logger.fatal("displayed");
    }
}
