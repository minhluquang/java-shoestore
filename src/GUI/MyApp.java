package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import BUS.QuyenBUS;
import BUS.RoleBUS;
import DTO.Quyen;
import DTO.Role;

public class MyApp extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlSidebar;
	private JPanel pnlSidebarTop;
	private JPanel pnlSidebarCenter;
	private JPanel pnlSidebarBottom;
	private JButton btnTrangChu;
	private JButton btnBanHang;
	private JButton btnSanPham;
	private JButton btnKhuyenMai;
	private JButton btnNhanVien;
	private JButton btnKhachHang;
	private JButton btnPhieuNhap;
	private JButton btnTaiKhoan;
	private JButton btnDangXuat;
	private JButton btnPhanQuyen;
	private JButton btnWarranty;
	private JButton btnThongKe;
	private JButton btnNhaCungCap;
	private JButton btnNhapHang;
	private JButton btnReturn;	
	public String absolutePath = new File("").getAbsolutePath();
	private JPanel pnlCards;

	private CardLayout cardLayout;
	private JPanel pnlNhaCungCap;
	private JPanel pnlNhapHang;
	private JPanel pnlBanHang;
	private JPanel pnlSanPham;
	private JPanel pnlKhuyenMai;
	private JPanel pnlNhanVien;
	private JPanel pnlKhachHang;
	private JPanel pnlPhieuNhap;
	private JPanel pnlReturn;
	private JPanel pnlTrangChu;
	private JPanel pnlTaiKhoan;
	private JPanel pnlPhanQuyen;
	private JPanel pnlWarranty;
	private JPanel pnlThongKe;
	
	private static String username = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					MyApp frame = new MyApp(username);
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
	public MyApp(String username) {
		this.username = username;
		
		System.out.println(username);
		setBackground(new Color(230, 230, 230));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
//    			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng phần mềm không?", "Xác nhận đóng phần mềm", JOptionPane.YES_NO_OPTION);
//    	        if (choice == JOptionPane.YES_OPTION) {
//    	            System.exit(0);
//    	        }
				System.exit(0);
			}
		});

		int width = 1280;
		int height = 720;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, width, height);
		setLocationRelativeTo(null);
		setTitle("Phần mềm quản lý cửa hàng bán giày");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(10, 10));

		pnlSidebar = new JPanel();
		pnlSidebar.setBackground(new Color(51, 51, 51));
		pnlSidebar.setPreferredSize(new Dimension(200, 0));
		pnlSidebar.setForeground(new Color(51, 51, 51));
		contentPane.add(pnlSidebar, BorderLayout.WEST);
		pnlSidebar.setLayout(new BorderLayout(0, 0));

		pnlSidebarTop = new JPanel();
		pnlSidebarTop.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(100, 100, 100), null));
		pnlSidebarTop.setPreferredSize(new Dimension(200, 160));
		pnlSidebarTop.setBackground(new Color(51, 51, 51));
		pnlSidebar.add(pnlSidebarTop, BorderLayout.NORTH);
		pnlSidebarTop.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("");
		pnlSidebarTop.add(lblNewLabel);

		JLabel lblLogoIcon = new JLabel("");
		lblLogoIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoIcon.setIcon(new ImageIcon(absolutePath + "/src/images/icons/logo.png"));
		pnlSidebarTop.add(lblLogoIcon);

		JLabel lblLogo = new JLabel("Shopgiay88");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setInheritsPopupMenu(false);
		lblLogo.setForeground(new Color(255, 255, 255));
		lblLogo.setFont(new Font("Tahoma", Font.BOLD, 20));
		pnlSidebarTop.add(lblLogo);

		JLabel lblNewLabel_1 = new JLabel("");
		pnlSidebarTop.add(lblNewLabel_1);

		pnlSidebarCenter = new JPanel();
		pnlSidebarCenter.setBorder(null);
		pnlSidebarCenter.setBackground(new Color(51, 51, 51));
		pnlSidebar.add(pnlSidebarCenter, BorderLayout.CENTER);
		pnlSidebarCenter.setLayout(new GridLayout(0, 1, 0, 0));

		btnTrangChu = new JButton(" Trang chủ");
		btnTrangChu.setEnabled(false);
		btnTrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTrangChu.setIcon(new ImageIcon(absolutePath + "/src/images/icons/home.png"));
		btnTrangChu.setForeground(new Color(255, 255, 255));
		btnTrangChu.setBackground(new Color(51, 51, 51));
		btnTrangChu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTrangChu.setFocusable(false);
		btnTrangChu.setBorder(null);
		pnlSidebarCenter.add(btnTrangChu);

		btnBanHang = new JButton(" Bán hàng");
		btnBanHang.setEnabled(false);
		btnBanHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBanHang.setIcon(new ImageIcon(absolutePath + "/src/images/icons/cart.png"));
		btnBanHang.setForeground(new Color(255, 255, 255));
		btnBanHang.setBackground(new Color(51, 51, 51));
		btnBanHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBanHang.setFocusable(false);
		btnBanHang.setBorder(null);
		pnlSidebarCenter.add(btnBanHang);
		
		btnSanPham = new JButton(" Sản phẩm");
		btnSanPham.setEnabled(false);
		btnSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSanPham.setIcon(new ImageIcon(absolutePath + "/src/images/icons/sneakers.png"));
		btnSanPham.setForeground(new Color(255, 255, 255));
		btnSanPham.setBackground(new Color(51, 51, 51));
		btnSanPham.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSanPham.setFocusable(false);
		btnSanPham.setBorder(null);
		pnlSidebarCenter.add(btnSanPham);

		btnNhapHang = new JButton(" Nhập Hàng ");
		btnNhapHang.setEnabled(false);
		btnNhapHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhapHang.setIcon(new ImageIcon(absolutePath + "/src/images/icons/import.png"));
		btnNhapHang.setForeground(new Color(255, 255, 255));
		btnNhapHang.setBackground(new Color(51, 51, 51));
		btnNhapHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNhapHang.setFocusable(false);
		btnNhapHang.setBorder(null);
		pnlSidebarCenter.add(btnNhapHang);
		
		btnPhieuNhap = new JButton(" Phiếu Nhập");
		btnPhieuNhap.setEnabled(false);
		btnPhieuNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPhieuNhap.setIcon(new ImageIcon(absolutePath + "/src/images/icons/truck.png"));
		btnPhieuNhap.setForeground(new Color(255, 255, 255));
		btnPhieuNhap.setBackground(new Color(51, 51, 51));
		btnPhieuNhap.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPhieuNhap.setFocusable(false);
		btnPhieuNhap.setBorder(null);
		pnlSidebarCenter.add(btnPhieuNhap);

		btnKhuyenMai = new JButton(" Khuyến mãi");
		btnKhuyenMai.setEnabled(false);
		btnKhuyenMai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnKhuyenMai.setIcon(new ImageIcon(absolutePath + "/src/images/icons/coupon.png"));
		btnKhuyenMai.setForeground(new Color(255, 255, 255));
		btnKhuyenMai.setBackground(new Color(51, 51, 51));
		btnKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnKhuyenMai.setFocusable(false);
		btnKhuyenMai.setBorder(null);
		pnlSidebarCenter.add(btnKhuyenMai);

		btnNhaCungCap = new JButton(" Nhà cung cấp");
		btnNhaCungCap.setEnabled(false);
		btnNhaCungCap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhaCungCap.setIcon(new ImageIcon(absolutePath + "/src/images/icons/supplier.png"));
		btnNhaCungCap.setForeground(Color.WHITE);
		btnNhaCungCap.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNhaCungCap.setFocusable(false);
		btnNhaCungCap.setBorder(null);
		btnNhaCungCap.setBackground(new Color(51, 51, 51));
		pnlSidebarCenter.add(btnNhaCungCap);

		// warranty
		btnWarranty = new JButton(" Bảo hành");
		btnWarranty.setEnabled(false);
		btnWarranty.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnWarranty.setIcon(new ImageIcon(absolutePath + "/src/images/icons/support.png"));
		btnWarranty.setForeground(Color.WHITE);
		btnWarranty.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnWarranty.setFocusable(false);
		btnWarranty.setBorder(null);
		btnWarranty.setBackground(new Color(51, 51, 51));
		pnlSidebarCenter.add(btnWarranty);
		
		btnReturn = new JButton(" Đổi trả");
		btnReturn.setEnabled(false);
		btnReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReturn.setIcon(new ImageIcon(absolutePath + "/src/images/icons/support.png"));
		btnReturn.setForeground(Color.WHITE);
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReturn.setFocusable(false);
		btnReturn.setBorder(null);
		btnReturn.setBackground(new Color(51, 51, 51));
		pnlSidebarCenter.add(btnReturn);
		
		// phân quyền
		btnPhanQuyen = new JButton(" Phân Quyền");
		btnPhanQuyen.setEnabled(false);
		btnPhanQuyen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPhanQuyen.setIcon(new ImageIcon(absolutePath + "/src/images/icons/search.png"));
		btnPhanQuyen.setForeground(Color.WHITE);
		btnPhanQuyen.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPhanQuyen.setFocusable(false);
		btnPhanQuyen.setBorder(null);
		btnPhanQuyen.setBackground(new Color(51, 51, 51));
		pnlSidebarCenter.add(btnPhanQuyen);
		
		btnNhanVien = new JButton(" Nhân viên");
		btnNhanVien.setEnabled(false);
		btnNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNhanVien.setIcon(new ImageIcon(absolutePath + "/src/images/icons/staff.png"));
		btnNhanVien.setForeground(new Color(255, 255, 255));
		btnNhanVien.setBackground(new Color(51, 51, 51));
		btnNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNhanVien.setFocusable(false);
		btnNhanVien.setBorder(null);
		pnlSidebarCenter.add(btnNhanVien);

		btnKhachHang = new JButton(" Khách hàng");
		btnKhachHang.setEnabled(false);
		btnKhachHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnKhachHang.setIcon(new ImageIcon(absolutePath + "/src/images/icons/customer.png"));
		btnKhachHang.setForeground(new Color(255, 255, 255));
		btnKhachHang.setBackground(new Color(51, 51, 51));
		btnKhachHang.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnKhachHang.setFocusable(false);
		btnKhachHang.setBorder(null);
		pnlSidebarCenter.add(btnKhachHang);
		
		btnTaiKhoan = new JButton(" Tài khoản");
		btnTaiKhoan.setEnabled(false);
		btnTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTaiKhoan.setIcon(new ImageIcon(absolutePath + "/src/images/icons/account.png"));
		btnTaiKhoan.setForeground(Color.WHITE);
		btnTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTaiKhoan.setFocusable(false);
		btnTaiKhoan.setBorder(null);
		btnTaiKhoan.setBackground(new Color(51, 51, 51));
		pnlSidebarCenter.add(btnTaiKhoan);
