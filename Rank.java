import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;

public class Rank extends JFrame implements ActionListener{

   public Rank() {
      
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.setBackground(new Color(204, 255, 204));

      JPanel p = new JPanel(new GridLayout(10, 1));
      Toolkit tk = Toolkit.getDefaultToolkit();
       Image cursorImage = tk.getImage("src/image/ball.png");
       Point point = new Point(20, 20);
       Image ball = cursorImage.getScaledInstance(45, 55, Image.SCALE_SMOOTH);
       Cursor cursor = tk.createCustomCursor(ball, point, ""); 
       cp.add(p, BorderLayout.CENTER);    
       p.setCursor(cursor);

       JPanel j = new JPanel(new FlowLayout());
      JButton close = new JButton("Close");
      close.addActionListener(this);
      j.add(close);
      cp.add(j, BorderLayout.SOUTH);
      
      JSONArray ja = sortRank();
      JPanel[] rank = new JPanel[10];
      for(int i = 0; i < 10; i++) {
         try {
            rank[i] = new JPanel(new GridLayout(1, 3, 10, 5));
            rank[i].setBackground(new Color(204, 255, 205));
            JSONObject jo = (JSONObject)ja.get(i);
            String name = (String) jo.keySet().iterator().next();
            jo = (JSONObject)jo.get(jo.keySet().iterator().next());
            Long win = (Long) jo.get("승");
            Long lose = (Long) jo.get("패");
            JLabel nameLabel = new JLabel(name);
            JLabel winLabel = new JLabel("승: " + win);
            JLabel loseLabel = new JLabel("패: " + lose);
            nameLabel.setFont(new Font("나눔고딕", Font.BOLD, 11));
            winLabel.setFont(new Font("나눔고딕", Font.PLAIN, 10));
            loseLabel.setFont(new Font("나눔고딕", Font.PLAIN, 10));
            rank[i].add(nameLabel);
            rank[i].add(winLabel);
            rank[i].add(loseLabel);
            p.add(rank[i]);
         } catch (IndexOutOfBoundsException e) {
            // TODO Auto-generated catch block
            break;
         }
      }
      
      rank[0].setBackground(new Color(255, 153, 153));
      rank[1].setBackground(new Color(255, 204, 153));
      rank[2].setBackground(new Color(255, 255, 204));
      
      setLocationRelativeTo(null);
      setTitle("Rank");
      setSize(450,310);
      setVisible(true);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      
   }
   
   public JSONArray sortRank() {
      
      JSONArray sortedArr = new JSONArray();
      
      List<JSONObject> jsonValues = new ArrayList<JSONObject>();
      FileManager fm = new FileManager();
      JSONArray arr = fm.getArray();
      
      for(int i = 0; i < arr.size(); i++) {
         jsonValues.add((JSONObject)arr.get(i));
      }
      
      Collections.sort(jsonValues, new Comparator<JSONObject>() {
         private static final String KEY1 = "승";
         private static final String KEY2 = "패";
         
         public int compare(JSONObject a, JSONObject b) {
            int x;
            
            JSONObject ax = (JSONObject)a.get(a.keySet().iterator().next());
            JSONObject bx = (JSONObject)b.get(b.keySet().iterator().next());
            
            Long valA = (Long)ax.get(KEY1);
            Long valB = (Long)bx.get(KEY1);
            
            Long valAA = (Long)ax.get(KEY2);
            Long valBB = (Long)bx.get(KEY2);
            x = Long.compare(-valA, -valB);
            if(valA.equals(valB)) x = Long.compare(valAA, valBB);
            return x;
         }
      });
      
      for(int i = 0; i < arr.size(); i++) {
         sortedArr.add(jsonValues.get(i));
      }
      
      return sortedArr;
      
   }
   
   
   public void actionPerformed(ActionEvent e) {
      dispose();
   }
   

}