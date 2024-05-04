
package GUI;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DTO.HangDTO;
import DTO.NhanVien;
import BUS.HangBUS;
import BUS.NhanVienBUS;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import DTO.Role;
import DTO.TheLoaiDTO;
import BUS.RoleBUS;
import BUS.TheLoaiBUS;

public class QLHang extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    public String absolutePath = new File("").getAbsolutePath();
    private JTextField txtTimKiem;
    private JTable tblHang;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JButton btnChiTiet;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnTim;
    private JButton btnXoa;
    private JButton btnNhapExcel;
    private JButton btnXuatExcel;
    private JButton btnDoiTrangThai;

    private DefaultTableModel dtmHang;

    public ArrayList<HangDTO> dsHang;

    public QLHang qlHang;

    /**
     * Create the panel.
     */
    public QLHang() {
        this.qlHang = this;
        setBackground(new Color(230, 230, 230));
        setLayout(new BorderLayout(10, 10));

        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(new Color(255, 255, 255));
        add(pnlTop, BorderLayout.NORTH);
        pnlTop.setLayout(new BorderLayout(20, 5));

        JPanel pnlSearch = new JPanel();
        pnlSearch.setBackground(new Color(255, 255, 255));
        pnlTop.add(pnlSearch, BorderLayout.CENTER);
        pnlSearch.setLayout(new BorderLayout(5, 10));

        JPanel panel_1 = new JPanel();
        pnlSearch.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        // ========== Start: Xử lý search ==========
        txtTimKiem = new JTextField();
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                xuLyTimKiem();
            }
        });
        txtTimKiem.setMinimumSize(new Dimension(250, 19));
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTimKiem.setColumns(10);
        panel_1.add(txtTimKiem);
        // ========== End: Xử lý search ==========

        txtTimKiem.setMinimumSize(new Dimension(250, 19));
        txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtTimKiem.setColumns(10);
        panel_1.add(txtTimKiem);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        pnlSearch.add(panel_2, BorderLayout.EAST);
        panel_2.setLayout(new GridLayout(0, 1, 0, 0));

        btnTim = new JButton("Làm mới");
        btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTim.setIcon(new ImageIcon(absolutePath + "/src/images/icons/reload.png"));
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnTim.setFocusable(false);
        btnTim.setBackground(new Color(255, 255, 255));
        panel_2.add(btnTim);

        JPanel pnlTopBottom = new JPanel();
        pnlTopBottom.setBackground(Color.WHITE);
        pnlSearch.add(pnlTopBottom, BorderLayout.SOUTH);
        pnlTopBottom.setLayout(new GridLayout(0, 7, 5, 0));

        btnThem = new JButton("Thêm");
        btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnThem.setIcon(new ImageIcon(absolutePath + "/src/images/icons/add.png"));
        btnThem.setPreferredSize(new Dimension(0, 40));
        btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnThem.setFocusable(false);
        btnThem.setBackground(Color.WHITE);
        pnlTopBottom.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSua.setIcon(new ImageIcon(absolutePath + "/src/images/icons/edit.png"));
        btnSua.setPreferredSize(new Dimension(0, 40));
        btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSua.setFocusable(false);
        btnSua.setBackground(Color.WHITE);
        pnlTopBottom.add(btnSua);

        btnDoiTrangThai = new JButton("Đổi trạng thái");
        btnDoiTrangThai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDoiTrangThai.setIcon(new ImageIcon(absolutePath + "/src/images/icons/delete.png"));
        btnDoiTrangThai.setPreferredSize(new Dimension(200, 40));
        btnDoiTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDoiTrangThai.setFocusable(false);
        btnDoiTrangThai.setBackground(Color.WHITE);
        pnlTopBottom.add(btnDoiTrangThai);

        btnNhapExcel = new JButton("Nhập excel");
        btnNhapExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNhapExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
        btnNhapExcel.setPreferredSize(new Dimension(0, 40));
        btnNhapExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNhapExcel.setFocusable(false);
        btnNhapExcel.setBackground(Color.WHITE);
        pnlTopBottom.add(btnNhapExcel);

        btnXuatExcel = new JButton("Xuất excel");
        btnXuatExcel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnXuatExcel.setIcon(new ImageIcon(absolutePath + "/src/images/icons/excel.png"));
        btnXuatExcel.setPreferredSize(new Dimension(0, 40));
        btnXuatExcel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXuatExcel.setFocusable(false);
        btnXuatExcel.setBackground(Color.WHITE);
        pnlTopBottom.add(btnXuatExcel);

        JPanel panel_7 = new JPanel();
        panel_7.setBackground(new Color(255, 255, 255));
        pnlTop.add(panel_7, BorderLayout.NORTH);

        JLabel lblNewLabel_1 = new JLabel("");
        pnlTop.add(lblNewLabel_1, BorderLayout.WEST);

        JLabel lblNewLabel_2 = new JLabel("");
        pnlTop.add(lblNewLabel_2, BorderLayout.EAST);

        JPanel panel_8 = new JPanel();
        panel_8.setBackground(new Color(255, 255, 255));
        pnlTop.add(panel_8, BorderLayout.SOUTH);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(255, 255, 255));
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(new BorderLayout(0, 0));

        // ========== TABLE DANH SÁCH NHÂN VIÊN ==========
        tblHang = new JTable();
        // tblHang.addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseClicked(MouseEvent e) {
        // xuLyClickTable();
        // }
        // });
        tblHang.setBorder(null);
        tblHang.setSelectionBackground(new Color(232, 57, 95));
        tblHang.setRowHeight(25);
        tblHang.setIntercellSpacing(new Dimension(0, 0));
        tblHang.setFocusable(false);

        dtmHang = new DefaultTableModel(new Object[] { "Mã Hãng", "Tên Hãng", "Trạng thái" }, 0);
        tblHang.setModel(dtmHang);
        tblHang.setDefaultEditor(Object.class, null);
        tblHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tblHang);
        scrollPane.setBorder(null);
        scrollPane.setBackground(new Color(255, 255, 255));
        pnlCenter.add(scrollPane);

        tblHang.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        tblHang.getTableHeader().setOpaque(false);
        tblHang.getTableHeader().setBackground(new Color(36, 136, 203));
        tblHang.getTableHeader().setForeground(new Color(255, 255, 255));
        tblHang.setRowHeight(25);

        dsHang = HangBUS.getDanhSachHang();
        loadDanhSachHang();
        // ========== TABLE DANH SÁCH NHÂN VIÊN ==========

        // Sự kiện lắng nghe click
        btnTim.addActionListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnDoiTrangThai.addActionListener(this);
        btnNhapExcel.addActionListener(this);
        btnXuatExcel.addActionListener(this);
    }

    // Load danh sách nhân viên
    public void loadDanhSachHang() {
        dtmHang.setRowCount(0);
        // dsHang = HangBUS.getDanhSachHang();

        

        for (HangDTO hang : dsHang) {
            String status = "Hoạt động";
            if (!hang.isStatus()) {
                status = "Không hoạt động";
            }
            Object[] row = { hang.getBrand_id(), hang.getBrand_name(), status };
            dtmHang.addRow(row);
        }
    }

    public void reLoad() {
        dsHang = HangBUS.getDanhSachHang();
        loadDanhSachHang();
    }

    public void xuLyTimKiem() {
        String keyword = txtTimKiem.getText();
        dtmHang.setRowCount(0);
        dsHang = HangBUS.searchHang(keyword);
        loadDanhSachHang();
    }

    public void hienThiGiaoDienSua() {
        int selectedRow = tblHang.getSelectedRow();
        if (selectedRow != -1) {
            int hangID = (int) tblHang.getValueAt(selectedRow, 0);
            // Lấy thông tin hãng từ cơ sở dữ liệu dựa trên hangID
            HangDTO hang = HangBUS.getHangByID(hangID);
            if (hang != null) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                            ChiTietHang frame = new ChiTietHang(hang, qlHang);
                            frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin hãng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hãng để sửa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void themHang() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietHang frame = new ChiTietHang(new HangDTO(0, "", true), qlHang);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void doiTrangThai() {
        int selectedRow = tblHang.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
            int hangID = (int) tblHang.getValueAt(selectedRow, 0);
            HangDTO hang = HangBUS.getHangByID(hangID);
            int choice = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn thay dổi trạng thái hãng này?",
                    "Xác nhận thay đổi trạng thái hãng", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                hang.setStatus(!hang.isStatus());
                if (HangBUS.suaHang(hang)) {
                    JOptionPane.showMessageDialog(null, "Thay đổi trạng thái thành công.", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thay đổi trạng thái thất bại.", "Thông báo",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hãng để thay đổi trạng thái.", "Thông báo",
                    JOptionPane.ERROR_MESSAGE);
        }
        loadDanhSachHang();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            themHang();
        } else if (e.getSource() == btnSua) {
            hienThiGiaoDienSua();
        } else if (e.getSource() == btnTim) {
            txtTimKiem.setText("");
            xuLyTimKiem();
        } else if (e.getSource() == btnDoiTrangThai) {
            doiTrangThai();
        } else if (e.getSource() == btnNhapExcel) {
            // Xử lý khi button "Nhập excel" được nhấn
        } else if (e.getSource() == btnXuatExcel) {
            // Xử lý khi button "Xuất excel" được nhấn
        }
    }

}
