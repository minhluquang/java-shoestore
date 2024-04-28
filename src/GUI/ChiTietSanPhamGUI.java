/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.NhanVien;
import DTO.SanPhamDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.apache.poi.hwpf.usermodel.BorderCode;

/**
 *
 * @author MSI
 */
public class ChiTietSanPhamGUI extends JFrame {

        public String absolutePath = new File("").getAbsolutePath();
        private JTextField txtIDSP;
        private JTextField txtTenSP;
        private JTextField txtGiaBan;
        private JTextField txtXuatXu;
        private JTextField txtNamSX;
        private JTextField txtKhuyenMai;
        private JComboBox cbbTheLoai;
        private JComboBox cbbHangSX;
        private JLabel lblAnhSP;
        private JButton btnThayDoiAnh;
        private JButton btnLuu;
        private JButton btnHuy;
        private JPanel jPanel1;
        private JPanel jPanel2;
        private JLabel jLabel1;
        private ImageIcon icon;

        public ChiTietSanPhamGUI() {
                initComponent();
        }

        public ChiTietSanPhamGUI(SanPhamDTO sanPham) {
                initComponent();
                loadData(sanPham);
        }

        public void initComponent() {

                addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                                int choice = JOptionPane.showConfirmDialog(null,
                                                "Bạn có muốn đóng chi tiết sản phẩm không?",
                                                "Xác nhận đóng chi tiết sản phẩm", JOptionPane.YES_NO_OPTION);
                                if (choice == JOptionPane.YES_OPTION) {
                                        dispose();
                                }
                        }
                });

                int width = 880;
                int height = 400;
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                setBounds(100, 100, width, height);
                setLocationRelativeTo(null);
                setTitle("Thông tin sản phẩm");
                setBackground(new Color(230, 230, 230));
                setLayout(new BorderLayout(10, 10));

                jPanel1 = new JPanel();
                jPanel1.setBackground(new java.awt.Color(36, 136, 203));
                jPanel1.setPreferredSize(new java.awt.Dimension(1040, 30));
                jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));

                jLabel1 = new JLabel();
                jLabel1.setFont(new Font("Tahoma", Font.BOLD, 18));
                jLabel1.setText("Thông tin chi tiết sản phẩm");
                jLabel1.setForeground(Color.WHITE);
                jPanel1.add(jLabel1);
                add(jPanel1, BorderLayout.NORTH);

                txtIDSP = new JTextField();
                // txtIDSP.setBorder(BorderFactory.createTitledBorder(null, "ID sản phẩm",
                // TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                // new Font("Tahoma", Font.PLAIN, 14)));
                txtIDSP.setPreferredSize(new Dimension(200, 40));
                txtIDSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtTenSP = new JTextField();
                // txtTenSP.setBorder(BorderFactory.createTitledBorder(null, "Tên sản phẩm",
                // TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                // new Font("Tahoma", Font.PLAIN, 14)));
                // txtTenSP.setPreferredSize(new Dimension(200, 40));
                txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtGiaBan = new JTextField();
                // txtGiaBan.setBorder(
                // BorderFactory.createTitledBorder(null, "Giá bán",
                // TitledBorder.DEFAULT_JUSTIFICATION,
                // TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
                // txtGiaBan.setPreferredSize(new Dimension(200, 40));
                txtGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtXuatXu = new JTextField();
                // txtXuatXu.setBorder(
                // BorderFactory.createTitledBorder(null, "Xuất xứ",
                // TitledBorder.DEFAULT_JUSTIFICATION,
                // TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
                // txtXuatXu.setPreferredSize(new Dimension(200, 40));
                txtXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtNamSX = new JTextField();
                // txtNamSX.setBorder(BorderFactory.createTitledBorder(null, "Năm sản xuất",
                // TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                // new Font("Tahoma", Font.PLAIN, 14)));
                // txtNamSX.setPreferredSize(new Dimension(200, 40));
                txtNamSX.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtKhuyenMai = new JTextField();
                // txtKhuyenMai.setBorder(
                // BorderFactory.createTitledBorder(null, "Khuyến mãi",
                // TitledBorder.DEFAULT_JUSTIFICATION,
                // TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
                // txtKhuyenMai.setPreferredSize(new Dimension(200, 40));
                txtKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 14));

                cbbHangSX = new JComboBox();
                // cbbHangSX.setBorder(BorderFactory.createTitledBorder(null, "Hãng sản xuất",
                // TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                // new Font("Tahoma", Font.PLAIN, 14)));
                // cbbHangSX.setPreferredSize(new Dimension(200, 40));
                cbbHangSX.setFont(new Font("Tahoma", Font.PLAIN, 14));

                cbbTheLoai = new JComboBox();
                // cbbTheLoai.setBorder(
                // BorderFactory.createTitledBorder(null, "Thể loại",
                // TitledBorder.DEFAULT_JUSTIFICATION,
                // TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 14)));
                // cbbTheLoai.setPreferredSize(new Dimension(200, 40));
                cbbTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));

                lblAnhSP = new JLabel("");
                lblAnhSP.setPreferredSize(new Dimension(200, 200));
                lblAnhSP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                icon = new ImageIcon(absolutePath + "/src/images/icons/MyPictures.24.png");
                btnThayDoiAnh = new JButton("Thay đổi ảnh", icon);
                btnThayDoiAnh.setPreferredSize(new Dimension(200, 40));
                btnThayDoiAnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
                icon = new ImageIcon(absolutePath + "/src/images/icons/save.24.png");
                btnLuu = new JButton("Lưu", icon);
                // btnLuu.setPreferredSize(new Dimension(200, 40));
                btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 14));
                icon = new ImageIcon(absolutePath + "/src/images/icons/delete.png");
                btnHuy = new JButton("Hủy", icon);
                // btnHuy.setPreferredSize(new Dimension(200, 40));
                btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 14));

                JPanel jPanel3 = new JPanel(new BorderLayout(5, 5));
                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                JPanel jPanel4 = new JPanel(new BorderLayout(5, 5));
                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                JPanel jPanel5 = new JPanel(new BorderLayout(5, 5));
                jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                jPanel2 = new JPanel(new GridLayout(0, 2, 5, 5));
                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                // jPanel2.setPreferredSize(getPreferredSize());
                JLabel labelIDSP = new JLabel("ID sản phẩm:");
                labelIDSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel labelTenSP = new JLabel("Tên sản phẩm:");
                labelTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
                jPanel2.add(labelIDSP);
                jPanel2.add(labelTenSP);
                jPanel2.add(txtIDSP);
                jPanel2.add(txtTenSP);

                JLabel labelGiaBan = new JLabel("Giá bán:");
                labelGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel labelXuatXu = new JLabel("Xuất xứ:");
                labelXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 14));
                jPanel2.add(labelGiaBan);
                jPanel2.add(labelXuatXu);
                jPanel2.add(txtGiaBan);
                jPanel2.add(txtXuatXu);

                JLabel labelNamSX = new JLabel("Năm sản xuất:");
                labelNamSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel labelKhuyenMai = new JLabel("Khuyến mãi:");
                labelKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 14));
                jPanel2.add(labelNamSX);
                jPanel2.add(labelKhuyenMai);
                jPanel2.add(txtNamSX);
                jPanel2.add(txtKhuyenMai);

                JLabel labelTheLoai = new JLabel("Thể loại:");
                labelTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel labelHangSX = new JLabel("Hãng sản xuất:");
                labelHangSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
                jPanel2.add(labelTheLoai);
                jPanel2.add(labelHangSX);
                jPanel2.add(cbbTheLoai);
                jPanel2.add(cbbHangSX);
                jPanel2.add(new JLabel(""));

                jPanel3.add(lblAnhSP, BorderLayout.NORTH);
                jPanel3.add(btnThayDoiAnh, BorderLayout.CENTER);
                jPanel4.add(jPanel3, BorderLayout.NORTH);

                JPanel btnPanel = new JPanel(new FlowLayout());
                btnPanel.setBackground(Color.WHITE);
                btnPanel.add(btnLuu);
                btnPanel.add(btnHuy);
                jPanel4.add(btnPanel, BorderLayout.SOUTH);

                jPanel5.add(jPanel2, BorderLayout.CENTER);
                jPanel5.add(jPanel4, BorderLayout.EAST);

                add(jPanel5);
        }

        public void loadCombobox(){

        }

        public void loadData(SanPhamDTO sanPham) {
                txtIDSP.setText(absolutePath);
                // txtTenSP
                // txtGiaBan
                // txtXuatXu
                // txtNamSX
                // txtKhuyenMai
                // cbbTheLoai
                // cbbHangSX
                // lblAnhSP
        }

        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                                        ChiTietSanPhamGUI frame = new ChiTietSanPhamGUI(new SanPhamDTO());
                                        frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
}
