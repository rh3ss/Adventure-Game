package main;

import java.io.*;

public class Config {
    private GamePanel gamePanel;

    public Config(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void saveCurrentConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            // full screen
            String fullScreenState = this.gamePanel.fullScreenOn ? "On" : "Off";
            bw.write(fullScreenState);
            bw.newLine();

            bw.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCurrentConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String line = br.readLine();
            // full screen
            this.gamePanel.fullScreenOn = (line.equals("On")) ? true : false;
            br.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
