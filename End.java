import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class End extends JFrame implements ActionListener{
   
   private JButton close, re, set;
   private GamePanel panel;
   private GameBoardFrame frame;
   private FileManager f_m;
   private String n1, n2;
   private String counter;
   
   public End(int i, GamePanel pp, GameBoardFrame f) { // i==1이면 승리 0이면 패배
      panel = pp;
      f_m = new FileManager();
      frame = f;
      n1 = frame.getName1();
      n2 = frame.getName2();
      
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.setBackground(new Color(204, 255, 204));

      JPanel p = new JPanel();
      p.setBackground(new Color(204, 255, 204));
      Toolkit tk = Toolkit.getDefaultToolkit();
       Image cursorImage = tk.getImage("src/image/ball.png");
       Point point = new Point(20, 20);
       Image ball = cursorImage.getScaledInstance(45, 55, Image.SCALE_SMOOTH);
       Cursor cursor = tk.createCustomCursor(ball, point, ""); 
       cp.add(p, BorderLayout.CENTER);    
       p.setCursor(cursor);
       // 정답
       JPanel o = new JPanel(new GridLayout(3,1));
       int[] s = new int[panel.getDiff()];
       s = panel.qb;
       StringBuilder sb = new StringBuilder();
      for(int j=0; j<panel.getDiff(); j++) {
         sb.append(s[j]);
      }
      JLabel sol = new JLabel("정답: "+sb.toString());
      sol.setFont(new Font("SansSerif", Font.BOLD, 15));
      o.add(sol); o.add(new JLabel());
      
       if(i==1) {
          f_m.writeResult(panel.getName(), i);
          counter = whoCounter(panel.getName());
          if(panel.getName().equals(n1)) {f_m.writeResult(n2, 0);}
          JLabel _a = new JLabel(panel.getName()+" Win!");
          _a.setFont(new Font("SansSerif", Font.BOLD, 15));
          JLabel _aa;
          if(panel.getName().equals(n1)) {_aa = new JLabel(n2+" Lose!");}
          else {_aa = new JLabel(n1+" Lose!");}
          _aa.setFont(new Font("SansSerif", Font.BOLD, 15));
           _a.setForeground(new Color(050,000,000)); if(!n2.equals("")) {_aa.setForeground(new Color(050,000,000));}
          o.add(_a); if(!n2.equals("")) {o.add(_aa);} else {o.add(new JLabel());}
       }
       else if (i==0){
          f_m.writeResult(panel.getName(), i);
          counter = whoCounter(panel.getName());
          if(panel.getName().equals(n1)) {f_m.writeResult(n2, 1);}
          JLabel _a = new JLabel(panel.getName()+" Lose.");
          _a.setFont(new Font("SansSerif", Font.BOLD, 15));
          JLabel _aa;
          if(panel.getName().equals(n1)) {_aa = new JLabel(n2+" Win!");}
          else {_aa = new JLabel(n1+" Win!");}
          _aa.setFont(new Font("SansSerif", Font.BOLD, 15));
           _a.setForeground(new Color(050,000,000)); if(!n2.equals("")) {_aa.setForeground(new Color(050,000,000));}
          o.add(_a); if(!n2.equals("")) {o.add(_aa);} else {o.add(new JLabel());}
       }
       else { // 둘다 졌을 때
          f_m.writeResult(n1, 0); f_m.writeResult(n2, 0);
          counter = n1;
          JLabel _a = new JLabel(n1 +" and "+ n2 +" Lose.");
          _a.setFont(new Font("SansSerif", Font.BOLD, 15));
          JLabel _b = new JLabel("That's okay.");
          _b.setFont(new Font("SansSerif", Font.BOLD, 15));
           _a.setForeground(new Color(050,000,000)); _b.setForeground(new Color(050,000,000));
          o.add(_a); o.add(_b);
          
       }
       // 기록 보여주기
       JLabel r1 = new JLabel(panel.getName()+" 님의 기록: "+f_m.getRecord(panel.getName(), "승")+" 승"+f_m.getRecord(panel.getName(), "패")+" 패");
       r1.setFont(new Font("SansSerif", Font.BOLD, 15));r1.setForeground(new Color(050,000,000));o.add(r1);
       if(!n2.equals("")) {
          JLabel r2 = new JLabel("| "+counter+" 님의 기록: "+f_m.getRecord(counter, "승")+" 승"+f_m.getRecord(counter, "패")+" 패");
          r2.setFont(new Font("SansSerif", Font.BOLD, 15));r2.setForeground(new Color(050,000,000));o.add(r2);
       }
       cp.add(o, BorderLayout.CENTER);
       
       JPanel j = new JPanel(new GridLayout());
      close = new JButton("Close"); re = new JButton("Retry"); set = new JButton("Main");
      close.addActionListener(this); re.addActionListener(this); set.addActionListener(this);
      j.add(set); j.add(re); j.add(close);
      cp.add(j, BorderLayout.SOUTH);
      
      
      setLocationRelativeTo(null);
      setTitle("Good Play!");
      setSize(500,250);
      setVisible(true);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
   }

   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==close) System.exit(0);
      else if(e.getSource()==re) {
         new GameBoardFrame(new NumberBaseball(panel.getDiff()), frame.getName1(), frame.getName2(), panel.getDiff(), panel.getNumber());
         dispose();
      }
      else new StartFrame(); // main으로 돌아가기
      dispose();
   }
   
   public String whoCounter(String name) {
      if(name.equals(n1)) return n2;
      else return n1;
   }

}