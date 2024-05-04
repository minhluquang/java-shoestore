/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietSanPhamBUS;
import BUS.HangBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import BUS.TheLoaiBUS;
import DAO.SanPhamDAO;
import DTO.ChiTietSanPhamDTO;
import DTO.HangDTO;
import DTO.HoaDonDTO;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.SanPhamDTO;
import DTO.TheLoaiDTO;
import DTO.ChiTietHoaDonDTO;

/**
 *
 * @author MSI
 */
public class BanHangGUI extends JPanel implements ActionListener {

    // Khai báo các thành phần của giao diện
    public String absolutePath = new File("").getAbsolutePath();
    private JTabbedPane tabbedPane;

    private JPanel pnlBanHang;
    private JPanel pnlBHPhai;
    private JScrollPane spnSanPham;
    private JLabel lblDSSP;
    private JTextField txtTenSP;
    private JComboBox<String> cbbHang;
    private JComboBox<String> cbbTheLoai;
    private Map<String, Integer> mapHang;
    private Map<String, Integer> mapLoai;
    private JButton btnReset;
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
    private JLabel lblTheLoai;
    private JLabel lblXuatXu;
    private JLabel lblNamSanXuat;
    private JLabel lblDonGia;
    private JLabel lblAnhSP;
    private JPanel pnlButton;
    private JButton btnLuu;
    // private JButton btnXem;
    private JButton btnXoa;
    private JButton btnMuaHang;

    private JPanel jPanelSanPham;
    private JPanel jPanelGioHang;

    private JPanel pnlHoaDon;
    private JPanel pnlDanhSachHD;
    private JPanel pnlThongTinHD;
    private JLabel lblThongTinHD;
    private JLabel lblMaHD;
    private JLabel lblMaKH;
    private JLabel lblNVLap;
    private JLabel lblNgayLap;
    private JLabel lblDiaChi;
    private JLabel lblTongTien;
    private JTextField txtGiaTu;
    private JTextField txtGiaDen;
    private JSpinner sprDateStart;
    private JSpinner sprDateEnd;
    // private JTextField txtNgayLapTu;
    // private JTextField txtNgayLapDen;
    private JTextField txtMaKH;
    private JTextField txtMaNVLap;
    private JButton btnTim;
    private JButton btnXoatim;
    private JScrollPane spnDSHD;
    private JTable tblDSHD;
    private JPanel pnlChiTietHD;
    private JPanel pnlThongTinCTHD;
    private JTextField txtMaHD;
    private JScrollPane spnCTHD;
    private JTable tblCTHD;

    private DefaultTableModel sanPhamModel;
    private DefaultTableModel gioHangModel;
    private DefaultTableModel dsHDModel;
    private DefaultTableModel dsCTModel;
    private ArrayList<ChiTietSanPhamDTO> dssp;
    private ArrayList<HoaDonDTO> dshd;
    private ArrayList<ChiTietHoaDonDTO> cthd;
    private ArrayList<ChiTietSanPhamDTO> dsgh = new ArrayList<>();

    public BanHangGUI banHangGUI;

