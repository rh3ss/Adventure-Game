package main;


import javax.swing.JFrame;

class Main{
    public static JFrame window;

    public static void main(String[] args){
        window = new JFrame("Legends of Arcadia");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        // Create game panel and attach it to the window
        GamePanel panel = new GamePanel();
        window.add(panel);
        panel.config.loadCurrentConfig();
        if (panel.fullScreenOn) {
            window.setUndecorated(true);
        }
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setupGame();
        panel.startGame();
    }
}