import java.awt.event.*;
import javax.swing.*;

public class Number extends JButton implements ActionListener {

	private GamePanel panel;
	private int num;
	
	public Number(GamePanel p, String i) {
		super(i);
		panel = p;
		num = Integer.parseInt(i);
		addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		panel.num(num);
	}
	
}