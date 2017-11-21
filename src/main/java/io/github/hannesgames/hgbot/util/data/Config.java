package io.github.hannesgames.hgbot.util.data;

import java.io.*;
import java.util.Properties;

public class Config {
    static File f = new File("config.HG");
    static Properties prop = new Properties();
    static InputStream input = null;
    static OutputStream output = null;

    private static boolean checkFile(File f) {
        if (f != null) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.err.println("Error  creating " + f.toString());
            }
            if (f.isFile() && f.canWrite() && f.canRead())
                return true;
        }
        return false;
    }

    public static String getMulti() {
        if (checkFile(f)) {
            try {
                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("multi");

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return prop.getProperty("multi");
    }

    public static String getTwitchAccount() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("twitchAccount");
//
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return prop.getProperty("twitchAccount");
    }

    public static String getMitDabei() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("mitDabei");

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return prop.getProperty("mitDabei");
    }
}
