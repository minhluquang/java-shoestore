	package GUI;
	
	import java.awt.BorderLayout;
	import java.awt.CardLayout;
	import java.awt.Color;
	import java.awt.Cursor;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	
	import javax.swing.JButton;
	import javax.swing.JCheckBox;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;
	import javax.swing.border.EmptyBorder;
	import javax.swing.border.LineBorder;
	
	import BUS.DangKyBUS;
	import BUS.NhanVienBUS;
	import BUS.TaiKhoanBUS;
	import DTO.NhanVien;
	import DTO.TaiKhoan;
	
	public class ChiTietDangKyGUI extends JPanel {
	
		private static final long serialVersionUID = 1L;
		private JPasswordField txtEmail;
		private JTextField txtFullNameRegister;
		private JTextField txtEmailRegister;
		private JTextField txtSdtRegister;
		private CardLayout cardLayout;
		private JPanel pnlCards;
		
		  
		public void setCardLayout(CardLayout cardLayout) {
	        this.cardLayout = cardLayout;
	    }
		
		public void setPnlCards(JPanel pnlCards) {
	        this.pnlCards = pnlCards;
	    }
		
	
		/**
		 * Create the panel.
		 */
		public ChiTietDangKyGUI() {
			 int width = 380;
		        int height = 500;
		        setPreferredSize(new Dimension(380, 600));
		        setBackground(new Color(255, 255, 255));
		        setBorder(new EmptyBorder(5, 5, 5, 5));
		        setLayout(new BorderLayout());
		        
		        JPanel pnlTop = new JPanel();
		        pnlTop.setBackground(new Color(255, 255, 255));
		        add(pnlTop, BorderLayout.NORTH);
		        pnlTop.setLayout(new GridLayout(0, 1, 0, 10));
		        
		        JPanel pnlTitle = new JPanel();
		        pnlTitle.setBackground(new Color(255, 255, 255));
		        pnlTop.add(pnlTitle);
		        pnlTitle.setLayout(new GridLayout(0, 1, 0, 0));
		        
		        JLabel spacing = new JLabel("");
		        pnlTitle.add(spacing);
		        
		        JLabel lnlDangNhap = new JLabel("Thông tin tài khoản");
		        lnlDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		        lnlDangNhap.setFont(new Font("Tahoma", Font.BOLD, 18));
		        pnlTitle.add(lnlDangNhap);
		        
		        JPanel pnlTitle_1 = new JPanel();
		        pnlTitle_1.setBackground(Color.WHITE);
		        pnlTop.add(pnlTitle_1);
		        pnlTitle_1.setLayout(new GridLayout(0, 1, 0, 0));
		        
		        JLabel lblHyNhpThng = new JLabel("Chỉ cần một vài điều nhanh chóng");
		        lblHyNhpThng.setHorizontalAlignment(SwingConstants.CENTER);
		        lblHyNhpThng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		        pnlTitle_1.add(lblHyNhpThng);
		        
		        JLabel lblngNhp = new JLabel("để bắt đầu");
		        lblngNhp.setHorizontalAlignment(SwingConstants.CENTER);
		        lblngNhp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		        pnlTitle_1.add(lblngNhp);
		        
		        JPanel pnlCenter = new JPanel();
		        pnlCenter.setBackground(new Color(255, 255, 255));
		        add(pnlCenter, BorderLayout.CENTER);
		        pnlCenter.setLayout(new BorderLayout(0, 0));
		        
		        JPanel pnlInput = new JPanel();
		        pnlInput.setBackground(new Color(255, 255, 255));
		        pnlCenter.add(pnlInput, BorderLayout.NORTH);
		        pnlInput.setLayout(new GridLayout(0, 1, 0, 5));
		        
		        JLabel txtFullName = new JLabel("Họ và tên ");
		        txtFullName.setFont(new Font("Tahoma", Font.BOLD, 12));
		        pnlInput.add(txtFullName);
		        
		        txtFullNameRegister = new JTextField();
		        txtFullNameRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		        txtFullNameRegister.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		        txtFullNameRegister.setPreferredSize(new Dimension(7, 30));
		        pnlInput.add(txtFullNameRegister);
		        txtFullNameRegister.setColumns(10);
		        
		        JLabel txtEmail = new JLabel("Email");
		        txtEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		        pnlInput.add(txtEmail);
		        
		        txtEmailRegister = new JTextField();
		        txtEmailRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		        txtEmailRegister.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		        txtEmailRegister.setPreferredSize(new Dimension(7, 30));
		        txtEmailRegister.setColumns(10);
		        pnlInput.add(txtEmailRegister);
		        
		        JLabel txtSdt = new JLabel("Số điện thoại");
		        txtSdt.setFont(new Font("Tahoma", Font.BOLD, 12));
		        pnlInput.add(txtSdt);
		        
		        txtSdtRegister = new JTextField();
		        txtSdtRegister.setPreferredSize(new Dimension(7, 30));
		        txtSdtRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		        txtSdtRegister.setColumns(10);
		        txtSdtRegister.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		        pnlInput.add(txtSdtRegister);
		        
		        JPanel pnlService = new JPanel();
		        pnlService.setBackground(new Color(255, 255, 255));
		        pnlCenter.add(pnlService, BorderLayout.SOUTH);
		        pnlService.setLayout(new GridLayout(0, 1, 0, 5));
		        
		        JPanel panelSubService = new JPanel();
		        panelSubService.setBackground(new Color(255, 255, 255));
		        pnlService.add(panelSubService);
		        panelSubService.setLayout(new GridLayout(0, 1, 0, 0));
		        
		        JCheckBox chckbxNewCheckBox = new JCheckBox("Tôi đồng ý với những điều khoản");
		        chckbxNewCheckBox.setSelected(true);
		        chckbxNewCheckBox.setPreferredSize(new Dimension(300, 23));
		        chckbxNewCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		        chckbxNewCheckBox.setFocusable(false);
		        chckbxNewCheckBox.setBackground(Color.WHITE);
		        panelSubService.add(chckbxNewCheckBox);
		        
		        JButton btnXacNhan = new JButton("Xác nhận");
		        btnXacNhan.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		String fullName = txtFullNameRegister.getText();
		        		String email = txtEmailRegister.getText();
		        		String sdt = txtSdtRegister.getText();
		        		
		        		if (!fullName.equals("") && !email.equals("") && !sdt.equals("")) {
		        			int dk = JOptionPane.showConfirmDialog(null,"Bạn đã xác nhận lại thông tin", "Confirm", JOptionPane.YES_NO_OPTION );
		            		if(dk != JOptionPane.YES_OPTION) {
		            			return;
		            		}
		            		String regexPhoneNumber = "^0\\d{9}$";
		        			String regexEmail = "^[a-zA-Z0-9.-_]+@[a-zA-Z0.9.-_]+\\.[a-zA-Z]{2,}$";
		        			String regexFullname = "^[a-zA-Z]+(\\s[a-zA-Z]+)+$";
		        			
		            		NhanVien nv = new NhanVien();
		            		DangKyGUI dangKyGUI = new DangKyGUI();
		            		int accountId = dangKyGUI.getAccountId()-1;
		            		nv.getTaiKhoan().setAccountId(accountId);
		            		
		            		if (!sdt.matches(regexPhoneNumber)) {
								JOptionPane.showMessageDialog(null, "- Số điện thoại bao gồm 10 số và bắt đầu bằng '0' \n- Ví dụ : (033125639)");
								return;
							} else {
								nv.setPhone_number(sdt);
							}
		            		
		            		if (!fullName.matches(regexFullname)) {
								JOptionPane.showMessageDialog(null, "- Họ tên không được có dấu \n- Ví dụ : (Nguyen Van A) ");
								return;
							} else {
								nv.setFull_name(fullName);
							}
		            		
		            		if (!email.matches(regexEmail)) {
								JOptionPane.showMessageDialog(null, "- email không hợp lệ \n- Ví dụ :(example@gmail.com)");
								return;
							} else {
								nv.setEmail(email);
							}
		            			
		            		
		            		
		            		nv.setStaffId(NhanVienBUS.generateIdNhanVien(true));
		            		nv.setStaffStatus(1);
		            		DangKyBUS.registerDetail(nv);
		            		JOptionPane.showMessageDialog(null, "Đăng Ký thành công");
		            		cardLayout.show(pnlCards,"pnlDangNhap");
						} else {
							JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
						}
		        	}
		        });
		        btnXacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        btnXacNhan.setBorder(null);
		        btnXacNhan.setBackground(new Color(51, 51, 51));
		        btnXacNhan.setForeground(new Color(255, 255, 255));
		        btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 12));
		        btnXacNhan.setPreferredSize(new Dimension(200, 30));
		        pnlService.add(btnXacNhan);
		    }
	
	
		
	
	
	
	
	
	
	
		
	
		
		
	
	}
