/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSI
 */
public class BanHangGUI extends JPanel {

    // Khai báo các thành phần của giao diện
    private JTabbedPane tabbedPane;
    private JPanel pnlBanHang;
    private JPanel pnlBHPhai;
    private JScrollPane spnSanPham;
    private JLabel lblDSSP;
    private JTable tblSanPham;
    private JScrollPane spnGioHang;
    private JLabel lblGH;
    private JTable tblGioHang;
    private JPanel pnlBHTrai;
    private JPanel pnlThongTinSPGH;
    private JLabel lblThongTinSP;
    private JLabel lblID;
    private JLabel lblTen;
    private JLabel lblHang;
    private JLabel lblXuatXu;
    private JLabel lblKhuyenMai;
    private JLabel lblDonGia;
    private JLabel lblSoLuong;
    private JTextField txtSoLuong;
    private JLabel lblAnhSP;
    private JPanel pnlButton;
    private JButton btnLuu;
    private JButton btnHuy;
    private JButton btnXoa;
    private JButton btnXuatHoaDon;

    private JPanel jPanel1;
    private JPanel jPanel2;

    private JPanel pnlHoaDon;
    private JPanel pnlDanhSachHD;
    private JPanel pnlThongTinHD;
    private JLabel lblThongTinHD;
    private JLabel lblMaHD;
    private JLabel lblMaKH;
    private JLabel lblNVLap;
    private JLabel lblNgayLap;
    private JLabel lblTongTien;
    private JLabel lblGhiChu;
    private JTextField txtGiaTu;
    private JTextField txtGiaDen;
    private JTextField txtNgayLapTu;
    private JTextField txtNgayLapDen;
    private JScrollPane spnDSHD;
    private JTable tblDSHD;
    private JPanel pnlChiTietHD;
    private JPanel pnlThongTinCTHD;
    private JTextField txtMaHD;
    private JScrollPane spnCTHD;
    private JTable tblCTHD;

