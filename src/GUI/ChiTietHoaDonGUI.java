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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import BUS.ChiTietHoaDonBUS;
import BUS.ChiTietSanPhamBUS;
import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
import DAO.SanPhamDAO;
import DTO.ChiTietHoaDonDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.HoaDonDTO;
import DTO.KhachHang;
import DTO.KhuyenMai;
import DTO.NhanVien;
import DTO.SanPhamDTO;
import DTO.TaiKhoan;

/**
 *
 * @author MSI
 */
public class ChiTietHoaDonGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBillID;
    private JTextField txtAccountID;
    private JTextField txtDate;
    private JTextField txtToTalPrice;
    private JTextField txtCustomerID;
    private JButton btnThemKhachHang;
    private JComboBox<String> cbbDiscountCode;
    private DefaultComboBoxModel<String> dcmMaGiamGia;
    private JScrollPane spnSanPham;
    private JLabel lblDSSP;
    private JTable tblSanPham;
    private DefaultTableModel sanPhamModel;
    private JPanel jPanelSanPham;
    private JTextField txtDiaChi;
    private JLabel lblTongTien;

    private JPanel panel_5;
    private JButton btnHuy;
    private JButton btnLuu;

    private final ButtonGroup buttonGroup = new ButtonGroup();

    public HoaDonDTO hoaDonDTO;
    public BanHangGUI banHangGUI;
    public ChiTietHoaDonGUI chiTietHoaDonGUI;
    public ArrayList<ChiTietHoaDonDTO> chiTietHoaDonDTOs;
    public int tamTinh;
    public KhachHang khachHang;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietHoaDonGUI frame = new ChiTietHoaDonGUI(new HoaDonDTO(), new ArrayList<>(), new BanHangGUI());
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
    public ChiTietHoaDonGUI(HoaDonDTO hoaDon, ArrayList<ChiTietHoaDonDTO> chiTietHoaDons, BanHangGUI bHGUI) {
        this.hoaDonDTO = hoaDon;
        this.chiTietHoaDonDTOs = chiTietHoaDons;
        this.chiTietHoaDonGUI = this;
        this.banHangGUI = bHGUI;
        this.tamTinh = hoaDonDTO.getTotalPrice();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chi tiết nhân viên không?",
                        "Xác nhận đóng chi tiết nhân viên", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        int width = 980;
        int height = 600;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, width, height);
        setLocationRelativeTo(null);
        setTitle("Chi tiết hóa đơn");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel pnlRight = new JPanel();
        pnlRight.setPreferredSize(new Dimension(200, 10));
        pnlRight.setBorder(null);
        pnlRight.setBackground(Color.WHITE);
        contentPane.add(pnlRight, BorderLayout.WEST);
        pnlRight.setLayout(new BorderLayout(0, 10));

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        pnlRight.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblNewLabel_4 = new JLabel("Chi tiết hóa đơn");
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

        panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel_4.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 1, 0, 5));

        JLabel lblNewLabel_6_2 = new JLabel("Mã Hóa đơn");
        lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_2);

        txtBillID = new JTextField();
        txtBillID.setEnabled(false);
        txtBillID.setEditable(false);
        txtBillID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBillID.setColumns(10);
        panel_5.add(txtBillID);

        JLabel lblNewLabel_6 = new JLabel("Khách hàng");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6);

        txtCustomerID = new JTextField();
        txtCustomerID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtCustomerID.setColumns(10);
        txtCustomerID.setEnabled(false);
        panel_5.add(txtCustomerID);

        btnThemKhachHang = new JButton("Thêm thông tin khách hàng");
        btnThemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(btnThemKhachHang);

        JLabel lblNewLabel_6_3 = new JLabel("Nhân viên lập");
        lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3);

        txtAccountID = new JTextField();
        txtAccountID.setPreferredSize(new Dimension(100, 19));
        txtAccountID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtAccountID.setColumns(10);
        txtAccountID.setEnabled(false);
        panel_5.add(txtAccountID);

        JLabel lblNewLabel_6_3_1 = new JLabel("Ngày lập");
        lblNewLabel_6_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1);

        txtDate = new JTextField();
        txtDate.setPreferredSize(new Dimension(100, 19));
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDate.setColumns(10);
        txtDate.setEnabled(false);
        panel_5.add(txtDate);

        JLabel lblNewLabel_6_3_1_1 = new JLabel("Địa chỉ");
        lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_1);

        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(new Dimension(100, 19));
        txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDiaChi.setColumns(10);
        panel_5.add(txtDiaChi);

        JLabel lblNewLabel_6_3_1_2 = new JLabel("Tổng tiền");
        lblNewLabel_6_3_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_2);

        txtToTalPrice = new JTextField();
        txtToTalPrice.setPreferredSize(new Dimension(100, 19));
        txtToTalPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtToTalPrice.setColumns(10);
        txtToTalPrice.setEnabled(false);
        panel_5.add(txtToTalPrice);

        lblTongTien = new JLabel("Tổng tiền: " + tamTinh);
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblTongTien);

        JLabel lblNewLabel_6_3_1_3 = new JLabel("Mã giảm giá");
        lblNewLabel_6_3_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_3);

        cbbDiscountCode = new JComboBox<>();
        dcmMaGiamGia = new DefaultComboBoxModel<>();
        cbbDiscountCode.setModel(dcmMaGiamGia);
        cbbDiscountCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbbDiscountCode.setFocusable(false);
        panel_5.add(cbbDiscountCode);

        JLabel lblNewLabel_7_1_1 = new JLabel("");
        panel_5.add(lblNewLabel_7_1_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_5.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 20, 0));

        btnLuu = new JButton("Lưu");
        btnLuu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLuu.setPreferredSize(new Dimension(100, 30));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLuu.setFocusable(false);
        btnLuu.setBorder(null);
        btnLuu.setBackground(new Color(21, 155, 71));
        panel_2.add(btnLuu);

        btnHuy = new JButton("Huỷ bỏ");
        btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHuy.setPreferredSize(new Dimension(100, 30));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnHuy.setFocusable(false);
        btnHuy.setBorder(null);
        btnHuy.setBackground(new Color(220, 53, 69));
        panel_2.add(btnHuy);

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

        jPanelSanPham = new JPanel(new BorderLayout());
        spnSanPham = new JScrollPane();

        JPanel panel1 = new JPanel(new GridLayout(1, 0));
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

        contentPane.add(jPanelSanPham, BorderLayout.CENTER);

        loadData();

        btnThemKhachHang.addActionListener(this);
        btnHuy.addActionListener(this);
        btnLuu.addActionListener(this);
        cbbDiscountCode.addActionListener(this);
    }

    public void loadMaGiamGia() {
        dcmMaGiamGia.addElement("Mã giảm giá");
        dcmMaGiamGia.setSelectedItem("Mã giảm giá");
        ArrayList<KhuyenMai> khuyenMais = KhuyenMaiBUS.getDanhSachKhuyenMai();
        for (KhuyenMai khuyenMai : khuyenMais) {
            if(khuyenMai.getStatus()==1){
                dcmMaGiamGia.addElement(khuyenMai.getDiscount_code());
            }
        }
    }

    public void loadData() {
        txtBillID.setText(hoaDonDTO.getBillId() + "");
        txtDate.setText(hoaDonDTO.getDate().toString());
        txtAccountID.setText(hoaDonDTO.getStaffId() + "");
        txtToTalPrice.setText(tamTinh + "");
        loadChiTietHoaDon();
        loadMaGiamGia();
    }

    public void loadCustumerId(int id) {
        hoaDonDTO.setCustomerId(id);
        txtCustomerID.setText(id + "");
    }

    public void loadChiTietHoaDon() {

        sanPhamModel.setRowCount(0);

        for (ChiTietHoaDonDTO chiTietHoaDonDTO : chiTietHoaDonDTOs) {
            ChiTietSanPhamDTO chiTietSanPhamDTO = ChiTietSanPhamBUS
                    .getChiTietSanPhamBySerial(chiTietHoaDonDTO.getProductSerialId());
            SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSanPhamDTO.getProductId());
            Object[] ctHoaDonData = { chiTietHoaDonDTO.getBillId(), chiTietHoaDonDTO.getProductSerialId(),
                    sanPhamDTO.getProduct_name(), chiTietHoaDonDTO.getPriceSingle() };
            sanPhamModel.addRow(ctHoaDonData);
        }
    }

    public void tinhToTalPrice() {
        KhuyenMai khuyenMai = KhuyenMaiBUS.getKhuyenMaiByDiscountCode(hoaDonDTO.getDiscountCode());
        if (khuyenMai.getType() == "AR") {
            System.out.println(khuyenMai.getDiscount_value());
            int newtotal = tamTinh - khuyenMai.getDiscount_value();
            hoaDonDTO.setTotalPrice(newtotal);
            lblTongTien.setText("Tổng tiền: " + hoaDonDTO.getTotalPrice() + "đ");
        } else {
            int newtotal = tamTinh - (tamTinh * khuyenMai.getDiscount_value() / 100);
            hoaDonDTO.setTotalPrice(newtotal);
            lblTongTien.setText("Tổng tiền: " + hoaDonDTO.getTotalPrice() + "đ");
        }
    }

    public void addMaGiamGia() {
        if (cbbDiscountCode.getSelectedItem().toString().equals("Mã giảm giá")) {
            hoaDonDTO.setTotalPrice(tamTinh);
            hoaDonDTO.setDiscountCode(null);
            lblTongTien.setText("Tổng tiền: " + tamTinh + "đ");
        } else {
            String magg = cbbDiscountCode.getSelectedItem().toString();
            KhuyenMai khuyenMai = KhuyenMaiBUS.getKhuyenMaiByDiscountCode(magg);
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            Date startDate = Date.valueOf(khuyenMai.getStart_date());
            Date endDate = Date.valueOf(khuyenMai.getEnd_date());
            if (date.after(startDate) && date.before(endDate)) {
                hoaDonDTO.setDiscountCode(cbbDiscountCode.getSelectedItem().toString());
                tinhToTalPrice();
            } else {
                JOptionPane.showMessageDialog(null, "Không năm trong khoảng thời gian khuyến mãi");
                cbbDiscountCode.setSelectedItem("Mã giảm giá");
            }
        }
    }

    public void danhDauDanhSachDaBanSanPham() {
        for (ChiTietHoaDonDTO chiTietHoaDonDTO : chiTietHoaDonDTOs) {
            ChiTietSanPhamBUS.danhDauDaBan(chiTietHoaDonDTO.getProductSerialId());
        }
    }

    public void luuHoaDon() throws FileNotFoundException {
        if (hoaDonDTO.getCustomerId() == 0) {
            themKhachHang();
        } else if (txtDiaChi.getText() == "") {
            JOptionPane.showMessageDialog(null, "Địa chỉ không được bỏ trống");
        } else {
            hoaDonDTO.setAddress(txtDiaChi.getText());
            int op = JOptionPane.showConfirmDialog(null, "Lưu hóa đơn", "Xác nhận lưu", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                if (HoaDonBUS.themHoaDon(hoaDonDTO)) {
                    if (ChiTietHoaDonBUS.themDSChiTietHoaDon(chiTietHoaDonDTOs)) {
                        danhDauDanhSachDaBanSanPham();
                        xuatPdf();
                        banHangGUI.reLoadData();
                    }
                }
                dispose();
            }
        }
    }

    public void themKhachHang() {
        khachHang = new KhachHang(0, "", "");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietKhachHangGUI frame = new ChiTietKhachHangGUI(khachHang, chiTietHoaDonGUI);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbbDiscountCode) {
            addMaGiamGia();
        }
        if (e.getSource() == btnThemKhachHang) {
            themKhachHang();
        }
        if (e.getSource() == btnHuy) {
            int op = JOptionPane.showConfirmDialog(null, "Hủy tạo hóa đơn", "Xác nhận hủy",
                    JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
        if (e.getSource() == btnLuu) {
            try {
                luuHoaDon();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public boolean isIn(ArrayList<SanPhamDTO> sanPhams, ChiTietSanPhamDTO chiTietSP) {
        for (SanPhamDTO e : sanPhams) {
            if (chiTietSP.getProductId() == e.getProduct_id()) {
                return true;
            }
        }
        return false;
    }

    public void xuatPdf() throws FileNotFoundException {
        String path = "invoice" + hoaDonDTO.getBillId() + ".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        Paragraph storeTitle = new Paragraph("Cua hang ban giay Shopgiay88").setBold().setFontSize(16);
        storeTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
        Paragraph storeAddress = new Paragraph("DC: 273 An Duong Vuong, P.3, Q.5, Tp. HCM");
        storeAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
        Paragraph phoneNumberAddress = new Paragraph("SDT: 028.392.44.690");
        phoneNumberAddress.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

        Paragraph invoiceTitle = new Paragraph("HOA DON BAN HANG").setBold().setFontSize(16).setMarginTop(20);
        invoiceTitle.setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);

        document.add(storeTitle);
        document.add(storeAddress);
        document.add(phoneNumberAddress);
        document.add(invoiceTitle);

        // bill info
        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = { twocol150, twocol };

        Table table = new Table(twocolumnWidth);
        table.setMarginTop(20);
        table.addCell(new Cell().add(new Paragraph("Ngay tao: "+hoaDonDTO.getDate())).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("Ma so: "+hoaDonDTO.getBillId())).setBorder(Border.NO_BORDER));
        NhanVien nv = NhanVienBUS.getNhanVienByID(hoaDonDTO.getStaffId());
        table.addCell(new Cell().add(new Paragraph("Thu ngan: "+nv.getFull_name())).setBorder(Border.NO_BORDER));

        document.add(table);

        // bill detail
        float threecol = 190f;
        float threeColumnWidth[] = { threecol, threecol, threecol };
        Table tableBillDetail = new Table(threeColumnWidth);
        tableBillDetail.setMarginTop(20);

        // header
        Cell cellHeader1 = new Cell().add(new Paragraph("Ten san pham")).setBorder(new SolidBorder(1));
        Cell cellHeader2 = new Cell().add(new Paragraph("So luong")).setBorder(new SolidBorder(1));
        Cell cellHeader3 = new Cell().add(new Paragraph("Don gia")).setBorder(new SolidBorder(1));

        tableBillDetail.addCell(cellHeader1);
        tableBillDetail.addCell(cellHeader2);
        tableBillDetail.addCell(cellHeader3);

        // body
        ArrayList<SanPhamDTO> sanPhams = new ArrayList<>();
        for (ChiTietHoaDonDTO chiTietHD : chiTietHoaDonDTOs) {
            ChiTietSanPhamDTO chiTietSP = ChiTietSanPhamBUS.getChiTietSanPhamBySerial(chiTietHD.getProductSerialId());
            if (isIn(sanPhams, chiTietSP)) {
                for (SanPhamDTO sanPhamDTO : sanPhams) {
                    if (sanPhamDTO.getProduct_id() == chiTietSP.getProductId()) {
                        sanPhamDTO.setQuantity(sanPhamDTO.getQuantity() + 1);
                    }
                }
            } else {
                SanPhamDTO sanPhamDTO = SanPhamBUS.getSanPhamByID(chiTietSP.getProductId());
                sanPhamDTO.setQuantity(1);
                sanPhams.add(sanPhamDTO);
            }
        }

        for (SanPhamDTO sanPhamDTO : sanPhams) {
            Cell cellBody1 = new Cell().add(new Paragraph(sanPhamDTO.getProduct_name())).setBorder(new SolidBorder(1));
            Cell cellBody2 = new Cell().add(new Paragraph(String.valueOf(sanPhamDTO.getQuantity()))).setBorder(new SolidBorder(1));
            Cell cellBody3 = new Cell().add(new Paragraph(String.valueOf(sanPhamDTO.getOutput_price()))).setBorder(new SolidBorder(1));

            cellBody2.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);
            cellBody3.setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);

            tableBillDetail.addCell(cellBody1);
            tableBillDetail.addCell(cellBody2);
            tableBillDetail.addCell(cellBody3);
        }

        document.add(tableBillDetail);

        // Phan bottom
        Table tableBottom = new Table(twocolumnWidth);
        tableBottom.setMarginTop(20);
        tableBottom.addCell(new Cell().add(new Paragraph("Tien hang: "+tamTinh+"đ")).setBorder(Border.NO_BORDER));
        tableBottom.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
        KhuyenMai km = KhuyenMaiBUS.getKhuyenMaiByDiscountCode(hoaDonDTO.getDiscountCode());
        int giamGia;
        if (km.getType()=="AR") {
            giamGia = km.getDiscount_value();
        } else {
            giamGia = tamTinh*km.getDiscount_value()/100;
        }
        tableBottom.addCell(new Cell().add(new Paragraph("Giam gia: "+giamGia+"đ")).setBorder(Border.NO_BORDER));
        tableBottom.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        tableBottom.addCell(new Cell(1, 2).add(new LineSeparator(new DottedLine())));

        tableBottom.addCell(new Cell().add(new Paragraph("TONG TIEN "+hoaDonDTO.getTotalPrice()+"")).setBorder(Border.NO_BORDER));
        tableBottom.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        document.add(tableBottom);

        document.close();
    }
}
