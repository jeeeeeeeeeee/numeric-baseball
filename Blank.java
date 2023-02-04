import java.awt.event.*;
import javax.swing.*;

public class Blank extends JButton implements ActionListener{
	
	private GamePanel panel;
	public int index;
	
	public Blank(GamePanel p, int i) {
		panel = p;
		index = i;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		panel.select(index);
	}
	
}