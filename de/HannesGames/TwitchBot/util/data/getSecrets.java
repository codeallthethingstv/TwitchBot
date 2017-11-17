package de.HannesGames.TwitchBot.util.data;

import java.io.*;
import java.util.Properties;

public class getSecrets {
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
    public static void setConfig() {
        try {
            output = new FileOutputStream(f);

            // set the properties value
            prop.setProperty("clientID ", setSecrets.clientID());
            prop.setProperty("authKey", setSecrets.authKey());
            prop.setProperty("username", setSecrets.username());

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void getConfig() {
          setConfig();
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

    public static String getClientID() {
        setConfig();
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
        setConfig();
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
        setConfig();
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
}
