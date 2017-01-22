package jp.hkawabata.log4j;

import org.apache.log4j.*;

/**
 * Apache Log4J のトライアル
 *
 * Created by kawabatahiroto on 2017/01/22.
 */
public class Log4jTrial {
    public static void main(String[] args) {
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%-4r [%t] %-5p %c %x - %m%n");

        ConsoleAppender appender = new ConsoleAppender();
        appender.setLayout(layout);
        appender.setTarget(ConsoleAppender.SYSTEM_OUT);
        appender.activateOptions();

        Logger logger = Logger.getLogger(Log4jTrial.class);
        // WARN 以上なら出力する
        logger.setLevel(Level.WARN);
        logger.addAppender(appender);

        logger.debug("not displayed");
        logger.info("not displayed");
        logger.warn("displayed");
        logger.error("displayed");
        logger.fatal("displayed");
    }
}
