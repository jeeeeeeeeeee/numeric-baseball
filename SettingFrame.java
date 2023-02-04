import javax.swing.*;

import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.lang.System.Logger.Level;
import java.security.MessageDigest;

public class SettingFrame extends JFrame implements ActionListener{

   private JTextField name1, name2;
   private JPasswordField pass1, pass2;
   private JButton easy, normal, hard;
   private JButton start;
   private JButton selected;
   private int playerAmount;
   private int level; //3,4,5
   
   public SettingFrame(int playerNum){
      playerAmount = playerNum;
      LoginManager manager = new LoginManager();
      Container cp = getContentPane();
      cp.setLayout(new GridLayout(3, 1));
      
      JPanel name = new JPanel(new GridLayout(3, 3, 5, 5));
      name.add(new JLabel("")); name.add(new JLabel("ID")); name.add(new JLabel("PWD"));
      
      name1 = new JTextField(); name2 = new JTextField();
      pass1 = new JPasswordField(); pass2 = new JPasswordField();
      
      name.add(new JLabel("1번 플레이어: ")); name.add(name1); name.add(pass1);
      if(playerAmount == 2) {
         name.add(new JLabel("2번 플레이어: ")); name.add(name2); name.add(pass2);
      }
      else {
         name.add(new JLabel()); name.add(new JLabel()); name.add(new JLabel());
      }
      
      JPanel difficulty = new JPanel(new GridLayout(1, 3, 5, 5));
      easy = new JButton("쉬움"); normal = new JButton("보통"); hard = new JButton("어려움");
      easy.addActionListener(this); normal.addActionListener(this); hard.addActionListener(this);
      difficulty.add(easy); difficulty.add(normal); difficulty.add(hard);
      
      start = new JButton("시작"); start.addActionListener(this);
      
      cp.add(name); cp.add(difficulty); cp.add(start);
      setLocationRelativeTo(null);
      setTitle("Setting Window");
      setSize(300,200);
      setVisible(true);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   }
   
   public void actionPerformed(ActionEvent e){
      if(e.getSource() != start) {
         try {
            selected.setEnabled(true);
            selected = (JButton)e.getSource();
            selected.setEnabled(false);
         } catch (NullPointerException e1) {
            // TODO Auto-generated catch block
            selected = (JButton)e.getSource();
            selected.setEnabled(false);
         }
      }
      else {
         int check = 1;
         if(selected == null) {
            JOptionPane.showMessageDialog(null, "난이도를 설정해주세요");
            return;
         }
         
         String level_string = selected.getText();
         level = getLevel(level_string);
         
         if(playerAmount == 1) {
            // 이름, 패스워드 입력 안했을 경우
            String p1 = new String(pass1.getPassword()); 
            if(isBlank(name1.getText())) {
               JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
               check = 0;
            }
            if(isBlank(p1)) {
               JOptionPane.showMessageDialog(null, "패스워드를 입력해주세요");
               check = 0;
            }

            if(check == 0) return;
            
            if(!LoginManager.isExist(name1.getText())) {
               int answer = JOptionPane.showConfirmDialog(null, name1.getText() + " 님의 회원정보가 없습니다. 회원가입을 진행하시겠습니까?", "회원가입", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
               if(answer == 0)LoginManager.signup();
               else System.exit(0);
               
            }
            else if(!LoginManager.isRight(name1.getText(), p1))
               JOptionPane.showMessageDialog(null, "패스워드가 다릅니다.");
            else{
               JOptionPane.showMessageDialog(null,"10번 안에 정답을 맞춰주세요!");
               
               new GameBoardFrame(new NumberBaseball(level), name1.getText(), name2.getText(), level, playerAmount);
               dispose();
               }
         }
         else{
            String p1 = new String(pass1.getPassword()); 
            if(isBlank(name1.getText())) {
               JOptionPane.showMessageDialog(null, "플레이어1의 이름을 입력해주세요");
               check = 0;
            }
            if(isBlank(p1)) {
               JOptionPane.showMessageDialog(null, "플레이어1의 패스워드를 입력해주세요");
               check = 0;
            }
            
            String p2 = new String(pass2.getPassword());
            if(isBlank(name2.getText())) {
               JOptionPane.showMessageDialog(null, "플레이어2의 이름을 입력해주세요");
               check = 0;
            }
            if(isBlank(p2)) {
            	
               JOptionPane.showMessageDialog(null, "플레이어2의 패스워드를 입력해주세요");
               check = 0;
            }
            if(check == 0) return;
            if(!LoginManager.isExist(name1.getText())) {
               String answer = JOptionPane.showInputDialog(name1.getText()+" 님의 정보가 없습니다. 회원가입을 진행하시겠습니까?(y/n)");
               if(answer.equals("y"))LoginManager.signup();
               else System.exit(0);
            }
            else if(!LoginManager.isExist(name2.getText())) {
               String answer = JOptionPane.showInputDialog(name2.getText()+" 님의 정보가 없습니다. 회원가입을 진행하시겠습니까?(y/n)");
               if(answer.equals("y"))LoginManager.signup();
               else System.exit(0);
            }
            else if(!LoginManager.isRight(name1.getText(), p1))
               JOptionPane.showMessageDialog(null, "플레이어1의 패스워드가 다릅니다.");
            else if(!LoginManager.isRight(name2.getText(), p2))
               JOptionPane.showMessageDialog(null, "플레이어2의 패스워드가 다릅니다.");
            else{
               JOptionPane.showMessageDialog(null,"10번 안에 정답을 맞춰주세요!");
               new GameBoardFrame(new NumberBaseball(level), name1.getText(), name2.getText(), level, playerAmount);
               dispose();
            }
         }
      }
   }
   public boolean isBlank(String str) {
      if(str.equals("")) return true;
      else return false;
   }
   public int getLevel(String level) {
      if(level == "쉬움") return 3;
      else if(level == "보통") return 4;
      else return 5;
   }
}