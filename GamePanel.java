import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener{

   
   private JPanel solution, nums;
   private JScrollPane history;
   private JTextArea history_text;
   private int difficulty, total;
   
   private Blank[] sol;
   private Number[] num;
   private JButton submit;
   
   private String name;
   private JLabel info;
   private int cnt=1, index, select_num=-1, playernum;
   private int[] s;
   private NumberBaseball nb;
   private GameBoardFrame gbf;
   public int[] qb;
   
   public GamePanel(NumberBaseball b, String n, int diff, GameBoardFrame f, int pn) {
      super(null);
      gbf = f;
      nb = b;
      name = n;
      playernum = pn;
      total = 10;
      info = new JLabel(name + " 님의 노트" + " | 현재 1번째 시도입니다");
      info.setBounds(0, 0, 500, 30);
      difficulty = diff;
      s = new int[difficulty];
      qb = new int[difficulty]; qb = nb.getQuestion();
      
      for(int i=0; i<difficulty; i++) {
         s[i] = -1;
      }
      this.add(info);
      
      solution = new JPanel(new GridLayout(1, 5));
      solution.setBounds(0,30, 150+50*(diff-3), 50);
      sol = new Blank[5];
      for(int i = 0; i < difficulty; i++) {
         sol[i] = new Blank(this, i);
         if(i < difficulty) {
            sol[i].addActionListener(this);
            solution.add(sol[i]);
         }
         else {
            solution.add(sol[i]);
            sol[i].setEnabled(false);
         }
      }
      history_text = new JTextArea();
      history_text.setFont(new Font("", Font.BOLD, 14));
      history_text.setAutoscrolls(true);
      history_text.setEditable(false);
      history = new JScrollPane(history_text);
      history.setBounds(0, 80, 500, 140);
      
      this.add(solution);
      this.add(history);
      
      nums = new JPanel(new GridLayout(1, 10, 0, 0));
      num = new Number[10];
      for(int i = 0; i < 10; i++) {
         num[i] = new Number(this, Integer.toString(i));
         num[i].addActionListener(this);
         nums.add(num[i]);
      }
      submit = new JButton("제출");
      submit.addActionListener(this);
      
      submit.setBounds(400, 40, 70, 30);
      nums.setBounds(0, 220, 485, 40);
      this.add(submit);
      this.add(nums);
      
      addKeyListener(new KeyListener() {
      
      @Override
      public void keyTyped(KeyEvent e) {
         // TODO Auto-generated method stub
         
      }
      
      @Override
      public void keyReleased(KeyEvent e) {
         // TODO Auto-generated method stub
         
      }
      
      @Override
      public void keyPressed(KeyEvent e) {
         // TODO Auto-generated method stub
         if(e.getKeyCode() == KeyEvent.VK_ENTER) submit();
         else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            select((index+difficulty-1)%difficulty);
         }
         else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            select((index+1)%difficulty);
         }
         else if(e.getKeyChar() <= '9' && e.getKeyChar() >= '0') {
            keypress_num(e.getKeyChar()-'0');
         }
      }
   });
   }
   
   public void select(int i) {
     try{
        sol[index].setEnabled(true);
     }
     catch(NullPointerException e){}
      index = i;
      sol[index].setEnabled(false);
   }
   
   public void num(int n) {
      select_num = n;
      sol[index].setText(Integer.toString(select_num));
      s[index] = select_num;
      select((index+1)%difficulty);
      select_num = -1;
   }
   
   public void keypress_num(int n) {
      select_num = n;
      sol[index].setText(Integer.toString(select_num));
      s[index] = select_num;
      select_num = -1;
   }
   
   public boolean check(int[] ss) {
      for(int i=0; i<difficulty; i++) {
         if(ss[i]==-1)
            return false;
      }
      return true;
   }
   
   public void submit() {
      nb.question();
      if(!check(s)) {
           JOptionPane.showMessageDialog(null, "모든 숫자를 선택해 주세요.");
           return;
        }
      else if(checkEqual(s)) {
         JOptionPane.showMessageDialog(null, "서로 다른 숫자를 선택해 주세요.", "알림", JOptionPane.WARNING_MESSAGE);
         return;
      }
        else if(nb.check(s)[0] == difficulty) {
           new End(1, this, gbf); 
           gbf.dispose();
        }
        else {
           total -= 1;
           StringBuilder sb = new StringBuilder();
           for(int i=0; i<difficulty; i++) {
              sb.append(s[i]);
           }
           history_text.append("["+ sb.toString() +"] | "+ nb.check(s)[0]+ " Strike   " + nb.check(s)[1] +" Ball   " +nb.check(s)[2] +" Out\n");
           for(int i=0; i<difficulty; i++) {
              s[i] = -1;
              sol[i].setText("");
           }
           cnt++;
           info.setText(name + " 님의 노트" + " | 현재 " + cnt +"번째 시도입니다");
           select_num = -1;
        }
        if(playernum==2 && total >= 0) {
           if(nb.one==1) {
              new End(2, this, gbf); 
              gbf.dispose();
           }
           else if(total == 0)
              nb.one = 1;
           gbf.swap();         
        }
        if(playernum==1 && total ==0) {
           new End(0, this, gbf);
           gbf.dispose();
        }
   }
   
   public void actionPerformed(ActionEvent e) {
     this.requestFocus();
     if(e.getSource()==submit) {
         submit();
      }
   }
   
   public int getDiff() {
      return difficulty;
   }
   public String getName() {
      return name;
   }
   public int getNumber() {
      return playernum;
   }
   public boolean checkEqual(int[] n) {
      int[] nn = new int[difficulty];
      for(int i=0; i<difficulty; i++) nn[i] = -1;
      
      for(int i=0; i<difficulty; i++) {
         for(int j=0; j<difficulty; j++) {
            if(nn[j] == n[i]) return true;
            
         }
         nn[i] = n[i];
      }
      return false;
   }
}