package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChiTietQuyenGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietQuyenGUI frame = new ChiTietQuyenGUI();
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
	public ChiTietQuyenGUI() {
		int width = 800;
		int height = 300;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(36, 136, 203));
		pnlCenter.add(panel);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblDanhMcQuyn = new JLabel("Danh mục quyền");
		lblDanhMcQuyn.setForeground(new Color(255, 255, 255));
		lblDanhMcQuyn.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblDanhMcQuyn);
		
		JLabel lblXem = new JLabel("Xem");
		lblXem.setForeground(new Color(255, 255, 255));
		lblXem.setHorizontalAlignment(SwingConstants.CENTER);
		lblXem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblXem);
		
		JLabel lblThm = new JLabel("Thêm");
		lblThm.setForeground(new Color(255, 255, 255));
		lblThm.setHorizontalAlignment(SwingConstants.CENTER);
		lblThm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblThm);
		
		JLabel lblCpNht = new JLabel("Cập nhật");
		lblCpNht.setForeground(new Color(255, 255, 255));
		lblCpNht.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpNht.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCpNht);
		
		JLabel lblXo = new JLabel("Xoá");
		lblXo.setForeground(new Color(255, 255, 255));
		lblXo.setHorizontalAlignment(SwingConstants.CENTER);
		lblXo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblXo);
		
		JPanel pnlQLSP = new JPanel();
		pnlQLSP.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLSP);
		pnlQLSP.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLSn = new JLabel("Quản lý sản phẩm");
		lblQunLSn.setBackground(new Color(255, 255, 255));
		lblQunLSn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLSP.add(lblQunLSn);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLSP.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLSP.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("");
		chckbxNewCheckBox_2.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLSP.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("");
		chckbxNewCheckBox_3.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLSP.add(chckbxNewCheckBox_3);
		
		JPanel pnlQLNV = new JPanel();
		pnlQLNV.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLNV);
		pnlQLNV.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNhn = new JLabel("Quản lý nhân viên");
		lblQunLNhn.setBackground(new Color(255, 255, 255));
		lblQunLNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNV.add(lblQunLNhn);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("");
		chckbxNewCheckBox_4.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNV.add(chckbxNewCheckBox_4);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNV.add(chckbxNewCheckBox_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNV.add(chckbxNewCheckBox_2_1);
		
		JCheckBox chckbxNewCheckBox_3_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNV.add(chckbxNewCheckBox_3_1);
		
		JPanel pnlQLNCC = new JPanel();
		pnlQLNCC.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLNCC);
		pnlQLNCC.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNh = new JLabel("Quản lý nhà cung cấp");
		lblQunLNh.setBackground(new Color(255, 255, 255));
		lblQunLNh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNCC.add(lblQunLNh);
		
		JCheckBox chckbxNewCheckBox_4_1 = new JCheckBox("");
		chckbxNewCheckBox_4_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNCC.add(chckbxNewCheckBox_4_1);
		
		JCheckBox chckbxNewCheckBox_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNCC.add(chckbxNewCheckBox_1_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNCC.add(chckbxNewCheckBox_2_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNCC.add(chckbxNewCheckBox_3_1_1);
		
		JPanel pnlQLTK = new JPanel();
		pnlQLTK.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLTK);
		pnlQLTK.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNh_1 = new JLabel("Quản lý tài khoản");
		lblQunLNh_1.setBackground(new Color(255, 255, 255));
		lblQunLNh_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLTK.add(lblQunLNh_1);
		
		JCheckBox chckbxNewCheckBox_4_1_1 = new JCheckBox("");
		chckbxNewCheckBox_4_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLTK.add(chckbxNewCheckBox_4_1_1);
		
		JCheckBox chckbxNewCheckBox_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLTK.add(chckbxNewCheckBox_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLTK.add(chckbxNewCheckBox_2_1_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLTK.add(chckbxNewCheckBox_3_1_1_1);
		
		JPanel pnlQLNH = new JPanel();
		pnlQLNH.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLNH);
		pnlQLNH.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNh_1_1 = new JLabel("Quản lý nhập hàng");
		lblQunLNh_1_1.setBackground(new Color(255, 255, 255));
		lblQunLNh_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNH.add(lblQunLNh_1_1);
		
		JCheckBox chckbxNewCheckBox_4_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_4_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNH.add(chckbxNewCheckBox_4_1_1_1);
		
		JCheckBox chckbxNewCheckBox_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNH.add(chckbxNewCheckBox_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNH.add(chckbxNewCheckBox_2_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLNH.add(chckbxNewCheckBox_3_1_1_1_1);
		
		JPanel pnlQLXH = new JPanel();
		pnlQLXH.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLXH);
		pnlQLXH.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNh_1_1_1 = new JLabel("Quản lý xuất hàng");
		lblQunLNh_1_1_1.setBackground(new Color(255, 255, 255));
		lblQunLNh_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLXH.add(lblQunLNh_1_1_1);
		
		JCheckBox chckbxNewCheckBox_4_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_4_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLXH.add(chckbxNewCheckBox_4_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLXH.add(chckbxNewCheckBox_1_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLXH.add(chckbxNewCheckBox_2_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLXH.add(chckbxNewCheckBox_3_1_1_1_1_1);
		
		JPanel pnlQLPQ = new JPanel();
		pnlQLPQ.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLPQ);
		pnlQLPQ.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNh_1_1_1_1 = new JLabel("Quản lý phân quyền");
		lblQunLNh_1_1_1_1.setBackground(new Color(255, 255, 255));
		lblQunLNh_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLPQ.add(lblQunLNh_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_4_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_4_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLPQ.add(chckbxNewCheckBox_4_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_1_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLPQ.add(chckbxNewCheckBox_1_1_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLPQ.add(chckbxNewCheckBox_2_1_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLPQ.add(chckbxNewCheckBox_3_1_1_1_1_1_1);
		
		JPanel pnlQLKK = new JPanel();
		pnlQLKK.setBackground(new Color(255, 255, 255));
		pnlCenter.add(pnlQLKK);
		pnlQLKK.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblQunLNh_1_1_1_1_1 = new JLabel("Quản lý thống kê");
		lblQunLNh_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		lblQunLNh_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLKK.add(lblQunLNh_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_4_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_4_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_4_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_4_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLKK.add(chckbxNewCheckBox_4_1_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_1_1_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLKK.add(chckbxNewCheckBox_1_1_1_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_2_1_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_2_1_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_2_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_2_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLKK.add(chckbxNewCheckBox_2_1_1_1_1_1_1_1);
		
		JCheckBox chckbxNewCheckBox_3_1_1_1_1_1_1_1 = new JCheckBox("");
		chckbxNewCheckBox_3_1_1_1_1_1_1_1.setBackground(new Color(255, 255, 255));
		chckbxNewCheckBox_3_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_3_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlQLKK.add(chckbxNewCheckBox_3_1_1_1_1_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		contentPane.add(panel_1_1, BorderLayout.EAST);
		
		JPanel pnlBtns = new JPanel();
		pnlBtns.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlBtns, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(null);
		btnNewButton.setPreferredSize(new Dimension(100, 30));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(21, 155, 71));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlBtns.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Huỷ bỏ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setPreferredSize(new Dimension(100, 30));
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(new Color(220, 53, 69));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlBtns.add(btnNewButton_1);
	}

}
