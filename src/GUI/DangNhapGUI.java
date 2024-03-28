package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class DangNhapGUI {

	static final long serialVersionUID = 1L;
	static JPanel contentPane;
	static JTextField email;
	static JTextField password;

	/**
	 * 
	 * /**
	 * Create the frame.
	 */
	public static JPanel dangNhapGUI() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));

		contentPane.setLayout(null);

		JLabel lbDangNhap = new JLabel("Đăng Nhập");
		lbDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbDangNhap.setBounds(359, 24, 168, 80);
		contentPane.add(lbDangNhap);

		JLabel txtEmail = new JLabel("Email: ");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setBounds(249, 152, 62, 25);
		contentPane.add(txtEmail);

		JLabel txtPassWord = new JLabel("PassWord: ");
		txtPassWord.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassWord.setBounds(249, 225, 106, 36);
		contentPane.add(txtPassWord);

		email = new JTextField();
		email.setBounds(358, 140, 284, 58);
		contentPane.add(email);
		email.setColumns(10);

		password = new JTextField();
		password.setBounds(359, 219, 283, 58);
		contentPane.add(password);
		password.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.DARK_GRAY);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(359, 329, 128, 47);
		contentPane.add(btnLogin);
		return contentPane;
	}
}
