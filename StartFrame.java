import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartFrame extends JFrame implements ActionListener{
   
   private JButton manual, start, rank, join;
   
   private Image background = new ImageIcon(MenuFrame.class.getResource("/image/start.png")).getImage();
   
   private ImageIcon manual_img = new ImageIcon("src/Button_Image/manual.jpg");
   private ImageIcon start_img = new ImageIcon("src/Button_Image/start.jpg");
   private ImageIcon rank_img = new ImageIcon("src/Button_Image/join.jpg");
   private ImageIcon join_img = new ImageIcon("src/Button_Image/rank.jpg");
   
   public StartFrame() {
      
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      
      JPanel bg = new JPanel(new BorderLayout()) {
         public void paintComponent(Graphics g) {
            Image changeImg = background.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            ImageIcon changeIcon = new ImageIcon(changeImg);
            g.drawImage(changeImg,  0,  0, null);
            setOpaque(false);
            super.paintComponent(g);
         }
      };
      
      cp.add(bg, BorderLayout.CENTER);
      
      Toolkit tk = Toolkit.getDefaultToolkit();
       Image cursorImage = tk.getImage("src/image/ball.png");
       Point point = new Point(20, 20);
       Image ball = cursorImage.getScaledInstance(45, 55, Image.SCALE_SMOOTH);
       Cursor cursor = tk.createCustomCursor(ball, point, ""); 

       cp.setCursor(cursor);
      
      JPanel j = new JPanel(new GridLayout(1, 4, 5, 5));
      manual = new JButton(manual_img); start = new JButton(start_img); rank = new JButton(rank_img); join = new JButton(join_img);
      manual.setFont(new Font("나눔고딕",Font.PLAIN,11)); rank.setFont(new Font("나눔고딕",Font.PLAIN,11)); join.setFont(new Font("나눔고딕",Font.PLAIN,11));
      manual.addActionListener(this); start.addActionListener(this); rank.addActionListener(this); join.addActionListener(this);
      
      manual.setCursor(cursor); start.setCursor(cursor); rank.setCursor(cursor); join.setCursor(cursor);
      manual.setPreferredSize(new Dimension(72,32));
      start.setPreferredSize(new Dimension(72,32));
      rank.setPreferredSize(new Dimension(72,32));
      join.setPreferredSize(new Dimension(72,32));
      manual.setBorderPainted(false); start.setBorderPainted(false);
      rank.setBorderPainted(false); join.setBorderPainted(false);
      j.add(manual); j.add(start); j.add(rank); j.add(join);
   
      bg.add(j, BorderLayout.SOUTH);
      j.setOpaque(false);
      setTitle("___Number Baseball Game___");
      setSize(300,400);
      setResizable(false);
      setLocationRelativeTo(null);
      setVisible(true);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }
   
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == manual) new Manual();
      else if(e.getSource() == start) {
         new MenuFrame();
         dispose();
      }
      else if(e.getSource() == join) new SignupFrame();
      else new Rank();
   }
}