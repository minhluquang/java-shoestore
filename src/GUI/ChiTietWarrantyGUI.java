package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import BUS.KhuyenMaiBUS;
import BUS.WarrantyBUS;
import DAO.KhuyenmaiDAO;
import DAO.WarrantyDAO;
import DTO.Warranty;

public class ChiTietWarrantyGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtWarrantyId;
    private JTextField txtProductId;
    private JTextField txtStartDate;
    private JTextField txtEndDate;
    private JTextField txtWarrantyDate;
    private JTextField txtReason;
    private JTextField txtWarrantyStatus;
    private JComboBox cmbTrangThai;
    private Warranty wt;
    private WarrantyGUI parentGUI;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietWarrantyGUI frame = new ChiTietWarrantyGUI(new Warranty(), new WarrantyGUI());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ChiTietWarrantyGUI(Warranty wt, WarrantyGUI parentGUI) {
    	this.wt = wt;
    	this.parentGUI = parentGUI;
    	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết bảo hành không?", "Xác nhận đóng chi tiết bảo hành", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        int width = 400;
        int height = 600;
    	
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Thông tin bảo hành");
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
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin bảo hành");
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
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 5));
		
		JLabel lblNewLabel_6_2 = new JLabel("WarrantyID");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_2);
		
		txtWarrantyId = new JTextField();
		txtWarrantyId.setEnabled(false);
		txtWarrantyId.setEditable(false);
		txtWarrantyId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtWarrantyId.setColumns(10);
		panel_5.add(txtWarrantyId);
		
		JLabel lblNewLabel_6 = new JLabel("ProductID");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6);
		
		txtProductId = new JTextField();
		txtProductId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProductId.setColumns(10);
		panel_5.add(txtProductId);
		
		JLabel lblNewLabel_6_3 = new JLabel("Start Date");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3);
		
		txtStartDate = new JTextField();
		txtStartDate.setPreferredSize(new Dimension(100, 19));
		txtStartDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStartDate.setColumns(10);
		panel_5.add(txtStartDate);
		
		
		JLabel lblNewLabel_6_3_1 = new JLabel("End Date");
		lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1);
		
		txtEndDate = new JTextField();
		txtEndDate.setPreferredSize(new Dimension(100, 19));
		txtEndDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEndDate.setColumns(10);
		panel_5.add(txtEndDate);
		
		JLabel lblNewLabel_6_3_1_1 = new JLabel("Reason");
		lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_1);
		
		txtReason = new JTextField();
		txtReason.setPreferredSize(new Dimension(100, 19));
		txtReason.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtReason.setColumns(10);
		panel_5.add(txtReason);
		
		JLabel lblNewLabel_6_3_1_2 = new JLabel("Warranty Date");
		lblNewLabel_6_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_2);
	
		txtWarrantyDate = new JTextField();
		txtWarrantyDate.setPreferredSize(new Dimension(100, 19));
		txtWarrantyDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtWarrantyDate.setColumns(10);
		panel_5.add(txtWarrantyDate);
		
		JLabel lblNewLabel_6_3_1_3 = new JLabel("Status");
		lblNewLabel_6_3_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_5.add(lblNewLabel_6_3_1_3);
		
		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] {"1", "0"}));
		cmbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbTrangThai.setFocusable(false);
		panel_5.add(cmbTrangThai);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		panel_5.add(lblNewLabel_7_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_5.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));
		
		
		
		// ========= Xử lý lưu  =========
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTinBaoHanh();
			}
		});
		// ========= Xử lý lưu =========
		
		
		
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setPreferredSize(new Dimension(100, 30));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(21, 155, 71));
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Huỷ bỏ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết bảo hành không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết bảo hành", JOptionPane.YES_NO_OPTION);
    	        if (choice == JOptionPane.YES_OPTION) {
    	            dispose();
    	        }
			}
		});
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
        
        if(wt != null) {
        	xuLyTuDongGanGiaTri();
        }
    }
    public void xuLyTuDongGanGiaTri() {
    	int warrantyId = wt.getWarrantyid();
    	if(warrantyId == 0) {
    		txtWarrantyId.setText(Integer.toString(WarrantyBUS.generateIdWar(true)));
    	} else {
    		txtWarrantyId.setText(Integer.toString(wt.getWarrantyid()));
    	}
    	txtProductId.setText(Integer.toString(wt.getProduct_serial_id()));
    	txtProductId.setEditable(true);
    	txtStartDate.setText(wt.getStartDate());
    	txtStartDate.setEditable(true);
    	txtEndDate.setText(wt.getEndDate());
    	txtEndDate.setEditable(true);
    	txtWarrantyDate.setText(wt.getWarrantyDate());
    	txtWarrantyDate.setEditable(true);
    	txtReason.setText(wt.getReason());
    	txtReason.setEditable(true);
    }
    // btnLuu
    public void xuLyLuuThongTinBaoHanh() {
    	int warranty_id = Integer.parseInt(txtWarrantyId.getText());
    	int product_id = Integer.parseInt(txtProductId.getText().trim());
        String startdate = txtStartDate.getText().trim();
        String enddate = txtEndDate.getText().trim();
        String wardate = txtWarrantyDate.getText().trim();
        String reason = txtReason.getText().trim();
        int warstatus = Integer.parseInt(cmbTrangThai.getSelectedItem().toString());
        if(startdate.isEmpty() || enddate.isEmpty() || wardate.isEmpty() || reason.isEmpty()) {
    		 String message = "Vui lòng nhập đầy đủ các trường:";
 	        message += "\n - Ngày bắt đầu";
 	        message += "\n - Ngày kết thúc";
 	        message += "\n - Ngày bảo hành";
 	        message += "\n - Lý do ";
 	       JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    	} else {
    		boolean isExistWarId = WarrantyBUS.isExitWar(warranty_id);
    		 if (!isExistWarId) {
	        	   if (WarrantyBUS.insertWar(warranty_id, product_id, startdate, enddate, wardate, reason,warstatus)) {
		                JOptionPane.showMessageDialog(null, "Hệ thống thêm thành công thông tin bảo hành", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
		                parentGUI.loadDanhSachWarranty();
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Hệ thống thêm thất bại thông tin bảo hành", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
		            }
	        } else {
	            if(WarrantyBUS.updateWar(warranty_id, product_id, startdate, enddate, wardate, reason,warstatus) ) {
	            	 JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thành công thông tin bảo hành", "Thông báo thành công", JOptionPane.INFORMATION_MESSAGE);
	            	  parentGUI.loadDanhSachWarranty();
		                dispose();
	            }
	            else {
	            	 JOptionPane.showMessageDialog(null, "Hệ thống cập nhật thất bại thông tin khuyến mãi", "Thông báo thất bại", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
    	}
    }
}
