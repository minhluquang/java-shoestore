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

import BUS.ReturnBUS;
import BUS.RoleBUS;
import DTO.Return;
import java.util.regex.*;

public class ChiTietBaoHanhGUI extends JFrame {
	 private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JTextField txtIdBaoHanh;
	    private JTextField txtidProduct;
	    private JTextField txtDateReturn;
	    private JTextField txtReason;
//	    private JTextField txtActive;
	    private JComboBox cmbTrangThai;
	    private Return rt;
	    private ReturnGUI parentGUI;
	    
	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	    	EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	                    ChiTietBaoHanhGUI frame = new ChiTietBaoHanhGUI(new Return(), new ReturnGUI());
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
	    public ChiTietBaoHanhGUI(Return rt, ReturnGUI parentGUI) {
	    	this.rt = rt;
	    	this.parentGUI = parentGUI;
	    	
	    	 addWindowListener(new WindowAdapter() {
	             @Override
	             public void windowClosing(WindowEvent e) {
	                 int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết đổi trả không?", "Xác nhận đóng chi tiết đổi trả", JOptionPane.YES_NO_OPTION);
	                 if (choice == JOptionPane.YES_OPTION) {
	                     dispose();
	                 }
	             }
	         });
	         int width = 600;
	         int height = 500;
	         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	         setBounds(100, 100, width, height);
	         setLocationRelativeTo(null);
	         setTitle("Thông tin phiếu đổi trả");
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
	         
	         JLabel lblNewLabel_4 = new JLabel("Thông tin đổi trả");
	         lblNewLabel_4.setForeground(new Color(255, 255, 255));
	         lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
	         lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
	         panel.setBackground(new Color(36, 136, 203));
	         panel.add(lblNewLabel_4);
	         
	        
	         
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
	         
	         
	         
	         JLabel lblNewLabel_6_2 = new JLabel("Return_ID");
	         lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	         panel_5.add(lblNewLabel_6_2);
	         txtIdBaoHanh = new JTextField();
	         txtIdBaoHanh.setEnabled(false);
	         txtIdBaoHanh.setEditable(false);
	         txtIdBaoHanh.setFont(new Font("Tahoma", Font.PLAIN, 14));
	         txtIdBaoHanh.setColumns(10);
	         panel_5.add(txtIdBaoHanh);
	         
	         JLabel lblNewLabel_6 = new JLabel("Product_Serial_ID");
	         lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
	         panel_5.add(lblNewLabel_6);

	         txtidProduct = new JTextField();
	         txtidProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
	         panel_5.add(txtidProduct);
	    	
	         JLabel lblNewLabel_5 = new JLabel("Date_Return");
	         panel_5.add(lblNewLabel_5);
	         txtDateReturn= new JTextField();
	         txtDateReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
	         txtDateReturn.setColumns(10);
	         panel_5.add(txtDateReturn);
	         
	         JLabel lblNewLabel_5_1 = new JLabel("Reason");
	         panel_5.add(lblNewLabel_5_1);
	         txtReason =  new JTextField();
	         txtReason.setFont(new Font("Tahoma", Font.PLAIN, 14));
	         txtReason.setColumns(10);
	         panel_5.add(txtReason);
	         
//	         JLabel lblNewLabel_5_2 = new JLabel("Active");
//	         panel_5.add(lblNewLabel_5_2);
//	         txtActive = new JTextField();
//	         txtActive.setFont(new Font("Tahoma", Font.PLAIN, 14));
//	         txtActive.setColumns(10);
//	         panel_5.add(txtActive);

	         
	         
	         JLabel lblNewLabel_5_1_2 = new JLabel("Status");
	         lblNewLabel_5_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	 		 panel_5.add(lblNewLabel_5_1_2);
	         
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
	         
	         // ========= Xử lý lưu thông tin  =========
	         JButton btnNewButton = new JButton("Lưu");
	         btnNewButton.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				xuLyLuuThongTinBaoHanh();
	 			}
	 		});
	         // ========= Xử lý lưu thông tin =========
	         
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
	                 int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn huỷ bỏ chỉnh sửa chi tiết đổi trả không?", "Xác nhận huỷ bỏ chỉnh sửa chi tiết đổi trả", JOptionPane.YES_NO_OPTION);
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
	         
	         if(rt!=null) {
	        	 xuLyTuDongGanGiaTri();
	         }
	         
	    }
        public void xuLyTuDongGanGiaTri() {
       	 int return_id = rt.getReturn_id();
       	 if(return_id == 0) {
       		 txtIdBaoHanh.setText(Integer.toString(ReturnBUS.generateIdReturn(true)));
       	 } else {
       		 txtIdBaoHanh.setText(Integer.toString(rt.getReturn_id()));
       	 }
       	txtidProduct.setText(Integer.toString(rt.getProduct_serial_id()));
       	txtidProduct.setEditable(true);
       	txtDateReturn.setText(rt.getDate_return());
       	txtDateReturn.setEditable(true);
       	txtReason.setText(rt.getReason());
       	txtReason.setEditable(true);
//       	txtActive.setText(rt.getActive());
//       	txtActive.setEditable(true);
       }
        // luu
        public void xuLyLuuThongTinBaoHanh() {
            int return_id = Integer.parseInt(txtIdBaoHanh.getText());
            int product_serial_id;
            String date_return = txtDateReturn.getText();
            String reason = txtReason.getText();  
//            String active = txtActive.getText();
            int status = Integer.parseInt(cmbTrangThai.getSelectedItem().toString());
            
            // Kiểm tra định dạng ngày tháng
            Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher dateMatcher = datePattern.matcher(date_return);
            if (!dateMatcher.matches()) {
                JOptionPane.showMessageDialog(null, "Date_Return phải có định dạng yyyy--mm--dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Tách năm, tháng và ngày từ chuỗi ngày tháng
            String[] dateParts = date_return.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);          
            if (month < 1 || month > 12 || day < 1 || day > 31) {
                JOptionPane.showMessageDialog(null, "Ngày hoặc tháng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Kiểm tra giá trị ban đầu của product_id và xử lý lưu thông tin
            if (txtidProduct.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Product_id không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                product_serial_id = Integer.parseInt(txtidProduct.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Product_id phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (product_serial_id <= 0) {
                JOptionPane.showMessageDialog(null, "Product_id phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra giá trị của reason
            if (reason.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập lý do", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Thực hiện lưu hoặc cập nhật thông tin
            if (!ReturnBUS.isExistReturn(return_id)) {
                if (ReturnBUS.insertReturn(return_id, product_serial_id, date_return, reason,"OK", status)) {
                    JOptionPane.showMessageDialog(null, "Lưu thông tin đổi trả thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    parentGUI.loadDanhSachBaoHanh();
                    parentGUI.revalidate();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Lưu thông tin đổi trả thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (ReturnBUS.updateReturn(return_id, product_serial_id, date_return, reason,"OK", status)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin đổi trả thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    parentGUI.loadDanhSachBaoHanh();
                    parentGUI.revalidate();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thông tin đổi trả thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
}
