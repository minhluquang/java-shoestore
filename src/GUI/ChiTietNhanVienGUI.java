package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.JButton;

public class ChiTietNhanVienGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietNhanVienGUI frame = new ChiTietNhanVienGUI();
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
	public ChiTietNhanVienGUI() {
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết nhân viên không?", "Xác nhận đóng chi tiết nhân viên", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
    		}
    	});
		
		int width = 380;
        int height = 600;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin nhân viên");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlRight = new JPanel();
		pnlRight.setPreferredSize(new Dimension(200, 10));
		pnlRight.setBorder(null);
		pnlRight.setBackground(Color.WHITE);
		contentPane.add(pnlRight);
		pnlRight.setLayout(new BorderLayout(0, 10));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pnlRight.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin nhân viên");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.setBackground(new Color(36, 136, 203));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		panel.add(lblNewLabel_5_1);
		
		JPanel panel_3 = new JPanel();
		pnlRight.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblNewLabel_6_2 = new JLabel("Mã nhân viên");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		panel_5.add(textField_3);
		
		JLabel lblNewLabel_6 = new JLabel("Họ và tên");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		panel_5.add(textField);
		
		JLabel lblNewLabel_6_1 = new JLabel("Giới tính");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(Color.WHITE);
		panel_5.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbtnNam);
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNam.setFocusable(false);
		rdbtnNam.setBackground(Color.WHITE);
		panel_4_1.add(rdbtnNam);
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(rdbtnNu);
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNu.setFocusable(false);
		rdbtnNu.setBackground(Color.WHITE);
		panel_4_1.add(rdbtnNu);
		
//		JLabel lblNewLabel_6_2 = new JLabel("Chức vụ");
//		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
//		panel_5.add(lblNewLabel_6_2);
//		
//		JComboBox cmbChucVu = new JComboBox();
//		cmbChucVu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		cmbChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Admin"}));
//		cmbChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		cmbChucVu.setFocusable(false);
//		panel_5.add(cmbChucVu);
		
		JLabel lblNewLabel_6_3 = new JLabel("Số điện thoại");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);
		
		textField_1 = new JTextField();
		textField_1.setPreferredSize(new Dimension(100, 19));
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		panel_5.add(textField_1);
		
		JLabel lblNewLabel_6_3_1 = new JLabel("Email");
		lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1);
		
		textField_2 = new JTextField();
		textField_2.setPreferredSize(new Dimension(100, 19));
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		panel_5.add(textField_2);
		
		JLabel lblNewLabel_6_3_1_1 = new JLabel("Trạng thái");
		lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Hoạt động", "Ngưng hoạt động"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_1.setFocusable(false);
		panel_5.add(comboBox_1);
		
//		JLabel lblNewLabel_6_2_1 = new JLabel("Trạng thái");
//		lblNewLabel_6_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
//		panel_5.add(lblNewLabel_6_2_1);
//		
//		JComboBox cmbTrangThai = new JComboBox();
//		cmbTrangThai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Hoạt động", "Ngưng hoạt động"}));
//		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		cmbTrangThai.setFocusable(false);
//		panel_5.add(cmbTrangThai);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));
		
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setPreferredSize(new Dimension(100, 30));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(21, 155, 71));
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Huỷ bỏ");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(220, 53, 69));
		panel_2.add(btnNewButton_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_3.add(panel_6, BorderLayout.EAST);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBackground(Color.WHITE);
		panel_3.add(panel_6_1, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		pnlRight.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel);
	}

}
