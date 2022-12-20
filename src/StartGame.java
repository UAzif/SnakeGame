import javax.swing.*;

public class StartGame {

    JFrame startFrame = new JFrame("Змейка");
  StartGame(){
      JFrame.setDefaultLookAndFeelDecorated(true);
      startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      startFrame.setBounds(500,250,500,515);
      GamePanel gamePanel= new GamePanel();
      startFrame.add(gamePanel);
      startFrame.setVisible(true);
  }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
       StartGame startGame= new StartGame();
    }
}
