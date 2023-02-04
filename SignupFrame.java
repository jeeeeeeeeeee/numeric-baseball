import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupFrame extends JFrame implements ActionListener{
	
	private JTextField id;
	private JPasswordField pwd1, pwd2;
	private JButton signup, show, close;

	public SignupFrame() {
		
		new LoginManager();
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		cp.add(new JLabel("[ 회원가입 ]"), BorderLayout.NORTH);
		
		JPanel input = new JPanel(new GridLayout(3, 2));
		id = new JTextField(); pwd1 = new JPasswordField(); pwd2 = new JPasswordField();
		input.add(new JLabel("이름: ")); input.add(id);
		input.add(new JLabel("패스워드: ")); input.add(pwd1);
		input.add(new JLabel("패스워드 재입력: ")); input.add(pwd2);
		cp.add(input, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel(new FlowLayout());
		signup = new JButton("회원가입"); show = new JButton("패스워드 보기"); close = new JButton("Close");
		signup.addActionListener(this); show.addActionListener(this); close.addActionListener(this);
		buttons.add(signup); buttons.add(show); buttons.add(close);
		cp.add(buttons, BorderLayout.SOUTH);
		
		setTitle("SignUp");
		setSize(300,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == signup) {
			if(id.getText().equals("") || new String(pwd1.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "아이디/패스워드가 비어있을 수는 없습니다");
				return;
			}
			
			if(!new String(pwd1.getPassword()).equals(new String(pwd2.getPassword()))) {
				JOptionPane.showMessageDialog(null, "비밀번호가 동일하지 않습니다");
			}
			else {
				if(LoginManager._signup(id.getText(), new String(pwd1.getPassword()))) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다, 로그인해주세요.");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디거나 지원하지 않는 형식의 아이디입니다.");
				}
			}
		}
		else if(e.getSource()==close) dispose();
		else {
			if(pwd1.getEchoChar() != (char)0) {
				pwd1.setEchoChar((char)0);
			}
			else pwd1.setEchoChar('•');
		}
	}
	
}