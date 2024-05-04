/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.HangDTO;
import DTO.NhanVien;
import DTO.SanPhamDTO;
import DTO.TheLoaiDTO;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FileChooserUI;

import org.apache.poi.hwpf.usermodel.BorderCode;

import BUS.HangBUS;
import BUS.SanPhamBUS;
import BUS.TheLoaiBUS;

/**
 *
 * @author MSI
 */
public class ChiTietSanPhamGUI extends JFrame implements ActionListener {

        public String absolutePath = new File("").getAbsolutePath();
        private JTextField txtIDSP;
        private JTextField txtTenSP;
        private JTextField txtGiaBan;
        private JTextField txtXuatXu;
        private JTextField txtNamSX;
        private JButton btnStatus;
        private JComboBox<String> cbbHang;
        private JComboBox<String> cbbLoai;
        private Map<String, Integer> mapHang;
        private Map<String, Integer> mapLoai;
        private JLabel lblAnhSP;
        private JButton btnThayDoiAnh;
        private JButton btnLuu;
        private JButton btnHuy;
        private JPanel jPanel1;
        private JPanel jPanel2;
        private JLabel jLabel1;
        private ImageIcon icon;

        public SanPhamDTO sanPhamDTO;
        public String action;
        public QuanLySanPhamGUI quanLySanPhamGUI;

        public ChiTietSanPhamGUI() {
                initComponent();
        }

