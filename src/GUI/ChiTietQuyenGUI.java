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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.border.LineBorder;

import BUS.ChiTietQuyenBUS;
import BUS.QuyenBUS;
import DAO.QuyenDAO;
import DTO.Quyen;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ChiTietQuyenGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlCenter;
	
	private ArrayList<Integer> listCheckboxSelected = new ArrayList<>();
	private int accountId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ChiTietQuyenGUI frame = new ChiTietQuyenGUI(0);
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
	public ChiTietQuyenGUI(int id) {
		this.accountId = id;
		
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết quyền không?", "Xác nhận đóng chi tiết quyền", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
    		}
    	});
		
		int width = 400;
		int height = 300;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Chi tiết quyền nhân viên");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(36, 136, 203));
		pnlCenter.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDanhMcQuyn = new JLabel("Danh mục quyền");
		lblDanhMcQuyn.setPreferredSize(new Dimension(80, 20));
		lblDanhMcQuyn.setForeground(new Color(255, 255, 255));
		lblDanhMcQuyn.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblDanhMcQuyn);
		
		// Load danh sách quyền
		loadDanhSachQuyen();
		
		JLabel lblXo = new JLabel("");
		lblXo.setForeground(new Color(255, 255, 255));
		lblXo.setHorizontalAlignment(SwingConstants.CENTER);
		lblXo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblXo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		contentPane.add(panel_1_1, BorderLayout.EAST);
		
		JPanel pnlBtns = new JPanel();
		pnlBtns.setBackground(new Color(255, 255, 255));
		contentPane.add(pnlBtns, BorderLayout.SOUTH);
		
		// ========= Xử lý lưu quyền tài khoản =========
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Xoá hết quyền rồi thêm lại quyền mới
				ChiTietQuyenBUS.deleteTatCaQuyenCuaTaiKhoanBangId(accountId);
				
				boolean success = true;
				for (int roleId : listCheckboxSelected) {
					if (!ChiTietQuyenBUS.insertQuyenVaoTaiKhoan(roleId, accountId)) {
						success = false;
					}
				}
				if (success == false) {
					JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại quyền của tài khoản", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công quyền của tài khoản", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		// ========= Xử lý lưu quyền tài khoản =========
		
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
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa quyền tài khoản không?", "Xác nhận huỷ bỏ chỉnh sửa quyền tài khoản", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
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

	public void loadDanhSachQuyen() {
		ArrayList<Quyen> dsq = QuyenBUS.getDanhSachQuyen();
		
		// Gán quyền của tài khoản cho listCheckboxSelected
		listCheckboxSelected = ChiTietQuyenBUS.getDanhSachQuyenCuaTaiKhoanBangId(accountId);
		
		for (Quyen q : dsq) {
			JLabel label = new JLabel(q.getRoleName());
			label.setBackground(new Color(255, 255, 255));
			label.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			JCheckBox checkBox = new JCheckBox();
			checkBox.setBackground(new Color(255, 255, 255));
	        checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        // Kiểm tra xem q.getRoleId() có trong listCheckboxSelected hay không
	        if (listCheckboxSelected.contains(q.getRoleId())) {
	        	checkBox.setSelected(true);
	        }
	        
	        checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (checkBox.isSelected()) {
						listCheckboxSelected.add(q.getRoleId());
					} else {
						listCheckboxSelected.remove(Integer.valueOf(q.getRoleId()));
					}
				}
			});
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 255, 255));
	        panel.setLayout(new GridLayout(0, 2, 0, 0));
	        
	        panel.add(label);
	        panel.add(checkBox);
	        
	        pnlCenter.add(panel);
		}
	}
}


// Component: label + checkbox
//JLabel label = new JLabel(q.getRoleName());
//label.setBackground(new Color(255, 255, 255));
//label.setFont(new Font("Tahoma", Font.PLAIN, 14));
//
//JCheckBox checkBox = new JCheckBox();
//checkBox.setBackground(new Color(255, 255, 255));
//checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
//checkBox.setHorizontalAlignment(SwingConstants.CENTER);
//
//checkBox.addActionListener(new ActionListener() {
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (checkBox.isSelected()) {
//			listCheckboxSelected.add(q.getRoleId());
//		} else {
//			listCheckboxSelected.remove(Integer.valueOf(q.getRoleId()));
//		}
//	}
//});
//
//JPanel panel = new JPanel();
//panel.setBackground(new Color(255, 255, 255));
//panel.setLayout(new GridLayout(0, 2, 0, 0));
//
//panel.add(label);
//panel.add(checkBox);
//
//pnlCenter.add(panel);








