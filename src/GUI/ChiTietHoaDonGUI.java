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

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import BUS.HoaDonBUS;
import BUS.KhuyenMaiBUS;
import DTO.ChiTietHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.KhuyenMai;

/**
 *
 * @author MSI
 */
public class ChiTietHoaDonGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBillID;
    private JTextField txtAccountID;
    private JTextField txtDate;
    private JTextField txtToTalPrice;
    private JTextField txtCustomerID;
    private JComboBox cmbDiscountCode;
    private DefaultComboBoxModel dcmMaGiamGia;
    private JScrollPane spnSanPham;
    private JLabel lblDSSP;
    private JTable tblSanPham;
    private DefaultTableModel sanPhamModel;
    private JPanel jPanelSanPham;

    private final ButtonGroup buttonGroup = new ButtonGroup();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    ChiTietHoaDonGUI frame = new ChiTietHoaDonGUI();
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
    public ChiTietHoaDonGUI() {

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

        int width = 880;
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

        JPanel panel_5 = new JPanel();
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

        JLabel lblNewLabel_6_3_1_1 = new JLabel("Tổng tiền");
        lblNewLabel_6_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_1);

        txtToTalPrice = new JTextField();
        txtToTalPrice.setPreferredSize(new Dimension(100, 19));
        txtToTalPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtToTalPrice.setColumns(10);
        txtToTalPrice.setEnabled(false);
        panel_5.add(txtToTalPrice);

        JLabel lblNewLabel_6_3_1_3 = new JLabel("Mã giảm giá");
        lblNewLabel_6_3_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_5.add(lblNewLabel_6_3_1_3);

        cmbDiscountCode = new JComboBox();
        dcmMaGiamGia = new DefaultComboBoxModel();
        cmbDiscountCode.setModel(dcmMaGiamGia);
        cmbDiscountCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbDiscountCode.setFocusable(false);
        panel_5.add(cmbDiscountCode);

        JLabel lblNewLabel_7_1_1 = new JLabel("");
        panel_5.add(lblNewLabel_7_1_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_5.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 20, 0));

        JButton btnNewButton = new JButton("Lưu");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chi tiết hóa đơn không?",
                        "Xác nhận huỷ bỏ hóa đơn", JOptionPane.YES_NO_OPTION);
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

        jPanelSanPham = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new GridLayout(0, 1));
        spnSanPham = new JScrollPane();
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
        sanPhamModel = new DefaultTableModel(sanPhamColumns, 0);
        tblSanPham.setModel(sanPhamModel);
        tblSanPham.setEnabled(false);
        spnSanPham.setBorder(null);
        spnSanPham.setBackground(new Color(255, 255, 255));
        spnSanPham.setViewportView(tblSanPham);
        jPanelSanPham.add(panel1, BorderLayout.NORTH);
        jPanelSanPham.add(spnSanPham, BorderLayout.CENTER);

        contentPane.add(jPanelSanPham, BorderLayout.CENTER);

        loadMaGiamGia();

    }

    public HoaDonDTO taoHoaDon() {
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        int id = HoaDonBUS.getSoLuongHoaDon() + 1;
        return hoaDonDTO;
    }

    public ArrayList<ChiTietHoaDonDTO> taoChiTietHoaDon(int billId) {
        ArrayList<ChiTietHoaDonDTO> chiTietHoaDonDTOs = new ArrayList<>();
        return chiTietHoaDonDTOs;
    }

    public void loadMaGiamGia() {
        dcmMaGiamGia.addElement("Mã giảm giá");
        dcmMaGiamGia.setSelectedItem(dcmMaGiamGia.getElementAt(0));
        ArrayList<KhuyenMai> khuyenMais = KhuyenMaiBUS.getDanhSachKhuyenMai();
        for (KhuyenMai khuyenMai : khuyenMais) {
            dcmMaGiamGia.addElement(khuyenMai.getDiscount_code());
        }
    }
}
