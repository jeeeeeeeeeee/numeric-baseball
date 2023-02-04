import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoardFrame extends JFrame {
   
   private GamePanel panel1, panel2;
   private int state;
   public CardLayout card;
   private NumberBaseball nb;
   private String player1, player2;
   public Container cp;
   
   public GameBoardFrame(NumberBaseball b, String _1, String _2, int diff, int num) {
      nb = b;
      player1 = _1;
      player2 = _2;
      state = 1;
      card = new CardLayout();
      
      cp = getContentPane();
      cp.setLayout(card);
      cp.setBackground(new Color(204, 255, 204));
      
      panel1 = new GamePanel(nb, player1, diff, this, num); panel2 = new GamePanel(nb, player2, diff, this, num);
      cp.add("P1", panel1); cp.add("P2", panel2);
      
      panel1.requestFocus();
      panel1.setFocusable(true);
      
      setTitle("숫자야구");
      setSize(500,300);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }
   public String getName1() {return player1;}
   public String getName2() {return player2;}
   
   public void swap() {
      this.card.next(cp);
      if(state == 1) {
         panel2.requestFocus();
         panel2.setFocusable(true);
         state = 2;
      }
      else {
         panel1.requestFocus();
         panel1.setFocusable(true);
         state = 1;
      }
   }
}