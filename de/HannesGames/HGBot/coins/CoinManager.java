package de.HannesGames.HGBot.coins;

import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;

import java.io.*;
import java.util.HashMap;

public class CoinManager {
    private final String PATH = "coins.map";
    private HashMap<String, Double> coins;
    private Channel channel;
    private boolean run = true;

    public CoinManager(Channel channel) {
        coins = new HashMap<>();
        this.channel = channel;
    }

    public void setCoins(User user, Double coins) {
        this.coins.put(user + "", coins);
    }

    /**
     * Use this to startup CoinManager
     */
    public void startUp() {
        loadCoinsfromFile();
        new Thread(runnable).start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (run) {
                if (!channel.isLive()) {
                    System.out.println("Test!");
                    for (User user : channel.getViewers()) {
                        System.out.println(user + "");

                        setCoins(user, coins.get(user + "") + 0.1);
                    }
                }
                writeCoinsToFile();
                try {
                    Thread.sleep(40000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * Loads from File
     */
    private void loadCoinsfromFile() {
        File file = new File(PATH);
        try {
            if (!file.exists()) file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                coins.put(line.split(";")[0], Double.parseDouble(line.split(";")[1]));
            }
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This Method just writes Coins to file.
     */
    private void writeCoinsToFile() {
        File file = new File(PATH);
        try {
            if (!file.exists()) file.delete();
            assert file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String s : coins.keySet()) {
                writer.write(s + ";" + coins.get(s) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Double getCoinsForUser(User user) {
        return coins.get(user + "");
    }
}
