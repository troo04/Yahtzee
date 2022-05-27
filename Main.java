import javax.swing.*;
import java.awt.*;

class Main {
  //This method instantiates a Name object
  private static void runGUI() {
    JFrame.setDefaultLookAndFeelDecorated(true);

    Game game = new Game();
  }
  //This is the main method that calls the runGUI and invokes the GUI.
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        runGUI();
      }
    });
  }
}