        public ChiTietSanPhamGUI(SanPhamDTO sanPham, String action, QuanLySanPhamGUI quanLySanPhamGUI) {
                this.sanPhamDTO = sanPham;
                this.action = action;
                this.quanLySanPhamGUI = quanLySanPhamGUI;
                initComponent();
                loadData();
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
                txtIDSP.setEnabled(false);
                txtIDSP.setEditable(false);
                txtIDSP.setPreferredSize(new Dimension(200, 40));
                txtIDSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtTenSP = new JTextField();
                txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtGiaBan = new JTextField();
                txtGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtXuatXu = new JTextField();
                txtXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 14));

                txtNamSX = new JTextField();
                txtNamSX.setFont(new Font("Tahoma", Font.PLAIN, 14));

                btnStatus = new JButton();
                btnStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));

                cbbHang = new JComboBox<>();
                cbbHang.setFont(new Font("Tahoma", Font.PLAIN, 14));

                cbbLoai = new JComboBox<>();
                cbbLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));

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
                JLabel labelStatus = new JLabel("Trạng thái:");
                labelStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
                jPanel2.add(labelNamSX);
                jPanel2.add(labelStatus);
                jPanel2.add(txtNamSX);
                jPanel2.add(btnStatus);

                JLabel labelTheLoai = new JLabel("Thể loại:");
                labelTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel labelHangSX = new JLabel("Hãng sản xuất:");
                labelHangSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
                jPanel2.add(labelTheLoai);
                jPanel2.add(labelHangSX);
                jPanel2.add(cbbLoai);
                jPanel2.add(cbbHang);
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

                btnHuy.addActionListener(this);
                btnLuu.addActionListener(this);
                btnStatus.addActionListener(this);
                btnThayDoiAnh.addActionListener(this);
        }

        public void loadComboboxLoai(int maLoai) {
                mapLoai = new HashMap<>();
                ArrayList<TheLoaiDTO> theLoaiDTOs = TheLoaiBUS.getDanhSachTheLoai();
                for (TheLoaiDTO theLoaiDTO : theLoaiDTOs) {
                        if (theLoaiDTO.isStatus()) {
                                mapLoai.put(theLoaiDTO.getCategory_name(), theLoaiDTO.getCategory_id());

                        }
                }
                for (String key : mapLoai.keySet()) {
                        cbbLoai.addItem(key);
                }
                for (Map.Entry<String, Integer> entry : mapLoai.entrySet()) {
                        if (entry.getValue() == maLoai) {
                                cbbLoai.setSelectedItem(entry.getKey());
                                break;
                        }
                }
        }

        public void loadComboboxHang(int maHang) {
                mapHang = new HashMap<>();
                ArrayList<HangDTO> hangDTOs = HangBUS.getDanhSachHang();
                for (HangDTO hangDTO : hangDTOs) {
                        if (hangDTO.isStatus()) {
                                mapHang.put(hangDTO.getBrand_name(), hangDTO.getBrand_id());

                        }
                }
                for (String key : mapHang.keySet()) {
                        cbbHang.addItem(key);
                }
                for (Map.Entry<String, Integer> entry : mapHang.entrySet()) {
                        if (entry.getValue() == maHang) {
                                cbbHang.setSelectedItem(entry.getKey());
                                break;
                        }
                }
        }

        public void loadData() {
                txtIDSP.setText(sanPhamDTO.getProduct_id() + "");
                txtTenSP.setText(sanPhamDTO.getProduct_name());
                txtGiaBan.setText(sanPhamDTO.getOutput_price() + "");
                txtXuatXu.setText(sanPhamDTO.getCountry() + "");
                txtNamSX.setText(sanPhamDTO.getYear_of_product() + "");
                if (sanPhamDTO.isStatus()) {
                        btnStatus.setText("Đang kinh doanh");
                } else {
                        btnStatus.setText("Ngừng kinh doanh");
                }

                loadComboboxHang(sanPhamDTO.getBrand_id());
                loadComboboxLoai(sanPhamDTO.getCategory_id());
                ImageIcon icon = new ImageIcon(absolutePath + sanPhamDTO.getImage_path());
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                icon = new ImageIcon(scaledImage);
                lblAnhSP.setIcon(icon);

        }

        public boolean isValidProductName(String productName) {
                return productName.matches("^[a-zA-Z0-9\\s]+$");
        }

        public boolean isValidOutputPrice(String outputPrice) {
                if (outputPrice.matches("^[0-9]+$")) {
                        int price = Integer.parseInt(outputPrice);
                        return price > 0;
                }
                return false;
        }

        public boolean isValidCountry(String country) {
                return country.matches("^[a-zA-Z\\s]+$");
        }

        public boolean isValidYear(int year) {
                return year > 1900;
        }

        public void luuSanPham() {
                boolean isValid = true;
                String error = "";
                String xuatXu = txtXuatXu.getText();
                String giaBan = txtGiaBan.getText();
                String tenSp = txtTenSP.getText();
                String namSx = txtNamSX.getText();
                if (tenSp.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Tên không được để trống");
                        txtTenSP.setFocusable(true);
                        return;
                }
                if (giaBan.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Giá bán không được để trống");
                        txtGiaBan.setFocusable(true);
                        return;
                }
                if (xuatXu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Xuất xứ không được để trống");
                        txtXuatXu.setFocusable(true);
                        return;
                }
                if (namSx.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Năm sản xuất không được để trống");
                        txtNamSX.setFocusable(true);
                        return;
                }
                if (!isValidCountry(xuatXu)) {
                        isValid = false;
                        error += "Xuất xứ không được chứa ký tự đặt biệt và số\n";
                }
                if (!isValidOutputPrice(giaBan)) {
                        isValid = false;
                        error += "Giá bán phải lớn hơn 0\n";
                }
                if (!isValidProductName(tenSp)) {
                        isValid = false;
                        error += "Tên sản phảm không được chứa ký tự đặt biệt\n";
                }
                if (!isValidYear(Integer.parseInt(namSx))) {
                        isValid = false;
                        error += "Năm phải là số, lớn hơn 1900\n";
                }
                if (isValid) {
                        if (sanPhamDTO.getImage_path() == null) {
                                sanPhamDTO.setImage_path("");
                        }
                        sanPhamDTO.setProduct_name(tenSp);
                        sanPhamDTO.setOutput_price(Integer.parseInt(giaBan));
                        sanPhamDTO.setCountry(xuatXu);
                        sanPhamDTO.setYear_of_product(Integer.parseInt(namSx));
                        sanPhamDTO.setBrand_id(mapHang.get(cbbHang.getSelectedItem()).intValue());
                        sanPhamDTO.setCategory_id(mapLoai.get(cbbLoai.getSelectedItem()).intValue());

                        int choice = JOptionPane.showConfirmDialog(null,
                                        "Xác nhận lưu chi tiết sản phẩm",
                                        "Lưu chi tiết sản phẩm", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                                if (action == "add") {
                                        if (SanPhamBUS.isExistSanPham(sanPhamDTO.getProduct_name())) {
                                                JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại");
                                        } else {
                                                if (SanPhamBUS.themSanPham(sanPhamDTO)) {
                                                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                                                } else {
                                                        JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại");
                                                }
                                        }
                                } else {
                                        if (SanPhamBUS.suaSanPham(sanPhamDTO)) {
                                                JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công");
                                        } else {
                                                JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại");
                                        }
                                }
                        }

                        quanLySanPhamGUI.reLoadData();
                        dispose();
                } else {
                        JOptionPane.showMessageDialog(null, error);
                }
        }

        public void thayDoiTrangThai() {
                String trangThai = btnStatus.getText();
                if (trangThai.equals("Ngừng kinh doanh")) {
                        sanPhamDTO.setStatus(true);
                        btnStatus.setText("Đang kinh doanh");
                } else {
                        sanPhamDTO.setStatus(false);
                        btnStatus.setText("Ngừng kinh doanh");
                }
        }

        public String getPathAfterSrc(String fullPath) {
                int srcIndex = fullPath.indexOf("\\src");
                if (srcIndex != -1) {
                        return fullPath.substring(srcIndex);
                }
                return "";
        }

        public void thayDoiAnh() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        System.out.println(file.getAbsolutePath());
                        String imagePath = getPathAfterSrc(file.getAbsolutePath());
                        if (!imagePath.isEmpty()) {
                                sanPhamDTO.setImage_path(imagePath);
                        }
                }
                ImageIcon icon = new ImageIcon(absolutePath + sanPhamDTO.getImage_path());
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                icon = new ImageIcon(scaledImage);
                lblAnhSP.setIcon(icon);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnHuy) {
                        int choice = JOptionPane.showConfirmDialog(null,
                                        "Xác nhận đóng chi tiết sản phẩm",
                                        "Đóng chi tiết sản phẩm", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                                dispose();
                        }
                }
                if (e.getSource() == btnLuu) {
                        luuSanPham();
                }
                if (e.getSource() == btnStatus) {
                        thayDoiTrangThai();
                }
                if (e.getSource() == btnThayDoiAnh) {
                        thayDoiAnh();
                }
        }

        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                                        ChiTietSanPhamGUI frame = new ChiTietSanPhamGUI(new SanPhamDTO(), "add",
                                                        new QuanLySanPhamGUI());
                                        frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
}