    public BanHangGUI() {
        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        pnlBanHang = new JPanel(new BorderLayout());

        // Phần bên trái của pnlBanHang
        pnlBHTrai = new JPanel(new GridLayout(2, 1));

        // Phần danh sách sản phẩm
        jPanel1 = new JPanel(new BorderLayout());
        spnSanPham = new JScrollPane();
        
        JPanel panel1 = new JPanel(new GridLayout(1,0));
        lblDSSP = new JLabel("Danh sách sản phẩm");
        lblDSSP.setForeground(new Color(255, 255, 255));
        lblDSSP.setHorizontalAlignment(SwingConstants.CENTER);
        lblDSSP.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel1.setBackground(new Color(36, 136, 203));
        panel1.add(lblDSSP);
        tblSanPham = new JTable();
        tblSanPham.setBorder(null);
        tblSanPham.setSelectionBackground(new Color(232, 57, 95));
        tblSanPham.setRowHeight(25);
        tblSanPham.setIntercellSpacing(new Dimension(0, 0));
        tblSanPham.setFocusable(false);
        tblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblSanPham.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblSanPham.getTableHeader().setOpaque(false);
        tblSanPham.getTableHeader().setBackground(new Color(36,136,203));
        tblSanPham.getTableHeader().setForeground(new Color(255,255,255));
        tblSanPham.setRowHeight(25);
        spnSanPham.setBorder(null);
        spnSanPham.setBackground(new Color(255, 255, 255));
        spnSanPham.setViewportView(tblSanPham);
        jPanel1.add(panel1, BorderLayout.NORTH);
        jPanel1.add(spnSanPham, BorderLayout.CENTER);
        pnlBHTrai.add(jPanel1);

        // Phần giỏ hàng
        jPanel2 = new JPanel(new BorderLayout());
        spnGioHang = new JScrollPane();
        
        JPanel panel2 = new JPanel(new GridLayout(1,0));
        lblGH = new JLabel("Giỏ hàng");
        lblGH.setForeground(new Color(255, 255, 255));
        lblGH.setHorizontalAlignment(SwingConstants.CENTER);
        lblGH.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel2.setBackground(new Color(36, 136, 203));
        panel2.add(lblGH);
        
        tblGioHang = new JTable();
        tblGioHang.setBorder(null);
        tblGioHang.setSelectionBackground(new Color(232, 57, 95));
        tblGioHang.setRowHeight(25);
        tblGioHang.setIntercellSpacing(new Dimension(0, 0));
        tblGioHang.setFocusable(false);
        tblGioHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblGioHang.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblGioHang.getTableHeader().setOpaque(false);
        tblGioHang.getTableHeader().setBackground(new Color(36,136,203));
        tblGioHang.getTableHeader().setForeground(new Color(255,255,255));
        tblGioHang.setRowHeight(25);
        spnGioHang.setBorder(null);
        spnGioHang.setBackground(new Color(255, 255, 255));
        spnGioHang.setViewportView(tblSanPham);
        jPanel2.add(panel2, BorderLayout.NORTH);
        jPanel2.add(spnGioHang, BorderLayout.CENTER);
        pnlBHTrai.add(jPanel2);

        pnlBanHang.add(pnlBHTrai, BorderLayout.CENTER);

        // Phần bên phải của pnlBanHang
        pnlBHPhai = new JPanel(new BorderLayout());

        // Phần thông tin sản phẩm
        pnlThongTinSPGH = new JPanel(new GridLayout(8, 1));
        
        JPanel panel3 = new JPanel(new GridLayout(1,0));
        lblThongTinSP = new JLabel("Thông tin sản phẩm");
        lblThongTinSP.setForeground(new Color(255, 255, 255));
        lblThongTinSP.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongTinSP.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel3.setBackground(new Color(36, 136, 203));
        panel3.add(lblThongTinSP);
        
        pnlThongTinSPGH.add(panel3);
        lblID = new JLabel("ID:");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTen = new JLabel("Tên:");
        lblTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblHang = new JLabel("Hãng:");
        lblHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblXuatXu = new JLabel("Xuất Xứ:");
        lblXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKhuyenMai = new JLabel("Khuyến Mãi:");
        lblKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDonGia = new JLabel("Đơn Giá:");
        lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtSoLuong = new JTextField(5);
        txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAnhSP = new JLabel();
        pnlButton = new JPanel(new FlowLayout());
        btnLuu = new JButton("Lưu");
        btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLuu.setPreferredSize(new Dimension(100, 30));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLuu.setFocusable(false);
        btnLuu.setBorder(null);
        btnLuu.setBackground(new Color(21, 155, 71));
        btnHuy = new JButton("Hủy");
        btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHuy.setPreferredSize(new Dimension(100, 30));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnHuy.setFocusable(false);
        btnHuy.setBorder(null);
        btnHuy.setBackground(new Color(220, 53, 69));
        btnXoa = new JButton("Xóa");
        btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXoa.setPreferredSize(new Dimension(100, 30));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoa.setFocusable(false);
        btnXoa.setBorder(null);
        btnXoa.setBackground(new Color(220, 53, 69));
        btnXuatHoaDon = new JButton("Xuất Hóa Đơn");
        btnXuatHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXuatHoaDon.setPreferredSize(new Dimension(100, 30));
        btnXuatHoaDon.setForeground(Color.WHITE);
        btnXuatHoaDon.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXuatHoaDon.setFocusable(false);
        btnXuatHoaDon.setBorder(null);
        btnXuatHoaDon.setBackground(new Color(24, 24, 24));

        pnlThongTinSPGH.add(lblID);
        pnlThongTinSPGH.add(lblTen);
        pnlThongTinSPGH.add(lblHang);
        pnlThongTinSPGH.add(lblXuatXu);
        pnlThongTinSPGH.add(lblKhuyenMai);
        pnlThongTinSPGH.add(lblDonGia);
        pnlThongTinSPGH.add(txtSoLuong);

        pnlButton.add(btnLuu);
        pnlButton.add(btnHuy);
        pnlButton.add(btnXuatHoaDon);
        pnlButton.add(btnXoa);

        pnlBHPhai.add(pnlThongTinSPGH, BorderLayout.NORTH);
        pnlBHPhai.add(lblAnhSP, BorderLayout.CENTER);
        pnlBHPhai.add(pnlButton, BorderLayout.SOUTH);

        pnlBanHang.add(pnlBHPhai, BorderLayout.EAST);

        pnlHoaDon = new JPanel(new BorderLayout());

        pnlDanhSachHD = new JPanel(new BorderLayout());
        pnlThongTinHD = new JPanel(new GridLayout(7, 1));
        
        lblThongTinHD = new JLabel("Thông Tin Hóa Đơn");
        lblThongTinHD.setFont(new Font("Tahoma", Font.BOLD, 18));
        
        lblMaHD = new JLabel("Mã Hóa Đơn:");
        lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaKH = new JLabel("Mã Khách Hàng:");
        lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNVLap = new JLabel("Nhân Viên Lập:");
        lblNVLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNgayLap = new JLabel("Ngày Lập:");
        lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTongTien = new JLabel("Tổng Tiền:");
        lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGhiChu = new JLabel("Ghi Chú:");
        lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGiaTu = new JTextField(10);
        txtGiaTu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGiaDen = new JTextField(10);
        txtGiaDen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNgayLapTu = new JTextField(10);
        txtNgayLapTu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNgayLapDen = new JTextField(10);
        txtNgayLapDen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblDSHD = new JTable();
        tblDSHD.setBorder(null);
        tblDSHD.setSelectionBackground(new Color(232, 57, 95));
        tblDSHD.setRowHeight(25);
        tblDSHD.setIntercellSpacing(new Dimension(0, 0));
        tblDSHD.setFocusable(false);
        tblDSHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblDSHD.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblDSHD.getTableHeader().setOpaque(false);
        tblDSHD.getTableHeader().setBackground(new Color(36,136,203));
        tblDSHD.getTableHeader().setForeground(new Color(255,255,255));
        tblDSHD.setRowHeight(25);
        spnDSHD = new JScrollPane(tblDSHD);
        spnDSHD.setBorder(null);
        spnDSHD.setBackground(new Color(255, 255, 255));

        pnlChiTietHD = new JPanel(new BorderLayout());
        pnlThongTinCTHD = new JPanel();
        tblCTHD = new JTable();
        tblCTHD.setBorder(null);
        tblCTHD.setSelectionBackground(new Color(232, 57, 95));
        tblCTHD.setRowHeight(25);
        tblCTHD.setIntercellSpacing(new Dimension(0, 0));
        tblCTHD.setFocusable(false);
        tblCTHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblCTHD.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblCTHD.getTableHeader().setOpaque(false);
        tblCTHD.getTableHeader().setBackground(new Color(36,136,203));
        tblCTHD.getTableHeader().setForeground(new Color(255,255,255));
        tblCTHD.setRowHeight(25);
        spnCTHD = new JScrollPane(tblCTHD);
        spnCTHD.setBorder(null);
        spnCTHD.setBackground(new Color(255, 255, 255));
        txtMaHD = new JTextField(10);
        txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel jLabeltk = new JLabel("Tìm kiếm");
        jLabeltk.setFont(new Font("Tahoma", Font.BOLD, 14));
        pnlThongTinHD.add(lblThongTinHD);
        pnlThongTinHD.add(lblMaHD);
        pnlThongTinHD.add(lblMaKH);
        pnlThongTinHD.add(lblNVLap);
        pnlThongTinHD.add(lblNgayLap);
        pnlThongTinHD.add(lblTongTien);
        pnlThongTinHD.add(lblGhiChu);
        pnlThongTinHD.add(jLabeltk);
        pnlThongTinHD.add(txtGiaTu);
        pnlThongTinHD.add(txtGiaDen);
        pnlThongTinHD.add(txtNgayLapTu);
        pnlThongTinHD.add(txtNgayLapDen);
        pnlDanhSachHD.add(pnlThongTinHD, BorderLayout.NORTH);
        pnlDanhSachHD.add(spnDSHD, BorderLayout.CENTER);

        JLabel jLabelttct = new JLabel("Thông tin chi tiết hóa đơn");
        jLabelttct.setFont(new Font("Tahoma", Font.BOLD, 18));
        pnlThongTinCTHD.add(jLabelttct);
        pnlThongTinCTHD.add(txtMaHD);

        pnlChiTietHD.add(pnlThongTinCTHD, BorderLayout.NORTH);
        pnlChiTietHD.add(spnCTHD, BorderLayout.CENTER);

        pnlHoaDon.add(pnlDanhSachHD, BorderLayout.WEST);
        pnlHoaDon.add(pnlChiTietHD, BorderLayout.CENTER);

        tabbedPane.addTab("Bán Hàng", pnlBanHang);
        tabbedPane.addTab("Hóa Đơn", pnlHoaDon);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        // Khởi tạo dữ liệu mẫu cho các bảng
        initData();
    }