// Thong ke

		btnThongKe = new JButton(" Thống kê");
		btnThongKe.setEnabled(false);
		btnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThongKe.setIcon(new ImageIcon(absolutePath + "/src/images/icons/tk.png"));
		btnThongKe.setForeground(Color.WHITE);
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThongKe.setFocusable(false);
		btnThongKe.setBorder(null);
		btnThongKe.setBackground(new Color(51, 51, 51));
		pnlSidebarCenter.add(btnThongKe);

		pnlSidebarBottom = new JPanel();
		pnlSidebarBottom.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(100, 100, 100), null));
		pnlSidebarBottom.setBackground(new Color(51, 51, 51));
		pnlSidebar.add(pnlSidebarBottom, BorderLayout.SOUTH);

		btnDangXuat = new JButton(" Đăng xuất");
		btnDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDangXuat.setIcon(new ImageIcon(absolutePath + "/src/images/icons/logout.png"));
		btnDangXuat.setPreferredSize(new Dimension(200, 30));
		btnDangXuat.setForeground(Color.WHITE);
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDangXuat.setFocusable(false);
		btnDangXuat.setBorder(null);
		btnDangXuat.setBackground(new Color(51, 51, 51));
		pnlSidebarBottom.add(btnDangXuat);

		// ========== Start: CardLayout section ==========
		pnlCards = new JPanel();
		contentPane.add(pnlCards, BorderLayout.CENTER);
		pnlCards.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout) pnlCards.getLayout();

		pnlBanHang = new JPanel();
		pnlCards.add(pnlBanHang, "pnlBanHang");
		pnlBanHang.setLayout(new BorderLayout(0, 0));
		pnlBanHang.add(new BanHangGUI(), BorderLayout.CENTER);

		pnlNhapHang = new JPanel();
		pnlCards.add(pnlNhapHang, "pnlNhapHang");
		pnlNhapHang.setLayout(new BorderLayout(0, 0));
		pnlNhapHang.add(new NhapHangGUI(), BorderLayout.CENTER);

		pnlNhaCungCap = new JPanel();
		pnlCards.add(pnlNhaCungCap, "pnlNhaCungCap");
		pnlNhaCungCap.setLayout(new BorderLayout(0, 0));
		pnlNhaCungCap.add(new NhaCungCapGUI(), BorderLayout.CENTER);

		pnlSanPham = new JPanel();
		pnlCards.add(pnlSanPham, "pnlSanPham");
		pnlSanPham.setLayout(new BorderLayout(0, 0));
		pnlSanPham.add(new QuanLySanPhamGUI(), BorderLayout.CENTER);

		pnlKhuyenMai = new JPanel();
		pnlCards.add(pnlKhuyenMai, "pnlKhuyenMai");
		pnlKhuyenMai.setLayout(new BorderLayout(0, 0));
		pnlKhuyenMai.add(new KhuyenMaiGUI(), BorderLayout.CENTER);
		
		pnlPhieuNhap = new JPanel();
		pnlCards.add(pnlPhieuNhap, "pnlPhieuNhap");
		pnlPhieuNhap.setLayout(new BorderLayout(0, 0));
		pnlPhieuNhap.add(new PhieuNhapGUI(), BorderLayout.CENTER);

		pnlReturn = new JPanel();
		pnlCards.add(pnlReturn, "pnlReturn");
		pnlReturn.setLayout(new BorderLayout(0, 0));
		pnlReturn.add(new ReturnGUI(), BorderLayout.CENTER);

		// warranty
		pnlWarranty = new JPanel();
		pnlCards.add(pnlWarranty, "pnlWarranty");
		pnlWarranty.setLayout(new BorderLayout(0, 0));
		pnlWarranty.add(new WarrantyGUI(), BorderLayout.CENTER);

		// phân quyền
		pnlPhanQuyen = new JPanel();
		pnlCards.add(pnlPhanQuyen, "pnlPhanQuyen");
		pnlPhanQuyen.setLayout(new BorderLayout(0, 0));
		pnlPhanQuyen.add(new PhanQuyenGUI(), BorderLayout.CENTER);

		// Thong ke

		pnlThongKe = new JPanel();
		pnlCards.add(pnlThongKe, "pnlThongKe");
		pnlThongKe.setLayout(new BorderLayout(0, 0));
		pnlThongKe.add(new ThongKeGUI(), BorderLayout.CENTER);

		pnlTrangChu = new JPanel();
		pnlCards.add(pnlTrangChu, "pnlTrangChu");
		pnlTrangChu.setLayout(new BorderLayout(0, 0));
		pnlTrangChu.add(new TrangChuGUI(), BorderLayout.CENTER);

		pnlNhanVien = new JPanel();
		pnlCards.add(pnlNhanVien, "pnlNhanVien");
		pnlNhanVien.setLayout(new BorderLayout(0, 0));
		pnlNhanVien.add(new NhanVienGUI(), BorderLayout.CENTER);

		pnlKhachHang = new JPanel();
		pnlCards.add(pnlKhachHang, "pnlKhachHang");
		pnlKhachHang.setLayout(new BorderLayout(0, 0));
		pnlKhachHang.add(new KhachHangGUI(), BorderLayout.CENTER);
		
		pnlTaiKhoan = new JPanel();
		pnlCards.add(pnlTaiKhoan, "pnlTaiKhoan");
		pnlTaiKhoan.setLayout(new BorderLayout(0, 0));
		pnlTaiKhoan.add(new TaiKhoanGUI(), BorderLayout.CENTER);

		// ========== End: CardLayout section ==========
		
		
		
		// ========== Start: Xử lý quyền chọn tab ==========
		phanQuyenChonTab();
		// ========== End: Xử lý quyền chọn tab ==========

		

		// Add action listener
		btnBanHang.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnKhuyenMai.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnPhieuNhap.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnTrangChu.addActionListener(this);
		btnReturn.addActionListener(this);
		btnWarranty.addActionListener(this);
		btnTaiKhoan.addActionListener(this);
		btnPhanQuyen.addActionListener(this);
		btnNhapHang.addActionListener(this);
		btnNhaCungCap.addActionListener(this);
		btnThongKe.addActionListener(this);
		// Add mouse listener to buttons
		btnTrangChu.addMouseListener(this);
		btnBanHang.addMouseListener(this);
		btnSanPham.addMouseListener(this);
		btnKhuyenMai.addMouseListener(this);
		btnNhanVien.addMouseListener(this);
		btnNhaCungCap.addMouseListener(this);
		btnKhachHang.addMouseListener(this);
		btnPhieuNhap.addMouseListener(this);
		btnDangXuat.addMouseListener(this);
		btnReturn.addMouseListener(this);
		btnWarranty.addMouseListener(this);
		btnTaiKhoan.addMouseListener(this);
		btnNhapHang.addMouseListener(this);
		btnPhanQuyen.addMouseListener(this);
		btnThongKe.addMouseListener(this);
	}

	// ========== Start: Xử lý click btn ==========
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTrangChu) {
			cardLayout.show(pnlCards, "pnlTrangChu");
			btnTrangChu.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnTrangChu);
		} else if (e.getSource() == btnNhapHang) {
			cardLayout.show(pnlCards, "pnlNhapHang");
			btnNhapHang.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnNhapHang);
		} else if (e.getSource() == btnBanHang) {
			cardLayout.show(pnlCards, "pnlBanHang");
			btnBanHang.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnBanHang);
		} else if (e.getSource() == btnSanPham) {
			cardLayout.show(pnlCards, "pnlSanPham");
			btnSanPham.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnSanPham);
		} else if (e.getSource() == btnKhuyenMai) {
			cardLayout.show(pnlCards, "pnlKhuyenMai");
			btnKhuyenMai.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnKhuyenMai);
		} else if (e.getSource() == btnNhaCungCap) {
			cardLayout.show(pnlCards, "pnlNhaCungCap");
			btnNhaCungCap.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnNhaCungCap);
		} else if (e.getSource() == btnThongKe) {
			cardLayout.show(pnlCards, "pnlThongKe");
			btnThongKe.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnThongKe);
		} else if (e.getSource() == btnNhanVien) {
			cardLayout.show(pnlCards, "pnlNhanVien");
			btnNhanVien.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnNhanVien);
		} else if (e.getSource() == btnKhachHang) {
			cardLayout.show(pnlCards, "pnlKhachHang");
			btnKhachHang.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnKhachHang);
		} else if (e.getSource() == btnPhieuNhap) {
			cardLayout.show(pnlCards, "pnlPhieuNhap");
			btnPhieuNhap.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnPhieuNhap);
		} else if (e.getSource() == btnReturn) {
			cardLayout.show(pnlCards, "pnlReturn");
			btnReturn.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnReturn);
		} else if (e.getSource() == btnWarranty) {
			cardLayout.show(pnlCards, "pnlWarranty");
			btnWarranty.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnWarranty);
		} else if (e.getSource() == btnPhanQuyen) {
			cardLayout.show(pnlCards, "pnlPhanQuyen");
			btnPhanQuyen.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnPhanQuyen);
		} else if (e.getSource() == btnTaiKhoan) {
			cardLayout.show(pnlCards, "pnlTaiKhoan");
			btnTaiKhoan.setBackground(new Color(100, 100, 100));
			resetButtonColors(btnTaiKhoan);
		} else if (e.getSource() == btnDangXuat) {
			resetButtonColors(btnDangXuat);
			btnDangXuat.setBackground(new Color(100, 100, 100));
			int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Xác nhận đăng xuất",
					JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
	// ========== End: Xử lý click btn ==========

	// ========== Start: Xử lý hover btn ==========
	public void mouseEntered(MouseEvent e) {
		JButton sourceButton = (JButton) e.getSource();
		sourceButton.setBackground(new Color(101, 101, 101));
	}

	public void mouseExited(MouseEvent e) {
		JButton sourceButton = (JButton) e.getSource();
		if (!sourceButton.getBackground().equals(new Color(100, 100, 100))) {
			sourceButton.setBackground(new Color(51, 51, 51));
		}
	}
	// ========== End: Xử lý hover btn ==========
	
	
	
	// ========== Start: Xử lý quyền chọn tab ==========
	public void phanQuyenChonTab() {
		ArrayList<Quyen> dsqUser = QuyenBUS.getDanhSachQuyenByUsername(username);
		JButton[] buttons = { btnTrangChu, btnThongKe, btnBanHang, btnSanPham, btnKhuyenMai, btnNhanVien, btnKhachHang,
				btnPhieuNhap, btnReturn, btnTaiKhoan, btnPhanQuyen, btnWarranty, btnNhapHang,
				btnNhaCungCap };
		for (JButton button : buttons) {
			for (Quyen qUser : dsqUser) {
				if (button.getText().trim().equalsIgnoreCase(qUser.getRoleTabName())) {
					button.setEnabled(true);
				}
			}
		}
	}
	// ========== End: Xử lý quyền chọn tab ==========

	

	// ========== Start: Reset màu btns ==========
	private void resetButtonColors(JButton selectedButton) {
		JButton[] buttons = { btnTrangChu, btnThongKe, btnBanHang, btnSanPham, btnKhuyenMai, btnNhanVien, btnKhachHang,
				btnPhieuNhap, btnDangXuat, btnReturn, btnTaiKhoan, btnPhanQuyen, btnWarranty, btnNhapHang,
				btnNhaCungCap };
		for (JButton button : buttons) {
			if (button != selectedButton) {
				button.setBackground(new Color(51, 51, 51));
			}
		}
	}

	// ========== End: Reset màu btns ==========
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	
}
