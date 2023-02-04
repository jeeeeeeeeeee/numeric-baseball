import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Manual extends JFrame implements ActionListener{

	private Image background = new ImageIcon(MenuFrame.class.getResource("/image/manual.png")).getImage();

	public Manual() {
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		JPanel p = new JPanel();
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
		
		setLocationRelativeTo(null);
		setTitle("How to play");
		setSize(450,310);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
	}
	
	public void paint(Graphics g) {
		Image changeImg = background.getScaledInstance(450, 320, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		g.drawImage(changeImg,  0,  0, null);
	}
	
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	

}
