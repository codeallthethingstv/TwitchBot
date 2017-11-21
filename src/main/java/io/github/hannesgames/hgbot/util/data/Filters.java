package io.github.hannesgames.hgbot.util.data;

import java.io.*;
import java.util.Properties;

public class Filters {
    static File f = new File("filters.HG");
    static Properties prop = new Properties();
    static InputStream input = null;
    static OutputStream output = null;

    private static boolean checkFile(File f) {
        if (f != null) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.err.println("Error  creating  " + f.toString());
            }
            if (f.isFile() && f.canWrite() && f.canRead())
                return true;
        }
        return false;
    }

    public static int getFilterCaps() {
        if (checkFile(f)) {
            try {
                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("FilterCaps");

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
        return Integer.parseInt(prop.getProperty("FilterCaps"));
    }

    public static int getFilterEmotes() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("FilterEmotes");
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
        return Integer.parseInt(prop.getProperty("FilterEmotes"));
    }

    public static int getFilterLinks() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("getFilterLinks");

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
        return Integer.parseInt(prop.getProperty("getFilterLinks"));
    }

    public static int getFilterBlacklistedWords() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("getFilterBlacklistedWords");

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
        return Integer.parseInt(prop.getProperty("getFilterBlacklistedWords"));
    }

    public static int getFilters() {
        int links = getFilterLinks();
        int emotes = getFilterEmotes();
        int blacklistedWords = getFilterBlacklistedWords();
        int caps = getFilterCaps();
        return links + emotes + blacklistedWords + caps;
    }
}
