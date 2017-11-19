package de.HannesGames.HGBot.util.data;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {
    java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getName());
    private FileHandler fh;

    public Logger() {
        try {
            setLog("log");
            fh = new FileHandler("D:/java/TwitchBot/logs/logs.html");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public void setLog(String log) {
        logger.info(log);
    }
}
