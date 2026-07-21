package main;


import javax.swing.JFrame;

class Main{
    public static void main(String[] args){
        JFrame window = new JFrame("My Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        // Create game panel and attach it to the window
        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        panel.startGame();
    }
}