    public BanHangGUI() {
        this.banHangGUI = this;
        initComponents();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        pnlBanHang = new JPanel(new BorderLayout());

        // Phần bên trái của pnlBanHang
        pnlBHTrai = new JPanel(new GridLayout(2, 1));

        // Phần danh sách sản phẩm
        jPanelSanPham = new JPanel(new BorderLayout());
        spnSanPham = new JScrollPane();

        JPanel panel1 = new JPanel(new BorderLayout());
        lblDSSP = new JLabel("Danh sách sản phẩm");
        lblDSSP.setForeground(new Color(255, 255, 255));
        lblDSSP.setHorizontalAlignment(SwingConstants.CENTER);
        lblDSSP.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel1.setBackground(new Color(36, 136, 203));
        panel1.add(lblDSSP, BorderLayout.NORTH);

        JPanel panel1_TimKiem = new JPanel(new GridLayout(1, 0));
        txtTenSP = new JTextField();
        txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTenSP.setPreferredSize(new Dimension(0, 30));
        txtTenSP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timSanPham();
            }
        });
        panel1_TimKiem.add(txtTenSP);
        JPanel pnlTmp = new JPanel(new GridLayout(1, 0));
        cbbHang = new JComboBox<>();
        cbbHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbbHang.setPreferredSize(new Dimension(0, 30));
        pnlTmp.add(cbbHang);
        cbbTheLoai = new JComboBox<>();
        cbbTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbbTheLoai.setPreferredSize(new Dimension(0, 30));
        pnlTmp.add(cbbTheLoai);
        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnReset.setPreferredSize(new Dimension(0, 30));
        pnlTmp.add(btnReset);
        panel1_TimKiem.add(pnlTmp);

        panel1.add(panel1_TimKiem, BorderLayout.CENTER);

        tblSanPham = new JTable();
        tblSanPham.setBorder(null);
        tblSanPham.setSelectionBackground(new Color(232, 57, 95));
        tblSanPham.setRowHeight(25);
        tblSanPham.setIntercellSpacing(new Dimension(0, 0));
        tblSanPham.setFocusable(false);
        tblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblSanPham.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblSanPham.getTableHeader().setOpaque(false);
        tblSanPham.getTableHeader().setBackground(new Color(36, 136, 203));
        tblSanPham.getTableHeader().setForeground(new Color(255, 255, 255));
        tblSanPham.setRowHeight(25);
        String[] sanPhamColumns = { "Serial", "Tên", "Hãng", "Loại", "Đơn giá" };
        sanPhamModel = new DefaultTableModel(sanPhamColumns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSanPham.setModel(sanPhamModel);
        tblSanPham.setRowSelectionAllowed(true);
        tblSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        spnSanPham.setBorder(null);
        spnSanPham.setBackground(new Color(255, 255, 255));
        spnSanPham.setViewportView(tblSanPham);
        jPanelSanPham.add(panel1, BorderLayout.NORTH);
        jPanelSanPham.add(spnSanPham, BorderLayout.CENTER);
        pnlBHTrai.add(jPanelSanPham);

        // Phần giỏ hàng
        jPanelGioHang = new JPanel(new BorderLayout());
        spnGioHang = new JScrollPane();

        JPanel panel2 = new JPanel(new GridLayout(1, 0));
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
        tblGioHang.getTableHeader().setBackground(new Color(36, 136, 203));
        tblGioHang.getTableHeader().setForeground(new Color(255, 255, 255));
        tblGioHang.setRowHeight(25);
        String[] gioHangColumns = { "Serial", "Tên", "Hãng", "Loại", "Đơn giá" };
        gioHangModel = new DefaultTableModel(gioHangColumns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblGioHang.setModel(gioHangModel);
        tblGioHang.setRowSelectionAllowed(true);
        tblGioHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        spnGioHang.setBorder(null);
        spnGioHang.setBackground(new Color(255, 255, 255));
        spnGioHang.setViewportView(tblGioHang);
        jPanelGioHang.add(panel2, BorderLayout.NORTH);
        jPanelGioHang.add(spnGioHang, BorderLayout.CENTER);
        pnlBHTrai.add(jPanelGioHang);

        pnlBanHang.add(pnlBHTrai, BorderLayout.CENTER);

        // Phần bên phải của pnlBanHang
        pnlBHPhai = new JPanel(new BorderLayout());

        // Phần thông tin sản phẩm
        pnlThongTinSPGH = new JPanel(new GridLayout(8, 1));

        JPanel panel3 = new JPanel(new GridLayout(1, 0));
        lblThongTinSP = new JLabel("Thông tin sản phẩm");
        lblThongTinSP.setForeground(new Color(255, 255, 255));
        lblThongTinSP.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongTinSP.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongTinSP.setPreferredSize(new Dimension(300, 30));
        panel3.setBackground(new Color(36, 136, 203));
        panel3.add(lblThongTinSP);

        pnlThongTinSPGH.add(panel3);
        lblID = new JLabel("Serial:");
        lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTen = new JLabel("Tên sản phẩm:");
        lblTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblHang = new JLabel("Hãng:");
        lblHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTheLoai = new JLabel("Thể loại:");
        lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblXuatXu = new JLabel("Xuất Xứ:");
        lblXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNamSanXuat = new JLabel("Năm sản xuất: ");
        lblNamSanXuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDonGia = new JLabel("Đơn Giá:");
        lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAnhSP = new JLabel();
        lblAnhSP.setPreferredSize(new Dimension(300, 300));
        pnlButton = new JPanel(new GridLayout(0, 1, 10, 5));
        btnLuu = new JButton("Thêm vào giỏ hàng");
        btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLuu.setPreferredSize(new Dimension(100, 30));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLuu.setFocusable(false);
        btnLuu.setBorder(null);
        btnLuu.setBackground(new Color(21, 155, 71));
        // btnXem = new JButton("Xem thông tin");
        // btnXem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // btnXem.setPreferredSize(new Dimension(100, 30));
        // btnXem.setForeground(Color.WHITE);
        // btnXem.setFont(new Font("Tahoma", Font.BOLD, 14));
        // btnXem.setFocusable(false);
        // btnXem.setBorder(null);
        // btnXem.setBackground(new Color(220, 53, 69));
        btnXoa = new JButton("Xóa");
        btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXoa.setPreferredSize(new Dimension(100, 30));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXoa.setFocusable(false);
        btnXoa.setBorder(null);
        btnXoa.setBackground(new Color(220, 53, 69));
        btnMuaHang = new JButton("Mua hàng");
        btnMuaHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMuaHang.setPreferredSize(new Dimension(100, 30));
        btnMuaHang.setForeground(Color.WHITE);
        btnMuaHang.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMuaHang.setFocusable(false);
        btnMuaHang.setBorder(null);
        btnMuaHang.setBackground(new Color(24, 24, 24));

        pnlThongTinSPGH.add(lblID);
        pnlThongTinSPGH.add(lblTen);
        pnlThongTinSPGH.add(lblHang);
        pnlThongTinSPGH.add(lblXuatXu);
        pnlThongTinSPGH.add(lblDonGia);

        // pnlButton.add(btnXem);
        pnlButton.add(btnLuu);
        pnlButton.add(btnXoa);
        pnlButton.add(btnMuaHang);

        pnlBHPhai.add(pnlThongTinSPGH, BorderLayout.NORTH);
        pnlBHPhai.add(lblAnhSP, BorderLayout.CENTER);
        pnlBHPhai.add(pnlButton, BorderLayout.SOUTH);

        pnlBanHang.add(pnlBHPhai, BorderLayout.EAST);

        pnlHoaDon = new JPanel(new BorderLayout());

        pnlDanhSachHD = new JPanel(new BorderLayout());
        pnlThongTinHD = new JPanel(new BorderLayout());

        lblThongTinHD = new JLabel("Thông Tin Hóa Đơn");
        lblThongTinHD.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongTinHD.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongTinHD.setForeground(new Color(255, 255, 255));
        lblMaHD = new JLabel("Mã Hóa Đơn:");
        lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaKH = new JLabel("Khách Hàng:");
        lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNVLap = new JLabel("Nhân Viên Lập:");
        lblNVLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNgayLap = new JLabel("Ngày Lập:");
        lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTongTien = new JLabel("Tổng Tiền:");
        lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGiaTu = new JTextField(10);
        txtGiaTu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtGiaDen = new JTextField(10);
        txtGiaDen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        java.util.Date defaultDate = calendar.getTime();

        // Tạo một SpinnerDateModel với giá trị mặc định là ngày hiện tại
        SpinnerDateModel model = new SpinnerDateModel(defaultDate, null, null, Calendar.DAY_OF_MONTH);
        sprDateStart = new JSpinner(model);
        // Định dạng ngày tháng năm cho JSpinner
        JSpinner.DateEditor editor = new JSpinner.DateEditor(sprDateStart, "yyyy/MM/dd");
        sprDateStart.setEditor(editor);
        // Đặt font và border cho spinner
        sprDateStart.setFont(new Font("Tahoma", Font.PLAIN, 14));

        SpinnerDateModel model_end = new SpinnerDateModel(defaultDate, null, null, Calendar.DAY_OF_MONTH);
        sprDateEnd = new JSpinner(model_end);

        // Định dạng ngày tháng năm cho JSpinner
        JSpinner.DateEditor editor_end = new JSpinner.DateEditor(sprDateEnd, "yyyy/MM/dd");
        sprDateEnd.setEditor(editor_end);

        sprDateEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));

        // txtNgayLapTu = new JTextField(10);
        // txtNgayLapTu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        // txtNgayLapDen = new JTextField(10);
        // txtNgayLapDen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMaKH = new JTextField();
        txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMaNVLap = new JTextField();
        txtMaNVLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnXoatim = new JButton("xóa");
        btnXoatim.setFont(new Font("Tahoma", Font.PLAIN, 14));

        tblDSHD = new JTable();
        tblDSHD.setBorder(null);
        tblDSHD.setSelectionBackground(new Color(232, 57, 95));
        tblDSHD.setRowHeight(25);
        tblDSHD.setIntercellSpacing(new Dimension(0, 0));
        tblDSHD.setFocusable(false);
        tblDSHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblDSHD.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblDSHD.getTableHeader().setOpaque(false);
        tblDSHD.getTableHeader().setBackground(new Color(36, 136, 203));
        tblDSHD.getTableHeader().setForeground(new Color(255, 255, 255));
        tblDSHD.setRowHeight(25);
        String[] dsHDColumns = { "Mã HD", "Ngày Lập", "Tổng tiền" };
        dsHDModel = new DefaultTableModel(dsHDColumns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDSHD.setModel(dsHDModel);
        tblDSHD.setRowSelectionAllowed(true);
        tblDSHD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        tblCTHD.getTableHeader().setBackground(new Color(36, 136, 203));
        tblCTHD.getTableHeader().setForeground(new Color(255, 255, 255));
        tblCTHD.setRowHeight(25);
        String[] cthdColumns = { "Mã Hóa Đơn", "Serial", "Tên sản phẩm", "Đơn giá" };
        dsCTModel = new DefaultTableModel(cthdColumns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCTHD.setModel(dsCTModel);
        tblCTHD.setRowSelectionAllowed(true);
        tblCTHD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        spnCTHD = new JScrollPane(tblCTHD);
        spnCTHD.setBorder(null);
        spnCTHD.setBackground(new Color(255, 255, 255));
        txtMaHD = new JTextField(10);
        txtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtMaHD.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setChiTietHoaDonTable();
                }
            }
        });
        JLabel jLabeltk = new JLabel("Tìm kiếm");
        jLabeltk.setFont(new Font("Tahoma", Font.BOLD, 14));
        JLabel jLabelgia1 = new JLabel("Giá từ");
        jLabelgia1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel jLabelgia2 = new JLabel("đến");
        jLabelgia2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelgia2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel jLabelngay1 = new JLabel("Ngày từ");
        jLabelngay1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel jLabelngay2 = new JLabel("đến");
        jLabelngay2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelngay2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel jLabelMaKh = new JLabel("Mã khách hàng: ");
        jLabelMaKh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JLabel jLabelMaNv = new JLabel("Mã nhân viên: ");
        jLabelMaNv.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JPanel panelHD1 = new JPanel(new GridLayout(0, 1));
        panelHD1.setBackground(new Color(36, 136, 203));
        panelHD1.add(lblThongTinHD);
        pnlThongTinHD.add(panelHD1, BorderLayout.NORTH);

        JPanel panelHD2 = new JPanel(new GridLayout(0, 1, 3, 3));
        panelHD2.setBackground(Color.WHITE);
        panelHD2.add(lblMaHD);
        panelHD2.add(lblMaKH);
        panelHD2.add(lblNVLap);
        panelHD2.add(lblNgayLap);
        panelHD2.add(lblDiaChi);
        panelHD2.add(lblTongTien);

        JPanel panelHD3 = new JPanel(new GridLayout(0, 4, 3, 3));
        panelHD3.setBackground(Color.WHITE);
        panelHD3.add(jLabeltk);
        panelHD3.add(new JLabel(""));
        panelHD3.add(new JLabel(""));
        panelHD3.add(new JLabel(""));
        panelHD3.add(jLabelgia1);
        panelHD3.add(txtGiaTu);
        panelHD3.add(jLabelgia2);
        panelHD3.add(txtGiaDen);
        panelHD3.add(jLabelngay1);
        panelHD3.add(sprDateStart);
        // panelHD3.add(txtNgayLapTu);
        panelHD3.add(jLabelngay2);
        panelHD3.add(sprDateEnd);
        // panelHD3.add(txtNgayLapDen);
        panelHD3.add(jLabelMaKh);
        panelHD3.add(txtMaKH);
        panelHD3.add(jLabelMaNv);
        panelHD3.add(txtMaNVLap);
        panelHD3.add(new JLabel());
        panelHD3.add(btnTim);
        panelHD3.add(btnXoatim);

        pnlThongTinHD.add(panelHD1, BorderLayout.NORTH);
        pnlThongTinHD.add(panelHD2, BorderLayout.CENTER);
        pnlThongTinHD.add(panelHD3, BorderLayout.SOUTH);

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
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        tabbedPane.setForegroundAt(0, new Color(36, 136, 203));
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                // Tùy chỉnh giao diện của tab được chọn
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    tabbedPane.setForegroundAt(i, Color.BLACK); // Đặt màu chữ của tất cả các tab về màu đen
                }
                tabbedPane.setForegroundAt(selectedIndex, new Color(36, 136, 203)); // Đặt màu chữ của tab được chọn
                                                                                    // thành màu đỏ
            }
        });

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        tableAddListener();
        dssp = ChiTietSanPhamBUS.getDanhSachChiTietSanPham();
        dshd = HoaDonBUS.getDanhSachHoaDon();
        cthd = ChiTietHoaDonBUS.getAllChiTietHoaDon();

        loadData();
        loadcbbHang();
        loadcbbLoai();

        cbbTheLoai.addActionListener(this);
        cbbHang.addActionListener(this);
        btnReset.addActionListener(this);
        btnXoa.addActionListener(this);
        // btnXem.addActionListener(this);
        btnLuu.addActionListener(this);
        btnMuaHang.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoatim.addActionListener(this);
    }

    public void loadcbbLoai() {
        mapLoai = new HashMap<>();
        mapLoai.put("Loại", 0);
        ArrayList<TheLoaiDTO> theLoaiDTOs = TheLoaiBUS.getDanhSachTheLoai();
        for (TheLoaiDTO theLoaiDTO : theLoaiDTOs) {
            if (theLoaiDTO.isStatus()) {
                mapLoai.put(theLoaiDTO.getCategory_name(), theLoaiDTO.getCategory_id());
            }
        }
        for (String key : mapLoai.keySet()) {
            cbbTheLoai.addItem(key);
        }
        cbbTheLoai.setSelectedItem("Loại");
    }

    public void loadcbbHang() {
        mapHang = new HashMap<>();
        mapHang.put("Hãng", 0);
        ArrayList<HangDTO> hangDTOs = HangBUS.getDanhSachHang();
        for (HangDTO hangDTO : hangDTOs) {
            if (hangDTO.isStatus()) {
                mapHang.put(hangDTO.getBrand_name(), hangDTO.getBrand_id());
            }
        }
        for (String key : mapHang.keySet()) {
            cbbHang.addItem(key);
        }
        cbbHang.setSelectedItem("Hãng");
    }

    public void loadDanhSachSanPham() {

        sanPhamModel.setRowCount(0);

        for (ChiTietSanPhamDTO chiTietSanPhamDTO : dssp) {
            if (chiTietSanPhamDTO.isSold()) {
                continue;
            }
            SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
            HangDTO hangDTO = HangBUS.getHangByID(sanPhamDTO.getBrand_id());
            TheLoaiDTO theLoaiDTO = TheLoaiBUS.getTheLoaiByID(sanPhamDTO.getCategory_id());
            Object[] sanPhamData = { chiTietSanPhamDTO.getProductSerialId(), sanPhamDTO.getProduct_name(),
                    hangDTO.getBrand_name(), theLoaiDTO.getCategory_name(), sanPhamDTO.getOutput_price() };
            sanPhamModel.addRow(sanPhamData);
        }
    }

    public void loadDanhSachHoaDon() {

        dsHDModel.setRowCount(0);

        for (HoaDonDTO hoaDonDTO : dshd) {
            Object[] hoaDonData = { hoaDonDTO.getBillId(), hoaDonDTO.getDate(), hoaDonDTO.getTotalPrice() };
            dsHDModel.addRow(hoaDonData);
        }
    }

    public void loadChiTietHoaDon() {

        dsCTModel.setRowCount(0);

        for (ChiTietHoaDonDTO chiTietHoaDonDTO : cthd) {
            ChiTietSanPhamDTO chiTietSanPhamDTO = ChiTietSanPhamBUS
                    .getChiTietSanPhamBySerial(chiTietHoaDonDTO.getProductSerialId());
            SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
            Object[] ctHoaDonData = { chiTietHoaDonDTO.getBillId(), chiTietHoaDonDTO.getProductSerialId(),
                    sanPhamDTO.getProduct_name(), chiTietHoaDonDTO.getPriceSingle() };
            dsCTModel.addRow(ctHoaDonData);
        }
    }

    public void loadDanhSachGioHang() {

        gioHangModel.setRowCount(0);

        for (ChiTietSanPhamDTO chiTietSanPhamDTO : dsgh) {
            SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
            HangDTO hangDTO = HangBUS.getHangByID(sanPhamDTO.getBrand_id());
            TheLoaiDTO theLoaiDTO = TheLoaiBUS.getTheLoaiByID(sanPhamDTO.getCategory_id());
            Object[] sanPhamData = { chiTietSanPhamDTO.getProductSerialId(), sanPhamDTO.getProduct_name(),
                    hangDTO.getBrand_name(), theLoaiDTO.getCategory_name(), sanPhamDTO.getOutput_price() };
            gioHangModel.addRow(sanPhamData);
        }
    }

    public void timSanPham() {
        int hangId = mapHang.get(cbbHang.getSelectedItem()).intValue();
        int loaiId = mapLoai.get(cbbTheLoai.getSelectedItem()).intValue();
        String ten = txtTenSP.getText().toLowerCase().strip();
        ArrayList<SanPhamDTO> dsSanPham = SanPhamBUS.searchDanhSachSanPham(hangId, loaiId, ten, 1);
        ArrayList<ChiTietSanPhamDTO> danhSachMoi = new ArrayList<>();
        for (SanPhamDTO sanPhamDTO : dsSanPham) {
            ArrayList<ChiTietSanPhamDTO> dsTmp = ChiTietSanPhamBUS.getChiTietSanPhamByID(sanPhamDTO.getProduct_id());
            danhSachMoi.addAll(dsTmp);
        }
        dssp = new ArrayList<>(danhSachMoi);
        loadDanhSachSanPham();
    }

    public void loadData() {
        loadDanhSachSanPham();
        loadDanhSachHoaDon();
        loadChiTietHoaDon();
    }

    public void reLoadData() {
        dssp = ChiTietSanPhamBUS.getDanhSachChiTietSanPham();
        dshd = HoaDonBUS.getDanhSachHoaDon();
        cthd = ChiTietHoaDonBUS.getAllChiTietHoaDon();
        loadData();
    }

    public void themVaoGioHang() {
        int selectedIndex = tblSanPham.getSelectedRow();
        if (selectedIndex == -1) {
            return;
        }
        int serial = (int) tblSanPham.getValueAt(selectedIndex, 0);
        for (ChiTietSanPhamDTO chiTietSanPhamDTO2 : dsgh) {
            if (chiTietSanPhamDTO2.getProductSerialId() == serial) {
                return;
            }
        }
        ChiTietSanPhamDTO chiTietSanPhamDTO = ChiTietSanPhamBUS.getChiTietSanPhamBySerial(serial);
        dsgh.add(chiTietSanPhamDTO);
        loadDanhSachGioHang();
    }

    public void xoaKhoiGioHang() {
        int selectedIndex = tblGioHang.getSelectedRow();
        if (selectedIndex == -1) {
            return;
        }
        dsgh.remove(selectedIndex);
        loadDanhSachGioHang();
    }

    public void xoaTatCaKhoiGioHang() {
        dsgh.clear();
        loadDanhSachGioHang();
    }

    public HoaDonDTO taoHoaDon() {
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        int id = HoaDonBUS.getSoLuongHoaDon() + 1;
        hoaDonDTO.setBillId(id);
        hoaDonDTO.setDate(Date.valueOf(LocalDate.now()));
        int total = 0;
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : dsgh) {
            SanPhamDTO sp = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
            total += sp.getOutput_price();
        }
        hoaDonDTO.setTotalPrice(total);
        NhanVien nv = NhanVienBUS.getNhanVienByAccountID(MyApp.user.getAccountId());
        hoaDonDTO.setStaffId(nv.getStaffId());
        hoaDonDTO.setCustomerId(0);
        System.out.println(hoaDonDTO.getBillId());
        return hoaDonDTO;
    }

    public ArrayList<ChiTietHoaDonDTO> taoChiTietHoaDon(int billId) {
        ArrayList<ChiTietHoaDonDTO> chiTietHoaDonDTOs = new ArrayList<>();
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : dsgh) {
            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            chiTietHoaDonDTO.setBillId(billId);
            chiTietHoaDonDTO.setProductSerialId(chiTietSanPhamDTO.getProductSerialId());
            chiTietHoaDonDTO
                    .setPriceSingle(SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId()).getOutput_price());
            chiTietHoaDonDTOs.add(chiTietHoaDonDTO);
        }
        return chiTietHoaDonDTOs;
    }

    public void muaHang() {
        if (dsgh.size() == 0) {
            JOptionPane.showMessageDialog(null, "Hãy thêm sản phẩm vào giỏ hàng !");
            return;
        }
        HoaDonDTO hoaDonDTO = taoHoaDon();
        ArrayList<ChiTietHoaDonDTO> chiTietHoaDonDTOs = taoChiTietHoaDon(hoaDonDTO.getBillId());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietHoaDonGUI frame = new ChiTietHoaDonGUI(hoaDonDTO, chiTietHoaDonDTOs, banHangGUI);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        xoaTatCaKhoiGioHang();
    }

    public boolean ktrThongTinTimHoaDon() {
        boolean isValid = true;
        if (!txtGiaTu.getText().isEmpty()) {
            try {
                int tmp = Integer.parseInt(txtGiaTu.getText());
            } catch (NumberFormatException e) {
                isValid = false;
            }
        }
        if (!txtGiaDen.getText().isEmpty()) {
            try {
                int tmp = Integer.parseInt(txtGiaDen.getText());
            } catch (NumberFormatException e) {
                isValid = false;
            }
        }
        if (!txtMaKH.getText().isEmpty()) {
            try {
                int tmp = Integer.parseInt(txtMaKH.getText());
            } catch (NumberFormatException e) {
                isValid = false;
            }
        }
        if (!txtMaNVLap.getText().isEmpty()) {
            try {
                int tmp = Integer.parseInt(txtMaNVLap.getText());
            } catch (NumberFormatException e) {
                isValid = false;
            }
        }
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Giá trị nhập phải là số");
        }
        return isValid;
    }

    public void timHoaDon() {
        if (ktrThongTinTimHoaDon()) {
            java.util.Date date = (java.util.Date) sprDateStart.getValue();
            Date dateStart = new Date(date.getTime());
            date = (java.util.Date) sprDateEnd.getValue();
            Date dateEnd = new Date(date.getTime());
            int totalMin = -1;
            int totalMax = -1;
            int MaKH = -1;
            int MaNV = -1;
            if (!txtGiaTu.getText().isEmpty()) {
                totalMin = Integer.parseInt(txtGiaTu.getText());
            }
            if (!txtGiaDen.getText().isEmpty()) {
                totalMax = Integer.parseInt(txtGiaDen.getText());
            }
            if (!txtMaKH.getText().isEmpty()) {
                MaKH = Integer.parseInt(txtMaKH.getText());
            }
            if (!txtMaNVLap.getText().isEmpty()) {
                MaNV = Integer.parseInt(txtMaNVLap.getText());
            }
            // System.out.println(totalMin);
            // System.out.println(totalMax);
            // System.out.println(MaKH);
            // System.out.println(MaNV);
            // System.out.println(dateStart);
            // System.out.println(dateEnd);
            dshd = HoaDonBUS.searchHoaDon(totalMin, totalMax, MaKH, MaNV, dateStart, dateEnd);
            loadDanhSachHoaDon();
        }
    }

    public void xoaTimHoaDon() {
        txtGiaTu.setText("");
        txtGiaDen.setText("");
        txtMaKH.setText("");
        txtMaNVLap.setText("");
        dshd = HoaDonBUS.getDanhSachHoaDon();
        loadDanhSachHoaDon();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLuu) {
            themVaoGioHang();
        } else if (e.getSource() == btnXoa) {
            xoaKhoiGioHang();
        } else if (e.getSource() == btnMuaHang) {
            muaHang();
        } else if (e.getSource() == btnTim) {
            timHoaDon();
        } else if (e.getSource() == btnXoatim) {
            xoaTimHoaDon();
        } else if (e.getSource() == btnReset) {
            dssp = ChiTietSanPhamBUS.getDanhSachChiTietSanPham();
            loadDanhSachSanPham();
        } else if (e.getSource() == cbbHang) {
            timSanPham();
        } else if (e.getSource() == cbbTheLoai) {
            timSanPham();
        }
    }

    public void xemThongTinSanPham(ChiTietSanPhamDTO chiTietSanPhamDTO, SanPhamDTO sanPhamDTO, String hang,
            String loai) {
        lblID.setText("Serial: " + chiTietSanPhamDTO.getProductSerialId());
        lblTen.setText("Tên sản phẩm: " + sanPhamDTO.getProduct_name());
        lblHang.setText("Hãng: " + hang);
        lblTheLoai.setText("Thể loại: " + loai);
        lblXuatXu.setText("Xuất Xứ: " + sanPhamDTO.getCountry());
        lblNamSanXuat.setText("Năm sản xuất: " + sanPhamDTO.getYear_of_product());
        lblDonGia.setText("Đơn Giá: " + sanPhamDTO.getOutput_price());
        ImageIcon icon = new ImageIcon(absolutePath + sanPhamDTO.getImage_path());
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon newIcon = new ImageIcon(newImage);
        lblAnhSP.setIcon(newIcon);
    }

    public void xemThongTinHoaDon(HoaDonDTO hoaDonDTO) {
        lblMaHD.setText("Mã Hóa Đơn: " + hoaDonDTO.getBillId());
        KhachHang khachHang = KhachHangBUS.getKhachHangByID(hoaDonDTO.getCustomerId());
        lblMaKH.setText("Khách Hàng: " + khachHang.getCustomerName());
        NhanVien nhanVien = NhanVienBUS.getNhanVienByID(hoaDonDTO.getStaffId());
        lblNVLap.setText("Nhân Viên Lập: " + nhanVien.getFull_name());
        lblNgayLap.setText("Ngày Lập: " + hoaDonDTO.getDate());
        lblDiaChi.setText("Địa chỉ: " + hoaDonDTO.getAddress());
        lblTongTien.setText("Tổng Tiền: " + hoaDonDTO.getTotalPrice());
        txtMaHD.setText(hoaDonDTO.getBillId() + "");
        setChiTietHoaDonTable();

    }

    public void setChiTietHoaDonTable() {
        int maHoaDon = -1;
        if (!txtMaHD.getText().isEmpty()) {
            maHoaDon = Integer.parseInt(txtMaHD.getText());
        }

        if (maHoaDon == -1) {
            cthd = ChiTietHoaDonBUS.getAllChiTietHoaDon();
        } else {
            cthd = ChiTietHoaDonBUS.getChiTietHoaDonByID(maHoaDon);
        }
        loadChiTietHoaDon();
    }

    public void tableAddListener() {
        tblSanPham.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = tblSanPham.getSelectedRow();
                int serial = (int) tblSanPham.getValueAt(selectedIndex, 0);
                ChiTietSanPhamDTO chiTietSanPhamDTO = ChiTietSanPhamBUS.getChiTietSanPhamBySerial(serial);
                SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
                String hang = tblSanPham.getValueAt(selectedIndex, 2).toString();
                String theLoai = tblSanPham.getValueAt(selectedIndex, 3).toString();
                xemThongTinSanPham(chiTietSanPhamDTO, sanPhamDTO, hang, theLoai);
            }
        });
        tblDSHD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = tblDSHD.getSelectedRow();
                int maHoaDon = (int) tblDSHD.getValueAt(selectedIndex, 0);
                HoaDonDTO hoaDonDTO = HoaDonBUS.getHoaDonByID(maHoaDon);
                xemThongTinHoaDon(hoaDonDTO);
            }
        });
        tblGioHang.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedIndex = tblGioHang.getSelectedRow();
                int serial = (int) tblGioHang.getValueAt(selectedIndex, 0);
                ChiTietSanPhamDTO chiTietSanPhamDTO = ChiTietSanPhamBUS.getChiTietSanPhamBySerial(serial);
                SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
                String hang = tblGioHang.getValueAt(selectedIndex, 2).toString();
                String theLoai = tblGioHang.getValueAt(selectedIndex, 3).toString();
                xemThongTinSanPham(chiTietSanPhamDTO, sanPhamDTO, hang, theLoai);
            }
        });
    }

}
