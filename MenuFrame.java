import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame implements ActionListener{
	
	private JButton _1player, _2player;
	
	public MenuFrame() {
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(1, 2, 5, 5));
		cp.setBackground(new Color(204, 255, 204));
		
		_1player = new JButton("1인용"); _2player = new JButton("2인용");
		_1player.setFont(new Font("나눔고딕",Font.PLAIN,20)); _2player.setFont(new Font("나눔고딕",Font.PLAIN,20));
		_1player.addActionListener(this); _2player.addActionListener(this);
		
		cp.add(_1player); cp.add(_2player);
		
		setLocationRelativeTo(null);
		setTitle("Solo? or Together?");
		setSize(300,150);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == _1player) new SettingFrame(1);
		else new SettingFrame(2);
		dispose();
	}
}