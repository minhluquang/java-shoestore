package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Cursor;

public class DangNhapDangKyGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private CardLayout cardLayout;
	private JPanel pnlCards;
	private JPanel pnlDangNhap;
	private JPanel pnlDangKy;
	private JPanel pnlSwich;
	private JLabel lblSwitchToRegister;
	private JButton btnDangKyNgay;
	private JLabel lblSwitchToLogin;
	private JButton btnDangNhapNgay;
	private JPanel panel;
	private JLabel lblNewLabel;
	
    private String username = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					DangNhapDangKyGUI frame = new DangNhapDangKyGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DangNhapDangKyGUI() {
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng ứng dụng không?", "Xác nhận đóng ứng dụng", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                	System.exit(1);
                }
    		}
    	});
		int width = 380;
	    int height = 500;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 16));
		
		pnlSwich = new JPanel();
		pnlSwich.setVisible(false);
		pnlSwich.setBackground(Color.WHITE);
		contentPane.add(pnlSwich, BorderLayout.SOUTH);
		pnlSwich.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
//		lblSwitchToRegister = new JLabel("Bạn chưa có tài khoản?");
//		lblSwitchToRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		pnlSwich.add(lblSwitchToRegister);
//		
//		btnDangKyNgay = new JButton("Đăng ký ngay!");
//		btnDangKyNgay.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				lblSwitchToLogin.setVisible(false);
//				btnDangKyNgay.setVisible(false);
//				
//				lblSwitchToRegister.setVisible(true);
//				btnDangNhapNgay.setVisible(true);
//				
//				cardLayout.show(pnlCards, "pnlDangKy");
//			}
//		});
//		btnDangKyNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnDangKyNgay.setForeground(new Color(0, 64, 128));
//		btnDangKyNgay.setFont(new Font("Tahoma", Font.ITALIC, 12));
//		btnDangKyNgay.setFocusable(false);
//		btnDangKyNgay.setBorder(null);
//		btnDangKyNgay.setBackground(Color.WHITE);
//		pnlSwich.add(btnDangKyNgay);
//		
//		lblSwitchToLogin = new JLabel("Bạn đã có tài khoản?");
//		lblSwitchToLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		pnlSwich.add(lblSwitchToLogin);
//		
//		btnDangNhapNgay = new JButton("Đăng nhập ngay!");
//		btnDangNhapNgay.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				lblSwitchToLogin.setVisible(false);
//				btnDangKyNgay.setVisible(true);
//				
//				lblSwitchToRegister.setVisible(true);
//				btnDangNhapNgay.setVisible(false);
//				
//				cardLayout.show(pnlCards, "pnlDangNhap");
//			}
//		});
//		btnDangNhapNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnDangNhapNgay.setForeground(new Color(0, 64, 128));
//		btnDangNhapNgay.setFont(new Font("Tahoma", Font.ITALIC, 12));
//		btnDangNhapNgay.setFocusable(false);
//		btnDangNhapNgay.setBorder(null);
//		btnDangNhapNgay.setBackground(Color.WHITE);
//		pnlSwich.add(btnDangNhapNgay);
//		
		pnlCards = new JPanel();
		contentPane.add(pnlCards, BorderLayout.CENTER);
		pnlCards.setLayout(new CardLayout());
		cardLayout = (CardLayout) pnlCards.getLayout();
		
		JPanel pnlDangNhap = new JPanel();
		pnlCards.add(pnlDangNhap, "pnlDangNhap");
		pnlDangNhap.setLayout(new BorderLayout());
		pnlDangNhap.add(new DangNhapGUI(), BorderLayout.CENTER);
		
		JPanel pnlDangKy = new JPanel();
		pnlCards.add(pnlDangKy, "pnlDangKy");
		DangKyGUI dangKyGUI = new DangKyGUI();
		pnlDangKy.setLayout(new BorderLayout());
		pnlDangKy.add(dangKyGUI, BorderLayout.CENTER);
		dangKyGUI.setCardLayout(cardLayout); 
		dangKyGUI.setPnlCards(pnlCards); 


		JPanel pnlChiTietDangKy = new JPanel();
		pnlCards.add(pnlChiTietDangKy, "pnlChiTietDangKy");
		ChiTietDangKyGUI chiTietDangKyGUI = new ChiTietDangKyGUI();
		pnlChiTietDangKy.setLayout(new BorderLayout());
		pnlChiTietDangKy.add(chiTietDangKyGUI, BorderLayout.CENTER);
		chiTietDangKyGUI.setCardLayout(cardLayout);
		chiTietDangKyGUI.setPnlCards(pnlCards);
	}

}
