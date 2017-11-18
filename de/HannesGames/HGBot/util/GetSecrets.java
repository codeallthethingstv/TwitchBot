package de.HannesGames.HGBot.util;

import java.io.*;
import java.util.Properties;

public class GetSecrets {
    static File f = new File("secure.HG");
    static Properties prop = new Properties();
    static InputStream input = null;
    static OutputStream output = null;

    private static boolean checkFile(File f) {
        if (f != null) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating " + f.toString());
            }
            if (f.isFile() && f.canWrite() && f.canRead())
                return true;
        }
        return false;
    }

    public static String getClientID() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("clientID");

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
        return prop.getProperty("clientID");
    }

    public static String getAuthKey() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("authKey");
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
        return prop.getProperty("authKey");
    }

    public static String getUsername() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty("username");

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
        return prop.getProperty("username");
    }

    public void getConfig() {
        if (checkFile(f)) {
            try {

                input = new FileInputStream(f);

                // load a properties file
                prop.load(input);

                // get the property value and print it out

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
    }
}