    private void initData() {
        // Tạo dữ liệu mẫu cho bảng Sản phẩm
        Object[][] sanPhamData = {
            {"SP001", "Sản phẩm 1", "Hãng A", "Xuất xứ A", "KM 10%", 100000},
            {"SP002", "Sản phẩm 2", "Hãng B", "Xuất xứ B", "KM 20%", 200000},
            {"SP003", "Sản phẩm 3", "Hãng C", "Xuất xứ C", "KM 30%", 300000}
        };
        String[] sanPhamColumns = {"ID", "Tên", "Hãng", "Xuất Xứ", "KM", "Đơn giá"};
        DefaultTableModel sanPhamModel = new DefaultTableModel(sanPhamData, sanPhamColumns);
        tblSanPham.setModel(sanPhamModel);

        // Tạo dữ liệu mẫu cho bảng Giỏ hàng
        Object[][] gioHangData = {
            {"SP001", "Sản phẩm 1", 2, 200000},
            {"SP002", "Sản phẩm 2", 3, 600000}
        };
        String[] gioHangColumns = {"ID", "Tên", "Số lượng", "Thành tiền"};
        DefaultTableModel gioHangModel = new DefaultTableModel(gioHangData, gioHangColumns);
        tblGioHang.setModel(gioHangModel);

        // Tạo dữ liệu mẫu cho bảng Danh sách hóa đơn
        Object[][] dsHDData = {
            {"HD001", "01/01/2024", 800000},
            {"HD002", "02/01/2024", 1500000}
        };
        String[] dsHDColumns = {"Mã HD", "Ngày Lập", "Tổng tiền"};
        DefaultTableModel dsHDModel = new DefaultTableModel(dsHDData, dsHDColumns);
        tblDSHD.setModel(dsHDModel);

        // Tạo dữ liệu mẫu cho bảng Chi tiết hóa đơn
        Object[][] cthdData = {
            {"HD001", "SP001", "Sản phẩm 1", 2, 200000},
            {"HD001", "SP002", "Sản phẩm 2", 3, 600000}
        };
        String[] cthdColumns = {"Mã Hóa Đơn", "Mã sản phẩm", "Tên", "Số lượng", "Thành tiền"};
        DefaultTableModel cthdModel = new DefaultTableModel(cthdData, cthdColumns);
        tblCTHD.setModel(cthdModel);
    }

}
